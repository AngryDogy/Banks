package org.banks.transactions;

import org.banks.accounts.Account;
import org.banks.tools.InvalidOperation;

import java.math.BigDecimal;

/**
 * Class for withdraw operations
 */
public class TransactionWithdraw extends Transaction {
    public TransactionWithdraw(Account account, BigDecimal money) {
        super(account, money);
        this.account.withdrawMoney(money);
    }

    /**
     * The method cancels transaction withdraw operation
     */
    @Override
    public void Cancel() {
        if (this.wasCanceled) {
            throw new InvalidOperation("The transaction was already canceled");
        }
        this.account.depositMoney(money);
        this.wasCanceled = true;
    }
}
