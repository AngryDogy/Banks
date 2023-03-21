import org.banks.accounts.Account;
import org.banks.accounts.CreditAccount;
import org.banks.accounts.DebitAccount;
import org.banks.accounts.DepositAccount;
import org.banks.bankSystem.Bank;
import org.banks.bankSystem.BankData;
import org.banks.bankSystem.CentralBank;
import org.banks.bankSystem.CentralBankTime;
import org.banks.builder.ClientBuilder;
import org.banks.builder.ClientBuilderDirector;
import org.banks.client.Client;
import org.junit.jupiter.api.Test;
import org.banks.builder.ClientBuilderDirector;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    BankData bankData = new BankData(
            5,
            7,
            new BigDecimal(50000),
            8,
            new BigDecimal(100000),
            10,
            new BigDecimal(3),
            new BigDecimal(100000),
            new BigDecimal(10000));
    @Test
    public void UseCentralBankTime() {
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = new Bank(bankData);
        centralBank.addBank(bank);
        ClientBuilderDirector builder = new ClientBuilderDirector(new ClientBuilder());
        Client client = builder.BuildClient(bank, "Vlad", "Hober", 68872345121L);
        Account account1 = new DebitAccount(UUID.randomUUID(), client);
        Account account2 = new DepositAccount(UUID.randomUUID(), client, 50);
        CentralBankTime time = CentralBankTime.getInstance();
        account1.depositMoney(new BigDecimal(100));
        account2.depositMoney(new BigDecimal(100));
        time.increaseTime(31);
        assertTrue(account1.getMoney().compareTo(BigDecimal.ZERO) > 0);

    }
    @Test
    public void UseNotify() {
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = new Bank(bankData);
        centralBank.addBank(bank);
        ClientBuilderDirector builder = new ClientBuilderDirector(new ClientBuilder());
        Client client = builder.BuildClient(bank, "Vlad", "Hober", 68872345121L);
        Account account1 = new DebitAccount(UUID.randomUUID(), client);
        bank.attach(client);
        centralBank.changeDebitInterest(bank, 10);
        centralBank.changeCreditLimit(bank, new BigDecimal(500000));
        assertTrue(client.getUpdates().size() == 2);
    }
    @Test
    public void UseTransactions()
    {
        CentralBank centralBank = CentralBank.GetInstance();
        Bank bank = new Bank(bankData);
        centralBank.addBank(bank);
        ClientBuilderDirector builder = new ClientBuilderDirector(new ClientBuilder());
        Client client = builder.BuildClient(bank, "Vlad", "Hober", 68872345121L);
        Account account1 = new DebitAccount(UUID.randomUUID(), client);
        Account account2 = new DepositAccount(UUID.randomUUID(), client, 100);
        centralBank.createTransactionDeposit(account1, new BigDecimal(100));
        centralBank.createTransactionDeposit(account2, new BigDecimal(100));
        UUID id = centralBank.createTransactionTransfer(account1, account2, new BigDecimal(100));
        centralBank.cancelTransaction(id);
        assertTrue(account2.getMoney().compareTo(BigDecimal.ZERO) > 0);
    }
}
