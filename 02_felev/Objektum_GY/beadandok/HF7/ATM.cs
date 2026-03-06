using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HF7
{
    public class ATM
    {
        private Center center;
        public string site;

        public ATM(string site, Center center)
        {
            this.site = site;
            this.center = center;
        }

        public void Process(Customer c)
        {
            Card card;
            int a;

            card = c.ProvidesCard();
            if (card.CheckPIN(c.ProvidesPIN()))
            {
                a = c.RequestMoney();
                if (center.GetBalance(card.cNum) >= a)
                    center.Transaction(card.cNum, -a);
            }
        }
    }
}
