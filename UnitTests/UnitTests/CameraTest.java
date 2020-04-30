package UnitTests;

import static org.junit.Assert.*;

import geometries.Intersectable;
import geometries.Sphere;
import org.junit.Test;

import elements.Camera;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Testing Camera Class
 * @author Dan
 *
 */
public class CameraTest {

    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     */
    @Test
    public void testConstructRayThroughPixel() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: 3X3 Corner (0,0)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, -2, 10)),
                camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6));

        // TC02: 4X4 Corner (0,0)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-3, -3, 10)),
                camera.constructRayThroughPixel(4, 4, 0, 0, 10, 8, 8));

        // TC03: 4X4 Side (0,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -3, 10)),
                camera.constructRayThroughPixel(4, 4, 1, 0, 10, 8, 8));

        // TC04: 4X4 Inside (1,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -1, 10)),
                camera.constructRayThroughPixel(4, 4, 1, 1, 10, 8, 8));

        // =============== Boundary Values Tests ==================
        // TC11: 3X3 Center (1,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, 0, 10)),
                camera.constructRayThroughPixel(3, 3, 1, 1, 10, 6, 6));

        // TC12: 3X3 Center of Upper Side (0,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, -2, 10)),
                camera.constructRayThroughPixel(3, 3, 1, 0, 10, 6, 6));

        // TC13: 3X3 Center of Left Side (1,0)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, 0, 10)),
                camera.constructRayThroughPixel(3, 3, 0, 1, 10, 6, 6));

    }

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

}
