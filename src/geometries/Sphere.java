package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class to represent a sphere in 3D
 * by center and radius
 * @author Michael Bergshtein and Ishai Lutvak
 */
public class Sphere extends RadialGeometry {
    Point3D center;

    /*****************constractors**************/
    public Sphere(double _radius, Point3D center) {
        super(_radius);
        this.center = center;
    }

    public Sphere(RadialGeometry _rg, Point3D center) {
        super(_rg);
        this.center = center;
    }

    /**************getters and setters***************/
    public Point3D getCenter() {
        return center;
    }

    /************methods****************/
    @Override
    public String toString() {
        return super.toString() + ", center=" + center;
    }



    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
