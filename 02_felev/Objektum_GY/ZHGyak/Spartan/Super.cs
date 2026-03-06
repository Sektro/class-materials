namespace Spartan
{
    public class Super : Kategoria
    {
        //INSTANCE
        private Super() { }
        private Super? instance = null;
        public Super? Instance()
        {
            if (instance == null)
                instance = new Super();
            return instance;
        }

        public string Tipus()
        {
            return "super";
        }
    }
}
