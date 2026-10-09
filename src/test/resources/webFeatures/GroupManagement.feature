Feature: Group management

  1. Validate that user is able to add a group based on guests available in the stateroom
  2. Validate that user is able to add new group member for the group
  3. Validate that user is able to view required guest loyalty information in the group
  4. Validate that user is able to add group column using drag/drop functionality
  5. Validate that user is able to add new group
  6. Validate that user is able to add special request for required guest in the group(GS9)
  7. Validate that user is able to edit special request for required guest in the group(GS9)
  8. Validate that user is able to print folio for entire group


#  @GD
#  Scenario Outline: Validate that user is able to add new group member for the group
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Guest Services | Welcome2024! | Guest Service |
#    Then User navigate to menu "<Service>" service
#    And User open service link "<ServiceItem>"
#    And User clear reservation status of the crew
#    And User click on label or heading "Group Reservation Search"
#    And User open group on the reservation screen
#    And User open guest "Group Members" information details
#    And User select tab "All" under group members
#    And User tap on add icon
#    And User clear reservation status in the group management
#    And User search guests to add them in group
#    And User select role form options "Group-Member"
#    And User click on "Save"
#    And User select tab "Group-Member" under group members
#    Then User verify the guest present inside the group from "new group"
#
#    Examples:
#      | Service        | ServiceItem              |
#      | Guest Services | Group Reservation Search |
#
#  @GroupManagement @Smoke @GD5 @C2956339
#  Scenario Outline: Validate that user is able to add new group
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Guest Services | Welcome2024! | Guest Services |
#    Then User navigate to menu "<Service>" service
#    And User open service link "<ServiceItem>"
#    And User add group on the Group screen
#    And User save group code
#    And User click on "Save"
#    And User verify group is created
##    And User open created group
##    And User add reservation for group with estimated "2" and expected "5" members
##    Then Verify reservation is created for the group
#
#    Examples:
#      | Service        | ServiceItem    |
#      | Guest Services | Group Overview |

#  @GroupManagement @Smoke @GD9 @C2956385
#  Scenario Outline:Validate that user is able to reset the communication for entire group
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Guest Services | Welcome2024! | Guest Services |
#    Then User navigate to menu "<Service>" service
#    And User open service link "<ServiceItem>"
#    And User clear reservation status of the crew
#    And User click on label or heading "Group Reservation Search"
#    And User open group on the reservation screen
#    And User save group name
#    And User open submenu "Group Info" details
#    And User click on group info tab "Communication Info"
#    And User click on "Add new Communication Type" to add new communication type
#    And User click on "Communication Type"
#    And User select email option from dropdown
#    And User type communication value "test@ncl.com"
#    And User click on "Update"
#    Then User verify communication information is added
#    And User save communication type before modification of value
#    And User click on saved communication type
#    And User modify an option from dropdown
#    And User click on "Update"
#    And User save communication type after modification of value
#    And User click on "Reset"
#    #And User verify communication info is reset
#   # Then User verify communication type is updated with latest value
#
#    Examples:
#      | Service        | ServiceItem              |
#      | Guest Services | Group Reservation Search |


#  @GroupManagement @Smoke @GD11 @C1326948 @NotDependent @GDTemp
#  Scenario Outline:Validate that user is able to add folio for the group
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Financial Management | Welcome2024! | Guest Services |
#    Then User navigate to menu "<Service>" service
#    And User open service link "<ServiceItem>"
#    And User clear reservation status of the crew
#    And User click on label or heading "Group Reservation Search"
#    And User open group on the reservation screen
#    And User save group name
#    And User open guest "Account Statement" information details
#    And User open submenu "Account Statement" details
#    And User save the folio header count before clicking on add
#    And User click on add folio header
#    And User Verify the folio is added
#
#    Examples:
#      | Service        | ServiceItem              |
#      | Guest Services | Group Reservation Search |

#  @GroupManagement @Smoke @GD12 @C1326948 @NotDependent @GDTemp
#  Scenario Outline:Validate that user is able to add folio for the group
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Financial Management | Welcome2024! | Guest Services |
#    Then User navigate to menu "<Service>" service
#    And User open service link "<ServiceItem>"
#    And User clear reservation status of the crew
#    And User click on label or heading "Group Reservation Search"
#    And User open group on the reservation screen
#    And User save group name
#    And User open guest "Account Statement" information details
#    And User open submenu "Account Statement" details
#    And User navigate to folio "Folio 3"
#    Then User click on "Add Deposit"
#    And User select financial department "990001 - Cash Payment"
#    And User type the amount
#    And User save the amount
#    And User click on "Add"
#    Then User verify the "Department" folio
#
#    Examples:
#      | Service        | ServiceItem              |
#      | Guest Services | Group Reservation Search |

