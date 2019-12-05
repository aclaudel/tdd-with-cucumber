package aclaudel.codurance.atm;

import java.util.UUID;

public class Atm {
    private AccountRepository accountRepository;

    public Atm(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void deposit(UUID accountId, int amountToDeposit) {
        var account = accountRepository.getById(accountId);
        if(account == null) {
            throw new AccountNotFoundException();
        }
        if(amountToDeposit < 0) {
            throw new NegativeMoneyAmountException();
        }
        var newBalance = account.getBalance() + amountToDeposit;
        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    public void withdraw(UUID accountId, int amountToWithdraw) {
        var account = accountRepository.getById(accountId);
        var newBalance = account.getBalance() - amountToWithdraw;
        account.setBalance(newBalance);
        accountRepository.save(account);
    }
}
