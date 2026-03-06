namespace Elm
{
    public class House
    {
        private string address;
        private List<Company> companies;
        private List<Appartement> appartements;

        public House(string address)
        {
            this.address = address;
            companies = new List<Company>();
            appartements = new List<Appartement>();
        }
    }
}
