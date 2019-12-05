package aclaudel.codurance.atm;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DepositSteps {
    // constants
    private static final int DEFAULT_INITIAL_BALANCE = 1;
    public static final UUID AN_ACCOUNT_ID = randomUUID();
    public static final int SOME_MONEY = 10;

    // mocks
    private AccountRepository accountRepositoryMock;

    // SUT
    private Atm atm;

    // test variables
    private UUID accountId;
    private int amountToDeposit;
    private int initialBalance;

    @Before
    public void before() {
        accountRepositoryMock = mock(AccountRepository.class);
        atm = new Atm(accountRepositoryMock);
    }

    @Given("an account")
    public void an_account() {
        accountId = AN_ACCOUNT_ID;
        initialBalance = DEFAULT_INITIAL_BALANCE;
        Account account = new Account(accountId, initialBalance);
        given(accountRepositoryMock.getById(accountId)).willReturn(account);
    }

    @And("an amount of money")
    public void an_amount_of_money() {
        amountToDeposit = SOME_MONEY;
    }

    @And("a negative amount of money")
    public void a_negative_amount_of_money() {
        amountToDeposit = -SOME_MONEY;
    }

    @Given("a not existing account")
    public void a_not_existing_account() {
        accountId = AN_ACCOUNT_ID;
        given(accountRepositoryMock.getById(accountId)).willReturn(null);
    }

    @When("the deposit is made")
    public void the_deposit_is_made() {
        atm.deposit(accountId, amountToDeposit);
    }

    @Then("the money has been added to the account")
    public void the_money_has_been_added_to_the_account() {
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepositoryMock).save(accountCaptor.capture());

        Account savedAccount = accountCaptor.getValue();
        assertEquals(accountId, savedAccount.getId());
        assertEquals(initialBalance + amountToDeposit, savedAccount.getBalance());
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
