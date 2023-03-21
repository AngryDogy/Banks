package org.banks.builder;

import org.banks.bankSystem.Bank;
import org.banks.client.Address;
import org.banks.client.Client;
import org.banks.client.Name;
import org.banks.client.PassportNumber;
import org.banks.tools.NullReferenceException;

import java.util.UUID;

/**
 * Builder for Client
 */
public class ClientBuilder {

    private Bank bank;
    private Name name;
    private Address address;
    private PassportNumber passportNumber;

    /**
     * @return new client
     */
    public Client GetClient() {
        return new Client(bank, name, address, passportNumber);
    }

    /**
     * @param bank - client's bank
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * @param firstName - client's first name
     * @param secondName - client's second name
     */
    public void setFullName(String firstName, String secondName) {
        this.name = new Name(firstName, secondName);
    }

    /**
     * @param country - client's country
     * @param city - client's city
     * @param street - client's street
     * @param houseNumber - client's house number
     */
    public void setAddress(String country, String city, String street, int houseNumber) {
        this.address = new Address(country, city, street, houseNumber);
    }

    /**
     * @param passportNumber - client's passport number
     */
    public void setPassportNumber(long passportNumber) {
        this.passportNumber = new PassportNumber(passportNumber);
    }
}
