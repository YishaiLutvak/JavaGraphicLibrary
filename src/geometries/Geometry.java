package geometries;

import primitives.*;

/**
 * Interface for all basic geometries providing mandatory methods
 * @author Michael Bergshtein and Yishai Lutvak
 */
public interface Geometry extends Intersectable  {
    /**
     * Calculate a unit vector orthogonal to the surface or the geometry body
     * at the given point. Basic assumption - the point lays in the surface
     * @param p in the surface
     * @return unit orthogonal vector
     */
    Vector getNormal(Point3D p);
}
