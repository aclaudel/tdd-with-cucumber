package aclaudel.codurance.atm.context.infrastructure;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.infrastructure.MongoDBAccountRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoDBAccountRepositoryContext extends AccountRepositoryContext {

    public MongoDBAccountRepositoryContext() {
        super(new MongoDBAccountRepository());
    }

    @Override
    public void save_account(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void assert_account_was_saved_with(UUID expectedId, int finalBalance) {
        assertEquals(finalBalance, accountRepository.getById(expectedId).getBalance());
    }
}
