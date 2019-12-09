package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmContext;
import io.cucumber.java.en.When;

import static aclaudel.codurance.atm.context.ExecutionContext.execute;


public class DepositSteps {

    @When("the deposit is made")
    public void the_deposit_is_made() {
        execute(AtmContext::do_deposit);
    }
}
