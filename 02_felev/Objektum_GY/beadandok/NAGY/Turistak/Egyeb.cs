using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Turistak
{
    public class Egyeb : Turista
    {
        public int rontas;
        public Egyeb(int letszam)
        {
            this.letszam = letszam;
            rontas = 2;
        }
    }
}
