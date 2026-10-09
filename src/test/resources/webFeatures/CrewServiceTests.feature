Feature: Crew Service

  @CrewService @Smoke @C1051002
  Scenario: Validate that the user is able to assign cains to expected crew members
    Given A new crew user is created without assigning a cabin number
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Cabin Assignment" item
    And User clear existing filters of embarkation date
    And User search unassigned cabin crew member
    And User change status of assign to "No"
    And User save crew name
    And User select crew for cabin allocation
    And User save cabin number
    And User select cabin for crew
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User verify cabin number assigned to crew member on crew information page

  @CrewService @Smoke @C1051003
  Scenario: Validate that the user is able to unassign cabins to expected crew members
    Given A new crew user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Cabin Assignment" item
    And User clear existing filters of embarkation date
    And User search assigned cabin crew member
    And User change status of assign to "Yes"
    And User save crew name
    And User select crew for cabin allocation
    And User save cabin number
    And User unAssign cabin for crew member
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @CrewService @Smoke @C1051005
  Scenario: Validate that the user is not able to add crew effects to the checked-out crew members.
    Given A new crew user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked Out" option from the filter
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Operations" details
    And User click on "Personal Items" tab
    Then Validate user should not be able to add crew effects to checked-out crew members

  @CrewService @Smoke @C1051006
  Scenario: Validate that the user is able to re-assign cabins to crew members.
    Given A new crew user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Cabin Assignment" item
    And User clear existing filters of embarkation date
    And User search assigned cabin crew member
    And User change status of assign to "Yes"
    And User search assigned cabin crew member
    And User save crew name
    And User select crew for cabin allocation
    And User click on the tab "Suggested Cabins"
    And User save cabin number
    And User select cabin for crew
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User verify cabin number "different" to crew member on crew information page


  @CrewService @Smoke @C1051007
  Scenario: Verify that the user is able to deny the boarding of the required crew member.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User search the profile photo crew user
    And Navigates to profile photo cabin crew profile
    Then User verify the crew profile picture

  @CrewService @Smoke @C1051009
  Scenario: Validate that user is able to view the crew member details in crew profile.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User search the profile photo crew user
    And Navigates to profile photo cabin crew profile
    Then User view the profile
    Then User verifies that the crew member details such as name, age, onboard status, gender, embarkation date, and disembarkation date are displayed


  @CrewService @C1051014
  Scenario: Validate that the user is able to execute bulk check-outs for the required crew members.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And Click on three dots and select Bulk Operation
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

  @CrewService @C1051015
  Scenario: Validate that the user is able to execute bulk check-outs for the required crew members.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And Click on three dots and select Bulk Operation
    And User unselects all guest members
    And Search for the bulk operation guest user
    And Selects one guest member
    And User click on Next button
    When User select radio button "Apply bulk Reservation Actions"
    And User click on Choose Operation Group tab Next button
    And User click on "Update Disembarkation Date" checkbox
    Then User set "currentDate" date for Disembarkation
    And User click on "Check Out" checkbox
    And User click on Confirm button
    And User views the confirmation pop-up and clicks Confirm
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User validate the 1 and disembarkation status

  @CrewService @Smoke @C1051016
  Scenario: Validate that the user is able to check out onboard crew member
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    Then User select "Leaving Today" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User click on chain icon at top right pane.
    And User click on "Check Out"
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    Then User verify the crew status as "Checked Out"


  @CrewService @Smoke @C1051017
  Scenario: Validate that the user is able to check out ashore crew member
    Given A new crew user is created "Shoreside"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User click on quick link "Checked In Ashore"
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    Then User select "Leaving Today" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User click on chain icon at top right pane.
    And User click on "Check Out"
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    Then User verify the crew status as "Checked Out"

  @CrewService @Smoke @C1051018
  Scenario: Validate that user is able to move a checked in Ashore guest (To Onboard) through the gangway.
    Given A new crew user is created "Shoreside"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | HR Operations Manager | Welcome2024! | Crew Services |
    Then User navigate to menu "Ship Security" service
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And Click on "Crew" icon at top right pane
    And User apply filter "Checked In"
    And User searches for the newly created crew member
    And User click on "Select"
    Then Verify user status "Onboard"

  @CrewService @Smoke @C1051021
  Scenario: Validate that the user is able to bulk update the required disembarkation date for the crew members.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And Click on three dots and select Bulk Operation
    And User unselects all guest members
    And Search for the bulk operation guest user
    And Selects one guest member
    And User click on Next button
    When User select radio button "Apply bulk Reservation Actions"
    And User click on Choose Operation Group tab Next button
    And User click on "Update Disembarkation Date" checkbox
    Then User set "currentDate" date for Disembarkation
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User validate the 1 and disembarkation status


  @CrewService @Smoke @C1050999
  Scenario: Validate that the user is able to remove denied boarding and Ashore denied to the crew members.
    Given A new crew user is created "Shoreside"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    # on Boarding denied
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User "enable" the "add-boarding-denied" toggle, verifies that "Lost & Found, Security Incidents" is clickable after the warning flag is tagged on the guest profile, and views the profile
    Then User verify the "BOARDING DENIED" warning to guest profile
    And User click on delete icon
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    Then User view the profile
    Then User verify the "BOARDING DENIED" warning not tagged to guest profile
    And User logout from nclh

      #Ashore Denied
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User "enable" the "add-ashore-denied" toggle, verifies that "Lost & Found, Security Incidents" is clickable after the warning flag is tagged on the guest profile, and views the profile
    Then User verify the "ASHORE DENIED" warning to guest profile
    And User click on delete icon
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    Then User view the profile
    Then User verify the "ASHORE DENIED" warning not tagged to guest profile

  @CrewService @Smoke @C1051019
  Scenario: Validate that the Otalio application displays an error message. 'You need to apply at least 1 filter to perform bulk operations' filter on click of bulk operation if user does not apply any filter on crew members screen.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    And Click on three dots and select Bulk Operation
    Then User verify error message on bulk operation "You need to apply at least 1 filter to perform Bulk Operations"

  @CrewService @Smoke @C1051020
  Scenario: Validate that the 'Check Out' checkbox is greyed out on the' Operational Details screen' of bulk operations in the Otalio application if the user add the expected future/expected today crew members in crew members screen.
    Given A new crew user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | HR Operations Manager | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    And Click on three dots and select Bulk Operation
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
    Then User validate the 1 and disembarkation status on reservation bulk log

  @CrewService @Smoke @C1051023
  Scenario: Validate that the application displays an alert message (Confirmation dialog box) when any crew record is selected for bulk operation that has already been selected in an earlier bulk operation selection.
    Given A new crew user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | HR Operations Manager | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    And Click on three dots and select Bulk Operation
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
    Then User validate the 1 and disembarkation status on reservation bulk log


  @CrewService @C1051024
  Scenario: Validate that user is able to update the disembarkation date of the checked-in on-board crew member earlier than the expected disembarkation date and after the embarkation date.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Assignments and Contract Information" details
    Then User saves the "Disembarkation Date"
    And User set date for "Disembarkation Date" in new crew reservation
    And User selects the "Disembarkation Port" from the dropdown
    And User click on "Save"
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    Then User validates the "Disembarkation Date" is updated

  @CrewService @Smoke @C1051025
  Scenario: Validate that user is able to add the crew effects in personal items for crew member.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Operations" details
    And User click on "Personal Items" tab
    Then User click on plus button
    And User click on "Add" button personal items tab
    Then User select the "Item Type" and select value
    Then User select the "Code" and select value
    And User type the Quantity as "4"
    Then User click on "Add"
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

  @CrewService @Smoke @C1051027
  Scenario: Validate that the Otalio shipboard application is able to record crew movements in and out of the ship.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Ship Security |
      | O&R | Crew Purser           | Welcome2024! | Ship Security |
    Then User navigate to menu "Ship Security" service
    And User navigates to "Gangway Logs" link
    Then User clear data on gangway logs
    And User select action types as "In"
    And User verify that action type list do not contain "Out"

  @CrewService @Smoke @SC3  @SC @C1051028
  Scenario: Verify that the user is able to deny the boarding of the required crew member.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open guest "Lost & Found, Security Incidents" information details
    And User click on three dots
    And User add the Boarding Denied
    And User click on Reason and select 'Person of Interest' and add Reason Detail
    And User click on "Save"
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"
    Then User view the profile
    Then User verify the "BOARDING DENIED" warning to guest profile


  @CrewService @Smoke @C1051030
  Scenario: Validate that the Otalio application is able to display a required crew with all the details: crew name, cabin number, embarkation, and disembarkation details.(Crew search)
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    Then User verify crew required details
      | Cabin | Last Name | First Name | Crew ID | Safety | Nation | Res. status | Operational Position | Operational Department | Embark Date | Disembark Date |


  @CrewService @C1051032
  Scenario: Validate that the user is able to print the passport labels report
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Reporting" service
    And User click on "Report Overview"
    Then User select the Folder as "Crew General"
    And User click on "Crew Passport Labels by Name" code link
    Then User click on "Preview" button in reporting
    Then User click on "Print"

  @CrewService @Smoke @C1051033
  Scenario: Validate that user is able to add the postings to respective crew member.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Reporting |
      | O&R | Crew Purser           | Welcome2024! | Reporting |
    Then User navigate to menu "Reporting" service
    And User click on "Report Overview"
    Then User select the Folder as "Crew General"
    And User click on "Crew Passport Labels by Name" code link
    Then User click on "Preview" button in reporting
    Then User click on "Print"
    #And User switch to pdf page and get pdf content
    #Then User verify content present in the PDF content


  @CrewService @Smoke @C1062971
  Scenario: Validate that the user is able to check out onboard crew member
    Given A new crew user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User click on chain icon at top right pane.
    And User verify the checkout button not available in quick action groups panel

  @CrewService @C1101819
  Scenario: Validate that the Otalio application is able to disable the added crew effects of the crewmember while checking out the crewmember
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | HR Operations Manager | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    Then User select "Leaving Today" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User click on chain icon at top right pane.
    Then User is able to view "Check Out"
    And User click on "Check Out"
    Then User is able to view "Confirm"
    And User click on "Confirm"
    And User verify the checkout button not available in quick action groups panel
    Then User verify the crew status as "Checked Out"

  @CrewService @Smoke @C2110320
  Scenario: Validate that user is able to create a new reservation for the crew member.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | HR Operations Manager | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User click on "New Reservation" link under status
    Then User enter the crew first name and last name
    Then User select the "Gender" as "Male"
    Then User set date for "Date of Birth" in add new document crew reservation
    And User able enable the toggle slider in new profile
    Then User save the fill name details on add new reservation
    And User click on "Next"
    Then User set date for "Embarkation Date" in add new crew reservation
    And User set date for "Disembarkation Date" in new crew reservation
    And User able click the tab options
    And User click on "Save & Next"
