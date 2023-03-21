package org.banks.accounts;

import lombok.Getter;
import org.banks.bankSystem.Bank;
import org.banks.client.Client;
import org.banks.tools.ArgumentException;
import org.banks.tools.InvalidOperation;

import java.math.BigDecimal;
import java.util.UUID;

public class DepositAccount extends Account {
    @Getter
    private int term;
    private double incomeProcent;
    public DepositAccount(UUID id, Client client, int term) {
        super(id, client);
        if (term < 0) {
            throw new ArgumentException("The term is negative");
        }
        this.term = term;
    }

    /**
     * @param money - amount of money which will be withdrawn
     * @return  amount of money which was withdrawn
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
        if (this.term >= this.bank.getTime().getCurrentTime()) {
            this.money = this.money.subtract(money);
            return money;
        }
        throw new InvalidOperation("Can't withdraw money, term of the account is not over");
    }

    @Override
    public String getType() {
        return "Deposit";
    }

    private void determineProcent()
    {
        if (this.money.compareTo(this.bank.getBankData().getLowDepositBorder()) < 0)
        {
            this.incomeProcent = this.bank.getBankData().getLowDepositInterest() / DaysInYear;
            return;
        }

        if (this.money.compareTo(this.bank.getBankData().getLowDepositBorder()) >= 0 && this.money.compareTo(this.bank.getBankData().getMiddleDepositBorder()) < 0)
        {
            this.incomeProcent = this.bank.getBankData().getMiddleDepositInterest() / DaysInYear;
            return;
        }

        this.incomeProcent = this.bank.getBankData().getHighDepositInterest() / DaysInYear;
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
            determineProcent();
            this.interestMoney = this.interestMoney.add(this.money.multiply(new BigDecimal(this.incomeProcent)));
            days++;
        }
    }
}
