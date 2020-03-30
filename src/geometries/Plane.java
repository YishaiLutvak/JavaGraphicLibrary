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

        //Calculate the normal by creating two vectors between the points
        Vector u = new Vector(point_B.subtract(point_A));
        Vector v = new Vector(point_C.subtract(point_A));
        Vector n = u.crossProduct(v);
        n.normalize();

        _normal = n.scale(-1);
    }

    /**
     * constractor by point and normal
     * @param point for point in plane
     * @param vector for the vector that vertical to plane
     */
    public Plane(Point3D point, Vector vector) {
        this._p = new Point3D(point);
        this._normal = new Vector(vector);
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
}
