using Gyak10;

namespace DumTest
{
    [TestClass]
    public class DummyTestClass
    {
        [TestMethod]
        public void DummyGetNameEqual()
        {
            Dummy tested = new Dummy("Steve");
            Assert.AreEqual("Steve",tested.GetName());
            tested.ChangeName("John");
            Assert.AreEqual("John", tested.GetName());
        }
        [TestMethod]
        public void IsDivisible()
        {
            Dummy tested = new Dummy("Steve");
            Assert.IsTrue(tested.IsDividable(3, 3));
        }
        [TestMethod]
        public void IsDivisibleNotEqual()
        {
            Dummy tested = new Dummy("Steve");
            Assert.IsTrue(tested.IsDividable(6, 3));
        }
        [TestMethod]
        public void IsNotDivisible()
        {
            Dummy tested = new Dummy("Steve");
            Assert.IsFalse(tested.IsDividable(5, 3));
        }
        public void NullDivision()
        {
            Dummy tested = new Dummy("Steve");
            Assert.ThrowsException<ArgumentException>(() => tested.IsDividable(2, 0)); //() => kell, mert így nem a függvény értékét adja át paraméterként, hanem magát a függvényt
        }

        [TestClass]
        public class Dummier
        {
            [TestMethod]
            public void DoesntDoShit()
            {
                Assert.IsTrue(true);
            }
        }
    }
}