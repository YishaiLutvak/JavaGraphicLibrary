package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
    Ray ray;

    public Tube(double _radius, Ray ray) {
        super(_radius);
        this.ray = ray;
    }

    public Tube(RadialGeometry _rg, Ray ray) {
        super(_rg);
        this.ray = ray;
    }

    public Ray getRay() {
        return ray;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", ray=" + ray;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
