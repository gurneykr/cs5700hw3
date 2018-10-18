package examples.shapes;

import java.awt.*;
import java.util.stream.Stream;

public interface Shape {
    Stream serialize();
    void render(Graphics2D graphics) throws ShapeException;
}
