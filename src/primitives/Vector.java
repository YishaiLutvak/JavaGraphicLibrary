package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

public class Vector {
    Point3D _head;

    public Vector(Point3D _head) {
        try{
            if (_head.equals(Point3D.ZERO))
                throw new IllegalArgumentException();
            this._head = _head;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Vector(Coordinate _x,Coordinate _y,Coordinate _z) {
        try{
            Point3D p =  new Point3D(_x, _y, _z);
            if (p.equals(Point3D.ZERO))
                throw new IllegalArgumentException();
            this._head = p;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Vector(double _x,double _y,double _z) {
        try{
            Point3D p =  new Point3D(_x, _y, _z);
            if (p.equals(Point3D.ZERO))
                throw new IllegalArgumentException();
            this._head = p;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Vector(Vector vector) {
        this._head = vector._head;
    }
    /*public Vector(Point3D _p1, Point3D _p2) {
        try{
            if (
                    (isZero(_p1.get_x().get()-_p2.get_x().get())) &&
                    (isZero(_p1.get_y().get()-_p2.get_y().get())) &&
                    (isZero(_p1.get_z().get()-_p2.get_z().get()))
            )
                throw new IllegalArgumentException();
        } catch (Exception ex) {
        System.out.println(ex);
        }
        this._head = (_p2.subtract(_p1))._head;
    }*/

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

    public Vector subtract (Vector v){
        return new Vector(_head.get_x().get() - v._head.get_x().get(),
                _head.get_y().get() - v._head.get_y().get(),
                _head.get_z().get() - v._head.get_z().get());
    }

    public Vector add (Vector v){
        return new Vector(_head.get_x().get() + v._head.get_x().get(),
                _head.get_y().get() + v._head.get_y().get(),
                _head.get_z().get() + v._head.get_z().get());
    }

    public Vector scale (double d){
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
