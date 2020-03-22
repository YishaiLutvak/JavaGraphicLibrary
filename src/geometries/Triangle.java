package geometries;

import primitives.Point3D;

import java.util.Objects;

/**
 * Triangle class represents a triangle in 3D Cartesian coordinate system
 * by three vertexes
 * @author Michael Bergshtein and Yishay Lutvak
 */
public class Triangle extends Polygon {
    /************constractor**************/
    /**
     * constractor by 3 Point3D
     * @param vertexA for A vertex
     * @param vertexB for B vertex
     * @param vertexC for C vertex
     */
    public Triangle(Point3D vertexA ,Point3D vertexB,Point3D vertexC) {
        super(vertexA,vertexB,vertexC);
    }

    /**************getters*****************/
    public Point3D getVertexA() {
        return super._vertices.get(0);
    }

    public Point3D getVertexB() {
        return super._vertices.get(1);
    }

    public Point3D getVertexC() {
        return super._vertices.get(2);
    }

    /****************admin**************/
    @Override
    public String toString() {
        return
                "_vertices=" + _vertices +
                ", _plane=" + _plane
                ;
    }



}
