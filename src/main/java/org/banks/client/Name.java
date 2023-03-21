package org.banks.client;

import org.banks.tools.NullReferenceException;

/**
 * @param firstName - first name of a client
 * @param secondName - second name of a client
 */
public record Name(String firstName, String secondName) {
    public Name {
        if (firstName == null || secondName == null) {
            throw new NullReferenceException("The name is null");
        }
    }
}
