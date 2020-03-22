package geometries;

import primitives.Point3D;

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
}
