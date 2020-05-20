package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Interface for common actions of light sources
 * @author Michael Bergshtein and Yishai Lutvak
 */
public interface LightSource {
    /**
     * getIntensity function
     * calculates the color of point on the geometry
     * @param p the point3D on the geometry
     * @return color af point p
     */
    public Color getIntensity(Point3D p);

    /**
     * getL function calculates the direction
     * of the light rays from the light source to the point
     * @param p the point3D on the geometry
     * @return The direction of the light rays that hit the point
     */
    public Vector getL(Point3D p);

}
