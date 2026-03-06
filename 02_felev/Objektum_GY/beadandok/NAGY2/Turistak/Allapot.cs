using System.Security.Cryptography.X509Certificates;

namespace Turistak
{
    public interface Allapot
    {
        public int Accept(Japan j);
        public int Accept(Nyugati n);
        public int Accept(Egyeb e);
        public int getAllapot();
        public string writeAllapot();
        public Allapot Rontas(int n, int e);
        public Allapot Javitas(double extrapenz);
    }
}
