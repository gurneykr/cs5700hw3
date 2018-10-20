package examples.shapes;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class EllipseTest {

    @Test
    public void testValidConstruction()throws ShapeException{
        Point center = new Point(5,5);
        Ellipse ellipse = new Ellipse(center, 7, 3);
        Point expectedCenter = ellipse.getCenter();

        assertEquals(expectedCenter, center);
    }

    @Test
    public void testGetFoci()throws ShapeException{
        Point center = new Point(6,5);
        Ellipse ellipse = new Ellipse(center,7, 3);

        assertEquals(ellipse.getFoci(), ( Math.sqrt(Math.pow(7,2) - Math.pow(3,2) )), 0.000000001);
    }

    @Test
    public void testGetArea() throws ShapeException {
        Point center = new Point(6,5);
        Ellipse ellipse = new Ellipse(center,7, 3);

        assertEquals(ellipse.getArea(), Math.PI * 7 * 3, 0.000001);
    }

    @Test
    public void testMoveTo()throws ShapeException{//move to a location
        Point center = new Point(6,5);
        Ellipse ellipse = new Ellipse(center,7, 3);

        Point movePoint = new Point(1,2);

        ellipse.moveTo(movePoint);
        assertEquals(ellipse.getCenter(), movePoint);

    }

    @Test
    public void testMove()throws ShapeException{//move an amount
        Point center = new Point(6,5);
        Ellipse ellipse = new Ellipse(center,7, 3);

        ellipse.move(0,0);//test for no move
        assertEquals(ellipse.getCenter(), center);

        ellipse.move(1, 3);
        Point expectedCenter = new Point(center.getX()+1, center.getY()+3);
        assertEquals(ellipse.getCenter(), expectedCenter);

    }

    @Test
    public void testDeserialize(){
        try {
            String string = "<Ellipse::center=<Point::x=6.0,y=7.0::Point>,a=8.0,b=9.0::Ellipse>";
            Stream stream = string.codePoints().mapToObj(c -> String.valueOf((char) c));
            Ellipse ellipse = Ellipse.deserialize(stream);
            assertNotNull(ellipse);
            assertEquals(ellipse instanceof Ellipse, true);
            assertEquals(ellipse.getCenter().getX() == 6, true);
            assertEquals(ellipse.getCenter().getY() == 7, true);
            assertEquals(ellipse.getA() == 8, true);
            assertEquals(ellipse.getB() == 9, true);

        }catch (ShapeException e){
            assertEquals(true, false);
            e.printStackTrace();
        }
    }

    @Test
    public void testInvalidDeserialize(){//expect to fail
        try {
            String string = "<Ellipse::center=12,a=8,b=9::Ellipse>";
            Stream stream = string.codePoints().mapToObj(c -> String.valueOf((char) c));
            Ellipse ellipse = Ellipse.deserialize(stream);
            assertEquals(true, false);
        }catch (ShapeException e){
            assertEquals(true, true);
        }

    }
}
