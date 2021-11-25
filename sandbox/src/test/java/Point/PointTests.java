package Point;

import org.testng.Assert;
import org.testng.annotations.Test;

import static Point.Point.distance;
@Test
public class PointTests {
    public void testPoint(){
        // нет у теста типа возвращаемого значения, а также функции main
        Point p1 = new Point(2, 2);
        Point p2 = new Point(1, 3);
        double d = distance(p1, p2);
        // assert для проверки значения, assertEquals также для обработки исключения
        //assert d == 1.4142135623730951;
        Assert.assertEquals(d, 1.4142135623730951);




    }
}
