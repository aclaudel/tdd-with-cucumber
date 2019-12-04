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

So let's start TDDing our scenarios.
First thing to do is defining our backlog with the *@todo* tag.
We're going to tag every scenario, except the first one which will be our starting point, with *@wip*. 
```gherkin
@wip
Scenario: Deposit money to an account
```

The test will be ignored