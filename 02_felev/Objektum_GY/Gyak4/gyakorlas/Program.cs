namespace gyakorlas
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Item e = new Item();
            e.data = "asd";
            e.key = 12;
            Map kek = new Map();
            kek.Insert(e);
            Console.WriteLine(kek.Count());
        }
    }
}