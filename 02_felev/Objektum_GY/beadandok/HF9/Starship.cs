using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HF9
{
    public class Starship
    {
        private string name;
        public int shield;
        public int armor;
        public int guardian;
        private Planet? planet;

        public Starship(string name, int shield, int armor, int guardian)
        {
            this.name = name;
            this.shield = shield;
            this.armor = armor;
            this.guardian = guardian;
            planet = null;
        }
        public int GetShield()
        {
            return shield;
        }
        public void StaysAtPlanet(Planet p)
        {
            if (planet != null)
                planet.Leaves(this);
            planet = p;
            planet.Defends(this);
        }
        public void LeavesPlanet()
        {
            if (planet == null)
                throw new Exception();
            planet.Leaves(this);
            planet = null;
        }
        public virtual double FireP()
        {
            return 0;
        }
    }
}
