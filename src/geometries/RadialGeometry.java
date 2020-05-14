package geometries;

import elements.Material;
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

    private void initRadius(double radius) {
        if (isZero(radius) || (radius < 0.0))
            throw new IllegalArgumentException("radius " + radius + " is not valid");
        this._radius = radius;
    }

    /**
     * constructor for a new extended RadialGeometry object.
     *
     * @param radius the radius of the RadialGeometry
     * @param material the material of the RadialGeometry
     * @throws Exception in case of negative or zero radius
     */
    public RadialGeometry(Color emissionLight, Material material, double radius ) {
        super(emissionLight, material);
        initRadius(radius);
    }

    /**
     *
     * @param emissionLight
     * @param radius
     */
    public RadialGeometry(Color emissionLight, double radius) {
        super(emissionLight);
        initRadius(radius);
    }

    /**
     * constactor by double value
     * @param radius for the length of the radius
     */
    public RadialGeometry(double radius) {
        super();
        initRadius(radius);
    }

    /**
     * copy constractor
     * @param other for the length of the radius
     */
    public RadialGeometry(RadialGeometry other) {
        super(other._emissionLight, other._material);
        initRadius(other._radius);
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

