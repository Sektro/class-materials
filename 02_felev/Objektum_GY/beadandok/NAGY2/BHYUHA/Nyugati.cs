namespace Turistak
{
    public class Nyugati : Turista
    {
        public int letszam;

        public Nyugati(int letszam)
        {
            this.letszam = letszam;
        }

        public override void Visit(Varos v)
        {
            v.Accept(this);
        }
    }
}
