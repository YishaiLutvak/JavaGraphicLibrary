package UnitTests;
import org.junit.Test;
import primitives.Vector;
import static primitives.Util.isZero;

import static org.junit.Assert.*;

/**
 * Testing Vectors
 *  @author Michael Bergshtein and Yishay Lutvak
 */
public class VectorTest {

    @Test
    public void subtract() {

        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-2.0, -2.0, -2.0);

        Vector exepted1 = v1.subtract(v2);
        assertEquals(exepted1, (new Vector(3.0, 3.0, 3.0)));

        Vector exepted2 = v2.subtract(v1);
        assertEquals(exepted2, (new Vector(-3.0, -3.0, -3.0)));

        // =============== Boundary Values Tests ==================
        try {
            Vector exepted3 = v1.subtract(v1);
            fail("Vector cannot be subtracted by itself because the result will be zero (0,0,0)");
        } catch (IllegalArgumentException ex) { }
    }


    @Test
    public void add() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-2.0, -2.0, -2.0);

        Vector exepted1 = v1.add(v2);
        assertEquals(exepted1,(new Vector(-1.0,-1.0,-1.0)));

        Vector exepted2 = v2.add(v1);
        assertEquals(exepted2,(new Vector(-1.0,-1.0,-1.0)));
        // =============== Boundary Values Tests ==================
        try {
            v1.add(new Vector(-1.0,-1.0,-1.0));
            fail("Vector cannot be multiplied by its double-1 because the result will be zero (0,0,0)");
        } catch (IllegalArgumentException ex) { }
    }

    @Test
    public void scale() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1.0, 1.0, 1.0);

        Vector expected1 = v1.scale(1);
        assertEquals(expected1,v1);

        Vector expected2 =  v1.scale(2);
        assertEquals(expected2,(new Vector(2.0,2.0,2.0)));

        Vector expected3 =  v1.scale(-2);
        assertEquals(expected3,(new Vector(-2.0,-2.0,-2.0)));

        // =============== Boundary Values Tests ==================

        try {
            v1.scale(0);
            fail("Vector cannot be multiplied by a zero scale because the result will be zero (0,0,0)");
        } catch (IllegalArgumentException ex) { }
    }

    @Test
    public void dotProduct() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(3.5,-5,10);
        Vector v2 = new Vector(2.5,7,0.5);

        assertTrue(Double.compare(v1.dotProduct(v2), (8.75 + -35 + 5)) == 0);
        //נראה לי מיותר - תגיד לי מה אתה חושב
        /*assertTrue(Double.compare(v2.dotProduct(v1), (8.75 + -35 + 5)) == 0);

        // =============== Boundary Values Tests ==================

        Vector v3 = new Vector(2.0,2.0,0.0);
        assertTrue(v3.dotProduct(new Vector(-2.0,2.0,0.0)) == 0);*/
    }

    @Test
    public void crossProduct() {

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-product of co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }

    @Test
    public void length() {
        // ============ Equivalence Partitions Tests ==============

        Vector v = new Vector(3.5,-5,10);
        assertEquals("",v.length(),Math.sqrt(12.25 + 25 + 100),1e-10);
    }

    @Test
    public void normalize() {
        // ============ Equivalence Partitions Tests ==============

        Vector v = new Vector(3.5,-5,10);
        v.normalize();
        assertEquals("", 1, v.length(),1e-10);
    }
}
