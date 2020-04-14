package geometries;

import primitives.Point3D;
import primitives.Ray;
import java.util.List;

/**
 * Interface for Composite Design Pattern the Composite Class - Geometries the
 * basic Classes - all the specific geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public interface Intersectable {
    /**
     * The functions looks for intersection points between a basic or a composite
     * geometry and a given ray. the function returns null if there are no intersections
     * @param ray the ray to intersect a geometries
     * @return list of intersection points
     */
    List<Point3D> findIntersections(Ray ray);
}
