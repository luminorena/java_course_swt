package Point;

import org.testng.Assert;
import org.testng.annotations.Test;

import static Point.Point.distance;
@Test
public class PointTests {
    public void testPoint(){
        Point p1 = new Point(2, 2);
        Point p2 = new Point(1, 3);
        double d = distance(p1, p2);
        System.out.println(d);
        Assert.assertEquals(d, 1.4142135623730951);

        double a = new Point(1, 2).distance(new Point(3, 4));
        Assert.assertEquals(a, 2.8284271247461903);


    }
}