#------------------------------------------------------------------------------------------#
  @GroupManagement @Smoke @GD1 @C1326936
  Scenario: Validate that user is able to print folio for entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    Then User is able to view "Group Reservation Search"
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open guest "Account Statement" information details
    And User open submenu "Account Statement" details
    And User click on "Print Statement"
    And User click on "Account Statement Type"
    And User select an option from dropdown
    And User views the "Confirm" button
    And User click on "Confirm"

  @GroupManagement @Smoke @@GD2 @C1326937
  Scenario: Validate that user is able to add new group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Overview"
    Then User view plus icon in group page
    And User add group on the Group screen
    And User save group code
    And User views the "Save" button
    And User click on the "Save"
    And User view the success pop up
    And User verify group is created

  @GroupManagement @Smoke @GD3 @C1326938
  Scenario: Validate that user is able to update reservation dates of entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open guest "Reservation Info" information details
    And User views the previous reservation dates of the entire group
    And User update reservation for group  with estimated "2" and expected "5" members
    And User views the "Update" button
    And User click on the "Update"
    Then User verifies the updated reservation dates for the entire group

  @GroupManagement @Smoke @GD4 @C1326939
  Scenario: Validate that user is able to reset reservation dates of entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Overview"
    And user click on the group link list
    And User clicks on the Edit button under the Reservations section
    Then User is able to view the Add Reservation page box
    And User clicks on the Active From and Active To fields and updates the dates
    And User views the "Reset" button
    And User click on "Reset"
    Then User verifies that the Active From and Active To fields contain the previous details

  @GroupManagement @Smoke @GD5 @C1326943
  Scenario: Validate that user is able to apply payment method to entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Financial Management | Welcome2024! | Guest Services |
#      | O&R | Financial Management | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open submenu "Financial Information" details
    And User click on group info tab "Payment Methods"
    And User views the "Add new Payment Method" button
    And User click on the "Add new Payment Method"
    And User views the "Payment method" button
    And User click on the "Payment method"
    And User select financial department "990001 - Cash Payment" in payment method
    And User enter comment "test" in the comment inputBox
    And User views the "Save" button
    And User click on the "Save"
    And User view the success pop up
    Then User verify payment method is created

  @GroupManagement @Smoke @GD6 @C1326944
  Scenario: Validate that user is able to delete payment method for entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Guest Services | Welcome2024! | Guest Services |
