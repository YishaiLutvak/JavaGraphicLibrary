package geometries;

public abstract class RadialGeometry implements Geometry {
    double _radius;

    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }

    public RadialGeometry(RadialGeometry _rg) {
        this._radius = _rg._radius;
    }

    @Override
    public String toString() {
        return "radius=" + _radius;
    }
}

