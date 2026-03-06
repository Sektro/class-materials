namespace Gyak3
{
    internal class Program
    {
        static void Main(string[] args)
        {
            LTM a = new LTM(4);
            try
            {
                a[0, 3] = 2;
            }
            catch (LTMException e)
            {
                Console.WriteLine(e.Message);
            }
            catch (Exception e)
            {
                Console.WriteLine("Általános");
            }
        }
    }
}