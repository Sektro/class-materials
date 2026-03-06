namespace Spartan
{
    public class Eredmeny
    {
        private Kategoria kat;
        private int perc;
        private int mp;
        private int id;
        public Eredmeny(int p, int mp, int id, Kategoria k)
        {
            if (p<=0 || mp<0 || mp>59 || id<=0) { throw new Exception(); }
            this.perc = p;
            this.mp = mp;
            this.id = id;
            this.kat = k;
        }


        public Kategoria Kat() { return kat; }
        public int ID() { return id; }
        public int Perc() { return perc;}
        public int Mp() { return mp;} 

    }
}
