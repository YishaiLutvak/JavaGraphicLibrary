package UnitTests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing Triangles
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class TriangleTest {
    /**
     * Test method for {@link geometries.Triangle#getNormal(Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // Test that the getNormal function of triangle is proper
        Triangle tri = new Triangle(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0));
        Vector expected = new Vector(1, 1, 1).normalize();
        // Each triangle has two normals in opposite directions
        assertTrue("getNormal function is not proper",
                expected.equals(tri.getNormal(null)) ||
                        expected.equals(tri.getNormal(null).scale(-1)));
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(
                new Point3D(2,0,1),
                new Point3D(-2,0,1),
                new Point3D(0,2,1));

        // ============ Equivalence Partitions Tests ==============

        //T01: Ray starts before the triangle and cross the triangle(1 points)
        Point3D crossPoint = new Point3D(0,0.5,1);

        List<Point3D> result = triangle.findIntersections(new Ray(
                new Point3D(0, 1, 0),
                new Vector(0, -0.5, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses triangle", List.of(crossPoint), result);

        //T02: Ray cross outside against vertex(0 points)
        assertEquals("Ray cross outside against vertex", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 2, 0),
                        new Vector(0, 1, 1))));

        //T03: Ray cross outside against edge(0 points)
        assertEquals("Ray cross outside against edge", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, -1, 0),
                        new Vector(0, -1, 1))));

        //T04: Ray starts after the triangle(0 points)
        assertEquals("Ray starts after the triangle", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 1, 2),
                        new Vector(0, 1, 1))));

        // =============== Boundary Values Tests ==================

        //**********group: ray cross triangle's edge and vertex**************

        //T11: Ray cross triangle's vertex(0 points)
        assertEquals("Ray cross triangle's vertex", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 1, 0),
                        new Vector(0, 1, 1))));

        //T12: Ray cross triangle's edge(0 points)
        assertEquals("Ray cross triangle's edge", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 1, 0),
                        new Vector(0, -1, 1))));

        //T13: Ray cross triangle's edge line(0 points)
        assertEquals("Ray starts on the triangle and not orthogonal", null,
                triangle.findIntersections(new Ray(
                        new Point3D(2, 0, 0),
                        new Vector(1, 0, 1))));

        //**********group: ray starts on the plane**************

        //T14: Ray starts on the triangle and not orthogonal(0 points)
        assertEquals("Ray starts on the triangle and not orthogonal", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 1, 1),
                        new Vector(0, 1, 1))));

        //T15:ray starts on the plane of the triangle outside the triangle
        ///ray is not orthogonal to the plane and not contained in the plane(0 points)
        assertEquals("Ray starts on the plane and not orthogonal or contained", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 4, 1),
                        new Vector(0, 1, 1))));

        //*******group: ray is orthogonal to the triangle********

        //T16:ray starts before the triangle(1 points)
        crossPoint = new Point3D(0,1,1);

        result = triangle.findIntersections(new Ray(
                new Point3D(0, 1, 0),
                new Vector(0, 0, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses triangle", List.of(crossPoint), result);

        //T17: Ray starts on the triangle(0 points)
        assertEquals("Ray starts on the triangle and orthogonal", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 1, 1),
                        new Vector(0, 0, 1))));

        //T18: Ray starts after the triangle(0 points)
        assertEquals("Ray starts after the triangle and orthogonal", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 1, 2),
                        new Vector(0, 0, 1))));


        //********group: ray is orthogonal to the plane of the triangle***********

        //T19:ray starts before the plane(0 points)
        assertEquals("Ray starts before the plane and orthogonal", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 3, 0),
                        new Vector(0, 0, 1))));

        //T20: Ray starts on the plane(0 points)
        assertEquals("Ray starts on the plane and orthogonal", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 3, 1),
                        new Vector(0, 0, 1))));

        //T21: Ray starts after the plane(0 points)
        assertEquals("Ray starts after the plane and orthogonal", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 3, 2),
                        new Vector(0, 0, 1))));


        //******group: ray is contained in the plane*******

        //T22:The ray starts before the triangle(0 points)
        assertEquals("Ray contained in the plane and starts before the triangle", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, -1, 1),
                        new Vector(0, 1, 0))));

        //T23:The ray starts in the triangle(0 points)
        assertEquals("Ray contained in the plane and starts in the triangle", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 1, 1),
                        new Vector(0, 1, 0))));

        //T24:The ray starts after the triangle(0 points)
        assertEquals("Ray contained in the plane and starts after the triangle", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 3, 1),
                        new Vector(0, 1, 0))));

        //T25:the ray line is out of the triangle(0 points)
        assertEquals("Ray contained in the plane and not cross the triangle", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, -1, 1),
                        new Vector(1, 0, 0))));
    }
}
