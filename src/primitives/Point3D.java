package primitives;

/**
 * class that represent point in 3D by cartesian representive
 * @author Michael Bergshtein and Ishay Lutvak
 */
public class Point3D {
    /**
     * Three coordinates of x,y,z
     */
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;
/***************constractors***********************/
    /**
     * constractor by 3 coordinates
     * @param _x for x coordinate
     * @param _y for y coordinate
     * @param _z for z coordinate
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
    }

    /**
     * constactor by coordinates values
     * @param _x for value of coordinate x
     * @param _y for value of coordinate y
     * @param _z for value of coordinate z
     */
    public Point3D(double _x, double _y, double _z) {
        this(new Coordinate( _x),new Coordinate( _y),new Coordinate( _z));
    }

    /**
     * copy constractor
     * @param point
     */
    public Point3D(Point3D point) {
        this._x = point.get_x();
        this._y = point.get_y();
        this._z = point.get_z();
    }

    /**
     * For the zero point (the origin)
     */
    public final static Point3D ZERO = (new Point3D(0.0,0.0,0.0));

    /***************getters***************/

    public Coordinate get_x() {
        return new Coordinate(_x);
    }

    public Coordinate get_y() {
        return new Coordinate(_y);
    }

    public Coordinate get_z() {
        return new Coordinate(_z);
    }

    /*****************methods******************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "(" +
                _x +
                ", " + _y +
                ", " + _z +
                ')';
    }

    /**
     *
     * @param p another point
     * @return a vector from p to this point
     */
    public Vector subtract(Point3D p) {
        return new Vector(new Point3D(
                this._x.get() - p._x.get(),
                this._y.get() - p._y.get(),
                this._z.get() - p._z.get()
        ));
    }

    /**
     *
     * @param p another point
     * @return the square of the distansd between the points
     */

    public double distanceSquared (Point3D p){
        return (_x.get() - p.get_x().get())*(_x.get() - p.get_x().get())+
                (_y.get() - p.get_y().get())*(_y.get() - p.get_y().get())+
                (_z.get() - p.get_z().get())*(_z.get() - p.get_z().get());
    }

    /**
     *
     * @param p another point
     * @return the distance between the points
     */
    public double distance (Point3D p){
        return Math.sqrt(distanceSquared(p));
    }

    /**
     *
     * @param v a vector to add
     * @return a point we get if we add the vector to the point
     */
    public Point3D add(Vector v){
        return new Point3D(
              _x.get() +v._head.get_x().get(),
                _y.get() +v._head.get_y().get(),
                _z.get() +v._head.get_z().get()
        );
    }
}
