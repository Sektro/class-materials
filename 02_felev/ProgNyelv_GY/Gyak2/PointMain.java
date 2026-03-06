public class PointMain
{
    public static void main(String[] args)
    {
        Point point;
        point = new Point(6,9);
        //point.x = 6;
        //point.y = 9;

        Point point2;
        point2 = new Point();

        Point point3;
        point3 = new Point(1,2);


        System.out.println("X: " + point.x + " Y: " + point.y);
        point.move(2,2);
        System.out.println("X: " + point.x + " Y: " + point.y);
        System.out.println();

        System.out.println("point1: X: " + point.x + " Y: " + point.y);
        System.out.println("point2: X: " + point2.x + " Y: " + point2.y);
        System.out.println("point3: X: " + point3.x + " Y: " + point3.y);
        point.mirror();
        point2.mirror(1,1);
        point3.mirror(point);
        System.out.println("Mirrored:");
        System.out.println("point1: X: " + point.x + " Y: " + point.y);
        System.out.println("point2: X: " + point2.x + " Y: " + point2.y);
        System.out.println("point3: X: " + point3.x + " Y: " + point3.y);
        System.out.println();

        System.out.println("Distance:");
        System.out.println(point.distance(point3));
        System.out.println("^point1 and point3");
        System.out.println();

        Circle circle = new Circle(point, 5);
        System.out.println("Kozeppont:");
        System.out.println(circle.point.x + " " + circle.point.y);
        point.x = 80;
        point.y = -60;
        System.out.println(circle.point.x + " " + circle.point.y); // érték változik, mert csak a referenciáját adjuk át, tehát pointerként működik
        System.out.println("Terulet: " + circle.getArea());
        circle.enlarge(2);
        System.out.println("Novelt terulet: " + circle.getArea());
        System.out.println();
    } 
}