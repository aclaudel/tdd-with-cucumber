package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.AccountRepository;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AtmMockContext extends AtmContext {

    @Override
    protected AccountRepository get_repository() {
        return mock(AccountRepository.class);
    }

    @Override
    protected void save_account(AccountRepository accountRepository, Account account) {
        given(accountRepository.getById(account.getId())).willReturn(account);
    }

    @Override
    protected void assert_account_was_saved_with(AccountRepository accountRepository, UUID expectedId, int finalBalance) {
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());

        Account savedAccount = accountCaptor.getValue();
        assertEquals(expectedId, savedAccount.getId());
        assertEquals(finalBalance, savedAccount.getBalance());
    }


}
