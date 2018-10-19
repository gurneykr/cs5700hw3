package examples.shapes;

import java.util.stream.Stream;

public class Square extends Rectangle{

    public Square (Point bottomLeft, Point bottomRight, Point topLeft, Point topRight)throws ShapeException{
        super(bottomLeft, bottomRight,topLeft, topRight );
        Validator.validateEqualSides(bottomLeft, bottomRight,topLeft, topRight);
    }

    public static Shape deserialize(Stream stream) throws ShapeException{
        return deserialize(stream,"Square");
    }

    @Override
    public String toString() {
        return "<Square::bottomLeft=" + bottomLeft.toString() + ",bottomRight=" +  bottomRight.toString()
                + ",topLeft=" + topLeft.toString() + ",topRight=" + topRight.toString() + "::Square>";
    }
}
