package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class ImprovedSpot extends SpotLight {
    private int _focus;

    /**
     *
     * @param intensity
     * @param position
     * @param direction
     * @param kC
     * @param kL
     * @param kQ
     * @param focus
     */
    public ImprovedSpot(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ, int focus) {
        super(intensity, position, direction, kC, kL, kQ);
        this._focus = focus;
    }

    /**
     *
     * @param intensity
     * @param position
     * @param direction
     * @param focus
     */
    public ImprovedSpot(Color intensity, Point3D position, Vector direction, int focus) {
        this(intensity, position, direction, 1d, 0d, 0d, focus);
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
        return (_intensity.scale(Math.max(0,Math.pow(_dir.dotProduct(l),_focus))).reduce(_kC + _kL * d + _kQ * dSquared));
    }
}
