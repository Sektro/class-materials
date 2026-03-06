namespace Gyak9
{
    public enum Sex
    {
        MALE,
        FEMALE
    }

    public class Game
    {
        public double weight;
        public Sex sex;
        public Game(double weight, Sex sex)
        {
            this.weight = weight;
            this.sex = sex;
        }
    }
}
