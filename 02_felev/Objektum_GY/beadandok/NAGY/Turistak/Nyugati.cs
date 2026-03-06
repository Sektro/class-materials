using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Turistak
{
    public class Nyugati : Turista
    {
        public int rontas;
        public Nyugati(int letszam)
        {
            this.letszam = letszam;
            rontas = 1;
        }
    }
}
