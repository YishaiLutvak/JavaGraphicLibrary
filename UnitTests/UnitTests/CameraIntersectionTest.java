package UnitTests;

import elements.Camera;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

class CameraIntersectionTest {
    Camera myCamera = new Camera(
            Point3D.ZERO,
            new Vector(0, -1,0),
            new Vector(0,0,1));
    @Test
    public void testIntegrationForTriangle() {

    }

    @Test
    public void testIntegrationForSphere() {
        ArrayList<Point3D> result = new ArrayList<Point3D>();
        Sphere sphere = new Sphere(1,new Point3D(0,0,3));
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                result.addAll(
                        sphere.findIntersections(
                                myCamera.constructRayThroughPixel(
                                        3,3, j,i, 1,3,3)));
        assertEquals("Wrong number of points",2,result.size());
    }

    @Test
    public void testIntegrationForPlane() {

    }

}