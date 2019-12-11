package aclaudel.codurance.atm.context.infrastructure;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.AccountRepository;
import aclaudel.codurance.atm.context.AccountRepositoryContext;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MockAccountRepositoryContext extends AccountRepositoryContext {

    public MockAccountRepositoryContext() {
        super(mock(AccountRepository.class));
    }

    @Override
    public void save_account(Account account) {
        given(accountRepository.getById(account.getId())).willReturn(account);
    }

    @Override
    public void assert_account_was_saved_with(UUID accountId, int finalBalance) {
        var accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());

        var savedAccount = accountCaptor.getValue();
        assertEquals(accountId, savedAccount.getId());
        assertEquals(finalBalance, savedAccount.getBalance());
    }
}
