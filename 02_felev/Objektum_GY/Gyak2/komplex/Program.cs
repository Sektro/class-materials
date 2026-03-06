namespace komplex
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Complex a = new Complex(1, 2);
            Complex b = new Complex(1.55555, 2.55555);
            Console.WriteLine(a); // Console.WriteLine(a.ToString());
            Console.WriteLine(b);
            Console.WriteLine(a+b);
            Console.WriteLine(a-b);
            Console.WriteLine(a*b);
            Console.WriteLine(a/b);
        }
    }
}