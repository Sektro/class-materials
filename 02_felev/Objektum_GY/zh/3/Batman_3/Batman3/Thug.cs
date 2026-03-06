namespace Batman3
{
    public class Thug : Enemy
    {
        public Thug(int hp, int damage) : base(hp, damage) { }
        public override void Attack()
        {
            Batman.Instance.TakeDamage(damage);
        }
    }
}
