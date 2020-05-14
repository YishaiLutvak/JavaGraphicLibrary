package elements;

/**
 *
 */
public class Material {
    private final double _kD;
    private final double _kS;
    private final int _nShinines;

    public Material(double kD, double kS, int nShinines) {
        _kD = kD;
        _kS = kS;
        _nShinines = nShinines;
    }

    public double getKD() {
        return _kD;
    }

    public double getKS() {
        return _kS;
    }

    public int getNShinines() {
        return _nShinines;
    }
}