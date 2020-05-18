package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    /**
     * @param intensity
     */
    public PointLight(Color intensity) {
        super(intensity);
    }

    @Override
    public Color getIntensity(Point3D p) {
        return null;
    }

    @Override
    public Vector getL(Point3D p) {
        return null;
    }
}
