package aclaudel.codurance.atm.steps;

import io.cucumber.java.en.When;

import static aclaudel.codurance.atm.context.AtmContext.*;

public class WithdrawSteps {

    @When("the withdraw is made")
    public void the_withdraw_is_made() {
        atm.withdraw(accountId, amount);
    }

}
