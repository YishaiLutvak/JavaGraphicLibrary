package scene;


import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable;
import org.w3c.dom.Node;
import primitives.Color;

import java.util.ArrayList;
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
     * _camera - The camera that photo the scene
     * _distance - The distance between the camera and the viewPlane
     * _geometries - a struct of the geometries in the scene
     * _lights - a list of all the lights in the scene
     * _actDepthOfField - boolean field activate the depth of field feature
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
     * getter of lights
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

    public class Voxel{
        double max_x;
        double max_y;
        double max_z;
        double min_x;
        double min_y;
        double min_z;

        public Voxel(double max_x, double max_y, double max_z, double min_x, double min_y, double min_z) {
            this.max_x = max_x;
            this.max_y = max_y;
            this.max_z = max_z;
            this.min_x = min_x;
            this.min_y = min_y;
            this.min_z = min_z;
        }

        public Voxel(Voxel v) {
            this.max_x = v.max_x;
            this.max_y = v.max_y;
            this.max_z = v.max_z;
            this.min_x = v.min_x;
            this.min_y = v.min_y;
            this.min_z = v.min_z;


        }
    }

    public class Tree {
        private Node root;

        public Tree(Voxel voxel) {
            root = new Node(voxel,null);
        }

        public void addGeometry(Geometry geometry){
            root.addGeometry(geometry);
        }

        public class Node {
            private Voxel voxel;
            private Geometries geometries;
            private Node parent;
            private List<Node> children;

            public Node(Voxel voxel,Node parent) {
                this.voxel = voxel;
                this.geometries = null;
                this.parent = parent;
                this.children = null;
            }

            public void addGeometry(Geometry geometry){
                if (this.children == null) {
                    if (this.geometries == null) {
                        this.geometries = new Geometries(geometry);
                    }
                    else{
                       this.children = new ArrayList<Node>(8);

                       double new_max_x = (voxel.max_x - voxel.min_x)/2;
                       double new_min_x = voxel.min_x;
                        double new_max_y = (voxel.max_y - voxel.min_y)/2;
                        double new_min_y = voxel.min_y;
                        double new_max_z = (voxel.max_z - voxel.min_z)/2;
                        double new_min_z = voxel.min_z;
                       this.children.set(0,new Node(new Voxel(new_max_x,new_min_x,new_max_y,new_min_y,new_max_z,new_min_z),this));

                        new_max_x = (voxel.max_x - voxel.min_x)/2;
                        new_min_x = voxel.min_x;
                        new_max_y = (voxel.max_y - voxel.min_y)/2;
                        double new_min_y = voxel.min_y;
                        double new_max_z = (voxel.max_z - voxel.min_z)/2;
                        double new_min_z = voxel.min_z;
                        this.children.set(1,new Node(new Voxel(new_max_x,new_min_x,new_max_y,new_min_y,new_max_z,new_min_z),this));
                    }
                }
                else{
                    for (int i = 0; i < 8; i++){
                        if (    geometry.getBox()._max_X <= children[i].voxel.max_x&&
                                geometry.getBox()._max_Y <= children[i].voxel.max_y&&
                                geometry.getBox()._max_Z <= children[i].max_z&&
                                geometry.getBox()._min_X >= children[i].min_x&&
                                geometry.getBox()._min_Y >= children[i].min_y&&
                                geometry.getBox()._min_Z >= children[i].min_z){
                            children[i].addGeometry(geometry);
                        }
                    }
                }
            }
        }
    }
    /**
     *
     */
    public void buildBoundingTree(int treeDepth){


    }
}
