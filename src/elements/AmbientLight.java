package elements;

import primitives.Color;

/**
 * represent the Ambient light in the scene
 */
public class AmbientLight extends Light {
    //double _kA;

    /**
     * constructor
     * @param _iA the color of the image
     * @param _kA intensity factor
     */
    public AmbientLight(Color _iA, double _kA) {
        super(_iA.scale(_kA)) ;
    }

    /**
     * constructor
     * @param _iA the color of the image
     */
    public AmbientLight(Color _iA) {
        this(_iA,1);
    }

}
