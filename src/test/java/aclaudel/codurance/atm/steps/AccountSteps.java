package aclaudel.codurance.atm.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static aclaudel.codurance.atm.context.AtmContext.*;

public class AccountSteps {

    @Given("an account")
    public void an_account() {
        an_account_with_an_initial_balance(DEFAULT_INITIAL_BALANCE);
    }

    @Given("an account with an initial balance of {int}")
    public void an_account_with_an_initial_balance(int initialBalance) {
        setup_account(initialBalance);
    }

    @Given("a negative amount of money")
    public void a_negative_amount_of_money() {
        an_amount_of_money_of(NEGATIVE_AMOUNT_OF_MONEY);
    }

    @Given("an amount of money of {int}")
    public void an_amount_of_money_of(int amountOfMoney) {
        setup_amount_of_money(amountOfMoney);
    }

    @Given("a not existing account")
    public void a_not_existing_account() {
        setup_a_not_existing_account();
    }

    @Then("the final account balance is {int}")
    public void the_final_balance_has_been_updated(int finalBalance) {
        assert_account_was_saved_with(finalBalance);
    }

}
