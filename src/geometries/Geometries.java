package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * composite class which include a collection of any base and composite geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Geometries implements Intersectable {

    private List<Intersectable> _geometries;

    public Geometries() {
        _geometries = new LinkedList<Intersectable>();
    }

    /**
     * Geometries constructor allowing to add zero or more geometries
     * while  creating it
     * @param geometries to add to the collection
     */
    public Geometries(Intersectable... geometries) {
        _geometries = new LinkedList<Intersectable>();
        add(geometries);
    }

    /**
     * The function add allows to add zero or more geometries to the
     * composite geometry
     * @param geometries to add to the collection
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            _geometries.add(geo);
        }
    }

    /**
     * find intersections of all the geometries parts
     * @param ray that intersects the geometries
     * @param max
     * @return a list of intersect points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double max) {
        List<GeoPoint> intersections = null;
        for (Intersectable geo : _geometries) {
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
