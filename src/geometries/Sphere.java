package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    Point3D center;

    public Sphere(double _radius, Point3D center) {
        super(_radius);
        this.center = center;
    }

    public Sphere(RadialGeometry _rg, Point3D center) {
        super(_rg);
        this.center = center;
    }

    @Override
    public String toString() {
        return super.toString() + ", center=" + center;
    }

    public Point3D getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
