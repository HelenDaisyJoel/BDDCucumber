Feature: Safety Management

#  @SafetyManagement @Smoke @SM1 @C1050331 @C1050330
#  Scenario Outline: Validate that user should be able to create emergency drills for safety assignment
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    Then User navigate to menu "<Service>" service
#    And User navigated to module "<ServiceName>"
#    Then User verify error page else create emergency drill type and verify
##    And User verify the error page is displayed
##    And User navigated to safety management item "<SafetyManagementItem>"
##    And User delete the created "01TESTCODE" emergency drill
##    And User add emergency drill type using code "01TESTCODE", description "TestDescription" and listingNumber "number"
##    #Then User verify emergency drill item is created with code "Code" and description "Description"
##    And User delete the created "01TESTCODE" emergency drill
##    Then User verify "01TESTCODE" emergency drill is deleted
#
#    Examples:
#      | Service           | ServiceName  | SafetyManagementItem  |
#      | Safety Management | Safety Setup | Emergency Drill Types |
#
#  #@SafetyManagement @Smoke @SM2 @SC @C2956584
#  Scenario: Validate that user is able to see Ship On/Off Counts of guest, Validate that user is able to see Ship On/Off Counts of crew, Validate that user is able to see Ship On/Off Counts of visitors
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    And Clicks on "Safety Management" module
#    Then User navigates to "Safety Management" page
#    Then "Safety Management" landing page should be displayed
#    Then User navigate to menu "Guest Services" service
#    Then "Guest Service" landing page should be displayed
#    And User save "Checked In Onboard" guests count on guest service
#    And User click on link "Visitor Overview"
#    And User switch to new tab
#    And User clears valid from filter
#    And User saves "Visitors" count on guest service
#    Then User navigate to menu "Crew Services" service
#    And User save "Checked In Onboard" crews count on crew service
#    Then User navigate to menu "Safety Management" service
#    And User save "Guests Onboard" guests count "safety management"
#    And User save "Crew Onboard" guests count "safety management"
#    And User click on link "Visitors Onboard"
#    And User switch to new tab
#    And User clear board status filter
#
#    And User save "Visitors" guests count "safety management"
#    Then User compare guests,crews and visitors count from respective pages to safety management
#
#  @SafetyManagement @Smoke @SM3 @C1050325 @Sanity @C2956592
#  Scenario Outline: Validate that user is able to add 'Muster List(Safety Plan Build Up)'
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    Then User navigate to menu "<Service>" service
#    And User click on link "<ServiceName>"
#    And User add new safety number
#      | Captain | Assembly Coordination | Lifeboat Support | Assembly Coordination A-DEPUTY | Aft Air Filling Station | Lifeboat 01 |
#    And User save the safety number
#    And User click on add button
#    And User search for created safety number
#    Then User verify safety number is created
#
#    Examples:
#      | Service           | ServiceName          |
#      | Safety Management | Safety Plan Build Up |
#
#  @SM4
#  Scenario Outline: Validate that user is able to change 'Muster Station Location' for guest
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    Then User navigate to menu "<Service>" service
#    And User click on link "<ServiceName>"
#    And User add new cabin safety number for "Passenger"
#    And User save the safety number
#    And User click on ADD button
#    And User search for created Cabin safety number
#    Then User verify cabin safety number is created
#    And User verify the cabin type selected for "Passenger"
#
#    Examples:
#      | Service | ServiceName |
#      | Setup   | Cabins      |
#
#  @SM5
#  Scenario Outline: Validate that user is able to change 'Muster Station Location' for crew
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    Then User navigate to menu "<Service>" service
#    And User click on link "<ServiceName>"
#    And User add new cabin safety number for "Crew"
#    And User save the safety number
#    And User click on ADD button
#    And User search for created Cabin safety number
#    Then User verify cabin safety number is created
#    And User verify the cabin type selected for "Crew"
#
#    Examples:
#      | Service | ServiceName |
#      | Setup   | Cabins      |
#
#  #@SafetyManagement @Smoke @SM8 @C1050293 @C2956579 @C2956578
#  Scenario Outline: Validate that user is able to unassign safety station assignments to crew by crew member
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    Then User navigate to menu "<Service>" service
#    And User click on link "<ServiceName>"
#    And User click on "<SafetyCardBy>"
#    And User select "Assigned" filter option to "Yes"
#    And User save "<SafetyCardBy>" name or number
#    And User save "By Crew Member" name or number
#    And User save firstname of the crew member
#    And User select the checkbox "<SafetyCardBy>"
#    And User click on unAssign button
#    Then User verify crew member not present under assigned section
    #Then User verify toast message "SUCCESS" appears
