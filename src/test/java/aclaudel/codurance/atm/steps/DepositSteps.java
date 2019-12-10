package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmMockContext;
import aclaudel.codurance.atm.context.ErrorContext;
import io.cucumber.java.en.When;


public class DepositSteps {

    private final AtmMockContext atmContext;
    private final ErrorContext errorContext;

    public DepositSteps(AtmMockContext atmContext, ErrorContext errorContext) {
        this.atmContext = atmContext;
        this.errorContext = errorContext;
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
