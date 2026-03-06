using System.Runtime.CompilerServices;

namespace Batman3
{
    public class Simulation
    {
        private bool isOver;
        public bool IsOver
        {
            get { return isOver; }
            set { isOver = value; }
        }
        private int rounds;
        public int Rounds { get { return rounds; } }
        private bool batmanLost;
        public bool BatmanLost { get {  return batmanLost; } set {  batmanLost = value; } }
        private int id;
        public int Id { get { return id; } }
        private List<Enemy> enemies;
        public List<Enemy> Enemies { get { return enemies; } }


        public Simulation(List<Enemy> enemies, int id)
        {
            isOver = false;
            rounds = 0;
            batmanLost = false;
            this.id = id;
            this.enemies = enemies;
            foreach ( Enemy e in enemies)
            {
                e.Simulation = this;
            }
        }
        public void DeleteEnemy(Enemy enemy)
        {
            enemies.Remove(enemy);
        }
        public void StartRound()
        {
            if (enemies.Count > 0)
            {
                rounds = rounds + 1;
                if (rounds % 3 == 0)
                {
                    if (StrongestVillain().Item1)
                        Batman.Instance.CallForHelp(StrongestVillain().Item2);
                    else
                        Batman.Instance.CallForHelp(enemies[0]);
                }
                else
                {
                    Batman.Instance.Attack(enemies[0]);
                }
                if (enemies.Count == 0)
                {
                    IsOver = true;
                    return;
                }
                enemies[0].Attack();
                if (enemies[0] is Villain)
                    if (rounds % 4 == 0)
                    {
                        Villain villain = enemies[0] as Villain;
                        (bool l, List<Thug>? newthugs) = villain.CallMoreThugs(5);
                        if (l)
                        {
                            //enemies.Union(newthugs);
                            foreach (Thug newthug in newthugs)
                                enemies.Add(newthug);
                        }
                    }
            }
            else
                isOver = true;
        }
        public int FullPower()
        {
            return enemies.Sum(e => e.Damage);
        }
        private (bool, Villain?) StrongestVillain()
        {
            Enemy? elem = enemies.Where(e => e is Villain).MaxBy(e => e.Damage);
            return ((elem != null), (elem == null) ? null : (elem as Villain));
        }
    }
}
