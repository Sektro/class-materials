package famous.sequence;

public class TriangularNumbers
{
    public static int getTriangularNumber(int n)
    {
        int retval = 0;
        for (int i = 1; i <= n; ++i)
        {
            retval += i;
        }
        return retval;
    }

    public static int getTriangularNumberAlternative(int n)
    {
        return ((n*(n+1))/2);
    }
}