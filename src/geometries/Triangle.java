package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Triangle class represents a triangle in 3D Cartesian coordinate system
 * by three vertexes
 * @author Michael Bergshtein and Yishay Lutvak
 */
public class Triangle extends Polygon {
    /************constractor**************/
    /**
     * constractor by 3 Point3D
     * @param vertex_A for A vertex
     * @param vertex_B for B vertex
     * @param vertex_C for C vertex
     */
    public Triangle(Point3D vertex_A ,Point3D vertex_B,Point3D vertex_C) {
        super(vertex_A,vertex_B,vertex_C);
    }

    /**************getters*****************/

    /**
     * @return vertex A
     */
    public Point3D getVertexA() {
        return super._vertices.get(0);
    }

    /**
     * @return vertex B
     */
    public Point3D getVertexB() {
        return super._vertices.get(1);
    }

    /**
     * @return vertex C
     */
    public Point3D getVertexC() {
        return super._vertices.get(2);
    }

    /****************admin**************/
    @Override
    public String toString() {
        return "vertex A= " + super._vertices.get(0) +
                " vertex B= " + super._vertices.get(1) +
                " vertex C= " + super._vertices.get(2) +
                ", plane= " + _plane;
    }

    /**
     *
     * @param ray that intersect the triangle
     * @return alist of intersect points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray)
    {
        List<Point3D> intersections = _plane.findIntersections(ray);
        if (intersections == null) return null;

        Point3D p0 = ray.get_start();
        Vector v =  ray.get_direction();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if(isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if(isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if(isZero(s3)) return null;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0) ? intersections : null);
    }
}
