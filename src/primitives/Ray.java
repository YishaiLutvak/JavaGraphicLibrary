package primitives;

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
        if (vector.length() == 1) {
            this._direction = vector;
        }
        else {
            this._direction = vector.normalized();
        }
    }

    public Point3D get_start() {
        return _start;
    }

    public Vector get_direction() {
        return _direction;
    }

    @Override
    public String toString() {
        return "start= " + _start +
                ", direction= " + _direction;
    }
}
