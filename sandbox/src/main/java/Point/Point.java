package Point;

import java.lang.Math;

public class Point {
    public double x;
    public double y;
    public double rez;


    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double calc(double rez){
        return this.rez = rez;
    }
    public double calc(Point p) {
        double rez= Math.sqrt(Math.pow((x - p.x), 2) + Math.pow((y - p.y), 2));
        System.out.println(rez);
        return rez;
    }

}

