package geometries;

import primitives.Material;
import primitives.Color;

import static primitives.Util.isZero;

/**
 * Abstract class to represent all geometry shapes
 * that have a radius like circle cylinder and tube
 * by length of the radius
 * @author Michael Bergshtein and Yishai Lutvak
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * radius of radial geometry
     */
    protected double _radius;

    /**************constractors*****************/


    /**
     * constructor for a new extended RadialGeometry object.
     *
     * @param radius the radius of the RadialGeometry
     * @param emissionLight for color of RadialGeometry
     * @param material the material of the RadialGeometry
     * throws Exception in case of negative or zero radius
     */
    public RadialGeometry(Color emissionLight, Material material, double radius ) {
        super(emissionLight, material);
        if (isZero(radius) || (radius < 0.0))
            throw new IllegalArgumentException("radius " + radius + " is not valid");
        this._radius = radius;
    }

    /**
     * constructor. material gets default value
     * @param emissionLight for color of RadialGeometry
     ** @param radius the radius of the RadialGeometry
     */
    public RadialGeometry(Color emissionLight, double radius) {
        this(emissionLight, Material.DEFAULT ,radius);
    }

    /**
     * constactor by double value
     * @param radius for the length of the radius
     */
    public RadialGeometry(double radius) {
       this(Color.BLACK, Material.DEFAULT, radius);
    }

    /**
     * copy constractor
     * @param other for the length of the radius
     */
    public RadialGeometry(RadialGeometry other) {
        this(other._emissionLight, other._material, other._radius);
    }


    /************getters***********/

    /**
     * @return radius
     */
    public double get_radius() {
        return _radius;
    }

    /************admin************/

    @Override
    public String toString() {
        return "radius= " + _radius;
    }
}

