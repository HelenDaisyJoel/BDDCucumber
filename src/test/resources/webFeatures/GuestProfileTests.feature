Feature: Guest Profile

  @GuestProfile @GP1
  Scenario: Validating data from NVS is getting reflected in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome!2024 | Guest Service |
    Then User navigate to menu "Guest Service" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    And User get the required data from the excel sheet
    And User search for specific "ReservationId" and navigated to profile
    And User open crew "Personal Information" information details
    Then User verify user personal data for
      | Last Name | First Name | Middle Name | Title | Gender | Profession | Date of Birth | Birth Place | Nationality | Golden ID | Preferred Language |
    And User open submenu "Status" details
    Then User verify user loyalty data for
      | Membership ID | Loyalty Tier | Points Total |
