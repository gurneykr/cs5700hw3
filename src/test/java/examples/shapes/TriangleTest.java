package examples.shapes;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

    @Test
    public void testDeserialize(){
        try {
            String string = "<Triangle::a=<Point::x=1.0,y=1.0::Point>,b=<Point::x=2.0,y=3.0::Point>,c=<Point::x=3.0,y=1.0::Point>::Triangle>";
            Stream stream = string.codePoints().mapToObj(c -> String.valueOf((char) c));
            Triangle triangle = Triangle.deserialize(stream);
            assertNotNull(triangle);
            assertEquals(triangle instanceof Triangle, true);
            assertEquals(triangle.getA().getX() == 1.0, true);
            assertEquals(triangle.getA().getY() == 1.0, true);

            assertEquals(triangle.getB().getX() == 2.0, true);
            assertEquals(triangle.getB().getY() == 3.0, true);

            assertEquals(triangle.getC().getX() == 3.0, true);
            assertEquals(triangle.getC().getY() == 1.0, true);

        }catch (ShapeException e){
            assertEquals(true, false);
            e.printStackTrace();
        }
    }

    @Test
    public void testInvalidDeserialize(){
        try {
            String string = "<Triangle::a=0.0,b=<Point::x=2.0,y=3.0::Point>,c=<Point::x=3.0,y=1.0::Point>::Triangle>";
            Stream stream = string.codePoints().mapToObj(c -> String.valueOf((char) c));
            Triangle triangle = Triangle.deserialize(stream);
            assertEquals(true, false);

        }catch (ShapeException e){
            assertEquals(true, true);
        }
    }
}
