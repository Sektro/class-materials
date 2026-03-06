using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HF9
{
    public class Solarsystem
    {
        public List<Planet> planets;

        public Solarsystem()
        {
            planets = new List<Planet>();
        }

        public (bool, Starship?) MaxFireP()
        {
            (bool l,_, Starship? ship) = planets.Select((e) => e.MaxFireP()).MaxBy((a) => a.Item2);
            return (l, ship);
        }
        public List<Planet> Defenseless()
        {
            return planets.FindAll((e) => e.ShipCount() == 0);
        }
    }
}
