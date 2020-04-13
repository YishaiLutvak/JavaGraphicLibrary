package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable> _geometries;

    public Geometries() {
        _geometries = new ArrayList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        this._geometries = List.of(geometries);
    }

    public void add(Intersectable... geometries) {
        for (int i = 1; i < geometries.length; ++i)
            this._geometries.add(geometries[i]);
    }
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
