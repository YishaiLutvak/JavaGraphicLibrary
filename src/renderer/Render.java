package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * rendering a basic image
 *
 * @author
 */
public class Render {
    ImageWriter _imageWriter;
    Scene _scene;

    public Render(ImageWriter _imageWriter, Scene _scene) {
        this._imageWriter = _imageWriter;
        this._scene = _scene;
    }

    /**
     *
     */
    public 	void renderImage(){
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        java.awt.Color background = _scene.getBackground().getColor();
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();

        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();
        double distance = _scene.getDistance();

        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints.isEmpty())
                    _imageWriter.writePixel(j, i, background);
                else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
                }
            }

        }




            

    }

    /**
     *
     * @param p
     * @return
     */
    private Color calcColor(Point3D p){
        return _scene.getAmbientLight().getIntensity();
    }

    /**
     *
     * @param intersectionPoints
     * @return
     */
    private Point3D	getClosestPoint(List<Point3D> intersectionPoints){
        Point3D cameraLocation = _scene.getCamera().getLocation();
        Point3D min = intersectionPoints.get(0);
        for (Point3D point: intersectionPoints) {
            if (point.distance(cameraLocation) < min.distance(cameraLocation)) {
                min = point;
            }
        }
        return min;
    }

    /**
     *
     * @param interval
     * @param color
     */
    public	void printGrid(int interval, java.awt.Color color){
        int Nx=_imageWriter.getNx();
        int Ny = _imageWriter.getNy();

        for (int i= 0; i < Ny;i++)
            for (int j = 0; j <Nx; j++)
                if(i % interval == 0 || j % interval == 0 )
                    _imageWriter.writePixel(j,i, color);
    }
}



    /*



else
Point3D closestPoint = getClosestPoint(intersectionPoints);
_imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
*/



