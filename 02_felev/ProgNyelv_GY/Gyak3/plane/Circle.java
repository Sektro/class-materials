package plane;


public class Circle
{
    private double x;
    private double y;
    private double r;

    public Circle()
    {
        /*
        x = 0;
        y = 0;
        r = 1;
        itt azonban nincs hibavizsgálat ==> pontlevonás
        */
        
        setX(0);
        setY(0);
        setR(1);
        
    }

    public double getArea()
    {
        return r*r*Math.PI;
    }

    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getR()
    {
        return r;
    }

    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public void setR(double r)
    {
        if (r < 0.0)
        {
            throw new IllegalArgumentException("R cannot be non positive"); //Exception();
        }
        this.r = r;
    }
}