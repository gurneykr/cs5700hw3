package examples.shapes;

import java.util.stream.Stream;

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

    public static void main(String args[]){
        System.out.println("Hello shapes");
        try{
            Point topLeft = new Point(0, 1);
            Point topRight = new Point(1, 1);
            Point bottomLeft = new Point(0, 0);
            Point bottomRight = new Point(1, 0);

            Rectangle rectangle = new Rectangle(bottomLeft,bottomRight, topLeft, topRight);


            Stream s1 = rectangle.serialize();
            Rectangle p2 = Rectangle.deserialize(s1);
            System.out.println("Deserialized rectangle =>" + p2.toString());
        }catch(ShapeException e){
            e.printStackTrace();
        }
    }
}
