package primitives;

/**
 * Class for the material attributes
 */
public class Material {
    private final double _kD;
    private final double _kS;
    private final int _nShininess;
    private final double _kT;
    private final double _kR;

    public final static Material DEFAULT = new Material(0d,0d,0,0d,0d);

    /**
     * constructor
     * @param kD the diffusive factor
     * @param kS the specular factor
     * @param nShininess shininess factor. represents concentration of specular effect
     * @param kT the transparency factor
     * @param kR the reflection factor
     */
    public Material(double kD, double kS,int nShininess, double kT, double kR) {
        this._kD = kD;
        this._kS = kS;
        _nShininess = nShininess;
        this._kT = kT;
        this._kR = kR;
    }

    /**
     * constructor, by default _kT = 0 and _kR = 0
     * @param kD the diffusive factor
     * @param kS the specular factor
     * @param nShininess shininess factor. represents concentration of specular efect
     */
    public Material(double kD, double kS, int nShininess) {
       this(kD,kS,nShininess,0d,0d);
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
    /**
     * getter
     * @return kt parameter
     */
    public double getKt() {
        return _kT;
    }

    /**
     * getter
     * @return kr parameter
     */
    public double getKr() {
        return _kR;
    }
}