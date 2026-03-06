namespace Gyak9
{
    public class Hunter
    {
        private string name;
        private string birthYear;
        private List<Trophy> trophies;

        public Hunter(string name, string year)
        {
            this.name = name;
            birthYear = year;
            trophies = new List<Trophy>();
        }
        public void Hunt(string site, string date, Game prey)
        {
            trophies.Add(new Trophy(site, date, prey));
        }
        public int MaleLions()
        {
            //return trophies.Count(test);
            return trophies.Count(
                e => e.getPrey() is Lion l
                && 
                l.sex == Sex.MALE
                );
        }
        /*
        public bool test(Trophy t)
        {
            return t.getPrey() is Lion l && l.sex == Sex.MALE;
        }
        */
        public (bool, double, Trophy?) MaxHorn()
        {
            Trophy? max = trophies
                .Where(
                (e) => e.getPrey() is Rhino
                )
                .MaxBy(
                (e) => ((Rhino)e.getPrey()).horn / e.getPrey().weight
                );
            return (
                max != null, 
                max != null ? ((Rhino)max.getPrey()).horn / max.getPrey().weight : 0,
                max);
        }
        public bool EqualLength()
        {
            return trophies.Any(
                e => e.getPrey() is Elephant el
                &&
                el.left == el.right
                );
        }
    }
}
