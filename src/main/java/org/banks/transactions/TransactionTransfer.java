package org.banks.transactions;

import lombok.Getter;
import lombok.NonNull;
import org.banks.accounts.Account;
import org.banks.tools.InvalidOperation;

import java.math.BigDecimal;

/**
 * Class for transfer operations
 */
public class TransactionTransfer extends Transaction {
    @Getter
    @NonNull
    private Account toAccount;
    public TransactionTransfer(Account account, BigDecimal money, Account toAccount) {
        super(account, money);
        this.toAccount = toAccount;
        this.account.withdrawMoney(money);
        this.toAccount.depositMoney(money);
    }

    /**
     * The method cancels transaction transfer operation
     */
    @Override
    public void Cancel() {
        if (this.wasCanceled) {
            throw new InvalidOperation("The transaction was already canceled");
        }
        this.toAccount.withdrawMoney(money);
        this.account.depositMoney(money);
        this.wasCanceled = true;
    }
}
