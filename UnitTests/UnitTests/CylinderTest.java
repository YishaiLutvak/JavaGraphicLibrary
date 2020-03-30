package UnitTests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * Testing Cylindes
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class CylinderTest {
    /**
     *
     */

    @Test
    public void testGetNormal() {
        Cylinder cy = new Cylinder(5, new Ray(new Point3D(0,0,0),new Vector(1,0,0)),10);

        // ============ Equivalence Partitions Tests ==============

        //
        assertTrue(cy.getNormal(new Point3D(1,5,0)).equals(new Vector(new Point3D(0,1,0))));
        assertTrue(cy.getNormal(new Point3D(1,-5,0)).equals(new Vector(new Point3D(0,-1,0))));
        assertTrue(cy.getNormal(new Point3D(1,0,5)).equals(new Vector(new Point3D(0,0,1))));
        assertTrue(cy.getNormal(new Point3D(1,0,-5)).equals(new Vector(new Point3D(0,0,-1))));



        //
        assertTrue(cy.getNormal(new Point3D(0,0,0)).equals(new Vector(new Point3D(-1,0,0))));

        //
        assertTrue(cy.getNormal(new Point3D(10,0,0)).equals(new Vector(new Point3D(1,0,0))));

        // =============== Boundary Values Tests ==================

        //
        assertTrue(cy.getNormal(new Point3D(0,5,0)).equals(new Vector(new Point3D(-1,0,0))));

        //
        assertTrue(cy.getNormal(new Point3D(10,0,5)).equals(new Vector(new Point3D(1,0,0))));
    }
}