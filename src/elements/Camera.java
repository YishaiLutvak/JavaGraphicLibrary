package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


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
        this.vup = vup.normalize();

        this.vto = vto.normalize();

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

        return null;
    }




}
