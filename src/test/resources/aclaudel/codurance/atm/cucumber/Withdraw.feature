@delivery
Feature: Money withdraw

  @done
  Scenario Outline: Withdraw money from an account
    Given an account with an initial balance of <initial balance>
    And an amount of money of <amount>
    When the withdraw is made
    Then the final account balance is <final balance>
    Examples:
      | initial balance  | amount | final balance |
      | 10               | 10     | 0             |
      | 15               | 5      | 10            |
      | -10              | 10     | -20           |

  @done
  Scenario: The withdraw should fail if the account does not exist
    Given a not existing account
    When we try to do the withdraw
    Then the error AccountNotFound is generated

  @todo
  Scenario: The withdraw should fail if the amount is negative
    Given an account
    And a negative amount of money
    When we try to do the withdraw
    Then the error NegativeMoneyAmount is generated