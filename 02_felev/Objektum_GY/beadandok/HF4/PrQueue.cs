using System.Runtime.CompilerServices;

namespace HF4
{
    public struct Element
    {
        public int pr;
        public string data;
    }

    public class PrQueue
    {
        private List<Element> seq;

        public PrQueue()
        {
            seq = new List<Element>();
        }

        public void SetEmpty()
        {
            seq.Clear();
        }

        public bool isEmpty()
        {
            return seq.Count == 0;
        }
        public void Add(Element e)
        {
            seq.Add(e);
        }

        public Element GetMax()
        {
            if (seq.Count == 0)
                throw new Exception();
            (int max, int ind) = MaxSelect();
            return seq[ind];
        }

        public Element RemMax()
        {
            if (seq.Count == 0)
                throw new Exception();
            (int max, int ind) = MaxSelect();
            Element e = seq[ind];
            seq.RemoveAt(ind);
            return e;
        }

        private (int, int) MaxSelect()
        {
            if (seq.Count == 0)
                throw new Exception();
            int max = seq[0].pr;
            int ind = 0;

            for (int i = 1; i < seq.Count; ++i)
            {
                if (seq[i].pr > max)
                {
                    max = seq[i].pr;
                    ind = i;
                }
            }

            return (max, ind);
        }
    }
}
