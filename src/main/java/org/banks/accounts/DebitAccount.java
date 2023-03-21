package org.banks.accounts;

import org.banks.bankSystem.Bank;
import org.banks.client.Client;
import org.banks.tools.ArgumentException;
import org.banks.tools.InvalidOperation;

import java.math.BigDecimal;
import java.util.UUID;


public class DebitAccount extends Account {

    public DebitAccount(UUID id, Client client) {
        super(id, client);
    }

    /**
     * @param money - amount of money which will be withdrawn
     * @return amount of money which was withdrawn
     */
    @Override
    public BigDecimal withdrawMoney(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArgumentException("Amount of money is negative");
        }
        if (this.client.isSuspicious()) {
            if (this.money.compareTo(this.bank.getBankData().getLimitForSuspicious()) < 0) {
                throw new InvalidOperation("Can't withdraw this amount of money. The client is suspicious");
            }
        }
        BigDecimal balance = this.money.subtract(money);
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidOperation("The account doesn't have enough money");
        }
        this.money = this.money.subtract(money);
        return money;
    }

    @Override
    public String getType() {
        return "Debit";
    }

    /**
     * @param time - amount of time passed
     */
    public void interestPay(int time)
    {
        if (time < 0)
        {
            throw new ArgumentException("Time can't be negative");
        }

        for (int i = 0; i < time; i++)
        {
            if (this.days == 30)
            {
                this.depositMoney(this.interestMoney);
                days = 0;
                this.interestMoney = BigDecimal.ZERO;
            }

            this.interestMoney = this.interestMoney.add(this.money.multiply(new BigDecimal(this.bank.getBankData().getDebitInterest() / DaysInYear)));
            days++;
        }
    }
}
