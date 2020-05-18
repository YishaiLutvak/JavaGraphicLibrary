package elements;

import primitives.Color;
import primitives.Vector;

public class SpotLight extends PointLight {
    private Vector _direction;
    /**
     * @param intensity
     */
    public SpotLight(Color intensity) {
        super(intensity);
    }
}
