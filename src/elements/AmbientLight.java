package elements;

import primitives.Color;

/**
 * represent the Ambient light in the scene
 */
public class AmbientLight {
    Color _intensity;
    double _kA;

    /**
     *constructor
     * @param _iA the color of the image
     * @param _kA intencity factor
     */
    public AmbientLight(Color _iA, double _kA) {
        this._intensity = _iA.scale(_kA);
    }

    /**
     *constructor
     * @param _iA the color of the image
     */
    public AmbientLight(Color _iA) {
        this(_iA,1);
    }

    /**
     * getter
     * @return intensity
     */
    public Color getIntensity(){
        return _intensity;
    }
}
