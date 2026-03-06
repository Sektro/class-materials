namespace Gyak6
{
    internal class Program
    {
        static void Main(string[] args)
        {
            ElectronicsEnumerable test = new IntEnumerable("test.txt");
            int db_pos = 0;
            int db_neg = 0;
            bool neg = false;
            foreach (int e in test)
            {
                if (e < 0)
                    neg = true;
                if (!neg && e % 2 == 0)
                    ++db_pos;
                else if (e % 2 == 0)
                    ++db_neg;
            }
            /*
             * Példányosít (megnyitja a fájlt)
             * MoveNext() belvasás  művelete
             * e := Current lekéri nekünk a jelenlegi sort
             * Dolgozunk vele
             */
            Console.WriteLine(db_pos);
            Console.WriteLine(db_neg);

            test.ChangePath("test2.txt");

            db_pos = 0;
            db_neg = 0;
            neg = false;
            foreach (int e in test)
            {
                if (e < 0)
                    neg = true;
                if (!neg && e % 2 == 0)
                    ++db_pos;
                else if (e % 2 == 0)
                    ++db_neg;
            }
            Console.WriteLine($"2.:{db_pos}");
            Console.WriteLine($"2.:{db_neg}");

            ElectronicsEnumerable test2 = new ElectronicsEnumerable("test2.txt");
            foreach (Receipt e in test2)
            {
                Console.WriteLine(e.name);
            }
        }
    }
}