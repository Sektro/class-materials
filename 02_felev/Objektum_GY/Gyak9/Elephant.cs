namespace Gyak9
{
    public class Elephant : Game
    {
        public double left;
        public double right;
        public Elephant(double weight, double left, double right, Sex sex) : base(weight, sex)
        {
            this.left = left;
            this.right = right;
        }
    }
}
