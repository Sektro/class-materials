namespace Gyak3
{
    public class LTM
    {
        private double[] x;
        private int dim;

        public LTM(int n)
        {
            double[] x = new double[n * (n + 1) / 2]; //UML osztálydiagrammban rossz
            dim = n;
        }

        public double this[int i, int j]
        {
            get 
            { 
                if (i < 0 || i >=dim || j < 0 || j >= dim)
                    throw new IndexOutOfRangeException();
                if (i >= j)
                    return x[Index(i, j)];
                return 0;
            }
            set
            {
                if (i < 0 || i >= dim || j < 0 || j >= dim)
                    throw new IndexOutOfRangeException();
                if (i >= j)
                    x[Index(i, j)] = value; //value = valami_érték
                else
                    throw new LTMException("Saját üzenet");
            }
        }

        /*
         * set:
         *         LTMPéldány[2, 3] = valami_érték
         * get:
         *         if (LTMPéldány[2, 3])
         *         valami_változó = LTMPéldány[2, 3]
         *
         * Ha nem = van utána, akkor get, egyébként set 
        */

        public static LTM Add(LTM a, LTM b)
        {
            if (a.dim != b.dim)
                throw new Exception();
            LTM c = new LTM(a.dim);
            for (int i = 0; i < c.x.Length; ++i)
            {
                c.x[i] = a.x[i] + b.x[i];
            }
            return c;
        }

        public static LTM Multiply(LTM a, LTM b)
        {
            if (a.dim != b.dim)
                throw new Exception();
            LTM c = new LTM(a.dim);
            for (int i = 0; i < c.dim; ++i)
                for (int j = 0; j < i; ++j)
                {
                    c.x[Index(i, j)] = 0;
                    for (int k = j; k < i; ++k)
                        c.x[Index(i, j)] += a.x[Index(i, k)] * b.x[Index(k, j)];
                }
            return c;
        }

        private static int Index(int i, int j)
        {
            return j + i * (i - 1) / 2;
        }
    }
}
