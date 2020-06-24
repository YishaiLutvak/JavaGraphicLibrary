package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Interface for Composite Design Pattern the Composite Class - Geometries the
 * basic Classes - all the specific geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public abstract class Intersectable {
    private static boolean _actBoundingBox = false;

    public static void set_actBoundingBox(boolean _actBoundingBox) {
        Intersectable._actBoundingBox = _actBoundingBox;
    }

    protected class Box{
        protected double _max_X = Double.POSITIVE_INFINITY;
        protected double _min_X = Double.NEGATIVE_INFINITY;
        protected double _max_Y = Double.POSITIVE_INFINITY;
        protected double _min_Y = Double.NEGATIVE_INFINITY;
        protected double _max_Z = Double.POSITIVE_INFINITY;
        protected double _min_Z = Double.NEGATIVE_INFINITY;

    }

    protected Box box = new Box();

    /**
     *
     * @return
     */
    public Box getBox() {
        return box;
    }

    /**
     *
     * @param _max_X
     */
    public void set_max_X(double _max_X) {
        this.box._max_X = _max_X;
    }

    /**
     *
     * @param _min_X
     */
    public void set_min_X(double _min_X) {
        this.box._min_X = _min_X;
    }

    /**
     *
     * @param _max_Y
     */
    public void set_max_Y(double _max_Y) {
        this.box._max_Y = _max_Y;
    }

    /**
     *
     * @param _min_Y
     */
    public void set_min_Y(double _min_Y) {
        this.box._min_Y = _min_Y;
    }

    public void set_max_Z(double _max_Z) {
        this.box._max_Z = _max_Z;
    }

    public void set_min_Z(double _min_Z) {
        this.box._min_Z = _min_Z;
    }

    /**
     *
      * @return
     */
    public double getMiddleX(){
       return box._min_X + (box._max_X - box._min_X)/2;
    }

    /**
     *
     * @return
     */
    public double getMiddleY(){
        return box._min_Y + (box._max_Y - box._min_Y)/2;
    }

    /**
     *
     * @return
     */
    public double getMiddleZ(){
        return box._min_Z + (box._max_Z - box._min_Z)/2;
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

    /**
     * Calculate if the ray intersect the box. Using parametric
     * presentation of line.
     * @param ray a ray in the scene that intersect the Intersectable
     * @return boolean value if their is intersection with the box.
     */
    public boolean isIntersectionWithBox(Ray ray){

        Point3D start = ray.get_start();

        double start_X = start.get_x().get();
        double start_Y = start.get_y().get();
        double start_Z = start.get_z().get();

        Point3D direction = ray.get_direction().get_head();

        double direction_X = direction.get_x().get();
        double direction_Y = direction.get_y().get();
        double direction_Z = direction.get_z().get();

        double max_t_for_X;
        double min_t_for_X;

        //If the direction_X is negative then the _min_X give the maximal value
        if (direction_X < 0) {
            max_t_for_X = (box._min_X - start_X) / direction_X;
            // Check if the Intersectble is behind the camera
            if (max_t_for_X <= 0) return false;
            min_t_for_X = (box._max_X - start_X) / direction_X;
        }
        else if (direction_X > 0) {
            max_t_for_X = (box._max_X - start_X) / direction_X;
            if (max_t_for_X <= 0) return false;
            min_t_for_X = (box._min_X - start_X) / direction_X;
        }
        else {
            if (start_X >= box._max_X || start_X <= box._min_X)
                return false;
            else{
                max_t_for_X = Double.POSITIVE_INFINITY;
                min_t_for_X = Double.NEGATIVE_INFINITY;
            }
        }

        double max_t_for_Y;
        double min_t_for_Y;

        if (direction_Y < 0) {
            max_t_for_Y = (box._min_Y - start_Y) / direction_Y;
            if (max_t_for_Y <= 0) return false;
            min_t_for_Y = (box._max_Y - start_Y) / direction_Y;
        }
        else if (direction_Y > 0) {
            max_t_for_Y = (box._max_Y - start_Y) / direction_Y;
            if (max_t_for_Y <= 0) return false;
            min_t_for_Y = (box._min_Y - start_Y) / direction_Y;
        }
        else {
            if (start_Y >= box._max_Y || start_Y <= box._min_Y)
                return false;
            else{
                max_t_for_Y = Double.POSITIVE_INFINITY;
                min_t_for_Y = Double.NEGATIVE_INFINITY;
            }
        }

        //Check the maximal and the minimal value for t
        double temp_max = Math.min(max_t_for_Y,max_t_for_X);
        double temp_min = Math.max(min_t_for_Y,min_t_for_X);
        temp_min = Math.max(temp_min,0);

        if (temp_max < temp_min) return false;

        double max_t_for_Z;
        double min_t_for_Z;

        if (direction_Z < 0) {
            max_t_for_Z = (box._min_Z - start_Z) / direction_Z;
            if (max_t_for_Z <= 0) return false;
            min_t_for_Z = (box._max_Z - start_Z) / direction_Z;
        }
        else if (direction_Z > 0) {
            max_t_for_Z = (box._max_Z - start_Z) / direction_Z;
            if (max_t_for_Z <= 0) return false;
            min_t_for_Z = (box._min_Z - start_Z) / direction_Z;
        }
        else {
            if (start_Z >= box._max_Z || start_Z <= box._min_Z)
                return false;
            else{
                max_t_for_Z = Double.POSITIVE_INFINITY;
                min_t_for_Z = Double.NEGATIVE_INFINITY;
            }
        }

        temp_max = Math.min(max_t_for_Z,temp_max);
        temp_min = Math.max(min_t_for_Z,temp_min);

        if (temp_max < temp_min) return false;

        return true;
    }
}
