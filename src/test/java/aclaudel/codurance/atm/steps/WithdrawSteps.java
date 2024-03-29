package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmContext;
import aclaudel.codurance.atm.context.ErrorContext;
import io.cucumber.java.en.When;

public class WithdrawSteps {

    private final AtmContext atmContext;
    private final ErrorContext errorContext;

    public WithdrawSteps(AtmContext atmContext, ErrorContext errorContext) {
        this.atmContext = atmContext;
        this.errorContext = errorContext;
    }

    @When("the withdraw is made")
    public void the_withdraw_is_made() {
        atmContext.do_withdraw();
    }

    @When("we try to do the withdraw")
    public void we_try_to_do_the_withdraw() {
        errorContext.execute_and_save_generated_exception(this::the_withdraw_is_made);
    }
}
