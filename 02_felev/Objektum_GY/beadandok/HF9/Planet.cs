using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HF9
{
    public class Planet
    {
        public string name;
        private List<Starship> ships;

        public Planet(string name)
        {
            this.name = name;
            ships = new List<Starship>();
        }

        public void Defends(Starship s)
        {
            if (ships.Contains(s))
                throw new Exception();
            ships.Add(s);
        }
        public void Leaves(Starship s)
        {
            if (!ships.Contains(s))
                throw new Exception();
            ships.Remove(s);
        }
        public int ShipCount()
        {
            return ships.Count;
        }
        public int ShieldSum()
        {
            return ships.Sum(e => e.GetShield());
        }
        public (bool, double, Starship?) MaxFireP()
        {
            Starship? starship = ships.MaxBy( (e) => e.FireP());
            return (starship != null, starship != null ? starship.FireP() : 0, starship);
        }
    }
}
