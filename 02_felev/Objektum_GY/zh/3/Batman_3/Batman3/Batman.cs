namespace Batman3
{
    public class Batman
    {
        private int hp;
        public int Hp
        {
            get { return hp; }
            set { hp = value; }
        }
        private int damage;
        public int Damage
        {
            get { return damage; }
            set { damage = value; }
        }
        private Allies allies;
        public Simulation simulation;
        public Simulation Simulation
        {
            get { return simulation; }
            set { simulation = value; }
        }


        private Batman() { allies = new Allies(); }
        private static Batman? instance = null;
        public static Batman? Instance
        {
            get
            {
                if (instance == null)
                    instance = new Batman();
                return instance;
            }
            set { instance = value; }
        }
        public void Attack(Enemy target)
        {
            if (target is Villain)
                target.TakeDamage(damage*2);
            else
                target.TakeDamage(damage);
        }
        public void TakeDamage(int amount)
        {
            hp = hp - amount;
            if (hp <= 0)
            {
                simulation.BatmanLost = true;
                simulation.IsOver = true;
            }
        }
        public void CallForHelp(Enemy target)
        {
            if (target is Thug)
                allies.Lucius();
            else
                allies.Robin(target);
        }
    }
}
