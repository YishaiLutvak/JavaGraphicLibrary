package renderer;

import elements.Camera;
import elements.LightSource;
import primitives.*;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Render class for rendering a image
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class Render {
    /**
     * The max recursion depth in calcColor
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * The minimum value of color necessary to calculate at calcColor function
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    ImageWriter _imageWriter;
    Scene _scene;

    /**
     * constructor
     *
     * @param imageWriter save in file the image
     * @param scene       contain all the elements of the scene
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    /**
     * renderImage function
     * The function colors the view plane
     * If their are intersection points with geometries,
     * the function Takes the closest intersection points to the camera,
     * and paints the pixels of the closest points in the color of the points.
     * In addition, the function colors the rest of the pixels in the background color
     */
    public void renderImage() {
        Camera camera = _scene.getCamera();

        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();
        double distance = _scene.getDistance();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                /*Ray centeralRay = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
                if (actDepthOfField) {*/
                    List<Color> colors = new LinkedList<Color>();
                    List<Ray> rays = camera.constructBeamThroughPixel(nX, nY, j, i, distance, width, height);
                    for (Ray ray: rays) {
                        GeoPoint closestPoint = findClosestIntersection(ray);
                        colors.add(new Color(closestPoint == null ? _scene.getBackground().getColor() : calcColor(closestPoint, ray).getColor()));
                    }
                    _imageWriter.writePixel(j, i, new Color(colors).getColor());
                /*}
                else{
                    GeoPoint closestPoint = findClosestIntersection(centeralRay);
                    _imageWriter.writePixel(j, i, closestPoint == null ? _scene.getBackground().getColor() : calcColor(closestPoint, centeralRay).getColor());
                }*/
            }
        }
    }

    /**
     * The function gets a ray and find the closest intersection point to the ray origin
     *
     * @param ray a ray that intersect  the geometry
     * @return the closest intersection point. If their is no points return null
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        Intersectable geometries = _scene.getGeometries();
        List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
        if (intersectionPoints == null)
            return null;
        else {
            GeoPoint minDistance = intersectionPoints.get(0);
            for (GeoPoint geoPoint : intersectionPoints) {
                if (geoPoint._point.distance(ray.get_start()) < minDistance._point.distance(ray.get_start())) {
                    minDistance = geoPoint;
                }
            }
            return minDistance;
        }
    }

    /**
     * Calculate the reflection ray from point
     *
     * @param normal   the normal of the geometry
     * @param rayPoint the intersection point
     * @param ray      ray to the point
     * @return new reflection ray
     */
    private Ray constructReflectedRay(Vector normal, Point3D rayPoint, Ray ray) {
        Vector newVector = reflectionDirection(ray.get_direction(), normal);
        return new Ray(rayPoint, newVector, normal);
    }

    /**
     * construct new refracted ray after shift in delta
     *
     * @param normal   the normal of the geometry
     * @param rayPoint the intersection point
     * @param ray      ray hit the geometry
     * @return new ray
     */
    private Ray constructRefractedRay(Vector normal, Point3D rayPoint, Ray ray) {
        return new Ray(rayPoint, ray.get_direction(), normal);
    }

    /**
     * caculate reflected ray from the geometry
     *
     * @param v the vector that hit the geometry
     * @param n normal from the geometry
     * @return a ray reflected from the geometry
     */
    private Vector reflectionDirection(Vector v, Vector n) {
        //r = v - 2*(v*n)*n

        return v.add(n.scale(-2 * v.dotProduct(n)));
    }

    /**
     * Acting imageWriter's writeToImage function
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * calcColor function
     * Calculate the point's color
     *
     * @param geoPoint a GeoPoint for point 3D and its Geometry
     * @param inRay    the ray of light we calculate its color reflection and transparency
     * @return color representing the point's appearance
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
        return calcColorRec(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.getAmbientLight().getIntensity());
    }

    /**
     * a recursive calculate of the color of the point
     *
     * @param gp    the point we looking for its color
     * @param inRay the ray of light we calculate its color reflection and transparency
     * @param level the current level of recursion
     * @param k     the proportional weight of the current recursion color calculation
     * @return the total color of the point
     */
    private Color calcColorRec(GeoPoint gp, Ray inRay, int level, double k) {
        if (level == 0 || k < MIN_CALC_COLOR_K)
            return Color.BLACK;

        Color color = gp._geometry.getEmissionLight(); // remove Ambient Light
        Vector v = gp._point.subtract(_scene.getCamera().getLocation()).normalize();
        Vector n = gp._geometry.getNormal(gp._point);
        Material material = gp._geometry.getMaterial();
        int nShininess = material.getShininess();
        double kd = material.getKd();
        double ks = material.getKs();

        for (LightSource lightSource : _scene.getLights()) {
            Vector l = lightSource.getL(gp._point);
            if (alignZero(n.dotProduct(l)) * alignZero(n.dotProduct(v)) > 0) {
                double ktr = transparency(lightSource,l,n,gp);
                if (ktr*k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(gp._point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }

        if (level == 1)
            return Color.BLACK;

        double kr = material.getKr(), kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, gp._point, inRay);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null)
                color = color.add(calcColorRec(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }

        double kt = material.getKt(), kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(n, gp._point, inRay);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null)
                color = color.add(calcColorRec(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }

        return color;
    }

    /**
     * @param kd             kd value in the point's material
     * @param l              vector in the direction from the light source the point
     * @param n              the normal of the geometry
     * @param lightIntensity the intensity of the light source
     * @return the diffusive in the point
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        return new Color(lightIntensity.scale(Math.abs(kd * l.dotProduct(n))));
    }

    /**
     * @param ks             ks value in the point's material
     * @param l              vector from the light source the point
     * @param n              the normal of the geometry
     * @param v              vector from the camera to the point
     * @param nShininess     the shininess factor in the point
     * @param lightIntensity the intensity of the light source
     * @return the color with specular effect in the point
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = reflectionDirection(l, n);
        return new Color(lightIntensity.scale(ks * Math.pow(v.scale(-1).dotProduct(r), nShininess)));
    }

    /**
     * printGrid function
     *
     * @param interval the size of the grid's squares
     * @param color    the color of the grid
     */
    public void printGrid(int interval, java.awt.Color color) {
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();

        for (int i = 0; i < Ny; i++)
            for (int j = 0; j < Nx; j++)
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, color);
    }

    /**
     * calculate the total transparency factor for the light ray hit the geopoint
     * @param light a light source
     * @param l light vector
     * @param n normal from the point
     * @param gp geopoint
     * @return if their is no shadow return 1
     * if total shadow return 0
     * else return the transparency factor
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1); // from point to light source

        Ray lightRay = new Ray(gp._point, lightDirection, n);

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null)
            return 1d;
        double lightDistance = light.getDistance(gp._point);
        double ktr = 1d;

        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint._point.distance(gp._point) - lightDistance) <= 0) {
                ktr *= geoPoint._geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) {
                    return 0d;
                }
            }
        }
        return ktr;
    }
/*
    *//**
     * check if their is no shadow on the point
     *
     * @param l     vector from the light source the point
     * @param n     the normal of the geometry
     * @param gp    GeoPoint of the point checked
     * @param light the light Source causing shadow
     * @return true if their is no shadow on the point
     *//*
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp._point, lightDirection, n);

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null)
            return true;
        double lightDistance = light.getDistance(gp._point);
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint._point.distance(gp._point) - lightDistance) <= 0 &&
                    geoPoint._geometry.getMaterial().getKt() == 0)
                return false;
        }
        return true;
    }

    *//**
     * getClosestPoint private function
     * @param intersectionPoints a list of intersection points
     * between the geometries and the ray from the camera
     * @return the closest point and its Geometry
     *//*
    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        Point3D cameraLocation = _scene.getCamera().getLocation();
        GeoPoint minDistance = intersectionPoints.get(0);
        for (GeoPoint geoPoint : intersectionPoints) {
            if (geoPoint._point.distance(cameraLocation) < minDistance._point.distance(cameraLocation)) {
                minDistance = geoPoint;
            }
        }
        return minDistance;
    }
*/
}