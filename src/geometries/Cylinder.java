package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends RadialGeometry {
    Ray ray;
    double height;

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

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
