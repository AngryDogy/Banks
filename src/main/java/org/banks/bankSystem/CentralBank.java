package org.banks.bankSystem;

import lombok.Getter;
import org.banks.accounts.Account;
import org.banks.client.Client;
import org.banks.tools.ArgumentException;
import org.banks.tools.InvalidOperation;
import org.banks.tools.NullReferenceException;
import org.banks.transactions.Transaction;
import org.banks.transactions.TransactionDeposit;
import org.banks.transactions.TransactionTransfer;
import org.banks.transactions.TransactionWithdraw;

import java.math.BigDecimal;
import java.util.*;

/**
 * The singleton class's the main repository for transactions and banks
 */
public class CentralBank {
    private static CentralBank instance = null;
    @Getter
    private CentralBankTime time = CentralBankTime.getInstance();
    private List<Bank> banks;
    private List<Transaction> transactions;
    private CentralBank() {
        banks = new ArrayList<Bank>();
        transactions = new ArrayList<Transaction>();
        time.addCentralBank(this);
    }

    /**
     * @return The instance of the CentralBank
     */
    public static CentralBank GetInstance() {
        if (instance == null) {
            instance = new CentralBank();
        }
        return instance;
    }

    /**
     * @param bank - the bank to be added to the collection
     */
    public void addBank(Bank bank) {
        if (bank == null) {
            throw new NullReferenceException("The bank is null");
        }
        this.banks.add(bank);
    }

    /**
     * @return An unmodifiable list of banks
     */
    public List<Bank> getBanks() {
        return Collections.unmodifiableList(banks);
    }

    /**
     * @return An unmodifiable list of transactions
     */
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    /**
     * @param time - amount of time
     */
    public void interestPayment(int time)
    {
        if (time < 0)
        {
            throw new ArgumentException("Time is invalid");
        }

        for (Bank bank : this.banks)
        {
            bank.interestPayment(time);
        }
    }


    /**
     * @param account - client's account
     * @param money - amount of money
     * @return id of created transaction
     */
    public UUID createTransactionDeposit(Account account, BigDecimal money)
    {
        var transaction = new TransactionDeposit(account, money);
        this.transactions.add(transaction);
        return transaction.getId();
    }

    /**
     * @param account - client's account
     * @param money - amount of money
     * @return id of created transaction
     */
    public UUID createTransactionWithdraw(Account account, BigDecimal money)
    {
        var transaction = new TransactionWithdraw(account, money);
        transactions.add(transaction);
        return transaction.getId();
    }

    /**
     * @param account - client's account
     * @param toAccount - other client's account
     * @param money - amount of money
     * @return id of created transaction
     */
    public UUID createTransactionTransfer(Account account, Account toAccount, BigDecimal money)
    {
        var transaction = new TransactionTransfer(account, money, toAccount);
        this.transactions.add(transaction);
        return transaction.getId();
    }

    /**
     * @param id - transaction's id
     */
    public void cancelTransaction(UUID id)
    {
        for (Transaction transaction : this.transactions) {
            if (transaction.getId() == id) {
                transaction.Cancel();
                return;
            }
        }
        throw new InvalidOperation("This transaction doesn't exist");
    }

    /**
     * @param id - bank's id
     * @return bank
     */
    public Bank getBank(UUID id)
    {
        Bank bank = this.banks.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (bank == null) {
            throw new InvalidOperation("This bank doesn't exit");
        }
        return bank;
    }

    /**
     * @param id - client's id
     * @return client
     */
    public Client getClient(UUID id)
    {
        for (Bank bank : this.banks) {

            Client client = bank.getClients().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
            if (client != null) {
                return client;
            }
        }
        throw new InvalidOperation("This client doesn't exit");
    }

    /**
     * @param id - account's id
     * @return account
     */
    public Account getAccount(UUID id)
    {
        for (Bank bank : this.banks) {
            Account account = bank.getAccounts().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
            if (account != null) {
                return account;
            }
        }
        throw new InvalidOperation("This account doesn't exit");

    }

