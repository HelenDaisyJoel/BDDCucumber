Feature: Login Page
  As Customer , I can login to nclh.com

  @DataTableHeaders @LoginSmokeTest
  Scenario: Login using valid account that registered in personal and corporate account type
    Given Login page is displayed
    When Input credentials to login into nclh
      | Email            | Password     |
      | Meher@otalio.com | NCLProject23 |

  @SimplyDataTable
  Scenario Outline: Login using valid account that registered in personal and corporate account type
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome!2024 | Guest Service |
    Then User navigate to menu "<Service>" service
    And User logout from nclh
    Examples:
      | Service       |
      | Guest Service |
