package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.AccountNotFoundException;
import aclaudel.codurance.atm.NegativeMoneyAmountException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static aclaudel.codurance.atm.context.AtmContext.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DepositSteps {

    @When("the deposit is made")
    public void the_deposit_is_made() {
        atm.deposit(accountId, amount);
    }

    @Then("the deposit should generate the error AccountNotFound")
    public void the_deposit_should_generate_the_error_account_not_found() {
        assertThrows(AccountNotFoundException.class, this::the_deposit_is_made);
    }

    @Then("the deposit should generate the error NegativeMoneyAmount")
    public void the_deposit_should_generate_the_error_negative_money_amount() {
        assertThrows(NegativeMoneyAmountException.class, this::the_deposit_is_made);
    }

}
