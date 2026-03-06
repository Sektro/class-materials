public class Point 
{
    //public, "üres" (mappán belül látszódik), protected, private
    int x;
    int y;

    public Point()
    {
        //x = 0;
        //y = 0;
        this(0,0); //ez előtt nem lehet semmi
    }

    public Point(int x, int y)
    {
        //this.x = 0;
        //this.y = 0;
        this.x = x;
        this.y = y;
    }

    public void mirror()
    {
        x = -x;
        y = -y;
    }

    public void mirror(int dx, int dy)
    {
        x = x - dx;
        y = y - dy;
    }

    public void mirror(Point other)
    {
        x = x - other.x;
        y = y - other.y;
    }

    void move(int x, int y)
    {
        //x = x + dx;
        //y = y + dy;
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public double distance(Point other)
    {
        int xDistance = x-other.x;
        int yDistance = y-other.y;
        return Math.sqrt(xDistance*xDistance + yDistance*yDistance);
    }
}