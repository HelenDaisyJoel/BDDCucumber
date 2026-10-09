Feature: Ship Security

  @ShipSecurity @Smoke @C1050669
    Scenario: Validate that user is able to move a checked in onboard guest (To Ashore) through the gangway.
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User apply filter "Checked In"
    And User select a guest name
    And User click on "Select"
    Then Verify user status "Ashore"

  @ShipSecurity @Smoke @C1050670
    Scenario: Validate that user is able to move a checked in Ashore guest (To Onboard) through the gangway.
    Given A new guest user is created "Shoreside"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User apply filter "Checked In"
    And User select a guest name
    And User click on "Select"
    Then Verify user status "Onboard"

  @ShipSecurity @Smoke @C1050671
  Scenario: Validate that user is able to move a Other status Onboard guest (To Ashore) through the gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User navigate to menu "Ship Security" service
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    And User save "Other Status Onboard" guests count on gangway
    Then User open "Guests" member for "Other Status Onboard"
    And User changed to AShoreOrOnBoard "Ashore"
    And User click on "Change To Ashore"
    Then Verify user status "Ashore"

  @ShipSecurity @Smoke @C1050674
  Scenario: Validate that user is able to move a disembarking guest through the gangway
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User apply filter "Leaving Today"
    And User search for the Disembarkation guest
    And User click on "Select"
    Then Verify user status "Ashore"

  @ShipSecurity @Smoke @C1050676
  Scenario: Validate that user is able to move checked In onboard crew member through the gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And Click on "Crew" icon at top right pane
    And User apply filter "Checked In"
    And User search for the Crew on Board guest
    And User click on "Select"
    Then Verify user status "Ashore"

  @ShipSecurity @Smoke @C1050677
    Scenario: Validate that the Otalio application is able to display warning notifications for the 'Boarding Denied' guest at the gangway movements.
    #Given User marks the guest "GuestOnboard2" as "BOARDING DENIED" using "add-boarding-denied"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User search for the Boarding denied guest way
    And User click on "Select"
    Then User verify error message on Gangway mode with "Boarding denied"
      #----Removing-------
    Then User unmarks the "GuestOnboard2" guest as "BOARDING DENIED" using "add-boarding-denied"

  @ShipSecurity @Smoke @C1050678
  Scenario: Validate that the Otalio application is able to display warning notifications for the 'Ashore Denied' guest at the gangway movements.
    #Given User marks the guest "GuestOnboard3" as "ASHORE DENIED" using "add-ashore-denied"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User search for the Ashore Denied guest
    And User click on "Select"
    Then User verify error message on Gangway mode with "Movement ashore denied"
  #----Removing-------
    #Then User unmarks the "GuestOnboard3" guest as "ASHORE DENIED" using "add-ashore-denied"

  @ShipSecurity @Smoke @C1050679
  Scenario: Validate that user is able to see the Ashore guest list (Guest service)
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item card
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "No"
    Then User verify that users having stateroom status icon is in "orange" color for the guests or crews

  @ShipSecurity @Smoke @C1050680
    Scenario: Validate that user is able to see the on board guest list(Guest service).
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item card
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    Then User verify that users having stateroom status icon is in "green" color for the guests or crews

  @ShipSecurity @Smoke @C1050681
    Scenario: Validate that user is able to see the all the guests list (Guest service).
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item card
    Then User check for all the list of guests

  @ShipSecurity @Smoke @C1050682
    Scenario: Validate that user is able to see the Ashore crew list (Crew Service).
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item card
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User select "Onboard" filter option to "No"
    Then User verify that users having stateroom status icon is in "orange" color for the guests or crews

  @ShipSecurity @Smoke @C1050683
  Scenario: Validate that user is able to see the On board crew list(Crew service).
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item card
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User select "Onboard" filter option to "Yes"
    Then User verify that users having stateroom status icon is in "green" color for the guests or crews

  @ShipSecurity @Smoke @C1050684
    Scenario: Validate that user is able to see the all the Crew members list (Crew service).
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item card
    Then User check for all the list of guests

  @ShipSecurity @Smoke @C1050685
    Scenario: Validate that user is able to see the Ashore visitor list.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User click on link "Visitor Overview"
    Then User switch to new tab
    And User select "Board Status" filter option to "No"
    Then User verify user data present on the page else "No Data" should present on the page

  @ShipSecurity @Smoke @C1050686
    Scenario: Validate that user is able to see the on board visitor list.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User click on link "Visitor Overview"
    Then User switch to new tab
    And User select "Board Status" filter option to "Yes"
    Then User verify user data present on the page else "No Data" should present on the page

  @ShipSecurity @Smoke @C1050687
  Scenario: Validate that user is able to see the all the visitors list.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User click on link "Visitor Overview"
    Then User switch to new tab
    Then User verify user data present on the page else "No Data" should present on the page

  @ShipSecurity @Smoke @C1050688
  Scenario: Validate that the user is able to track the required person (Guest or crew member or Visitor) gangway logs
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Gangway Logs" module
    And User navigated to module "Gangway Logs"
    And User add "Account" filter to guest service page
    And User clear the date range
    And User selects account types "Guest"
    Then User capture details of the user selected "log"
    Then User navigated to gangway page
    And User clear the date range
    And User selects account types "Crew"
    Then User capture details of the user selected "log"
    Then User navigated to gangway page
    And User clear the date range
    And User selects account types "Visitor"
    Then User capture details of the user selected "log"
    Then User navigated to gangway page
    And User clear the date range
    And User selects account types Guest, Crew and Visitor
    Then User capture details of the user selected "log"

  @ShipSecurity @Smoke @C1050689
    Scenario: Validate that the user is able to see details of the guest or crew or visitor in the gangway logs - date & time, location, name, cabin number, action type, method, account type, visitor type, gangway mode, workstation, user, port of call.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Gangway Logs" module
    And User navigated to module "Gangway Logs"
    And User selects filters "Account,Age,Cruise,Deny Reason,Details,Message,Override,Override User,Port of Call,Visitor Type,Workstation"
    Then User capture details of the user selected "log"

  @ShipSecurity @Smoke @C1050690
    Scenario: Validate that the Otalio application is able to display the guest reservation details with a guest name search.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User apply filter "Checked In"
    And User search for the Guest reservation
    And User click on "Select"
    Then User able View Guest reservation details

  @ShipSecurity @Smoke @C1050691
    Scenario: Validate that the Otalio application is able to display the guest reservation details with a guest cabin search.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User search for the "9120" guest

  @ShipSecurity @Smoke @C1050692
  Scenario: Validate that the Otalio application is able to display the count of other travelers who have the same booking number as the searched guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User able count of other travelers same booking "37651681" number
    Then User view the "Same Booking Number"

  @ShipSecurity @Smoke @C1050693
  Scenario: Validate that the Otalio application is able to display the count of other travelers who have the same cabin number as the searched guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User able count of other travelers same booking "37651681" number
    Then User view the "Same Cabin"

  @ShipSecurity @Smoke @C1050694
  Scenario: Validate that the Otalio application is able to display the count of other travelers who are 'Travel with' as the searched guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User able count of other travelers same booking "37651681" number
    Then User view the "Same Travel With"

  @ShipSecurity @Smoke @C1050701
    Scenario: Validate that the user is able to see onboard guest count on the gangway dashboard
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    And validate "Checked In Onboard" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box
    And validate "Other Status Onboard" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

  @ShipSecurity @Smoke @C1050702
  Scenario: Validate that the user is able to see excursion counts by tour on the gangway dashboard
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    And validate "On Tour" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

  @ShipSecurity @Smoke @C1050703
    Scenario: Validate that the user is able to see Scheduled drills and trainings details on the gangway dashboard
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    And User clicks the day having activity data
    And User verify any scheduled trainings present on gangway

  @ShipSecurity @Smoke @C1050704
  Scenario: Validate that the user is able to see Individual status counts and overall persons counts of (guests, crew and visitors) on the gangway dashboard
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
      | NCLH | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    #-------------------Guests----------------------------
    And validate "Checked In Onboard" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Checked In Ashore" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Other Status Onboard" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Expected" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Embarked" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarking" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarked" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    #-------------------Crew--------------------------------
    And validate "Checked In Onboard" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Checked In Ashore" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Other Status Onboard" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Expected" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Embarked" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarking" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarked" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box
      #-------------------Visitors--------------------------------

    And validate "Checked In Onboard" and "Visitors" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Other Status Onboard" and "Visitors" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Expected" and "Visitors" count
    Then User checking the dashboard count
    And User close the dashboard box

        #-------------------Total--------------------------------
    And validate "Checked In Onboard" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Checked In Ashore" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Other Status Onboard" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Expected" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Embarked" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarking" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarked" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box


  @ShipSecurity @Smoke @C1050705
    Scenario: Validate that user is able to see the checked in Ashore, expected, embarked, disembarking, disembarked guests count on the gangway dashboard.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
