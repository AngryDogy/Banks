package org.banks.tools;

/**
 * Exception for invalid argument situations
 */
public class ArgumentException extends RuntimeException{
    public ArgumentException(String message) {
        super(message);
    }
}
