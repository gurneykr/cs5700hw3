package examples.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CompositeShape implements Shape {
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
    public Stream serialize() {
        return null;
    }

    @Override
    public Shape deserialize(Stream stream) throws ShapeException{
        return null;
    }
}
