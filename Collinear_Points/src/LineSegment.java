import edu.princeton.cs.algs4.StdDraw;

public class LineSegment {

    Point _p, _q;

    // constructs the line segment between points p and q
    public LineSegment(Point p, Point q) {
        _p = p;
        _q = q;
    }

    // draws this line segment
    public void draw() {
        StdDraw.line(_p._x, _p._y, _q._x, _q._y);
    }

    // string representation
    public String toString() {
        return _p + " -> " + _q;
    }
}
