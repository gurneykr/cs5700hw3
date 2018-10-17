package examples.shapes;

import java.awt.*;
import java.util.stream.Stream;

public interface Shape {
    Stream serialize();
 //   public abstract Shape deserialize(Stream stream) throws ShapeException;
    void render(Graphics2D graphics) throws ShapeException;
}
