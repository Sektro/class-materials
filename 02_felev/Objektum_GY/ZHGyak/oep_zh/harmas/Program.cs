namespace SpartanProject
{
    internal class Program
    {
        static void Main(string[] args)
        {
            try
            {
                Console.Write("verseny constructor check: ");
                List<Kategoria> kat = new();
                Verseny verseny = new(2009, "salgo", kat);
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - zero categories");
            }
            try
            {
                Console.Write("verseny constructor check: ");
                List<Kategoria> kat = new();
                kat.Add(Sprint.Instance());
                Verseny verseny = new(1999, "salgo", kat);
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - too small year");
            }
            try
            {
                Console.Write("verseny befuto check: ");
                List<Kategoria> kat = new();
                kat.Add(Sprint.Instance());
                Verseny verseny = new(2009, "salgo", kat);
                verseny.Befuto(1, 1, 1, Super.Instance());
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - nonexisting kategoria");
            }
            try
            {
                Console.Write("verseny befuto check: ");
                List<Kategoria> kat = new();
                kat.Add(Sprint.Instance());
                Verseny verseny = new(2009, "salgo", kat);
                verseny.Befuto(1, 1, 1, Sprint.Instance());
                verseny.Befuto(1, 2, 1, Sprint.Instance());
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - existing befuto");
            }
            try
            {
                Console.Write("eredmeny constructor check: ");
                Eredmeny er = new(-2, 2, 1, null);
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - negative perc");
            }
            try
            {
                Console.Write("eredmeny constructor check: ");
                Eredmeny er = new(2, -2, 1, null);
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - negative mp");
            }
            try
            {
                Console.Write("eredmeny constructor check: ");
                Eredmeny er = new(2, 60, 1, null);
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - too big mp");
            }
            List<Kategoria> kat2 = new();
            kat2.Add(Sprint.Instance());
            kat2.Add(Super.Instance());
            Verseny verseny2 = new(2001, "Salgotarjan", kat2);
            Console.WriteLine("Nepszeru empty: " + (verseny2.NepszeruKat().Tipus()=="sprint"?"OK":"not OK"));

            bool l;
            int gyoztes = 0;

            (l, gyoztes) = verseny2.Gyoztes(Sprint.Instance());
            Console.WriteLine("Gyoztes empty: " + (l ? "not OK" : "OK"));


            verseny2.Befuto(10, 8, 4, Super.Instance());
            verseny2.Befuto(10, 8, 4, Sprint.Instance());
            (l, gyoztes) = verseny2.Gyoztes(Sprint.Instance());
            Console.WriteLine("Gyoztes 4: " + ((l && gyoztes == 4) ? "OK" : "not OK"));

            verseny2.Befuto(10, 10, 1, Sprint.Instance());
            verseny2.Befuto(20, 10, 1, Super.Instance());
            Console.WriteLine("Nepszeru 1: " + (verseny2.NepszeruKat().Tipus() == "sprint" ? "OK" : "not OK"));
            (l, gyoztes) = verseny2.Gyoztes(Sprint.Instance());
            Console.WriteLine("Gyoztes 1: " + ((l&&gyoztes==4) ? "OK" : "not OK"));

            verseny2.Befuto(10, 7, 2, Super.Instance());
            Console.WriteLine("Nepszeru super: " + (verseny2.NepszeruKat().Tipus() == "super" ? "OK" : "not OK"));
            (l, gyoztes) = verseny2.Gyoztes(Super.Instance());
            Console.WriteLine("Gyoztes 2: " + ((l && gyoztes == 2) ? "OK" : "not OK"));

        }
    }
}
