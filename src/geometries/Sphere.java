package geometries;

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
     * @param radius of sphere
     * @param center of sphere
     */
    public Sphere(double radius, Point3D center) {
        /**
         * constractor by double and Point3D values
         * @param _radius for the length of the radius
         * @param _center for the center point
         */
        super(radius);
        this._center = center;
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
     * @param ray
     * @return
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 =  ray.get_start();
        Vector v =  ray.get_direction();
        Vector u;
        try {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException ex){
            return List.of(ray.getPoint(_radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = tm == 0 ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);
        if (thSquared <= 0) return null;
        double th =  alignZero(Math.sqrt(thSquared));
        //if (th == 0) return null;
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getPoint(t1), ray.getPoint(t2));
        //if (t1 > 0) return List.of(ray.getPoint(t1));
        else return List.of(ray.getPoint(t2));
    }
}
