package geometries;

import primitives.*;

import java.util.Objects;

import static primitives.Util.isZero;

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
        //Calculate the normal of the tube from point p
        //O is projection of P on cylinder's ray
        Point3D O;
        //According to the formula: t = v ∙ (P − P0)
        double t = _axisRay.get_direction().dotProduct(p.subtract(_axisRay.get_start()));
        //Make sure you don't multiply in Scalar Zero
        if (!isZero(t))
            //According to the formula: O = P0 + t ∙ v
            O = new Point3D(_axisRay.get_start().add(_axisRay.get_direction().scale(t)));
        else
            O = _axisRay.get_start();
        //Calculate and return the normal vector
        return p.subtract(O).normalized();
    }

    @Override
    public String toString() {
        return super.toString()+
                ", ray= " + _axisRay;
    }
}
