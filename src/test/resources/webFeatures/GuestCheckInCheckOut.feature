Feature: Guest Check In Check Out

  @GuestCheckInCheckOut @C1326857
  Scenario: Update guest's first and last name in personal information
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      |O&R| Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User gets the guest name text
    And User open submenu "Personal Information" details
    Then User update the first name and last name
    Then User is able to view "Save"
    And User click on "Save"
    Then User refresh the page
    Then User verify guest name is updated in a profile page

  @GuestCheckInCheckOut @C1326864
  Scenario: Validate that Guest/Pier Agent can update guest's last name in personal information
    Given a new guest user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      |O&R|  Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    Then User select "Expect Future" option from the filter
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User click on chain icon at top right pane.
    And User click on "Check In"
    And User click on "Confirm"
    Then User verify the crew status as "Checked In"


  @GuestCheckInCheckOut @C1326865
  Scenario: Validate that user is able to enable the denied boarding flag in Otalio Application.
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL|  Guest Service Manager | Welcome2024! | Guest Services |
      |O&R|  Concierge | Welcome2024! | Guest Services |
#      |O&R|  Guest Service Manager | Welcome2024! | Guest Services |

    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And Search the guest user
    And User able to click "lastName" in the detail table
    And User open guest "Lost & Found, Security Incidents" information details
    And User click on three dots
    And User add the Boarding Denied
    And User click on Reason and select 'Person of Interest' and add Reason Detail
    And User click on "Save"
    Then User view the profile
    Then User verify the "BOARDING DENIED" warning to guest profile

  @GuestCheckInCheckOut @C1326867
  Scenario: Validate that user is able to view the updated guest statuses in Guest search page
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      |O&R|  Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    Then User check for all the list of guests

  @GuestCheckInCheckOut @C1326870
  Scenario: Validate that the Application is able to check out a guest individually
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      |O&R|  Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User click on chain icon at top right pane.
    And User click on "Check Out"
    And User click on "Confirm"
    Then User verify the crew status as "Checked Out"

  @GuestCheckInCheckOut @C2788905
  Scenario: Validate that user is able to add Flight info for a guest
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      |O&R|  Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And Search the guest user
    And User able to click "lastName" in the detail table
    And User open guest "Cruise Information" information details
    Then User click on "Flight Info" tab and click on "Add Flight Info" in Cruise Information
    And User enter flight mandatory details
    And User click on "Save"
    Then User verify the flight details

  @GuestCheckInCheckOut @C1326858
  Scenario: Validate manual guest check-in when all required fields have been completed
    Given a new guest user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      |O&R|  Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User click on chain icon at top right pane.
    And User click on "Check In" button
    And User click on "Confirm"
    Then User verify the crew status as "Checked In"

  @GuestCheckInCheckOut @C1326862
  Scenario: Validate that if a guest attempts to checks out with pending payments Otalio does not allow this action, then settle account and retry
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      |O&R|  Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User adds the amount as a deposit to the guest account
    Then User click on chain icon at top right pane
    And User click on "Check Out"
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Person has an open balance and should contact the Reception Desk"
    Then User close the pop error message and left panel
    And User is "Settle Account" for the respect guest
    Then User is able to view "Settle Account"
    Then User click on chain icon at top right pane.
    Then User is able to view "Check Out"
    And User click on "Check Out"
    Then User is able to view "Confirm"
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @GuestCheckInCheckOut @C1326863
  Scenario: Create a new guest reservation and view that the newly created guest displays in guest search
    Given Login page is displayed
    When Input credentials to login into nclh portal
#      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
#      |O&R|  Receptionist | Welcome2024! | Guest Services |
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Guest Services | Welcome2024! | Guest Services |

    Then User navigate to menu "Guest Services" service
    And User click on "New Reservation" link under status
    Then User enter the crew first name and last name
    And User enters all mandatory fields in the Fill Name and Date section
    And User click on "Save & Next (optional)"
    And User selects the required cabin
    And Clicks the Save and Next button
    And Enter all the mandatory fields
    And Click on save and finish in add document section page
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"
    Then User view the profile
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    Then User searches for the newly created guest

  @GuestCheckInCheckOut @C1326869
  Scenario: Validate that the Otalio Application is able to check out guests in bulk operations
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL |  Guest Service Manager | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User click on three dots and select Bulk Operation
    And User unselects all guest members
    And Search for the bulk operation guest user
    And Selects one guest member
    And User click on Next button
    When User select radio button "Apply bulk Reservation Actions"
    And User click on Choose Operation Group tab Next button
    And User click on "Check Out" checkbox
    And User click on Confirm button
    And User views the confirmation pop-up and clicks Confirm
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User validate the 1 and disembarkation status


  @GuestCheckInCheckOut @C1326866
  Scenario: Validate that the user is able to print the keycard of the guest.
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      |O&R|  Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User click on chain icon at top right pane
    And User click on "Print Boardcard"
    And User select the printer type in printer dropdown
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

