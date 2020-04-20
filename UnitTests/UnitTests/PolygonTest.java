package UnitTests;

import static org.junit.Assert.*;
import org.junit.Test;
import geometries.*;
import primitives.*;

import java.util.List;

/**
 * Testing Polygons
 * @author Dan
 *
 */
public class PolygonTest {

    /**
     * Test method for {@link geometries.Polygon#Polygon(Point3D... vertices)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0),
                    new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector expected = new Vector(sqrt3, sqrt3, sqrt3);
        // Each polygon has two normals in opposite directions
        assertTrue("Bad normal to triangle",expected.equals(pl.getNormal(null)) ||
                expected.equals(pl.getNormal(null).scale(-1)));
    }

    @Test
    public void testFindIntersections() {
        Polygon polygon = new Polygon(
                new Point3D(2,2,1),
                new Point3D(-2,2,1),
                new Point3D(-2,-2,1),
                new Point3D(2,-2,1));

        // ============ Equivalence Partitions Tests ==============

        //T01: Ray starts before the polygon and cross the polygon(1 points)
        Point3D crossPoint = new Point3D(0,0,1);

        List<Point3D> result = polygon.findIntersections(new Ray(
                new Point3D(1, 0, 0),
                new Vector(0, -1, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses polygon", List.of(crossPoint), result);

        //T02: Ray cross outside against vertex(0 points)
        assertEquals("Ray cross outside against vertex", null,
                polygon.findIntersections(new Ray(
                        new Point3D(2, 2, 0),
                        new Vector(1, 1, 1))));

        //T03: Ray cross outside against edge(0 points)
        assertEquals("Ray cross outside against edge", null,
                polygon.findIntersections(new Ray(
                        new Point3D(1, 2, 0),
                        new Vector(0, 1, 1))));

        //T04: Ray starts after the polygon(0 points)
        assertEquals("Ray starts after the polygon", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 1, 2),
                        new Vector(0, 1, 1))));

        // =============== Boundary Values Tests ==================

        //**********group: ray cross polygon's edge and vertex**************

        //T11: Ray cross polygons's vertex(0 points)
        assertEquals("Ray cross polygon's vertex", null,
                polygon.findIntersections(new Ray(
                        new Point3D(2, 1, 0),
                        new Vector(0, 1, 1))));

        //T12: Ray cross polygon's edge(0 points)
        assertEquals("Ray cross polygon's edge", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 1, 0),
                        new Vector(0, 1, 1))));

        //T13: Ray cross polygon's edge line(0 points)
        assertEquals("Ray starts on the polygon and not orthogonal", null,
                polygon.findIntersections(new Ray(
                        new Point3D(2, 2, 0),
                        new Vector(1, 0, 1))));

        //**********group: ray starts on the plane**************

        //T14: Ray starts on the polygon and not orthogonal(0 points)
        assertEquals("Ray starts on the polygon and not orthogonal", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 1, 1),
                        new Vector(0, 1, 1))));

        //T15:ray starts on the plane of the polygon outside the polygon
        ///ray is not orthogonal to the plane and not contained in the plane(0 points)
        assertEquals("Ray starts on the plane and not orthogonal or contained", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 4, 1),
                        new Vector(0, 1, 1))));

        //*******group: ray is orthogonal to the polygon********

        //T16:ray starts before the polygon(1 points)
        crossPoint = new Point3D(0,1,1);

        result = polygon.findIntersections(new Ray(
                new Point3D(0, 1, 0),
                new Vector(0, 0, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses polygon", List.of(crossPoint), result);

        //T17: Ray starts on the polygon(0 points)
        assertEquals("Ray starts on the polygon and orthogonal", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 1, 1),
                        new Vector(0, 0, 1))));

        //T18: Ray starts after the polygon(0 points)
        assertEquals("Ray starts after the polygon and orthogonal", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 1, 2),
                        new Vector(0, 0, 1))));


        //********group: ray is orthogonal to the plane of the polygon***********

        //T19:ray starts before the plane(0 points)
        assertEquals("Ray starts before the plane and orthogonal", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 3, 0),
                        new Vector(0, 0, 1))));

        //T20: Ray starts on the plane(0 points)
        assertEquals("Ray starts on the plane and orthogonal", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 3, 1),
                        new Vector(0, 0, 1))));

        //T21: Ray starts after the plane(0 points)
        assertEquals("Ray starts after the plane and orthogonal", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 3, 2),
                        new Vector(0, 0, 1))));


        //******group: ray is contained in the plane*******

        //T22:The ray starts before the polygon(0 points)
        assertEquals("Ray contained in the plane and starts before the polygon", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, -3, 1),
                        new Vector(0, 1, 0))));

        //T23:The ray starts in the polygon(0 points)
        assertEquals("Ray contained in the plane and starts in the polygon", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 1, 1),
                        new Vector(0, 1, 0))));

        //T24:The ray starts after the polygon(0 points)
        assertEquals("Ray contained in the plane and starts after the polygon", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, 3, 1),
                        new Vector(0, 1, 0))));

        //T25:the ray line is out of the polygon(0 points)
        assertEquals("Ray contained in the plane and not cross the polygon", null,
                polygon.findIntersections(new Ray(
                        new Point3D(0, -3, 1),
                        new Vector(1, 0, 0))));
    }
}
