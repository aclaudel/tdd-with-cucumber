package aclaudel.codurance.atm;

import aclaudel.codurance.context.AtmContext;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;

public class WithdrawSteps extends AtmContext {

    @When("the withdraw is made")
    public void the_withdraw_is_made() {
        atm.withdraw(accountId, amount);
    }

}
