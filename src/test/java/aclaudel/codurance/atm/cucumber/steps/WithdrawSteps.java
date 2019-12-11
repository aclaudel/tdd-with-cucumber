package aclaudel.codurance.atm.cucumber.steps;

import aclaudel.codurance.atm.AtmContext;
import aclaudel.codurance.atm.ContextFactory;
import aclaudel.codurance.atm.ErrorContext;
import io.cucumber.java.en.When;

public class WithdrawSteps {

    private final AtmContext atmContext;
    private final ErrorContext errorContext;

    public WithdrawSteps(ContextFactory contextFactory) {
        atmContext = contextFactory.atmContext();
        errorContext = contextFactory.errorContext();
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
