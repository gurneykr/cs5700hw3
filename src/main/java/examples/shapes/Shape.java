package examples.shapes;

import java.awt.*;
import java.util.stream.Stream;

public interface Shape {
    Stream serialize();
 //   public abstract Shape deserialize(Stream stream) throws ShapeException;
    void render(Graphics graphics) throws ShapeException;
}
