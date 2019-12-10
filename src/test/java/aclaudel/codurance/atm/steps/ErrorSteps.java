package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.AccountNotFoundException;
import aclaudel.codurance.atm.NegativeMoneyAmountException;
import aclaudel.codurance.atm.context.ErrorContext;
import io.cucumber.java.en.Then;

import static aclaudel.codurance.atm.context.Contexts.errorContext;

public class ErrorSteps {

    private final ErrorContext errorContext = errorContext();

    @Then("the error AccountNotFound is generated")
    public void the_error_account_not_found_is_generated() {
        errorContext.assert_error_was_generated(AccountNotFoundException.class);
    }

    @Then("the error NegativeMoneyAmount is generated")
    public void the_error_negative_money_amount_is_generated() {
        errorContext.assert_error_was_generated(NegativeMoneyAmountException.class);
    }

}
