package UnitTests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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
}