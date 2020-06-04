package elements;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;


/**
 * Camera class represent a camera that photo 3D geometries
 * @author Yishai Lutvak and Michael Bergshtein
 */

public class Camera {

    private Point3D _location;
    private Vector _vTo;
    private Vector _vUp;
    private Vector _vRight;

    private double _focusDistance;
    private Plane _focalPlane;
    private double _aperture;
    private int _dimensionRays;
    private boolean _actDepthOfField;


    /**
     * Construct a camera by two vectors and location. Calculate the third vector
     * @param location the location of the camera
     * @param vTo vector from the camera to the geometry
     * @param vUp vector from the camera up - orthogonal to vTo
     */
    public Camera(Point3D location,Vector vTo ,Vector vUp) {

        this._location = new Point3D(location);
        this._vTo = vTo.normalized();
        this._vUp = vUp.normalized();

        //Make sure the vectors vTo orthogonal to vUp
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("vTo not orthogonal to vUp!");

        //Calculate the third vector
        this._vRight = vTo.crossProduct(vUp);

        this._actDepthOfField = false;

    }

    /**
     * Construct a camera by two vectors and location. Calculate the third vector
     * @param location the location of the camera
     * @param vTo vector from the camera to the geometry
     * @param vUp vector from the camera up - orthogonal to vTo
     * @param focusDistance
     * @param aperture
     */
    public Camera(Point3D location,Vector vTo ,Vector vUp ,double focusDistance, double aperture, boolean actDepthOfField) {

        this._location = new Point3D(location);
        this._vTo = vTo.normalized();
        this._vUp = vUp.normalized();

        //Make sure the vectors vTo orthogonal to vUp
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("vTo not orthogonal to vUp!");

        //Calculate the third vector
        this._vRight = vTo.crossProduct(vUp);

        this._aperture = aperture;
        this._focusDistance = focusDistance;
        this._focalPlane = new Plane(new Ray(_location,_vTo).getPoint(_focusDistance),_vTo);
        this._actDepthOfField = actDepthOfField;
    }
/**********getters**********/

    /**
     * @return location point3D of camera
     */
    public Point3D getLocation() {
        return new Point3D(_location);
    }

    /**
     * @return vUp vector of camera
     */
    public Vector getVup() {
        return new Vector(_vUp);
    }

    /**
     * @return vRight vector of camera
     */
    public Vector getVright() {
        return new Vector(_vRight);
    }

    /**
     * @return vTo vector of camera
     */
    public Vector getVto() {
        return new Vector(_vTo);
    }

    /*public void setFocusDistance(double _focusDistance) {
        this._focusDistance = _focusDistance;
        this._focalPlane = new Plane(new Ray(_location,_vTo).getPoint(_focusDistance),_vTo);
    }

    public void setAperture(double _aperture) {
        this._aperture = _aperture;
    }*/

    public boolean isActDepthOfField() {
        return _actDepthOfField;
    }

    public void setActDepthOfField(boolean _actDepthOfField) {
        this._actDepthOfField = _actDepthOfField;
    }


   /* *//**
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
     *//*
    public Ray constructRayThroughPixel (int nX, int nY,
                                         int j, int i, double screenDistance,
                                     double screenWidth, double screenHeight){
        // If the distance between the View Plane and the camera is not positive, an error will be thrown
        if (screenDistance <= 0)
            throw new IllegalArgumentException("distance must to be greater from zero");

        // Calculate the center point3D of the view plane
        Point3D pCenter = _location.add(_vTo.scale(screenDistance));

        // Calculate the length and width of the pixel
        double rX = screenWidth/nX;
        double rY = screenHeight/nY;

        // Calculate the distance between the center of the pixel and the center point on the x axis and the y axis
        double yI = (i-(double)nY/2)*rY + rY/2;
        double xJ = (j-(double)nX/2)*rX + rX/2;

        // Calculate the point3D of the pixel center point
        Point3D pIJ = pCenter;
        // Avoid generating vector (0.0 ,0.0, 0.0) in case the center of the pixel is in the center of the View Plane
        if (xJ != 0) pIJ = pIJ.add(_vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(_vUp.scale(-yI));

        // Calculates and returns the vector between the camera and the pixel center
        Vector vIJ = pIJ.subtract(_location);
        return new Ray(getLocation(),vIJ);
    }*/


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
    public List<Ray> constructBeamThroughPixel (int nX, int nY,
                                             int j, int i, double screenDistance,
                                             double screenWidth, double screenHeight){
        // If the distance between the View Plane and the camera is not positive, an error will be thrown
        if (screenDistance <= 0)
            throw new IllegalArgumentException("distance must to be greater from zero");

        // Calculate the center point3D of the view plane
        Point3D pCenter = _location.add(_vTo.scale(screenDistance));

        // Calculate the length and width of the pixel
        double rX = screenWidth/nX;
        double rY = screenHeight/nY;

        // Calculate the distance between the center of the pixel and the center point on the x axis and the y axis
        double yI = (i-(double)nY/2)*rY + rY/2;
        double xJ = (j-(double)nX/2)*rX + rX/2;

        // Calculate the point3D of the pixel center point
        Point3D pIJ = pCenter;

        // Avoid generating vector (0.0 ,0.0, 0.0) in case the center of the pixel is in the center of the View Plane
        if (xJ != 0)
            pIJ = pIJ.add(_vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(_vUp.scale(-yI));

        Vector vIJ = pIJ.subtract(_location);
        Ray rayThroughPixel = new Ray(getLocation(),vIJ);

        List<Ray> raysList = new LinkedList<Ray> ();
        raysList.add(rayThroughPixel);

        if(!_actDepthOfField){
            return raysList;
        }

        Point3D focalPoint = (Point3D) _focalPlane.findIntersections(rayThroughPixel);

        // Calculate the length and width of the pixel
        double focalPlaneWidth = screenWidth/nX*_aperture;
        double focalPlaneHeight = screenHeight/nY*_aperture;

        // Calculate the length and width of the pixel of focalPlane
        double FPX = focalPlaneWidth/_dimensionRays;
        double FPY = focalPlaneHeight/_dimensionRays;

        List<Ray> beam = new LinkedList<>();
        for (int m = 0; m < _dimensionRays; m++) {
            for (int n = 0; n < _dimensionRays; n++) {

                // Calculate the distance between the center of the pixel and the center point on the x axis and the y axis
                double FPyM = (m-(double)_dimensionRays/2)*FPY + _dimensionRays/2;
                double FPxN = (n-(double)_dimensionRays/2)*FPX + _dimensionRays/2;

                // Calculate the point3D of the pixel center point
                Point3D pMN = pCenter;

                // Avoid generating vector (0.0 ,0.0, 0.0) in case the center of the pixel is in the center of the View Plane
                if (FPxN != 0)
                    pMN = pMN.add(_vRight.scale(FPxN));
                if (FPyM != 0)
                    pMN = pMN.add(_vUp.scale(-FPyM));
            }}
        return null;
    }
}