#
#    Examples:
#      | Service           | ServiceName            | SafetyCardBy     |
#      | Safety Management | Safety Plan Assignment | By Crew Member   |
#      | Safety Management | Safety Plan Assignment | By Safety Number |
#
#  @SM11 #@C1050332 covered ib crew service
#  Scenario Outline: Validate that user is able to change existing 'Cabin Assignment' for crew
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Crew Services | Welcome2024! | Crew Services |
#    Then User navigate to menu "<Service>" service
#    And User open service "<CabinAssignment>" item
#    And User clear existing filters of embarkation date
#    And User change status of assign to "No"
#    And User save crew name
#    And User select crew for cabin allocation
#    And User save cabin number
#    And User select cabin for crew
#    And User refresh the page
#    Then User navigate to menu "<Service>" service
#    And User open service "<ServiceName>" item
#    And User clear reservation status of the crew
#    #And User search for crew and navigated to "<CrewName>" profile page
#    And User search for crew and open crew profile page
#    Then User verify cabin number assigned to crew member on crew information page
#
#    Examples:
#      | Service       | ServiceName | CrewName        | CabinAssignment  |
#      | Crew Services | Crew Search | DynamicCrewName | Cabin Assignment |
#
#  #@SafetyManagement @SM13 @Smoke @C2956604 @C2956610
#  Scenario Outline: Validate that user is able to edit and delete 'Training Call' activity for respective crew
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    Then User navigate to menu "<Service>" services
#    And User open service "<ServiceName>" item
#    And User click the activity button
#    And User able click on the Activity Category field and select as Safety
#    And User add "Reservation Status" filter to guest service page
#    And User select "Reservation Status" filter option to "Checked In"
#    And User select crew from the list and finish adding activity
#    And User open Edit Activity Screen
#    And User updates required fields and save
#    Then User verify updated activity date
#    When User deletes selected activity
#    Then User verifies activity deletion
#
#    Examples:
#      | Service          | ServiceName         |
#      | Activity Planner | Activities Overview |
#
#  #@SafetyManagement @Smoke @SM14 @C2956575
#  Scenario Outline: Validate that user is able to see created safety numbers
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    Then User navigate to menu "<Service>" service
#    And User click on link "<ServiceName>"
#    And User click on "<SafetyCardBy>"
#    Then User verifies display of safety numbers
#
#    Examples:
#      | Service | ServiceName | SafetyCardBy |
#      | Safety Management | Safety Plan Assignment | By Safety Number |
#
#  @SafetyManagement @Smoke @SM38 @C1165449
#  Scenario:Validate that user is able to confirm as completed in training activity in otalio
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    And Clicks on "Safety Management" module
#    Then User navigates to "Safety Management" page
#    And User click on link "Activity Overview"
#    Then "Overview" page should be displayed
#    Then User able to List of crew participants should be displayed
#    And User clicks the Select All checkbox, then all participants should be selected
#    And User able to click three dots and see "Mark as Completed"
#    And User able to click "Mark as Completed"
#    Then User able to view the confirm pop up
#    And click the confirm Button
#
#  @SafetyManagement @Smoke @SM39 @C1165451
#  Scenario:Validate that user is not able to add particular crew for more than one activity on same date at same timings
#    Given Login page is displayed
#    #The below comments are creating for data
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    And Clicks on "Safety Management" module
#    Then User navigates to "Safety Management" page
#    And User click on link "Activity Overview"
#    Then "Overview" page should be displayed
#    And User able click on the Add Activity Button
#    #1st time data adding
#    Then "Add Activity" landing page should be displayed
#    And User able click on the Activity Category field and select as Safety and time
#    And User OFF the Overlap toggle
#    And User click on NEXT button
#    And User click on Skip
#    And User is able to select the Crew in the dropdown
#    Then User able see all the crew detail in the table
#    And User is able to search for a crew member
#    And User able to click "checkBox" in the detail table
#    And Click on the FINISH button
#    Then validate the Finish button
#         #2nd time data adding
#    And Click on the ADD ACTIVITY button on same date
#    And User able click on the Activity Category field and select as Safety and time
#    And User OFF the Overlap toggle
#    And User click on NEXT button
#    And User click on Skip
#    And User is able to select the Crew in the dropdown
#    Then User able see all the crew detail in the table
#    And User is able to search for a crew member
#    And User able to click "checkBox" in the detail table
#    And Click on the FINISH button
#    #Warning message
#    And User is able to see the invalid message
  #-------------------------------------------------------------------------------------------------

  @SafetyManagement @Smoke @C1050289
  Scenario: Validate that user is able to create safety numbers
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Build Up"
    Then User view the plus icon
    And  user fills General Information details
    Then User view "Add" element
    And User click on add button
    And User search for created safety number
    Then User verify safety number is created

  @SafetyManagement @Smoke @C1050325
  Scenario: Validate that user is able to add 'Muster List(Safety Plan Build Up)'
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Build Up"
    Then User view the plus icon
    And  user fills General Information details
    Then User view "Add" element
    And User click on Save and Finish button if it is displayed
    And User search for created safety number
    Then User verify safety number is created

  @SafetyManagement @Smoke @C1050290
  Scenario: Validate that user is able to see created safety numbers
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Assignment"
    Then User checks if the "Active Mode" screen is enabled on the crew member page
    And User click on "By Safety Number"
    Then User able capture details of the user selected "By Safety Number"

  @SafetyManagement @Smoke @SM9 @C1050291
  Scenario: Validate that user is able to assign safety station assignments to crew by safety number
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    #Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Assignment"
    #Then "Safety Assignment" page should be displayed
    Then User checks if the "Active Mode" screen is enabled on the crew member page
    And User click on "By Safety Number"
    And User select "Assigned" filter option to "No"
    And User save "By Safety Number" name or number
    And User select the checkbox "By Safety Number"
    And User save the cabin number for assign or unassign
    And User select or unselect cabin for crew
    Then User view the assign button
    And User click the assign button
    And User refresh the page
    Then User is able to assign safety station assignments to crew by safety number
    And User save "By Safety Number" name or number

  @SafetyManagement @Smoke @C1050292
  Scenario: Validate that user is able to assign safety station assignments to crew by crew member
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    #Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Assignment"
    #Then "Safety Assignment" page should be displayed
    Then User checks if the "Active Mode" screen is enabled on the crew member page
    And User click on "By Crew Member"
    And User select "Assigned" filter option to "No"
    And User save "By Crew Member" name or number
    And User select the checkbox "By Crew Member"
    And User save the cabin number for assign or unassign
    And User select or unselect cabin for crew
    Then User view the assign button
    And User click the assign button
    And User refresh the page
    Then User is able to assign safety station assignments to crew by crew member
    #And User search guest "XXXCREWLAST02"
    And User save "By Crew Member" name or number

  @SafetyManagement @Smoke @C1050293
  Scenario: Validate that user is able to unassign safety station assignments to crew by crew member
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    #Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Assignment"
    #Then "Safety Assignment" page should be displayed
    Then User checks if the "Active Mode" screen is enabled on the crew member page
    And User click on "By Crew Member"
    And User select "Assigned" filter option to "Yes"
    #And User search guest "XXXCREWLAST02"
    And User save "By Crew Member" name or number
    And User select the checkbox "By Crew Member"
    Then User view the unassign button
    And User click on unAssign button
    Then User view Confirm button
    And User click on "Confirm"
    And User refresh the page
    Then User is able to unassign safety station assignments to crew by crew member
    #And User search guest "XXXCREWLAST02"
    And User save "By Crew Member" name or number

  @SafetyManagement @Smoke @C1050294
  Scenario: Validate that user is able to unassign safety station assignments to crew by Safety Number
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    #Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Assignment"
    #Then "Safety Assignment" page should be displayed
    Then User checks if the "Active Mode" screen is enabled on the crew member page
    And User click on "By Safety Number"
    And User select "Assigned" filter option to "Yes"
    And User save "By Safety Number" name or number
    And User select the checkbox By Safety Number
    Then User view the unassign button
    And User click on unAssign button
    Then User view Confirm button
    And User click on "Confirm"
    And User refresh the page
    Then User is able to unassign safety station assignments to crew by Safety Number
    And User save "By Safety Number" name or number

  @SafetyManagement @Smoke @C1050295
  Scenario:Validate that user is able to see updated data in the crew profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And Clicks on "Safety Management" module
    And User click on link "Safety Plan Assignment"
    And User click on "By Crew Member"
    #And User search guest "XXXCREW02"
    And User able to Click on any one of the crew name
    And User switch to new tab
    And User open submenu "Personal Information" details
    And User updates the first name and last name of the crew member
    And User refresh the page
    Then User verifies the crew profile name is updated

  @SafetyManagement @Smoke @C1050296
  Scenario: Validate that user is able to change assigned safety station assignments of particular crew to other crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Assignment"
    And User click on "By Safety Number"
    And User select "Assigned" filter option to "Yes"
    And User save "By Safety Number" name or number
    And User save crew name from the table
    And User select the checkbox "By Safety Number"
    Then User view the unassign button
    And User click on unAssign button
    Then User view Confirm button
    And User click on "Confirm"
    And User select "Assigned" filter option to "No"
    Then User seach for the safety number
    #And User search guest "XXXCREW01"
    Then User view the assign button
    And User click the assign button
    And User refresh the page
    Then User is able to change assigned safety station assignments of particular crew to other crew
    Then User verify both users are not same

  @SafetyManagement @Smoke @C1050301
  Scenario: Validate that user is able to see 'Current Safety Assignment' details of crew
  #Scenario: Validate the user is able to view 'current safety assignment' of crew members. @C2956613
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User getting the crew member name
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Crew Onboard"
    And User switch to new tab
    And User search "Crew ID" in crew member page
    #And User search guest "XXXCREWLAST01"
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open submenu "Safety" details
    Then User view current Saftey Assignment details

  @SafetyManagement @Smoke @C1050302
  Scenario: Validate that user is able to see 'History of Safety Assignment' details of crew
  #Scenario: Validate the user is able to view 'History of safety assignment' of crew members. @C2956614
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User getting the crew member name
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Crew Onboard"
    And User switch to new tab
    #And User search guest "XXXCREWLAST01"
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open submenu "Safety" details
    And User clicks on the "History of Safety Assignment" section
    Then User view current "History of Safety Assignment" details

  @SafetyManagement @Smoke @C1050303
  Scenario: Validate that user is able to see 'Upcoming Drills/ Training details of crew
  #Scenario:Validate the user is able to view 'Upcoming Drills / Training' of crew members. @C2956615
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User create a new Upcoming Drills Training details of crew
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Crew Onboard"
    And User switch to new tab
    And User search "Crew ID" in crew member page
    #And User search guest "XXXCREWLAST01"
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open submenu "Safety" details
    And User clicks on the "Upcoming Drills / Training" section
    Then User view current "Upcoming Drills / Training" details

  @SafetyManagement @Smoke @C1050304
  Scenario: Validate that user is able to see 'Attended Drills/Trainings' details of crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User create a new Attended Drills Trainings details of crew
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Crew Onboard"
    And User switch to new tab
    And User search "Crew ID" in crew member page
    #And User search guest "XXXCREWLAST01"
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open submenu "Safety" details
    And User clicks on the "Attended Drills / Trainings" section
    Then User view current "Attended Drills / Trainings" details

  @SafetyManagement @Smoke @C1050305
  Scenario: Validate that user is able to create 'Training Call' for respective crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User getting the crew member name
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates an activity
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User is able to select the Crew in the dropdown
    #And User search guest "XXXCREWLAST01"
    And User search "Crew ID" in crew member page
    And User able to click "checkBox" in the detail table
    And Click on the FINISH button
    Then User view the finish button
    Then validate the Finish button

  @SafetyManagement @Smoke @C1050307
  Scenario: Validate that user is able to edit 'Training Call' activity for respective crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User clicks the day having activity data
    And User select the activity list
    And User views the "Edit activity" button
    And User click on "Edit activity"
    And User views the "Confirm" button
    And User click on "Confirm"
    And User updates the active date
    And User views the "Save" button
    And User click on "Save"
    And User click on "Save & Finish"
    And User views the "Edit activity" button

  @SafetyManagement @Smoke @C1050327
  Scenario: Validate that user is able to see Ship On/Off Counts of guest
  #Scenario: Validate that user is able to see the Ship On/Off Counts of guest #C2956582
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User validates that "Guests Onboard" count
    And User click on link "Guests Onboard"
    And User switch to new tab
    Then User verifies the onboard count with table items count

  @SafetyManagement @Smoke @C1050328
  Scenario: Validate that user is able to see Ship On/Off Counts of crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
      | NCLH | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User validates that "Crew Onboard" count
    And User click on link "Crew Onboard"
    And User switch to new tab
    Then User verifies the onboard count with table items count

  @SafetyManagement @Smoke @C1050329
    Scenario: Validate that user is able to see Ship On/Off Counts of visitors
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User navigates to "Safety Management" page
    Then User validates that "Visitors Onboard" count
    And User click on link "Visitors Onboard"
    And User switch to new tab
    Then User verifies the onboard count with table items count

  @SafetyManagement @Smoke @C1141445
  Scenario: Validate that user is able to print safety card for crew by safety number
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Assignment"
    Then User checks if the "Active Mode" screen is enabled on the crew member page
    And User click on "By Safety Number"
    And User select "Assigned" filter option to "Yes"
    And User save "By Safety Number" name or number
    And User save crew name from the table
    And User select the checkbox "By Safety Number"
    Then User is able to view "Print Safety Card"
    And User click on "Print Safety Card"

  @SafetyManagement @Smoke @C1141462
  Scenario: Validate that user is able to print safety card to crew by crew member
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    #Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Assignment"
    Then User checks if the "Active Mode" screen is enabled on the crew member page
    Then "Safety Assignment" page should be displayed
    And User click on "By Crew Member"
    And User select "Assigned" filter option to "Yes"
    And User save "By Crew Member" name or number
    And User select the checkbox By Crew Member
    And User wait for "3" seconds
    And User select the checkbox "By Crew Member"
    And User select the checkbox "By Crew Member"
    And User wait for "3" seconds
    Then User is able to view "Print Safety Card"
    And User click on "Print Safety Card"

  @SafetyManagement @Smoke @C2956605
  Scenario: Validate the user is able to remove participants from training activity.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User select the activity list
    And User add the Guest member
    Then Checking the data is added
    And User able to see "Remove Participant(s)"
    And User able to click "Remove Participant(s)"
    Then User able to view the confirm pop up
    And click the confirm Button

  @SafetyManagement @Smoke @C2956585
  Scenario: Validate that user is able to see the disembarked guest count
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item card
    And User clear reservation status filter of the guest
    And User is able to select the "Checked Out" in the "Reservation Status" dropdown
    Then User able see the "Reservation Status" column data as "CO"
    Then User verifies the onboard count with table items count

  #@SafetyManagement @Smoke @C2956586
  Scenario:Validate that security manager is able to opens Gate mode in otalio
    When Input credentials to login into nclh
      | Security Guard | Welcome2024! | Ship Security |
    Then User navigate to menu "Ship Security" service
    And User navigated to module "Start Gangway"
    And User wait for "3" seconds

  #@SafetyManagement @Smoke @C2956588
  Scenario:Validate that manager starts the muster drill in Apass, view overall passenger/crew count
    Given Login page is displayed
    When Input credentials to login into nclh
      | Security Guard | Welcome2024! | Ship Security |
    Then User navigate to menu "Ship Security" service
    And User navigated to module "Start Gangway"
    And User select location "Gangway Internal - @Health Check A"
    #-------------------Guests----------------------------
    And User save "Checked In Onboard" and "Guests" count on gangway
    And User save "Checked In Ashore" and "Guests" count on gangway
    And User save "Other Status Onboard" and "Guests" count on gangway
    And User save "Expected" and "Guests" count on gangway
    And User save "Embarked" and "Guests" count on gangway
    And User save "Disembarking" and "Guests" count on gangway
    And User save "Disembarked" and "Guests" count on gangway

    #-------------------Crew--------------------------------
    And User save "Checked In Onboard" and "Crew" count on gangway
    And User save "Checked In Ashore" and "Crew" count on gangway
    And User save "Other Status Onboard" and "Crew" count on gangway
    And User save "Expected" and "Crew" count on gangway
    And User save "Embarked" and "Crew" count on gangway
    And User save "Disembarking" and "Crew" count on gangway
    And User save "Disembarked" and "Crew" count on gangway


  #@SafetyManagement @Smoke @C2956593
    Scenario: Validate that user is able to filter 'Assembly Station' in Build Up plan
    Given Login page is displayed
    When Input credentials to login into nclh
      | Safety Officer | Welcome2024! | Safety Management |
    And Clicks on "Safety Management" module
    Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Build Up"
    And Click on Add more filters icon
    Then User is able to filter Assembly Station in Build Up plan
    Then User able see the "Assembly Station" column data as "Station"

  @SafetyManagement @Smoke @C2956617
  Scenario:Validate the user is able to view the cabin number assigned to the guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User validates that "Guests Onboard" count
    And User click on link "Guests Onboard"
    And User switch to new tab
    #And User search guest "XXXTRAINING1"
    And User able to click "lastName" in the detail table
    And User open submenu "Cruise Information" details
    Then User navigate to "Cabin info" section
    And User views the "Cabin info" button

  #@SafetyManagement @Smoke @C2956625
  Scenario:Validate the user is able to view comment field in the 'Special needs' section.
    Given Login page is displayed
    When Input credentials to login into nclh
      | Safety Officer | Welcome2024! | Safety Management |
    And Clicks on "Safety Management" module
    Then User navigates to "Safety Management" page
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User save guest first name
    Then User save guest full name
    And User able to click "lastName" in the detail table
    And User open submenu "Personal Information" details
    And User clicks on the "Special Needs" section
    Then user add the data in special fileds
    And User click on "Save"

  #@SafetyManagement @Smoke @C3572262
  Scenario: Validate that user is able to view the instruction text to increase CHAR length to 2000 in Buildup Plan
    Given Login page is displayed
    When Input credentials to login into nclh
      | Safety Officer | Welcome2024! | Safety Management |
    And Clicks on "Safety Management" module
    Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Build Up"
    And User wait for "3" seconds
    And Click on the required safety number link
    Then Scroll down to see Specific Safety Instruction tab
    And Enter maximum limit of 2000 chars in instructions field
    And User wait for "3" seconds
    And User clicks the Save button, and a pop‑up appears

  #@SafetyManagement @Smoke @C3572263 @C2956587
  Scenario:Validate that user is able to display drill/ training absence count by crew member for a given time frame
  #Scenario:Validate that otalio is able to show crew member drill absences
    Given Login page is displayed
    When Input credentials to login into nclh
      | Safety Officer | Welcome2024! | Safety Management |
    Then User navigate to menu "Safety Management" service
    Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Assignment"
    Then "Safety Assignment" page should be displayed
    And User click on "By Crew Member"
    And User select "Assigned" filter option to "Yes"
    And User save "By Crew Member" name or number
    Then User navigate to menu "Safety Management" service
    Then User navigates to "Safety Management" page
    And User click on link "Activity Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates an activity for an Attended Drills session
    And User click on NEXT button
    And User click on Skip
    And User is able to select the Crew in the dropdown
    And User search "Crew ID" in crew member page
    And User able to click "checkBox" in the detail table
    And Click on the FINISH button
    Then validate the Finish button
    And User search "Crew ID" in crew member page
    Then Checking the data is added
    And User able to see "Mark as Absent"
    And User able to click "Mark as Absent"
    Then User able to view the confirm pop up
    And click the confirm Button
    Then user is able validate the count crew member drill absences
    Then user is able validate the count

  #@SafetyManagement @Smoke @C2956612 @C2956589
  Scenario:Validate the user is able to view "Training/Certifications" of crew members.
  #Scenario:Validate that user is able to identify certifications and training required for the safety assignment for crew
    Given Login page is displayed
    When Input credentials to login into nclh
      | Safety Officer | Welcome2024! | Safety Management |
    And Clicks on "Safety Management" module
    Then User navigates to "Safety Management" page
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User select "Onboard" filter option to "Yes"
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open submenu "Operations" details
    And User clicks on the "Training / Certifications" section
    Then user verifies training certification record exists or creates a new one

  @SafetyManagement @Smoke @C1050297
  Scenario: Validate that user should be able to create emergency drills for safety assignment
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Setup"
    And User switch to new tab
    And User add emergency drill type
    Then User view safty emergency plus icon
    And User click on "add"
    And User enter all the fields in Add new Emergency Drill Type page
    Then User view "Add" element
    And User click on "Add"
    And User search for created emergency drills for safety assignment number

  #@SafetyManagement @Smoke @SM23 @C2956611
  Scenario:Validate the user is not able to create another activity with same participants on the same date when the 'allow overlap' toggle bar is disabled.
    Given Login page is displayed
    #The below comments are creating for data
    When Input credentials to login into nclh
      | Safety Officer | Welcome2024! | Safety Management |
    Then User navigate to menu "Safety Management" service
    Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Assignment"
    Then "Safety Assignment" page should be displayed
    And User click on "By Crew Member"
    And User select "Assigned" filter option to "Yes"
    And User save "By Crew Member" name or number
    Then User navigate to menu "Safety Management" service
    Then User navigates to "Safety Management" page
    And User click on link "Activity Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates an activity for an Attended Drills session
    And User OFF the Overlap toggle
    And User click on NEXT button
    And User click on Skip
    And User is able to select the Crew in the dropdown
    And User search "Crew ID" in crew member page
    And User able to click "checkBox" in the detail table
    And Click on the FINISH button
    Then validate the Finish button
         #2nd time data adding
    And User able click on the Add Activity Button
    And User creates an activity for an Attended Drills session
    And User OFF the Overlap toggle
    And User click on NEXT button
    And User click on Skip
    And User is able to select the Crew in the dropdown
    And User search "Crew ID" in crew member page
    And User able to click "checkBox" in the detail table
    And Click on the FINISH button
    #Warning message
    And User is able to see the invalid message

  @SafetyManagement @Smoke @C1141391
  Scenario: Validate that user is able to confirm training attendance in otalio
  #Scenario: Validate the user is able to mark activity as completed. #@C2956607
  #Scenario:Validate the user is able to view 'Attended Drills / Trainings' of crew members.@C2956616
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User select the activity list
    And User add the Guest member
    Then Checking the data is added
    And User able to see "Mark as Attending"
    And User able to click "Mark as Attending"
    Then User able to view the confirm pop up
    And click the confirm Button
    Then Checking the data is added

  @SafetyManagement @Smoke @C1050310
  Scenario: Validate that user is able to delete 'Training Call' activity
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User clicks the day having activity data
    When User deletes selected activity
    And User select the activity list
    And User views the "Delete Activity" button
    And User click on "Delete Activity"
    And User views the "Confirm" button
    And User click on "Confirm"
    Then User verifies activity deletion

  @SafetyManagement @Smoke @C2956604
  Scenario: Validate the user is able to edit training activity for crew.
  #Scenario: Validate the user is able to delete the activity. @C2956609
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User wait for "3" seconds
    And User clicks the day having activity data
    And User select the activity list
    And User views the "Edit activity" button
    And User click on "Edit activity"
    And User views the "Confirm" button
    And click the confirm Button pop up
    And User updates the active date
    And User views the "Save & Finish" button
    And User click on "Save & Finish"

  @SafetyManagement @Smoke @C2956603
  Scenario: Validate the user is able to create training activity for crew.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User getting the crew member name
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates an activity
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User click on Account type filter and enable crew checkbox
    Then User view the Add button
    And Click on the FINISH button
    Then User view the finish button
    Then validate the Finish button

  @SafetyManagement @Smoke @C2956606
  Scenario: Validate the user is able to mark participants as completed in training activity.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User select the activity list
    And User add the Guest member
    Then Checking the data is added
    And User able to see "Mark as Completed"
    And User able to click "Mark as Completed"
    Then User able to view the confirm pop up
    And click the confirm Button

  #@SafetyManagement @Smoke @C2956626
  Scenario: Validate the user is able to print a safety card from otalio application.
    Given Login page is displayed
    When Input credentials to login into nclh
      | Safety Officer | Welcome2024! | Safety Management |
    Then User navigate to menu "Safety Management" service
    And User click on link "Safety Plan Assignment"
    And User click on "By Crew Member"
    And User select "Assigned" filter option to "Yes"
    And User save "By Crew Member" name or number
    And User select the checkbox By Crew Member
    And User wait for "3" seconds
    And User select the checkbox "By Crew Member"
    And User select the checkbox "By Crew Member"
    And User wait for "3" seconds
    Then User is able to view "Print Safety Card"
    And User click on "Print Safety Card"

  @SafetyManagement @Smoke @C1165449
  Scenario: Validate that user is able to mark an activity as completed
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User select the activity list
    And User add the Guest member
    Then Checking the data is added
    And User able to see "Mark as Completed"
    And User able to click "Mark as Completed"
    Then User able to view the confirm pop up
    And click the confirm Button
    Then Checking the data is added

  @SafetyManagement @Smoke @C1165451
  Scenario: Validate that user is not able to add particular crew for more than one activity on same date at same timings
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    #And User creates an activity
    And User able click on the Activity Category field and select as Safety and time
    And User OFF the Overlap toggle
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User is able to select the Crew in the dropdown
    #And User search guest "XXXCREWLAST01"
    And User search "Crew ID" in crew member page
    And User able to click "checkBox" in the detail table
    And Click on the FINISH button
    Then User view the finish button
    Then validate the Finish button


    And User able click on the Add Activity Button
    #And User creates an activity
    And User able click on the Activity Category field and select as Safety and time
    And User OFF the Overlap toggle
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User is able to select the Crew in the dropdown
    #And User search guest "XXXCREWLAST01"
    And User search "Crew ID" in crew member page
    And User able to click "checkBox" in the detail table
    And Click on the FINISH button
    And User is able to see the invalid message
    Then User view the finish button
    Then validate the Finish button

  @SafetyManagement @Smoke @C2956608
  Scenario:Validate the user is able to mark participant as absent.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User select the activity list
    And User add the Guest member
    Then Checking the data is added
    And User able to see "Mark as Absent"
    And User able to click "Mark as Absent"
    Then User able to view the confirm pop up
    And click the confirm Button
    Then Checking the data is added

  @SafetyManagement @Smoke @C1202327
  Scenario:Validate that otalio is able to show crew member drill absences
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User select the activity list
    Then Checking the data is added
    And User able to see "Mark as Absent"
    And User able to click "Mark as Absent"
    Then User able to view the confirm pop up
    And click the confirm Button
    Then Checking the data is added

  @SafetyManagement @Smoke @C2055893
  Scenario: Validate that user is able to display drill/training absence count by crew member for a given time frame
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User clicks on the "Crew Onboard" link
    And User switch to new tab
    And User search absence crew person
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User navigates to the "Safety" submenu
    And User clicks on the "Drills / Training Absence History" section
    Then User view current "Drills / Training Absence History" details
    Then User check the count of Drills Training Absence History

  @SafetyManagement @Smoke @C2408123
  Scenario:Validate that user is able to set up the in port manning group for the required crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User clicks on the "Activity Overview" link
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates an activity for an IPM session
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User selects the "In Port Manning Groups" dropdown group checkbox
    Then User view the Add button
    And Click on the FINISH button
    Then User view the finish button
    Then validate the Finish button
    Then User verify the IPM activity is displayed

  @SafetyManagement @Smoke @C2956594
  Scenario: Validate that user is able to view 'Emergency Role/Team' in safety buildup
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Build Up"
    Then User clicks the Emergency Role dropdown and validates the values

  @SafetyManagement @Smoke @C1050287
  Scenario: Validate that locations are created as 'Muster Station' or 'Lifecraft/Rescue Boats'
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Build Up"
    Then User view the plus icon
    And  user fills General Information details
    Then User view "Add" element
    And User click on Save and Finish button if it is displayed
    And User search for created safety number
    Then User verify safety number is created

  @SafetyManagement @Smoke @C1050298
  Scenario: Validate that user is able to identify certifications and trainings of crew members
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User clicks on the "Crew Onboard" link
    And User switch to new tab
    #And User search guest "XXXCREWLAST01"
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User navigates to the "Operations" submenu
    And User clicks on the "Training / Certifications" section
    And User adds the "Training / Certifications"
    Then User check the count of Training Certifications

  @SafetyManagement @Smoke @C1050306
  Scenario: Validate that user is able to see training activity for respective crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User create a new Upcoming Drills Training details of crew
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Crew Onboard"
    And User switch to new tab
    And User search "Crew ID" in crew member page
    #And User search guest "XXXCREWLAST01"
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open submenu "Safety" details
    And User clicks on the "Upcoming Drills / Training" section
    Then User view current "Upcoming Drills / Training" details

  @SafetyManagement @Smoke @C1050309
  Scenario: Validate that user is able to Reset 'Training Call' activity details after making changes to activity list
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User clicks the day having activity data
    And User select the activity list
    Then Checking the data is added
    And User views the "Edit activity" button
    And User click on "Edit activity"
    And User views the "Confirm" button
    And User click on "Confirm"
    Then User View the original value
    And User updates the active date
    And User views the "Reset" button
    And User click on "Reset" button
    Then User verifies the activity date is reset to the original value

  @SafetyManagement @Smoke @C1050322
  Scenario: Validate that user is able to add 'Respective Disability' for particular guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Guest Services |
      | O&R | Safety Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User search guest "xxx"
    Then User save guest first name
    Then User save guest full name
    And User able to click "lastName" in the detail table
    And User open submenu "Personal Information" details
    And User clicks on the "Special Needs" section
    And User adds a disability for the selected guest
    And User click on "Save" button
    Then User verifies the disability is added under Special Needs

  @SafetyManagement @Smoke @C1050323
  Scenario: Validate that user is able to comment on 'Respective Disability' for particular guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Guest Services |
      | O&R | Safety Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User search guest "xxx"
    Then User save guest first name
    Then User save guest full name
    And User able to click "lastName" in the detail table
    And User open submenu "Personal Information" details
    And User clicks on the "Special Needs" section
    And User adds a disability for the selected guest
    And User enters a comment for the Special Needs
    And User click on "Save" button
    Then User verifies the disability is added under Special Needs

  @SafetyManagement @Smoke @C2956620
  Scenario: Validate the user is able to view the Name, Nationality, Age, Language, and Guest Photo of a guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Guest Services |
      | O&R | Safety Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User clicks on the guest profile that has a profile image
    And User captures and saves the guest's Name, Nationality, Age, and Language
    And User open submenu "Personal Information" details
    Then User validates that the saved guest details match the Personal Information section

  @SafetyManagement @Smoke @C1050326
  Scenario: Validate that user is able to filter 'Assembly Station' in Build Up plan
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Build Up"
    And User click on Add more filters icon
    And User enables the "Assembly Station" filter by selecting its checkbox
    Then User validates that the table contains the selected Assembly Station

  @SafetyManagement @Smoke @C1264662
  Scenario: Validate that 'Emergency Plan' and 'In port Manning' in safety plan buildup shown as optional
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Build Up"
    And User click on the first Safety Number
    Then User verifies the "In Port Manning" and "Emergency Plan" safety section is displayed

  @SafetyManagement @Smoke @C1707495
  Scenario: Validate that user is able to view crew details by search crew first name
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Assignment"
    Then User selects a random crew member
    And User searches for the selected crew member
    Then User validates that the search results contain the selected crew member

  @SafetyManagement @Smoke @C1707496
  Scenario: Validate that user is able to view crew details by search safety number
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Safety Plan Assignment"
    And User click on "By Safety Number"
    Then User selects a random safety number
    And User searches for the selected safety number
    Then User validates that the search results contain the selected safety number

  @SafetyManagement @Smoke @C1050308
  Scenario: Validate that user is able to see updated 'Training Call' activity for respective crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User saves the previous Training Call activity for the respective crew
    Then User update Training Call activity for the respective crew
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Crew Onboard"
    And User switch to new tab
    Then User search guest first name
    And User click on the last name
    And User open submenu "Safety" details
    And User clicks on the "Upcoming Drills / Training" section
    Then User updated Training Call activity for respective crew

  @SafetyManagement @Smoke @C1188567
  Scenario: Validate that user is able to add activity for expected future crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    Then User view the Activity Overview button
    And User click on link "Activity Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates a new "Safety Training" activity
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User selects the crew whose "Reservation Status" is "Expect Future"
    Then User view the Add button
    And Click on the FINISH button
    Then User view the finish button
    Then validate the Finish button
    Then User verifies the "Safety Training" Call activity contains the selected crew member's name

