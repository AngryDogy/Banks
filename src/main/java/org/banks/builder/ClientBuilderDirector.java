package org.banks.builder;

import org.banks.bankSystem.Bank;
import org.banks.client.Client;

/**
 * Director for client builder
 */
public class ClientBuilderDirector {

    private ClientBuilder clientBuilder;

    public ClientBuilderDirector(ClientBuilder clientBuilder)
    {
        this.clientBuilder = clientBuilder;
    }

    /**
     * @param bank - client's bank
     * @param firstName - client's first name
     * @param secondName - client's second name
     * @return client
     */
    public Client BuildClient(Bank bank, String firstName, String secondName)
    {
        clientBuilder.setBank(bank);
        clientBuilder.setFullName(firstName, secondName);
        return clientBuilder.GetClient();
    }

    /**
     * @param bank - client's bank
     * @param firstName - client's first name
     * @param secondName - client's second name
     * @param country - client's country
     * @param city - client's city
     * @param street - client's street
     * @param houseNumber - client's house number
     * @return client
     */
    public Client BuildCLient(Bank bank, String firstName, String secondName, String country, String city, String street, int houseNumber)
    {
        clientBuilder.setBank(bank);
        clientBuilder.setFullName(firstName, secondName);
        clientBuilder.setAddress(country, city, street, houseNumber);
        return clientBuilder.GetClient();
    }

    /**
     * @param bank - client's bank
     * @param firstName - client's first name
     * @param secondName - client's second name
     * @param passportNumber - client's passport number
     * @return
     */
    public Client  BuildClient(Bank bank, String firstName, String secondName, long passportNumber)
    {
        clientBuilder.setBank(bank);
        clientBuilder.setFullName(firstName, secondName);
        clientBuilder.setPassportNumber(passportNumber);
        return clientBuilder.GetClient();
    }

    /**
     * @param bank - client's name
     * @param firstName - client's first name
     * @param secondName - client's second name
     * @param country - client's country
     * @param city - client's city
     * @param street - client's street
     * @param houseNumber - client's house number
     * @param passportNumber - client's passport number
     * @return client
     */
    public Client BuildClient(Bank bank, String firstName, String secondName, String country, String city, String street, int houseNumber, long passportNumber)
    {
        clientBuilder.setBank(bank);
        clientBuilder.setFullName(firstName, secondName);
        clientBuilder.setAddress(country, city, street, houseNumber);
        clientBuilder.setPassportNumber(passportNumber);
        return clientBuilder.GetClient();

    }
}
