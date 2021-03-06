package examples.shapes;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rectangle extends BaseShape{
    protected Point bottomLeft;
    protected Point bottomRight;
    protected Point topLeft;
    protected Point topRight;


    public Rectangle(Point bottomLeft, Point bottomRight, Point topLeft, Point topRight) throws ShapeException{

        Validator.validateRectangle(bottomLeft, bottomRight, topLeft, topRight);

        this.bottomLeft = bottomLeft.copy();
        this.bottomRight = bottomRight.copy();
        this.topLeft = topLeft.copy();
        this.topRight = topRight.copy();

    }


    public Point getBottomLeft(){
        Point point = null;
        try {
            point = bottomLeft.copy();
        }catch(ShapeException e){
            //According to Point this will never be thrown
        }
        return point;
    }

    public Point getBottomRight(){
        Point point = null;
        try {
            point = bottomRight.copy();
        }catch(ShapeException e){
            //According to Point this will never be thrown
        }
        return point;
    }

    public Point getTopLeft(){
        Point point = null;
        try {
            point = topLeft.copy();
        }catch(ShapeException e){
            //According to Point this will never be thrown
        }
        return point;
    }

    public Point getTopRight(){
        Point point = null;
        try {
            point = topRight.copy();
        }catch(ShapeException e){
            //According to Point this will never be thrown
        }
        return point;
    }

    public double getWidth()throws ShapeException{
        Line line = new Line(bottomLeft, bottomRight);

        return line.computeLength();
    }

    public double getHeight() throws ShapeException{

        Line line = new Line(topLeft,  bottomLeft);

        return line.computeLength();

    }

    @Override
    public double getArea()throws ShapeException{
        return getHeight() * getWidth();
    }

    public void move(double x, double y) throws ShapeException{
        bottomLeft.moveX(x);
        bottomLeft.moveY(y);

        bottomRight.moveX(x);
        bottomRight.moveY(y);

        topLeft.moveX(x);
        topLeft.moveY(y);

        topRight.moveX(x);
        topRight.moveY(y);
    }

    //serialize is now in the BaseShape


    public static Shape deserialize(Stream stream) throws ShapeException{
        return deserialize(stream,"Rectangle");
    }

    protected static Shape deserialize(Stream stream, String shapeType) throws ShapeException{
        Shape shape  = null;
        String string = (String)stream.collect(Collectors.joining(""));

        String regX = "<" + shapeType + "::bottomLeft=(\\S+),bottomRight=(\\S+),topLeft=(\\S+),topRight=(\\S+)::" + shapeType + ">";
        Pattern p = Pattern.compile(regX);
        Matcher m = p.matcher(string);
        if(m.find()){
            String bottomLeft = m.group(1);
            Stream streamBottomLeft = bottomLeft.codePoints().mapToObj(c -> String.valueOf((char) c));
            Point bottomLeftPoint = Point.deserialize(streamBottomLeft);

            String bottomRight = m.group(2);
            Stream streamBottomRight = bottomRight.codePoints().mapToObj(c -> String.valueOf((char) c));
            Point bottomRightPoint = Point.deserialize(streamBottomRight);

            String topLeft = m.group(3);
            Stream streamTopLeft = topLeft.codePoints().mapToObj(c -> String.valueOf((char) c));
            Point topLeftPoint = Point.deserialize(streamTopLeft);

            String topRight = m.group(4);
            Stream streamTopRight = topRight.codePoints().mapToObj(c -> String.valueOf((char) c));
            Point topRightPoint = Point.deserialize(streamTopRight);

            if(shapeType.equals("Rectangle")) {
                shape = new Rectangle(bottomLeftPoint, bottomRightPoint, topLeftPoint, topRightPoint);
            }else{
                shape = new Square(bottomLeftPoint, bottomRightPoint, topLeftPoint, topRightPoint);
            }
        }else{
            throw new ShapeException("Invalid points");
        }
        return shape;
    }
    @Override
    public String toString() {
        return "<Rectangle::bottomLeft=" + bottomLeft.toString() + ",bottomRight=" +  bottomRight.toString()
                    + ",topLeft=" + topLeft.toString() + ",topRight=" + topRight.toString() + "::Rectangle>";
    }


    public void renderOrig(Graphics2D graphics) throws ShapeException {
        graphics.setColor(Color.GREEN);
        graphics.fillRect( (int)bottomLeft.getX(), (int)bottomLeft.getY(), (int)getWidth(), (int)getHeight());
        graphics.drawRect((int)bottomLeft.getX(), (int)bottomLeft.getY(), (int)getWidth(), (int)getHeight());
    }

    @Override
    public void render(Graphics2D graphics) throws ShapeException{

        // Compute the left side of the bounding box
        int x = (int)topLeft.getX();

        // Compute the top side of the bounding box
        int y = (int)topLeft.getY();

        // Draw the circle by drawing an oval in a square bounding box
        if(renderDetails != null){
            graphics.translate(renderDetails.getxPosition(),renderDetails.getyPosition());

            graphics.setColor(renderDetails.getLineColor());
            graphics.drawRect((int)bottomLeft.getX(), (int)bottomLeft.getY(), (int)getWidth(), (int)getHeight());
            if(renderDetails.getFillColor() != null) {//make sure there is a fill color
                graphics.setColor(renderDetails.getFillColor());
                graphics.fillRect( (int)bottomLeft.getX(), (int)bottomLeft.getY(), (int)getWidth(), (int)getHeight());
            }

            graphics.translate(-renderDetails.getxPosition(),-renderDetails.getyPosition());
        }else{
            graphics.translate(x, y);

            graphics.setColor(Color.white);//default
            graphics.drawRect(x, y, (int)getWidth(), (int)getHeight());

            graphics.translate(-x, -y);
        }
    }
}
