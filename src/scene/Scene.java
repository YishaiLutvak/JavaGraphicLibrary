package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Plane;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Contain all the elements of the scene
 */
public class Scene {
    /**
     *
     * _name - The name of the scene
     * _background - The background color of the scene
     * _ambientLight - The ambient light color of the scene
     * _cameraThe
     * _distance
     * _geometries
     * _lights
     * _actDepthOfField
     */
    private final  String _name;
    private Color _background;
    private AmbientLight _ambientLight;
    private Camera _camera;
    private double _distance;
    private Geometries _geometries;
    private List<LightSource> _lights;


    /**
     * constructor
     * @param name name of the scene
     */
    public Scene(String name) {
        this._name = name;
        this._geometries = new Geometries();
        this._lights = new LinkedList<LightSource>();
    }


    // ***************** Getters ********************** //

    /**
     * getter of name
     * @return the name of the scene
     */
    public String get_name() {
        return _name;
    }

    /**
     * getter of background
     * @return the background color
     */
    public Color getBackground() {
        return _background;
    }
    /**
     * getter of AmbientLight
     * @return the AmbientLight color
     */
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * getter of camera
     * @return the camera
     */
    public Camera getCamera() {
        return _camera;
    }

    /**
     * getter of the distance
     * @return _distance
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * getter of geometries
     * @return _geometries
     */
    public Geometries getGeometries() {
        return _geometries;
    }

    /**
     *
     * @return lights
     */
    public List<LightSource> getLights() {
        return _lights;
    }


    // ***************** Setters ********************** //

    /**
     * setter of background
     * @param _background background color
     */
    public void setBackground(Color _background) {
        this._background = _background;
    }

    /**
     * setter of ambient light
     * @param _ambientLight the ambient light color and intensity
     */
    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     *  setter of the scene camera
     * @param _camera the camera of the scene
     */
    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    /**
     * setter of distance
     * @param _distance the distance bdtween the camera and the view plane
     */
    public void setDistance(double _distance) {
        this._distance = _distance;
    }


    /**
     * Adding a new geometry to the geometry list
     * @param geometries one or more new geometries
     */
    public void addGeometries(Intersectable... geometries){
        _geometries.add(geometries);
    }

    /**
     * Adding a new LightSource to the lights list
     * @param lights a list of LightSource
     */
    public void addLights(LightSource... lights) {
        for ( LightSource lightSource : lights) {
            _lights.add(lightSource);
        }
    }
}
