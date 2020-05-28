package geometries;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import primitives.Material;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Cilinder class represents a cylinder in 3D Cartesian coordinate system
 * by radius, ray that start in the base, and height from the base
 *
 * @author Michael Bergshtein and Ishay Lutvak
 */
public class Cylinder extends Tube {
    protected double _height;

    /****************Constructors***************/
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
     * getNormal functiom
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

    @Override
    public List<GeoPoint> findIntersections(Ray ray, double max) {
        Plane planeBottom = new Plane(_axisRay.get_start(),_axisRay.get_direction());
        Plane planeTop = new Plane(_axisRay.getPoint(_height),_axisRay.get_direction());
        List<GeoPoint> intersections = null;
        List<GeoPoint> tempIntersections1 = planeBottom.findIntersections(ray, max);
        if (tempIntersections1 != null) {
            if (alignZero(_radius - _axisRay.get_start().distance(tempIntersections1.get(0)._point)) > 0) {
                intersections = new LinkedList<GeoPoint>();
                intersections.add(tempIntersections1.get(0));
            }
        }
        List<GeoPoint> tempIntersections2 = planeTop.findIntersections(ray, max);
        if (tempIntersections2 != null){
            if (alignZero(_radius - _axisRay.getPoint(_height).distance(tempIntersections2.get(0)._point)) > 0) {
                if (intersections == null)
                    intersections = new LinkedList<GeoPoint>();
                intersections.add(tempIntersections2.get(0));
            }
        }
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
