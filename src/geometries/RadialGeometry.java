package geometries;

/**
 * Abstract class to represent all geometry shapes
 * that have a radius like circle cylinder and tube
 * by length of the radius
 * @author Michael Bergshtein and Yishai Lutvak
 */
public abstract class RadialGeometry implements Geometry {
    /**
     * radius of radial geometry
     */
   protected double _radius;

    /**************constractors*****************/

    /**
     * constactor by double value
     * @param radius for the length of the radius
     */
    public RadialGeometry(double radius) {
        this._radius = radius;
    }

    /**
         * copy constractor
     * @param rg for the length of the radius
     */
    public RadialGeometry(RadialGeometry rg) {
        this._radius = rg._radius;
    }

    /************getters***********/

    public double get_radius() {
        return _radius;
    }

    /************admin************/

    @Override
    public String toString() {
        return "radius= " + _radius;
    }
}

