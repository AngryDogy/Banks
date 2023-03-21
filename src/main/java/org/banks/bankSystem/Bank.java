package org.banks.bankSystem;

import lombok.Getter;
import lombok.NonNull;
import org.banks.accounts.Account;
import org.banks.accounts.DebitAccount;
import org.banks.accounts.DepositAccount;
import org.banks.client.Client;
import org.banks.interfaces.Observer;
import org.banks.interfaces.Subject;
import org.banks.tools.ArgumentException;
import org.banks.tools.NullReferenceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Bank which contains clients and their accounts
 */
public class Bank implements Subject {

    @Getter
    private UUID id;
    private List<Client> clients;
    private List<Account> accounts;
    @Getter
    @NonNull
    private BankData bankData;
    @Getter
    @NonNull
    private CentralBankTime time;

    public Bank(BankData bankData) {
        id = UUID.randomUUID();
        clients = new ArrayList<Client>();
        accounts = new ArrayList<Account>();
        this.bankData = bankData;
        time = CentralBankTime.getInstance();
    }

    /**
     * @return the list of clients
     */
    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    /**
     * @return the list of accounts
     */
    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }
    /**
     * @param client - client which will be added to the bank
     */
    public void addClient(Client client) {
        if (client == null) {
            throw new NullReferenceException("The client is null");
        }
        this.clients.add(client);
    }

    /**
     * @param account - account which will be added to the bank
     */
    public void addAccount(Account account) {
        if (account == null) {
            throw new NullReferenceException("The account is null");
        }
        this.accounts.add(account);
    }

    /**
     * @param observer - an observer which can be attached to the subject
     */
    public void attach(Observer observer) {
        Client client = (Client)observer;
        if (client == null) {
            throw new NullReferenceException("The client is null");
        }
        client.changeSubscribeStatus(true);
    }


    /**
     * @param observer - an observer which can be detached from the subject
     */
    public void detach(Observer observer) {
        Client client = (Client)observer;
        if (client == null) {
            throw new NullReferenceException("The client is null");
        }
        client.changeSubscribeStatus(false);

    }

    /**
     * @param message - message which can be sent to observer
     */
    public void notify(String message) {
        if (message == null) {
            throw new NullReferenceException("Message is null");
        }
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).isSubscribed()) {
                clients.get(i).update(message);
            }
        }
    }

    /**
     * @param time - amount of time
     */
    public void interestPayment(int time) {
        if (time < 0) {
            throw  new ArgumentException("Time is invalid");
        }
        for (Account account : this.accounts) {
            if (account.getType() == "Debit") {
                DebitAccount debitAccount = (DebitAccount)account;
                debitAccount.interestPay(time);
            }
            if (account.getType() == "Deposit") {
                DepositAccount depositAccount = (DepositAccount)account;
                depositAccount.interestPay(time);
            }
        }
    }
}
