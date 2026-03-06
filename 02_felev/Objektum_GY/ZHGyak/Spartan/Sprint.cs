namespace Spartan
{
    public class Sprint : Kategoria
    {
        //INSTANCE
        private Sprint() { }
        private Sprint? instance = null;
        public Sprint? Instance()
        {
            if (instance == null)
                instance = new Sprint();
            return instance;
        }

        public string Tipus()
        {
            return "sprint";
        }
    }
}
