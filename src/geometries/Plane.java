package geometries;

import elements.Material;
import primitives.*;
import java.util.List;

import static primitives.Util.*;

/**
 * Plane class represents a plan in 3D Cartesian coordinate system
 * by point and normal
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Plane extends Geometry{
    protected Point3D _p;
    protected Vector _normal;

    /**********constractors**********/

    private void initNormal(Point3D p1, Point3D p2, Point3D p3) {
        _p = new Point3D(p1);

        //Calculate the normal by creating two vectors between the points
        Vector u = new Vector(p2.subtract(p1));
        Vector v = new Vector(p3.subtract(p1));
        Vector n = u.crossProduct(v);
        n.normalize();

        _normal = n.scale(-1);
    }

    /**
     *
     * @param emissionLight
     * @param material
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight, material);

        _p = new Point3D(p1);

        //Calculate the normal by creating two vectors between the points
        Vector u = new Vector(p2.subtract(p1));
        Vector v = new Vector(p3.subtract(p1));
        Vector n = u.crossProduct(v);
        n.normalize();

        _normal = n.scale(-1);
    }

    /**
     *
     * @param emissionLight
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        this(emissionLight,Material.DEFAULT,p1, p2, p3);
    }

    /**
     *constractor by 3 Point3D
     * @param p1 for first point in plane
     * @param p2 for second point in plane
     * @param p3 for third point in plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this(Color.BLACK,Material.DEFAULT,p1, p2, p3);
    }

    /**
     *
     * @param emissionLight
     * @param material
     * @param point
     * @param vector
     */
    public Plane(Color emissionLight, Material material,Point3D point, Vector vector) {
        super(emissionLight,material);
        this._p = new Point3D(point);
        this._normal = new Vector(vector);
    }

    /**
     *
     * @param emissionLight
     * @param point
     * @param vector
     */
    public Plane(Color emissionLight,Point3D point, Vector vector) {
        this(emissionLight, Material.DEFAULT, point, vector);
    }

    /**
     * constractor by point and normal
     * @param point for point in plane
     * @param vector for the vector that vertical to plane
     */
    public Plane(Point3D point, Vector vector) {
        this(Color.BLACK, Material.DEFAULT, point, vector);
    }

    /*************getters***********/
    /**
     * @return point
     */
    public Point3D getPoint() {
        return new Point3D(_p);
    }

    /**
     * @return normal
     */
    public Vector getNormal() {
        return new Vector(_normal);
    }

    /*************methods*************/
    @Override
    public Vector getNormal(Point3D p) {return this.getNormal(); }

    @Override
    public String toString() {
        return "point= " + _p +
                ", normal= " + _normal;
    }

    /**
     *
     * @param ray that intersect the plane
     * @return a list of intersect points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Vector p0Q;
        try {
            p0Q = _p.subtract(ray.get_start());
        }catch (IllegalArgumentException ex) {
            return null; // ray start from point Q - no intersections
        }

        double nv = _normal.dotProduct(ray.get_direction());
        if (isZero(nv))
            return null; // ray is parallel to the plane - no intersections

        double t = alignZero(_normal.dotProduct(p0Q) / nv);
        return t <= 0 ? null : List.of(new GeoPoint (this,ray.getPoint(t)));
        // if ray start from plane there is no intersections
        // else return point intersections
    }
}
