package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


public class Camera {
    protected Point3D location;
    protected Vector vup;
    protected Vector vright;
    protected Vector vto;

    public Camera(Point3D location, Vector vup, Vector vto) {
        this.location = location;
        this.vup = vup.normalize();

        this.vto = vto.normalize();

        this.vright = vup.crossProduct(vto);


    }

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
    public Ray constructRayThroughPixel (int nX, int nY,
                                         int j, int i, double screenDistance,
                                         double screenWidth, double screenHeight){

        return null;
    }




}
