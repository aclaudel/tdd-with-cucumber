package aclaudel.codurance.atm;

import java.util.UUID;

public interface AccountRepository {
    void save(Account account);

    Account getById(UUID accountId);
}
