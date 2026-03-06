using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HF7
{
    public class Customer
    {
        private string PIN;
        private int withdraw;
        private List<Account> accounts;

        public Customer(string PIN, int withdraw)
        {
            this.PIN = PIN;
            this.withdraw = withdraw;
            accounts = new List<Account>();
        }

        public void Withdrawal(ATM atm)
        {
            atm.Process(this);
        }
        public Card ProvidesCard()
        {
                return accounts[0].cards[0];
        }
        public string ProvidesPIN()
        {
            return PIN;
        }
        public int RequestMoney()
        {
            return withdraw;
        }
        public void AddAccount(Account a)
        {
            accounts.Add(a);
        }
    }
}