#-------------------Guests----------------------------
    And validate "Expected" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Embarked" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarking" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

  @ShipSecurity @Smoke @C1050710
  Scenario:Validate that user is able to see the Scheduled for Overnight - Onboard/Ashore guests count on the gangway dashboard
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
#-------------------Guests----------------------------
    And validate "Scheduled for Overnight - Onboard" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Scheduled for Overnight - Ashore" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Overnight - Ashore" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

  @ShipSecurity @Smoke @C1050713
  Scenario: Validate that user is able to identify the guest or crew status through the status icon on the profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Guest Services |
      | O&R | Security Guard | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item card
    #-------------------Guests----------------------------
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    Then User sets the "Onboard" filter to "Yes"
    Then User verifies board status "Checked In / On Board"
    And User navigates to the previous page
    Then User sets the "Onboard" filter to "No"
    Then User verifies board status "Checked In / Shoreside"

#-------------------Crew----------------------------
    Then User navigate to menu "Crew Services" service
    And User open service "Crew Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User select "Onboard" filter option to "Yes" for crew
    Then User verifies board status "Checked In / On Board"
    And User navigate to Crew Services page
    And User select "Onboard" filter option to "No" for crew
    Then User verifies board status "Checked In / Shoreside"

  @ShipSecurity @Smoke @C1050718
  Scenario: Validate that the user is able to view all the visitors.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Visitors Management" module
    And User navigates to "Visitors Management" link
    Then User capture details of the user selected "log"

  @ShipSecurity @Smoke @C1050719
  Scenario: Validate that the user is able to see/filter the following visitor details - first name, last name, board status, visit purpose, visiting party name, Valid from, Valid to, company.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
      | NCLH | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Visitors Management" module
    And User navigates to "Visitors Management" link
    Then User verify visitor table
      | First Name | Last Name | On Board Status | Visit Purpose | Visiting Party Name | Valid From | Valid To | Company |
    Then User capture details of the user selected "log"

  @ShipSecurity @Smoke @C1050726
    Scenario: Validate that Otalio application is able to flag a minor guest with guardian details (' Guardian of 'guests)
    #Given User verifies the "Guest minor" details
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User search for the Minor guest guest
    And User click on "Select"
    Then User verify error message on Gangway mode with "Minor not allowed OUT without a Guardian"

  @ShipSecurity @Smoke @C1050729
  Scenario: Validate that the otalio application is able to display a flag to the guest with alcohol consumption.
  #Scenario: Validate that user is able to flag a guest with alcohol consumption @C2968007
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item card
    And User search guest with Alcohol consumption
    Then User save guest first name
    Then User save guest full name
    #And User save guest firstName excluding sensitive cabins
    And User able to click "lastName" in the detail table
    And User open guest "Cruise Information" information details
    And User enables qualified for checkIn for guest
    And User enables "intoxicated" for guest
    Then User verify the "INTOX" warning to guest profile
    And User disable "intoxicated" for guest

  @ShipSecurity @Smoke @C1050730
  Scenario: Validate that the otalio application is able to display a flag to the crew member with alcohol consumption
  #Scenario: Validate if Shipside Crew can enable/disable the guest intoxication flag for a crew member #C1430008
  #Scenario: Validate that user is able to flag a crew member with alcohol consumption #C2968008
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User search crew member with alcohol consumption
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open submenu "Personal Information" details
    And User click "Additional Info" on Personal Information
    And User accept pop up
    Then User verify "Intoxicated" toggle button in "true" mode
    Then User click on "Save"
    Then User verify the "INTOX" warning to guest profile
      #----Removing-------
    Then User verify "Intoxicated" toggle button in "false" mode for crew

  @ShipSecurity @Smoke @C1050731
  Scenario: Validate that the otalio application is able to display a flag to the guest with deny boarding.
  #Scenario: Validate that the user is able to flag a guest with Deny boarding. @C2968009
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User search for the Boarding denied guest
    Then User save guest first name
    Then User save guest full name
    #And User save guest firstName excluding sensitive cabins
    And User able to click "lastName" in the detail table
    And User open guest "Lost & Found, Security Incidents" information details
    And User "enable" toggle for "add-boarding-denied" to guest
    Then User verify the "BOARDING DENIED" warning to guest profile
      #----Removing-------
    Then User "disable" the "add-boarding-denied" toggle and verifies that the "BOARDING DENIED" warning is not tagged on the guest profile and views the profile

  @ShipSecurity @Smoke @C1050732
    Scenario: Validate that the otalio application is able to display a flag to the guest with Ashore Denied.
    #Scenario: Validate that the user is able to flag a guest with Ashore Denied. @C2968010
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User search for Ashore denied guest
    Then User save guest first name
    Then User save guest full name
    #And User save guest firstName excluding sensitive cabins
    And User able to click "lastName" in the detail table
    And User open guest "Lost & Found, Security Incidents" information details
    And User "enable" toggle for "add-ashore-denied" to guest
    Then User verify the "ASHORE DENIED" warning to guest profile
      #----Removing-------
    Then User "disable" the "add-boarding-denied" toggle and verifies that the "ASHORE DENIED" warning is not tagged on the guest profile and views the profile

  @ShipSecurity @Smoke @C1050735
    Scenario: Validate that the user is able to flag a required guest as missing
    #Scenario: Validate that user is able to flag a required guest ( children/parents ) as missing. @C2968012
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item card
    And User search guest Missing flag
    Then User save guest first name
    Then User save guest full name
    #And User save guest firstName excluding sensitive cabins
    And User able to click "lastName" in the detail table
    And User open link "quick-links"
    And User views the "Report as missing" button
    And User navigate to "Report as missing"

    And User set missing date and time
    Then User select the Reported person
    And User views the "Select" button
    And User click on "Select"
    And User views the "Report as missing" button
    And User click the "Report as missing" button
    And User confirms the signature
    And User views the "Sign & confirm" button
    And User click on "Sign & confirm"
    Then User can see "Missing" flag on guest profile

  @ShipSecurity @Smoke @C1050734
  Scenario: Validate that the otalio application is able to display a missing flag for a required guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item card
    And User search guest Missing guest found
    Then User save guest first name
    Then User save guest full name
    #And User save guest firstName excluding sensitive cabins
    And User able to click "lastName" in the detail table
    Then User can see "Missing" flag on guest profile
      #----Removing-------
    And User remove the missing flag from the guest profile

  @ShipSecurity @Smoke @C1050736
  Scenario: Validate that user is able to flag a required crew as missing.
  #Scenario: Validate that the Otalio application is able to display'missing' on the crew profile pic who has been flagged as missing.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User search crew as missing
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open link "quick-links"
    And User views the "Report as missing" button
    And User navigate to "Report as missing"

    And User set missing date and time
    Then User select the Reported person
    And User views the "Select" button
    And User click on "Select"
    And User views the "Report as missing" button
    And User click the "Report as missing" button
    And User confirm and perform signature
    And User views the "Sign & confirm" button
    And User click on "Sign & confirm"
    Then User can see "Missing" flag on guest profile
        #----Removing-------
    #And User remove the missing flag from the guest profile

  @ShipSecurity @Smoke @C1050741
    Scenario: Validate that the otalio application is able to display a missing flag for a minor guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User search Missing Minor guest
    Then User save guest first name
    Then User save guest full name
     #And User save guest firstName excluding sensitive cabins
    And User able to click "lastName" in the detail table
    And User marks the Missing flag from the crew profile
    Then User can see "Missing" flag on guest profile
      #----Removing-------
    And User remove the missing flag from the guest profile

  @ShipSecurity @Smoke @C1050748
  Scenario: Validate that the user is able to set the tender capacity.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    And User set the tender capacity "2" number
    Then User view the tender capacity count
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User view the tender capacity number

  @ShipSecurity @Smoke @C1050749
  Scenario: Validate that the otalio application is able to alert when tender capacity has reached its maximum limit.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    And User set the tender capacity "2" number
    Then User view the tender capacity count
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    And User click on search
    And User apply filter "Checked In"
    And User search for the Tender Capacity first guest
    And User click on "Select"
    And User click on search
    And User search for the Tender Capacity second guest
    And User click on "Select"
    And User click on search
    And User search for the Tender Capacity third guest
    And User click on "Select"
    Then User verify error message on Gangway mode with "Tender maximum capacity has been reached please Reset and scan/swipe again."
    Then User view the tender capacity number
    And User click increase authority button 2 times
    And User click decrease authority button 1 times
    Then User should see the error popup

  @ShipSecurity @Smoke @C1050750
  Scenario: Validate that the user is able to reset the maximum tender capacity.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    And User set the tender capacity "2" number
    Then User view the tender capacity count
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User view the tender capacity number
    And User click on reset button
    And User enter the tender capacity count "1"
    And User click on "Confirm"
    Then User view the reset tender capacity limit changed

  @ShipSecurity @Smoke @C1050752
  Scenario: Validate that the otalio application is able to display a warning message for the missing guest at the gangway movement.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User search for the missing guest at the gangway movement
    And User click on "Select"
    Then User verify error message on Gangway mode with "Guest Person was reported missing"

  @ShipSecurity @Smoke @C1050751
  Scenario: Validate that the user is able to update the missing guest status to found at the gangway movement
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User search for the missing guest at the gangway movement
    And User click on "Select"
    Then User verify error message on Gangway mode with "Guest Person was reported missing"
    And User click on "Yes"
    Then Verify user status "Ashore"

  @ShipSecurity @Smoke @C1050780
    Scenario: Validate that the Otalio application is able to display a Denied boarding flag to the crew member.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item card
    And User search for Denied boarding guest
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    And User open crew "Personal Information" information details
    And User open guest "Lost & Found, Security Incidents" information details
    And User "enable" toggle for "add-boarding-denied" to guest
    Then User verify the "BOARDING DENIED" warning to guest profile
      #----Removing-------
    Then User "disable" the "add-boarding-denied" toggle and verifies that the "BOARDING DENIED" warning is not tagged on the guest profile and views the profile

  @ShipSecurity @Smoke @C1050781
    Scenario: Validate that the Otalio application is able to display the Ashore denied flag to the crew member.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User search for Ashore denied guest
    And User able to click "lastName" in the detail table
    And User "enable" the "add-ashore-denied" toggle, verifies that "Lost & Found, Security Incidents" is clickable after the warning flag is tagged on the guest profile, and views the profile
    Then User verify the "ASHORE DENIED" warning to guest profile
      #----Removing-------
    Then User "disable" the "add-boarding-denied" toggle and verifies that the "ASHORE DENIED" warning is not tagged on the guest profile and views the profile

  @ShipSecurity @Smoke @C1050786
    Scenario: Validate that the application is able to display the counts of the individuals in their respective statuses on clicking the count link on the gangway dashboard.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
        #-------------------Guests--------------------------------
    And validate "Checked In Onboard" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Checked In Ashore" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Other Status Onboard" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Expected" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Embarked" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarking" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarked" and "Guests" count
    Then User checking the dashboard count
    And User close the dashboard box

    #-------------------Crew--------------------------------
    And validate "Checked In Onboard" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Checked In Ashore" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Other Status Onboard" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Expected" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Embarked" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarking" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarked" and "Crew" count
    Then User checking the dashboard count
    And User close the dashboard box
      #-------------------Visitors--------------------------------

    And validate "Checked In Onboard" and "Visitors" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Other Status Onboard" and "Visitors" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Expected" and "Visitors" count
    Then User checking the dashboard count
    And User close the dashboard box

        #-------------------Total--------------------------------
    And validate "Checked In Onboard" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Checked In Ashore" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Other Status Onboard" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Expected" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Embarked" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarking" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

    And validate "Disembarked" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

  @ShipSecurity @Smoke @C2110396
    Scenario: Validate that user is able to view all the gangway logs in Otalio.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    And User view on log icon
    And User navigated to gangway logs
    Then User capture details of the user selected "log"

  @ShipSecurity @Smoke @C2110398 @C2110399
    Scenario: Validate that user is able to view all the filters and filter options in it.
    #Scenario: Validate that user is able to modify the search filters to get the specific data.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
      | NCLH | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Gangway Logs" module
    And User navigated to module "Gangway Logs"
    And User selects filters "Account,Age,Cruise,Deny Reason,Details,Message,Override,Override User,Port of Call,Visitor Type,Workstation"
    Then User capture details of the user selected "log"

  @ShipSecurity @Smoke @C2110401
    Scenario: Validate that user is able to view the count after moving the guest from onboard to ashore in gangway by clicking on 'Change to Ashore' button.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    And validate "Checked In Onboard" and "Guests" count
    Then User checking the dashboard count
    And User changed to AShoreOrOnBoard "Ashore"
    And User views the "Change To Ashore" button
    And User click on "Change To Ashore"
    And User click on reload icon
    And validate "Checked In Onboard" and "Guests" count

  @ShipSecurity @Smoke @C2110402
  Scenario: Validate that user is able to view the count after moving the guest from shoreside to onboard in gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User apply filter "Checked In"
    And User unCheck the "Onboard" status "No"
    And User search for the shoreside to onboard guest
    And User click on "Select"
    Then Verify user status "Onboard"

  @ShipSecurity @Smoke @C2110403
  Scenario: Validate that user is able to view the count after moving guest from shoreside to onboard in gangway by clicking on 'change to onboard' button.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    And validate "Checked In Ashore" and "Guests" count
    Then User checking the dashboard count
    And User changed to AShoreOrOnBoard "Ashore"
    And User views the "Change To Onboard" button
    And User click on "Change To Onboard"
    And User click on reload icon
    And validate "Checked In Ashore" and "Guests" count

  @ShipSecurity @Smoke @C2110404
    Scenario: Validate that user is able to select the gangway location before starting gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown

  @ShipSecurity @Smoke @C2110409
    Scenario: Validate that user is able to access the check in status of all accounts in gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    And User view on log icon
    And User navigated to gangway logs
    And User select action types as "In"
    Then User capture details of the user selected "log"

  @ShipSecurity @Smoke @C2110410
  Scenario: Validate that user is able to access the check out status of all accounts in gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    And User view on log icon
    And User navigated to gangway logs
    And User select action types as "Out"
    Then User capture details of the user selected "log"

  @ShipSecurity @Smoke @C2110412
  Scenario: Validate that user is able to view the checked in guest details through gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    And validate "Checked In Onboard" and "Guests" count
    Then User checking the dashboard count
    Then User click on required guest link under name column
    Then User switch to new tab
    And user validates the guest profile name

  @ShipSecurity @Smoke @C2110413
  Scenario: Validate that user is able to view the checked in crew details through gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
      | NCLH | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    And validate "Checked In Onboard" and "Crew" count
    Then User checking the dashboard count
    Then User click on required guest link under name column
    Then User switch to new tab
    And user validates the guest profile name

  @ShipSecurity @Smoke @C2110414
  Scenario: Validate that user is able to view the checked in visitor details through gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    And validate "Checked In Onboard" and "Visitors" count
    Then User checking the dashboard count
    Then User click on required guest link under name column
    Then User switch to new tab
    And user validates the guest profile name

  #@ShipSecurity @Smoke @C2788904
  Scenario: Validate that user is able to view total Checked in onboard guests
    Given Login page is displayed
    When Input credentials to login into nclh
      | Security Officer | Welcome2024! | Ship Security |
    Then User navigate to menu "Ship Security" service
    And validate "Checked In Onboard" and "Total" count
    Then User checking the dashboard count
    And User close the dashboard box

  #@ShipSecurity @Smoke @C2967982
  Scenario: Validate that the user is able to see overall persons count (guests, crew and visitors) on the gangway dashboard
    Given Login page is displayed
    When Input credentials to login into nclh
      | Security Guard | Welcome2024! | Ship Security |
    Then User navigate to menu "Ship Security" service
    And validate "Checked In Onboard" and "Total" count
    And validate "Checked In Ashore" and "Total" count
    And validate "Other Status Onboard" and "Total" count
    And validate "Expected" and "Total" count
    And validate "Embarked" and "Total" count
    And validate "Disembarking" and "Total" count
    And validate "Disembarked" and "Total" count

  #@ShipSecurity @Smoke @C2968013
  Scenario: Validate that the Otalio application is able to display 'missing' on the guest profile who has been flagged as missing.
    Given Login page is displayed
    When Input credentials to login into nclh
      | Security Guard | Welcome2024! | Ship Security |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User search guest with "MISSING FLAG"
    Then User save guest first name
    Then User save guest full name
    #And User save guest firstName excluding sensitive cabins
    And User able to click "lastName" in the detail table
    Then User can see "Missing" flag on guest profile
    Then User view the profile

  #@ShipSecurity @Smoke @C2956506
    Scenario: Validate that the Otalio application is able to display warning notifications for the 'Print on Manifest' guest
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    Then User save guest first name
    Then User save guest full name
    #And User save guest firstName excluding sensitive cabins
    And User able to click "lastName" in the detail table
    And User open guest "Cruise Information" information details
    And User enables "manifest-toggle" for guest
    Then User view Print on Manifest Toggle Slider


  @ShipSecurity @Smoke @C1050712
  Scenario: Validate that the otalio application is able to display each user in one status only at any time.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    And validate "Checked In Onboard" and "Guests" count
    Then User checking the dashboard count
    Then User click on required guest link under name column
    Then User switch to new tab
    And user validates the guest profile name
    Then User verifies board status "Checked In / On Board"

    Then User navigate to menu "Ship Security" service
    And validate "Checked In Ashore" and "Guests" count
    Then User checking the dashboard count
    Then User click on required guest link under name column
    Then User switch to new tab
    And user validates the guest profile name
    Then User verifies board status "Checked In / Shoreside"

    Then User navigate to menu "Ship Security" service
    And validate "Other Status Onboard" and "Guests" count
    Then User checking the dashboard count
    Then User click on required guest link under name column
    Then User switch to new tab
    And user validates the guest profile name
    Then User verifies board status "Checked Out / On Board"

  @ShipSecurity @Smoke @C2110415
  Scenario: Validate that user is able to view the visitor profile through the gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Visitors Management" module
    And User navigates to "Visitors Management" link
    And User clears valid from filter
    Then User is able to view "guest"
    And User able to click "lastName" in the detail table
    Then User view the profile


  @ShipSecurity @Smoke @C2110397
    Scenario: Validate that user is able to input the value manually for authorities at Gangway
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User set the tender capacity "2" number
    Then User view the tender capacity count
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User view the tender capacity number
    And User click increase authority button 2 times
    And User click decrease authority button 1 times
    Then User is able to view "tender capacity"

  @ShipSecurity @Smoke @C1050784
  Scenario: Validate that user is able to move a checked-in onboard visitor through the gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And Click on "Visitors" icon at top right pane
    And User apply filter "Checked In"
    And User search for the visitor person
    And User click on "Select"
    Then Verify user status "Onboard"


  @ShipSecurity @Smoke @C1050725
    Scenario: Validate that the Otalio application is able to display a flag to the guest as a Person of Interest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User search Person of Interest guest
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open guest "Lost & Found, Security Incidents" information details
    And User click on three dots
    And User add the Boarding Denied
    And User click on Reason and select 'Person of Interest' and add Reason Detail
    And User click on "Save"
    Then User view the profile
    Then User verify the "BOARDING DENIED" warning to guest profile

    Then User navigate to menu "Ship Security" service
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User search for the Person of Interest guest
    And User click on "Select"
    Then User verify error message on Gangway mode with "Boarding denied due"

  @ShipSecurity @Smoke @C1050724
  Scenario: Validate that the Otalio application is able to display a flag to the crew member as a Person of Interest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User search crew Person of Interest
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open guest "Lost & Found, Security Incidents" information details
    And User click on three dots
    And User add the Boarding Denied
    And User click on Reason and select 'Person of Interest' and add Reason Detail
    And User click on "Save"
    Then User view the profile
    Then User verify the "BOARDING DENIED" warning to guest profile

    Then User navigate to menu "Ship Security" service
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And Click on "Crew" icon at top right pane
    And User search for the crew Person of Interest
    And User click on "Select"
    Then User verify error message on Gangway mode with "Boarding denied due"

  @ShipSecurity @Smoke @C1050672
  Scenario: ValidateValidate that user is able to move a expected guest through the gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User apply filter "Expect Today"
    And User search for the expected guest today
    And User click on the "Select"
    Then Verify user status "Onboard"

  @ShipSecurity @Smoke @C1050783
  Scenario: Validate that the Otalio application is able to display Gangway Movement Statistics data.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
      | NCLH | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    And User open service link "Statistics"
    Then User is able to view the Gangway Movement Statistics data

  @ShipSecurity @Smoke @C2110400
  Scenario: Validate that user is able to view the count after moving the guest from onboard to ashore in gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    And User click on the counts tab
    And validate "Checked In Onboard" and "Guests" count
    Then User checking the dashboard count
    And User changed to AShoreOrOnBoard "Ashore"
    And User views the "Change To Ashore" button
    And User click on "Change To Ashore"
    Then User validate "Checked In Onboard" and "Guests" count


  @ShipSecurity @Smoke @C2110408
  Scenario: Validate that user is able to access the visitors from Visitor Management window in gangway
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    And User clicks the Actions button and verifies the "Visitor Management" option is displayed
    And User clicks on "Visitor Management" and navigates to the Visitor tab page
    And User clears valid from filter
    Then User capture details of the user selected "log"
    Then User view the visitors

  @ShipSecurity @Smoke @C1050714
  Scenario: Validate that the user is able to view the following port details - port name, country, arrrival time, departure time, go ashore time, all onboard for the selected date in the guest service calendar view screen
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User check the calender is middle or not
    And User checks the Port Name field under Port Details and clicks on it
    Then User view the port details

  @ShipSecurity @Smoke @C1050715
  Scenario: Validate that the user is able to view the scheduled drill and training activities for the selected date in the guest service calendar view screen.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User check the calender is middle or not
    And User checks the scheduled activities and clicks on it
    And User view the scheduled drill and training activities

  @ShipSecurity @Smoke @C1050716
  Scenario: Validate that the user is able to view the guest embarking/ disembarking count for the selected date in the guest service calendar view screen.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Guest Services |
      | O&R | Security Officer | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User check the calender is middle or not
    And User checks the Port Name field under Port Details and clicks on it
    Then User view the the guest embarking and disembarking count validate it

  @ShipSecurity @Smoke @C1050717
  Scenario: Validate that user is able to move a checked-in onboard visitor through the gangway.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    And User checks the details "Authorities Counter", "All Aboard Crew", "All Aboard Guests" and "Time till Departure" at the top left side of the screen

  @ShipSecurity @Smoke @C1050737
  Scenario: Validate that the Otalio application is able to display'missing' on the crew profile pic who has been flagged as missing.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item
    And User search crew as missing
    Then User save crew first name
    Then User save crew full name on crew page
    And User able to click "lastName" in the detail table
    Then User can see "Missing" flag on guest profile

  @ShipSecurity @Smoke @C2110405
  Scenario: Validate that user is able to search and view the gangway log history.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Gangway Logs" module
    And User navigated to module "Gangway Logs"
    And User selects a random cabin number
    And User searches for the selected cabin number in the "Cabin" search dropdown
    Then User is able to view the crew member details for the searched cabin number

  @ShipSecurity @Smoke @C2859856
  Scenario: Verify that the gangway movements are disabled for guest having POI flagged.
    Then User adds the POI flag to the selected guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User navigate to menu "Ship Security" service
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User search for the Person of Interest guest
    And User click on "Select"
    Then User verify error message on Gangway mode with "Boarding denied due"

  @ShipSecurity @Smoke @C3836247
  Scenario: Validate that the user is able to move a checked in guest through Gate
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Start Location Gate" module
    And User navigated to module "Start Location Gate"
    And User selects the "in" direction in Gangway Gate mode
    And User selects a location from the Start Location Gate dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User searches and selects the guest
    And User click on "Select"
    Then Verify user status "IN"

  @ShipSecurity @Smoke @C1050727
  Scenario: Validate that the otalio application is able to display a flag to guests with medical conditions.
    #Then User adds the medical flag to the selected guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User navigate to menu "Ship Security" service
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And User search for the Medical Conditions guest
    And User click on "Select"
    Then User verify error message on Gangway mode with "Boarding denied due to Medical Reasons Automation Testing."

  @ShipSecurity @Smoke @C1050728
  Scenario: Validate that that otalio application is able to display a flag to the crew members with medical conditions.
    #Then User adds the medical flag to the selected crew
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Ship Security |
      | O&R | Security Officer | Welcome2024! | Ship Security |
    Then User navigate to menu "Ship Security" service
    Then User navigate to menu "Ship Security" service
    Then User display "Start Gangway" module
    And User navigated to module "Start Gangway"
    Then user select the gangway both option
    And User select location "Gangway Internal - @Health Check A" in dropdown
    And User click on "confirm"
    Then User display the search icon
    And User click on search
    And Click on "Crew" icon at top right pane
    And User search for the Medical Conditions crew
    And User click on "Select"
    Then User verify error message on Gangway mode with "Boarding denied due to Medical Reasons Automation Testing."

  @ShipSecurity @Smoke @C3836245
  Scenario: Validate that the denied reason is being shown in Gangway logs
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Guard | Welcome2024! | Ship Security |
      | O&R | Security Guard | Welcome2024! | Ship Security |
    Then User selects the "Ship Security" service from the left-side panel
    Then User display "Gangway Logs" module
    And User navigated to module "Gangway Logs"
    And User verifies the "success" filter is set to "No" and the results are displayed
    Then User verifies the deny reasons are displayed

  @ShipSecurity @Smoke @C1050740
  Scenario:Validate that the Otalio application is able to display the in port manning flag to the crew member.
    Given User has added the crew member to the Port Manning details
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User selects the "Crew Services" service from the left-side panel
    And User open service "Crew Search" item card
    And User searches and assigns the crew member to the Port Manning details
    And User able to click "lastName" in the detail table
    Then User verifies the "In Port Manning" value under Safety in the Crew Summary

  @ShipSecurity @Smoke @C3912535
  Scenario: Validate user is able to edit the reported by field while reporting a missing person
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Security Officer | Welcome2024! | Crew Services |
      | O&R | Security Officer | Welcome2024! | Crew Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    And Search the guest user
    And User able to click "lastName" in the detail table
    And User open link "quick-links"
    And User views the "Report as missing" button
    And User navigate to "Report as missing"
    And User set missing date and time
    Then User select the Reported person
    And User views the "Select" button
    And User click on "Select"
    And User fill out required fields
    And User edits and fills out the required fields
    Then User verifies that the required field text has been edited
