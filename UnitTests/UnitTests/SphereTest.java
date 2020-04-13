package UnitTests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing Spheres
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class SphereTest {
    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        Sphere s1 = new Sphere(5, new Point3D(0,0,0));

        // Test that the getNormal function of sphere is proper
        assertTrue("getNormal function is not proper",
                s1.getNormal(new Point3D(0,0,5)).equals(new Vector(new Point3D(0,0,1))));
        assertTrue("getNormal function is not proper",
                s1.getNormal(new Point3D(0,0,-5)).equals(new Vector(new Point3D(0,0,-1))));
        assertTrue("getNormal function is not proper",
                s1.getNormal(new Point3D(0,5,0)).equals(new Vector(new Point3D(0,1,0))));
        assertTrue("getNormal function is not proper",
                s1.getNormal(new Point3D(0,-5,0)).equals(new Vector(new Point3D(0,-1,0))));
        assertTrue("getNormal function is not proper",
                s1.getNormal(new Point3D(5,0,0)).equals(new Vector(new Point3D(1,0,0))));
        assertTrue("getNormal function is not proper",
                s1.getNormal(new Point3D(-5,0,0)).equals(new Vector(new Point3D(-1,0,0))));
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).get_x().get() > result.get(1).get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        //...
        // TC04: Ray starts after the sphere (0 points)
        //...

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        // TC12: Ray starts at sphere and goes outside (0 points)

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        // TC14: Ray starts at sphere and goes inside (1 points)
        // TC15: Ray starts inside (1 points)
        // TC16: Ray starts at the center (1 points)
        // TC17: Ray starts at sphere and goes outside (0 points)
        // TC18: Ray starts after sphere (0 points)

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        // TC20: Ray starts at the tangent point
        // TC21: Ray starts after the tangent point

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line

    }


}