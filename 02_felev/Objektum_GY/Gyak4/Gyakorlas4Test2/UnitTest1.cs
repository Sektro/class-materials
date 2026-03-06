using gyakorlas;

namespace Gyakorlas4Test2
{
    [TestClass]
    public class Barmi
    {
        [TestMethod]
        public void EmptyOnCreation()
        {
            Map map = new Map();
            Assert.AreEqual(0, map.Count());
            Assert.ThrowsException<Exception>(() => map.Remove(1));
        }
    }
}