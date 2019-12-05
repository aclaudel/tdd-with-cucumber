package aclaudel.codurance.atm;

import java.util.UUID;

public class Atm {
    private AccountRepository accountRepository;

    public Atm(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void deposit(UUID accountId, int amountToDeposit) {
        var account = accountRepository.getById(accountId);
        if(account == null)
            throw new AccountNotFoundException();
        var newBalance = account.getBalance() + amountToDeposit;
        account.setBalance(newBalance);
        accountRepository.save(account);
    }
}
