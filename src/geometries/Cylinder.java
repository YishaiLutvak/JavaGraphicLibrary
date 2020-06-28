package geometries;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import primitives.Material;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Cylinder class represents a cylinder in 3D Cartesian coordinate system
 * by radius, ray that start in the base, and height from the base
 *
 * @author Michael Bergshtein and Ishay Lutvak
 */
public class Cylinder extends Tube {
    protected double _height;

    /****************Constructors***************/

    private void buildBoundingBox(){

        if (this._axisRay.get_direction().get_head().get_x().get() == 0 &&
                this._axisRay.get_direction().get_head().get_z().get() ==0){
            if (this._axisRay.get_direction().get_head().get_y().get() > 0){
                this.box._min_Y = this._axisRay.get_start().get_y().get();
                this.box._max_Y = box._min_Y+_height;
            }
            else {
                this.box._max_Y = this._axisRay.get_start().get_y().get();
                this.box._min_Y = box._max_Y -_height;
            }
            this.box._max_X = this._axisRay.get_start().get_x().get() + _radius;
            this.box._min_X = this._axisRay.get_start().get_x().get() - _radius;

            this.box._max_Z = this._axisRay.get_start().get_z().get() + _radius;
            this.box._min_Z = this._axisRay.get_start().get_z().get() - _radius;
        }
        else {
            double xCenterBottom, xCenterTop;

            xCenterBottom = _axisRay.get_start().get_x().get();
            xCenterTop = _axisRay.getPoint(_height).get_x().get();
            Point3D normal = _axisRay.get_direction().get_head();
            double sqrX = _radius*Math.sqrt(1 - normal.get_x().get()*normal.get_x().get());

            double max_x_bottom = xCenterBottom + sqrX;
            double min_x_bottom = xCenterBottom -sqrX;
            double max_x_top = xCenterTop + sqrX;
            double min_x_top = xCenterTop - sqrX;

            this.box._max_X = max_x_bottom > max_x_top? max_x_bottom: max_x_top;
            this.box._min_X = min_x_bottom < min_x_top? min_x_bottom: min_x_top;

            double yCenterBottom, yCenterTop;
            yCenterBottom = _axisRay.get_start().get_y().get();
            yCenterTop = _axisRay.getPoint(_height).get_y().get();
            double sqrY = _radius*Math.sqrt(1 - normal.get_y().get()*normal.get_y().get());

            double max_y_bottom = yCenterBottom + sqrY;
            double min_y_bottom = yCenterBottom - sqrY;
            double max_y_top = yCenterTop + sqrY;
            double min_y_top = yCenterTop - sqrY;

            this.box._max_Y = max_y_bottom > max_y_top? max_y_bottom: max_y_top;
            this.box._min_Y = min_y_bottom < min_y_top? min_y_bottom: min_y_top;

            double zCenterBottom, zCenterTop;
            zCenterBottom = _axisRay.get_start().get_z().get();
            zCenterTop = _axisRay.getPoint(_height).get_z().get();
            double sqrZ = _radius*Math.sqrt(1 - normal.get_z().get()*normal.get_z().get());

            double max_z_bottom = zCenterBottom + sqrZ;
            double min_z_bottom = zCenterBottom - sqrZ;
            double max_z_top = zCenterTop + sqrZ;
            double min_z_top = zCenterTop - sqrZ;

            this.box._max_Z = max_z_bottom > max_z_top? max_z_bottom: max_z_top;
            this.box._min_Z = min_z_bottom < min_z_top? min_z_bottom: min_z_top;
        }
    }
    /**
     * constructor with all parameters
     *
     * @param emissionLight for color of tube
     * @param material for material attributes of tube
     * @param radius for radius of Tube
     * @param ray    for center of tube
     * @param height for height of tube
     */
    public Cylinder(Color emissionLight, Material material, double radius, Ray ray, double height) {
        super(emissionLight, material, radius, ray);
        this._height = height;

        buildBoundingBox();
    }

