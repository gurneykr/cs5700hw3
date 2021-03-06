package examples.shapes;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Triangle extends BaseShape{
    private Point a = new Point(1,1);
    private Point b = new Point(2,3);
    private Point c = new Point(3,1);

    public Triangle(Point a, Point b, Point c)throws ShapeException{

        this.a = a.copy();
        this.b = b.copy();
        this.c = c.copy();

        if(this.getArea()==0){
            //if there's ever a triangle with 0 area it's not valid, points are on the same line
            throw new ShapeException("Invalid triangle");
        }
    }

    public Point getA(){
        Point point = null;
        try {
            point = a.copy();
        }catch(ShapeException e){
            //According to Point this will never be thrown
        }
        return point;
    }

    public Point getB(){
        Point point = null;
        try {
            point = b.copy();
        }catch(ShapeException e){
            //According to Point this will never be thrown
        }
        return point;
    }

    public Point getC(){
        Point point = null;
        try {
            point = c.copy();
        }catch(ShapeException e){
            //According to Point this will never be thrown
        }
        return point;
    }

    @Override
    public double getArea() throws ShapeException{
        Line ab = new Line(getA(), getB());
        Line ac = new Line(getA(), getC());
        Line bc = new Line(getB(), getC());

        double abLength = ab.computeLength();
        double acLength = ac.computeLength();
        double bcLength = bc.computeLength();

        //p is half of the perimeter
        double p = (abLength + acLength + bcLength)/2;
        double area = Math.sqrt(p*(p-acLength)*(p-abLength)*(p-bcLength));
        return area;
    }

    public void move(double x, double y) throws ShapeException{
        a.moveX(x);
        a.moveY(y);

        b.moveX(x);
        b.moveY(y);

        c.moveX(x);
        c.moveY(y);
    }

    //@Override
    public static Triangle deserialize(Stream stream) throws ShapeException{
        Triangle triangle = null;
        String string = (String)stream.collect(Collectors.joining(""));

        String regX = "<Triangle::a=(\\S+),b=(\\S+),c=(\\S+)::Triangle>";
        Pattern p = Pattern.compile(regX);
        Matcher m = p.matcher(string);
        if(m.find()){
            String stringA = m.group(1);
            Stream streamA = stringA.codePoints().mapToObj(c -> String.valueOf((char) c));
            Point a = Point.deserialize(streamA);

            String stringB = m.group(2);
            Stream streamB = stringB.codePoints().mapToObj(c -> String.valueOf((char) c));
            Point b = Point.deserialize(streamB);

            String stringC = m.group(3);
            Stream streamC = stringC.codePoints().mapToObj(c -> String.valueOf((char) c));
            Point c = Point.deserialize(streamC);

            triangle = new Triangle(a, b,c);
        }else{
            throw new ShapeException("Invalid triangle points");
        }
        return triangle;
    }
    @Override
    public String toString() {
        return "<Triangle::a=" + a.toString() + ",b=" +  b.toString()
                + ",c=" + c.toString() + "::Triangle>";
    }

    @Override
    public void render(Graphics2D graphics) throws ShapeException{

        // Compute the left side of the bounding box
        int x = (int)a.getX();

        // Compute the top side of the bounding box
        int y = (int)a.getY();

        int xPoints[] = {(int)a.getX() ,(int)b.getX(), (int)c.getX()  };
        int yPoints[] = {(int)a.getY() ,(int)b.getY(), (int)c.getY()  };

        // Draw the circle by drawing an oval in a square bounding box
        if(renderDetails != null){
            graphics.translate(renderDetails.getxPosition(),renderDetails.getyPosition());

            graphics.setColor(renderDetails.getLineColor());
            graphics.drawPolygon(xPoints, yPoints, 3);
            if(renderDetails.getFillColor() != null) {//make sure there is a fill color
                graphics.setColor(renderDetails.getFillColor());
                graphics.fillPolygon(xPoints, yPoints, 3);
            }

            graphics.translate(-renderDetails.getxPosition(),-renderDetails.getyPosition());
        }else{
            graphics.translate(x, y);

            graphics.setColor(Color.white);//default
            graphics.drawPolygon(xPoints, yPoints, 3);

            graphics.translate(-x, -y);
        }
    }
}
