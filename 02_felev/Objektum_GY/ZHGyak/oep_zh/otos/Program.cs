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
                Console.Write("verseny regisztral check: ");
                List<Kategoria> kat = new();
                kat.Add(Sprint.Instance());
                Verseny verseny = new(2009, "salgo", kat);
                verseny.Regisztral(new Versenyzo(1, "", true));
                verseny.Regisztral(new Versenyzo(1, "", true));
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - existing ID");
            }
            try
            {
                Console.Write("verseny befuto check: ");
                List<Kategoria> kat = new();
                kat.Add(Sprint.Instance());
                Verseny verseny = new(2009, "salgo", kat);
                verseny.Regisztral(new Versenyzo(1, "", true));
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
                verseny.Regisztral(new Versenyzo(1, "", true));
                verseny.Befuto(1, 1, 2, Sprint.Instance());
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - nonexisting ID");
            }
            try
            {
                Console.Write("verseny befuto check: ");
                List<Kategoria> kat = new();
                kat.Add(Sprint.Instance());
                Verseny verseny = new(2009, "salgo", kat);
                verseny.Regisztral(new Versenyzo(1, "", true));
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
                Console.Write("versenyzo constructor check: ");
                Versenyzo versenyzo = new(-2, "", true);
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - negative ID");
            }
            try
            {
                Console.Write("eredmeny constructor check: ");
                Eredmeny er = new(-2, 2, null, null);
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - negative perc");
            }
            try
            {
                Console.Write("eredmeny constructor check: ");
                Eredmeny er = new(2, -2, null, null);
                Console.WriteLine("not OK");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine("OK - negative mp");
            }
            try
            {
                Console.Write("eredmeny constructor check: ");
                Eredmeny er = new(2, 60, null, null);
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
            List<Versenyzo> versenyzok = new();
            versenyzok.Add(new Versenyzo(1, "A", true)); verseny2.Regisztral(versenyzok[0]);
            versenyzok.Add(new Versenyzo(2, "B", true)); verseny2.Regisztral(versenyzok[1]);
            versenyzok.Add(new Versenyzo(3, "C", true)); verseny2.Regisztral(versenyzok[2]);
            versenyzok.Add(new Versenyzo(4, "D", false)); verseny2.Regisztral(versenyzok[3]);
            versenyzok.Add(new Versenyzo(5, "E", false)); verseny2.Regisztral(versenyzok[4]);
            versenyzok.Add(new Versenyzo(6, "F", false)); verseny2.Regisztral(versenyzok[5]);
            Console.WriteLine("Nepszeru empty: " + (verseny2.NepszeruKat().Tipus()=="sprint"?"OK":"not OK"));
            bool l;
            Versenyzo gyoztes;
            verseny2.Befuto(10, 8, 4, Super.Instance());
            verseny2.Befuto(10, 8, 4, Sprint.Instance());
            Console.WriteLine("not ElsoE 0: " + (!versenyzok[0].ElsoE(Super.Instance()) ? "OK" : "not OK"));
            Console.WriteLine("ElsoE 1: " + (versenyzok[3].ElsoE(Super.Instance()) ? "OK" : "not OK"));

            (l, gyoztes) = verseny2.Gyoztes(Sprint.Instance(), true);
            Console.WriteLine("Gyoztes empty: "+ (l ? "not OK" : "OK"));

            verseny2.Befuto(10, 10, 1, Sprint.Instance());
            verseny2.Befuto(20, 10, 1, Super.Instance());
            Console.WriteLine("Nepszeru 1: " + (verseny2.NepszeruKat().Tipus() == "sprint" ? "OK" : "not OK"));
            (l, gyoztes) = verseny2.Gyoztes(Sprint.Instance(), true);
            Console.WriteLine("Gyoztes 1: " + ((l&&gyoztes.ID()==1) ? "OK" : "not OK"));

            Console.WriteLine("not ElsoE 0: " + (!versenyzok[2].ElsoE(Super.Instance()) ? "OK" : "not OK"));
            Console.WriteLine("ElsoE 1: " + (versenyzok[0].ElsoE(Super.Instance()) ? "OK" : "not OK"));

            verseny2.Befuto(10, 9, 2, Super.Instance());
            Console.WriteLine("Nepszeru super: " + (verseny2.NepszeruKat().Tipus() == "super" ? "OK" : "not OK"));
            (l, gyoztes) = verseny2.Gyoztes(Super.Instance(), true);
            Console.WriteLine("Gyoztes 2: " + ((l && gyoztes.ID() == 2) ? "OK" : "not OK"));
            Console.WriteLine("ElsoE 2: " + (versenyzok[3].ElsoE(Super.Instance()) ? "OK" : "not OK"));

            Console.WriteLine("not ElsoE 2: " + (!versenyzok[0].ElsoE(Super.Instance()) ? "OK":"not OK"));
        }
    }
}
