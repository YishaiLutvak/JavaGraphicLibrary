package UnitTests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
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

        //T01: Ray's line don't cross the triangle(0 points)
        assertEquals("Ray's line out of plane", null,
                triangle.findIntersections(new Ray(
                        new Point3D(0, 2, 0),
                        new Vector(1, 1, 1))));
        
        //T02: Ray starts before the triangle(1 points)
        //T03: Ray starts after the triangle(0 points)

        // =============== Boundary Values Tests ==================


        //T11: Ray starts on the plane and not orthogonl(0 points)

        //ray starts on the plane of the triangle outside the triangle
        ///ray is not orthogonal to the plane and not contained in the plane

        //*******group: ray is orthgonal to the triangle********
        //ray starts before the triangle(1 points)
        //T11: Ray starts on the triangle(0 points)
        //T11: Ray starts after the triangle(0 points)

        //********group: ray is orthogonal to the plane of the triangle***********
        //ray starts before the plane(0 points)
        //T11: Ray starts on the plane(0 points)
        //T11: Ray starts after the plane(0 points)

        //******group: ray is contained in the plane*******
        //the triangle contain the ray
        //the triangle don't contain the ray
    }
}
