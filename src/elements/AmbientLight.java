package elements;

import primitives.Color;

public class AmbientLight {
    Color _iA;
    double _kA;

    public AmbientLight(Color _iA, double _kA) {
        _iA = _iA.scale(_kA);

    }

    public AmbientLight(Color _iA) {
        this(_iA,1);

    }

    public Color getIntensity(){
        return _iA;
    }
}
