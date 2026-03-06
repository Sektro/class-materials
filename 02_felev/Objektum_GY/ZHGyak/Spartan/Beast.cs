namespace Spartan
{
    public class Beast : Kategoria
    {
        //INSTANCE
        private Beast() { }
        private Beast? instance = null;
        public Beast? Instance()
        {
            if (instance == null)
                instance = new Beast();
            return instance;
        }

        public string Tipus()
        {
            return "beast";
        }
    }
}
