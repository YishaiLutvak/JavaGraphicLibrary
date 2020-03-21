package geometries;

import primitives.Point3D;

/**
 * class that represents a triangle in 3D
 */
public class Triangle extends Polygon {
    /************constractor**************/
    public Triangle(Point3D vertexA ,Point3D vertexB,Point3D vertexC) {
        super(vertexA,vertexB,vertexC);
    }
}
