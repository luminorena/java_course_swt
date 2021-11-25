package Point;

import java.lang.Math;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point p1, Point p2) {
        double rez = Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
       /* можно сделать функцию void, но не выводить sout в main.
       Задача сделать расчёт, а если тип данных указывать, то тогда
       появляются лишние строчки кода, что по задаче, в принципе, лишнее
       System.out.println(rez);
        */
        return rez;
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(1, 3);
        System.out.println(distance(p1, p2));
        distance(p1, p2);
    }
}