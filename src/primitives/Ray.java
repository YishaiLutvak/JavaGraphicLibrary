package primitives;

import static primitives.Util.*;

/**
 * Ray class represents a ray in 3D Cartesian coordinate system
 * by point and vector
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Ray {
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
        //Ensures that the ray's vector is normalized
        if (isZero(vector.length() - 1)) {
            this._direction = new Vector(vector);
        }
        else {
            this._direction = vector.normalized();
        }
    }

    /**
     * Copy constructor for a deep copy of an Ray object.copy constructor
     * @param ray the object that being copied
     */
    public Ray(Ray ray) {
        this._start = new Point3D(ray._start);
        this._direction = ray._direction.normalized();
    }

    /******************gettters****************/

    /**
     * @return start point of ray
     */
    public Point3D get_start() {
        return _start;
    }

    /**
     * @return direction of ray
     */
    public Vector get_direction() {
        return _direction;
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
}

//public class SphereTests {
//
//    @Test
//    public void getNormalTest() {
//        Sphere s1 = new Sphere(4, new Point3D(0,0,0));
//        Sphere s2 = new Sphere(1, new Point3D(1,1,1));
//
//
//        assertTrue(s1.getNormal(new Point3D(0,0,4)).equals(new Vector(new Point3D(0,0,1))));
//        assertTrue(s1.getNormal(new Point3D(0,0,-4)).equals(new Vector(new Point3D(0,0,-1))));
//        assertTrue(s1.getNormal(new Point3D(0,4,0)).equals(new Vector(new Point3D(0,1,0))));
//        assertTrue(s1.getNormal(new Point3D(0,-4,0)).equals(new Vector(new Point3D(0,-1,0))));
//        assertTrue(s1.getNormal(new Point3D(4,0,0)).equals(new Vector(new Point3D(1,0,0))));
//        assertTrue(s1.getNormal(new Point3D(-4,0,0)).equals(new Vector(new Point3D(-1,0,0))));
//
//        assertTrue(s2.getNormal(new Point3D(1,1,0)).equals(new Vector(new Point3D(0,0,-1))));
//        assertTrue(s2.getNormal(new Point3D(0,1,1)).equals(new Vector(new Point3D(-1,0,0))));
//        assertTrue(s2.getNormal(new Point3D(1,0,1)).equals(new Vector(new Point3D(0,-1,0))));
//
//        assertEquals(s1.getNormal(new Point3D(7,7,7)),null);
//
//    }
//}