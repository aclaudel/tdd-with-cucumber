package aclaudel.codurance.context;

import aclaudel.codurance.atm.AccountRepository;
import aclaudel.codurance.atm.Atm;
import io.cucumber.java.Before;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.mock;

public class AtmContext {
    // constants
    protected static final int DEFAULT_INITIAL_BALANCE = 1;
    protected static final UUID AN_ACCOUNT_ID = randomUUID();
    protected static final int NEGATIVE_AMOUNT_OF_MONEY = -10;

    // mocks
    protected static AccountRepository accountRepositoryMock;

    // context
    protected static Atm atm;

    // test variables
    protected static UUID accountId;
    protected static int amount;

    protected AtmContext() {}

    protected void before() {
        accountRepositoryMock = mock(AccountRepository.class);
        atm = new Atm(accountRepositoryMock);
    }
}
