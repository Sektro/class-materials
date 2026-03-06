using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Turistak
{
    public class Japan : Turista
    {
        public int rontas;
        public Japan(int letszam) 
        {
            this.letszam = letszam;
            rontas = 0;
        }
    }
}