#    Then User select the requires cabin
    And User click on "Skip"
    Then User enter document number and click on open eye button
    Then User select the "Issuing Country" as "Georgia"
    Then User set date for "Expiry Date" in add new document on crew reservation
    And User click on "Save & Next" on add travel documents
    And User click on "Confirm"
    And User click on "Save & Open Profile" on new document crew reservation
    Then User verify create a new reservation for crew member.

  @CrewService @C2110321
  Scenario: Validate that user is able to edit the reservation for the crew member.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | HR Operations Manager | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Check-in" item
    Then User click on "search" in Check in dashboard
    And Click on "Crew" icon at top right pane
    And User clear the reservation status of the crew
    And User searches for the newly created crew member
    And User click on "Select"
    Then User select the "Nationality"
    And User click on "Save"
    Then User validates the nationality is updated

  @CrewService @Smoke @C2110322
  Scenario: Validate that the user is able to check out onboard crew member
    Given A new crew user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | HR Operations Manager | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User click on chain icon at top right pane.
    Then User is able to view "Cancel Reservation"
    And User click on "Cancel Reservation"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @CrewService @Smoke @C2110326
  Scenario: Validate that user is able to checkout the checked in onboard crew member.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User click on chain icon at top right pane.
    Then User is able to view "Check Out"
    And User click on "Check Out"
    Then User is able to view "Confirm"
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    Then User verify the crew status as "Checked Out"

  @CrewService @Smoke @C2110339
  Scenario: Validate that user is able to add the crew effects in personal items for crew member.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Operations" details
    And User click on "Personal Items" tab
    Then User click on plus button
    And User click on "Add" button personal items tab
    Then User select the "Item Type" and select value
    Then User select the "Code" and select value
    And User type the Quantity as "4"
    Then User click on "Add"
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

  @CrewService @Smoke @C2110340
  Scenario: Validate that user is able to edit the crew effects in personal items for crew member.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Operations" details
    And User click on "Personal Items" tab
    Then User verifies the edit icon functionality
    And User click on "Save"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @CrewService @C2110341
  Scenario: Validate that user is able to delete the crew effects in personal items for crew member.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Operations" details
    And User click on "Personal Items" tab
    Then User verifies the trash can icon functionality

  @CrewService @Smoke @C2110356
  Scenario: Validate that user is able to view all the authorizations made by crew member
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | HR Operations Manager | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Financial Information" details
    And User clicks on the "Payment Methods" section
    Then User should be able to view the payment method details of the crew member

  @CrewService @Smoke @C2136028
  Scenario: Validate that the Otalio application is able to auto-assign cabins to crew members.
    Given A new crew user is created without assigning a cabin number
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Cabin Assignment" item
    And User clear existing filters of embarkation date
    And User search unassigned cabin crew member
    And User change status of assign to "No"
    And User save crew name
    And User select crew for cabin allocation
    And User click on "Autoassign"
    And User click on "Save"
    And User click on "Yes"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @CrewService @Smoke @C2173355
  Scenario: Validate that the 'Flight info' tab is added to assignment and contract information tile for crew services
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Assignments and Contract Information" details
    Then User click on "Flight Info" tab and click on "Add Flight Info" in Cruise Information
    And User enter flight mandatory details
    And User click on "Save"
    Then User verify the flight details
    Then User verify Assignments and Contract Information details

    @CrewService @Smoke @C2208243
  Scenario: Validate that the user is able to view the crew flight details
      Given A new crew user is created "Onboard"
      Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
      Then User selects the "Crew Services" service from the left-side panel
      And User open service "Crew Search" item
      And User search the crew user
      And User able to click "lastName" in the detail table
      Then User view the profile
      And User open submenu "Assignments and Contract Information" details
      Then User click on "Flight Info" tab and click on "Add Flight Info" in Cruise Information
      And User enter flight mandatory details
      And User click on "Save"
      Then User verify the flight details
      Then User verify Assignments and Contract Information details

  @CrewService @Smoke @C1050332
  Scenario: Validate that user is able to change existing 'Cabin Assignment' for crew
    Given A new crew user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Cabin Assignment" item
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    And User change status of assign to "Yes"
    And User search expected future crew user for cabin assignment
    And User clear existing filters of embarkation date
    And User save crew name
    And User select crew for cabin allocation
    And User save cabin number
    And User unAssign cabin for crew member
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    And User refresh the page
    And User change status of assign to "No"
    And User search expected future crew user for cabin assignment
    And User clear existing filters of embarkation date
    And User select crew for cabin allocation
    And User save cabin number
    And User select cabin for crew
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @CrewService @Smoke @C2896438
  Scenario: Validate that user is able to view the emergency contact info of the crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User able to click "lastName" in the detail table
    And User open submenu "Personal Information" details
    And User clicks on the "Emergency Contact" section
    Then User should be able to view the emergency contact information of the crew

  @CrewService @Smoke @C1051029
  Scenario: Validate that the user is able to 'ashore deny' the required crew member
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User click on "Onboard" filter and select option to "Yes"
    And User search the crew user
    And User able to click "lastName" in the detail table
    And User open guest "Lost & Found, Security Incidents" information details
    And User click on three dots icon
    And User click on "Add Ashore Denied" text
    And User click on "Reason" and select "Person of Interest" and add Reason Detail
    And User click on "Save"
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"
    Then User view the profile
    Then User verify the "ASHORE DENIED" warning to guest profile

  @CrewService @Smoke @C3463936
  Scenario:Verify removal of mandatory requirement for salutation field in crew profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User able to click "lastName" in the detail table
    And User open submenu "Personal Information" details
    And User should be able to select "Mr." as the salutation dropdown
    And User should be able to select "None"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    Then User should be able to verify that a salutation is selected on the Guest Profile home page

  @CrewService @Smoke @C2110350
  Scenario: Validate that the user can view the payment method details of the crew member in Otalio.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    Then User save crew first name
    And User able to click "lastName" in the detail table
    And User open submenu "Financial Information" details
    And User clicks on the "Payment Methods" section
    Then User should be able to view the payment method details of the crew member

  @CrewService @Smoke @C2110354
  Scenario: Validate that user can deactivate the payment method of a crew member in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
