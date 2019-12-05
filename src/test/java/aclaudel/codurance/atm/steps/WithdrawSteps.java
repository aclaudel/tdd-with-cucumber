package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.ErrorContext;
import io.cucumber.java.en.When;

import static aclaudel.codurance.atm.context.AtmContext.do_withdraw;

public class WithdrawSteps {

    @When("the withdraw is made")
    public void the_withdraw_is_made() {
        do_withdraw();
    }

    @When("we try to do the withdraw")
    public void we_try_to_do_the_withdraw() {
        ErrorContext.execute_and_save_generated_exception(this::the_withdraw_is_made);
    }
}
