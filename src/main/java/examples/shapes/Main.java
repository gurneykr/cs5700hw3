package examples.shapes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.io.IOException;

public class Main {
//    public static void main(String args[]){
//        System.out.println("Hello shapes");
//        try{
//            Point p1 = new Point(11, 22);
//            Stream s1 = p1.serialize();
//            Point p2 = (Point)p1.deserialize(s1);
//            System.out.println("Deserialized point =>" + p2.toString());
//        }catch(ShapeException e){
//            e.printStackTrace();
//        }
//    }

//    public static void main(String args[]){
//        System.out.println("Hello shapes");
//        try{
//            Point topLeft = new Point(0, 1);
//            Point topRight = new Point(1, 1);
//            Point bottomLeft = new Point(0, 0);
//            Point bottomRight = new Point(1, 0);
//
//            Rectangle rectangle = new Rectangle(bottomLeft,bottomRight, topLeft, topRight);
//
//
//            Stream s1 = rectangle.serialize();
//            Rectangle p2 = Rectangle.deserialize(s1);
//            System.out.println("Deserialized rectangle =>" + p2.toString());
//        }catch(ShapeException e){
//            e.printStackTrace();
//        }
//    }

//    public static void main(String args[]){
//        System.out.println("Hello lines");
//        try{
//            Point point1 = new Point(0, 1);
//            Point point2 = new Point(1, 1);
//
//            Line line = new Line(point1, point2);
//
//            Stream s1 = line.serialize();
//            Line p2 = Line.deserialize(s1);
//            System.out.println("Deserialized line =>" + p2.toString());
//        }catch(ShapeException e){
//            e.printStackTrace();
//        }
//    }

//

//    public static void main(String args[]){
//        System.out.println("Hello Ellipse");
//        try{
//            Point center = new Point(0, 1);
//            double a = 5;
//            double b = 3;
//            Ellipse ellipse = new Ellipse(center, a, b);
//
//            Stream s1 = ellipse.serialize();
//            Ellipse p2 = Ellipse.deserialize(s1);
//            System.out.println("Deserialized Ellipse =>" + p2.toString());
//        }catch(ShapeException e){
//            e.printStackTrace();
//        }
//    }

    public static void main(String args[]){
        System.out.println("Hello Circle");
        List<Shape> shapeList = new ArrayList();

        try {
            Point center = new Point(0, 1);
            double radius = 5;
            Circle circle = new Circle(center, radius);
            shapeList.add(circle);

            Point a = new Point(0, 0);
            Point b = new Point(1, 0);
            Point c = new Point(0, 1);
            Triangle triangle = new Triangle(a, b, c);
            shapeList.add(triangle);

            Point topLeft = new Point(0, 1);
            Point topRight = new Point(1, 1);
            Point bottomLeft = new Point(0, 0);
            Point bottomRight = new Point(1, 0);
            Rectangle rectangle = new Rectangle(bottomLeft, bottomRight, topLeft, topRight);
            shapeList.add(rectangle);

            Point topLeft2 = new Point(0, 1);
            Point topRight2 = new Point(1, 1);
            Point bottomLeft2 = new Point(0, 0);
            Point bottomRight2 = new Point(1, 0);
            Square square = new Square(bottomLeft2, bottomRight2, topLeft2, topRight2);
            shapeList.add(square);

            Point center2 = new Point(0, 1);
            double a2 = 5;
            double b2 = 3;
            Ellipse ellipse = new Ellipse(center2, a2, b2);
            shapeList.add(ellipse);

            Point point1 = new Point(0, 1);
            Point point2 = new Point(1, 1);
            Line line = new Line(point1, point2);
            shapeList.add(line);

        }catch(ShapeException e){
                e.printStackTrace();
        }
        writeShapesToFile(shapeList,"test.txt");
        readFromFile("test.txt");
    }

    public static void writeShapesToFile(List<Shape> shapeList, String fileName){
        OutputStream fop = null;
        File file;

        try {
            file = new File(fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            fop = new FileOutputStream(file);

            for (Shape shape:shapeList) {
                fop.write(shape.toString().getBytes());
                fop.write("\n".getBytes());
            }
            fop.flush();
            fop.close();
            fop = null;
            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Shape> readFromFile(String fileName){
        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String stringShape;
            while ((stringShape = br.readLine()) != null)
                System.out.println(stringShape);

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
