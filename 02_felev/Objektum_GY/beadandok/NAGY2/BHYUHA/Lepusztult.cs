using System.Transactions;

namespace Turistak
{
    public class Lepusztult : Allapot
    {
        public int allapot;

        //----------INSTANCE----------
        private static Lepusztult? instance = null;
        private Lepusztult(int allapot)
        {
            this.allapot = allapot;
        }

        public static Lepusztult? Instance(int allapot)
        {
            if (instance == null)
                instance = new Lepusztult(allapot);
            return instance;
        }
        //

        //----------ACCEPT----------
        public int Accept(Japan j)
        {
            int tenyleges = 0;
            return tenyleges;
        }
        public int Accept(Nyugati n)
        {
            int tenyleges = n.letszam;
            return  tenyleges;
        }
        public int Accept(Egyeb e)
        {
            int tenyleges = e.letszam;
            return tenyleges;
        }

        //----------GETTER----------
        public int getAllapot()
        {
            return allapot;
        }
        public string writeAllapot()
        {
            return "Lepusztult";
        }

        //----------RONTAS/JAVITAS----------
        public Allapot Rontas(int n, int e)
        {
            allapot = Math.Max(1,allapot - n / 100 - e / 50);
            return this;
        }
        public Allapot Javitas(double extrapenz)
        {
            allapot = Math.Min(100, allapot + ((int)(extrapenz / 50000000)));

            if (allapot > 66)
                return Jo.Instance(allapot);
            if (allapot > 33)
                return Atlagos.Instance(allapot);
            else
                return this;
        }
    }
}
