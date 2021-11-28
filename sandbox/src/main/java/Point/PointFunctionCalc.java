package Point;

public class PointFunctionCalc {
    public double x;
    public double y;

    public PointFunctionCalc(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point p1, Point p2) {
        System.out.println("public static double distance");
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }


}
