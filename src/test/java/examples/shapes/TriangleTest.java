package examples.shapes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TriangleTest {
    @Test
    public void testValidConstruction() throws Exception {
        Point a = new Point(1,1);
        Point b = new Point(2,3);
        Point c = new Point(3, 1);

        Triangle triangle = new Triangle(a, b, c);

        assertEquals(a.getX(), triangle.getA().getX(), 0.0000001);
    }

    @Test
    public void testZeroTriangle() {

        Point a = null;
        Point b = null;
        Point c = null;
        Triangle triangle = null;
        try {
            a = new Point(0, 0);
            b = new Point(0, 0);
            c = new Point(0, 0);

            triangle = new Triangle(a, b, c);
            fail("Cannot construct an empty triangle"); //if we ever get here, an exception is expected

        }catch (ShapeException e){

            assertEquals(true,true);
        }
    }

    @Test
    public void testMove()throws ShapeException{
        Point a = new Point(1,1);
        Point b = new Point(2,3);
        Point c = new Point(3, 1);
        Triangle triangle = new Triangle(a, b, c);

        triangle.move(0,0);//no move

        assertEquals(triangle.getA(), a);
        assertEquals(triangle.getB(), b);
        assertEquals(triangle.getC(), c);

        //////////////////////////////

        Triangle triangle2 = new Triangle(a, b, c);

        triangle2.move(-1,-2);//negative move

        Point expectedA = new Point(0,-1);
        Point expectedB = new Point(1,1);
        Point expectedC = new Point(2, -1);

        assertEquals(triangle2.getA(), expectedA);
        assertEquals(triangle2.getB(), expectedB);
        assertEquals(triangle2.getC(), expectedC);

    }

    @Test
    public void testGetArea()throws ShapeException{
        Point a = new Point(1,1);
        Point b = new Point(3,3);
        Point c = new Point(3, 1);

        Triangle triangle = new Triangle(a, b, c);
        double area = triangle.getArea();

        assertEquals(2, area, 0.0000001);
    }
}
