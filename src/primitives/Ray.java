package primitives;
import static primitives.Util.isZero;

public class Ray {
    Point3D _p0;
    Vector _dir;

    public Ray(Point3D _p0, Vector _dir) {
        try {
            if(isZero
                    (Math.sqrt(
                            _dir._head.get_x().get()*_dir._head.get_x().get()+
                                    _dir._head.get_y().get()*_dir._head.get_y().get() +
                                    _dir._head.get_z().get()*_dir._head.get_z().get()
                    )
                    -1)
            )
                throw new IllegalArgumentException();

        }
        catch (Exception ex) {
//צריך להוסיף פה מה לעשות
        }
        this._p0 = _p0;
        this._dir = _dir;
    }


}
