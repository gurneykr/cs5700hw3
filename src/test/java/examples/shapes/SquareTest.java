package examples.shapes;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class SquareTest {

    @Test
    public void testValidConstruction()throws ShapeException{
        Point bottomLeft = new Point(0,0);
        Point bottomRight = new Point(1,0);
        Point topLeft = new Point(0,1);
        Point topRight = new Point(1,1);

        Square square = new Square(bottomLeft, bottomRight, topLeft, topRight);

        assertEquals(true, true);//no exception thrown if it gets to this
    }

    @Test
    public void testInvalidConstruction(){
        try{
            Point bottomLeft = new Point(0,0);
            Point bottomRight = new Point(2,0);
            Point topLeft = new Point(0,1);
            Point topRight = new Point(2,1);

            Square square = new Square(bottomLeft, bottomRight, topLeft, topRight);
            fail("Non square coordinates should fail");
        }catch (ShapeException e){
            assertEquals(true,true);//expected this exception
        }
    }
    @Test
    public void testDeserialize(){
        try {
            String string = "<Square::bottomLeft=(<Point::x=0.0,y=0.0::Point>,bottomRight=<Point::x=1.0," +
                    "y=0.0::Point>,topLeft=<Point::x=0.0,y=1.0::Point>,topRight=<Point::x=1.0,y=1.0::Point>::Square>";
            Stream stream = string.codePoints().mapToObj(c -> String.valueOf((char) c));
            Shape shape = Square.deserialize(stream);
            Square square= (Square) shape;
            assertNotNull(square);
            assertEquals(square instanceof Rectangle, true);
            assertEquals(square.getBottomLeft().getX() == 0.0, true);
            assertEquals(square.getBottomLeft().getY() == 0.0, true);

            assertEquals(square.getBottomRight().getX() == 1.0, true);
            assertEquals(square.getBottomRight().getY() == 0.0, true);

            assertEquals(square.getTopLeft().getX() == 0.0, true);
            assertEquals(square.getTopLeft().getY() == 1.0, true);

            assertEquals(square.getTopRight().getX() == 1.0, true);
            assertEquals(square.getTopRight().getY() == 1.0, true);

        }catch (ShapeException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testInvalidDeserialize(){
        try {
            String string = "<Square::bottomLeft=0.0::Point>,bottomRight=<Point::x=1.0," +
                    "y=0.0::Point>,topLeft=<Point::x=0.0,y=1.0::Point>,topRight=<Point::x=1.0,y=1.0::Point>::Square>";
            Stream stream = string.codePoints().mapToObj(c -> String.valueOf((char) c));
            Shape shape = Square.deserialize(stream);
            Square square = (Square) shape;
            assertEquals(true, false);
        }catch (ShapeException e){
            assertEquals(true, true);
        }
    }
}
