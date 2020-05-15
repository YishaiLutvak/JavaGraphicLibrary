package geometries;

import elements.Material;
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
     *
     * @param emissionLight
     * @param material
     * @param radius
     * @param center
     */
    public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
        super(emissionLight, material, radius);
        this._center = center;
    }

    /**
     *
     * @param emissionLight
     * @param radius
     * @param center
     */
    public Sphere(Color emissionLight, double radius, Point3D center) {
       this(emissionLight, Material.DEFAULT, radius, center);
    }

    /**
     * constractor by double and Point3D values
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
     * @return a list of intersect points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 =  ray.get_start();
        Vector v =  ray.get_direction();
        Vector u;
        try {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException ex){
            return List.of(new GeoPoint(this,ray.getPoint(_radius)));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = tm == 0 ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);
        if (thSquared <= 0) return null;
        double th =  Math.sqrt(thSquared);
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
        else
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
    }
}
