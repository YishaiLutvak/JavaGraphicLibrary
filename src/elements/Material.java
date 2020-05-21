package elements;

import primitives.Color;

/**
 * Class for the material attributes
 */
public class Material {
    private final double _kD;
    private final double _kS;
    private final int _nShininess;

    public final static Material DEFAULT = new Material(0d,0d,0);

    /**
     *constructor
     * @param kD the diffusive factor
     * @param kS the specular factor
     * @param nShininess shininess factor. represents concentration of specular efect
     */
    public Material(double kD, double kS, int nShininess) {
        _kD = kD;
        _kS = kS;
        _nShininess = nShininess;
    }

    /********************getters******************
    /**
     * getter
     * @return kd parameter
     */
    public double getKd() {
        return _kD;
    }

    /**
     * getter
     * @return ks parameter
     */
    public double getKs() {
        return _kS;
    }

    /**
     * getter
     * @return nShininess parameter
     */
    public int getShininess() {
        return _nShininess;
    }
}