package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.AccountRepository;
import aclaudel.codurance.atm.Atm;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;

public class AtmContext {
    // constants
    public static final int DEFAULT_INITIAL_BALANCE = 1;
    public static final UUID AN_ACCOUNT_ID = randomUUID();
    public static final int NEGATIVE_AMOUNT_OF_MONEY = -10;

    // mocks
    public static AccountRepository accountRepositoryMock;

    // context
    public static Atm atm;

    // test variables
    public static UUID accountId;
    public static int amount;
    public static Exception generatedError;

    private AtmContext() {}

    public static void setup() {
        accountRepositoryMock = mock(AccountRepository.class);
        atm = new Atm(accountRepositoryMock);
    }
}
