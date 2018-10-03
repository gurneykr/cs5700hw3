package examples.shapes;

import java.util.stream.Stream;

public class Main {
    public static void main(String args[]){
        System.out.println("Hello shapes");
        try{
            Point p1 = new Point(1.1, 2.2);
            Stream s1 =p1.serialize();
            Point p2 = (Point)p1.deserialize(s1);
            System.out.println("Deserialized point =>" + p2.toString());
        }catch(ShapeException e){
            e.printStackTrace();
        }
    }
}
