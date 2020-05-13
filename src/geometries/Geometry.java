package geometries;

import elements.Material;
import primitives.*;

/**
 * Interface for all basic geometries providing mandatory methods
 * @author Michael Bergshtein and Yishai Lutvak
 */
public abstract class Geometry implements Intersectable  {
    /**
     * Calculate a unit vector orthogonal to the surface or the geometry body
     * at the given point. Basic assumption - the point lays in the surface
     * @param p in the surface
     * @return unit orthogonal vector
     */
    protected  Color _emmission;
    protected Material _material;

    abstract public Vector getNormal(Point3D p);
    //*********************cons************************//

    public Geometry(Color _emmission, Material _material) {
        this._emmission = _emmission;
        this._material = _material;
    }

    /**
     *
     * @param _emmisionLight
     */
    public Geometry(Color _emmisionLight) {
        this._emmission = _emmisionLight;
        _material = new Material(0d,0d,0);
    }
    /**
     *
     */
    public Geometry() {
        _emmission = Color.BLACK;
    }

    /**
     *
     * @return
     */
    public Color getEmmission() {
        return _emmission;
    }
}
