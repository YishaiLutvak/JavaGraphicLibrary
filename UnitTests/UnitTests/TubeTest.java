package UnitTests;

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
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Tube tu = new Tube(5, new Ray(new Point3D(0,0,0),new Vector(1,0,0)));

        // Test that the getNormal function of tube is proper
        assertTrue("getNormal function is not proper",
                tu.getNormal(new Point3D(1,5,0)).equals(new Vector(new Point3D(0,1,0))));
        assertTrue("getNormal function is not proper",
                tu.getNormal(new Point3D(0,-5,0)).equals(new Vector(new Point3D(0,-1,0))));
        assertTrue("getNormal function is not proper",
                tu.getNormal(new Point3D(-1,0,5)).equals(new Vector(new Point3D(0,0,1))));
        assertTrue("getNormal function is not proper",
                tu.getNormal(new Point3D(0,0,-5)).equals(new Vector(new Point3D(0,0,-1))));
    }
}