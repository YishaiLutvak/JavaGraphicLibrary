package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;


/**
 * represent a camera that photo 3D geometries
 * @author Yishai Lutvak and Michael Bergshtein
 */

public class Camera {

    protected Point3D location;
    protected Vector vTo;
    protected Vector vUp;
    protected Vector vRight;

    /**
     * Constract a camera by two vectors and location. Calculate the third vector
     * @param location the  location of the camera
     *
     * @param vTo vector from the camera to the geometry
     * @param vUp vector from the camera up. orthogonal to vTo
     */
    public Camera(Point3D location,Vector vTo ,Vector vUp ) {
        this.location = new Point3D(location);
        this.vTo = vTo.normalized();
        this.vUp = vUp.normalized();
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("vTo not orthogonal to vUp!");
        this.vRight = vTo.crossProduct(vUp);
    }
/**********getters**********/
    public Point3D getLocation() {
        return new Point3D(location);
    }

    public Vector getVup() {
        return new Vector(vUp);
    }

    public Vector getVright() {
        return new Vector(vRight);
    }

    public Vector getVto() {
        return new Vector(vTo);
    }

    /**
     *
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

        if (screenDistance <= 0)
            throw new IllegalArgumentException("distance must to be greater from zero");
        Point3D pCenter = location.add(vTo.scale(screenDistance));

        double rX = screenWidth/nX;
        double rY = screenHeight/nY;

        double yI = (i-(double)nY/2)*rY + rY/2;
        double xJ = (j-(double)nX/2)*rX + rX/2;

        Point3D pIJ = pCenter;
        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(-yI));

        Vector vIJ = pIJ.subtract(location);
        return new Ray(getLocation(),vIJ);
    }
}
