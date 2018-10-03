package examples.shapes;

public class Square extends Rectangle{

    public Square (Point bottomLeft, Point bottomRight, Point topLeft, Point topRight)throws ShapeException{
        super(bottomLeft, bottomRight,topLeft, topRight );
        Validator.validateEqualSides(bottomLeft, bottomRight,topLeft, topRight);
    }

}
