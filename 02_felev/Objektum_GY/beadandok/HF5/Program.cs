namespace HF5
{
    internal class Program
    {
        public enum Status
        {
            ABNORMAL,
            NORMAL,
            ABORT
        }

        static void Main(string[] args)
        {
            double s = 0.0;
            int db = 0;
            double a, kicsi;
            bool l;
            using StreamReader inFile = new StreamReader(args[0]);
            Status st = Status.ABNORMAL;
            double e = 0.0;
            string input;
            if (!inFile.EndOfStream)
            {
                input = inFile.ReadLine();
                e = double.Parse(input);
                st = Status.NORMAL;
            }
            while (st == Status.NORMAL && e >= 0.0)
            {
                s += e;
                ++db;
                if (!inFile.EndOfStream)
                {
                    input = inFile.ReadLine();
                    e = double.Parse(input);
                    st = Status.NORMAL;
                }
                else
                    st = Status.ABNORMAL;
            }
            if (db != 0)
                a = s / db;
            else
                throw new Exception();

            l = true;
            kicsi = e;
            if (!inFile.EndOfStream)
            {
                input = inFile.ReadLine();
                e = double.Parse(input);
                st = Status.NORMAL;
            }
            while (st == Status.NORMAL)
            {
                l = l && e < 0;
                if (e < kicsi)
                    kicsi = e;

                if (!inFile.EndOfStream)
                {
                    input = inFile.ReadLine();
                    e = double.Parse(input);
                    st = Status.NORMAL;
                }
                else
                    st = Status.ABNORMAL;
            }
            Console.WriteLine(a);
            if (l)
                Console.WriteLine("True");
            else
                Console.WriteLine("False");
            Console.WriteLine(kicsi);
        }
    }
}
