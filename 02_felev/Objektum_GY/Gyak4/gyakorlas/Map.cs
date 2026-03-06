namespace gyakorlas
{
    public struct Item
    {
        public string data;
        public int key;
    }

    public class Map
    {
        private List<Item> seq;

        public Map() 
        { 
            this.seq = new List<Item>();
        }

        public void SetEmpty()
        {
            seq.Clear();
        }

        public int Count()
        {
            return seq.Count;
        }

        public void Insert(Item e)
        {
            (bool l, int ind) = LogSearch(e.key);
            if (l)
                throw new Exception();
            seq.Insert(ind, e);
        }

        public void Remove(int key)
        {
            (bool l, int ind) = LogSearch(key);
            if (!l) throw new Exception();
            seq.RemoveAt(ind);
        }

        public bool In(int key)
        {
            (bool l, int ind) = LogSearch(key);
            return l;
        }

        public string this[int key]
        {
            get
            {
                (bool l, int ind) = LogSearch(key);
                if (!l)
                    throw new Exception();
                return seq[ind].data;
            }
            private set { }
        }

        //public Map operator[](int key)

        private (bool, int) LogSearch(int key)
        {
            (bool l, int ah, int fh) = (false, 0, seq.Count);
            int ind;
            while (!l && ah < fh)
            {
                ind = (ah + fh) / 2;
                if (seq[ind].key > key)
                    fh = ind - 1;
                else if (seq[ind].key == key)
                    l = true;
                else if (seq[ind].key < key)
                    ah = ind + 1;
            }
            if (!l)
            {
                 ind = ah;
            }
            return (l, ah);
        }
    }
}
