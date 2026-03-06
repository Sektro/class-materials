package famous.sequence;

public class Fibonacci
{
    //default konstruktor ==> nincs konstruktor
    //static: USABLE_WITHOUT_INSTANCE
    public static int fib(int n)
    {
        return n == 1 ? 0 : n == 2 ? 1 : fib(n-1) + fib(n-2);
    }
}