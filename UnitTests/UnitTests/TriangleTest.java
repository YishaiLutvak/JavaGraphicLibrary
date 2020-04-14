package UnitTests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import static org.junit.Assert.*;

/**
 * Testing Triangles
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class TriangleTest {
    /**
     * Test method for {@link geometries.Polygon#getNormal(Point3D)}.
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

    @Test
    public void findIntersections() {
    }
}
