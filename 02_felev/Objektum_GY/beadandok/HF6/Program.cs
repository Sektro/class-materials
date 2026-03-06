using System.Diagnostics.CodeAnalysis;

namespace HF6
{
    internal class Program
    {
        public enum Status
        {
            ABNORMAL,
            NORMAL,
            ABORT
        }
        public struct Számla
        {
            public string név;
            public List<Áru> lista;
        }
        public struct Áru
        {
            public string cikkszám;
            public int ár;
        }

        static void Main(string[] args)
        {
            int bevét = 0;
            Áru seged;
            Számla sz;
            sz.lista = new List<Áru>();
            using StreamReader inFile = new StreamReader(args[0]);
            Status st = Status.ABNORMAL;
            string[] input;
            if (!inFile.EndOfStream)
            {
                input = inFile.ReadLine().Split();
                sz.név = input[0];
                sz.lista.Clear();
                for (int i = 1; i < input.Length; i += 2)
                {
                    seged.cikkszám = input[i];
                    seged.ár = int.Parse(input[i+1]);
                    sz.lista.Add(seged);
                }
                st = Status.NORMAL;
            }
            while (st == Status.NORMAL)
            {
                bevét = bevét + össz(sz.lista);
                if (!inFile.EndOfStream)
                {
                    input = inFile.ReadLine().Split();
                    sz.név = input[0];
                    sz.lista.Clear();
                    for (int i = 1; i < input.Length; i += 2)
                    {
                        seged.cikkszám = input[i];
                        seged.ár = int.Parse(input[i + 1]);
                        sz.lista.Add(seged);
                    }
                    st = Status.NORMAL;
                }
                else
                    st = Status.ABNORMAL;
            }
            Console.WriteLine(bevét);
        }

        static int össz(List<Áru> x)
        {
            int sum = 0;
            foreach (Áru e in x)
                sum = sum + e.ár;
            return sum;
        }
    }
}