#      | O&R | Guest Services | Welcome2024! | Guest Services |

    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User open group on the reservation screen
    And User open submenu "Financial Information" details
    And User click on group info tab "Payment Methods"
    And User click on "Add new Payment Method"
    Then User view delete icon for payment method
    And User deleted payment method
    Then User verify payment is deleted

  @GroupManagement @Smoke @GD7 @C1326950
  Scenario: Validate that user is able to add a group based on guests available in the stateroom
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User open group on the reservation screen
    And User save group name
    And User open guest "Group Members" information details
    And User select tab "Group-Member" under group members
    And User tap on add icon
    And search for the created guest user
    And User click on "Select"
    And User add the Guest member in group
    Then User views the added guest

  @GroupManagement @Smoke @GD8 @C1326953
  Scenario: Validate that user is able to modify the contact information of group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | O&R | Concierge | Welcome2024! | Guest Services |
      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open submenu "Group Info" details
    And User select tab "Communication Info" under group members
    And User click on group info tab "Communication Type" and Value 0 dropdown
    And User type communication value "test@mail.com"
    And User views the "Update" button
    And User click on the "Update"
    Then User verify communication information is added

  @GroupManagement @Smoke @GD9 @C1326954
  Scenario: Validate that user is able to add new communication type for entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | O&R | Concierge | Welcome2024! | Guest Services |
      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open submenu "Group Info" details
    And User click on group info tab "Communication Info"
    And User click on "Add new Communication Type"
    And User click on group info tab "Communication Type" and Value 1 dropdown
    And User type communication value "1234567"
    And User views the "Update" button
    And User click on the "Update"
    And User save communication type after modification of value

  @GroupManagement @Smoke @GD10 @C1326965
  Scenario: Validate that user is able to add a group based on guests available in the stateroom
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User save cabin number from the list
    And User click on cabin number dropdown
    And User enter cabin number in search box and select the checkbox
    And User save guest full name
    And User able to click "lastName" in the detail table
    And User open crew "Connections / Groups" information details
    And User open guest "Group Assignment" information details
    And User views the "Add new group" button
    And User click on "Add new group"
    And User adding a new guest in the group

  @GroupManagement @Smoke @GD11 @C1438828
  Scenario: Validate that user is able to add group column using drag/drop functionality
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | O&R | Concierge | Welcome2024! | Guest Services |
      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User click on hide columns button
    And User drag "Groups" and drop at the column header
    Then User check the column is drag

  @GroupManagement @Smoke @GD12 @C1438847
  Scenario: Validate that user is able to view required guest loyalty information in the group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open guest "Group Members" information details
    And User select tab "Group-Member" under group members
    And User click on profile
    And User open guest "Status" information details
    Then User verify loyalty information

  @GroupManagement @Smoke @GD10 @C1326945
  Scenario:Validate that user is able to see the payment methods for entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open submenu "Financial Information" details
    And User click on group info tab "Payment Methods"
    Then User verifies that the group payment methods are displayed

  @GroupManagement @Smoke @GD9 @C1326955
  Scenario:Validate that user is able to reset the communication for entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open submenu "Group Info" details
    And User click on group info tab "Communication Info"
    And User views the created Communication Info boxes
    And User add the communication info data
    And User views the "Reset" button
    And User click on "Reset"
    And User views the "Reset" button

  @GroupManagement @Smoke @C1326942
  Scenario: Validate that user is able to see the folio of entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User clicks on the "Group Reservation Search" link
    And User open group on the reservation screen
    And User save group name
    And User navigates to the "Account Statement" submenu
    Then User should see the "All Folio" text

  @GroupManagement @Smoke @C1326948
  Scenario: Validate that user is able to see the folio of entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User clicks on the "Group Reservation Search" link
    And User open group on the reservation screen
    And User save group name
    And User navigates to the "Account Statement" submenu
    Then User should see the "All Folio" text
    Then User validate the folio count and click on add folio button
    Then User validate the folio count is increased

  @GroupManagement @Smoke @C1326960
  Scenario: Validate that user is able to view the group member added by stateroom
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User clicks on the "Group Reservation Search" link
    And User open group on the reservation screen
    And User save group name
    And User navigates to the "Group Members" submenu
    And User select tab "Group-Member" under group members
    And User checks the guest in the Group Member list
    And User adds a guest member to the Group Member list
    Then User view the added member based on stateroom

  @GroupManagement @Smoke @C1326961
  Scenario:Validate that user is able to see the group name in the guest profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User add the guest in the group
    And User open service "Guest Search" item card
    And User search guest name in the group
    Then User view the profile
    Then User is able to see the group name in the guest profile

  @GroupManagement @Smoke @C1438827
  Scenario:Validate that user is able to cancel a special request for required guest in the group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User clicks on the "Group Reservation Search" link
    And User open group on the reservation screen
    And User save group name
    And User navigates to the "Group Members" submenu
    And User select tab "Group-Member" under group members
    And User add the Guest member in group
    And User select a guest from the list of group members
    Then User view the profile
    And User open guest "Cruise Information" information details
    And User select tab "Special Requests" under group members and click delete icon
    And User view delete pop up box
    And User views the "Delete" button
    And User click on the "Delete"
   And User view the success pop up

  @GroupManagement @Smoke @C2276343
  Scenario: Validate that user is able to search an existing group by name
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User searches by group name and verifies the result

  @GroupManagement @Smoke @C1326941
  Scenario: Validate that user is able to delete/cancel the group account
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User open group on the reservation screen
    And User open link "quick-links" and close pop message
    And User views the "Deactivate" button
    And User click the "Deactivate" button
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @GroupManagement @Smoke @C1326951
  Scenario: Validate that user is able to see the financial information of the group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Financial Management | Welcome2024! | Guest Services |
#      | O&R | Financial Management | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open submenu "Financial Information" details
    And User clicks on the "Payment Methods" section
    Then User should be able to view the payment method details for the selected group

  @GroupManagement @Smoke @C1326956
  Scenario: Validate that user is able to preview guest manifest(s) in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Reporting |
      | O&R | Concierge | Welcome2024! | Reporting |

