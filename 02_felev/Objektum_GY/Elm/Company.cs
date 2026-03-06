namespace Elm
{
    public class Company
    {
        private int paymentsum;
        private List<Owner> owners;
        private List<House> houses;
        private List<Owner> paid;

        public Company(int paymentsum)
        {
            this.paymentsum = paymentsum;
            owners = new List<Owner>();
            houses = new List<House>();
        }

        public void AppartementCost(Appartement appartement) { return (appartement.getArea() * appartement.getComfort()); }
    }
}
