package examples.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CompositeShape extends BaseShape {
    private List<Shape> shapeList = new ArrayList();

    public void addShape(Shape shape){
        shapeList.add(shape);
    }
    public void removeShape(Shape shape){
        shapeList.remove(shape);
    }
    public void removeAllShape(){
        shapeList.clear();
    }
    @Override
    public void render(Graphics2D graphics) throws ShapeException {
        for(Shape s: shapeList){
            s.render(graphics);
        }
    }

   //serialize is now in the BaseShape

    //@Override
    public Shape deserialize(Stream stream) throws ShapeException{
        return null;
    }

    @Override
    public double getArea() throws ShapeException {
        double area = 0;
        for(Shape s: shapeList){
            area += s.getArea();
        }
        return area;
    }
}
