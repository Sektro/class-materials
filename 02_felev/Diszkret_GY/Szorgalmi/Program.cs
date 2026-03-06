namespace Szorgalmi
{
    internal class Program
    {
        // 1 1 0 1
        static bool fas(bool[] v)
        {
            return v[0];
        }
        static void Main(string[] args)
        {
            int n = 0;
            Console.Write("Kombinációk száma: ");

            n = int.Parse(Console.ReadLine());

            bool[,] kombinaciok = new bool[(int)Math.Pow(2, n), n];
            for (int i = 0; i < Math.Pow(2, n); i++) 
            {
                for (int j = 0; j < n; j++)
                {
                    kombinaciok[i, j] = ((i >> j) & 1) == 1;
                }
            }

            bool teljesitheto = false;
            for (int i = 0; i < Math.Pow(2, n) && !teljesitheto; i++)
            {
                bool[] tomb = new bool[4];
                for (int j = 0; j < n; j++)
                {
                    tomb[j] = kombinaciok[i, j];

                    
                }
                Console.WriteLine(i + 1);
                teljesitheto = fas(tomb);

            }

            Console.WriteLine(teljesitheto ? "Fasza" : "nem");
        }
    }
}
