using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Gyak8
{
    public class Plant
    {
        protected int harvestTime;
        protected Plant()
        {

        }
        public virtual bool isVegetable()
        {
            return false;
        }
        public virtual bool isFlower()
        {
            return false;
        }
        public int GetHarvestTime()
        {
            return harvestTime;
        }
    }
}
