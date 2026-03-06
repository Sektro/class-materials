using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace HF7
{
    public class Center
    {
        private List<Bank> banks;
        public Center(List<Bank> banks)
        {
            this.banks = banks;
        }

        public int GetBalance(string cNum)
        {
            bool l;
            Bank? bank;
            (l, bank) = FindBank(cNum);
            if (l)
                return bank.GetBalance(cNum);
            else
                return -1;
        }
        public void Transaction(string cNum, int amount)
        {
            bool l;
            Bank? bank;
            (l, bank) = FindBank(cNum);
            if (l)
                bank.Transaction(cNum, amount);
        }
        private (bool, Bank?) FindBank(string cNum)
        {
            bool l = false;
            Bank? bank = null;
            int i = 0;
            while (i < banks.Count && !l)
            {
                l = banks[i].CheckAccount(cNum);
                if (l)
                    bank = banks[i];
                ++i;
            }
            return (l, bank);
        }
    }
}
