package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmContext;
import aclaudel.codurance.atm.context.AtmMongoDBContext;
import aclaudel.codurance.atm.context.ErrorContext;
import aclaudel.codurance.atm.context.Factory;
import io.cucumber.java.en.When;


public class DepositSteps {

    private final AtmContext atmContext;
    private final ErrorContext errorContext;

    public DepositSteps(AtmMongoDBContext atmContext, ErrorContext errorContext) {
        this.atmContext = Factory.getAtmContext();;
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
