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
        this(new Point3D(x,y,z));
    }

    /**
     * constractor gets three values of coordinates of the head
     * @param x for x coordinate
     * @param y for y coordinate
     * @param z for z coordinate
     */
    public Vector(double x,double y,double z)  {
        this(new Point3D(x,y,z));
    }

    /**
     * constractor with one point for the vector head
     * @param head of the vector
     */
    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Point 3D (0.0, 0.0, 0.0) not valid for vector head");
        this._head = new Point3D(head);
    }

    /**
     * copy constractor
     * @param vector to copy
     */
    public Vector(Vector vector) {
        this._head = new Point3D(vector._head);
    }

    public Point3D get_head() {
        return new Point3D(_head.get_x(), _head.get_y(), _head.get_z());
    }


    /*******************methods**********************/
    /**
     * subtraction of vectors
     * @param v another vector to subtract it
     * @return a new vector after subtraction
     */
    public Vector subtract (Vector v) {
        return new Vector(_head.subtract(v._head));
    }

    /**
     * add vector v to current vector
     * @param v a vector
     * @return a new vector after adding
     */
    public Vector add (Vector v) {
        return new Vector(_head.add(v));
    }

    /**
     * product the vector in scalar
     * @param d a scalar
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
     * @param v a vector
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
            throw new IllegalArgumentException("two parallel vectors are not valid for cross product");
        }
        return new Vector(new Point3D(
                _head.get_y().get()*edge2._head.get_z().get() - _head.get_z().get()*edge2._head.get_y().get(),
                _head.get_z().get()*edge2._head.get_x().get() - _head.get_x().get()*edge2._head.get_z().get(),
                _head.get_x().get()*edge2._head.get_y().get() - _head.get_y().get()*edge2._head.get_x().get()));
    }

    /**
     * @return the length of the vector squared
     */
    public double lengthSquared() {
        return _head.distanceSquared(Point3D.ZERO);
    }

    /**
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Acting a normalize of the vector
     * @return this vector after normalization
     */
    public Vector normalize() {
        double length =length();
        _head =new Point3D(
                _head.get_x().get()/length,
                _head.get_y().get()/length,
                _head.get_z().get()/length);
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
