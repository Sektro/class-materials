namespace Batman3
{
    public class Villain : Enemy
    {
        private List<Thug> thugs;
        public Villain(int hp, int damage, List<Thug> thugs) : base(hp, damage)
        {
            this.thugs = thugs;
        }
        public (bool, List<Thug>?) CallMoreThugs(int amount)
        {
            List<Thug> thugsCopy = new List<Thug>();
            if (thugs.Count < amount)
                return (false,null);
            else
            {
                for (int i = 0; i < amount; i++)
                {
                    thugs[i].Simulation = simulation;
                    thugsCopy.Add(thugs[i]);
                }
                thugs.RemoveRange(0, amount);
            }
            return (true, thugsCopy);
        }
        public override void TakeDamage(int amount)
        {
            if (thugs.Count > 0)
            {
                Thug elem = thugs.MaxBy(t => t.Hp);
                thugs[thugs.FindIndex(t => t == elem)].TakeDamage(amount);
                elem.TakeDamage(amount);
                if (elem.Hp <= 0)
                    thugs.Remove(elem);
            }
            else
                base.TakeDamage(amount);
        }
        public override void Attack()
        {
            Batman.Instance.TakeDamage(damage+3);
        }
    }
}
