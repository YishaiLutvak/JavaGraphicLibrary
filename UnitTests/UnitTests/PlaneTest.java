package UnitTests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing Planes
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class PlaneTest {
    /**
     * Test method for {@link Plane#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // Test that the getNormal function of plane is proper
        Plane pl = new Plane(
                new Point3D(1,1,0),
                new Point3D(1,-1,0),
                new Point3D(-1,-1,0));
        Vector expected = new Vector(0, 0, 1).normalize();
        // Each plane has two normals in opposite directions
        assertTrue("getNormal function is not proper",
                expected.equals(pl.getNormal(null)) ||
                        expected.equals(pl.getNormal(null).scale(-1)));
    }

    @Test
    public void findIntersections() {
        Plane plane  = new Plane(new Point3D(0,0,1),new Vector(0,0,1));
        // ============ Equivalence Partitions Tests ==============

        //T01: Ray's line don't cross the plane(0 points)
        assertEquals("Ray's line out of plane", null,
                plane.findIntersections(new Ray(new Point3D(0, 0, 2), new Vector(1, 0, 0))));
        //T02: Ray starts before the plane(1 points)
        Point3D crossPoint = new Point3D(0,1,2);
        List<Point3D> result = plane.findIntersections(new Ray(new Point3D(0, 1, 0),
                new Vector(0, 1, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses plane", List.of(crossPoint), result);
        //T03: Ray starts after the plane(0 points)
        assertEquals("Ray's starts after plane", null,
                plane.findIntersections(new Ray(new Point3D(0, 0, 2), new Vector(0, 1, 1))));
        // =============== Boundary Values Tests ==================

        //T11: Ray starts on the plane(0 points)
        assertEquals("Ray's starts on the plane", null,
                plane.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 1))));
        //T12: Ray is contained in the plane(0 points)
        assertEquals("Ray's contained in the plane", null,
                plane.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 0))));

        /****Group: Ray is orthogonal to the plane***/

        //T13: Ray starts after the plane(0 points)
        assertEquals("Ray is orthogonal to the plane, ray starts after the plane", null,
                plane.findIntersections(new Ray(new Point3D(0, 1, 2), new Vector(0, 0, 1))));
        //T14: Ray starts on the plane(0 points)
        assertEquals("Ray is orthogonal to the plane, ray starts on the plane", null,
                plane.findIntersections(new Ray(new Point3D(0, 1, 1), new Vector(0, 0, 1))));
        //T15: Ray starts before the plane(1 points)
         crossPoint = new Point3D(0,1,1);
         result = plane.findIntersections(new Ray(new Point3D(0, 1, 0),
                new Vector(0, 0, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses plane", List.of(crossPoint), result);
    }
}