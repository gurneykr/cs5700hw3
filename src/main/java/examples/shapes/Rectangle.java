package examples.shapes;

public class Rectangle {
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

    public double getArea() throws ShapeException{
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
}
