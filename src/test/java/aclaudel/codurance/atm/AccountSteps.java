package aclaudel.codurance.atm;

import aclaudel.codurance.context.AtmContext;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AccountSteps extends AtmContext {

    @Before
    public void before() {
        super.before();
    }

    @Given("an account")
    public void an_account() {
        an_account_with_an_initial_balance(DEFAULT_INITIAL_BALANCE);
    }

    @Given("an account with an initial balance of {int}")
    public void an_account_with_an_initial_balance(int initialBalance) {
        accountId = AN_ACCOUNT_ID;
        Account account = new Account(accountId, initialBalance);
        given(accountRepositoryMock.getById(accountId)).willReturn(account);
    }

    @And("a negative amount of money")
    public void a_negative_amount_of_money() {
        an_amount_of_money_of_amount(NEGATIVE_AMOUNT_OF_MONEY);
    }

    @And("an amount of money of {int}")
    public void an_amount_of_money_of_amount(int amount) {
        this.amount = amount;
    }

    @Given("a not existing account")
    public void a_not_existing_account() {
        accountId = AN_ACCOUNT_ID;
        given(accountRepositoryMock.getById(accountId)).willReturn(null);
    }

    @Then("the final account balance is {int}")
    public void the_final_balance_has_been_updated(int finalBalance) {
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepositoryMock).save(accountCaptor.capture());

        Account savedAccount = accountCaptor.getValue();
        assertEquals(accountId, savedAccount.getId());
        assertEquals(finalBalance, savedAccount.getBalance());
    }
}
