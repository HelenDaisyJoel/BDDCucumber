Feature: Finance Management Guests

  @FinanceManagement @FMG1 @NotDependent @C1518769
  Scenario Outline: Validate that the Add routing to guest user through the Finance user
    Given A Guest user is already created
    And A routing guest user is already created
    When Input credentials to login into nclh
      | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Search the guest user
    And Navigates to user profile
    And User open submenu "Financial Information" details
    Then User click on "Routing" tab and click on "ADD ROUTING" on financial information
    Then User select "<PayType>" radio button
    And User click on "ADD"
    And Select the guest user for routing
    And User click on "Select"
    Then User click on "save"
    And User click on "Yes"
    Then User verify the account statement as "<Status>"
    Then User verify the add routing


    Examples:
      | Service        | ServiceName  | PayType       | Status |
      | Guest Services | Guest Search | Is Paying For | Payer  |

  @FinanceManagement @FMG1 @NotDependent @C1483378
  Scenario Outline: Validate if the user is able to delete the routing to the respective guest
    Given A Guest user is already created
    And A routing guest user is already created
    When Input credentials to login into nclh
      | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Search the guest user
    And Navigates to user profile
    And User open submenu "Financial Information" details
    Then User click on "Routing" tab and click on "ADD ROUTING" on financial information
    Then User select "<PayType>" radio button
    And User click on "ADD"
    And Select the guest user for routing
    And User click on "Select"
    Then User click on "save"
    And User click on "Yes"
    Then User verify the account statement as "<Status>"
    Then User verify the add routing
    And  User click on cross button
    And User click on "Delete"
    Then Validate the added routing is deleted

    Examples:
      | Service        | ServiceName  | PayType       | Status |
      | Guest Services | Guest Search | Is Paying For | Payer  |