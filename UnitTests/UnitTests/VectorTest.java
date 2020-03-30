package UnitTests;

import org.junit.Test;
import primitives.Vector;
import static java.lang.System.out;
import static primitives.Util.isZero;
import static org.junit.Assert.*;

/**
 * Testing Vectors
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class VectorTest {

    /**
     * Test method for {@link primitives.Vector#subtract(Vector)}.
     */
    @Test
    public void testSubtract() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(-2.0, -2.0, -2.0);

        // Test that subtraction result of the first vector from the second is proper
        Vector exepted1 = v1.subtract(v2);
        assertEquals("Subtraction result of the first vector from the second is not proper",
                exepted1, (new Vector(3.0, 3.0, 3.0)));

        // Test that subtraction result of the second vector from the first is proper
        Vector exepted2 = v2.subtract(v1);
        assertEquals("Subtraction result of the second vector from the first is not proper",
                exepted2, (new Vector(-3.0, -3.0, -3.0)));

        // =============== Boundary Values Tests ==================
        // test zero vector from subtraction result of vectors
        try {
            Vector exepted3 = v1.subtract(v1);
            fail("Subtraction result of vectors that gives zero (0,0,0) does not throw an exception");
        } catch (IllegalArgumentException ex) { }
    }

    /**
     * Test method for {@link primitives.Vector#add(Vector)}.
     */
    @Test
    public void testAdd() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(-2.0, -2.0, -2.0);
        // Test that adding result of vectors is proper
        Vector exepted = v1.add(v2);
        assertEquals("Adding result of vectors is not proper",exepted,(new Vector(-1.0,-1.0,-1.0)));

        // =============== Boundary Values Tests ==================
        Vector v3 = new Vector(-1.0, -1.0, -1.0);
        // test zero vector from Adding result of vectors
        try {
            v1.add(v3);
            fail("Adding result of vectors that gives zero (0,0,0) does not throw an exception");
        } catch (IllegalArgumentException ex) { }
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    public void testScale() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);

        // ============ Equivalence Partitions Tests ==============

        // Test that multiplication result of vector and scalar 1 is proper
        Vector expected1 = v1.scale(1);
        assertEquals("multiplication result of vector and scalar 1 is not proper",expected1,v1);

        // Test that multiplication result of vector and scalar positive number is proper
        Vector expected2 =  v1.scale(2);
        assertEquals("multiplication result of vector and scalar positive number is not proper",
                expected2,(new Vector(2.0,2.0,2.0)));

        // Test that multiplication result of vector and scalar negative number is proper
        Vector expected3 =  v1.scale(-2);
        assertEquals("multiplication result of vector and scalar negative number is not proper",
                expected3,(new Vector(-2.0,-2.0,-2.0)));

        // =============== Boundary Values Tests ==================
        // test zero vector from Multiply by Scalar 0
        try {
            v1.scale(0);
            fail("Vector  multiplied by a zero scale donwt give zero (0,0,0)");
        } catch (IllegalArgumentException ex) { }
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    public void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // Test that dotProduct result of vectors is proper
        Vector v1 = new Vector(3.5,-5,10);
        Vector v2 = new Vector(2.5,7,0.5);
        assertTrue("dotProduct result of vectors is not proper", isZero(v1.dotProduct(v2)-(8.75 + -35 + 5)));

        // =============== Boundary Values Tests ==================
        // Test dot product of orthogonal vectors
        v1 = new Vector(1,2,3);
        v2 = new Vector(0,-3,2);
        assertTrue("dotProduct result of vectors is not proper", isZero(v2.dotProduct(v1)));
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     */
    @Test
    public void testCrossProduct() {

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

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    public void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // Test that the calculation result of length of vector is proper
        Vector v = new Vector(3.5,-5,10);
        assertEquals("the calculation result of length of vector is not proper",
                v.lengthSquared(),(12.25 + 25 + 100),1e-10);
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    public void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // Test that the calculation result of length of vector is proper
        Vector v = new Vector(3.5,-5,10);
        assertEquals("the calculation result of length of vector is not proper",
                v.length(),Math.sqrt(12.25 + 25 + 100),1e-10);
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    public void testNormalize() {
        Vector v = new Vector(3.5,-5,10);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();

        // ============ Equivalence Partitions Tests ==============

        // Test that normalise() don't create a new vector
        assertEquals("ERROR: normalize() function does not changes the original vector",vCopy ,vCopyNormalize);

        // Test that the length of vector after normalize() is 1
        assertEquals("the length of vector after normalize() is not 1", 1, vCopy.length(),1e-10);

        // Test that The direction of vector after normalize() is the original direction:
        // 1. Test that v and vCopy are parallel vectors
        try {
            v.crossProduct(vCopy);
            fail("The direction of vector after normalize() is not the original direction");
        } catch (Exception e) {}
        // 2. Test that v and vCopy are not in opposite directions
        assertTrue("The direction of vector after normalize() is reverse from the original direction",
                v.dotProduct(vCopy)>0);
    }

    /**
     * Test method for {@link Vector#normalized()}.
     */
    @Test
    public void testNormalized() {
        Vector v = new Vector(3.5,-5,10);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalized();

        // ============ Equivalence Partitions Tests ==============

        // Test that normalised() create a new vector
        assertEquals("ERROR: normalized() function changes the original vector",vCopy ,v);

        // Test that the length of vector after normalize() is 1
        assertEquals("the length of vector after normalize() is not 1", 1, vCopyNormalize.length(),1e-10);

        // Test that The direction of vector after normalize() is the original direction:
        // 1. Test that v and vCopy are parallel vectors
        try {
            v.crossProduct(vCopyNormalize);
            fail("The direction of vector after normalize() is not the original direction");
        } catch (Exception e) {}
        // 2. Test that v and vCopy are not in opposite directions
        assertTrue("The direction of vector after normalize() is reverse from the original direction",
                v.dotProduct(vCopyNormalize)>0);
    }
}
