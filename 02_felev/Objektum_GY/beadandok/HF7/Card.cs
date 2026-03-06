using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HF7
{
    public class Card
    {
        public string cNum;
        private string PIN;
        public Card(string cNum, string PIN)
        {
            this.cNum = cNum;
            this.PIN = PIN;
        }

        public bool CheckPIN(string p)
        {
            return PIN == p;
        }
        public void SetPIN(string p)
        {
            PIN = p;
        }
    }
}
