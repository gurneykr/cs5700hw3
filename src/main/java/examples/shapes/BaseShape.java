package examples.shapes;

import java.util.stream.Stream;

public abstract class BaseShape implements Shape{
//    <svg width="100" height="100">
//   <circle cx="50" cy="50" r="40" stroke="green" stroke-width="4" fill="yellow" />
//    Sorry, your browser does not support inline SVG.
//</svg>

    protected String lineColor = "Black";
    protected int lineWidth = 1;
    protected String fillColor = "White";

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }
}
