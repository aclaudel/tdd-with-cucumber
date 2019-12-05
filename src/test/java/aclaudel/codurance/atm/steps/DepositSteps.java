package aclaudel.codurance.atm.steps;

import io.cucumber.java.en.When;

import static aclaudel.codurance.atm.context.AtmContext.do_deposit;
import static aclaudel.codurance.atm.context.ErrorContext.execute_and_save_generated_exception;


public class DepositSteps {

    @When("the deposit is made")
    public void the_deposit_is_made() {
        do_deposit();
    }

    @When("we try to do the deposit")
    public void we_try_to_do_the_deposit() {
        execute_and_save_generated_exception(this::the_deposit_is_made);
    }

}
