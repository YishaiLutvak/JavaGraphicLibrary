package geometries;

import primitives.*;

/**
 * Plane is a class to represent a plan in 3D
 * by point and normal
 * @author Michael Bergshtein and Ishai Lutvak
 */
public class Plane implements Geometry{
    Point3D _p;
    Vector _normal;

    /********constractors**********/
    public Plane(Point3D _p, Vector _normal) {
        this._p = _p;
        this._normal = _normal;
    }

    public Plane(Point3D vertex_x, Point3D vertex_y, Point3D vertex_z) {
        _p = vertex_x;
        _normal = null;
    }

    /*************methods************/
    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    public Vector getNormal() {
        return getNormal(null);
    }//מה זה?
}
