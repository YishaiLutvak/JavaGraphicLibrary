package UnitTests;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Testing Geometries
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class GeometriesTest {

    /**
     * Test method for {@link geometries.Geometries#getFindIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Triangle tri = new Triangle(
                new Point3D(4, 0, 0),
                new Point3D(0, 4, 0),
                new Point3D(0, 0, 4));
        Plane pl = new Plane(
                new Point3D(0,4,1),
                new Point3D(1,4,0),
                new Point3D(-1,4,0));
        Sphere sph = new Sphere(
                5,
                new Point3D(0,10,0));

        Geometries threeGeometries = new Geometries(tri,pl,sph);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Some geometries are intersected but not all
        List<GeoPoint> result = threeGeometries.getFindIntersections(new Ray(
                        new Point3D(0, 0, -1),
                        new Vector(0, 1, 0)));
        assertEquals("Some geometries are intersected but not all - Wrong number of points", 3, result.size());

        // =============== Boundary Values Tests ==================

        // TC11: Empty geometry collection
        Geometries emptyGeometries = new Geometries();
        assertEquals("Empty geometry collection - Wrong number of points", null,
                emptyGeometries.getFindIntersections(new Ray(
                new Point3D(-1, 0, 0),
                new Vector(3, 1, 0))));

        // TC12: There are no intersection points
        result = threeGeometries.getFindIntersections(new Ray(
                        new Point3D(0, -1, 0),
                        new Vector(0, 0, 1)));
        assertEquals("There are no intersection points - Wrong number of points", null, result);

        // TC13: Only one geometry is intersected
        result = threeGeometries.getFindIntersections(new Ray(
                        new Point3D(0, 0, -6),
                        new Vector(0, 1, 0)));
        assertEquals("Only one geometry is intersected - Wrong number of points", 1, result.size());

        // TC14: All geometries are intersected
        result = threeGeometries.getFindIntersections(new Ray(
                        new Point3D(1, -1 ,1),
                        new Vector(0, 1, 0)));
        assertEquals("All geometries are intersected - Wrong number of points", 4, result.size());
    }
}