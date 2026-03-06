using System.Collections;

namespace Gyak6
{
    public struct Receipt
    {
        public string name;
        public List<Item> items;
    }
    public struct Item
    {
        public int id;
        public int price;
    }
    public class ElectronicsEnumerable : IEnumerable
    {
        private string path;
        public ElectronicsEnumerable(string path)
        {
            this.path = path;
        }
        IEnumerator IEnumerable.GetEnumerator()
        {
            return (IEnumerator)GetEnumerator();
        }
        public ElectronicsEnumerator GetEnumerator()
        {
            return new ElectronicsEnumerator(path);
        }
        public void ChangePath(string path)
        {
            this.path = path;
        }
    }

    public class ElectronicsEnumerator : IEnumerator
    {
        private string path;
        private StreamReader inFile;
        private Receipt curr;
        public ElectronicsEnumerator(string path)
        {
            this.path = path;
            inFile = new StreamReader(path);
        }

        public void Reset()
        {
            inFile.Close();
            inFile = new StreamReader(path);
        }
        public bool MoveNext()
        {
            string[] input;
            Item item;
            if (!inFile.EndOfStream)
            {
                input = inFile.ReadLine().Split();
                curr.name = input[0];
                for (int i = 1; i < input.Length; i += 2)
                {
                    item = new Item();
                    item.id = int.Parse(input[i]);
                    item.price = int.Parse(input[i + 1]);
                    curr.items.Add(item);
                }
                return true;
            }
            return false;
        }
        object IEnumerator.Current
        {
            get
            {
                return curr;
            }
        }
        public Receipt Current
        {
            get
            {
                return curr;
            }
        }
    }
}
