public class Circle
{
    Point point;
    double r;

    public Circle(int x, int y, double r)
    {
        this.point = new Point(x,y);
        this.r = r;
    }

    public Circle(Point point, double r)
    {
        this.point = new Point(point.x,point.y);
        this.r = r;
    }

    public void enlarge(double f)
    {
        this.r *= f;
    }

    public double getArea()
    {
        //r*r*pi
        return r*r*Math.PI;
    }
}