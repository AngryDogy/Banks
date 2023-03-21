package org.banks.bankSystem;

import lombok.Getter;
import org.banks.tools.ArgumentException;

import java.math.BigDecimal;

/**
 * The class contains bank's data
 */
public class BankData {
    @Getter
    private double DebitInterest;
    @Getter
    private double LowDepositInterest;
    @Getter
    private BigDecimal LowDepositBorder;
    @Getter
    private double MiddleDepositInterest;
    @Getter
    private BigDecimal MiddleDepositBorder;
    @Getter
    private double HighDepositInterest;
    @Getter
    private BigDecimal CreditCommission;
    @Getter
    private BigDecimal CreditLimit;
    @Getter
    private BigDecimal LimitForSuspicious;
    public BankData(
            double debitInterest,
            double lowDepositInterest,
            BigDecimal lowDepositBorder,
            double middleDepositInterest,
            BigDecimal middleDepositBorder,
            double highDepositInterest,
            BigDecimal creditCommission,
            BigDecimal creditLimit,
            BigDecimal limitForSuspicious) {
        if (!checkProcentData(debitInterest))
            throw new ArgumentException("DebitInterest is invalid");
        if (!checkProcentData(lowDepositInterest))
            throw new ArgumentException("LowDepositInterest is invalid");
        if (!checkValueData(lowDepositBorder))
            throw new ArgumentException("LowDepositBorder is invalid");
        if (!checkProcentData(middleDepositInterest))
            throw new ArgumentException("MiddleDepositInterest is invalid");
        if (!checkValueData(middleDepositBorder))
            throw new ArgumentException("MiddleDepositBorder is invalid");
        if (!checkProcentData(highDepositInterest))
            throw new ArgumentException("HighDepositInterest is invalid");
        if (!checkValueData(creditCommission))
            throw new ArgumentException("CreditCommission t is invalid");
        if (!checkValueData(creditLimit))
            throw new ArgumentException("CreditLimit is invalid");
        if (!checkValueData(limitForSuspicious))
            throw new ArgumentException("LimitForSuspicious is invalid");
        DebitInterest = debitInterest;
        LowDepositInterest = lowDepositInterest;
        LowDepositBorder = lowDepositBorder;
        MiddleDepositInterest = middleDepositInterest;
        MiddleDepositBorder = middleDepositBorder;
        HighDepositInterest = highDepositInterest;
        CreditCommission = creditCommission;
        CreditLimit = creditLimit;
        LimitForSuspicious = limitForSuspicious;
    }

    private boolean checkProcentData(double procent) {
        if (procent < 0 || procent > 100)
            return false;
        return true;
    }

    private boolean checkValueData(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0)
            return false;
        return true;
    }

    /**
     * @param debitInterest - new debitInterest
     */
    public void changeDebitInterest(double debitInterest) {
        if (!checkProcentData(debitInterest))
            throw new ArgumentException("debitInterest is invalid");
        DebitInterest = debitInterest;
    }

    /**
     * @param lowDepositInterest - new lowDepositInterest
     */
    public void changeLowDepositInterest(double lowDepositInterest) {
        if (!checkProcentData(lowDepositInterest))
            throw new ArgumentException("LowDepositInterest is invalid");
        LowDepositInterest = lowDepositInterest;
    }

    /**
     * @param middleDepositInterest - new middleDepositInterest
     */
    public void changeMiddleDepositInterest(double middleDepositInterest) {
        if (!checkProcentData(middleDepositInterest))
            throw new ArgumentException("MiddleDepositInterest is invalid");
        MiddleDepositInterest = middleDepositInterest;
    }

    /**
     * @param highDepositInterest - new highDepositInterest
     */
    public void changeHighDepositInterest(double highDepositInterest) {
        if (!checkProcentData(highDepositInterest))
            throw new ArgumentException("HighDepositInterest is invalid");
        HighDepositInterest = highDepositInterest;
    }

    /**
     * @param creditCommission - new creditCommission
     */
    public void changeCreditCommission(BigDecimal creditCommission) {
        if (!checkValueData(creditCommission))
            throw new ArgumentException("CreditCommission is invalid");
        CreditCommission = creditCommission;
    }

    /**
     * @param creditLimit - new changeCreditLimit
     */
    public void changeCreditLimit(double creditLimit) {
        if (!checkValueData(CreditLimit))
            throw new ArgumentException("CreditLimit is invalid");
        CreditLimit = CreditLimit;
    }

    /**
     * @param lowDepositBorder - new changeLowDepositBorder
     */
    public void changeLowDepositBorder(BigDecimal lowDepositBorder) {
        if (!checkValueData(lowDepositBorder))
            throw new ArgumentException("LowDepositBorder is invalid");
        LowDepositBorder = lowDepositBorder;
    }

    /**
     * @param middleDepositBorder - new middleDepositBorder
     */
    public void changeMiddleDepositBorder(BigDecimal middleDepositBorder) {
        if (!checkValueData(middleDepositBorder))
            throw new ArgumentException("MiddleDepositBorder is invalid");
        MiddleDepositBorder = middleDepositBorder;
    }

    /**
     * @param limitForSuspicious - new changeLimitForSuspicious
     */
    public void changeLimitForSuspicious(BigDecimal limitForSuspicious) {
        if (!checkValueData(limitForSuspicious))
            throw new ArgumentException("LimitForSuspicious is invalid");
        LimitForSuspicious = limitForSuspicious;
    }
}
