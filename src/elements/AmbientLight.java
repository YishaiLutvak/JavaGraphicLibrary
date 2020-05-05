package elements;

import primitives.Color;

/**
 *
 */
public class AmbientLight {
    Color _intensity;
    double _kA;

    /**
     *
     * @param _iA
     * @param _kA
     */
    public AmbientLight(Color _iA, double _kA) {
        this._intensity = _iA.scale(_kA);
    }

    /**
     *
     * @param _iA
     */
    public AmbientLight(Color _iA) {
        this(_iA,1);
    }

    /**
     *
     * @return
     */
    public Color getIntensity(){
        return _intensity;
    }
}
