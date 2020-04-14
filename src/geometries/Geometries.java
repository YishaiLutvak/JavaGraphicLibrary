package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * composite class which include a collection of any base and composite geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Geometries implements Intersectable {

    private List<Intersectable> _geometries = new ArrayList<Intersectable>();

    /*public Geometries() {
        _geometries = new ArrayList<Intersectable>();
    }*/

    /**
     * Geometries constructor allowing to add zero or more geometries
     * while  creating it
     * @param geometries to add to the collection
     */
    public Geometries(Intersectable... geometries) {
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

    /*public void add(Intersectable... geometries) {
         for (int i = 1; i < geometries.length; ++i)
             this._geometries.add(geometries[i]);
     }*/

    /**
     *
     * @param ray
     * @return
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = null;
        for (Intersectable geo : _geometries) {
            List<Point3D> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<Point3D>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}
