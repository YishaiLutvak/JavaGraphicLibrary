package geometries;

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
     *
     * @param p point on the cylinder. The normal is from p
     * @return a normal in the point
     */
    @Override
    public Vector getNormal(Point3D p) {
        //Calculate the point on the _axisRay the vector from that point and p is orthogonal to the ray
        double t = _axisRay.get_direction().dotProduct(p.subtract(_axisRay.get_start()));
        Point3D O = new Point3D(_axisRay.get_start().add(_axisRay.get_direction().scale(t)));

        //The point is not on the Cylinder bases
        if (p.distance(O) == _radius) {
            return new Vector(p.subtract(O)).normalized();
        }
        //The point is on the base that contains the start point
        else if (p.distance(O) < _radius && O.equals(_axisRay.get_start())) {
            return new Vector(_axisRay.get_direction().scale(-1));
        }

        //The point is on the base that not contains the start point
        return  new Vector(_axisRay.get_direction());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", height= " + _height;
    }
}
