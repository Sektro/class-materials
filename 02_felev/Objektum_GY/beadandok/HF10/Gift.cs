namespace HF10
{
    public class Gift
    {
        private Size size;
        public int Value()
        {
            return Point() * size.Multi();
        }
        public virtual int Point()
        {
            
        }
    }
}
