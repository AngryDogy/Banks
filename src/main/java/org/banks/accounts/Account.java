package org.banks.accounts;
import lombok.*;
import org.banks.bankSystem.Bank;
import org.banks.client.Client;
import org.banks.tools.ArgumentException;

import java.math.BigDecimal;
import java.util.UUID;


/**
 * Class of bank's account
 */
public abstract class Account {
    protected final int DaysInYear = 365;
    protected int days = 0;
    protected BigDecimal interestMoney = BigDecimal.ZERO;
    @Getter
    @NonNull
    protected UUID id;
    @Getter
    @NonNull
    protected Bank bank;
    @Getter
    @NonNull
    protected Client client;
    @Getter
    protected BigDecimal money = BigDecimal.ZERO;


    public Account (UUID id, Client client) {
        this.id = id;
        this.bank = client.getBank();
        this.bank.addAccount(this);
        this.client = client;
        this.client.addAccount(this);
    }

    /**
     * @param money - amount of money which will be deposited
     */
    public void depositMoney(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArgumentException("Amount of money is negative");
        }
        this.money = this.money.add(money);
    }

    /**
     * @param money - amount of money which will be withdrawn
     * @return amount of money which was withdrawn
     */
    public abstract BigDecimal withdrawMoney(BigDecimal money);
    public abstract  String getType();
}
