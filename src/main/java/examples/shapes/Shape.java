package examples.shapes;

import java.util.stream.Stream;

public interface Shape {
    public abstract  Stream serialize();
 //   public abstract Shape deserialize(Stream stream) throws ShapeException;
}
