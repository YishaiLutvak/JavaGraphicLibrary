package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * PointLight class represents models omni-directional point source
 * such as a bulb
 * @author Michael Bergshtein and Yishay Lutvak
 */
public class PointLight extends Light implements LightSource {

    protected Point3D _position;

    // Factors (kc, kl, kq) for attenuation with distance (d)
    protected double _kC;
    protected double _kL;
    protected double _kQ;

    /**
     *
     * @param intensity
     * @param position
     * @param kC
     * @param kL
     * @param kQ
     */
    public PointLight(Color intensity,Point3D position,double kC, double kL,double kQ) {
        super(intensity);
        this._position = new Point3D(position);
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
    }

    /**
     *
     * @param intensity
     * @param position
     */
    // by default, the constant attenuation value is 1 and the other two values are 0
    public PointLight(Color intensity, Point3D position) {
        this(intensity, position, 1d, 0d, 0d);
    }

    @Override
    public Color getIntensity(Point3D p) {
        double dSquared = p.distanceSquared(_position);
        double d = p.distance(_position);
        return (_intensity.reduce(_kC + _kL * d + _kQ * dSquared));
    }

    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position))
            return null;
        return p.subtract(_position).normalized();
    }
}
