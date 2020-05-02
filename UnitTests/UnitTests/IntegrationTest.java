package UnitTests;

import elements.Camera;
import geometries.Plane;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Testing integration of camera and intersection
 * in sphere, plane and triangle
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class IntegrationTest {
    //camera for testing
    private Camera camera1 = new Camera(
            Point3D.ZERO,
            new Vector(0, 0,1),
            new Vector(0,-1,0));

   private Camera camera2 = new Camera(
            new Point3D(0,0,-0.5),
            new Vector(0, 0,1),
            new Vector(0,-1,0));

    //list of results for camera1 and camera2
    List<Ray> rays1 = new ArrayList<Ray>();
    List<Ray> rays2 = new ArrayList<Ray>();
    int sumIntersections = 0;

    /**
     * construct the intersections for rays1 and rays2
     */
    public IntegrationTest() {
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                rays1.add(camera1.constructRayThroughPixel(
                        3,3, j, i, 1,3,3));


        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3;j++)
                rays2.add(camera2.constructRayThroughPixel(
                        3,3, j, i, 1,3,3));
    }


    @Test
    public void CameraSphereIntersections() {

        // TC01: 3X3 center: (0,0,3) radius:1
        //small sphere in front of the view plane
        //the plane outside the sphere

        Sphere sphere1 = new Sphere(
                1,
                new Point3D(0,0,3));

        for (Ray ray : rays1) {
            List<Point3D> tempIntersections = sphere1.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",2,sumIntersections);


        // TC02: 3X3 center: (0,0,2.5) radius:2.5
        // the plane inside the sphere but the camera outside the sphere

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


        // TC03: 3X3 center: (0,0,2) radius:2
        // the plane inside the sphere but the camera outside the sphere
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

        // TC04: 3X3 center: (0,0,1) radius:4
        // the plane and the camera inside the sphere
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


        // TC05: 3X3 center: (0,0,-1) radius:0/5
        // the sphere behind the camera
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
        // TC11: 3X3
        // the plane is parallel to the view plane
        Plane plane1 = new Plane(new Point3D(0,0,2),new Vector(camera1.getVto()));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<Point3D> tempIntersections = plane1.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",9,sumIntersections);

        // TC12: 3X3
        // the plane is not parallel to the view plane - three intersection points
        Plane plane2 = new Plane(new Point3D(0,0,5),new Vector(0,0.5,1));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<Point3D> tempIntersections = plane2.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",9,sumIntersections);

        // TC13: 3X3
        // the plane is not parallel to the view plane - two intersection points
        Plane plane3 = new Plane(new Point3D(0,0,5),new Vector(0,1,1));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<Point3D> tempIntersections = plane3.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",6,sumIntersections);

        // TC14: 3X3
        // the plane is orthogonal to the view plane in pCenter point
        Plane plane4 = new Plane(new Point3D(0,0,2),new Vector(camera1.getVup()));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<Point3D> tempIntersections = plane4.findIntersections(ray);
            if (tempIntersections != null)
                sumIntersections+= tempIntersections.size();
        }
        assertEquals("Wrong number of points",0,sumIntersections);
    }

    @Test
    public void CameraTriangleIntersections() {

    }

}