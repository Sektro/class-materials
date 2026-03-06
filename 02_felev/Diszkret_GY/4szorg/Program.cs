namespace _4szorg
{
    public struct rendezettParok
    {
        public int x; public int y;
    }

    internal class Program
    {
        static void Main(string[] args)
        {
            int[] N = { 3, 4, 5, 6 };

            //3
            int n = N[0];
            rendezettParok[,] R = relaciok(n);
            int s = ekvivalencia(R, n);
            Console.WriteLine("n=3: ",s);
            //4
            n = N[1];
            R = relaciok(n);
            s = ekvivalencia(R, n);
            Console.WriteLine("n=4: ", s);
            //5
            n = N[2];
            R = relaciok(n);
            s = ekvivalencia(R, n);
            Console.WriteLine("n=5: ", s);
            //6
            //n = N[3];
            //R = relaciok(n);
            //s = ekvivalencia(R, n);
            //Console.WriteLine("n=6: ", s);
            //n=6-ra nem elég nagy az int mérete, ezért nem működik

            //kiiras(R,n);
        }

        static rendezettParok[,] relaciok(int n)
        {
            rendezettParok[,] R = new rendezettParok[Convert.ToInt32(Math.Pow(2, (n * n))), n * n];

            bool[,] bit = new bool[Convert.ToInt32(Math.Pow(2, (n*n))), (n*n)];
            for (int i = 0; i < Math.Pow(2, (n*n)); ++i)
            {
                for (int j = 0; j < (n*n); ++j)
                {
                    bit[i, j] = ((i >> j) & 1) == 1;
                }
            }

            rendezettParok[] pelda = new rendezettParok[n * n];
            for (int i = 1; i <= n; ++i)
            {
                for (int j = 1; j <= n; ++j)
                {
                    pelda[(i - 1) * n + (j - 1)].x = i;
                    pelda[(i - 1) * n + (j - 1)].y = j;
                }
            }

            for (int i = 0; i < Math.Pow(2, (n * n)); ++i)
            {
                for (int j = 0; j < (n * n); ++j)
                {
                    if (bit[i, j])
                        R[i, j] = pelda[j];
                    else
                    {
                        R[i, j].x = 0;
                        R[i, j].y = 0;
                    }
                }
            }

            return R;
        }

        static void kiiras(rendezettParok[,] R, int n)
        {
            for (int i = 0; i < Math.Pow(2, (n * n)); ++i)
            {
                Console.Write("{");
                for (int j = 0; j < (n * n); ++j)
                {
                    if (R[i, j].x == 0 && R[i, j].y == 0)
                        Console.Write("");
                    else if (j == (n * n) - 1)
                        Console.Write("({0},{1})", R[i, j].x, R[i, j].y);
                    else
                        Console.Write("({0},{1}), ", R[i, j].x, R[i, j].y);
                }
                Console.WriteLine("}");
            }
        }
        
        static List<int> reflexiv(rendezettParok[,] R, int n)
        {
            List<int> jok = new List<int>();
            bool jo = true;
            bool josor = true;
            int k;

            for (int i = 0; i < Math.Pow(2, (n * n)); ++i)
            {
                josor = true;
                for (int j = 0; j < (n * n) && josor == true; ++j)
                {
                    if (!(R[i, j].x == 0 && R[i, j].y == 0))
                    {
                        jo = false;
                        k = 0;
                        while (k < (n * n) && jo == false)
                        {
                            if (R[i, j].x == R[i, k].y && R[i, j].x == R[i, k].x)
                            {
                                jo = true;
                            }
                            ++k;
                        }
                        if (!jo)
                            josor = false;
                    }
                }
                if (josor)
                    jok.Add(i);
            }

            return jok;
        }

        static List<int> szimmetrikus(rendezettParok[,] R, int n)
        {
            List<int> jok = new List<int>();
            bool jo = true;
            bool josor = true;
            int k;

            for (int i = 0; i < Math.Pow(2, (n * n)); ++i)
            {
                josor = true;
                for (int j = 0; j < (n * n) && josor == true; ++j)
                {
                    if (!(R[i, j].x == 0 && R[i, j].y == 0))
                    {
                        jo = false;
                        k = 0;
                        while (k < (n * n) && jo == false)
                        {
                            if (R[i, j].x == R[i, k].y && R[i, j].y == R[i, k].x)
                            {
                                jo = true;
                            }
                            ++k;
                        }
                        if (!jo)
                            josor = false;
                    }
                }
                if (josor)
                    jok.Add(i);
            }

            return jok;
        }

        static List<int> tranzitiv(rendezettParok[,] R, int n)
        {
            List<int> jok = new List<int>();
            bool jo = true;
            bool josor = true;
            int k, h;

            for (int i = 0; i < Math.Pow(2, (n * n)); ++i)
            {
                josor = true;
                for (int j = 0; j < (n * n) && josor == true; ++j)
                {
                    if (!(R[i, j].x == 0 && R[i, j].y == 0))
                    {
                        jo = true;
                        k = 0;
                        while (k < (n * n) && jo == true)
                        {
                            if (R[i, j].y == R[i, k].x)
                            {
                                jo = false;
                                h = 0;
                                while (h < (n * n) && jo == false)
                                {
                                    if (R[i, h].x == R[i, j].x && R[i, h].y == R[i, k].y)
                                        jo = true;
                                    ++h;
                                }
                            }
                            ++k;
                        }
                        if (!jo)
                            josor = false;
                    }
                }
                if (josor)
                    jok.Add(i);
            }

            return jok;
        }

        static int ekvivalencia(rendezettParok[,] R, int n)
        {
            int s = 0;

            List<int> sz = szimmetrikus(R, n);
            List<int> r = reflexiv(R, n);
            List<int> t = tranzitiv(R, n);

            int[] szim = new int[sz.Count];
            foreach (int i in sz)
            {
                foreach (int j in r)
                {
                    foreach (int k in t)
                    {
                        if (i == j && j == k)
                            ++s;
                    }
                }
            }

            return s;
        }

    }
}
