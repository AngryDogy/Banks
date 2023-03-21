package org.banks.transactions;

import org.banks.accounts.Account;
import org.banks.tools.InvalidOperation;

import java.math.BigDecimal;

/**
 * Class for deposit operations
 */
public class TransactionDeposit extends Transaction{

    public TransactionDeposit(Account account, BigDecimal money) {
        super(account, money);
        this.account.depositMoney(money);
    }


    /**
     * The method cancels transaction deposit operation
     */
    @Override
    public void Cancel() {
        if (this.wasCanceled) {
            throw new InvalidOperation("The transaction was already canceled");
        }
        this.account.withdrawMoney(money);
        this.wasCanceled = true;
    }
}
