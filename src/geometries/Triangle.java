package geometries;

import primitives.Point3D;
import primitives.Vector;

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


    /*public Vector getNormal(){
        return super.getNormal(super._vertices.get(0));
    }*/


    /****************admin**************/
    @Override
    public String toString() {
        return "vertex A= " + super._vertices.get(0) +
                " vertex B= " + super._vertices.get(1) +
                " vertex C= " + super._vertices.get(2) +
                ", plane= " + _plane;
    }
}
