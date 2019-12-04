@delivery
Feature: Money deposit

  @todo
  Scenario: Deposit money to an account
    Given an account
    And an amount of money
    When the deposit is made
    Then the money has been added to the account

  @todo
  Scenario: The deposit should fail if the account does not exist
    Given a not existing account
    Then the deposit should generate the error AccountNotFound

  @todo
  Scenario: The deposit should fail if the amount is negative
    Given an account
    And a negative amount of money
    Then the deposit should generate the error NegativeMoneyAmount