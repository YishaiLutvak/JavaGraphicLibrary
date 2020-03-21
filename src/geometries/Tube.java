package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class that represent infinite cylinder in 3D
 * @author Michael Bergshtein and Ishay Lutvak
 */
public class Tube extends RadialGeometry {
    Ray ray;

    /*********************constractors*********************/
    public Tube(double _radius, Ray ray) {
        super(_radius);
        this.ray = ray;
    }

    public Tube(RadialGeometry _rg, Ray ray) {
        super(_rg);
        this.ray = ray;
    }
/***************getters*********************/
    public Ray getRay() {
        return ray;
    }

    /******************methods****************/
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
