using System.Collections;

namespace Gyak6
{
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
            return new IntEnumerator(path);
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
        private int curr;
        public ElectronicsEnumerator(string path)
        {
            this .path = path;
            inFile = new StreamReader(path);
        }

        public void Reset()
        {
            inFile.Close();
            inFile = new StreamReader(path);
        }
        public bool MoveNext()
        {
            if (!inFile.EndOfStream)
            {
                curr = int.Parse (inFile.ReadLine());
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
        public int Current
        {
            get
            {
                return curr;
            }
        }
    }
}
