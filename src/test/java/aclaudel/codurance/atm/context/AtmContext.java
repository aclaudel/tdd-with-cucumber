package aclaudel.codurance.atm.context;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public interface AtmContext {
    // constants
    int DEFAULT_INITIAL_BALANCE = 1;
    UUID AN_ACCOUNT_ID = randomUUID();
    int NEGATIVE_AMOUNT_OF_MONEY = -10;

    void setup();

    void setup_account(int initialBalance);

    void setup_a_not_existing_account();

    void setup_amount_of_money(int amountOfMoney);

    void assert_account_was_saved_with(int finalBalance);

    void do_withdraw();

    void do_deposit();
}
