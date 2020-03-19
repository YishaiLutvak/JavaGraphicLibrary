package primitives;

public class Point3D {
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
    }

    public Point3D(double _x, double _y, double _z) {
        this(new Coordinate( _x),new Coordinate( _y),new Coordinate( _z));
    }

    public Point3D(Point3D point) {
        this._x = point.get_x();
        this._y = point.get_y();
        this._z = point.get_z();
    }

    public final static Point3D ZERO = (new Point3D(0.0,0.0,0.0));

    /**
     *
     * @return new Coordinate based on _x
     */
    public Coordinate get_x() {
        return new Coordinate(_x);
    }

    public Coordinate get_y() {
        return new Coordinate(_y);
    }

    public Coordinate get_z() {
        return new Coordinate(_z);
    }

    /**
     * to do
     * @param o
     * @return
     */
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

    public Vector subtract(Point3D p) {
        return new Vector(new Point3D(
                p._x.get() - this._x.get(),
                p._y.get() - this._y.get(),
                p._z.get() - this._z.get()
        ));
    }

    public double distanceSquared (Point3D p){
        return (_x.get() - p.get_x().get())*(_x.get() - p.get_x().get())+
                (_y.get() - p.get_y().get())*(_y.get() - p.get_y().get())+
                (_z.get() - p.get_z().get())*(_z.get() - p.get_z().get());
    }

    public double distance (Point3D p){
        return Math.sqrt(distanceSquared(p));
    }

    public Point3D add(Vector v){
        return new Point3D(
              _x.get() +v._head.get_x().get(),
                _y.get() +v._head.get_y().get(),
                _z.get() +v._head.get_z().get()
        );
    }
}
