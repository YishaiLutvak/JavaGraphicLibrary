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
                sphere.findIntersections(new Ray(
                        new Point3D(-1, 0, 0),
                        new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(
                new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).get_x().get() > result.get(1).get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        p1 = new Point3D(1,1 , 0);
        result = sphere.findIntersections(new Ray(
                new Point3D(1, 0.5, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray inside sphere", List.of(p1), result);
        // TC04: Ray starts after the sphere (0 points)
        assertEquals("Ray starts after the sphere", null,
                sphere.findIntersections(new Ray(
                        new Point3D(1, 2, 0),
                        new Vector(0, 1, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 points)
        p1 = new Point3D(2,0 , 0);
        result = sphere.findIntersections(new Ray(
                new Point3D(1, 1, 0),
                new Vector(1, -1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts on sphere and cross it in one point", List.of(p1), result);

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray starts after the sphere", null,
                sphere.findIntersections(new Ray(
                        new Point3D(1, 1, 0),
                        new Vector(1, 1, 0))));

        // **** Group: Ray's line goes through the center

        // TC13: Ray starts before the sphere (2 points)
         p1 = new Point3D(1, -1, 0);
         p2 = new Point3D(1, 1, 0);
         result = sphere.findIntersections(new Ray(
                 new Point3D(1, -2, 0),
                 new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).get_x().get() > result.get(1).get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere and the center", List.of(p1, p2), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        p1 = new Point3D(1, 1, 0);
        result = sphere.findIntersections(new Ray(
                new Point3D(1, -1, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts on the sphere and cross the center", List.of(p1), result);

        // TC15: Ray starts inside (1 points)
        p1 = new Point3D(1, 1, 0);
        result = sphere.findIntersections(new Ray(
                new Point3D(1, -0.5, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts in the sphere and cross the center", List.of(p1), result);

        // TC16: Ray starts at the center (1 points)
        p1 = new Point3D(1, 1, 0);
        result = sphere.findIntersections(new Ray(
                new Point3D(1, 0, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray starts in the center and cross the sphere", List.of(p1), result);

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray starts on the sphere", null,
                sphere.findIntersections(new Ray(
                        new Point3D(1, 1, 0),
                        new Vector(0, 1, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertEquals("Ray starts after the sphere", null,
                sphere.findIntersections(new Ray(
                        new Point3D(2, 1, 0),
                        new Vector(0, 1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        assertEquals("Ray starts before the tangent point to the sphere", null,
                sphere.findIntersections(new Ray(
                        new Point3D(0, 1, 0),
                        new Vector(1, 0, 0))));

        // TC20: Ray starts at the tangent point
        assertEquals("Ray starts at the tangent point", null,
                sphere.findIntersections(new Ray(
                        new Point3D(1, 1, 0),
                        new Vector(1, 0, 0))));

        // TC21: Ray starts after the tangent point
        assertEquals("Ray starts after the tangent point", null,
                sphere.findIntersections(new Ray(
                        new Point3D(2, 1, 0),
                        new Vector(1, 0, 0))));

        // **** Group: Special cases

        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertEquals("Ray's line is outside, ray is orthogonal to ray start to sphere's center line", null,
                sphere.findIntersections(new Ray(
                        new Point3D(-1, 0, 0),
                        new Vector(0, 0, 1))));
    }
}
//
//package geometries;
//
//        import org.junit.jupiter.api.Test;
//        import primitives.Point3D;
//        import primitives.Ray;
//        import primitives.Vector;
//
//        import java.util.List;
//
//        import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @author Dan zilberstein
// */
//class SphereTest {
//
//    Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));
//
//
//    @Test
//    void getNormalTest1() {
//        Sphere sp = new Sphere(1.0, new Point3D(0, 0, 1));
//        assertEquals(new Vector(0,0,1),sp.getNormal(new Point3D(0,0,2)));
//    }
//
//    @Test
//    void getNormalTest2() {
//        Sphere sp = new Sphere(1,new Point3D(0,0,1));
//        assertNotEquals(new Vector(0,0,1),sp.getNormal(new Point3D(0,1,1)));
//        System.out.println(sp.getNormal(new Point3D(0,1,1)));
//    }
//
//    @Test
//    void findIntersectionsTestEP_1()
//    {
//        // ============ Equivalence Partitions Tests ==============
//        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
//        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
//        List<Point3D> exp = List.of(p1, p2);
//
//        // TC01: Ray's line is outside the sphere (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))),
//                "Ray's line out of sphere");
//
//    }
//
//    /**
//     * @author Dan Zilberstein
//     */
//    @Test
//    public void findIntersectionsTest() {
////        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));
//
//        // ============ Equivalence Partitions Tests ==============
//        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
//        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
//        List<Point3D> exp = List.of(p1, p2);
//
//        // TC01: Ray's line is outside the sphere (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))),
//                "Ray's line out of sphere");
//
//        // TC02: Ray starts before and crosses the sphere (2 points)
//        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
//
//        assertEquals( 2, result.size(),"Wrong number of points");
//        if (result.get(0).get_x().get() > result.get(1).get_x().get()) {
//            result = List.of(result.get(1), result.get(0));
//        }
//        assertEquals(exp, result,"Ray crosses sphere");
//
//        // TC03: Ray starts inside the sphere (1 point)
//        assertEquals( List.of(p2),
//                sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0), new Vector(3, 1, 0))),
//                "Ray from inside sphere");
//
//        // TC04: Ray starts after the sphere (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(3, 1, 0))),
//                "Sphere behind Ray");
//
//        // =============== Boundary Values Tests ==================
//        // **** Group: Ray's line crosses the sphere (but not the center)
//
//        // TC11: Ray starts at sphere and goes inside (1 points)
//        assertEquals( List.of(new Point3D(2, 0, 0)),
//                sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(1, 1, 0))),
//                "Ray from sphere inside");
//
//        // TC12: Ray starts at sphere and goes outside (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 1, 0))),
//                "Ray from sphere outside");
//
//        // **** Group: Ray's line goes through the center
//        // TC13: Ray starts before the sphere (2 points)
//        result = sphere.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(0, 1, 0)));
//
//        assertEquals( 2, result.size(),"Wrong number of points");
//        if (result.get(0).get_y().get() > result.get(1).get_y().get()) {
//            result = List.of(result.get(1), result.get(0));
//        }
//        assertEquals(List.of(new Point3D(1, -1, 0), new Point3D(1, 1, 0)), result,
//                "Line through O, ray crosses sphere");
//
//        // TC14: Ray starts at sphere and goes inside (1 points)
//        assertEquals( List.of(new Point3D(1, 1, 0)),
//                sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0))),
//                "Line through O, ray from and crosses sphere");
//
//        // TC15: Ray starts inside (1 points)
//        assertEquals( List.of(new Point3D(1, 1, 0)),
//                sphere.findIntersections(new Ray(new Point3D(1, 0.5, 0), new Vector(0, 1, 0))),
//                "Line through O, ray from inside sphere");
//
//        // TC16: Ray starts at the center (1 points)
//        assertEquals( List.of(new Point3D(1, 1, 0)),
//                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0))),
//                "Line through O, ray from O");
//
//        // TC17: Ray starts at sphere and goes outside (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))),
//                "Line through O, ray from sphere outside");
//
//        // TC18: Ray starts after sphere (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))),
//                "Line through O, ray outside sphere");
//
//        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
//        // TC19: Ray starts before the tangent point
//        assertNull(sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))),
//                "Tangent line, ray before sphere");
//
//        // TC20: Ray starts at the tangent point
//        assertNull(sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))),
//                "Tangent line, ray at sphere");
//
//        // TC21: Ray starts after the tangent point
//        assertNull(sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0))),
//                "Tangent line, ray after sphere");
//
//        // **** Group: Special cases
//        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
//        // center line
//        assertNull(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 0, 1))),
//                "Ray orthogonal to ray head -> O line");
//
//    }
//}
