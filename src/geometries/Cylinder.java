package geometries;

import primitives.*;

/**
 * Cilinder class represents a cylinder in 3D Cartesian coordinate system
 * by radius, ray that start in the base, and height from the base
 * @author Michael Bergshtein and Ishay Lutvak
 */
public class Cylinder extends Tube {
    protected double _height;

    /****************Constructors***************/
    /**
     * constactor by two doubles and Ray values
     * @param radius for radius of Tube
     * @param ray for center of tube
     * @param height for height of tube
     */
    public Cylinder (double radius, Ray ray, double height) {
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

    @Override
    public Vector getNormal(Point3D p) { return null; }

    @Override
    public String toString() {
        return super.toString() +
                ", height= " + _height;
    }
}
