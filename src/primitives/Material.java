package primitives;

/**
 * Class for the material attributes
 */
public class Material {
    private final double _kD;
    private final double _kS;
    private final double _kT;
    private final double _kR;
    private final int _nShininess;

    public final static Material DEFAULT = new Material(0d,0d,0d,0d,0);

    /**
     * constructor
     * @param kD the diffusive factor
     * @param kS the specular factor
     * @param kT the transparency factor
     * @param kR the reflection factor
     * @param nShininess shininess factor. represents concentration of specular effect
     */
    public Material(double kD, double kS, double kT, double kR, int nShininess) {
        this._kD = kD;
        this._kS = kS;
        this._kT = kT;
        this._kR = kR;

        _nShininess = nShininess;
    }

    /**
     * constructor, by default _kT = 0 and _kR = 0
     * @param kD the diffusive factor
     * @param kS the specular factor
     * @param nShininess shininess factor. represents concentration of specular efect
     */
    public Material(double kD, double kS, int nShininess) {
       this(kD,kS,0d,0d,nShininess);
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
     * @return kt parameter
     */
    public double get_kT() {
        return _kT;
    }

    /**
     * getter
     * @return kr parameter
     */
    public double get_kR() {
        return _kR;
    }

    /**
     * getter
     * @return nShininess parameter
     */
    public int getShininess() {
        return _nShininess;
    }
}