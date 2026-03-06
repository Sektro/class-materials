namespace Batman3
{
    public class WayneTower
    {
        private Batman wayne;
        private List<Simulation> simulations;
        public WayneTower(Batman batman)
        {
            wayne = batman;
        }
        public Simulation HardestSimulation()
        {
            if (simulations.Count == 0) { throw new Exception(); }
            return simulations.MaxBy(s => s.FullPower());
        }
        public void MakeSimulation(List<Enemy> enemies, int id, int hp, int damage)
        {
            wayne.Hp = hp;
            wayne.Damage = damage;
            Simulation s = new Simulation(enemies, id);
            simulations.Add(s);
            wayne.Simulation = s;
            while (!s.IsOver)
            {
                s.StartRound();
            }
        }
        public int LostSimulationCount()
        {
            return simulations.Count(s => s.BatmanLost);
        }
        public (bool, int?, Simulation) ShortestLostSimulation()
        {
            Simulation? sim = simulations.Where(s => s.BatmanLost).MinBy(s => s.Rounds);
            int simround = simulations.Where(s => s.BatmanLost).Min(s => s.Rounds);
            return ((sim != null), (sim == null) ? 0 : simround, (sim == null) ? null : sim);

        }
    }
}
