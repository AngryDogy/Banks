package org.banks.transactions;

import lombok.Getter;
import lombok.NonNull;
import org.banks.accounts.Account;
import org.banks.tools.ArgumentException;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Abstract class for all type of transactions operations
 */
public abstract class Transaction {
    @Getter
    @NonNull
    protected UUID id;
    @Getter
    protected BigDecimal money;
    @Getter
    @NonNull
    protected Account account;
    @Getter
    protected boolean wasCanceled = false;
    public Transaction(Account account, BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArgumentException("The amount of money is negative");
        }
        this.id = UUID.randomUUID();
        this.account = account;
        this.money = money;
    }

    /**
     * The method cancels transaction operation
     */
    public abstract void Cancel();
}
