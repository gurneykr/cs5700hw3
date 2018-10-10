package examples.shapes;

import java.util.stream.Stream;

public class ShapeFactory {
    public static Shape createShape(String shapeString){
        Shape shape = null;
        try {
            Stream stream = shapeString.codePoints().mapToObj(c -> String.valueOf((char) c));
            if (shapeString.startsWith("<Point::")) {
                shape = Point.deserialize(stream);
            }else if(shapeString.startsWith("<Line::")){
                shape = Line.deserialize(stream);
            }else if(shapeString.startsWith("<Rectangle::")){
                shape = Rectangle.deserialize(stream);
            }else if(shapeString.startsWith("<Square::")) {
                shape = Square.deserialize(stream);
            }else if(shapeString.startsWith("<Circle::")){
                shape = Circle.deserialize(stream);
            }else if(shapeString.startsWith("<Ellipse::")){
                shape = Ellipse.deserialize(stream);
            }else if(shapeString.startsWith("<Triangle::")){
                shape = Triangle.deserialize(stream);
            }
        }catch (ShapeException e){
            e.printStackTrace();
        }
        return shape;
    }

}
