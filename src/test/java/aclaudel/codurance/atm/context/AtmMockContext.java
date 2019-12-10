package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.AccountRepository;
import aclaudel.codurance.atm.Atm;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AtmMockContext implements AtmContext {

    // mocks
    private AccountRepository accountRepositoryMock;

    // sut
    private Atm atm;

    // variables
    private UUID accountId;
    private int amount;

    @Override
    public void setup() {
        accountRepositoryMock = mock(AccountRepository.class);
        atm = new Atm(accountRepositoryMock);
    }

    @Override
    public void setup_account(int initialBalance) {
        accountId = AN_ACCOUNT_ID;
        Account account = new Account(accountId, initialBalance);
        given(accountRepositoryMock.getById(accountId)).willReturn(account);
    }

    @Override
    public void setup_a_not_existing_account() {
        accountId = AN_ACCOUNT_ID;
        given(accountRepositoryMock.getById(accountId)).willReturn(null);
    }

    @Override
    public void setup_amount_of_money(int amountOfMoney) {
        amount = amountOfMoney;
    }

    @Override
    public void assert_account_was_saved_with(int finalBalance) {
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepositoryMock).save(accountCaptor.capture());

        Account savedAccount = accountCaptor.getValue();
        assertEquals(accountId, savedAccount.getId());
        assertEquals(finalBalance, savedAccount.getBalance());
    }

    @Override
    public void do_withdraw() {
        atm.withdraw(accountId, amount);
    }

    @Override
    public void do_deposit() {
        atm.deposit(accountId, amount);
    }
}
