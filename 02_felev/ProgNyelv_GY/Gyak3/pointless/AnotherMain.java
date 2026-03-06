package pointless;

//import point2d.*; //import point2d.Point

import point2d.alibi.PointAlibi;

public class AnotherMain
{
    public static void main(String[] args)
    {
        point2d.Point point = new point2d.Point(); // ha nincs importálva
        Point alibi = new Point(); 
        System.out.println("X: " + point.x + " Y: " + point.y);
    }
}