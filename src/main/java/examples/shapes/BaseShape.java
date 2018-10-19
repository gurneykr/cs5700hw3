package examples.shapes;

import java.util.stream.Stream;

public abstract class BaseShape implements Shape{

    protected RenderDetails renderDetails;

    public RenderDetails getRenderDetails() {
        return renderDetails;
    }

    public void setRenderDetails(RenderDetails renderDetails) {
        this.renderDetails = renderDetails;
    }

    public Stream serialize() {
        return this.toString().codePoints().mapToObj(c -> String.valueOf((char) c));
    }
}
