package aclaudel.codurance.atm;

import aclaudel.codurance.context.AtmContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class DepositSteps {

    private final AtmContext atmContext = AtmContext.getInstance();

    @When("the deposit is made")
    public void the_deposit_is_made() {
        atmContext.atm.deposit(atmContext.accountId, atmContext.amount);
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
