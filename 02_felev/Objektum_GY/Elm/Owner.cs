namespace Elm
{
    public class Owner
    {
        private string name;
        private string postal;
        private string email;
        private List<Company> companies;
        private List<Appartement> appartements;

        public Owner(string name, string postal, string email)
        {
            this.name = name;
            this.postal = postal;
            this.email = email;
            companies = new List<Company>();
            appartements = new List<Appartement>();
        }

        public void Pays(Company company)
        {
            
        }
    }
}
