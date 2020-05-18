package UnitTests;

import org.junit.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

    /**
     * Test rendering abasic image
     *
     * @author Dan
     */
    public class RenderTests {

        /**
         * Produce a scene with basic 3D model and render it into a jpeg image with a
         * grid
         */
        @Test
        public void basicRenderTwoColorTest() {
            Scene scene = new Scene("Test scene");
            scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(100);
            scene.setBackground(new Color(75, 127, 90));
            scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1));

            scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

            scene.addGeometries(
                    new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                    new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                    new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                    new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

            ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.printGrid(50, java.awt.Color.YELLOW);
            render.writeToImage();
        }

        @Test
        public void basicRenderMultiColorTest() {
            Scene scene = new Scene("Test scene");
            scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(100);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));

            scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

            scene.addGeometries(
                    new Triangle(new Color(java.awt.Color.BLUE),
                            new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),      // lower right
                    new Triangle(
                            new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),    // upper right
                    new Triangle(new Color(java.awt.Color.RED),
                            new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),    // lower left
                    new Triangle(new Color(java.awt.Color.GREEN),
                            new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100))); // upper left

            ImageWriter imageWriter = new ImageWriter("color render test", 500, 500, 500, 500);
            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.printGrid(50, java.awt.Color.WHITE);
            render.writeToImage();
        }
        /**
         * Test method for {@link Render#getClosestPoint(List)}.
         */
        /*@Test
        public void TestGetClosestPoint() {
            Scene scene = new Scene("Test getClosestPoint method");
            scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1);
            scene.setBackground(new Color(75, 127, 90));
            scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1));

            scene.addGeometries(new Sphere(1, new Point3D(0, 0, 4)));

            ImageWriter imageWriter = new ImageWriter("render for getClosestPoint test", 500, 500, 500, 500);
            Render render = new Render(imageWriter, scene);

            Point3D closest = render.getClosestPoint(List.of(new Point3D(0, 0, 3) ,new Point3D(0, 0, 5)));
            assertEquals("The point is not the closest point to camera",closest,new Point3D(0,0,3));
        }*/
}
