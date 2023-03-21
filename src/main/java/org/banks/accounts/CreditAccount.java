package org.banks.accounts;

import org.banks.bankSystem.Bank;
import org.banks.client.Client;
import org.banks.tools.ArgumentException;
import org.banks.tools.InvalidOperation;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditAccount extends Account {


    public CreditAccount(UUID id, Client client) {
        super(id, client);
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
        if (this.money.compareTo(BigDecimal.ZERO) < 0) {
            money.add(this.bank.getBankData().getCreditCommission());
        }
        BigDecimal balance = this.money.subtract(money);
        if (balance.compareTo(this.bank.getBankData().getCreditLimit()) < 0) {
            throw new InvalidOperation("The credit limit was exceeded");
        }
        this.money = this.money.subtract(money);
        return money;
    }

    @Override
    public String getType() {
        return "Credit";
    }
}
