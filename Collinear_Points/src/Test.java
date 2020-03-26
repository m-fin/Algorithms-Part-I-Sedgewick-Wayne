import edu.princeton.cs.algs4.StdOut;

public class Test {
    public static void main(String[] args) {
        Point[] points = {
                new Point(10000, 0), new Point(0, 10000), new Point(3000, 7000),
                new Point(7000, 3000), new Point(20000, 21000), new Point(3000, 4000),
                new Point(14000, 15000), new Point(6000, 7000)
        };

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    }

//    public static void main(String[] args) {
//        Point p0 = new Point(0, 0);
//        Point p1 = new Point(1, 1);
//
//        LineSegment line0 = new LineSegment(p0, p1);
//
//        StdOut.println(line0.toString());
//    }
}
