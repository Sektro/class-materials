using System.Numerics;

namespace Gyak10
{
    public class Dummy
    {
        private string name;

        public Dummy(string name)
        {
            this.name = name;
        }

        public string GetName()
        {
            return name;
        }
        public void ChangeName(string name)
        {
            this.name = name;
        }
        public bool IsDividable(int a, int b)
        {
            if (b == 0)
                throw new ArgumentException();
            return a % b == 0;
        }
    }
}
