using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Gyak8
{
    public class Vegetable : Plant
    {
        protected Vegetable()
        {

        }

        public override bool isVegetable()
        {
            return true;
        }
    }
}
