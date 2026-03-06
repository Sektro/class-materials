namespace Gyak7
{
    public class Customer
    {
        private List<string> shoppingList;
        public List<Item> shoppingCart;

        public Customer(List<string> shoppingList)
        {
            this.shoppingList = shoppingList;
            shoppingCart = new List<Item>();
        }
        public void Purchase(Shop s)
        {
            bool I;
            Item? item;
            foreach (string name in shoppingList)
            {
                (I, item) = Search(name, s.food);
                if (I)
                    Take(item, s.food);
            }
            foreach (string name in shoppingList)
            {
                (I, item) = SearchCheap(name, s.electronics);
                if (I)
                    Take(item, s.electronics);
            }
        }

        private (bool, Item?) Search(string name, Department r)
        {
            foreach (Item item in r.supply)
                if (name == item.getName())
                    return (true,  item);
            return (false, null);
        }

        private (bool, Item?) SearchCheap(string name, Department r)
        {
            bool foundCheap = false;
            Item? foundItem = null;
            foreach (Item item in r.supply)
            {
                if (name == item.getName() && !foundCheap)
                {
                    foundCheap = true;
                    foundItem = item;
                }
                else if (name == item.getName())
                    if (item.getPrice() < foundItem?.getPrice())
                        foundItem = item;
            }
            return (foundCheap, foundItem);
        }

        private void Take(Item item, Department r)
        {
            r.supply.Remove(item);
            shoppingCart.Add(item);
        }
    }
}
