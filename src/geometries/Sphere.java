package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Sphere class represents a sphere in 3D Cartesian coordinate system
 * by center and radius
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Sphere extends RadialGeometry {
    /**
     * A point for the center of the radial geometry
     */
    protected Point3D _center;

    /****************constractors****************/
    public Sphere(double radius, Point3D center) {
        /**
         * constractor by double and Point3D values
         * @param _radius for the length of the radius
         * @param _center for the center point
         */
        super(radius);
        this._center = center;
    }

    /**************getters***************/
    public Point3D getCenter() {
        return _center;
    }

    /**************methods****************/
    @Override
    public String toString() {
        return super.toString() + ", center= " + _center;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
