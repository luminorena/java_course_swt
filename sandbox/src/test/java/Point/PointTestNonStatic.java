package Point;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class PointTestNonStatic {
    public void testPointNonStatic(){
        double a = new Point(1, 2).distance(new Point(3, 4));
        Assert.assertEquals(a, 2.8284271247461903);
    }
}
