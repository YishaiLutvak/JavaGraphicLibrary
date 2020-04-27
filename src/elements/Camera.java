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
    protected Vector vup;
    protected Vector vright;
    protected Vector vto;

    /**
     * Constract a camera by two vectors and location. Calculate the thirs vector
     * @param location the  location of the camera
     *
     * @param vto vector from the camera to the geometry
     * @param vup vector from the camera up. orhogonal to vto
     */
    public Camera(Point3D location, Vector vup, Vector vto) {
        this.location = location;
        this.vup = vup.normalized();
        this.vto = vto.normalized();
        if (!isZero(vup.dotProduct(vto)))
            throw new IllegalArgumentException("vup not orthogonal to vto!");
        this.vright = vup.crossProduct(vto);
    }
/**********getters**********/
    public Point3D getLocation() {
        return location;
    }

    public Vector getVup() {
        return vup;
    }

    public Vector getVright() {
        return vright;
    }

    public Vector getVto() {
        return vto;
    }

    /**
     *
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @param screenDistance
     * @param screenWidth
     * @param screenHeight
     * @return
     */
    public Ray constructRayThroughPixel (int nX, int nY,
                                         int j, int i, double screenDistance,
                                     double screenWidth, double screenHeight){

        Point3D pc = location.add(vto.scale(screenDistance));
        double rX = screenWidth/nX;
        double rY = screenHeight/nY;
        Point3D pij = new Point3D(pc.add(vright.scale((j-(nX-1)/2)*rX).subtract(vup.scale((i-(nY-1)/2)*rY))));
        Vector vij = new Vector(pij.subtract(location));
        return new Ray(new Point3D(location),vij);
    }




}
