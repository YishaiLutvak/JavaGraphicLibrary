package primitives;

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

    /***************constractors***************/

    /**
     * constractors gets three coordinates and create the head
     * @param x for x coordinate
     * @param y for y coordinate
     * @param z for z coordinate
     */
    public Vector(Coordinate x,Coordinate y,Coordinate z) {
        Point3D p =  new Point3D(x, y, z);
        if (p.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("Point 3D (0.0, 0.0, 0.0) not valid for vector head");
        }
        this._head = p;
    }

    /**
     * constractor gets three values of coordinates of the head
     * @param x for x coordinate
     * @param y for y coordinate
     * @param z for z coordinate
     */
    public Vector(double x,double y,double z)  {
        Point3D p =  new Point3D(x, y, z);
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Point 3D (0.0, 0.0, 0.0) not valid for vector head");
        this._head = p;
    }

    /**
     * constractor with one point for the vector head
     * @param head
     */
    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Point 3D (0.0, 0.0, 0.0) not valid for vector head");
        this._head = head;
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


    /*******************methods**********************/
    /**
     *sustruction of vectors
     * @param v another vector to substract it
     * @return a new vector arter sustraction
     */
    public Vector subtract (Vector v) {
        return new Vector(
                _head.get_x().get() - v._head.get_x().get(),
                _head.get_y().get() - v._head.get_y().get(),
                _head.get_z().get() - v._head.get_z().get());
    }

    /**
     * add vector v to current vector
     * @param v
     * @return a new vector after adding
     */
    public Vector add (Vector v) {
        return new Vector(
                _head.get_x().get() + v._head.get_x().get(),
                _head.get_y().get() + v._head.get_y().get(),
                _head.get_z().get() + v._head.get_z().get());
    }

    /**
     * product the vector in scalar
     * @param d the scalar
     * @return the vector after production
     */
    public Vector scale (double d) {
        return new Vector(
                _head.get_x().get()*d,
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
        //Checks that both vectors are not on the same straight line
        if (this.normalized().equals(edge2.normalized()) || this.normalized().equals(edge2.normalized().scale(-1)))
        {
            throw new IllegalArgumentException("two vectors on the same straight line are not valid for cross product");
        }
        return new Vector(new Point3D(
                _head.get_y().get()*edge2._head.get_z().get() - _head.get_z().get()*edge2._head.get_y().get(),
                _head.get_z().get()*edge2._head.get_x().get() - _head.get_x().get()*edge2._head.get_z().get(),
                _head.get_x().get()*edge2._head.get_y().get() - _head.get_y().get()*edge2._head.get_x().get()));
    }

    /**
     *
     * @return the length of the vector squared
     */
    public double lengthSquared() {
        return _head.distanceSquared(Point3D.ZERO);
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
     * Acting a normlize of the vector
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
