namespace Gyak1
{
    public enum Type
    {
        NORMAL,
        RULED,
        MATH
    }

    public class WorkBook
    {
        private Type type;
        private List<string> pages;
        private int empty;

        // Ez a Spirál, csak el van írva
        public WorkBook(int n, Type type)
        {
            pages = new List<string>();
            for (int i = 0; i < n; i++)
            {
                pages.Add("");
            }
            this.type = type; // this.type, ami az osztályhoz tartozik, másik type amit megkap a függvény
            empty = n;
        }
        public int NumberOfPages()
        {
            return pages.Count;
        }
        public void Dummy()
        {
            if (0 == 0)
            {
                throw new Exception();
            }
        }
        //return használat
        /*
        public int General()
        {
            if (pages.Count > 50)
                return 50;
            return 0;
        }
        */
        // plusz jel: listák összefűzése
    }
}
