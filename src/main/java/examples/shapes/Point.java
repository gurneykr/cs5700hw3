package examples.shapes;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Point
 *
 * This class represents point objects that can be moved and copied
 */
@SuppressWarnings("WeakerAccess")
public class Point implements Shape{
    private double x;
    private double y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        Boolean test = Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    /**
     * Constructor
     *
     * @param x                 The x-location of the point -- must be a valid double
     * @param y                 The y-location of the point -- must be a valid double
     * @throws ShapeException   Exception throw if any parameter is invalid
     */
    public Point(double x, double y) throws ShapeException {
        Validator.validateDouble(x, "Invalid x-location");
        Validator.validateDouble(y, "Invalid y-location");
        this.x = x;
        this.y = y;
    }

    /**
     * @return  The x-location of the point
     */
    public double getX() { return x; }

    /**
     * @return  The y-location of the point
     */
    public double getY() { return y; }

    /**
     * Move the point in the x direction
     *
     * @param deltaX            The delta amount to move the point -- must be a valid double
     * @throws ShapeException   Exception thrown if the parameter is invalid
     */
    public void moveX(double deltaX) throws ShapeException {
        Validator.validateDouble(deltaX, "Invalid delta-x value");
        x += deltaX;
    }

    /**
     * Move the point in the y direction
     *
     * @param deltaY            The delta amount to move the point -- must be a valid double
     * @throws ShapeException   Exception thrown if the parameter is invalid
     */
    public void moveY(double deltaY) throws ShapeException {
        Validator.validateDouble(deltaY, "Invalid delta-y value");
        y += deltaY;
    }

    /**
     * Move the point
     *
     * @param deltaX            The delta amount to move the point in the x direction -- must be a valid double
     * @param deltaY            The delta amount to move the point in the y direction -- must be a valid double
     * @throws ShapeException   Exception throw if any parameter is invalid
     */
    public void move(double deltaX, double deltaY) throws ShapeException {
        moveX(deltaX);
        moveY(deltaY);
    }

    /**
     * Copy the point
     * @return                  A new point with same x and y locations
     * @throws ShapeException   Should never thrown because the current x and y are valid
     */
    public Point copy() throws ShapeException {
        return new Point(x, y);
    }


    @Override
    public Stream serialize() {
        //<Point::x=123:y=456>
        //<Circle::radius=1234:center=<Point::x=123:y=456>

       // String obj = "<Point::x="+ this.getX() + ",y=" + this.getY() + "::Point>";
        return this.toString().codePoints().mapToObj(c -> String.valueOf((char) c));
    }

    @Override
    public Shape deserialize(Stream stream) throws ShapeException{
        //get the stream into a string
        //parse it and create a shape object
        String string = (String)stream.collect(Collectors.joining(""));
        //System.out.println("deserialized Point is:" + string);

        //tokenize the string to it's parts so that we can build a Point object
        int startXIndex = string.indexOf("<Point::x=") + 10;
        int endXIndex = string.indexOf(",y=");
        Double x = Double.parseDouble(string.substring(startXIndex, endXIndex));

        int startYIndex = string.indexOf(",y=") + 3;
        int endYIndex = string.indexOf("::Point>");
        Double y = Double.parseDouble(string.substring(startYIndex, endYIndex));

        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "<Point::x="+ this.getX() + ",y=" + this.getY() + "::Point>";
    }
}
