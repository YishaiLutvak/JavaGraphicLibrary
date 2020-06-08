package UnitTests;

import elements.AmbientLight;
import elements.Camera;
import elements.ImprovedSpot;
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
        scene.setCamera(new Camera(new Point3D(0, -120, -1000), new Vector(0, 0.05, 1), new Vector(0, -1, 0.05),
                7000, 16, 3, true));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.15));


        Geometries planes = new Geometries(
                new Plane(new Color(25,0,51), new Material(0.5, 0.5, 60,0,0),
                        new Point3D(0,50,0),new Vector(0,1,0))/*,
                new Plane(Color.BLACK, new Material(0.4, 0.6, 60,0,0),
                        new Point3D(0,50,10000),new Vector(0,0,1))*/
                );

        Tube MyTube = new Tube(new Color(25,70,51), new Material(0.5, 0.5, 500,0,0),//
                80,new Ray(new Point3D(0,-50,100000),new Vector(1,0,0)));

        Tube MyCylinder = new Cylinder(new Color(50,0,0), new Material(0.2, 0.2, 500,0,0.6),//
                80,new Ray(new Point3D(0,50,6000),new Vector(0,-1,0)),50);

        Geometries table  = new Geometries(
                //plate of the table
                /*new Polygon(new Color(76,0,153), new Material(0.5, 0.5, 60,0,0), //
                        new Point3D(-50,0,100), new Point3D(50,0,100), new Point3D(50,0,150),//
                        new Point3D(-50,0,150)),*/
                new Cylinder(new Color(153,76,0), new Material(0.5, 0.5, 60,0.5,0),//
                        65,new Ray(new Point3D(0,0,175),new Vector(0,1,0)), 2),

                //legs of the table
                new Cylinder(new Color(153,76,0), new Material(0.5, 0.5, 60,0.5,0),//
                        1,new Ray(new Point3D(41,0,160),new Vector(0,1,0)), 60),
                new Cylinder(new Color(153,76,0), new Material(0.5, 0.5, 60,0.5,0),//
                        1,new Ray(new Point3D(-41,0,160),new Vector(0,1,0)), 60),
                new Cylinder(new Color(153,76,0), new Material(0.5, 0.5, 60,0.5,0),//
                        1,new Ray(new Point3D(39,0,190),new Vector(0,1,0)), 60),
                new Cylinder(new Color(153,76,0), new Material(0.5, 0.5, 60,0.5,0),//
                        1,new Ray(new Point3D(-39,0,190),new Vector(0,1,0)), 60));

        Geometries bottle = new Geometries(
                new Cylinder(new Color(70,0,0), new Material(0.5, 0.5, 60,0.5,0),
                10,new Ray(new Point3D(10,0,175),new Vector(0,-1,0)), 25),
                new Cylinder(new Color(70,0,0), new Material(0.5, 0.5, 60,0.5,0),
                        2,new Ray(new Point3D(10,-35,175),new Vector(0,-1,0)), 10),
                new Sphere(new Color(70,0,0), new Material(0.5, 0.5, 60,0.5,0),
                        10,new Point3D(10,-25,175)));

        Geometries KiddushCup = new Geometries(
                new Cylinder(new Color(32,32,32), new Material(0.5, 0.5, 60,0.5,0),
                6,new Ray(new Point3D(-15,0,175),new Vector(0,-1,0)), 2),
                new Cylinder(new Color(32,32,32), new Material(0.5, 0.5, 60,0.5,0),
                        1,new Ray(new Point3D(-15,-2,175),new Vector(0,-1,0)), 5),
                new Cylinder(new Color(32,32,32), new Material(0.5, 0.5, 60,0.5,0),
                        6,new Ray(new Point3D(-15,-13,175),new Vector(0,-1,0)), 10),
                new Sphere(new Color(32,32,32), new Material(0.5, 0.5, 60,0.5,0),
                        6,new Point3D(-15,-13,175)));

        Geometries balls = new Geometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30),
                        150,new Point3D(0,-200,6000)),
                new Sphere(new Color(80,80,0), new Material(0.1, 0.1 ,0,0.8,0),
                        120,new Point3D(270,-170,6000)),
                new Sphere(new Color(0,50,0), new Material(0.1, 0.1 ,0,0.8,0.8),
                        120,new Point3D(-270,-170,6000)),
                new Sphere(new Color(60,0,0), new Material(0.05, 0.05, 60,0.0,0.9),
                        5000,new Point3D(0,4950,6000))
        );

        Geometries cylinders = new Geometries(
                new Cylinder(new Color(0,73,25), new Material(0.2, 0.2, 60,0,0.6),
                        80,new Ray(new Point3D(460,-70,6000),new Vector(0.1,-1,-0.7)), 70),
                new Cylinder(new Color(0,73,25), new Material(0.2, 0.2, 60,0,0.6),
                        60,new Ray(new Point3D(460,-70,6000),new Vector(0.1,-1,-0.7)), 110),
                new Cylinder(new Color(0,73,25), new Material(0.2, 0.2, 60,0,0.6),
                        40,new Ray(new Point3D(460,-70,6000),new Vector(0.1,-1,-0.7)), 150),
                new Cylinder(new Color(102,51,0), new Material(0.5, 0.5, 60,0,0),
                        80,new Ray(new Point3D(-460,-70,6000),new Vector(-0.1,-1,-0.7)), 70),
                new Cylinder(new Color(102,51,0), new Material(0.5, 0.5, 60,0,0),
                        60,new Ray(new Point3D(-460,-70,6000),new Vector(-0.1,-1,-0.7)), 110),
                new Cylinder(new Color(102,51,0), new Material(0.5, 0.5, 60,0,0),
                        40,new Ray(new Point3D(-460,-70,6000),new Vector(-0.1,-1,-0.7)), 150)
        );

        scene.addGeometries(table, bottle, planes, KiddushCup , balls, cylinders/*,MyCylinder, MyTube*/);


        scene.addLights(
                new SpotLight(new Color(150, 150, 150), //
                        new Point3D(0, -50, 0), new Vector(0, 0, 1), 1, 4E-3, 2E-5),
                new SpotLight(new Color(700, 400, 400), //
                        new Point3D(200, -350, 5), new Vector(-1, 1, 4), 1, 4E-6, 2E-8)/*,
                new ImprovedSpot(new Color(500, 500, 500),
                        new Point3D(0,-100,6000), new Vector(-2, 1, 0), 1, 0.0001, 0.000005, 2)*/
        );

        ImageWriter imageWriter = new ImageWriter("depthOfFieldFullImage_3", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}