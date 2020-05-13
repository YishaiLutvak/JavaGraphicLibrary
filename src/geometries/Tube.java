package geometries;

import primitives.*;
import java.util.List;
import static primitives.Util.isZero;

/**
 * Class that represent infinite cylinder in 3D Cartesian coordinate system
 * by radius ang ray
 * @author Michael Bergshtein and Yishay Lutvak
 */
public class Tube extends RadialGeometry {
    /**
     * The ray is located in the center of Tube
     */
    protected Ray _axisRay;

    /*********************constractors*********************/
    /**
     * constractor by double and Ray values
     * @param radius for the length of the radius
     * @param ray for the center of the tube
     */
    public Tube(double radius, Ray ray) {
        super(radius);
        this._axisRay = ray;
    }

    /******************getters*******************/

    /**
     * @return axisRay
     */
    public Ray getRay() {
        return _axisRay;
    }

    /******************methods******************/
    /**
     * getNormal function
     * @param p point on the sphere. The normal is from p
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point3D p){
        //Calculate the normal of the tube from point p
        //O is projection of P on cylinder's ray
        Point3D O;
        //According to the formula: t = v ∙ (P − P0)
        double t = _axisRay.get_direction().dotProduct(p.subtract(_axisRay.get_start()));
        //Make sure you don't multiply in Scalar Zero
        if (!isZero(t))
            //According to the formula: O = P0 + t ∙ v
            O = new Point3D(_axisRay.get_start().add(_axisRay.get_direction().scale(t)));
        else
            O = _axisRay.get_start();
        //Calculate and return the normal vector
        return p.subtract(O).normalized();
    }

    @Override
    public String toString() {
        return super.toString()+
                ", ray= " + _axisRay;
    }


    /**
     * Function for finding intersections points with an infinite
     * tube.
     * @param ray The ray that we check if it intersects the tube.
     * @return A list of intersection points, if any.
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {

        Point3D p0 = ray.get_start();
        Vector v = ray.get_direction();

        Point3D p0Tube =_axisRay.get_start();
        Vector vTube = _axisRay.get_direction();

        //if v and vTube are parallel vectors there is no intersection
        try { v.crossProduct(vTube); }
        catch (IllegalArgumentException ex) { return null;}

        if (p0.equals(p0Tube))
            p0Tube = _axisRay.getPoint(-1);
        Vector p0TubeP0 = new Vector(p0.subtract(p0Tube));

        double v_dot_vTube = v.dotProduct(vTube);
        double p0TubeP0_dot_vTube = p0TubeP0.dotProduct(vTube);

        double A;
        double B;
        double C;

        Vector v_subtract_vTube_scale_v_dot_vTube;
        Vector p0TubeP0_subtract_vTube_scale_p0TubeP0_dot_vTube = null;

        if (isZero(v_dot_vTube))
            v_subtract_vTube_scale_v_dot_vTube = v;
        else
            v_subtract_vTube_scale_v_dot_vTube = v.subtract(vTube.scale(v_dot_vTube));
        if (isZero(p0TubeP0_dot_vTube))
            p0TubeP0_subtract_vTube_scale_p0TubeP0_dot_vTube = p0TubeP0;
        else if (!p0TubeP0.equals(vTube.scale(p0TubeP0_dot_vTube)))
            p0TubeP0_subtract_vTube_scale_p0TubeP0_dot_vTube = p0TubeP0.subtract(vTube.scale(p0TubeP0_dot_vTube));

        if (isZero(p0TubeP0_dot_vTube) || !p0TubeP0.equals(vTube.scale(p0TubeP0_dot_vTube))) {
            B = 2 * v_subtract_vTube_scale_v_dot_vTube.dotProduct(p0TubeP0_subtract_vTube_scale_p0TubeP0_dot_vTube);
            C = p0TubeP0_subtract_vTube_scale_p0TubeP0_dot_vTube.dotProduct(p0TubeP0_subtract_vTube_scale_p0TubeP0_dot_vTube)
                     -_radius * _radius;
        }
        else {
            B = 0;
            C = -_radius * _radius;
        }
        A = v_subtract_vTube_scale_v_dot_vTube.dotProduct(v_subtract_vTube_scale_v_dot_vTube);

        double desc = B*B - 4*A*C;

        //No solution
        if (desc < 0) return null;

        //One solution
        if (isZero(desc)) return null;

        //Two solution
        double t1 = (-B+Math.sqrt(desc))/(2*A);
        double t2 = (-B-Math.sqrt(desc))/(2*A);

        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
        else return List.of(new GeoPoint(this,ray.getPoint(t1)));
    }
}