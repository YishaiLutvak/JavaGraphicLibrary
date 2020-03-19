package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends RadialGeometry {
    Point3D CenterBottom;
    Ray ray;
    double height;

    public Cylinder(double _radius, Point3D centerBottom, Ray ray, double height) {
        super(_radius);
        CenterBottom = centerBottom;
        this.ray = ray;
        this.height = height;
    }

    public Cylinder(RadialGeometry _rg, Point3D centerBottom, Ray ray, double height) {
        super(_rg);
        CenterBottom = centerBottom;
        this.ray = ray;
        this.height = height;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
