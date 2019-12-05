package aclaudel.codurance.atm;

import aclaudel.codurance.context.AtmContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AccountSteps {

    private final AtmContext atmContext = AtmContext.getInstance();

    @Given("an account")
    public void an_account() {
        an_account_with_an_initial_balance(AtmContext.DEFAULT_INITIAL_BALANCE);
    }

    @Given("an account with an initial balance of {int}")
    public void an_account_with_an_initial_balance(int initialBalance) {
        atmContext.accountId = AtmContext.AN_ACCOUNT_ID;
        Account account = new Account(atmContext.accountId, initialBalance);
        given(atmContext.accountRepositoryMock.getById(atmContext.accountId)).willReturn(account);
    }

    @Given("a negative amount of money")
    public void a_negative_amount_of_money() {
        an_amount_of_money_of_amount(AtmContext.NEGATIVE_AMOUNT_OF_MONEY);
    }

    @Given("an amount of money of {int}")
    public void an_amount_of_money_of_amount(int amount) {
        atmContext.amount = amount;
    }

    @Given("a not existing account")
    public void a_not_existing_account() {
        atmContext.accountId = AtmContext.AN_ACCOUNT_ID;
        given(atmContext.accountRepositoryMock.getById(atmContext.accountId)).willReturn(null);
    }

    @Then("the final account balance is {int}")
    public void the_final_balance_has_been_updated(int finalBalance) {
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(atmContext.accountRepositoryMock).save(accountCaptor.capture());

        Account savedAccount = accountCaptor.getValue();
        assertEquals(atmContext.accountId, savedAccount.getId());
        assertEquals(finalBalance, savedAccount.getBalance());
    }
}
