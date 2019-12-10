package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmContext;
import aclaudel.codurance.atm.context.AtmMongoContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static aclaudel.codurance.atm.context.AtmMockContext.DEFAULT_INITIAL_BALANCE;
import static aclaudel.codurance.atm.context.AtmMockContext.NEGATIVE_AMOUNT_OF_MONEY;

public class AccountSteps {

    private final AtmContext atmContext;

    public AccountSteps(AtmMongoContext atmContext) {
        this.atmContext = atmContext;
    }

    @Given("an account")
    public void an_account() {
        an_account_with_an_initial_balance(DEFAULT_INITIAL_BALANCE);
    }

    @Given("an account with an initial balance of {int}")
    public void an_account_with_an_initial_balance(int initialBalance) {
        atmContext.setup_account(initialBalance);
    }

    @Given("a negative amount of money")
    public void a_negative_amount_of_money() {
        an_amount_of_money_of(NEGATIVE_AMOUNT_OF_MONEY);
    }

    @Given("an amount of money of {int}")
    public void an_amount_of_money_of(int amountOfMoney) {
        atmContext.setup_amount_of_money(amountOfMoney);
    }

    @Given("a not existing account")
    public void a_not_existing_account() {
        atmContext.setup_a_not_existing_account();
    }

    @Then("the final account balance is {int}")
    public void the_final_balance_has_been_updated(int finalBalance) {
        atmContext.assert_account_was_saved_with(finalBalance);
    }

}
