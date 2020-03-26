package UnitTests;

import org.junit.Test;
import primitives.Vector;

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
            fail("Subtraction of the vector by itself do not give zero");
        } catch (IllegalArgumentException ex) { }
    }


    @Test
    public void add() {
        // ============ Equivalence Partitions Tests ==============
        // =============== Boundary Values Tests ==================
    }

    @Test
    public void scale() {
        // ============ Equivalence Partitions Tests ==============
        // =============== Boundary Values Tests ==================
    }

    @Test
    public void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // =============== Boundary Values Tests ==================
    }

    @Test
    public void crossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // =============== Boundary Values Tests ==================
    }

    @Test
    public void length() {
        // ============ Equivalence Partitions Tests ==============
        // =============== Boundary Values Tests ==================
    }

    @Test
    public void normalize() {
        // ============ Equivalence Partitions Tests ==============
        // =============== Boundary Values Tests ==================
    }
}
