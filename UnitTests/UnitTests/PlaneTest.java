package UnitTests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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
        // ============ Equivalence Partitions Tests ==============

    }
}