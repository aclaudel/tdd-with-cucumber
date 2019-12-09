@delivery
Feature: Money deposit

  @done
  Scenario Outline: Deposit money to an account
    Given an account with an initial balance of <initial balance>
    And an amount of money of <amount>
    When the deposit is made
    Then the final account balance is <final balance>
    Examples:
      | initial balance  | amount | final balance |
      | 0                | 10     | 10            |
      | 10               | 100    | 110           |
      | -10              | 10     | 0             |

  @wip
  Scenario: The deposit should fail if the account does not exist
    Given a not existing account
    Given an error is expected
    When the deposit is made
    Then the error AccountNotFound is generated

  @wip
  Scenario: The deposit should fail if the amount is negative
    Given an account
    And a negative amount of money
    Given an error is expected
    When the deposit is made
    Then the error NegativeMoneyAmount is generated
