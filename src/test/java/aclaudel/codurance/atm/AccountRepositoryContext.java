package aclaudel.codurance.atm;

import java.util.UUID;

public abstract class AccountRepositoryContext {

    protected final AccountRepository accountRepository;

    public AccountRepositoryContext(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public abstract void save_account(Account account);
    public abstract void assert_account_was_saved_with(UUID expectedId, int finalBalance);

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

}
