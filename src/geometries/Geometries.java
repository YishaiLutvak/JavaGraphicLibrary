package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * composite class which include a collection of any base and composite geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Geometries /*implements Intersectable*/ extends Intersectable2 {

    private List<Intersectable2> _geometries;

    public Geometries() {
        _geometries = new LinkedList<Intersectable2>();
    }

    /**
     * Geometries constructor allowing to add zero or more geometries
     * while  creating it
     * @param geometries to add to the collection
     */
    public Geometries(Intersectable2... geometries) {
        _geometries = new LinkedList<Intersectable2>();
        add(geometries);
    }

    /**
     * The function add allows to add zero or more geometries to the
     * composite geometry
     * @param geometries to add to the collection
     */
    public void add(Intersectable2... geometries) {
        for (Intersectable2 geo : geometries) {
            _geometries.add(geo);
        }
    }

    /**
     * find intersections of all the geometries parts
     * @param ray that intersects the geometries
     * @param max the maximum range from the source of the ray to the point
     * @return a list of intersect points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double max) {
        List<GeoPoint> intersections = null;
        for (Intersectable2 geo : _geometries) {
            List<GeoPoint> tempIntersections = geo.findIntersections(ray,max);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<GeoPoint>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}
