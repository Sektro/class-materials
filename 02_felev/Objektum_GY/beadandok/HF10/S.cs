namespace HF10
{
    public class S : Size
    {
        private S() { }
        public int Multi()
        {
            return 1;
        }
        public static S Instance { get { return new S(); } }
    }
}
