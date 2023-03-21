package org.banks.bankSystem;

import lombok.Getter;
import lombok.NonNull;
import org.banks.tools.ArgumentException;

/**
 * This class controls time
 */
public class CentralBankTime {
    private static CentralBankTime instance;
    private CentralBank bank;
    @Getter
    private int currentTime = 0;
    private CentralBankTime() {}

    /**
     * @return the instance of CentralBankTime
     */
    public static CentralBankTime getInstance() {
        if (instance == null) {
            instance = new CentralBankTime();
        }
        return instance;
    }

    /**
     * @param time - amount of "seconds" to increase time
     */
    public void increaseTime(int time) {
        if (time < 0) {
            throw new ArgumentException("The time is negative");
        }
        currentTime += time;
        if (this.bank != null) {
            this.bank.interestPayment(time);
        }
    }

    /**
     * @param bank - CentralBank object which would be added as a field of the class
     */
    public void addCentralBank(CentralBank bank) {
        this.bank = bank;
    }

}
