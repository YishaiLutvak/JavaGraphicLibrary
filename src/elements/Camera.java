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
    protected Vector vUp;
    protected Vector vRight;
    protected Vector vTo;

    /**
     * Constract a camera by two vectors and location. Calculate the thirs vector
     * @param location the  location of the camera
     *
     * @param vTo vector from the camera to the geometry
     * @param vUp vector from the camera up. orhogonal to vto
     */
    public Camera(Point3D location, Vector vUp, Vector vTo) {
        this.location = new Point3D(location);
        this.vUp = vUp.normalized();
        this.vTo = vTo.normalized();
        if (!isZero(vUp.dotProduct(vTo)))
            throw new IllegalArgumentException("vup not orthogonal to vto!");
        this.vRight = vUp.crossProduct(vTo);
    }
/**********getters**********/
    public Point3D getLocation() {
        return location;
    }

    public Vector getVup() {
        return vUp;
    }

    public Vector getVright() {
        return vRight;
    }

    public Vector getVto() {
        return vTo;
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

        Point3D pCenter = location.add(vTo.scale(screenDistance));
        double rX = screenWidth/nX;
        double rY = screenHeight/nY;

        Point3D pIJ = pCenter;
        double yI = (i-nY/2)*rY + rY/2;
        double xJ = (j-nX/2)*rX + rX/2;

        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(-yI));

        Vector vIJ = new Vector(pIJ.subtract(location));
        return new Ray(new Point3D(location),vIJ);
    }
}
