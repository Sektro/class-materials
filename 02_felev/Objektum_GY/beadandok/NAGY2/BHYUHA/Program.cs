using System.Globalization;

namespace Turistak
{
    internal class Program
    {
        static void Main(string[] args)
        {
            //Declarations
            int kezdo = 0;
            using StreamReader inFile = new StreamReader("turizmus.txt");
            string[] input;
            Varos varos = new Varos(100);
            Japan japanok = new Japan(0);
            Nyugati nyugatiak = new Nyugati(0);
            Egyeb egyebek = new Egyeb(0);
            int i = 1;

            //Read
            if (!inFile.EndOfStream)
            {
                input = inFile.ReadLine().Split();
                kezdo = int.Parse(input[0]);
                varos = new Varos(kezdo);
                Console.WriteLine("Kezdőállapot: {0} ({1})", varos.getAllapot(), varos.writeAllapot());
            }
            while (!inFile.EndOfStream)
            {
                Console.WriteLine("{0}. Év:", i);
                input = inFile.ReadLine().Split();
                japanok = new Japan(int.Parse(input[0]));
                nyugatiak = new Nyugati(int.Parse(input[1]));
                egyebek = new Egyeb(int.Parse(input[2]));
                Console.WriteLine("Tervezett:");
                Console.WriteLine("   japánok: {0}, nyugatiak: {1}, egyebek: {2}",japanok.letszam,nyugatiak.letszam,egyebek.letszam);
                japanok.Visit(varos);
                nyugatiak.Visit(varos);
                egyebek.Visit(varos);
                varos.AcceptAll();
                Console.WriteLine("Tényleges:");
                Console.WriteLine("   japánok: {0}, nyugatiak: {1}, egyebek: {2}", varos.getJapan(), varos.getNyugati(), varos.getEgyeb());
                Console.WriteLine("Város állapota: {0} ({1})",varos.getAllapot(),varos.writeAllapot());
                Console.WriteLine("Város ez évi jövedelme: {0} forint",varos.jovedelem);
                varos.UjEv();
                ++i;
            }

            //Write
            Console.WriteLine("A város legjobb állapotát a(z) {0}. évében érte el.",varos.LegjobbAllapot());
            
        }
    }
}
