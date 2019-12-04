# Outside-in TDD with Cucumber

## Define your actions
- Create a feature file for the action, like Deposit.feature

```gherkin
Feature: Deposit action
```

- Write one happy path scenario

```gherkin
Scenario: Deposit money to an account
  Given an account
  And an amount of money
  When the deposit is made
  Then the money has been added to the account
```
- Write several special case scenarios
```gherkin
Scenario: The deposit should fail if the account does not exist
  Given a not existing account
  Then the deposit should generate the error AccountNotFound

Scenario: The deposit should fail if the amount is negative
  Given an account
  And a negative amount of money
  Then the deposit should generate the error NegativeMoneyAmount
```

## Setup your features lifecycle

Cucumber allow us to tag scenarios and features.

A common use case for the tags is to add extra documentation, with *@happy_path* or *@ui.*  
Tags are also handy in CI platform, where you can filter the test execution, with *@nighty* or *@slow*.

Here we are going to use these tags to describe the lifecycle of our features.

- Features that will be delivered on the next iteration
```gherkin
@delivery
Feature: Money deposit
```

- Scenarios in the backlog
```gherkin
@todo
Scenario: Deposit money to an account
  Given an account
  ...
```

- Work In Progress scenarios (from @todo)
```gherkin
@wip
Scenario: The deposit should fail if the amount is negative
  Given an account
  ...
```

- Implemented scenarios (from @wip)
```gherkin
@done
Scenario: The deposit should fail if the account does not exist
  Given a not existing account
  ...
```

When working on our local machine, we may not want to run all the possible tests, especially when doing TDD.
We only want to execute a small set of tests, that are linked to the feature we are implementing. 

By tagging scenarios, we can easily exclude unrelated test and then reduce the feedback loop of the work in progress.
Once the feature is implemented locally, we'll then run the entire test suite before pushing to the CI platform.

Here is a cucumber runner that only execute the scenarios for the next iteration,
that are either in work in progress or already implemented.
```java
@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = "pretty",
    tags = "(@delivery and (@wip or @done))")
public class AtmCucumberRunner { }
```

###Start the TDD

#### Create empty step definitions

So let's start TDDing our scenarios.
First thing to do is defining our backlog with the *@todo* tag.
We're going to tag every scenario, except the first one which will be our starting point, with *@wip*. 
```gherkin
@wip
Scenario: Deposit money to an account
```

We can now run the `AtmCucumberRunner` in order to execute our scenario.
<!-- TODO add image here -->

The scenario will simply be ignored because there is no step implementation and we're not in *strict* mode.
We can easily create the steps definition directly from the feature file using Intellij. 
<!-- TODO add image here -->

Here are the empty definitions:

```java
@Given("an account")
public void an_account() {

}

@And("an amount of money")
public void an_amount_of_money() {

}

@When("the deposit is made")
public void the_deposit_is_made() {

}

@Then("the money has been added to the account")
public void the_money_has_been_added_to_the_account() {

}
```

#### Implement step definitions

We can now start to implement the step definitions.
We'll go bottom-up, so we'll start with `Then the money has been added to the account`.

The assertion will check  if we called the `AccountRepository` using the correct account id and amount of money.
```java
@Then("the money has been added to the account")
public void the_money_has_been_added_to_the_account() {
    ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
    verify(accountRepositoryMock).save(accountCaptor.capture());
    
    Account savedAccount = accountCaptor.getValue();
    assertEquals(accountId, savedAccount.getId());
    assertEquals(initialBalance + amountToDeposit, savedAccount.getBalance());
}
```

Here we added some test variables, like `accountId`, `initialBalance` or `amountToDeposit`.
These variables will be set in the `Given` steps like this:
```java
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
```

Now we simply add the deposit action in `When`
```java
@When("the deposit is made")
public void the_deposit_is_made() {
    atm.deposit(accountId, amountToDeposit);
}
```

And setup our mock and SUT
```java
@Before
public void before() {
    accountRepositoryMock = mock(AccountRepository.class);
    atm = new Atm(accountRepositoryMock);
}
```

We can run the test to see it fail and implement the deposit.
```java
public void deposit(UUID accountId, int amountToDeposit) {
    var account = accountRepository.getById(accountId);
    var newBalance = account.getBalance() + amountToDeposit;
    account.setBalance(newBalance);
    accountRepository.save(account);
}
```

And make it pass !
<!-- TODO add image here -->

The scenario is now implemented, so we can tag it as `@done`.