using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Turistak
{
    public enum Minoseg
    {
        JO,
        ATLAGOS,
        LEPUSZTULT
    }

    public class Varos
    {
        public int allapot;
        public Minoseg minoseg;
        public int osszturista;
        public int japan;
        public int nyugati;
        public int egyeb;

        public Varos(int allapot, int japan, int nyugati, int egyeb)
        {
            this.allapot = allapot;
            this.japan = japan;
            this.nyugati = nyugati;
            this.egyeb = egyeb;
            allapot = japan + nyugati + egyeb;
            minoseg = new Minoseg();

            if (allapot <= 33)
            {
                minoseg = Minoseg.LEPUSZTULT;
            }
            else if (allapot >= 34 && allapot <= 67)
            {
                minoseg = Minoseg.ATLAGOS;
            }
            else
            {
                minoseg = Minoseg.JO;
            }
        }

        public double Bevetel() //2.
        {
            double bevetel = (double)osszturista * 100000;
            double javitas = 0;
            if (bevetel > 20000000000)
                javitas = (bevetel - 20000000000)/50000000;
            allapot = allapot + (int)javitas;
            return bevetel;
        }
        public (int, int, int) Tenyleges() //1.
        {
            switch (minoseg)
            {
                case Minoseg.LEPUSZTULT:
                    japan = 0;
                    return (japan, nyugati, egyeb);
                case Minoseg.ATLAGOS:
                    nyugati = nyugati + nyugati / 10;
                    egyeb = egyeb + egyeb / 10;
                    return (japan, nyugati, egyeb);
                case Minoseg.JO:
                    japan = japan + japan / 5;
                    nyugati = nyugati + (nyugati / 100) * 30;
                    return (japan, nyugati, egyeb);
            }
            return (0, 0, 0);
        }
        public void AllapotValtozas() //3.
        {
            allapot = allapot - (nyugati / 100) - (egyeb / 50);
        }
    }
}
