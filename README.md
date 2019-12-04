# TDD with Cucumber

## Define your actions
- Create a feature file `Deposit.feature`

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

A common use case for the tags is to add extra documentation, with `@happy_path` or `@ui`.  
Tags are also handy in CI platform, where you can filter the test execution, with `@nighty` or `@slow`.

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

- Work In Progress scenarios (from `@todo`)
```gherkin
@wip
Scenario: The deposit should fail if the amount is negative
  Given an account
  ...
```

- Implemented scenarios (from `@wip`)
```gherkin
@done
Scenario: The deposit should fail if the account does not exist
  Given a not existing account
  ...
```

When working on our local machine, we may not want to run all the possible tests, especially when doing TDD.
We only want to execute a small set of tests, that are linked to the feature we are implementing. 

By tagging scenarios, we can easily exclude unrelated test and then reduce the feedback loop for the work in progress.
Once the feature is implemented locally, we'll run the entire test suite this time, in order to safely push to the CI platform.

Here is a cucumber runner that only execute the scenarios for the next iteration,
that are either in work in progress or already implemented.
```java
@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = "pretty",
    tags = "(@delivery and (@wip or @done))")
public class AtmCucumberRunner { }
```

### Create empty step definitions

So let's start TDDing our scenarios.
First thing to do is defining our backlog with the `@todo` tag.
We're going to tag every scenario, except the first one which will be our starting point. 
```gherkin
@wip
Scenario: Deposit money to an account
```

We can now run the `AtmCucumberRunner` in order to execute our scenario.
![first-scenario-execution](https://github.com/aclaudel/tdd-with-cucumber/blob/master/src/main/resources/img/first-scenario-execution.png)

The scenario is currently ignored because there is no step implementation and we're not in `strict` mode.
Step definitions can be directly from the feature file using Intellij. 
![create-step-def-shortcut](https://github.com/aclaudel/tdd-with-cucumber/blob/master/src/main/resources/img/create-step-def-shortcut.png)

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

### Implement step definitions

We'll implement the steps bottom-up, so we'll start with
```gherkin
Then the money has been added to the account
```

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

Here we added the test variables `accountId`, `initialBalance` and `amountToDeposit`.
These variables will be set in the `Given` steps
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

Now we call the deposit method in `When`
```java
@When("the deposit is made")
public void the_deposit_is_made() {
    atm.deposit(accountId, amountToDeposit);
}
```

And setup our SUT
```java
@Before
public void before() {
    accountRepositoryMock = mock(AccountRepository.class);
    atm = new Atm(accountRepositoryMock);
}
```

We can run the test to see it fail and implement the deposit method.
```java
public void deposit(UUID accountId, int amountToDeposit) {
    var account = accountRepository.getById(accountId);
    var newBalance = account.getBalance() + amountToDeposit;
    account.setBalance(newBalance);
    accountRepository.save(account);
}
```

And make it pass !
![passing-scenario](https://github.com/aclaudel/tdd-with-cucumber/blob/master/src/main/resources/img/passing-scenario.png)

The scenario is now implemented, so we can tag it as `@done` and start a new scenario from our backlog.
```gherkin
@done
Scenario: Deposit money to an account
  ...
@wip
Scenario: The deposit should fail if the account does not exist
  ...
```

If we run our tests again, then the new scenario will be executed.
![new-scenario-from-backlog](https://github.com/aclaudel/tdd-with-cucumber/blob/master/src/main/resources/img/new-scenario-from-backlog.png)
