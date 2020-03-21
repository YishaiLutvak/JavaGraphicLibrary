package geometries;

/**
 * abstract class to represent all geometry shapes
 * that have a radius like circle cylinder and tube
 */
public abstract class RadialGeometry implements Geometry {
    double _radius;

    /************constractors**********************/
    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }

    public RadialGeometry(RadialGeometry _rg) {
        this._radius = _rg._radius;
    }

    /************admin**************/
    @Override
    public String toString() {
        return "radius=" + _radius;
    }
}

