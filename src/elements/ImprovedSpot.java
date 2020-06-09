package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * PointLight class represents models point light source with direction
 * such as a luxo lamp. The attenuation by distance is represented by 3 parameters:
 *  kC,kL,kQ for constant, linear and squared. The spot light of the ImprovedSpot
 *  is thinner than the regular SpotLight. We get the effect by power the direction
 *  value by focus parameter.
 *  @author Michael Bergshtein and Yishay Lutvak
 */
public class ImprovedSpot extends SpotLight {
    private int _focus;

    /**
     * constructor
     * @param intensity the intensity of color of the PointLight
     * @param position the position of the point light
     * @param direction the direction of light of spot
     * Factors (kc, kl, kq) for attenuation with distance (d)
     * @param kC constant
     * @param kL linear
     * @param kQ square
     * @param focus the power parameter
     */
    public ImprovedSpot(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ, int focus) {
        super(intensity, position, direction, kC, kL, kQ);
        this._focus = focus;
    }

    /**
     * constructor with default parameters
     *  by default, the constant attenuation value is 1 and the other two values are 0
     * @param intensity the intensity of color of the PointLight
     * @param position the position of the point light
     * @param direction the direction of light of spot
     * @param focus the power parameter
     */
    public ImprovedSpot(Color intensity, Point3D position, Vector direction, int focus) {
        this(intensity, position, direction, 1d, 0d, 0d, focus);
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
        if (p.equals(_position))
            return null;
        Vector l = p.subtract(_position).normalized();
        return (_intensity.scale(Math.max(0,Math.pow(_dir.dotProduct(l),_focus))).reduce(_kC + _kL * d + _kQ * dSquared));
    }
}
