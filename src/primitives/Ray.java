package primitives;

import static primitives.Util.*;


/**
 * Ray class represents a ray in 3D Cartesian coordinate system
 * by point and vector
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Ray {
    /**
     * Constant for size of rays origin for shading, transparency and reflection
     * in order to avoid the unwanted case of self-shadow
     */
    private static final double DELTA = 0.1;

    /**
     * The point from which the ray starts.
     */
    protected final Point3D _start;
    /**
     * The direction of the ray.
     */
    protected final Vector _direction;

    /**
     * Constructor for creating a new instance of this class
     * @param point the start of the ray.
     * @param vector the direction of the ray.
     */
    public Ray(Point3D point, Vector vector) {
        this._start = new Point3D(point);
        this._direction = vector.normalized();
    }

    /**
     * Copy constructor for a deep copy of an Ray object.copy constructor
     * @param ray the object that being copied
     */
    public Ray(Ray ray) {
        this._start = new Point3D(ray._start);
        this._direction = ray._direction.normalized();
    }

    /**
     * constructor
     * Add a delta to points on geometry in order to avoid from
     * calculation mistakes
     * @param p the point on the geometry
     * @param n normal from the geometry
     * @param v the vector that hit the geometry
     */
    public Ray (Point3D p ,Vector v, Vector n){
        Vector delta = n.scale(n.dotProduct(v) > 0 ? DELTA : - DELTA);
        this._start =  p.add(delta);
        this._direction = v.normalized();
    }

    /******************gettters****************/

    /**
     * @return start point of ray
     */
    public Point3D get_start() {
        return new Point3D(_start);
    }

    /**
     * @return direction of ray
     */
    public Vector get_direction() {
        return new Vector(_direction);
    }

    /******************methods*****************/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _start.equals(ray._start) &&
                _direction.equals(ray._direction);
    }

    @Override
    public String toString() {
        return "start= " + _start +
                ", direction= " + _direction;
    }

    /**
     * Get point on ray at a distance from ray's head
     * @param t distance from ray head
     * @return the point
     */
    public Point3D getPoint(double t){
        try {
            return isZero(t) ? get_start() : new Point3D(_start.add(_direction.scale(t)));
        }catch (IllegalArgumentException ex){
            return get_start();
        }
        /*if (isZero(t) ||
                (isZero(_direction.get_head().get_x().get()*t) &
                isZero(_direction.get_head().get_y().get()*t) &
                isZero(_direction.get_head().get_z().get()*t)))
            return get_start();
        else
            return new Point3D(_start.add(_direction.scale(t)));*/
    }
}