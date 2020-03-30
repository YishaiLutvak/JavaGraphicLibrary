package UnitTests;

import geometries.Cylinder;
import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * Testing Tubes
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class TubeTest {

    @Test
    public void getNormal() {
        Tube tu = new Tube(5, new Ray(new Point3D(0,0,0),new Vector(1,0,0)));

        // ============ Equivalence Partitions Tests ==============
        assertTrue(tu.getNormal(new Point3D(1,5,0)).equals(new Vector(new Point3D(0,1,0))));
        assertTrue(tu.getNormal(new Point3D(0,-5,0)).equals(new Vector(new Point3D(0,-1,0))));
        assertTrue(tu.getNormal(new Point3D(-1,0,5)).equals(new Vector(new Point3D(0,0,1))));
        assertTrue(tu.getNormal(new Point3D(0,0,-5)).equals(new Vector(new Point3D(0,0,-1))));
    }
}