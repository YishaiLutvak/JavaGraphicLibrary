package geometries;

import primitives.*;

import java.util.Objects;

/**
 * Class that represent infinite cylinder in 3D Cartesian coordinate system
 * by radius ang ray
 * @author Michael Bergshtein and Yishay Lutvak
 */
public class Tube extends RadialGeometry {
    /**
     * The ray is located in the center of Tube
     */
    protected Ray _axisRay;

    /*********************constractors*********************/
    /**
     * constractor by double and Ray values
     * @param radius for the length of the radius
     * @param ray for the center of the tube
     */
    public Tube(double radius, Ray ray) {
        super(radius);
        this._axisRay = ray;
    }

    /******************getters*******************/

    /**
     * @return axisRay
     */
    public Ray getRay() {
        return _axisRay;
    }

    /******************methods******************/
    /**
     * getNormal function
     * @param p point on the sphere. The normal is from p
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point3D p){
        //Calculate the
        double t = _axisRay.get_direction().dotProduct(p.subtract(_axisRay.get_start()));
        Point3D O = new Point3D (_axisRay.get_start().add(_axisRay.get_direction().scale(t)));
        return new Vector(p.subtract(O));
    }

    @Override
    public String toString() {
        return super.toString()+
                ", ray= " + _axisRay;
    }
}