#      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
#      | O&R | Crew Purser           | Welcome2024! | Crew Services |

      | NCL | Financial Management | Welcome2024! | Guest Services |
      | O&R | Financial Management | Welcome2024! | Guest Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    Then User save crew first name
    And User able to click "lastName" in the detail table
    And User open submenu "Financial Information" details
    And User clicks on the "Payment Methods" section
    Then User ensures a payment method is available by using an existing payment method or creating a new one
    Then The checked box on new payment method is to set it to default, deactivating the previous 'active' payment method
    And User click on "Save"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    Then User verifies that the payment method is updated in the account statement

  @CrewService @Smoke @C2110323
  Scenario: Validate that user is able to view the respective reservation logs for crew.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User search the crew user
    And User able to click "lastName" in the detail table
    And User open link "quick-links"
    And User views the "Logs" button
    And User navigate to "Logs"
    Then User should be able to view the reservation logs for the selected crew member

  @CrewService @Smoke @C1051010
  Scenario: Validate that user is able to check in the crew members in otalio application.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Check-in" item
    Then User click on "search" in Check in dashboard
    And Click on "Crew" icon at top right pane
    And User clear the reservation status of the crew
    And User searches for the newly created crew member
    And User views the "Select" button
    And User click on "Select"
    And User fills in all mandatory fields
    And User should verify the Save button is enabled and click it
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    And User click on "Check In" button in checkin page
    Then User should verify the boarding status of the selected crew member
    And User able to click "lastName" in the detail table
    Then User verifies board status "Checked In / On Board"

  @CrewService @Smoke @C4347747
  Scenario: Validate the addition of a direct link to the Reservation Bulk Log screen on the Guest/Crew Service Landing page
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    Then User display "Bulk Log" module
    And User clicks on the "Bulk Log" quick link
    And User switch to new tab
    Then User should be able to view the Crew Log page with the details

  @CrewService @Smoke @C1051031
  Scenario: Validate that the user is able to export the required crew list details.
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User click on hide columns button
    And User drag "DOB" and drop at the column header
    Then User verify "DOB" column displayed in the header row
    And User export ticket data
    And User verify that pdf is downloaded