#  @SafetyManagement @Smoke @C2110318
#  Scenario: Validate that user is able to add crew to Ashore denied list via manning
#    Given Login page is displayed
#    When Input credentials to login into nclh portal
#      | NCL | Safety Officer | Welcome2024! | Activity Planner |
#      | O&R | Safety Officer | Welcome2024! | Activity Planner |
#    Then User selects the "Activity Planner" service from the left-side panel
#    Then User view the Activity Overview button
#    And User click on link "Activity Overview"
#    And User switch to new tab
#    And User able click on the Add Activity Button
#    And User creates a new "IPM" activity
#    Then User view next button
#    And User click on NEXT button
#    And User view on Skip button
#    And User click on Skip
#    And User selects the crew whose "Reservation Status" is "Checked In" and select "In Port Manning Groups" dropdown group checkbox
#    Then User view the Add button
#    And Click on the FINISH button
#    Then User view the finish button
#    Then validate the Finish button
#    Then User verifies the "IPM" Call activity contains the selected crew member's name
   # code need to be update

#  @SafetyManagement @Smoke @C2110319
#  Scenario: Validate that user is able to add crew to Ashore denied list via Safety Training
#    Given Login page is displayed
#    When Input credentials to login into nclh portal
#      | NCL | Safety Officer | Welcome2024! | Activity Planner |
#      | O&R | Safety Officer | Welcome2024! | Activity Planner |
#    Then User selects the "Activity Planner" service from the left-side panel
#    Then User view the Activity Overview button
#    And User click on link "Activity Overview"
#    And User switch to new tab
#    And User able click on the Add Activity Button
#    And User creates a new "Safety Training" activity
#    Then User view next button
#    And User click on NEXT button
#    And User view on Skip button
#    And User click on Skip
#    And User filters by "Safety Positions" and selects the required crew groups
#    Then User view the Add button
#    And Click on the FINISH button
#    Then User view the finish button
#    Then validate the Finish button
#    Then User verifies the "Safety Training" Call activity contains the selected crew member's name
  # code need to be update

  @SafetyManagement @Smoke @C1050324
  Scenario: Validate that user is able to delete 'Respective Disability' for particular guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL|  Guest Service Manager | Welcome2024! | Guest Services |
      |O&R|  Guest Service Manager | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User search guest "xxx"
    Then User save guest first name
    Then User save guest full name
    And User able to click "lastName" in the detail table
    And User open submenu "Personal Information" details
    And User clicks on the "Special Needs" section
    And User Add a respective disability for the guest only if it is not already present
    And User clicks on the delete icon
    Then User validates that the Special Need is deleted

  @SafetyManagement @Smoke @C1275215
  Scenario: Validate that user is able see the expected guest and crew count
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Crew Onboard"
    And User switch to new tab
    And User clears the reservation status and applies the "Expect Future", "Expect Today", and "Onboard" as "Yes" filters
    Then User validates the crew count displayed after applying the Expect Future and Expect Today filters

  @SafetyManagement @Smoke @C3572334
  Scenario: Validate user is able to search participant by safety number in the added list of participants
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Activity Planner |
      | O&R | Safety Officer | Welcome2024! | Activity Planner |
    Then User selects the "Activity Planner" service from the left-side panel
    And User click on link "Activities Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates a new "Safety Training" activity
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User selects the crew whose "Reservation Status" is "Checked In"
    And User selects multiple participant records by clicking the checkboxes
    And User clicks the add selected button and save safety number
    Then User searches the participant by Safety Number

  @SafetyManagement @Smoke @C3572335
  Scenario: Validate user is able to search participant by crew ID in the added list of participants.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Activity Planner |
      | O&R | Safety Officer | Welcome2024! | Activity Planner |
    Then User selects the "Activity Planner" service from the left-side panel
    And User click on link "Activities Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates a new "Safety Training" activity
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User selects the crew whose "Reservation Status" is "Checked In"
    And User selects multiple participant records by clicking the checkboxes
    And User clicks the add selected button and save the crew ID
    Then User searches the participant by crew ID Number

  @SafetyManagement @Smoke @C2956618
  Scenario: Validate the user is able to view the assigned lifeboat to the guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User clicks on the "Guests Onboard" link
    And User switch to new tab
     #And User search guest "xxx"
    And User able to click "lastName" in the detail table
    And User open guest "Cruise Information" information details
    Then User navigates to the "Lifeboat" section and verifies the assigned "lifeboat"

  @SafetyManagement @Smoke @C2956619
  Scenario: Validate the user is able to view the assigned musterstation location to the guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User clicks on the "Guests Onboard" link
    And User switch to new tab
     #And User search guest "xxx"
    And User able to click "lastName" in the detail table
    And User open guest "Cruise Information" information details
    Then User navigates to the "Musterstaion" section and verifies the assigned "musterstaion"

  @SafetyManagement @Smoke @C1050315
  Scenario: Validate that user is able to see 'Past Assignments' of crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Safety Management |
      | O&R | Safety Officer | Welcome2024! | Safety Management |
    Then User selects the "Safety Management" service from the left-side panel
    And User click on link "Crew Onboard"
    And User switch to new tab
    #And User search guest "XXXCREWLAST01"
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open submenu "Assignments and Contract Information" details
    And User clicks on the "Past Assignments" section
    Then User view current "Past Assignments" details

  @SafetyManagement @Smoke @C3572333
  Scenario: Validate the participant reverts back after clicking the arrow mark button when selecting crew attendance
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Activity Planner |
      | O&R | Safety Officer | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User click on link "Activities Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates a new "Safety Training" activity
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User able to click "checkBox" in the detail table
    And User clicks on the "Add Selected" button in the Select Detail table
    And User click on the Remove all button
    And User clicks the Undo icon
    And User able to click "checkBox" in the detail table
    And User clicks on the "Add Selected" button in the Select Detail table
    And User click on the Remove all button
    Then User verifies the selected name is displayed in orange color
    And User click the revert button
    Then User verifies the selected guest name is displayed in the original color after clicking the Revert button

  @SafetyManagement @Smoke @C3572331
  Scenario: Validate user is able to bulk add/remove all participants while creating a safety training/drill activity.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Activity Planner |
      | O&R | Safety Officer | Welcome2024! | Activity Planner |
    Then User select the "Activity Planner" service from the left-side panel
    And User click on link "Activities Overview"
    And User switch to new tab
    And User able click on the Add Activity Button
    And User creates a new "Safety Training" activity
    Then User view next button
    And User click on NEXT button
    And User view on Skip button
    And User click on Skip
    And User selects all checkboxes and clicks the Select All button
    And User clicks the "Remove All" button in the right window
    Then User verifies the selected name is displayed in orange color

  @SafetyManagement @Smoke @C2208244
  Scenario: Validate that the user is able to export reports of Safety Card
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Reporting |
      | O&R | Safety Officer | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "Safety" report folder and selects it
    And User searches for the report title in the report and clicks the first matching title
    And User clicks on the "Export" button
    Then User views the Report Format pop-up box
    And User click on "Confirm" button
    And User verify that pdf is downloaded

  @SafetyManagement @Smoke @C2208247
  Scenario: Validate that the user is able to export report of Crew Ashore Denied List
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Safety Officer | Welcome2024! | Reporting |
      | O&R | Safety Officer | Welcome2024! | Reporting |
#      | NCL | HR Operations Manager | Welcome2024! | Reporting |
#      | O&R | Crew Purser           | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "546" in the report
    And User clicks the first "546" matching title
    And User enters date fields and clicks on the "Export" button
    Then User views the Report Format pop-up box
    And User click on "Confirm" button
    And User verify that pdf is downloaded