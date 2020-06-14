package UnitTests;

import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static geometries.Intersectable.*;
import static org.junit.Assert.*;

/**
 * Testing Tubes
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class TubeTest {
    Tube tube = new Tube(5, new Ray(new Point3D(0,0,0),new Vector(1,0,0)));

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // Test that the getNormal function of tube is proper
        assertTrue("getNormal function is not proper",
                tube.getNormal(new Point3D(1,5,0)).equals(new Vector(new Point3D(0,1,0))));
        assertTrue("getNormal function is not proper",
                tube.getNormal(new Point3D(0,-5,0)).equals(new Vector(new Point3D(0,-1,0))));
        assertTrue("getNormal function is not proper",
                tube.getNormal(new Point3D(-1,0,5)).equals(new Vector(new Point3D(0,0,1))));
        assertTrue("getNormal function is not proper",
                tube.getNormal(new Point3D(0,0,-5)).equals(new Vector(new Point3D(0,0,-1))));
    }

    /**
     * Test method for {@link geometries.Tube#getFindIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the tube (0 points)
        assertEquals("Ray's line out of tube", null,
                tube.getFindIntersections(new Ray(
                        new Point3D(-1, 0, 6),
                        new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the tube (2 points)
        Point3D p1 = new Point3D(10, -5, 0);
        Point3D p2 = new Point3D(15, 0, -5);
        List<GeoPoint> result = tube.getFindIntersections(new Ray(
                new Point3D(5, -10, 5),
                new Vector(1, 1, -1)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0)._point.get_x().get() > result.get(1)._point.get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses tube", List.of(new GeoPoint(tube,p1), new GeoPoint(tube,p2)), result);

        // TC03: Ray starts inside the tube (1 point)
        p1 = new Point3D(5, -5, 0);
        result = tube.getFindIntersections(new Ray(
                new Point3D(10, 0, -5),
                new Vector(-1, -1, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray inside tube", List.of(new GeoPoint(tube,p1)), result);

        // TC04: Ray starts after the tube (0 points)
        assertEquals("Ray starts after the tube", null,
                tube.getFindIntersections(new Ray(
                        new Point3D(11, 1, -6),
                        new Vector(1, 1, -1))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the tube (but not the axisRay)

        // TC11: Ray starts at tube and goes inside (1 points)
        p1 = new Point3D(10, 0, -5);
        result = tube.getFindIntersections(new Ray(
                new Point3D(5, -5, 0),
                new Vector(1, 1, -1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts on tube and cross it in one point", List.of(new GeoPoint(tube,p1)), result);

        // TC12: Ray starts at tube and goes outside (0 points)
        assertEquals("Ray starts at tube and goes outside", null,
                tube.getFindIntersections(new Ray(
                        new Point3D(10, 0, -5),
                        new Vector(1, 1, -1))));

        // **** Group: Ray's line goes through the axisRay

        // TC13: Ray starts before the tube (2 points)
        p1 = new Point3D(5, -5, 0);
        p2 = new Point3D(15, 5, 0);
        result = tube.getFindIntersections(new Ray(
                new Point3D(4, -6, 0),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0)._point.get_x().get() > result.get(1)._point.get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses tube and the axisRay", List.of(new GeoPoint(tube,p1), new GeoPoint(tube,p2)), result);

        // TC14: Ray starts at tube and goes inside (1 points)
        p1 = new Point3D(15, 5, 0);
        result = tube.getFindIntersections(new Ray(
                new Point3D(5, -5, 0),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts on the tube and cross the axisRay", List.of(new GeoPoint(tube,p1)), result);

        // TC15: Ray starts inside (1 points)
        p1 = new Point3D(5, 5, 0);
        result = tube.getFindIntersections(new Ray(
                new Point3D(4, 4, 0),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts in the tube and cross the axisRay", List.of(new GeoPoint(tube,p1)), result);

        // TC16: Ray starts at the axisRay (1 points)
        p1 = new Point3D(10, 5, 0);
        result = tube.getFindIntersections(new Ray(
                new Point3D(5, 0, 0),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts in the axisRay and cross the tube", List.of(new GeoPoint(tube,p1)), result);

        // TC17: Ray starts at tube and goes outside (0 points)
        assertEquals("Ray starts on the tube", null,
                tube.getFindIntersections(new Ray(
                        new Point3D(5, 5, 0),
                        new Vector(1, 1, 0))));

        // TC18: Ray starts after tube (0 points)
        assertEquals("Ray starts after the tube", null,
                tube.getFindIntersections(new Ray(
                        new Point3D(5, 6, 0),
                        new Vector(1, 1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        assertEquals("Ray starts before the tangent point to the tube", null,
                tube.getFindIntersections(new Ray(
                        new Point3D(-1, 5, 1),
                        new Vector(1, 0, -1))));

        // TC20: Ray starts at the tangent point
        assertEquals("Ray starts at the tangent point", null,
                tube.getFindIntersections(new Ray(
                        new Point3D(5, 5, 0),
                        new Vector(1, 0, -1))));

        // TC21: Ray starts after the tangent point
        assertEquals("Ray starts after the tangent point", null,
                tube.getFindIntersections(new Ray(
                        new Point3D(1, 5, -1),
                        new Vector(1, 0, -1))));

        // **** Group: Special cases

        // TC22: Ray is contained at tube
        assertEquals("Ray is contained at tube", null,
                tube.getFindIntersections(new Ray(
                        new Point3D(1, 5, 0),
                        new Vector(1, 0, 0))));

        // TC23: p0TubeP0 orthogonal to vTube
        p1 = new Point3D(1, -5, 0);
        p2 = new Point3D(11, 5, 0);
        result = tube.getFindIntersections(new Ray(
                new Point3D(0, -6, 0),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0)._point.get_y().get() > result.get(1)._point.get_y().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses tube", List.of(new GeoPoint(tube,p1), new GeoPoint(tube,p2)), result);

        // TC24: v orthogonal to vTube
        p1 = new Point3D(5, -5, 0);
        p2 = new Point3D(5, 5, 0);
        result = tube.getFindIntersections(new Ray(
                new Point3D(5, -10, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0)._point.get_y().get() > result.get(1)._point.get_y().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses tube", List.of(new GeoPoint(tube,p1), new GeoPoint(tube,p2)), result);

        // TC25: p0 equal to p0tube
        p1 = new Point3D(5, 5, 0);
        result = tube.getFindIntersections(new Ray(
                new Point3D(0, 0, 0),
                new Vector(1, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses tube", List.of(new GeoPoint(tube,p1)), result);
    }
}