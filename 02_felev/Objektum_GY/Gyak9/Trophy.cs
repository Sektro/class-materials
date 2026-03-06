namespace Gyak9
{
    public class Trophy
    {
        private string site;
        private string date;
        private Game prey;

        public Trophy(string site, string date, Game prey)
        {
            this.site = site;
            this.date = date;
            this.prey = prey;
        }
        public Game getPrey()
        {
            return prey;
        }
    }
}
