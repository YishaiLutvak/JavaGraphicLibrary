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

    /**
     * default constructor
     */
    public Geometries() {
        //Initial the max/min values in order that the first geometries
        //will give the right value
        this.ResetBox();
        _geometries = new LinkedList<Intersectable>();
    }

    /**
     * Geometries constructor allowing to add zero or more geometries
     * while  creating it
     * @param geometries to add to the collection
     */
    public Geometries(Intersectable... geometries) {
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

    /**
     * main function for bounding tree building .
     * Act the recursion depthOf TreeRec.
     * The infinity geometries is not include in the recursion and go
     * by the end to the root of the tree
     * @param depthOfTree the depth of recursion
     */
        public void createTree(int depthOfTree){

        Intersectable infinityGeometries = new Geometries();
        Intersectable finiteGeometries = new Geometries();

        //Remove all the infinity geometries before sending for recursion
        for(Intersectable geo: this._geometries){
            if (geo.getBox()._max_X == Double.POSITIVE_INFINITY ||
                    geo.getBox()._max_Y == Double.POSITIVE_INFINITY ||
                    geo.getBox()._max_Z == Double.POSITIVE_INFINITY ||
                    geo.getBox()._min_X == Double.NEGATIVE_INFINITY ||
                    geo.getBox()._min_Y == Double.NEGATIVE_INFINITY ||
                    geo.getBox()._min_Z == Double.NEGATIVE_INFINITY)
                ((Geometries)infinityGeometries).add(geo);
            else
                ((Geometries)finiteGeometries).add(geo);
        }

        this._geometries.clear();
        this.ResetBox();

        for(Intersectable geo: ((Geometries)finiteGeometries)._geometries)
            this.add(geo);

        this.createTreeRec(depthOfTree);

        for(Intersectable geo: ((Geometries)infinityGeometries)._geometries)
            this.add(geo);
    }

    /**
     * Recursive function for bounding tree building. Build an octree of voxels
     * and send to the next stage of recursion any voxel contains more than
     * ong geometries.
     * The idea of octree hierarchy was taken from:
     * https://www.scratchapixel.com/lessons/advanced-rendering/introduction-acceleration-structure/bounding-volume-hierarchy-BVH-part2
     * @param depthOfTree the depth of recursion
     */
    public void createTreeRec(int depthOfTree) {

        Intersectable topRightCloseVoxel = null;
        Intersectable topLeftCloseVoxel = null;
        Intersectable downRightCloseVoxel = null;
        Intersectable downLeftCloseVoxel = null;
        Intersectable topRightFarVoxel = null;
        Intersectable topLeftFarVoxel = null;
        Intersectable downRightFarVoxel = null;
        Intersectable downLeftFarVoxel = null;

        //Insert any Geometries in the Geometries t the right voxel
        for (int i = 0; i < _geometries.size(); i++) {
            if (_geometries.get(i).getMiddleZ() < this.getMiddleZ()){
                if (_geometries.get(i).getMiddleY() < this.getMiddleY()){
                    if (_geometries.get(i).getMiddleX() > this.getMiddleX()){
                        if (topRightCloseVoxel == null)
                            topRightCloseVoxel = new Geometries();
                        ((Geometries) topRightCloseVoxel).add(_geometries.get(i));
                    }
                    else {
                        if (topLeftCloseVoxel == null)
                            topLeftCloseVoxel = new Geometries();
                        ((Geometries) topLeftCloseVoxel).add(_geometries.get(i));
                    }
                }
                else {
                    if (_geometries.get(i).getMiddleX() > this.getMiddleX()) {
                        if (downRightCloseVoxel == null)
                            downRightCloseVoxel = new Geometries();
                        ((Geometries) downRightCloseVoxel).add(_geometries.get(i));
                    }
                    else {
                        if (downLeftCloseVoxel == null)
                            downLeftCloseVoxel = new Geometries();
                        ((Geometries) downLeftCloseVoxel).add(_geometries.get(i));
                    }
                }
            }
            else {
                if (_geometries.get(i).getMiddleY() < this.getMiddleY()){
                    if (_geometries.get(i).getMiddleX() > this.getMiddleX()) {
                        if (topRightFarVoxel == null)
                            topRightFarVoxel = new Geometries();
                        ((Geometries) topRightFarVoxel).add(_geometries.get(i));
                    }
                    else {
                        if (topLeftFarVoxel == null)
                            topLeftFarVoxel = new Geometries();
                        ((Geometries) topLeftFarVoxel).add(_geometries.get(i));
                    }
                }
                else {
                    if (_geometries.get(i).getMiddleX() > this.getMiddleX()) {
                        if (downRightFarVoxel == null)
                            downRightFarVoxel = new Geometries();
                        ((Geometries) downRightFarVoxel).add(_geometries.get(i));
                    }
                    else {
                        if (downLeftFarVoxel == null)
                            downLeftFarVoxel = new Geometries();
                        ((Geometries) downLeftFarVoxel).add(_geometries.get(i));
                    }
                }
            }
        }

        _geometries.clear();

        //check for each voxel if it contain more than one geometries
        if(topRightCloseVoxel != null) {
            if (((Geometries)topRightCloseVoxel)._geometries.size() == 1)
                _geometries.add(((Geometries)topRightCloseVoxel)._geometries.get(0));
            else{
                if(depthOfTree > 1)
                    ((Geometries)topRightCloseVoxel).createTreeRec(depthOfTree -1);
                _geometries.add(topRightCloseVoxel);
            }
        }

        if(topLeftCloseVoxel != null) {
            if (((Geometries)topLeftCloseVoxel)._geometries.size() == 1)
                _geometries.add(((Geometries)topLeftCloseVoxel)._geometries.get(0));
            else{
                if(depthOfTree > 1)
                    ((Geometries)topLeftCloseVoxel).createTreeRec(depthOfTree -1);
                _geometries.add(topLeftCloseVoxel);
            }
        }

        if(downRightCloseVoxel != null) {
            if (((Geometries)downRightCloseVoxel)._geometries.size() == 1)
                _geometries.add(((Geometries)downRightCloseVoxel)._geometries.get(0));
            else{
                if(depthOfTree > 1)
                    ((Geometries)downRightCloseVoxel).createTreeRec(depthOfTree -1);
                _geometries.add(downRightCloseVoxel);
            }
        }

        if(downLeftCloseVoxel != null) {
            if (((Geometries)downLeftCloseVoxel)._geometries.size() == 1)
                _geometries.add(((Geometries)downLeftCloseVoxel)._geometries.get(0));
            else{
                if(depthOfTree > 1)
                    ((Geometries)downLeftCloseVoxel).createTreeRec(depthOfTree -1);
                _geometries.add(downLeftCloseVoxel);
            }
        }

        if(topRightFarVoxel != null) {
            if (((Geometries)topRightFarVoxel)._geometries.size() == 1)
                _geometries.add(((Geometries)topRightFarVoxel)._geometries.get(0));
            else{
                if(depthOfTree > 1)
                    ((Geometries)topRightFarVoxel).createTreeRec(depthOfTree -1);
                _geometries.add(topRightFarVoxel);
            }
        }

        if(topLeftFarVoxel != null) {
            if (((Geometries)topLeftFarVoxel)._geometries.size() == 1)
                _geometries.add(((Geometries)topLeftFarVoxel)._geometries.get(0));
            else{
                if(depthOfTree > 1)
                    ((Geometries)topLeftFarVoxel).createTreeRec(depthOfTree -1);
                _geometries.add(topLeftFarVoxel);
            }
        }

        if(downRightFarVoxel != null) {
            if (((Geometries)downRightFarVoxel)._geometries.size() == 1)
                _geometries.add(((Geometries)downRightFarVoxel)._geometries.get(0));
            else{
                if(depthOfTree > 1)
                    ((Geometries)downRightFarVoxel).createTreeRec(depthOfTree -1);
                _geometries.add(downRightFarVoxel);
            }
        }

        if(downLeftFarVoxel != null) {
            if (((Geometries)downLeftFarVoxel)._geometries.size() == 1)
                _geometries.add(((Geometries)downLeftFarVoxel)._geometries.get(0));
            else{
                if(depthOfTree > 1)
                    ((Geometries)downLeftFarVoxel).createTreeRec(depthOfTree -1);
                _geometries.add(downLeftFarVoxel);
            }
        }
    }
}
