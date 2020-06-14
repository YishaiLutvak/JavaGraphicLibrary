package geometries;

import primitives.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import java.util.List;

/**
 * Sphere class represents a sphere in 3D Cartesian coordinate system
 * by center and radius
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Sphere extends RadialGeometry {
    /**
     * A point for the center of the radial geometry
     */
    protected Point3D _center;

    /****************constractors****************/
    /**
     * * constructor by double and Point3D values
     * @param emissionLight for color of Sphere
     * @param material the material of the Sphere
     * @param radius for the length of the radius
     * @param center for the center point
     */
    public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
        super(emissionLight, material, radius);
        this._center = center;

        this.box._max_X = _center.get_x().get() + _radius;
        this.box._max_Y = _center.get_y().get() + _radius;
        this.box._max_Z = _center.get_z().get() + _radius;

        this.box._min_X = _center.get_x().get() - _radius;
        this.box._min_Y = _center.get_y().get() - _radius;
        this.box._min_Z = _center.get_z().get() - _radius;
    }

    /**
     * constructor by double and Point3D values.  material gets default values
     * @param emissionLight for color of Sphere
     * @param radius for the length of the radius
     * @param center for the center point
     */
    public Sphere(Color emissionLight, double radius, Point3D center) {
       this(emissionLight, Material.DEFAULT, radius, center);
    }

    /**
     * constructor by double and Point3D values. emissionLight and material gets default values
     * @param radius for the length of the radius
     * @param center for the center point
     */
    public Sphere(double radius, Point3D center) {
        this(Color.BLACK, Material.DEFAULT, radius, center);
    }

    /**************getters***************/
    /**
     * @return center
     */
    public Point3D getCenter() {
        return _center;
    }

    /**************methods****************/

    /**
     * getNormal function
     * @param p point on the sphere. The normal is from p
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point3D p) {
        // Calculate and return the normal vector
        return p.subtract(_center).normalized();
    }

    @Override
    public String toString() {
        return super.toString() + ", center= " + _center;
    }

    /**
     *
     * @param ray that intersect the sphere
     * @param max the maximum range from the source of the ray to the point
     * @return a list of intersect points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double max) {
        Point3D p0 =  ray.get_start();
        Vector v =  ray.get_direction();
        Vector u;
        try {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException ex){
            if (alignZero(_radius-max) <= 0)
                return List.of(new GeoPoint(this,ray.getPoint(_radius)));
            else
                return null;
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = tm == 0 ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);
        if (thSquared <= 0)
            return null;
        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0)
            return null;
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th); //Always t2 > t1 Because th is positive
        if (t2 <= 0) // t1 < t2 <= 0 < max
            return null;
        if (t1 > 0 && (alignZero(t2 - max) <= 0) ) // 0 < t1 < t2 < max
            return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
        if (t1 > 0 && (alignZero(t1 - max) <= 0)) // 0 < t1 < max < t2
            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        if (t2 > 0 && (alignZero(t2 - max) <= 0)) // t1 <= 0 < t2 < max
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        return null; // 0 < max < t1 < t2
    }
}
