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
    protected Color _emissionLight;
    protected Material _material;

    //*********************Constructors************************//

    public Geometry(Color emmissionLight, Material material) {
        this._emissionLight = emmissionLight;
        this._material = material;
    }

    /**
     *
     * @param emmissionLight
     */
    public Geometry(Color emmissionLight) {
        this(emmissionLight,new Material(0d,0d,0));
       /* this._emissionLight = emmissionLight;
        this._material = new Material(0d,0d,0);*/
    }
    /**
     *
     */
    public Geometry() {
        this(Color.BLACK);
        /*_emissionLight = Color.BLACK;*/
    }

    //*********************Getter************************//

    /**
     *
     * @return
     */
    public Color getEmissionLight() {
        return _emissionLight;
    }

    /**
     *
     * @return
     */
    public Material getMaterial() {
        return _material;
    }

    //**********************abstract methods*********************//

    public abstract Vector getNormal(Point3D p);

}
