using Turistak;
namespace TuristakTest
{
    [TestClass]
    public class VarosTest
    {
        [TestMethod]
        public void Konstruktor()
        {
            Varos v = new Varos(12);
            if (v == null)
            {
                Assert.Fail();
            }
            Assert.IsInstanceOfType(v.allapot, typeof(Lepusztult));
            v = new Varos(40);
            Assert.IsInstanceOfType(v.allapot, typeof(Atlagos));
            v = new Varos(80);
            Assert.IsInstanceOfType(v.allapot, typeof(Jo));
        }
        [TestMethod]
        public void LepusztultAccept()
        {
            Varos v = new Varos(12);
            Japan japan = new Japan(1000);
            Nyugati nyugati = new Nyugati(1000);
            Egyeb egyeb = new Egyeb(1000);

            v.Accept(japan);
            Assert.AreEqual(v.getJapan(), 0);
            Assert.AreEqual(v.jovedelem, 0);
            v.Accept(nyugati);
            Assert.AreEqual(v.getNyugati(), 1000);
            Assert.AreEqual(v.jovedelem, 1000 * 100000);
            v.Accept(egyeb);
            Assert.AreEqual(v.getEgyeb(), 1000);
            Assert.AreEqual(v.jovedelem, 1000 * 100000 + 1000 * 100000);
        }
        [TestMethod]
        public void AtlagosAccept()
        {
            Varos v = new Varos(40);
            Japan japan = new Japan(1000);
            Nyugati nyugati = new Nyugati(1000);
            Egyeb egyeb = new Egyeb(1000);

            v.Accept(japan);
            Assert.AreEqual(v.getJapan(), 1000);
            Assert.AreEqual(v.jovedelem, 1000 * 100000);
            v.Accept(nyugati);
            Assert.AreEqual(v.getNyugati(), 1100);
            Assert.AreEqual(v.jovedelem, 1000 * 100000 + 1100 * 100000);
            v.Accept(egyeb);
            Assert.AreEqual(v.getEgyeb(), 1100);
            Assert.AreEqual(v.jovedelem, 1000 * 100000 + 1100 * 100000 + 1100 * 100000);
        }
        [TestMethod]
        public void JoAccept()
        {
            Varos v = new Varos(80);
            Japan japan = new Japan(1000);
            Nyugati nyugati = new Nyugati(1000);
            Egyeb egyeb = new Egyeb(1000);

            v.Accept(japan);
            Assert.AreEqual(v.getJapan(), 1200);
            Assert.AreEqual(v.jovedelem, 1200 * 100000);
            v.Accept(nyugati);
            Assert.AreEqual(v.getNyugati(), 1300);
            Assert.AreEqual(v.jovedelem, 1200 * 100000 + 1300 * 100000);
            v.Accept(egyeb);
            Assert.AreEqual(v.getEgyeb(), 1000);
            Assert.AreEqual(v.jovedelem, 1200 * 100000 + 1300 * 100000 + 1000 * 100000);
        }
        
        [TestMethod]
        public void AcceptAllTest()
        {
            Varos v = new Varos(80);
            Japan japan = new Japan(1000);
            Nyugati nyugati = new Nyugati(1000);
            Egyeb egyeb = new Egyeb(1000);

            v.Accept(japan);
            v.Accept(nyugati);
            v.Accept(egyeb);
            v.AcceptAll();
            Assert.AreEqual(v.getAllapot(), 47);
        }
        [TestMethod]
        public void LegjobbAllapotTest()
        {
            Varos v = new Varos(100);
            Japan japan = new Japan(1000);
            Nyugati nyugati = new Nyugati(1000);
            Egyeb egyeb = new Egyeb(1000);

            japan.Visit(v);
            nyugati.Visit(v);
            egyeb.Visit(v);
            v.AcceptAll();
            v.UjEv();
            Assert.AreEqual(v.LegjobbAllapot(), 1);
        }

    }
    
    [TestClass]
    public class TuristaTest
    {
        [TestMethod]
        public void Japanok()
        {
            Japan japan = new Japan(1000);
            Assert.AreEqual(japan.letszam, 1000);
            Varos v = new Varos(50);
            japan.Visit(v);
            Assert.AreEqual(v.getJapan(), 1000);
        }
        [TestMethod]
        public void Nyugatiak()
        {
            Nyugati nyugati = new Nyugati(1000);
            Assert.AreEqual(nyugati.letszam, 1000);
            Varos v = new Varos(10);
            nyugati.Visit(v);
            Assert.AreEqual(v.getNyugati(), 1000);
        }
        [TestMethod]
        public void Egyebek()
        {
            Egyeb egyeb = new Egyeb(1000);
            Assert.AreEqual(egyeb.letszam, 1000);
            Varos v = new Varos(10);
            egyeb.Visit(v);
            Assert.AreEqual(v.getEgyeb(), 1000);
        }
    }
    
}