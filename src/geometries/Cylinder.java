package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cilinder is a class that represent a cylinder in 3D
 * by radius ray that start in the base and height from the base
 * @author Michael Bergshtein and Ishay Lutvak
 */
public class Cylinder extends RadialGeometry {
    Ray ray;
    double height;

    /****************Constructors***************/
    public Cylinder(double _radius, Ray ray, double height) {
        super(_radius);
        this.ray = ray;
        this.height = height;
    }

    public Cylinder(RadialGeometry _rg, Ray ray, double height) {
        super(_rg);
        this.ray = ray;
        this.height = height;
    }

    /****************methods*****************/
    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
