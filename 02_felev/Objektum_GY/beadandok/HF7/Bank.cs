using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HF7
{
    public class Bank
    {
        private List<Account> accounts;

        public Bank()
        {
            accounts = new List<Account>();
        }

        public void OpenAccount(string cNum, Customer C)
        {
            Account account = new Account(cNum);
            accounts.Add(account);
            C.AddAccount(account);
        }

        public void ProvidesCard(string cNum)
        {
            int i = 0;
            if (CheckAccount(cNum))
            {
                Card card = new Card(cNum, "1234");
                while (accounts[i].accNum != cNum)
                    ++i;
                accounts[i].cards.Add(card);
            }
        }

        public int GetBalance(string cNum)
        {
            bool l;
            Account? account;
            (l, account) = FindAccount(cNum);

            if (l)
                return account.GetBalance();
            else
                return -1;
        }

        public void Transaction(string cNum, int amount)
        {
            (bool l, Account? account) = FindAccount(cNum);
            if (l)
                account.Change(amount);
        }

        public bool CheckAccount(string cNum)
        {
            bool l = false;
            int i = 0;
            while (i < accounts.Count && !l)
            {
                if (accounts[i].accNum == cNum)
                {
                    l = true;
                }
                ++i;
            }
            return l;
        }

        private (bool, Account?) FindAccount(string cNum)
        {
            bool l = false;
            Account? account = null;
            int i = 0;
            while (i < accounts.Count && !l)
            {
                if (accounts[i].accNum == cNum)
                {
                    l = true;
                    account = accounts[i];
                }
                ++i;
            }
            return (l, account);
        }
    }
}
