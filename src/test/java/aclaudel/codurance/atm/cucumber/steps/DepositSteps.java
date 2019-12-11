package aclaudel.codurance.atm.cucumber.steps;

import aclaudel.codurance.atm.AtmContext;
import aclaudel.codurance.atm.ContextFactory;
import aclaudel.codurance.atm.ErrorContext;
import io.cucumber.java.en.When;


public class DepositSteps {

    private final AtmContext atmContext;
    private final ErrorContext errorContext;

    public DepositSteps(ContextFactory contextFactory) {
        atmContext = contextFactory.atmContext();
        errorContext = contextFactory.errorContext();
    }

    @When("the deposit is made")
    public void the_deposit_is_made() {
        atmContext.do_deposit();
    }

    @When("we try to do the deposit")
    public void we_try_to_do_the_deposit() {
        errorContext.execute_and_save_generated_exception(this::the_deposit_is_made);
    }

}