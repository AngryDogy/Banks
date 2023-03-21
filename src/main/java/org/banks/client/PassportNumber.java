package org.banks.client;

import org.banks.tools.ArgumentException;

/**
 * @param number - password number
 */
public record PassportNumber(long number) {
    public PassportNumber {
        if (number < 10000000000L) {
            throw new ArgumentException("The number is invalid");
        }
    }
}
