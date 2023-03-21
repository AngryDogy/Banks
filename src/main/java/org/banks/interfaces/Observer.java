package org.banks.interfaces;

/**
 * Observer interface is part of the observer pattern
 */
public interface Observer {

    /**
     * @param message - message which can be given to observer
     */
    void update(String message);
}
