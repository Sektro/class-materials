using System.Globalization;

namespace Gyak3
{
    public class LTMException : Exception
    {
        //public LTMException(String msg) : base("Tök mindegy mit adtunk át") { }  <--- jobb oldalit írja ki, itt mindegy mit adunk át neki
        public LTMException(String msg) : base(msg)
        {

        }
    }
}
