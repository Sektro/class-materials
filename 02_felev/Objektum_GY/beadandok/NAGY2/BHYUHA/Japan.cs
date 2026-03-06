namespace Turistak
{
    public class Japan : Turista
    {
        public int letszam;

        public Japan(int letszam)
        {
            this.letszam = letszam;
        }

        public override void Visit(Varos v)
        {
            v.Accept(this);
        }
    }
}
