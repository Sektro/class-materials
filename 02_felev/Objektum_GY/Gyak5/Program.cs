namespace Gyak5
{
    internal class Program
    {
        public struct Cactus
        {
            public string name;
            public string color;
            public string origin;
        }

        public enum Status
        {
            ABNORMAL,
            NORMAL,
            ABORT
        }

        static void Main(string[] args)
        {   
            
            // Feladat 1
            List<string> red = new List<string>();
            List<string> mexican = new List<string>();
            using StreamReader inFile = new StreamReader(args[0]); //cmd-ben: "Gyak5.exe" test.txt // using csak az olvasandó részét nyitja meg a fájlnak (nem kell sr.Close())
            Status st = Status.ABNORMAL;
            Cactus e = new Cactus();
            string[] input;
            if (!inFile.EndOfStream)
            {
                input = inFile.ReadLine().Split();
                e.name = input[0];
                e.color = input[1];
                e.origin = input[2];
                st = Status.NORMAL;
            }
            while (st == Status.NORMAL)
            {
                if (e.color == "piros")
                    red.Add(e.name);
                if (e.origin == "Mexikó")
                    mexican.Add(e.name);
                if (!inFile.EndOfStream)
                {
                    input = inFile.ReadLine().Split();
                    e.name = input[0];
                    e.color = input[1];
                    e.origin = input[2];
                    st = Status.NORMAL;
                }
                else
                    st = Status.ABNORMAL;
            }
            foreach (string name in red)
                Console.Write($"{name} ");
            Console.WriteLine();
            foreach (string name in mexican)
                Console.Write($"{name} ");
            
            /*
            int e;
            int m = 0;
            bool l = true;
            using StreamReader inFile = new StreamReader(args[0]); //test2.txt
            while (!inFile.EndOfStream)
            {
                e = int.Parse(inFile.ReadLine());
                if (e > m)
                    m = e;
                l = l && e % 2 == 0;
            }
            Console.WriteLine($"{m} {l}");
            */
            //Beadandóban kimenet soronként
        }
    }
}