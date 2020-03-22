package primitives;

import static primitives.Util.*;

/**
 * Ray class represents a ray in 3D Cartesian coordinate system
 * by point and vector
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Ray {
    /**
     * Point and vector for the ray
     */
    protected Point3D _start;
    protected Vector _direction;

    /**
     * constractor by point and vector
     * @param point for the ray's starting point
     * @param vector for the direction of the ray
     */
    public Ray(Point3D point, Vector vector) {
        this._start = point;
        //Ensures that the ray's vector is normalized
        if (isZero(vector.length() - 1)) {
            this._direction = vector;
        }
        else {
            this._direction = vector.normalized();
        }
    }

    /******************gettters****************/
    public Point3D get_start() {
        return _start;
    }

    public Vector get_direction() {
        return _direction;
    }
    /******************methods*****************/
    @Override
    public String toString() {
        return "start= " + _start +
                ", direction= " + _direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _start.equals( ray._start) &&
                _direction.equals(ray._direction);
    }
}
