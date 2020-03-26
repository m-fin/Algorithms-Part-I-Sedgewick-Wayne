import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    Point[] _points;
    int _numLineSegments;
    ArrayList<LineSegment> _segmentList;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        // Corner cases. Throw an IllegalArgumentException if the argument to the constructor is null,
        // if any point in the array is null, or if the argument to the constructor contains a repeated point.
        if (points == null) {
            throw new IllegalArgumentException("Null argument.");
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Null point.");
            }
            for (Point otherPoint : points) {
                if (point.compareTo(otherPoint) == 0) {
                    throw new IllegalArgumentException("Repeated point.");
                }
            }
        }

        _points = points;
        _numLineSegments = 0;

        ArrayList<Point> collinearPoints = new ArrayList<Point>();

        Arrays.sort(_points);

        // To check whether the 4 points p, q, r, and s are collinear,
        // check whether the three slopes between p and q, between p and r, and between p and s are all equal.
        for (int i = 0; i < _points.length; i++) {
            for (int j = i + 1; j < _points.length; j++) {
                for (int k = j + 1; k < _points.length; k++) {
                    for (int l = k + 1; l < _points.length; l++) {
                        Point p = _points[i];
                        Point q = _points[j];
                        Point r = _points[k];
                        Point s = _points[l];

                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                            _segmentList.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return _segmentList.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[_numLineSegments];

        for (int i = 0; i < lineSegments.length; i++) {
            lineSegments[i] = _segmentList.get(i);
        }

        return lineSegments;
    }

    // sample client
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

//        // print and draw the line segments
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
    }
}