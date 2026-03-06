namespace Gyak1
{
    public class Soap
    {
        // Adattagok / Fields
        // private int full, portion, actual;
        private int full;
        private int portion;
        private int actual;

        // invariáns állítás: mindig teljesülnie kell az osztályra ({akt <= tele})
        // most nem foglalkozunk vele

        // Metódusok / Methods
        // zárójelekben: paraméterek
        //rövid leírás: "ctor" + Enter 
        public Soap(int k, int e)
        {
            full = k;
            portion = e;
            actual = 0;
        }
        public void Take()
        {
            //Math.Max(actual - portion, 0);
            actual = actual - portion > 0 ? actual - portion : 0;
        }
        public void Fill()
        {
            actual = full;
        }
    }
}
