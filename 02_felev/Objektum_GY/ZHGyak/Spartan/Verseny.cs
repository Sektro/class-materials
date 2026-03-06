namespace Spartan
{
    public class Verseny
    {
        private List<Kategoria> kat;
        private List<Eredmeny> erk;
        private int ev;
        private string hely;
        public Verseny(int e, string h, Kategoria[] kk)
        {
            if (kk.Length == 0 || ev<=2000) { throw new Exception(); } 
        }


        public Kategoria NepszeruKat()
        {
            Kategoria? k = kat.MaxBy(e => erk.Where(e2 => e2.Kat().Tipus() == e.Tipus()).Sum((e2) => 1));
            return k;
        }
        public void Befuto(int perc, int mp, int rajtszam, Kategoria k)
        {
             (bool l, Kategoria? I2) = kat.Select((e) => e == k);
        }
    }
}
