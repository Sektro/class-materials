public class Distance
{
    public static void main(String[] args)
    {
        if (args.length % 2 != 0)
        {
            return;
        }
        double distance = 0.0;
        if (args.length < 4)
        {
            System.out.println(distance);
            return;
        }
        //1 2 4 6 7 6
        //1 2 4 6
        //4 6 7 6
        Point pont;
        Point pont2;
        for (int i = 0; i < args.length-2; i += 2)
        {
            pont = new Point(intArgs[i], intArgs[i+1]);
            int x2 = Integer.parseInt(args[i+2]);
            int y2 = Integer.parseInt(args[i+3]);
            pont2 = new Point(x2, y2);
            distance += pont.distance(pont2);
        }
    }
}