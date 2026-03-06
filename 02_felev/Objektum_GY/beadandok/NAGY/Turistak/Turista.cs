using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Turistak
{
    public class Turista
    {
        public int letszam;
        protected Turista() { }

        public int GetLetszam()
        {
            return letszam;
        }
        public void SetLetszam(int a)
        {
            letszam = a;
        }
    }
}