#      | NCL | Group Service Coordinator | Welcome2024! | Reporting |
#      | O&R | Group Service Coordinator | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "Guest Manifests" report folder and selects it
    And User searches for "Guest Manifest by Cabin" in the report
    And User clicks the first "Guest Manifest by Cabin" matching title
    And User enters all required fields and clicks on the "Preview" button
    Then User should be able to view the report preview

  @GroupManagement @Smoke @C2110429
  Scenario: Validate that user is able to view event locations
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    Then User display "Event Locations" module
    And User clicks on the "Event Locations" quick link
    And User switch to new tab
    Then User should be able to view all event locations

  @GroupManagement @Smoke @C2110455
  Scenario: Validate that user is able to view event types
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Activity Planner |
      | O&R | Concierge | Welcome2024! | Activity Planner |

#      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
#      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    Then User display "Event Types" module
    And User clicks on the "Event Types" quick link
    And User switch to new tab
    Then User should be able to view all event types

  @GroupManagement @Smoke @C2211204
    Scenario: Validate that user is able to check in or check out the entire group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Group Service Coordinator | Welcome2024! | Guest Services |
#      | O&R | Group Service Coordinator | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    And User open group on the reservation screen
    And User open link "quick-links" and close pop message
    And User views the "Activate" button
    And User click the "Activate" button
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"
    And User views the "Deactivate" button
    And User click the "Deactivate" button
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @GroupManagement @Smoke @C2110446
  Scenario: Validate that user is able to add an event via quick add from calendar view
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User open service "Event Overview" item
    And User selects the first available time slot on the calendar
    And User click on "Quick Add"
    Then User verifies that the event is created in the calendar view

  @GroupManagement @Smoke @C2110443
  Scenario: Validate that user is able to view and edit created events in event overview
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User open service "Event Overview" item
    And User selects an existing event from the calendar
    And User switch to new tab
    And User edit the required details on the Event Details page
    And User click the "Save" button
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @GroupManagement @Smoke @C2110435 # Step 4 has not been validated yet because the Item Code link is not clickable.
  Scenario: Validate that user is able to view item code details
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    Then User display "Item Codes" module
    And User clicks on the "Item Codes" quick link
    And User switch to new tab
    Then User should be able to view all item codes

  @GroupManagement @Smoke @C2679579
  Scenario: Validate that user is able to create an event and add participants
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    Then User should see the "Add Event" button
    And User click on "Add Event" button
    And User enters all the required details in the "Add New Event" dialog
    And User click on "Add"
    And User switch to new tab
    And User views the "Close" button in an event page
    And User open submenu "Participants" details
    And User able to click "checkBox" in the detail table
    And User clicks on the "Add Selected" button in the Select Detail table
    And User click the "Add & Finish" buttons
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @GroupManagement @Smoke @C2110444
  Scenario: Validate that user is able to delete/cancel created events
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User creates a new event
    And User open service "Event Overview" item
    And User need to selects an existing event from the calendar
    And User clicks the Delete icon for the existing event
    And User views the "Confirm" button
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @GroupManagement @Smoke @C2110447
  Scenario: Validate that user is able to add an event from calendar view
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User open service "Event Overview" item
    And User selects the first available time slot on the calendar
    And User click the "Add" button
    And User switch to new tab
    And User views the "Add" button in an event page
    And User click the "Add" button
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @GroupManagement @Smoke @C2110449
  Scenario: Validate that user is able to view all events in the calendar by day, week, and month
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User creates a new event
    And User open service "Event Overview" item
    Then User validates the "Timeline Day" view
    Then User validates the "Timeline Week" view
    Then User validates the "Timeline Month" view

  @GroupManagement @Smoke @C2110450
  Scenario: Validate that user is able to view existing notes in the history of an event
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User creates a new event
    And User open service "Event Overview" item
    And User select the required event and open event details
    And User open submenu "Notes & History" details
    Then User verifies the "Notes" & "History" section and views the existing event notes

  @GroupManagement @Smoke @C2110452
  Scenario: Validate that user is able to add a new note to an existing event
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User open service "Event Overview" item
    And User selects the first available time slot on the calendar
    And User click the "Add" button
    And User switch to new tab
    And User open submenu "Notes & History" details
    And User click on plus icon in note and history page
    And User fills in the required details in the Add Note box
    And User clicks the "Save" button in the Add Note box
    Then User verify Successfully toast message appears on the screen with message "Record created successfully."
    And User click the "Add" buttons
    And User click the "Save" buttons
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @GroupManagement @Smoke @C2186243
  Scenario: Validate that user is able to create event and add item details
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Group Service Coordinator | Welcome2024! | Activity Planner |
      | O&R | Group Service Coordinator | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User open service "Event Overview" item
    And User selects the first available time slot on the calendar
    And User click the "Add" button
    And User switch to new tab
    And User open submenu "Items" details
    And User click on plus icon in item page
    And User fills in the required details in the item box
    And User views the "Save & Finish" button in an item page
    And User click on the "Save & Finish" button in an item page
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

  @GroupManagement @Smoke @C1326949
  Scenario: Validate that user is able to update the financial information of the group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |

#      | NCL | Financial Management | Welcome2024! | Guest Services |
#      | O&R | Financial Management | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service link "Group Reservation Search"
    And User open group on the reservation screen
    And User save group name
    And User open submenu "Financial Information" details
    And User clicks on the "Payment Methods" section
    Then User ensures a payment method is available by using an existing payment method or creating a new one
    Then The checked box on new payment method is to set it to default, deactivating the previous 'active' payment method
    And User click on "Save"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @GroupManagement @Smoke @C1326957
  Scenario: Validate that user is able to preview guest manifest(s) in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Reporting |
      | O&R | Concierge | Welcome2024! | Reporting |

#      | NCL | Group Service Coordinator | Welcome2024! | Reporting |
#      | O&R | Group Service Coordinator | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "Guest Manifests" report folder and selects it
    And User searches for "Guest Manifest by Cabin" in the report
    And User clicks the first "Guest Manifest by Cabin" matching title
    And User enters all required fields and clicks on the "Preview" button
    Then User should be able to view the report preview
    And User click on "Print" button
    And User switch to pdf page and get pdf content
    Then User verify content present in the PDF content

  @GroupManagement @Smoke @C1326958
  Scenario: Validate that user is able to email guest manifest(s) in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Reporting |
      | O&R | Concierge | Welcome2024! | Reporting |

#      | NCL | Group Service Coordinator | Welcome2024! | Reporting |
#      | O&R | Group Service Coordinator | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "Guest Manifests" report folder and selects it
    And User searches for "Guest Manifest by Cabin" in the report
    And User clicks the first "Guest Manifest by Cabin" matching title
    And User enters all required fields and clicks on the "Email" button
    Then User views the Report Format pop-up box
    And User click on "Confirm" button
    And User enters the email address in the New Message box
    And User click on "Send" button
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

  @GroupManagement @Smoke @C1326959
    Scenario: Validate that user is able to export guest manifest(s) in Otalio
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Reporting |
      | O&R | Concierge | Welcome2024! | Reporting |

#      | NCL | Group Service Coordinator | Welcome2024! | Reporting |
#      | O&R | Group Service Coordinator | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "Guest Manifests" report folder and selects it
    And User searches for "Guest Manifest by Cabin" in the report
    And User clicks the first "Guest Manifest by Cabin" matching title
    And User enters all required fields and clicks on the "Export" button
    Then User views the Report Format pop-up box
    And User click on "Confirm" button
    And User verify that pdf is downloaded

  @GroupManagement @Smoke @C1326962
  Scenario: Validate that user is able to preview package report(s) in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Reporting |
      | O&R | Concierge | Welcome2024! | Reporting |

#      | NCL | Group Service Coordinator | Welcome2024! | Reporting |
#      | O&R | Group Service Coordinator | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "Guest Services" report folder and selects it
    And User searches for "List of Guests with Package" in the report
    And User clicks the first "List of Guests with Package" matching title
    And User enters all required fields Package page and clicks on the "Preview" button
    Then User should be able to view the report preview

  @GroupManagement @Smoke @C1326963
  Scenario: Validate that user is able to export guest package(s) in Otalio
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Reporting |
      | O&R | Concierge | Welcome2024! | Reporting |

#      | NCL | Group Service Coordinator | Welcome2024! | Reporting |
#      | O&R | Group Service Coordinator | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "Guest Services" report folder and selects it
    And User searches for "List of Guests with Package" in the report
    And User clicks the first "List of Guests with Package" matching title
    And User enters all required fields Package page and clicks on the "Export" button
    Then User views the Report Format pop-up box
    And User click on "Confirm" button
    And User verify that pdf is downloaded

  @GroupManagement @Smoke @C1438829
  Scenario: Validate that user is able to email package(s) report in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Concierge | Welcome2024! | Reporting |
      | O&R | Concierge | Welcome2024! | Reporting |

#      | NCL | Group Service Coordinator | Welcome2024! | Reporting |
#      | O&R | Group Service Coordinator | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "Guest Services" report folder and selects it
    And User searches for "Guest Manifest by Cabin" in the report
    And User clicks the first "Guest Manifest by Cabin" matching title
    And User enters all required fields and clicks on the "Email" button
    Then User views the Report Format pop-up box
    And User click on "Confirm" button
    And User enters the email address in the New Message box
    And User click on "Send" button
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"