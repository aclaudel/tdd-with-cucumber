@delivery
Feature: Money withdraw

  @wip
  Scenario Outline: Withdraw money from an account
    Given an account with an initial balance of <initial balance>
    And an amount of money of <amount>
    When the withdraw is made
    Then the final account balance is <final balance>
    Examples:
      | initial balance  | amount | final balance |
      | 10               | 10     | 0             |
      | 15               | 5      | 10            |
      | -10              | -10    | -20           |

  @todo
  Scenario: The withdraw should fail if the account does not exist
    Given a not existing account
    Then the withdraw should generate the error AccountNotFound

  @todo
  Scenario: The withdraw should fail if the amount is negative
    Given an account
    And a negative amount of money
    Then the withdraw should generate the error NegativeMoneyAmount