    /**
     * @param bank - bank
     * @param debitInterest - new debit interest
     */
    public void changeDebitInterest(Bank bank, double debitInterest)
    {
        if (bank == null) {
            throw new NullReferenceException("Bank in null");
        }

        bank.getBankData().changeDebitInterest(debitInterest);
        bank.notify("DebitInterest was changed. New value: " + debitInterest);
    }

    /**
     * @param bank - bank
     * @param lowDepositInterest - new los deposit interest
     */
    public void changeLowDepositInterest(Bank bank, double lowDepositInterest)
    {
        if (bank == null) {
            throw new NullReferenceException("Bank in null");
        }

        bank.getBankData().changeLowDepositInterest(lowDepositInterest);
        bank.notify("LowDepositInterest was changed. New value: " + lowDepositInterest);
    }

    /**
     * @param bank - bank
     * @param middleDepositInterest - new middle deposit interest
     */
    public void changeMiddleDepositInterest(Bank bank, BigDecimal middleDepositInterest)
    {
        if (bank == null) {
            throw new NullReferenceException("Bank in null");
        }

        bank.getBankData().changeMiddleDepositBorder(middleDepositInterest);
        bank.notify("MiddleDepositInterest was changed. New value: " + middleDepositInterest);
    }

    /**
     * @param bank - bank
     * @param highDepositInterest - new high deposit interest
     */
    public void changeHighDepositInterest(Bank bank, BigDecimal highDepositInterest)
    {
        if (bank == null) {
            throw new NullReferenceException("Bank in null");
        }

        bank.getBankData().changeMiddleDepositBorder(highDepositInterest);
        bank.notify("HighDepositInterest was changed. New value: " + highDepositInterest);
    }

    /**
     * @param bank - bank
     * @param creditCommission - new credit commision
     */
    public void changeCreditCommission(Bank bank, BigDecimal creditCommission)
    {
        if (bank == null) {
            throw new NullReferenceException("Bank in null");
        }

        bank.getBankData().changeCreditCommission(creditCommission);
        bank.notify("CreditCommission was changed. New value: " + creditCommission);
    }

    /**
     * @param bank - bank
     * @param creditLimit - new credit limit
     */
    public void changeCreditLimit(Bank bank, BigDecimal creditLimit)
    {
        if (bank == null) {
            throw new NullReferenceException("Bank in null");
        }

        bank.getBankData().changeMiddleDepositBorder(creditLimit);
        bank.notify("CreditLimit was changed. New value: " + creditLimit);
    }

    /**
     * @param bank - bank
     * @param lowDepositBorder - low deposit interest
     */
    public void changeLowDepositBorder(Bank bank, BigDecimal lowDepositBorder)
    {
        if (bank == null) {
            throw new NullReferenceException("Bank in null");
        }

        bank.getBankData().changeMiddleDepositBorder(lowDepositBorder);
        bank.notify("LowDepositBorder was changed. New value: " + lowDepositBorder);
    }

    /**
     * @param bank - bank
     * @param middleDepositBorder - new middle interest
     */
    public void changeMiddleDepositBorder(Bank bank, BigDecimal middleDepositBorder)
    {
        if (bank == null) {
            throw new NullReferenceException("Bank in null");
        }

        bank.getBankData().changeMiddleDepositBorder(middleDepositBorder);
        bank.notify("MiddleDepositBorder was changed. New value: " + middleDepositBorder);
    }

    /**
     * @param bank - bank
     * @param limitForSuspicious - new limit for suspicious
     */
    public void changeLimitForSuspicious(Bank bank, BigDecimal limitForSuspicious)
    {
        if (bank == null) {
            throw new NullReferenceException("Bank in null");
        }

        bank.getBankData().changeLimitForSuspicious(limitForSuspicious);
        bank.notify("LimitForSuspicious was changed. New value: " + limitForSuspicious);
    }


}
