package geometries;

import primitives.Point3D;
import primitives.Ray;
import java.util.List;
import java.util.Objects;

/**
 * Interface for Composite Design Pattern the Composite Class - Geometries the
 * basic Classes - all the specific geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public interface Intersectable {

    /**
     * Represent a point and the geometry it belong to
     */
    public static class GeoPoint {
        public Geometry _geometry;
        public Point3D _point;

        /**
         * constructor
         * @param geometry a geometry the point is in it
         * @param point 3D point on the geometry
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this._geometry = geometry;
            this._point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return _geometry.equals(geoPoint._geometry) &&
                    _point.equals(geoPoint._point);
        }


    }


    /**
     * The functions looks for intersection points between a basic or a composite
     * geometry and a given ray. the function returns null if there are no intersections
     * @param ray the ray to intersect a geometries
     * @return list of intersection points
     */
    default List<GeoPoint> findIntersections(Ray ray) {
        return findIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     *
     * @param ray
     * @param max
     * @return
     */
    List<GeoPoint> findIntersections(Ray ray, double max);
}
