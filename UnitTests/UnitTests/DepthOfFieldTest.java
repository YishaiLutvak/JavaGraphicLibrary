package UnitTests;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.*;
import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import static org.junit.Assert.*;

public class DepthOfFieldTest {

    @Test
    public void trianglesTransparentSphere() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0),
                1050, 12, 5, true));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)));

        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("depthOfFieldTest", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void trianglesTransparentSphere2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0),
                1150, 12, 5, true));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)));

        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("depthOfFieldTest2", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }


    @Test
    public void depthOfFieldFullImage() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, -1000, -1000), new Vector(0, 1, 1), new Vector(0, -1, 1),
                1050, 12, 9, false));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.15));

        Geometries table  = new Geometries(
                //plate of the table
                new Polygon(new Color(new Color(java.awt.Color.GREEN)), new Material(0.5, 0.5, 60,0.5,0), //
                        new Point3D(-100,0,-100), new Point3D(100,0,-100), new Point3D(100,0,100),//
                        new Point3D(-100,0,100)),

                //legs of the table
                new Cylinder(new Color(new Color(java.awt.Color.GREEN)), new Material(0.5, 0.5, 60,0.5,0),//
                        10,new Ray(new Point3D(80,0,-80),new Vector(0,1,0)), 50),
                new Cylinder(new Color(new Color(java.awt.Color.GREEN)), new Material(0.5, 0.5, 60,0.5,0),//
                        10,new Ray(new Point3D(-80,0,-80),new Vector(0,1,0)), 50),
                new Cylinder(new Color(new Color(java.awt.Color.GREEN)), new Material(0.5, 0.5, 60,0.5,0),//
                        10,new Ray(new Point3D(80,0,80),new Vector(0,1,0)), 50),
                new Cylinder(new Color(new Color(java.awt.Color.GREEN)), new Material(0.5, 0.5, 60,0.5,0),//
                        10,new Ray(new Point3D(-80,0,80),new Vector(0,1,0)), 50));

        Geometries bottle = new Geometries(new Cylinder(new Color(new Color(java.awt.Color.RED)), new Material(0.5, 0.5, 60,0.5,0),
                10,new Ray(new Point3D(80,0,80),new Vector(0,-1,0)), 25),
                new Cylinder(new Color(new Color(java.awt.Color.RED)), new Material(0.5, 0.5, 60,0.5,0),
                        2,new Ray(new Point3D(80,-35,80),new Vector(0,-1,0)), 10),
                new Sphere(new Color(new Color(java.awt.Color.RED)), new Material(0.5, 0.5, 60,0.5,0),
                        10,new Point3D(80,-25,80)));

        scene.addGeometries(table, bottle);


        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("depthOfFieldFullImage", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}