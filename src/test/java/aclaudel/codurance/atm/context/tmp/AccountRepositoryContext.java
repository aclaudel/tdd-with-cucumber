package aclaudel.codurance.atm.context.tmp;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.AccountRepository;

import java.util.UUID;

public abstract class AccountRepositoryContext implements AccountRepository {

    protected final AccountRepository accountRepository;

    public AccountRepositoryContext(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountRepository get_repository() {
        return accountRepository;
    }

    public abstract void save_account(Account account);
    public abstract void assert_account_was_saved_with(UUID expectedId, int finalBalance);

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account getById(UUID accountId) {
        return accountRepository.getById(accountId);
    }
}

