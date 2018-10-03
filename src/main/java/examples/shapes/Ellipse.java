package examples.shapes;
import java.lang.Math;

public class Ellipse {

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


}
