namespace Batman3
{
    public abstract class Enemy
    {
        public int hp;
        public int Hp
        {
            get { return hp; }
        }
        public int damage;
        public int Damage
        {
            get { return damage; }
        }
        public Simulation simulation;
        public Simulation Simulation
        {
            get { return  simulation; }
            set { simulation = value; }
        }


        public Enemy(int hp, int damage)
        {
            this.hp = hp;
            this.damage = damage;
        }
        public virtual void TakeDamage(int amount)
        {
            hp = hp - amount;
            if (hp <= 0)
                if (simulation != null)
                    simulation.DeleteEnemy(this);
        }
        public abstract void Attack();
    }
}
