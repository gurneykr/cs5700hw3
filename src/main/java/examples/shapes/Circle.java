package examples.shapes;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Circle
 *
 * This class represents circle objects that can be moved and scales.  Users of a circle can also get its area.
 *
 */
@SuppressWarnings("WeakerAccess")
public class Circle extends BaseShape{
    private Point center;
    private double radius;

    /**
     * Constructor with x-y Location for center
     *
     * @param x                 The x-location of the center of the circle -- must be a valid double
     * @param y                 The y-location of the center of the circle
     * @param radius            The radius of the circle -- must be greater or equal to zero.
     * @throws ShapeException   The exception thrown if the x, y, or z are not valid
     */
    public Circle(double x, double y, double radius) throws ShapeException {
        Validator.validatePositiveDouble(radius, "Invalid radius");
        center = new Point(x, y);
        this.radius = radius;
    }

    /**
     * Constructor with a Point for center
     *
     * @param center            The x-location of the center of the circle -- must be a valid point
     * @param radius            The radius of the circle -- must be greater or equal to zero.
     * @throws ShapeException   The exception thrown if the x, y, or z are not valid
     */
    public Circle(Point center, double radius) throws ShapeException {
        Validator.validatePositiveDouble(radius, "Invalid radius");
        if (center==null)
            throw new ShapeException("Invalid center point");

        this.center = center;
        this.radius = radius;
    }

    /**
     * @return  The center of the circle
     */
    public Point getCenter() { return center; }

    /**
     * @return  The radius of the circle
     */
    public double getRadius() { return radius; }

    /**
     * Move the circle
     * @param deltaX            a delta change for the x-location of center of the circle
     * @param deltaY            a delta change for the y-location of center of the circle
     * @throws ShapeException   Exception thrown if either the delta x or y are not valid doubles
     */
    public void move(double deltaX, double deltaY) throws ShapeException {
        center.move(deltaX, deltaY);
    }

    /**
     * Scale the circle
     *
     * @param scaleFactor       a non-negative double that represents the percentage to scale the circle.
     *                          0>= and <1 to shrink.
     *                          >1 to grow.
     * @throws ShapeException   Exception thrown if the scale factor is not valid
     */
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
        radius *= scaleFactor;
    }

    /**
     * @return  The area of the circle.
     */
    public double computeArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public Stream serialize() {
        return this.toString().codePoints().mapToObj(c -> String.valueOf((char) c));
    }

    //@Override
    public static Circle deserialize(Stream stream)throws ShapeException {
        Circle circle = null;
        String string = (String)stream.collect(Collectors.joining(""));

        String regX = "<Circle::center=(\\S+),radius=(\\S+)::Circle>";
        Pattern p = Pattern.compile(regX);
        Matcher m = p.matcher(string);
        if(m.find()){
            String centerString = m.group(1);
            Stream streamCenter = centerString.codePoints().mapToObj(c -> String.valueOf((char) c));
            Point center = Point.deserialize(streamCenter);

            String radius = m.group(2);


            circle = new Circle(center, Double.parseDouble(radius));
        }
        return circle;
    }

    @Override
    public String toString() {
        return "<Circle::center=" + center.toString() + ",radius=" +  radius + "::Circle>";
    }

//    public String toSVG(int width, int height){
////        <svg width="100" height="100">
////  <circle cx="50" cy="50" r="40" stroke="green" stroke-width="4" fill="yellow" />
////</svg>
//        StringBuilder sb = new StringBuilder();
//        sb.append("<svg width=").append("\"").append(width).append("\"").append(" height=").append("\"").append(height).append("\"").append(">")
//                .append("<circle cx=").append("\"").append(cx).append("\"").append(" cy=").append("\"").append(cy).append("\"")
//                .append(" r=").append("\"").append(radius).append("\"")
//                .append(" stroke=").append("\"").append(lineColor).append("\"").append(" stroke-width=")
//                .append("\"").append(lineWidth).append("\"").append(" fill=").append("\"").append(fillColor).append("\"").append(" />")
//        .append("</svg");
//
//        return sb.toString();
//    }

    public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException{
        // Shift the shape by the specified rendering offset
        //move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x = (int) Math.round(center.getX() - getRadius());

        // Compute the top side of the bounding box
        int y = (int) Math.round(center.getY() - getRadius());

        // Compute the width of the bounding box
        int width = (int) Math.round(getRadius()*2);

        // Draw the circle by drawing an oval in a square bounding box
        graphics.setColor(Color.GREEN);
        graphics.fillOval(x, y, width, width);
        graphics.drawOval(x, y, width, width);

        // Shift the shape back to its original location
        //move(xOffset, yOffset);
    }
}
