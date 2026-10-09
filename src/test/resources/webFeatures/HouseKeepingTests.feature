Feature: House Keeping

  @HouseKeeping @Smoke @HS1 @C1051083
  Scenario Outline: Verify that the stateroom status icon is in green color for the guests that are onboarded
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User save guest name
    And User search for Guest and navigated to "<GuestFirstName>" profile page
    Then Verify that the stateroom status icon is in "green" color for the guests that are onboarded

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicGuestName |

  @HouseKeeping @Smoke @HS2 @C1051084 @C2956764
  Scenario Outline: Verify that the stateroom status icon is in orange color for the guests that are not onboarded
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "No"
    And User search guest with "xxx"
#    Then User save guest first name
#    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
 #   And User navigated to user "GuestUser" profile page
    And User save guest name
    #And User save  second guest name
    And User search for Guest and navigated to "<GuestFirstName>" profile page
    Then Verify that the stateroom status icon is in "orange" color for the guests that are onboarded

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicGuestName |

  @HouseKeeping @Smoke @HS3 @NotDependent @C1051046 @C1553868 @Sanity @C2956726
  Scenario Outline: Validate that the user is able to view the below details on the guest profile Language Preferences Birthday Decorations (Packages) Amenities Special Request Guest preferences(Special Needs)
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
#    Then User save guest first name
    And User search guest with "xxx"
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
#    And User navigated to "<GuestFirstName>" profile page
    Then Verify Guest user name
    Then Verify Guest user country and language
    Then verify Guest user Amenities list
    Then verify Guest user diningInfo List
    And User open submenu "Packages" details
    And User open submenu "Personal Information" details
    Then User click on eye opened if its closed
    And User check the Date of Birth
    And User should change actual "Preferred Language" and update to "Spanish"
    Then User save the country "Spanish"
    And User click on "Save"
    And User open submenu "Personal Information" details

    Then User navigates to the 'Special Needs' section
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @HouseKeeping @Smoke @HS4 @NotDependent @C1051045 @C1051044 @C4092844  @C2956725
  Scenario Outline:Validate that the user is able to export the guest manifest details (Guest Name, Age and Latitude (Loyalty Tier) in the otalio application.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "xxx"
    And User click on hide columns button
    And User drag "Age" and drop at the column header
    Then User verify "Age" column displayed in the header row
    And User click on hide columns button
    And User drag "Loyalty Number" and drop at the column header
    Then User verify "Loyalty Number" column displayed in the header row
    And User export ticket data
    And User verify that file is downloaded
    And User is able to view guest name
    And User is able to view loyalty Tire
    And User is able to view Guest Image

#    Then User verify visitor table
#      | Last Name | First Name | Cabin Code | Experience | Loyalty Tier | Table Number | Dining Seating | Reservation Status | Embarkation | Disembarkation | Book. Nr | Same Cabin | Travel with |


    Examples:
      | Service        | ServiceName   |
      | Guest Services | Guest Search  |

  @HouseKeeping @Smoke @HS5 @C1051047
  Scenario Outline:Validate that the user (House keeping super visior) is able to assign cabin service sections to the stewards from the 'By Section' view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By section
    And User change status of assign to "No"
    Then User click on unassigned first section
    And save the section name
    #And User open hidden filters
    #And user select the position filter
    #Then User select "Cabin Steward/ess" option in the filter
    Then click the crew for section
    And User save crew name allocated for section
    Then click on Assign button
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    And User verify assigned crew member
    Examples:
      | SectionName        |
      | DynamicSectionName |
  @HouseKeeping @Smoke @HS @NotDependent  @C2956724
  Scenario Outline: Validate that the user is able to view the guest manifest details are guest picture, name, age, latitude in the otalio application.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
#    Then User save guest first name
#    Then User save guest full name
    And User search guest with "xxx"
#    Then User save guest first name
#    Then User save guest full name
    And User click on hide columns button
    And User drag "Age" and drop at the column header
    Then User verify "Age" column displayed in the header row
    And User click on hide columns button
    And User drag "Loyalty Number" and drop at the column header
    Then User verify "Loyalty Number" column displayed in the header row
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User get the guest full name
    And User get guest age, gender
    And User save the loyalty ID of guest
    Then User verify the crew profile picture
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |


  @HouseKeeping @Smoke @HS6 @C1051048
  Scenario Outline:Validate that the user (House keeping supervisor) is able to unassign the assigned stewards to cabin service section from the 'By Section' view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By section
    And User change status of assign to "No"
    Then User click on unassigned first section
    And save the section name
    #And User open hidden filters
    #And user select the position filter
    #Then User select "CABIN STEWARD/ESS" option in the filter
    Then click the crew for section
    And User save crew name allocated for section
    And User click on "Assign"
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    Then click the  assigned crew for section
    And User click on "Unassign"
    And User change status of assign to "No"
    And user search for the "<SectionName>"
    And User search for crew "<CrewFirstName>"
    And User verify unassigned crew member
    Examples:
      | SectionName        | CrewFirstName        |
      | DynamicSectionName | DynamicCrewFirstName |

  @HouseKeeping @Smoke @HS7 @C1051049
  Scenario Outline:Validate that the user (House keeping supervisor) is able to assign public location service sections to the stewards from the 'By Section' view
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on By section
    And User change status of assign to "No"
    Then User click on unassigned first section
    And save the section name
    #And User open hidden filters
    #And user select the position filter
    #Then User select "CABIN STEWARD/ESS" option in the filter
    Then click the crew for section
    And User save crew name allocated for section
    Then click on Assign button
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    And User verify assigned crew member
    Examples:
      | SectionName        |
      | DynamicSectionName |

  @HouseKeeping @Smoke @HS8 @C1051050
  Scenario Outline:Validate that the user (House keeping supervisor) is able to unassign the assigned stewards to public location service sections from the 'By Section' view
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on By section
    And User change status of assign to "No"
    Then User click on unassigned first section
    And save the section name
    #And User open hidden filters
    #And user select the position filter
    #Then User select "CABIN STEWARD/ESS" option in the filter
    Then click the crew for section
    And User save crew name allocated for section
    And User click on "Assign"
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    Then click the  assigned crew for section
    And User click on "Unassign"
    And User change status of assign to "No"
    And user search for the "<SectionName>"
    And User search for crew "<CrewFirstName>"
    And User verify unassigned crew member
    Examples:
      | SectionName        | CrewFirstName        |
      | DynamicSectionName | DynamicCrewFirstName |

  @HouseKeeping @Smoke @HS9 @NotDependent @C1051051
  Scenario:Validate that 'By Section' view display the following section details Code Description Included Cabins Included Public Areas Assignees
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on By section
    Then User verify crew table
      | Code | Description | Included Cabins | Included Public Areas | Assignee(s) |

  @HouseKeeping @Smoke @HS10 @NotDependent @C1051053 @C1051062
  Scenario Outline:Validate that 'By section' view displays the following filters on click of any section:1. Assigned crew members2. Unassigned crew members
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on By "<SectionOrCrewNumber>"
    And User change status of assign to "Yes"
    Then User click on unassigned first section
    Then User verify the "<Section>" section
    And User open hidden filters
    And user verify the "Position:" filter
    And user verify the "Department:" filter
    Then User verify the "<Assigned>" section

    Examples:
      | SectionOrCrewNumber | Section                 | Assigned              |
      | by-section          | Unassigned Crew Members | Assigned Crew Members |
      #| by_crew-button      | Unassigned Sections     | Assigned Sections     |

  @HouseKeeping @Smoke @HS11 @NotDependent @C1051054
  Scenario Outline:Validate that user can view unassigned crew members of a selected section
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    And User change status of assign to "Yes"
    Then User click on assigned first crew member
    And save the first crew name
    Then click on unassigned section
    And User save section name allocated for crew
    And User click on By section
    And User change status of assign to "No"
    And user search for the "<SectionName>"
    Then click on unassigned crew members
    And User verify unassigned crew member for section

    Examples:
      | SectionName        |
      | DynamicSectionName |


  @HouseKeeping @Smoke @HS12 @NotDependent @C1051055
  Scenario Outline:Validate that clicking on the 'Assigned Crew Members' tab displays the assigned crew members of the selected section.
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    And User change status of assign to "Yes"
    Then User click on assigned first crew member
    And save the first crew name
    Then click on assigned section
    And User save section name allocated for crew
    And User click on By section
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    And User verify assigned crew member for section

    Examples:
      | SectionName        |
      | DynamicSectionName |

  @HouseKeeping @Smoke @HS13 @NotDependent @C1051052
  Scenario Outline:Validate that the user (Housekeeping super visior) is able to export the section- assignments list from the 'By Section' view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By section
    And User export ticket data
    And User verify that file is downloaded
    Examples:
      | SectionName        |
      | DynamicSectionName |

  @HouseKeeping @Smoke @HS14 @NotDependent @C1051057 @C1051068 @C1051056
  Scenario Outline:Validate that the user (House keeping supervisior) is able to unassign the assigned cabin service sections to the steward from the 'By Crew Member' view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    #And user select the position filter
    #Then User select "Cabin Steward/ess" option in the filter(data is not there present)
    And User change status of assign to "No"
    Then User click on assigned first crew member
    And save the first crew name
    Then click the crew for section
    Then click on unassigned section
    And User save section allocated for crew
    And User click on "Assign"
    And User click on By section
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    Then click the  assigned crew for section
    And User click on "Unassign"
    And User change status of assign to "No"
    And user search for the "<SectionName>"
    And User search  crew "CrewFirstName"
    And User Verify unassigned crew
    Examples:
      | SectionName        |
      | DynamicSectionName |

  @HouseKeeping @Smoke @HS15 @NotDependent @C1051059 @C1051058 @C2956739
  Scenario Outline:Validate that the user (House keeping supervisior) is able to unassign the assigned public location sections to the steward from the 'By Crew Member' view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on By crew member
    #And user select the position filter
    #Then User select "Cabin Steward/ess" option in the filter(data is not there present)
    And User change status of assign to "No"
    Then User click on assigned first crew member
    And save the first crew name
    Then click the crew for section
    Then click on unassigned section
    And User save section allocated for crew
    And User click on "Assign"
    And User click on By section
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    Then click the  assigned crew for section
    And User click on "Unassign"
    And User change status of assign to "No"
    And user search for the "<SectionName>"
    And User search  crew "CrewFirstName"
    And User verify unassigned crewMember

    Examples:
      | SectionName        |
      | DynamicSectionName |

  @HouseKeeping @Smoke @HS16 @NotDependent @C1051063
  Scenario Outline:Validate that clicking on the 'Assigned Sections' tab displays the assigned sections to the selected crew member
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    And User change status of assign to "Yes"
    Then User click on assigned first crew member
    And save the first crew name
    Then click on assigned section
    And User save section name allocated for crew
    And User click on By section
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    And User verify assigned crew member for section
    And User click on public location
    And User click on By crew member
    Then User click on assigned first crew member
    Examples:
      | SectionName        |
      | DynamicSectionName |

  @HouseKeeping @Smoke @HS17 @NotDependent  @C1051071
  Scenario:Validate that user can view all created sections in 'By Crew Member' view
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    And User change status of assign to "Yes"
    And User verify that data is visible
    And User click on public location
    And User click on By crew member
    And User change status of assign to "Yes"
    And User verify that data is visible

  @HouseKeeping @Smoke @HS18 @NotDependent  @C1051070
  Scenario:Validate that user can view all created sections in 'By Crew Member' view
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By section
    And User verify that data is visible
    And User click on public location
    And User click on By section
    And User verify that data is visible

  @HouseKeeping @Smoke @HS19 @NotDependent @C1051072
  Scenario Outline:Validate that user can assign multiple sections to one crew member
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    And User change status of assign to "Yes"
    Then User click on assigned first crew member
    And save the first crew name
    Then User click on two Section
    And save the section text
    And User click on "Assign"
    And User change status of assign to "Yes"
    And user search for  "<CrewName>"
    Then click on assigned section
    Then verify the user is able to assign multiples sections to the crew member under Cabin service

    Examples:
      | CrewName        |
      | DynamicCrewName |

  @HouseKeeping @Smoke @NotDependent @C1051073
  Scenario Outline:Validate that user (Housekeeping Manager) is able to assign all the crew members to multiple sections under Public Location Service.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on public location
    And User click on By crew member
    And User change status of assign to "Yes"
    Then User click on assigned first crew member
    And save the first crew name
    Then User click on two Section
    And save the section text
    And User click on "Assign"
    And User change status of assign to "Yes"
    And user search for  "<CrewName>"
    Then click on assigned section
    Then verify the user is able to assign multiples sections to the crew member under Cabin service
    And User click on By section
    Then User click on unassigned first section
    And save the section name
    Then click multiple crew for section
    And User save crew names allocated for section
    And User click on "Assign"
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    Then verify the user is able to assign multiples  crew member  to the sections under Public Location Service

    Examples:--
      | CrewName        |  | SectionName        |
      | DynamicCrewName |  | DynamicSectionName |

  @HouseKeeping @Smoke @HS21 @NotDependent  @pending
  Scenario Outline:Validate that user (Housekeeping Manager) is able to assign all the crew members to multiple sections under Public Location Service.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User click on report overview
    And User open module "<ModuleName>" in reporting overview
    Examples:
      | Service     | ModuleName |
      | Reporting   | Guest Services|

  @HouseKeeping @Smoke @HS22 @NotDependent @C1051064
  Scenario Outline:Validate that user (Housekeeping Manager) is able to assign the stewards to the public location service.
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on public location
    And User click on By crew member
    And User change status of assign to "Yes"
    Then User click on assigned first crew member
    And save the first crew name
    Then click on unassigned section
    And User save section name allocated for crew
    And User click on "Assign"
    And User click on By section
    And User change status of assign to "No"
    Then User click on unassigned first section
    And save the section name
    And User open hidden filters
    And user select the position filter
    #Then User select "Cabin Steward/ess" option in the filter
    Then click the crew for section
    And User save crew name allocated for section
    And User click on "Assign"
    Examples:
      | SectionName        |
      | DynamicSectionName |

  @HouseKeeping @Smoke @HS23 @NotDependent @C1051066 @C1051065
  Scenario Outline:Validate that user (Housekeeping Manager) is able to reassign the stewards to the public location service.
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on public location
    And User click on By crew member
    And User change status of assign to "No"
    Then User click on assigned first crew member
    And save the first crew name
    Then click on unassigned section
    And User save section name allocated for crew
    Then click the  assigned  section
    Then click on Assign button
    And User change status of assign to "Yes"
    And user search for  "<CrewName>"
    Then click on assigned section
    And User save section name allocated for crew
    And save the section name
    Then click the  assigned  section
    And User click on "Unassign"
    And User change status of assign to "No"
    And user search for  "<CrewName>"
    Then click on unassigned section
    And User save section name allocated for crew
#    And User verify that reassign of section is done
    #Then click on Assign button

    Examples:
      | SectionName        |  | CrewName        |
      | DynamicSectionName |  | DynamicCrewName |

  @HouseKeeping @Smoke @HS24 @NotDependent @C1051060
  Scenario:Validate that 'By Crew Member' view display the crew member details Crew Name Operational Department Operational Position Embarkation Date Debarkation Date Section
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on public location
    And User click on By crew member
    And User change status of assign to "Yes"
    Then User verify crew table
      | Crew Name | Operational Department | Operational Position | Embarkation Date | Disembarkation Date|Section(s)|

  @HouseKeeping @Smoke @HS25 @NotDependent @C1051067
  Scenario Outline:Validate that user (Housekeeping Manager) is able to assign the stewards to the cabin service.
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    And User change status of assign to "Yes"
    Then User click on assigned first crew member
    And save the first crew name
    Then click on unassigned section
    And User save section name allocated for crew
    And User click on "Assign"
    And User click on By section
    And User change status of assign to "No"
    Then User click on unassigned first section
    And save the section name
    #And User open hidden filters
    #And user select the position filter
    #Then User select "Cabin Steward/ess" option in the filter
    Then click the crew for section
    And User save crew name allocated for section
    And User click on "Assign"
    Examples:
      | SectionName        |
      | DynamicSectionName |

  @HouseKeeping @Smoke @HS26 @NotDependent @C1051069
  Scenario Outline:Validate that user (Housekeeping Manager) is able to reassign the stewards to the cabin service.
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    And User change status of assign to "No"
    Then User click on assigned first crew member
    And save the first crew name
    Then click on unassigned section
    And User save section name allocated for crew
    Then click the  assigned  section
    Then click on Assign button
    And User change status of assign to "Yes"
    And user search for  "<CrewName>"
    Then click on assigned section
    And User save section name allocated for crew
    And save the section name
    Then click the  assigned  section
    And User click on "Unassign"
    And User change status of assign to "No"
    And user search for  "<CrewName>"
    Then click on unassigned section
    And User save section name allocated for crew
    Then click the  assigned  section
    Then click on Assign button
    And User change status of assign to "Yes"
    And user search for  "<CrewName>"
    Then click on assigned section
    And User save section name allocated for crew
    And save the section name
    Then click the  assigned  section
    And User click on "Unassign"
    Examples:
      | SectionName        |  | CrewName        |
      | DynamicSectionName |  | DynamicCrewName |

  @HouseKeeping @Smoke @HS27 @C1051061
  Scenario:Validate that the user (Housekeeping super visior) is able to export the section- assignments list from the 'By Section' view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    And User change status of assign to "Yes"
    And User export ticket data
    And User verify that file is downloaded

  @HouseKeeping @Smoke @HS28
  Scenario Outline:Validate that the user (House keeping supervisor) is able to unassign the assigned stewards to cabin service section from the 'By Section' view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Examples:
      | Service      | ServiceName  |  |
      | Housekeeping | Guest Search |  |

  @HouseKeeping @Smoke @C2956767
  Scenario Outline:Validate that the cabin status is automatically updated to "completed" after the assigned task is finished.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    #And User open service "<ServiceName>" item
    And User click on " Cleaning Locations "
    And User select "Cleaning Status" filter as "Vacant Dirty"

    Examples:
      | Service      | ServiceName  |  |
      | Housekeeping | Housekeeping |  |

  @HouseKeeping @HS29 @Smoke @C2956765
  Scenario Outline:Validate that user is able to track the below status of the task
  1. Completed
  2. Inprogress
  3. DND
  4. Not required
  5. Inspected
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on status filter
    And Verify the Status list

    Examples:
      | Service      | ServiceName   |
      | Housekeeping | Task Overview |

  @HouseKeeping @Smoke @HS30 @NotDependent @C2956731
  Scenario:Validate that 'By Section' view display the following section details 1. Code 2. Description 3. Included Cabins 4. Included Public Areas 5. Assignees
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "Housekeeping" service
    Then User click Fast Section Assignment
    And User click on public location
    And User click on By section
    And User change status of assign to "Yes"
    Then User verify crew table
      | Code | Description | Included Cabins | Included Public Areas | Assignee(s) |

  @HouseKeeping @Smoke @HS31 @NotDependent  @C2956733
  Scenario Outline:Validate that 'By section' view displays the following filters on click of any section:1. Assigned crew members2. Unassigned crew members
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "Housekeeping" service
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By section
#    And User change status of assign to "Yes"
    Then User click on unassigned first section
    Then User verify the "<Section>" section
    And User open hidden filters
    And user verify the "Position:" filter
    And user verify the "Department:" filter
    Then click on assigned crew members
    And User click on public location
    And User click on By section
#    And User change status of assign to "Yes"
    Then User click on unassigned first section
    Then User verify the "<Section>" section
    Then click on assigned crew members
    Then User verify the "<Assigned>" section

    Examples:
      | SectionOrCrewNumber | Section                 | Assigned              |
      | by-section          | Unassigned Crew Members | Assigned Crew Members |
      #| by_crew-button      | Unassigned Sections     | Assigned Sections     |

  @HouseKeeping @Smoke @HS32 @NotDependent  @C2956742
  Scenario Outline:Validate that the 'By Crew Member' view displays the following filters on the click of any crew member: 1.Assigned sections 2.Unassgined sections
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "Housekeeping" service
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
#    And User change status of assign to "No"
    Then User click on assigned first crew member
    Then User verify the "<Section>" section
    Then click on assigned section
    And User click on public location
    And User click on By crew member
    Then User click on assigned first crew member
#    And User change status of assign to "Yes"
    Then User verify the "<Section>" section
    Then click on assigned section
    Then User verify the "<Assigned>" section

    Examples:
      | SectionOrCrewNumber | Section                 | Assigned              |
      | by-section          | Unassigned Sections | Assigned Sections |

  @HouseKeeping @Smoke @HS15 @NotDependent @C2956739
  Scenario Outline:Validate that the user (House keeping supervisior) is able to unassign the assigned public location sections to the steward from the 'By Crew Member' view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "Housekeeping" service
    Then User click Fast Section Assignment
    And User click on Cabin Service
    And User click on By crew member
    #And user select the position filter
    #Then User select "Cabin Steward/ess" option in the filter(data is not there present)
    And User change status of assign to "No"
    And User search guest with "xxx"
    Then User click on assigned first crew member
    And save the first crew name
    Then click the crew for section
    Then click on unassigned section
    And User save section allocated for crew
    And User click on "Assign"
    And User click on By section
    And User change status of assign to "Yes"
    And user search for the "<SectionName>"
    Then click on assigned crew members
    Then click the  assigned crew for section
    And User click on "Unassign"
    And User change status of assign to "No"
    And user search for the "<SectionName>"
    And User search  crew "CrewFirstName"
    And User verify unassigned crewMember

    Examples:
      | SectionName        |
      | DynamicSectionName |

  @HouseKeeping @Smoke @HS33 @NotDependent @C1051082
  Scenario Outline:Validate that user can view guest stateroom occupancy status in guest profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User get the cabin number and occupancy status

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @HouseKeeping @Smoke @HS34 @NotDependent @C1051074
  Scenario Outline: Validate that user is able to view service requests report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Guest Services"
    And User click on "Maintenance and Service Requests Report" code link
    Then User click on "Preview" button in reporting

    Examples:
      | Service   |
      | Reporting |

  @HouseKeeping @Smoke @HS35 @NotDependent @C1051075
  Scenario Outline: Validate that user is able to print service requests report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Guest Services"
    And User click on "Maintenance and Service Requests Report" code link
    Then User click on "Preview" button in reporting
    Then User click on "Print"
    And User switch to pdf page and get pdf content
    Then User verify content present in the PDF content

    Examples:
      | Service   |
      | Reporting |

  @HouseKeeping @Smoke @HS36 @NotDependent @C1051076
  Scenario Outline: Validate that user is able to export service requests report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Guest Services"
    And User click on "Maintenance and Service Requests Report" code link
    Then User click on "Preview" button in reporting
    Then User click on "Export"

    Examples:
      | Service   |
      | Reporting |

  @HouseKeeping @Smoke @HS37 @NotDependent @C1051078
  Scenario Outline: Validate that user is able to view special requests report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Guest Services"
    And User click on "Guest Special Requests - ALL" code link
    Then User click on "Preview" button in reporting

    Examples:
      | Service   |
      | Reporting |

  @HouseKeeping @Smoke @HS38 @NotDependent @C1051079
  Scenario Outline: Validate that user is able to print special requests report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Guest Services"
    And User click on "Guest Special Requests - ALL" code link
    Then User click on "Preview" button in reporting
    Then User click on "Print"
    And User switch to pdf page and get pdf content
    Then User verify content present in the PDF content

    Examples:
      | Service   |
      | Reporting |

  @HouseKeeping @Smoke @HS39 @NotDependent @C1051080
  Scenario Outline: Validate that user is able to export special requests report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL |  Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Guest Services"
    And User click on "Guest Special Requests - ALL" code link
    Then User click on "Preview" button in reporting
    Then User click on "Export"

    Examples:
      | Service   |
      | Reporting |






