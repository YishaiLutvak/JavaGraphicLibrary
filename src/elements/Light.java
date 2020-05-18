package elements;

import primitives.Color;

/**
 *
 */
public abstract class Light {
    protected Color _intensity;

    /**
     *
     * @param _intensity
     */
    public Light(Color _intensity) {
        this._intensity = _intensity;
    }

    /**
     * getter
     * @return intensity
     */
    public Color getIntensity() {
        return _intensity;
    }
}
