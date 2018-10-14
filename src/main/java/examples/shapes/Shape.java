package examples.shapes;

import java.awt.*;
import java.util.stream.Stream;

public interface Shape {
    public abstract  Stream serialize();
 //   public abstract Shape deserialize(Stream stream) throws ShapeException;
    public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException;
}
