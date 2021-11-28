package Point;

import java.lang.Math;

public class Point {
    public double x;
    public double y;
    public double calculatedDistance;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(double calculatedDistance) {
        return this.calculatedDistance = calculatedDistance;
    }
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }
    public double distance(Point p) {
        double calculatedDistance = Math.sqrt(Math.pow((x - p.x), 2) + Math.pow((y - p.y), 2));
        System.out.println(calculatedDistance);
        return calculatedDistance;
    }
}

