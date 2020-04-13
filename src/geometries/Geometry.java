package geometries;

import primitives.*;

/**
 *Geometry is the common interface for all geometries
 *that using the getNormal() function
 */
public interface Geometry extends Intersectable  {
    Vector getNormal(Point3D p);
}
