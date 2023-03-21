package org.banks.tools;

/**
 * Exception for null reference situations
 */
public class NullReferenceException extends RuntimeException{
    public NullReferenceException(String message) {
        super(message);
    }
}
