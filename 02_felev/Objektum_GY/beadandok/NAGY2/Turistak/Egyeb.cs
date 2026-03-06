namespace Turistak
{
    public class Egyeb : Turista
    {
        public int letszam;

        public Egyeb(int letszam)
        {
            this.letszam = letszam;
        }

        public override void Visit(Varos v)
        {
            v.Accept(this);
        }
    }
}
