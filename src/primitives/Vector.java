package primitives;

/**
 * Vector class represents vector in 3D Cartesian coordinate system
 * by Point3D
 * @author Michael Bergshtein and Yishay Lutvak
 */

public class Vector {
    /**
     *
     */
    protected Point3D _head;

    /**
     *
     * @param _head
     */
    public Vector(Point3D _head) {
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException();
        this._head = _head;
    }

    /**
     *
     * @param _x
     * @param _y
     * @param _z
     */
    public Vector(Coordinate _x,Coordinate _y,Coordinate _z) {
        Point3D p =  new Point3D(_x, _y, _z);
        if (p.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException();
        }
        this._head = p;
    }

    /**
     *
     * @param _x
     * @param _y
     * @param _z
     */
    public Vector(double _x,double _y,double _z)  {
        Point3D p =  new Point3D(_x, _y, _z);
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException();
        this._head = p;
    }

    /**
     *
     * @param vector
     */
    public Vector(Vector vector) {
        this._head = vector._head;
    }

    public Point3D get_head() {
        return new Point3D(_head._x, _head._y, _head._z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    /**
     *
     * @param v
     * @return
     */
    public Vector subtract (Vector v) {
        return new Vector(_head.get_x().get() - v._head.get_x().get(),
                _head.get_y().get() - v._head.get_y().get(),
                _head.get_z().get() - v._head.get_z().get());
    }

    /**
     *
     * @param v
     * @return
     */
    public Vector add (Vector v) {
        return new Vector(_head.get_x().get() + v._head.get_x().get(),
                _head.get_y().get() + v._head.get_y().get(),
                _head.get_z().get() + v._head.get_z().get());
    }

    /**
     *
     * @param d
     * @return
     */
    public Vector scale (double d) {
        return new Vector(_head.get_x().get()*d,
                _head.get_y().get()*d,
                _head.get_z().get()*d);
    }

    /**
     *
     * @param v
     * @return
     */
    public double dotProduct(Vector v) {
        return _head.get_x().get()*v._head.get_x().get()+
                _head.get_y().get()*v._head.get_y().get()+
                _head.get_z().get()*v._head.get_z().get();
    }

    /**
     *
     * @param edge2
     * @return
     */
    public Vector crossProduct(Vector edge2) {
        if(_head.get_x().get()/edge2._head.get_x().get() == _head.get_y().get()/edge2._head.get_y().get() &&
                        _head.get_x().get()/edge2._head.get_x().get() == _head.get_z().get()/edge2._head.get_z().get())
        {
            throw new IllegalArgumentException();
        }
        return new Vector(new Point3D(
                _head.get_y().get()*edge2._head.get_z().get() - _head.get_z().get()*edge2._head.get_y().get(),
                _head.get_z().get()*edge2._head.get_x().get() - _head.get_x().get()*edge2._head.get_z().get(),
                _head.get_x().get()*edge2._head.get_y().get() - _head.get_y().get()*edge2._head.get_x().get()
        ));
    }

    /**
     *
     * @return
     */
    public double lengthSquared() {
        return ((_head.get_x().get())*(_head.get_x().get())+
                (_head.get_y().get())*(_head.get_y().get())+
                (_head.get_z().get())*(_head.get_z().get()));
    }

    /**
     *
     * @return
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     *
     * @return
     */
    public Vector normalize() {
        _head =new Point3D(
                _head.get_x().get()/length(),
                _head.get_y().get()/length(),
                _head.get_z().get()/length());
        return this;
    }

    /**
     *
     * @return
     */
    public Vector normalized () {
        Vector v = new Vector(this);
        return v.normalize();
    }

    @Override
    public String toString() {
        return "head= " + _head.toString();
    }
}
