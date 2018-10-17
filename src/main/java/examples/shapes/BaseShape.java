package examples.shapes;

public abstract class BaseShape implements Shape{

    protected RenderDetails renderDetails;

    public RenderDetails getRenderDetails() {
        return renderDetails;
    }

    public void setRenderDetails(RenderDetails renderDetails) {
        this.renderDetails = renderDetails;
    }
}
