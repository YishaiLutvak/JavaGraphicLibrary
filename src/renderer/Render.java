package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * Render class for rendering a image
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Render {
    ImageWriter _imageWriter;
    Scene _scene;

    /**
     * constructor
     * @param imageWriter save in file the image
     * @param scene contain all the elements of the scene
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    /**
     * renderImage function
     * The function colors the view plane
     * If their are intersection points with geometries,
     * the function Takes the closest intersection points to the camera,
     * and paints the pixels of the closest points in the color of the points.
     * In addition, the function colors the rest of the pixels in the background color
     */
    public void renderImage(){
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        java.awt.Color background = _scene.getBackground().getColor();

        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();
        double distance = _scene.getDistance();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints==null)
                    _imageWriter.writePixel(j, i, background);
                else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
                }
            }
        }
    }

    /**
     * Acting imageWriter's writeToImage function
     */
    public void writeToImage(){
        _imageWriter.writeToImage();
    }

    /**
     * calcColor function
     * Calculate the point's color
     * @param point a point3D
     * @return color representing the point's appearance
     */
    private Color calcColor(Point3D point){
        return _scene.getAmbientLight().getIntensity();
    }

    /**
     * getClosestPoint private function
     * @param intersectionPoints a list of intersection points
     * between the geometries and the ray from the camera
     * @return the closest point
     */
    private Point3D getClosestPoint(List<Point3D> intersectionPoints){
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
     * printGrid function
     * @param interval the size of the grid's squares
     * @param color the color of the grid
     */
    public void printGrid(int interval, java.awt.Color color){
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();

        for (int i= 0; i < Ny;i++)
            for (int j = 0; j <Nx; j++)
                if(i % interval == 0 || j % interval == 0 )
                    _imageWriter.writePixel(j, i, color);
    }
}