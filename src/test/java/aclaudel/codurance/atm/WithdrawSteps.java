package aclaudel.codurance.atm;

import aclaudel.codurance.context.AtmContext;
import io.cucumber.java.en.When;

public class WithdrawSteps {

    private final AtmContext atmContext = AtmContext.getInstance();

    @When("the withdraw is made")
    public void the_withdraw_is_made() {
        atmContext.atm.withdraw(atmContext.accountId, atmContext.amount);
    }

}
