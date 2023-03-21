package org.banks.client;

import org.banks.tools.ArgumentException;
import org.banks.tools.NullReferenceException;

/**
 * @param country - client's country
 * @param city - client's city
 * @param street - client's street
 * @param houseNumber - client's house number
 */
public record Address(String country, String city, String street, int houseNumber) {
    public Address {
        if (country == null) {
            throw new NullReferenceException("The string of country is null");
        }
        if (city == null) {
            throw new NullReferenceException("The string of city is null");
        }
        if (street == null) {
            throw new NullReferenceException("The string of street is null");
        }
        if (houseNumber < 0) {
            throw new ArgumentException("The houseNumber is negative");
        }
    }
}
