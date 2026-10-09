Feature: Negative Role Testing

  @negative_testing @C4368781
  Scenario Outline: Validate that NCLH - Bar Management user group does not have Access to Setup Tab
    Given Login page is displayed
    When Input credentials to login into nclh web client
      | Bar Management |  User profile page |
    And Clicks on "<Service>" module
    And User navigates to "settings-management/403" page
    Then User should get the validation message <loginValidationText>

    Examples:
      | Service | loginValidationText                                                                    |
      | Setup   | You are trying to reach the functionality in Current Module you don't have access for. |
