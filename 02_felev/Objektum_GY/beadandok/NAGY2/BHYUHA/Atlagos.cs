using System.Transactions;

namespace Turistak
{
    public class Atlagos : Allapot
    {
        public int allapot;

        //----------INSTANCE----------
        private static Atlagos? instance = null;
        private Atlagos(int allapot)
        {
            this.allapot = allapot;
        }

        public static Atlagos? Instance(int allapot)
        {
            if (instance == null)
                instance = new Atlagos(allapot);
            return instance;
        }
        //

        //----------ACCEPT----------
        public int Accept(Japan j)
        {
            int tenyleges = j.letszam;
            return tenyleges;    
        }
        public int Accept(Nyugati n)
        {
            int tenyleges = n.letszam + (n.letszam / 10);
            return tenyleges;
        }
        public int Accept(Egyeb e)
        {
            int tenyleges = e.letszam + (e.letszam / 10);
            return tenyleges;
        }

        //----------GETTER----------
        public int getAllapot()
        {
            return allapot;
        }
        public string writeAllapot()
        {
            return "Átlagos";
        }

        //----------RONTAS/JAVITAS----------
        public Allapot Rontas(int n, int e)
        {
            allapot = Math.Max(1, allapot - n / 100 - e / 50);

            if (allapot < 34)
                return Lepusztult.Instance(allapot);
            else
                return this;
        }
        public Allapot Javitas(double extrapenz)
        {
            allapot = Math.Min(100, allapot + ((int)(extrapenz / 50000000)));

            if (allapot > 66)
                return Jo.Instance(allapot);
            else
                return this;
        }
    }
}