#    And User switch to pdf page and get pdf content
#    Then User verify content present in the PDF content

  @CrewService @Smoke @C1051034
  Scenario: Validate that the user is able to export the passport labels report
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Reporting" service from the left-side panel
    Then User navigate to menu "Reporting" service
    And User click on "Report Overview"
    Then User select the Folder as "Crew General"
    And User click on "Crew Passport Labels by Name" code link
    And User clicks on the "Export" button
    Then User views the Report Format pop-up box
    And User click on "Confirm" button
    And User verify that pdf is downloaded

  @CrewService @Smoke @C3135241
  Scenario: Verify that the User is able to export the Crew cabin list
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    Then User select the Folder as "Crew General"
    And User click on "NCLH-00001" code link
    And User clicks on the "Export" button
    Then User views the Report Format pop-up box
    And User click on "Confirm" button
    And User verify that pdf is downloaded

  @CrewService @Smoke @C1050998
  Scenario: Validate that the user is able to print the board card of the expected crew member from the crew profile (Action Links)
    Given A new crew user is created without being checked in
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    Then User click on chain icon at top right pane
    And User click on "Print Boardcard"
    And User select the printer type in printer dropdown
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

  @CrewService @Smoke @C1050997
  Scenario: Validate that user is able to print board cards to the expected crew members using bulk board card printing
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    Then User display "Bulk BoardCard Printing" module
    And User clicks on the "Bulk BoardCard Printing" quick link
    And User switch to new tab
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    And User selects the "Bulk print crew board card" from the Template dropdown
    Then User click on "Print"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @CrewService @Smoke @C2208248
  Scenario: Validate that user can generate a report with historical ship data
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    Then User select the Folder as "Checked-In"
    And User click on "NCLH-00031" code link
    When the user selects past embarkation dates in the From and To fields and clicks "Export"
    Then User views the Report Format pop-up box
    And User click on "Confirm" button
    And User verify that pdf is downloaded

  @CrewService @Smoke @C4382416
  Scenario: Validate user can add/update 'Ashore Denied' and 'Boarding denied' to Reservation bulk operations
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And Click on three dots and select Bulk Operation
    Then Unselect all crew member and select 1 crew members
    And User click on Next button
    When User select radio button "Apply bulk Reservation Actions"
    And User click on Choose Operation Group tab Next button
    And User scroll down to Close Security Flag
    Then User should see the "Ashore/Boarding Denied" dropdown option as "Boarding"
    Then User should see the "Ashore/Boarding Denied" dropdown option as "Ashore"

  @CrewService @Smoke @C3463937
  Scenario: Verify that Crew IDs are included in the exported excel file from the crew members screen
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    Then User saves the crew ID, last name, and first name
    And User export ticket data
    Then User validates the last name and first name of the crew

  @CrewService @Smoke @C2956429
  Scenario:Validate that the added crew effects are disabled in the crew profile after crew member check-out and end of contract.
    Given A new crew user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | HR Operations Manager | Welcome2024! | Crew Services |
      | O&R | Crew Purser           | Welcome2024! | Crew Services |
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User search the crew user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Operations" details
    And User click on "Personal Items" tab
    Then User verifies the edit icon functionality
    And User click on "Save"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    Then User view the profile
    Then User click on chain icon at top right pane.
    Then User is able to view "Check Out"
    And User click on "Check Out"
    Then User is able to view "Confirm"
    And User click on "Confirm"
    Then User verify the crew status as "Checked Out"
    And User refresh the page
    Then User view the profile
    And User open submenu "Operations" details
    And User click on "Personal Items" tab
    Then User added crew effects should be disabled after crew checkout