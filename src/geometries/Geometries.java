package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * composite class which include a collection of any base and composite geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Geometries extends Intersectable {

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
        //Initial the max/min values in order that the first geometries
        //will give the right value
        this.box._max_X = Double.NEGATIVE_INFINITY;
        this.box._min_X = Double.POSITIVE_INFINITY;
        this.box._max_Y = Double.NEGATIVE_INFINITY;
        this.box._min_Y = Double.POSITIVE_INFINITY;
        this.box._max_Z = Double.NEGATIVE_INFINITY;
        this.box._min_Z = Double.POSITIVE_INFINITY;
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
            if (geo.box._max_X > this.box._max_X)
                this.box._max_X = geo.box._max_X;
            if (geo.box._min_X < this.box._min_X)
                this.box._min_X = geo.box._min_X;
            if (geo.box._max_Y > this.box._max_Y)
                this.box._max_Y = geo.box._max_Y;
            if (geo.box._min_Y < this.box._min_Y)
                this.box._min_Y = geo.box._min_Y;
            if (geo.box._max_Z > this.box._max_Z)
                this.box._max_Z = geo.box._max_Z;
            if (geo.box._min_Z < this.box._min_Z)
                this.box._min_Z = geo.box._min_Z;
        }
    }

    /**
     * find intersections of all the geometries parts
     * @param ray that intersects the geometries
     * @param max the maximum range from the source of the ray to the point
     * @return a list of intersect points
     */
    @Override
    protected List<GeoPoint> findIntersections(Ray ray, double max) {
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
