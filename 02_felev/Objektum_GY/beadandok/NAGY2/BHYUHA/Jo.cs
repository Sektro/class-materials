using System.Transactions;

namespace Turistak
{
    public class Jo : Allapot
    {
        public int allapot;

        //----------INSTANCE----------
        private static Jo? instance = null;
        private Jo(int allapot)
        {
            this.allapot = allapot;
        }

        public static Jo? Instance(int allapot)
        {
            if (instance == null)
                instance = new Jo(allapot);
            return instance;
        }
        //

        //----------ACCEPT----------
        public int Accept(Japan j)
        {
            int tenyleges = j.letszam + (j.letszam / 5);
            return tenyleges;
        }
        public int Accept(Nyugati n)
        {
            int tenyleges = n.letszam + ((n.letszam / 10) * 3);
            return tenyleges;
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
            return "Jó";
        }

        //----------RONTAS/JAVITAS----------
        public Allapot Rontas(int n, int e)
        {
            allapot = Math.Max(1, allapot - n / 100 - e / 50);

            if (allapot < 34)
                return Lepusztult.Instance(allapot);
            if (allapot < 67)
                return Atlagos.Instance(allapot);
            else
                return this;
        }
        public Allapot Javitas(double extrapenz)
        {
            allapot = Math.Min(100, allapot + ((int)(extrapenz / 50000000)));
            return this;
        }
    }
}


