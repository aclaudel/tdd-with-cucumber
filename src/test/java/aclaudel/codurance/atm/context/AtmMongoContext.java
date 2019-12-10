package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.AccountRepository;
import aclaudel.codurance.atm.infrastructure.MongoDBAccountRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmMongoContext extends AtmContext {

    @Override
    protected AccountRepository get_repository() {
        return new MongoDBAccountRepository();
    }

    @Override
    protected void save_account(AccountRepository accountRepository, Account account) {
        accountRepository.save(account);
    }

    @Override
    protected void assert_account_was_saved_with(AccountRepository accountRepository, UUID expectedId, int finalBalance) {
        assertEquals(finalBalance, accountRepository.getById(expectedId).getBalance());
    }

}
