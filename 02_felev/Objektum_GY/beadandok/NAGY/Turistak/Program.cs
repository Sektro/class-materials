using System.Text.RegularExpressions;

namespace Turistak
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
            Varos varos;
            (int allapot, int japan, int nyugati, int egyeb, double bevetel) = (0,0,0,0,0);
            using StreamReader inFile = new StreamReader(args[0]);
            Status st = Status.ABNORMAL;
            string[] input;
            if (!inFile.EndOfStream)
            {
                input = inFile.ReadLine().Split();
                allapot = Convert.ToInt32(input[0]);
                st = Status.NORMAL;
            }
            while (st == Status.NORMAL)
            {

                if (!inFile.EndOfStream)
                {
                    input = inFile.ReadLine().Split();
                    japan = Convert.ToInt32(input[0]);
                    nyugati = Convert.ToInt32(input[1]);
                    egyeb = Convert.ToInt32(input[2]);

                    varos = new Varos(allapot, japan, nyugati, egyeb);
                    (japan, nyugati, egyeb) = varos.Tenyleges();

                    bevetel = varos.Bevetel();


                    st = Status.NORMAL;
                }
                else
                    st = Status.ABNORMAL;
            }
        }
    }
}
