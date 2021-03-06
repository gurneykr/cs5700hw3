package examples.shapes;

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
public class Circle extends Ellipse{
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
        super(new Point(x,y), radius, radius);
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
        super(center, radius, radius);
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

    //Serialize method is now in the BaseShape

    //@Override
    public static Circle deserialize(Stream stream)throws ShapeException {
        Circle circle = null;
        String string = (String)stream.collect(Collectors.joining(""));

        String regX = "<Circle::center=(\\S+),radius=(\\S+)::Circle>";
        Pattern p = Pattern.compile(regX);
        Matcher m = p.matcher(string);
        if(m.find()){
            String centerString = m.group(1);
            Stream streamCenter = centerString.codePoints().mapToObj(c -> String.valueOf((char) c));//turn String into Stream
            Point center = Point.deserialize(streamCenter);//Asking Point to construct a new point from the Stream

            String radius = m.group(2);

            if(center != null) {
                circle = new Circle(center, Double.parseDouble(radius));
            }else{
                throw new ShapeException("Invalid center point");
            }
        }
        return circle;
    }

    @Override
    public String toString() {
        return "<Circle::center=" + center.toString() + ",radius=" +  radius + "::Circle>";
    }

    @Override
    public double getArea(){
        return computeArea();
    }
}
