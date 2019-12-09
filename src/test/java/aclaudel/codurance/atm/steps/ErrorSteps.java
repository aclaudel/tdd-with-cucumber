package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.AccountNotFoundException;
import aclaudel.codurance.atm.NegativeMoneyAmountException;
import aclaudel.codurance.atm.context.ExecutionContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static aclaudel.codurance.atm.context.ExecutionContext.assert_error_was_generated;

public class ErrorSteps {



    @Then("the error AccountNotFound is generated")
    public void the_error_account_not_found_is_generated() {
        assert_error_was_generated(AccountNotFoundException.class);
    }

    @Then("the error NegativeMoneyAmount is generated")
    public void the_error_negative_money_amount_is_generated() {
        assert_error_was_generated(NegativeMoneyAmountException.class);
    }

    @Given("an error is expected")
    public void an_error_is_expected() {
        ExecutionContext.save_generated_exception_of_further_exceutions();
    }
}
