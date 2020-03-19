package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Cylinder extends RadialGeometry {
    Point3D a;
    Point3D b;

    public Cylinder(double _radius, Point3D a, Point3D b) {
        super(_radius);
        this.a = a;
        this.b = b;
    }

    public Cylinder(RadialGeometry _rg, Point3D a, Point3D b) {
        super(_rg);
        this.a = a;
        this.b = b;
    }

    public Point3D getA() {
        return a;
    }

    public Point3D getB() {
        return b;
    }

    @Override
    public String toString() {
        return super.toString()+
                "a=" + a +
                ", b=" + b;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
