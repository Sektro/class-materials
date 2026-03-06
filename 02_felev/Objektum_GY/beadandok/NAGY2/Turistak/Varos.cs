namespace Turistak
{
    public class Varos
    {
        public Allapot allapot;
        public List<int> allapotok;
        public double jovedelem;
        public int japanok;
        public int nyugatiak;
        public int egyebek;

        public Varos(int allapotpont)
        {
            allapot = Jo.Instance(allapotpont);
            if (allapotpont >= 1 && allapotpont <= 33)
                allapot = Lepusztult.Instance(allapotpont);
            if (allapotpont >= 34 && allapotpont <= 66)
                allapot = Atlagos.Instance(allapotpont);
            if (allapotpont >= 67 && allapotpont <= 100)
                allapot = Jo.Instance(allapotpont);
            if (allapotpont < 1 || allapotpont > 100)
                throw new Exception();  
            jovedelem = 0;
            allapotok = new List<int>();
            allapotok.Add(allapotpont);
            japanok = 0;
            nyugatiak = 0;
            egyebek = 0;
        }


        //----------ACCEPT----------
        public void Accept(Japan j)
        {
            japanok = allapot.Accept(j);
            jovedelem += Bevetel(japanok);
        }
        public void Accept(Nyugati n)
        {
            nyugatiak = allapot.Accept(n);
            jovedelem += Bevetel(nyugatiak);
        }
        public void Accept(Egyeb e)
        {
            egyebek = allapot.Accept(e);
            jovedelem += Bevetel(egyebek);
        }

        public void AcceptAll()
        {
            allapot = allapot.Rontas(nyugatiak,egyebek);
            this.Javitas();
        }

        //----------GETTER----------
        public int getAllapot()
        {
            return allapot.getAllapot();
        }
        public string writeAllapot()
        {
            return allapot.writeAllapot();
        }
        public int getJapan()
        {
            return japanok;
        }
        public int getNyugati()
        {
            return nyugatiak;
        }
        public int getEgyeb()
        {
            return egyebek;
        }

        //----------BEVETEL----------
        public static double Bevetel(int letszam)
        {
            return ((double)letszam) * 100000;
        }
        public void Javitas()
        {
            if (jovedelem > 20000000000)
                allapot = allapot.Javitas(jovedelem - 20000000000);
        }

        //----------EXTRA----------
        public int LegjobbAllapot()
        {
            return allapotok.IndexOf(allapotok.Max()) + 1;
        }
        public void UjEv()
        {
            allapotok.Add(allapot.getAllapot());
            jovedelem = 0;
        }
    }
}

