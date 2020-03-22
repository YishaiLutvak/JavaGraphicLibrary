package primitives;

public class Vector {
    Point3D _head;

    public Vector(Point3D _head) {
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException();
        this._head = _head;
    }

    public Vector(Coordinate _x,Coordinate _y,Coordinate _z) {
        Point3D p =  new Point3D(_x, _y, _z);
        if (p.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException();
        }
        this._head = p;
    }

    public Vector(double _x,double _y,double _z)  {
        Point3D p =  new Point3D(_x, _y, _z);
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException();
        this._head = p;
    }

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

    public Vector subtract (Vector v) {
        return new Vector(_head.get_x().get() - v._head.get_x().get(),
                _head.get_y().get() - v._head.get_y().get(),
                _head.get_z().get() - v._head.get_z().get());
    }

    public Vector add (Vector v) {
        return new Vector(_head.get_x().get() + v._head.get_x().get(),
                _head.get_y().get() + v._head.get_y().get(),
                _head.get_z().get() + v._head.get_z().get());
    }

    public Vector scale (double d) {
        return new Vector(_head.get_x().get()*d,
                _head.get_y().get()*d,
                _head.get_z().get()*d);
    }

    public double dotProduct(Vector v) {
        return _head.get_x().get()*v._head.get_x().get()+
                _head.get_y().get()*v._head.get_y().get()+
                _head.get_z().get()*v._head.get_z().get();
    }

    public Vector crossProduct(Vector edge2) {
        if(
                _head.get_x().get()/edge2._head.get_x().get() == _head.get_y().get()/edge2._head.get_y().get() &&
                        _head.get_x().get()/edge2._head.get_x().get() == _head.get_z().get()/edge2._head.get_z().get()
        )
        {
            throw new IllegalArgumentException();
        }
        return new Vector(new Point3D(
                _head.get_y().get()*edge2._head.get_z().get() - _head.get_z().get()*edge2._head.get_y().get(),
                _head.get_z().get()*edge2._head.get_x().get() - _head.get_x().get()*edge2._head.get_z().get(),
                _head.get_x().get()*edge2._head.get_y().get() - _head.get_y().get()*edge2._head.get_x().get()
        ));
    }

    public double lengthSquared() {
        return ((_head.get_x().get())*(_head.get_x().get())+
                (_head.get_y().get())*(_head.get_y().get())+
                (_head.get_z().get())*(_head.get_z().get()));
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        _head =new Point3D(
                _head.get_x().get()/length(),
                _head.get_y().get()/length(),
                _head.get_z().get()/length());
        return this;
    }

    public Vector normalized () {
        Vector v = new Vector(this);
        return v.normalize();
    }
}