    /**
     * constructor. material get default value
     *
     * @param emissionLight for color of tube
     * @param radius for radius of Tube
     * @param ray    for center of tube
     * @param height for height of tube
     */
    public Cylinder(Color emissionLight, double radius, Ray ray, double height) {
       this(emissionLight, Material.DEFAULT, radius, ray, height);
    }

    /**
     * constactor by two doubles and Ray values
     *
     * @param radius for radius of Tube
     * @param ray    for center of tube
     * @param height for height of tube
     */
    public Cylinder(double radius, Ray ray, double height) {
       this(Color.BLACK,Material.DEFAULT,radius, ray, height);
    }

    /************getters************/
    /**
     * @return height
     */
    public double getHeight() {
        return _height;
    }


    /****************methods*****************/

    /**
     * getNormal function
     * @param p point on the cylinder. The normal is from point p
     * @return a normal in the point
     */
    @Override
    public Vector getNormal(Point3D p) {

        //The point is on the base that contains the start point
        //Checks whether the point is on the center of the base or not
        if (p.equals(_axisRay.get_start()) ||
                isZero(p.subtract(_axisRay.get_start()).dotProduct(_axisRay.get_direction())))
            return _axisRay.get_direction().scale(-1);

        //The point is on the base that not contains the start point
        //Checks whether the point is on the center of the base or not
        /*Point3D centerTop = new Point3D(_axisRay.get_start().add(_axisRay.get_direction().scale(_height)));*/
        Point3D centerTop = _axisRay.getPoint(_height);
        if (p.equals(centerTop) ||
                isZero(p.subtract(centerTop).dotProduct(_axisRay.get_direction())))
            return _axisRay.get_direction();

        //The point is not on the Cylinder bases
        else
            return super.getNormal(p);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", height= " + _height;
    }

    /**
     * Function for finding intersections points with an cylinder
     * @param ray The ray that we check if it intersects the cylinder.
     * @param max the maximum range from the source of the ray to the point
     * @return a list of intersection points, if any.
     */
    @Override
    protected List<GeoPoint> findIntersections(Ray ray, double max) {
        Plane planeBottom = new Plane(_axisRay.get_start(),_axisRay.get_direction());
        Plane planeTop = new Plane(_axisRay.getPoint(_height),_axisRay.get_direction());
        List<GeoPoint> intersections = null;

        //Uses a Plane class to find intersection points on the bottom base
        List<GeoPoint> tempIntersections1 = planeBottom.findIntersections(ray, max);
        if (tempIntersections1 != null) {
            if (alignZero(_radius - _axisRay.get_start().distance(tempIntersections1.get(0)._point)) > 0) {
                intersections = new LinkedList<GeoPoint>();
                intersections.add(tempIntersections1.get(0));
            }
        }

        //Uses a Plane class to find intersection points on the top base
        List<GeoPoint> tempIntersections2 = planeTop.findIntersections(ray, max);
        if (tempIntersections2 != null){
            if (alignZero(_radius - _axisRay.getPoint(_height).distance(tempIntersections2.get(0)._point)) > 0) {
                if (intersections == null)
                    intersections = new LinkedList<GeoPoint>();
                intersections.add(tempIntersections2.get(0));
            }
        }

        //Uses the parent class to find intersection points on the rest of the cylinder
        List<GeoPoint> tempIntersections3 = super.findIntersections(ray, max);
        if (tempIntersections3 != null) {
            double maxLenSquare = _height*_height + _radius*_radius;
            for (GeoPoint geoPoint : tempIntersections3) {
                if (alignZero(maxLenSquare - geoPoint._point.distanceSquared(_axisRay.get_start())) > 0 &&
                        alignZero(maxLenSquare - geoPoint._point.distanceSquared(_axisRay.getPoint(_height))) > 0 ){
                    if (intersections == null)
                        intersections = new LinkedList<GeoPoint>();
                    intersections.add(geoPoint);
                }
            }
        }

        if (intersections!=null){
            for (GeoPoint geoPoint : intersections) {
                geoPoint._geometry = this;
            }
        }

        return intersections;
    }
}
