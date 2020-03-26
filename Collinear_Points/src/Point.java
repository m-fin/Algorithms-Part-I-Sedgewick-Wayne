import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    int _x, _y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        _x = x;
        _y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(_x, _y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(_x, _y, that._x, that._y);
    }

    // string representation
    public String toString() {
        return "(" + _x + ", " + _y + ")";
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (_y == that._y && _x == that._x) {
            return 0;
        }
        else if (_y < that._y || (_y == that._y && _x < that._x)) {
            return -1;
        }
        else {
            return 1;
        }
    }

    // the slope between this point and that point
    // given by the formula (y1 − y0) / (x1 − x0).
    // Treat the slope of a horizontal line segment as positive zero;
    // treat the slope of a vertical line segment as positive infinity;
    // treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
    public double slopeTo(Point that) {
        if (_x == that._x && _y == that._y) {
            return Double.NEGATIVE_INFINITY;
        }
        if (_y == that._y) {
            return +0.0;
        }
        if (_x == that._x) {
            return Double.POSITIVE_INFINITY;
        }

        return (double)(that._y - _y) / (that._x - _x);
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new BySlope();
    }

    private class BySlope implements Comparator<Point> {
        Point basePoint = new Point(_x, _y);
        @Override
        public int compare(Point o0, Point o1) {
            double slope0 = basePoint.slopeTo(o0);
            double slope1 = basePoint.slopeTo(o1);
            return Double.compare(slope0, slope1);
        }
    }
}