package org.banks.client;

import lombok.Getter;
import lombok.NonNull;
import org.banks.accounts.Account;
import org.banks.bankSystem.Bank;
import org.banks.interfaces.Observer;
import org.banks.tools.NullReferenceException;

import java.util.*;

/**
 * The class of bank's client
 */
public final class Client implements Observer {

    @Getter
    private UUID id;
    @Getter
    @NonNull
    private Name name;
    @Getter
    private Address address;
    @Getter
    private PassportNumber passportNumber;
    @Getter
    private Bank bank;
    @Getter
    private boolean isSuspicious;
    @Getter
    private boolean isSubscribed;
    private List<Account> accounts;
    private List<String> updates;

    public Client (@NonNull Bank bank, @NonNull Name name, Address address, PassportNumber passportNumber) {
        if (name == null) {
            throw new NullReferenceException("The name is null");
        }
        this.id = UUID.randomUUID();
        this.bank = bank;
        this.bank.addClient(this);
        this.name = name;
        this.address = address;
        this.passportNumber = passportNumber;
        if (this.passportNumber != null || this.address != null) {
            isSuspicious = false;
        }
        else {
            isSuspicious = true;
        }
        isSubscribed = false;
        accounts = new ArrayList<Account>();
        updates = new ArrayList<String>();
    }

    /**
     * @return an unmodifiable list of accounts
     */
    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    /**
     * @return an unmodifiable list of updates
     */
    public List<String> getUpdates() {
        return Collections.unmodifiableList(updates);
    }

    /**
     * @param account - the account which will be added to the client
     */
    public void addAccount(Account account) {
        if (account == null) {
            throw new NullReferenceException("The account is null");
        }
        accounts.add(account);
    }

    /**
     * @param message - message which can be given to observer
     */
    public void update(String message) {
        if (message == null) {
            throw new NullReferenceException("Message is null");
        }
        updates.add(message);
    }

    /**
     * @param value - new value of the isSubscribed variable
     */
    public void changeSubscribeStatus(boolean value) {
        this.isSubscribed = value;
    }
}
