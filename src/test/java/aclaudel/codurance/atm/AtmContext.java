package aclaudel.codurance.atm;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;

public class AtmContext {
    // constants
    public static final int DEFAULT_INITIAL_BALANCE = 1;
    public static final UUID AN_ACCOUNT_ID = randomUUID();
    public static final int NEGATIVE_AMOUNT_OF_MONEY = -10;
    public static final AtmContext INSTANCE = new AtmContext();

    // mocks
    public AccountRepository accountRepositoryMock;

    // context
    public Atm atm;

    // test variables
    public UUID accountId;
    public int amount;

    private AtmContext() {}

    public static AtmContext getInstance() {
        return INSTANCE;
    }

    public void before() {
        accountRepositoryMock = mock(AccountRepository.class);
        atm = new Atm(accountRepositoryMock);
    }
}
