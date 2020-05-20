package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;


/**
 * Camera class represent a camera that photo 3D geometries
 * @author Yishai Lutvak and Michael Bergshtein
 */

public class Camera {

    protected Point3D location;
    protected Vector vTo;
    protected Vector vUp;
    protected Vector vRight;

    /**
     * Construct a camera by two vectors and location. Calculate the third vector
     * @param location the location of the camera
     * @param vTo vector from the camera to the geometry
     * @param vUp vector from the camera up - orthogonal to vTo
     */
    public Camera(Point3D location,Vector vTo ,Vector vUp ) {

        this.location = new Point3D(location);
        this.vTo = vTo.normalized();
        this.vUp = vUp.normalized();

        //Make sure the vectors vTo orthogonal to vUp
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("vTo not orthogonal to vUp!");

        //Calculate the third vector
        this.vRight = vTo.crossProduct(vUp);
    }
/**********getters**********/

    /**
     * @return location point3D of camera
     */
    public Point3D getLocation() {
        return new Point3D(location);
    }

    /**
     * @return vUp vector of camera
     */
    public Vector getVup() {
        return new Vector(vUp);
    }

    /**
     * @return vRight vector of camera
     */
    public Vector getVright() {
        return new Vector(vRight);
    }

    /**
     * @return vTo vector of camera
     */
    public Vector getVto() {
        return new Vector(vTo);
    }

    /**
     * The constructRayThroughPixel function
     * accepts parameters that represent View Plane
     * and a specific pixel
     * and returns a ray from the camera to the same pixel
     * @param nX number of pixels in x axis of view plane
     * @param nY number of pixels in y axis of view plane
     * @param j index of column on the view plane
     * @param i index of row on the view plane
     * @param screenDistance the distance from the camera to the view plane
     * @param screenWidth the total width of the view plane
     * @param screenHeight the total height of the view plane
     * @return ray from the camera to the pixel
     */
    public Ray constructRayThroughPixel (int nX, int nY,
                                         int j, int i, double screenDistance,
                                     double screenWidth, double screenHeight){
        // If the distance between the View Plane and the camera is not positive, an error will be thrown
        if (screenDistance <= 0)
            throw new IllegalArgumentException("distance must to be greater from zero");

        // Calculate the center point3D of the view plane
        Point3D pCenter = location.add(vTo.scale(screenDistance));

        // Calculate the length and width of the pixel
        double rX = screenWidth/nX;
        double rY = screenHeight/nY;

        // Calculate the distance between the center of the pixel and the center point on the x axis and the y axis
        double yI = (i-(double)nY/2)*rY + rY/2;
        double xJ = (j-(double)nX/2)*rX + rX/2;

        // Calculate the point3D of the pixel center point
        Point3D pIJ = pCenter;
        // Avoid generating vector (0.0 ,0.0, 0.0) in case the center of the pixel is in the center of the View Plane
        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(-yI));

        // Calculates and returns the vector between the camera and the pixel center
        Vector vIJ = pIJ.subtract(location);
        return new Ray(getLocation(),vIJ);
    }
}
