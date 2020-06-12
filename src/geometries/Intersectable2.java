package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

/**
 * Interface for Composite Design Pattern the Composite Class - Geometries the
 * basic Classes - all the specific geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public abstract class Intersectable2 {
    protected double _max_X;
    protected double _min_X;
    protected double _max_Y;
    protected double _min_Y;
    protected double _max_Z;
    protected double _min_Z;

    private static boolean _actBoundingBox = false;

    public void set_max_X(double _max_X) {
        this._max_X = _max_X;
    }

    public void set_min_X(double _min_X) {
        this._min_X = _min_X;
    }

    public void set_max_Y(double _max_Y) {
        this._max_Y = _max_Y;
    }

    public void set_min_Y(double _min_Y) {
        this._min_Y = _min_Y;
    }

    public void set_max_Z(double _max_Z) {
        this._max_Z = _max_Z;
    }

    public void set_min_Z(double _min_Z) {
        this._min_Z = _min_Z;
    }

    public static void set_actBoundingBox(boolean _actBoundingBox) {
        Intersectable2._actBoundingBox = _actBoundingBox;
    }

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
     * The default value of max is Double.POSITIVE_INFINITY
     * @param ray the ray to intersect a geometries
     * @return list of intersection points
     */
    public List<GeoPoint> getFindIntersections(Ray ray) {
        return getFindIntersections(ray, Double.POSITIVE_INFINITY);
    }

    public List<GeoPoint> getFindIntersections(Ray ray,double max) {
        if(!_actBoundingBox || isIntersectionWithBox(ray))
            return findIntersections(ray, max);
        return null;
    }
    /**
     *  The functions looks for intersection points between a basic or a composite
     *  geometry and a given ray. the function returns null if there are no intersections
     * @param ray the ray to intersect a geometries
     * @param max the maximum range from the source of the ray to the point
     * @return list of intersection points
     */
    abstract List<GeoPoint> findIntersections(Ray ray, double max);

    public boolean isIntersectionWithBox(Ray ray){

        Point3D start = ray.get_start();

        double start_X = start.get_x().get();
        double start_Y = start.get_y().get();
        double start_Z = start.get_z().get();

        Vector direction = ray.get_direction();

        double direction_X = direction.get_head().get_x().get();
        double direction_Y = direction.get_head().get_y().get();
        double direction_Z = direction.get_head().get_z().get();

        if ((direction_X < 0 ? (_min_X - start_X):(_max_X- start_X)) < 0  ||
                (direction_Y < 0 ? (_min_Y - start_Y):(_max_Y- start_Y)) <0 ||
                (direction_Z < 0 ? (_min_Z - start_Z):(_max_Z- start_Z)) <0)
            return false;

        double t = 0;
        double coordinate_X;
        double coordinate_Y;
        double coordinate_Z;

        if (direction_X < 0)
            t = (_max_X-start_X)/direction_X;
        else if(direction_X > 0)
            t = (_min_X-start_X)/direction_X;
        if (t > 0){
            coordinate_Y = start_Y+ direction_Y*t;
            if ( _min_Y < coordinate_Y && coordinate_Y < _max_Y) {
                coordinate_Z = start_Z+ direction_Z*t;
                if ( _min_Z < coordinate_Z && coordinate_Z < _max_Z)
                    return true;
            }
        }

        if (direction_Y < 0)
            t = (_max_Y-start_Y)/direction_Y;
        else if(direction_Y > 0)
            t = (_min_Y-start_Y)/direction_Y;
        if (t > 0){
            coordinate_X = start_X+ direction_X*t;
            if ( _min_X < coordinate_X && coordinate_X < _max_X) {
                coordinate_Z = start_Z+ direction_Z*t;
                if ( _min_Z < coordinate_Z && coordinate_Z < _max_Z)
                    return true;
            }
        }

        if (direction_Z < 0)
            t = (_max_Z-start_Z)/direction_Z;
        else if(direction_Z > 0)
            t = (_min_Z-start_Z)/direction_Z;
        if (t > 0){
            coordinate_X = start_X+ direction_X*t;
            if ( _min_X < coordinate_X && coordinate_X < _max_X) {
                coordinate_Y = start_Y+ direction_Y*t;
                if ( _min_Y < coordinate_Y && coordinate_Y < _max_Y)
                    return true;
            }
        }

        return false;
    }
}
