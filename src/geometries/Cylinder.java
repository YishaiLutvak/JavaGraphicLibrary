package geometries;
import static primitives.Util.isZero;
import primitives.*;

/**
 * Cilinder class represents a cylinder in 3D Cartesian coordinate system
 * by radius, ray that start in the base, and height from the base
 *
 * @author Michael Bergshtein and Ishay Lutvak
 */
public class Cylinder extends Tube {
    protected double _height;

    /****************Constructors***************/
    /**
     * constactor by two doubles and Ray values
     *
     * @param radius for radius of Tube
     * @param ray    for center of tube
     * @param height for height of tube
     */
    public Cylinder(double radius, Ray ray, double height) {
        super(radius, ray);
        this._height = height;
    }

    /************getters************/
    /**
     * @return height
     */
    public double get_height() {
        return _height;
    }


    /****************methods*****************/

    /**
     * getNormal functiom
     * @param p point on the cylinder. The normal is from point p
     * @return a normal in the point
     */
    @Override
    public Vector getNormal(Point3D p) {

        //The point is on the base that contains the start point
        //Checks whether the point is on the center of the base or not
        if (p.equals(_axisRay.get_start()) ||
                isZero(p.subtract(_axisRay.get_start()).dotProduct(_axisRay.get_direction())))
            return _axisRay.get_direction().scale(-1);

        //The point is on the base that not contains the start point
        //Checks whether the point is on the center of the base or not
        Point3D centerTop = new Point3D(_axisRay.get_start().add(_axisRay.get_direction().scale(_height)));
        if (p.equals(centerTop) ||
                isZero(p.subtract(centerTop).dotProduct(_axisRay.get_direction())))
            return _axisRay.get_direction();

        //The point is not on the Cylinder bases
        else
            return super.getNormal(p);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", height= " + _height;
    }
}
