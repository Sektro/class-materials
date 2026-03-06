namespace gyakorlas1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int[] A = { 86, 29, 90, 31, 47, 91, 20, 40 };
            for (int i = 0; i < A.Length; i++)
            {
                Console.Write("{0} ", A[i]);
            }
            Console.WriteLine();
            A = naiveInsertionSort(A, A.Length);
            for (int i = 0; i < A.Length; i++)
            {
                Console.Write("{0} ", A[i]);
            }
        }

        
        static int[] naiveInsertionSort(int[] A, int n)
        {
            int j;
            int ossz = 0;
            int csere = 0;
            bool volt = true;
            for (int i = 1; i < n; ++i)
            {
                ++ossz;
                j = i;
                while (j > 0 && A[j - 1] > A[j])
                {
                    if (!volt)
                        ++ossz;
                    ++csere;
                    swap(ref A[j-1], ref A[j]);
                    j = j - 1;
                    volt = false;
                }
                volt = true;
            }
            Console.WriteLine("Összehasonlítások: {0}, Cserék: {1}",ossz,csere);

            return A; 
        }
        
        static void swap(ref int a, ref int b)
        {
            int c = a;
            a = b;
            b = c;
        }
    }
}
