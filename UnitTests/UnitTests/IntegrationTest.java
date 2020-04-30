package UnitTests;

import elements.Camera;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IntegrationTest {
    @Test
    public void CameraSphereIntersections() {
        Camera camera1 = new Camera(
                Point3D.ZERO,
                new Vector(0, 0,1),
                new Vector(0,-1,0));

        Camera camera2 = new Camera(
                new Point3D(0,0,-0.5),
                new Vector(0, 0,1),
                new Vector(0,-1,0));

        List<Ray> rays1 = new ArrayList<Ray>();
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                rays1.add(camera1.constructRayThroughPixel(
                        3,3, j, i, 1,3,3));

        List<Ray> rays2 = new ArrayList<Ray>();
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                rays2.add(camera2.constructRayThroughPixel(
                        3,3, j, i, 1,3,3));

        int sumIntersections = 0;

        // TC01: 3X3 Corner (0,0)


        Sphere sphere1 = new Sphere(
                1,
                new Point3D(0,0,3));

        for (Ray ray : rays1) {
            List<Point3D> tempIntersections = sphere1.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",2,sumIntersections);


        // TC02: 3X3 Corner (0,0)

        Sphere sphere2 = new Sphere(
                2.5,
                new Point3D(0,0,2.5));

        sumIntersections = 0;
        for (Ray ray : rays2) {
            List<Point3D> tempIntersections = sphere2.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",18,sumIntersections);


        // TC03: 3X3 Corner (0,0)
        Sphere sphere3 = new Sphere(
                2,
                new Point3D(0,0,2));

        sumIntersections = 0;
        for (Ray ray : rays2) {
            List<Point3D> tempIntersections = sphere3.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",10,sumIntersections);

        // TC04: 3X3 Corner (0,0)
        Sphere sphere4 = new Sphere(
                4,
                new Point3D(0,0,1));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<Point3D> tempIntersections = sphere4.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",9,sumIntersections);


        // TC05: 3X3 Corner (0,0)
        Sphere sphere5 = new Sphere(
                0.5,
                new Point3D(0,0,-1));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<Point3D> tempIntersections = sphere5.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",0,sumIntersections);
    }

    @Test
    public void CameraPlaneIntersections() {

    }

    @Test
    public void CameraTriangleIntersections() {

    }

}