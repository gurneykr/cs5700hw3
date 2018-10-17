package examples.shapes;
import java.awt.*;
import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ellipse extends BaseShape{

    private Point center;
    private double a;
    private double b;

    public Ellipse(Point center, double a, double b)throws ShapeException{
        Validator.validatePositivePoint(center, "Center Invalid");

        if(a < 0 || b < 0){
            throw new ShapeException("A and B can't be negative");
        }

        this.center = center.copy();
        this.a = a;
        this.b = b;

    }

    public Point getCenter()throws ShapeException{
        return center.copy();
    }

    public double getFoci(){
        //length of foci = a^2-b^2
        return Math.sqrt(Math.pow(a,2) - Math.pow(b,2));
    }

    public double getArea(){
        //area= pi*a*b
        return Math.PI * a * b;
    }

    //move the ellipse
    public void moveTo(Point movePoint)throws ShapeException{
        center = movePoint.copy();
    }

    public void move(double x, double y)throws ShapeException{
        center.moveX(x);
        center.moveY(y);
    }

    @Override
    public Stream serialize() {
        return this.toString().codePoints().mapToObj(c -> String.valueOf((char) c));
    }

    // @Override
    public static Ellipse deserialize(Stream stream) throws ShapeException{
        Ellipse ellipse = null;
        String string = (String)stream.collect(Collectors.joining(""));

        String regX = "<Ellipse::center=(\\S+),a=(\\S+),b=(\\S+)::Ellipse>";
        Pattern p = Pattern.compile(regX);
        Matcher m = p.matcher(string);
        if(m.find()){
            String centerString = m.group(1);
            Stream streamCenter = centerString.codePoints().mapToObj(c -> String.valueOf((char) c));
            Point center = Point.deserialize(streamCenter);

            String a = m.group(2);

            String b = m.group(3);

            ellipse = new Ellipse(center, Double.parseDouble(a),Double.parseDouble(b));
        }
        return ellipse;
    }

    @Override
    public String toString() {
        return "<Ellipse::center=" + center.toString() + ",a=" +  a
                + ",b=" + b + "::Ellipse>";
    }

    public void render(Graphics graphics) throws ShapeException{
        // Shift the shape by the specified rendering offset
        //move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        //int x = (int) Math.round(center.getX() - getRadius());

        // Compute the top side of the bounding box
        //int y = (int) Math.round(center.getY() - getRadius());

        // Compute the width of the bounding box
        //int width = (int) Math.round(getRadius()*2);

        // Draw the circle by drawing an oval in a square bounding box
        graphics.setColor(Color.GREEN);
        graphics.fillOval((int)center.getX(), (int)center.getY(), (int)(2*a), (int)(2*b));
        graphics.drawOval((int)center.getX(), (int)center.getY(), (int)(2*a), (int)(2*b));

        // Shift the shape back to its original location
        //move(xOffset, yOffset);
    }
}
