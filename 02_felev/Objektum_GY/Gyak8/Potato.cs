using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Gyak8
{
    public class Potato : Vegetable
    {
        private static Potato? instance = null;
        private Potato()
        {
            harvestTime = 10;
        }
        public static Potato Instance()
        {
            if (instance == null)
            {
                instance = new Potato();
            }
            return instance;
        }
    }

    /*
     * házihoz segítség teams felvételen ~26 percnél
     */
}
