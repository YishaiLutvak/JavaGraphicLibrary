package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import primitives.Color;

public class Scene {
    private final  String _name;
    private Color _background;
    private AmbientLight _ambientLight;
    private Camera _camera;
    private double _distance;
    private Geometries _geometries;
    public Scene(String name) {
        _name = name;
    }

    public void addGeometries(Geometries... geometries){
        _geometries = new Geometries();

    }
}
