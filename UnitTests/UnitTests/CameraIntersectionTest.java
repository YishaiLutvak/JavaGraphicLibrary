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

    @Test
    public void testIntegrationForTriangle() {

    }

    @Test
    public void testIntegrationForSphere() {
        // TC01: 3X3 Corner (0,0)
        Camera camera1 = new Camera(
                Point3D.ZERO,
                new Vector(0, 0,1),
                new Vector(0,-1,0));

        Sphere sphere1 = new Sphere(
                1,
                new Point3D(0,0,3));

        ArrayList<Point3D> result = new ArrayList<Point3D>();
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                result.addAll(
                        sphere1.findIntersections(
                                camera1.constructRayThroughPixel(
                                        3,3, j, i, 1,3,3)));
        assertEquals("Wrong number of points",2,result.size());

        // TC02: 3X3 Corner (0,0)
        Camera camera2 = new Camera(
                new Point3D(0,0,-0.5),
                new Vector(0, 0,1),
                new Vector(0,-1,0));

        Sphere sphere2 = new Sphere(
                2.5,
                new Point3D(0,0,2.5));

        result.clear();
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                result.addAll(
                        sphere2.findIntersections(
                                camera2.constructRayThroughPixel(
                                        3,3, j, i, 1,3,3)));
        assertEquals("Wrong number of points",18,result.size());

        // TC03: 3X3 Corner (0,0)
        Sphere sphere3 = new Sphere(
                2,
                new Point3D(0,0,2));

        result.clear();
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                result.addAll(
                        sphere3.findIntersections(
                                camera2.constructRayThroughPixel(
                                        3,3, j, i, 1,3,3)));
        assertEquals("Wrong number of points",10,result.size());

        // TC04: 3X3 Corner (0,0)
        Sphere sphere4 = new Sphere(
                4,
                new Point3D(0,0,1));

        result.clear();
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                result.addAll(
                        sphere4.findIntersections(
                                camera1.constructRayThroughPixel(
                                        3,3, j, i, 1,3,3)));
        assertEquals("Wrong number of points",9,result.size());

        // TC05: 3X3 Corner (0,0)
        Sphere sphere5 = new Sphere(
                0.5,
                new Point3D(0,0,-1));

        result.clear();
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                result.addAll(
                        sphere5.findIntersections(
                                camera1.constructRayThroughPixel(
                                        3,3, j, i, 1,3,3)));
        assertEquals("Wrong number of points",0,result.size());
    }

    @Test
    public void testIntegrationForPlane() {

    }

}