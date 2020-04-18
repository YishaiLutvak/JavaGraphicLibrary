package UnitTests;

import geometries.*;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class GeometriesTest {

    @Test
    public void testAdd() {
    }

    @Test
    public void testFindIntersections() {
        Triangle tri = new Triangle(
                new Point3D(0, 0, 2),
                new Point3D(2, 0, 0),
                new Point3D(0, 2, 0));
        Plane pl = new Plane(
                new Point3D(0,4,1),
                new Point3D(1,4,0),
                new Point3D(-1,4,0));
        Sphere sph = new Sphere(5, new Point3D(0,10,0));
        Geometries threeGeometries = new Geometries();
        threeGeometries.add(tri,pl,sph);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Some geometries are intersection but not all
        List<Point3D> result1 = threeGeometries.findIntersections(
                new Ray(
                        new Point3D(0, 0, -1),
                        new Vector(0, 1, 0)
                )
        );
        assertEquals("Wrong number of points", 3, result1.size());

        // =============== Boundary Values Tests ==================
        // TC11: Empty geometry collection
        Geometries emptyGeometries = new Geometries();
        List<Point3D> result11 = emptyGeometries.findIntersections(
                new Ray(
                        new Point3D(-1, 0, 0),
                        new Vector(3, 1, 0)
                )
        );
        assertEquals("Wrong number of points", 0, result11.size());

        // TC12: There is no intersection points
        List<Point3D> result12 = threeGeometries.findIntersections(
                new Ray(
                        new Point3D(0, -1, 0),
                        new Vector(0, 0, 1)
                )
        );
        assertEquals("Wrong number of points", 0, result12.size());

        // TC13: Only one geometry is intersection
        List<Point3D> result13 = threeGeometries.findIntersections(
                new Ray(
                        new Point3D(0, 0, -6),
                        new Vector(0, 1, 0)
                )
        );
        assertEquals("Wrong number of points", 1, result13.size());

        // TC13: All geometries are intersection
        List<Point3D> result14 = threeGeometries.findIntersections(
                new Ray(
                        new Point3D(1, 1 ,1),
                        new Vector(0, 1, 0)
                )
        );
        assertEquals("Wrong number of points", 4, result14.size());
    }
}