package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * PointLight class represents models omni-directional point source such as a bulb.
 * The attenuation by distance is represented by 3 parameters: kC,kL,kQ for constant,
 * linear and squared.
 * @author Michael Bergshtein and Yishay Lutvak
 */
public class PointLight extends Light implements LightSource {

    protected Point3D _position;

    // Factors (kc, kl, kq) for attenuation with distance (d)
    protected double _kC;
    protected double _kL;
    protected double _kQ;

    /**
     * constructor
     * @param intensity the intensity of color of the PointLight
     * @param position the position of the point light
     * Factors (kc, kl, kq) for attenuation with distance (d)
     * @param kC constant
     * @param kL linear
     * @param kQ square
     */
    public PointLight(Color intensity,Point3D position,double kC, double kL,double kQ) {
        super(intensity);
        this._position = new Point3D(position);
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
    }

    /**
     * constructor
     * by default, the constant attenuation value is 1 and the other two values are 0
     * @param intensity the intensity of color of the PointLight
     * @param position the position of the point light
     */
    public PointLight(Color intensity, Point3D position) {
        this(intensity, position, 1d, 0d, 0d);
    }

    /**
     * getIntensity function
     * calculates the intensity of color of point on the geometry
     * @param p the point3D on the geometry
     * @return intensity of color af point p
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dSquared = p.distanceSquared(_position);
        double d = p.distance(_position);
        return (_intensity.reduce(_kC + _kL * d + _kQ * dSquared));
    }

    /**
     * getL function calculates the direction
     * of the light ray from the light source to the point
     * @param p the point3D on the geometry
     * @return The direction of the light rays that hit the point
     */
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position))
            return null;
        return p.subtract(_position).normalized();
    }

    /**
     * getDistance function
     * @param p 3D point
     * @return distance between the point and the light source
     */
    @Override
    public double getDistance(Point3D p) {
        return p.distance(_position);
    }
}
