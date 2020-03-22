package geometries;

import primitives.*;

/**
 * Plane class represents a plan in 3D Cartesian coordinate system
 * by point and normal
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Plane implements Geometry{
    Point3D _point;
    Vector _normal;

    /**********constractors**********/
    /**
     * constractor by point and normal
     * @param point for point in plane
     * @param normal for the vector that vertical to plane
     */
    public Plane(Point3D point, Vector normal) {
        this._point = point;
        this._normal = normal;
    }

    /**
     *constractor by 3 Point3D
     * @param pointA for first point in plane
     * @param pointB for second point in plane
     * @param pointC for third point in plane
     */
    public Plane(Point3D pointA, Point3D pointB, Point3D pointC) {
        _point = pointA;
        _normal = null;
    }

    /*************methods*************/
    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    public Vector getNormal() {
        return getNormal(_point);
    }

    @Override
    public String toString() {
        return "point= " + _point +
                ", normal= " + _normal;
    }
}
