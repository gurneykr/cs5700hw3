package examples.shapes;

import java.beans.Transient;

public class Validator {
    public static void validateDouble(double value, String errorMessage) throws ShapeException {
        if (Double.isInfinite(value) || Double.isNaN(value))
            throw new ShapeException(errorMessage);
    }

    public static void validatePositiveDouble(double value, String errorMessage) throws ShapeException {
        validateDouble(value, errorMessage);
        if (value<0)
            throw new ShapeException(errorMessage);
    }

    public static void validatePositivePoint(Point value, String errorMessage) throws ShapeException {
        validatePositiveDouble(value.getX(), errorMessage);
        validatePositiveDouble(value.getY(), errorMessage);
    }

    public static void validateRectangle(Point bottomLeft, Point bottomRight, Point topLeft, Point topRight) throws ShapeException {
        //check if points are not null
        if(bottomLeft == null  || bottomRight == null || topLeft == null || topRight == null ){
            throw new ShapeException("Invalid Rectangle, null points not allowed");
        }
        //validate if the points are positive
        validatePositivePoint(bottomLeft, "No negative points");
        validatePositivePoint(bottomRight, "No negative points");
        validatePositivePoint(topLeft, "No negative points");
        validatePositivePoint(topLeft, "No negative points");

        //check for right angles
        validateRightAngles(bottomLeft, bottomRight, topLeft, topRight);

    }

    public static void validateRightAngles(Point bottomLeft, Point bottomRight, Point topLeft, Point topRight) throws ShapeException {
        Line bottomLine = new Line(bottomLeft, bottomRight);
        Line topLine = new Line(topLeft, topRight);
        Line leftLine = new Line(topLeft,  bottomLeft);
        Line rightLine = new Line(topRight, bottomRight);

        double bottomLineLength = bottomLine.computeLength();
        double topLineLength = topLine.computeLength();
        double leftLineLength = leftLine.computeLength();
        double rightLineLength = rightLine.computeLength();


        Line diagonal = new Line(bottomLeft, topRight);
        double diagonalLength = diagonal.computeLength();

        //check if sqrt(a^2 + b^2 = sqrt(c^2)
        if( Math.sqrt( (Math.pow(leftLineLength,2)+ Math.pow(topLineLength,2)) )!= diagonalLength){
            throw new ShapeException("Not a right angle");
        }

        //check if sqrt(a^2 + b^2) = sqrt(c^2)
        if( Math.sqrt( (Math.pow(bottomLineLength,2)+ Math.pow(rightLineLength,2)) )!= diagonalLength){
            throw new ShapeException("Not a right angle");
        }

    }

    public static void validateEqualSides(Point bottomLeft, Point bottomRight, Point topLeft, Point topRight)throws ShapeException{
        Line bottomLine = new Line(bottomLeft, bottomRight);
        Line topLine = new Line(topLeft, topRight);
        Line leftLine = new Line(topLeft,  bottomLeft);
        Line rightSide = new Line(topRight, bottomRight);

        double bottomLineLength = bottomLine.computeLength();
        double topLineLength = topLine.computeLength();
        double leftLineLength = leftLine.computeLength();
        double rightLineLength = rightSide.computeLength();

        if( !(bottomLineLength == topLineLength && leftLineLength == rightLineLength && bottomLineLength == leftLineLength) ){
            throw new ShapeException("Sides are not equal length");
        }
    }


}
