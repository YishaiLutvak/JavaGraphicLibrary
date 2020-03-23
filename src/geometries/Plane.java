package geometries;

import primitives.*;

/**
 * Plane class represents a plan in 3D Cartesian coordinate system
 * by point and normal
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Plane implements Geometry{
   protected Point3D _p;
   protected Vector _normal;

    /**********constractors**********/

    /**
     *constractor by 3 Point3D
     * @param point_A for first point in plane
     * @param point_B for second point in plane
     * @param point_C for third point in plane
     */
    public Plane(Point3D point_A, Point3D point_B, Point3D point_C) {
        _p = point_A;
        _normal = null;
    }

    /**
     * constractor by point and normal
     * @param point for point in plane
     * @param vector for the vector that vertical to plane
     */
    public Plane(Point3D point, Vector vector) {
        this._p = point;
        this._normal = vector;
    }

    /*************getters***********/

    public Point3D get_point() {
        return _p;
    }

    public Vector get_normal() {
        return _normal;
    }

    /*************methods*************/
    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    public Vector getNormal() { return getNormal(_p); }

    @Override
    public String toString() {
        return "point= " + _p +
                ", normal= " + _normal;
    }
}
