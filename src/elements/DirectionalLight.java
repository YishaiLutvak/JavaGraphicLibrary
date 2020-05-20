package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * DirectionalLight class represent directional light source (like sun)
 * @author Michael Bergshtein and Yishay Lutvak
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector _direction;

    /**
     * Constructor
     * @param intensity the color of light source
     * @param direction the direction of the light rays
     */
    public DirectionalLight(Color intensity ,Vector direction) {
        super(intensity);
        this._direction = direction.normalized();
    }

    /**
     * getIntensity function in directional light
     * returns color of _intensity of light source
     * because there is no attenuation with the distance
     * @param p the point3D on the geometry
     * @return color af point p
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    /**
     * getL function in directional light
     * returns direction of _direction of directional light
     * because light source is far away (at infinity - like Sun)
     * @param p the point3D on the geometry
     * @return The direction of the light rays that hit the point
     */
    @Override
    public Vector getL(Point3D p) {
        return new Vector(_direction);
    }
}
