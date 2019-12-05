package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.AccountNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static aclaudel.codurance.atm.context.AtmContext.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WithdrawSteps {

    @When("the withdraw is made")
    public void the_withdraw_is_made() {
        atm.withdraw(accountId, amount);
    }

    @Then("the withdraw should generate the error AccountNotFound")
    public void the_withdraw_should_generate_the_error_account_not_found() {
        assertThrows(AccountNotFoundException.class, this::the_withdraw_is_made);
    }
}
