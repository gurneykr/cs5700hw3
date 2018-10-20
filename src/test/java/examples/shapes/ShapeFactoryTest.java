package examples.shapes;

import org.junit.Test;

public class ShapeFactoryTest {
    @Test
    public void testInvalidCreateShape(){
        try {
            String string = "Banana";
            ShapeFactory.createShape(string);
        }catch (ShapeException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testValidCircle(){
        try {
            String string = "Circle::";
            ShapeFactory.createShape(string);
        }catch (ShapeException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testValidEllipse(){
        try {
            String string = "Ellipse::";
            ShapeFactory.createShape(string);
        }catch (ShapeException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testValidLine(){
        try {
            String string = "Line::";
            ShapeFactory.createShape(string);
        }catch (ShapeException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testValidPoint(){
        try {
            String string = "Point::";
            ShapeFactory.createShape(string);
        }catch (ShapeException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testValidRectangle(){
        try {
            String string = "Rectangle::";
            ShapeFactory.createShape(string);
        }catch (ShapeException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testValidSquare(){
        try {
            String string = "Square::";
            ShapeFactory.createShape(string);
        }catch (ShapeException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testValidTriangle(){
        try {
            String string = "Triangle::";
            ShapeFactory.createShape(string);
        }catch (ShapeException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testValidEmbeddedPicture(){
        try {
            String string = "EmbeddedPicture::";
            ShapeFactory.createShape(string);
        }catch (ShapeException e){
            e.printStackTrace();
        }
    }

}
