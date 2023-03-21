package org.banks.tools;

/**
 * Exception for invalid operations
 */
public class InvalidOperation extends RuntimeException {
    public InvalidOperation(String message) {
        super(message);
    }
}
