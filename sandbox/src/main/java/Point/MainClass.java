package Point;

public class MainClass {
    public static void main(String[] args) {
        double a = new Point(1, 2).distance(new Point(3, 4));
        Point p1 = new Point(2, 2);
        Point p2 = new Point(1, 3);
        System.out.println(Point.distance(p1, p2));

    }
}
