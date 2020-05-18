package elements;

import primitives.Color;

/**
 *
 */
public class Material {
    private final double _kD;
    private final double _kS;
    private final int _nShininess;

    public final static Material DEFAULT = new Material(0d,0d,0);

    /**
     *
     * @param kD
     * @param kS
     * @param nShininess
     */
    public Material(double kD, double kS, int nShininess) {
        _kD = kD;
        _kS = kS;
        _nShininess = nShininess;
    }

    /**
     *
     * @return
     */
    public double getKd() {
        return _kD;
    }

    /**
     *
     * @return
     */
    public double getKs() {
        return _kS;
    }

    /**
     *
     * @return
     */
    public int getShininess() {
        return _nShininess;
    }
}