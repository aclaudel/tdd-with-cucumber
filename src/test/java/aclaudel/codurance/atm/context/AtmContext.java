package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.AccountRepository;
import aclaudel.codurance.atm.Atm;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AtmContext {
    // constants
    public static final int DEFAULT_INITIAL_BALANCE = 1;
    public static final UUID AN_ACCOUNT_ID = randomUUID();
    public static final int NEGATIVE_AMOUNT_OF_MONEY = -10;

    // mocks
    private static AccountRepository accountRepositoryMock;

    // sut
    private static Atm atm;

    // variables
    private static UUID accountId;
    private static int amount;

    private AtmContext() {}

    public static void setup() {
        accountRepositoryMock = mock(AccountRepository.class);
        atm = new Atm(accountRepositoryMock);
    }

    public static void setup_account(int initialBalance) {
        accountId = AN_ACCOUNT_ID;
        Account account = new Account(accountId, initialBalance);
        given(accountRepositoryMock.getById(accountId)).willReturn(account);
    }

    public static void setup_a_not_existing_account() {
        accountId = AN_ACCOUNT_ID;
        given(accountRepositoryMock.getById(accountId)).willReturn(null);
    }

    public static void setup_amount_of_money(int amountOfMoney) {
        amount = amountOfMoney;
    }

    public static void assert_account_was_saved_with(int finalBalance) {
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepositoryMock).save(accountCaptor.capture());

        Account savedAccount = accountCaptor.getValue();
        assertEquals(accountId, savedAccount.getId());
        assertEquals(finalBalance, savedAccount.getBalance());
    }

    public static void do_withdraw() {
        atm.withdraw(accountId, amount);
    }

    public static void do_deposit() {
        atm.deposit(accountId, amount);
    }
}
