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

    @Test
    public void getNormal() {
        Sphere s1 = new Sphere(5, new Point3D(0,0,0));

        // ============ Equivalence Partitions Tests ==============

        assertTrue(s1.getNormal(new Point3D(0,0,5)).equals(new Vector(new Point3D(0,0,1))));
        assertTrue(s1.getNormal(new Point3D(0,0,-5)).equals(new Vector(new Point3D(0,0,-1))));
        assertTrue(s1.getNormal(new Point3D(0,5,0)).equals(new Vector(new Point3D(0,1,0))));
        assertTrue(s1.getNormal(new Point3D(0,-5,0)).equals(new Vector(new Point3D(0,-1,0))));
        assertTrue(s1.getNormal(new Point3D(5,0,0)).equals(new Vector(new Point3D(1,0,0))));
        assertTrue(s1.getNormal(new Point3D(-5,0,0)).equals(new Vector(new Point3D(-1,0,0))));
    }
}