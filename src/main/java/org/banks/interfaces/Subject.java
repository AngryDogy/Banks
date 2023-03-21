package org.banks.interfaces;

/**
 * Subject interface is part of the observer pattern
 */
public interface Subject {

    /**
     * @param observer - an observer which can be attached to the subject
     */
    void attach(Observer observer);

    /**
     * @param observer - an observer which can be detached from the subject
     */
    void detach(Observer observer);

    /**
     * @param message - message which can be sent to observer
     */
    void notify(String message);
}
