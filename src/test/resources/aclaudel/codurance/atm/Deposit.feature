@delivery
Feature: Money deposit

  @done
  Scenario: Deposit money to an account
    Given an account
    And an amount of money
    When the deposit is made
    Then the money has been added to the account

  @done
  Scenario: The deposit should fail if the account does not exist
    Given a not existing account
    Then the deposit should generate the error AccountNotFound

  @done
  Scenario: The deposit should fail if the amount is negative
    Given an account
    And a negative amount of money
    Then the deposit should generate the error NegativeMoneyAmount
