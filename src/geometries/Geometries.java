package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * composite class which include a collection of any base and composite geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Geometries extends Intersectable {

    private List<Intersectable> _geometries;

    public Geometries() {
        this.box._max_X = Double.NEGATIVE_INFINITY;
        this.box._min_X = Double.POSITIVE_INFINITY;
        this.box._max_Y = Double.NEGATIVE_INFINITY;
        this.box._min_Y = Double.POSITIVE_INFINITY;
        this.box._max_Z = Double.NEGATIVE_INFINITY;
        this.box._min_Z = Double.POSITIVE_INFINITY;
        _geometries = new LinkedList<Intersectable>();
    }

    /**
     * Geometries constructor allowing to add zero or more geometries
     * while  creating it
     * @param geometries to add to the collection
     */
    public Geometries(Intersectable... geometries) {
        //Initial the max/min values in order that the first geometries
        //will give the right value
        this();
        add(geometries);
    }

    /**
     * The function add allows to add zero or more geometries to the
     * composite geometry
     * @param geometries to add to the collection
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            _geometries.add(geo);
            if (geo.box._max_X > this.box._max_X)
                this.box._max_X = geo.box._max_X;
            if (geo.box._min_X < this.box._min_X)
                this.box._min_X = geo.box._min_X;
            if (geo.box._max_Y > this.box._max_Y)
                this.box._max_Y = geo.box._max_Y;
            if (geo.box._min_Y < this.box._min_Y)
                this.box._min_Y = geo.box._min_Y;
            if (geo.box._max_Z > this.box._max_Z)
                this.box._max_Z = geo.box._max_Z;
            if (geo.box._min_Z < this.box._min_Z)
                this.box._min_Z = geo.box._min_Z;
        }
    }

    /**
     * find intersections of all the geometries parts
     * @param ray that intersects the geometries
     * @param max the maximum range from the source of the ray to the point
     * @return a list of intersect points
     */
    @Override
    protected List<GeoPoint> findIntersections(Ray ray, double max) {
        List<GeoPoint> intersections = null;
        for (Intersectable geo : _geometries) {
            List<GeoPoint> tempIntersections = geo.getFindIntersections(ray,max);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<GeoPoint>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
    public void createTree(int depthOfTree){
        Intersectable tempGeometries = new Geometries();

        for (int i = 0; i < _geometries.size(); i++){
            if      (_geometries.get(i).getBox()._max_X == Double.POSITIVE_INFINITY ||
                    _geometries.get(i).getBox()._max_Y == Double.POSITIVE_INFINITY ||
                    _geometries.get(i).getBox()._max_Z == Double.POSITIVE_INFINITY ||
                    _geometries.get(i).getBox()._min_X == Double.NEGATIVE_INFINITY ||
                    _geometries.get(i).getBox()._min_Y == Double.NEGATIVE_INFINITY ||
                    _geometries.get(i).getBox()._min_Z == Double.NEGATIVE_INFINITY){
                ((Geometries)tempGeometries).add(_geometries.get(i));
                _geometries.remove(i);
            }

        }
        createTreeRec(depthOfTree);
        for(Intersectable geo: ((Geometries)tempGeometries)._geometries)
            _geometries.add(geo);
    }
    public void createTreeRec(int depthOfTree) {

        Intersectable topRightCloseVoxel = null;
        Intersectable topLeftCloseVoxel = null;
        Intersectable downRightCloseVoxel = null;
        Intersectable downLeftCloseVoxel = null;
        Intersectable topRightFarVoxel = null;
        Intersectable topLeftFarVoxel = null;
        Intersectable downRightFarVoxel = null;
        Intersectable downLeftFarVoxel = null;

        for (int i = 0; i < _geometries.size(); i++) {
            if (_geometries.get(i).getMiddleZ() < this.getMiddleZ())
                if (_geometries.get(i).getMiddleY() < this.getMiddleY())
                    if (_geometries.get(i).getMiddleX() > this.getMiddleX()) {
                        if (topRightCloseVoxel == null)
                            topRightCloseVoxel = new Geometries();
                        ((Geometries) topRightCloseVoxel).add(_geometries.get(i));
                    } else {
                        if (topLeftCloseVoxel == null)
                            topLeftCloseVoxel = new Geometries();
                        ((Geometries) topLeftCloseVoxel).add(_geometries.get(i));
                    }
                else {
                    if (_geometries.get(i).getMiddleX() > this.getMiddleX()) {
                        if (downRightCloseVoxel == null)
                            downRightCloseVoxel = new Geometries();
                        ((Geometries) downRightCloseVoxel).add(_geometries.get(i));
                    } else {
                        if (downLeftCloseVoxel == null)
                            downLeftCloseVoxel = new Geometries();
                        ((Geometries) downLeftCloseVoxel).add(_geometries.get(i));
                    }
                }
            else {
                if (_geometries.get(i).getMiddleY() < this.getMiddleY())
                    if (_geometries.get(i).getMiddleX() > this.getMiddleX()) {
                        if (topRightFarVoxel == null)
                            topRightFarVoxel = new Geometries();
                        ((Geometries) topRightFarVoxel).add(_geometries.get(i));
                    } else {
                        if (topLeftFarVoxel == null)
                            topLeftFarVoxel = new Geometries();
                        ((Geometries) topLeftFarVoxel).add(_geometries.get(i));
                    }
                else {
                    if (_geometries.get(i).getMiddleX() > this.getMiddleX()) {
                        if (downRightFarVoxel == null)
                            downRightFarVoxel = new Geometries();
                        ((Geometries) downRightFarVoxel).add(_geometries.get(i));
                    } else {
                        if (downLeftFarVoxel == null)
                            downLeftFarVoxel = new Geometries();
                        ((Geometries) downLeftFarVoxel).add(_geometries.get(i));
                    }
                }
            }
        }

        _geometries.clear();
        if(topRightCloseVoxel != null) {
            if (((Geometries)topRightCloseVoxel)._geometries.size() == 1)
                _geometries.add(((Geometries)topRightCloseVoxel)._geometries.get(0));
            else


        }

    }
}
