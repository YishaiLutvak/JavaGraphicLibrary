package elements;

import primitives.Color;

/**
 *
 */
public abstract class Light {
    protected Color _intensity;

    /**
     *
     * @param intensity
     */
    public Light(Color intensity) {
        this._intensity = intensity;
    }

    /**
     * getter
     * @return intensity
     */
    public Color getIntensity() {
        return _intensity;
    }
}
