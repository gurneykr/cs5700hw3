package examples.shapes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
}
