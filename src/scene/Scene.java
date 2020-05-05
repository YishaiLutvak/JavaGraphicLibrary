package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import primitives.Color;

public class Scene {
    private final  String _name;
    private final Color _background;
    private final AmbientLight _ambientLight;
    private final Camera _camera;
    private final double _distance;
    private Geometries _geometries;

    /**
     *
     * @param name
     * @param background
     * @param ambientLight
     * @param camera
     * @param distance
     */
    public Scene(String name, Color background, AmbientLight ambientLight, Camera camera, double distance) {
        this._name = name;
        this._background = background;
        this._ambientLight = ambientLight;
        this._camera = camera;
        this._distance = distance;
        this._geometries = new Geometries();
    }

    public void addGeometries(Geometries... geometries){
        _geometries = new Geometries();

    }

    public Color getBackground() {
        return _background;
    }

    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Camera getCamera() {
        return _camera;
    }

    public double getDistance() {
        return _distance;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public static class SceneBuilder{
        private String _name;
        private Color _background;
        private AmbientLight _ambientLight;
        private Camera _camera;
        private double _distance;
        private Geometries _geometries;

        public SceneBuilder (String name){
            this._name = name;
        }

        public SceneBuilder setBackground(Color background) {
            this._background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this._ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setCamera(Camera camera) {
            this._camera = camera;
            return this;
        }

        public SceneBuilder setDistance(double distance) {
            this._distance = distance;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this._geometries = geometries;
            return this;
        }

        public Scene build(){
            Scene scene = new Scene(_name,_background,_ambientLight,_camera,_distance);
            validateSceneObject(scene);
            return scene;
        }
        private void validateSceneObject(Scene scene){
            //Do some basic validations to check
            //if scene object does not break any assumption of system
        }
    }
}
