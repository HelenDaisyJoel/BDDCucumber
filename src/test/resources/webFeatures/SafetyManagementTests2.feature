Feature: Safety Management2

#  @SafetyManagement2 @Smoke @SM40 @C1202327
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
#    And User able to click "Mark as Absent"
#    Then User able to view the confirm pop up
#    And click the confirm Button
#
#  @SafetyManagement2 @Smoke @SM41 @C2956589
#  Scenario:Validate that user is able to identify certifications and training required for the safety assignment for crew
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    And Clicks on "Safety Management" module
#    Then User navigates to "Safety Management" page
#    Then "Safety Management" landing page should be displayed
#    Then User navigate to menu "Crew Services" service
#    And User click on "Crew Search" link under status
#    Then "Crew Members" landing page should be displayed
#    And User able to click "lastName" in the detail table
#    Then Respective crew member profile should be displayed
#    And User open submenu "Operations" details
#    Then "Operations" tile should be expanded
#    And User clicks on the "Training / Certifications" section
#    Then User is able to confirm training "Training / Certifications" data is display
#
#  @SafetyManagement2 @Smoke @SM42 @C2956615
#  Scenario:Validate the user is able to view 'Upcoming Drills / Training' of crew members.
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    And Clicks on "Safety Management" module
#    Then User navigates to "Safety Management" page
#    Then "Safety Management" landing page should be displayed
#    Then User navigate to menu "Crew Services" service
#    And User click on "Crew Search" link under status
#    Then "Crew Members" landing page should be displayed
#    And User able to click "lastName" in the detail table
#    Then Respective crew member profile should be displayed
#    And User open submenu "Safety" details
#    Then "Safety" tile should be expanded
#    And User clicks on the "Upcoming Drills / Training" section
#    Then User able "Upcoming Drills / Training" that the crew member is part of should be displayed
#
#  @SafetyManagement2 @Smoke @SM43 @C2956616
#  Scenario:Validate the user is able to view 'Attended Drills / Trainings' of crew members.
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Safety | Welcome2024! | Safety Management |
#    And Clicks on "Safety Management" module
#    Then User navigates to "Safety Management" page
#    Then "Safety Management" landing page should be displayed
#    Then User navigate to menu "Crew Services" service
#    And User click on "Crew Search" link under status
#    Then "Crew Members" landing page should be displayed
#    And User able to click "lastName" in the detail table
#    Then Respective crew member profile should be displayed
#    And User open submenu "Safety" details
#    Then "Safety" tile should be expanded
#    And User clicks on the "Attended Drills / Trainings" section
#    Then User able "Attended Drills / Trainings" that the crew member is part of should be displayed

  #@SafetyManagement2 @Smoke @SM44 @C2956594
  Scenario: Validate that user is able to view 'Emergency Role/Team' in safety buildup
    Given Login page is displayed
    When Input credentials to login into nclh
      | Safety | Welcome2024! | Safety Management |
    And Clicks on "Safety Management" module
    Then User navigates to "Safety Management" page
    Then "Safety Management" landing page should be displayed
    And User click on link "Safety Plan Build Up"
    Then User is able to navigate to the "Safety Plan Build-up" page link
    Then User is able to click on the hidden filters and verify they are displayed
    Then User is able to see and click on the Emergency role Team
    Then User is able to view Emergency Role Team in the Safety Build up dropdown list

  #@SafetyManagement2 @Smoke @SM45 @C2956608
  Scenario:Validate the user is able to mark participant as absent.
    When Input credentials to login into nclh
      | Safety | Welcome2024! | Safety Management |
    And Clicks on "Safety Management" module
    Then User navigates to "Safety Management" page
    And User click on link "Activity Overview"
    Then "Overview" page should be displayed
    Then User able to List of crew participants should be displayed
    And User clicks the Select All checkbox, then all participants should be selected
    And User able to click three dots and see "Mark as Absent"
    And User able to click "Mark as Absent"
    Then User able to view the confirm pop up
    And click the cancel Button
    And User able to click three dots and see "Mark as Absent"
    And User able to click "Mark as Absent"
    And click the confirm Button

  #@SafetyManagement2 @Smoke @SM46 @C1141444
    Scenario: Validate that user is able to Approve Absence Request for crew in safety training
    When Input credentials to login into nclh
      | Safety | Welcome2024! | Safety Management |
    And Clicks on "Safety Management" module
    Then User navigates to "Safety Management" page
    And User click on link "Activity Overview"
    Then "Overview" page should be displayed
    Then User able to List of crew participants should be displayed
    And User clicks the Select All checkbox, then all participants should be selected
    And User able to click three dots and see "Mark as Absent"
    And User able to click "Mark as Absent"
    Then User able to view the confirm pop up
    And click the cancel Button
    And User able to click three dots and see "Mark as Absent"
    And User able to click "Mark as Absent"
    And click the confirm Button

  #@SafetyManagement2 @Smoke @SM47 @C2956577
    Scenario: Validate that user is able to assign safety station assignments to crew by crew member
    When Input credentials to login into nclh
      | Safety | Welcome2024! | Safety Management |
    And Clicks on "Safety Management" module
    Then User navigates to "Safety Management" page
    And User click on link "Safety Plan Assignment"
    Then "Safety Assignment" page should be displayed
    And User click on "By Crew Member"
    Then User checks if the "Active Mode" screen is enabled on the crew member page
    And User select "Assigned" filter option to "No"
    And User able to click "checkBox" in the detail table
    Then User respective Safety Numbers landing page should be displayed
    Then User checks if the "Suggested Unassigned Numbers" screen is enabled on the crew member page
    And User selects the required number checkbox under Suggested Unassigned Numbers
    And Click o the ASSIGN button
    Then User able to view the confirm pop up
    And User click the confirm Button in crew page