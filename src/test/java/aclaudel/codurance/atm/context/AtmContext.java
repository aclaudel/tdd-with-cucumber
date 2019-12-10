package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.Atm;
import aclaudel.codurance.atm.context.tmp.AccountRepositoryContext;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public abstract class AtmContext {
    // constants
    public static final int DEFAULT_INITIAL_BALANCE = 1;
    public static final int NEGATIVE_AMOUNT_OF_MONEY = -10;
    // dependencies
    private AccountRepositoryContext accountRepositoryContext;
    // sut
    private Atm atm;
    // variables
    private UUID accountId;
    private int amount;

    public AtmContext(AccountRepositoryContext accountRepositoryContext) {
        // TODO retrieve the repository from a factory
        this.accountRepositoryContext = accountRepositoryContext;
        atm = new Atm(accountRepositoryContext);
    }

    // non-specific methods
    public void setup_account(int initialBalance) {
        accountId = randomUUID();
        var account = new Account(accountId, initialBalance);
        accountRepositoryContext.save_account(account);
    }

    public void setup_a_not_existing_account() {
        accountId = randomUUID();
    }

    public void setup_amount_of_money(int amountOfMoney) {
        amount = amountOfMoney;
    }

    public void assert_account_was_saved_with(int finalBalance) {
        accountRepositoryContext.assert_account_was_saved_with(accountId, finalBalance);
    }

    public void do_withdraw() {
        atm.withdraw(accountId, amount);
    }

    public void do_deposit() {
        atm.deposit(accountId, amount);
    }
}
