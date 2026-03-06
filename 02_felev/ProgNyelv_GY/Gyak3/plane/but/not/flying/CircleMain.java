package plane.but.not.flying;

import plane.PublicCircle;
import plane.Circle;

public class CircleMain
{
    public static void main(String[] args)
    {
        PublicCircle circle = new PublicCircle();
        System.out.println(circle.getArea());
        circle.x = 5;
        circle.y = 2;
        circle.r = 10;
        System.out.println(circle.getArea());

        Circle circle2 = new Circle();
        System.out.println(circle2.getArea());
        circle2.setX(5);
        circle2.setY(2);
        circle2.setR(10);
        System.out.println(circle2.getArea());
    }
}

// futtatás pontokkal is működik: 
// javac plane/but/not/flying/CircleMain.java
// java plane.but.not.flying.CircleMain  vagy  java plane/but/not/flying/CircleMain 