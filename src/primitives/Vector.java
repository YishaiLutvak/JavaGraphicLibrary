package primitives;

import java.util.Objects;

/**
 * Vector class represents vector in 3D Cartesian coordinate system
 * by Point3D
 * @author Michael Bergshtein and Yishay Lutvak
 */

public class Vector {
    /**
     * the tip of the vector starts in the origin
     */
    protected Point3D _head;

    /*****************constractors***************/
    /**
     * constractor with one point for the vector head
     * @param _head
     */
    public Vector(Point3D _head) {
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException();
        this._head = _head;
    }

    /**
     * constractors gets three coordinates and create the head
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
     * constractor gets three values of coordinates of the head
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
     * copy constractor
     * @param vector
     */
    public Vector(Vector vector) {
        this._head = vector._head;
    }

    public Point3D get_head() {
        return new Point3D(_head._x, _head._y, _head._z);
    }


    /***************methods**************************/
    /**
     *sustruction of vectors
     * @param v another vector to substract it
     * @return a new vector arter sustraction
     */
    public Vector subtract (Vector v) {
        return new Vector(_head.get_x().get() - v._head.get_x().get(),
                _head.get_y().get() - v._head.get_y().get(),
                _head.get_z().get() - v._head.get_z().get());
    }

    /**
     * add vector v to current vector
     * @param v
     * @return a new vector after adding
     */
    public Vector add (Vector v) {
        return new Vector(_head.get_x().get() + v._head.get_x().get(),
                _head.get_y().get() + v._head.get_y().get(),
                _head.get_z().get() + v._head.get_z().get());
    }

    /**
     * product the vector in scalar
     * @param d the scalar
     * @return the vector after production
     */
    public Vector scale (double d) {
        return new Vector(_head.get_x().get()*d,
                _head.get_y().get()*d,
                _head.get_z().get()*d);
    }

    /**
     *
     * Acting dot product between this vector and v
     * @param v
     * @return a double number for the scalar that it the result of production
     */
    public double dotProduct(Vector v) {
        return _head.get_x().get()*v._head.get_x().get()+
                _head.get_y().get()*v._head.get_y().get()+
                _head.get_z().get()*v._head.get_z().get();
    }

    /**
     *Acting cross product between this vector and edg2
     * @param edge2 a vector
     * @return a vector for the result of cross product
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
     * @return the length of the vector squared
     */
    public double lengthSquared() {
        return ((_head.get_x().get())*(_head.get_x().get())+
                (_head.get_y().get())*(_head.get_y().get())+
                (_head.get_z().get())*(_head.get_z().get()));
    }

    /**
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Acting a normlize of the vector
     * @return this vector after normalization
     */
    public Vector normalize() {
        _head =new Point3D(
                _head.get_x().get()/length(),
                _head.get_y().get()/length(),
                _head.get_z().get()/length());
        return this;
    }

    /**
     *   Acting a normlize of the vector
     * @return a new vector cop of this vector after normalization
     */
    public Vector normalized () {
        Vector v = new Vector(this);
        return v.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }


    @Override
    public String toString() {
        return "head= " + _head.toString();
    }


}
