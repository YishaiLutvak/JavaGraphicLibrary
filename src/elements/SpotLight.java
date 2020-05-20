package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {
    protected Vector _dir;

    /**
     *
     * @param intensity
     * @param position
     * @param kC
     * @param kL
     * @param kQ
     * @param direction
     */
    public SpotLight(Color intensity, Point3D position,Vector direction, double kC, double kL, double kQ)
    {
        super(intensity, position, kC, kL, kQ);
        this._dir = direction.normalized();
    }

    /**
     * by default, the constant attenuation value is 1 and the other two values are 0
     * @param intensity
     * @param position
     * @param direction
     */
    public SpotLight(Color intensity, Point3D position, Vector direction)
    {
        this(intensity, position, direction, 1d, 0d, 0d );
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dSquared = p.distanceSquared(_position);
        double d = p.distance(_position);
        Vector l;
        try {
            l = p.subtract(_position).normalized();
        } catch (IllegalArgumentException ex) {
            l = new Vector(_dir);
        }
        return (_intensity.scale(Math.max(0,_dir.dotProduct(l))).reduce(_kC + _kL * d + _kQ * dSquared));
    }

    @Override
    public Vector getL(Point3D p) {
        return new Vector(_dir);
    }
}
