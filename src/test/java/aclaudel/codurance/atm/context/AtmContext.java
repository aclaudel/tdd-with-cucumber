package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.Account;
import aclaudel.codurance.atm.AccountRepository;
import aclaudel.codurance.atm.Atm;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public abstract class AtmContext {
    // constants
    public static final int DEFAULT_INITIAL_BALANCE = 1;
    public static final UUID AN_ACCOUNT_ID = randomUUID();
    public static final int NEGATIVE_AMOUNT_OF_MONEY = -10;
    // mocks
    private AccountRepository accountRepository;
    // sut
    private Atm atm;
    // variables
    private UUID accountId;
    private int amount;

    public void setup() {
        accountRepository = getRepository();
        atm = new Atm(accountRepository);
    }

    protected abstract AccountRepository getRepository();

    public void setup_account(int initialBalance) {
        accountId = AN_ACCOUNT_ID;
        Account account = new Account(accountId, initialBalance);
        save_account(accountRepository, account);
    }

    protected abstract void save_account(AccountRepository accountRepository, Account account);

    public void setup_a_not_existing_account() {
        accountId = randomUUID();
    }

    public void setup_amount_of_money(int amountOfMoney) {
        amount = amountOfMoney;
    }

    public void assert_account_was_saved_with(int finalBalance) {
        assert_account_was_saved_with(accountRepository, accountId, finalBalance);
    }

    protected abstract void assert_account_was_saved_with(AccountRepository accountRepository, UUID expectedId, int finalBalance);

    public void do_withdraw() {
        atm.withdraw(accountId, amount);
    }

    public void do_deposit() {
        atm.deposit(accountId, amount);
    }
}
