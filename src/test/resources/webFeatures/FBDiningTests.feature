Feature: Dining Service
  As a customer, I want to view dining information/status page

  Background: F&BDining Service page

  @FAndBDinning @FB1 @Smoke @NotDependent @C1101820 @C1165461  @C1051092 @C1166554 @C1051099 @C1167633  @C1101820  @FB @Sanity
    Scenario Outline:Create a Reservation for the Guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    #And User apply filter "<GuestStatus>"
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "<ServiceName>" item
    And User click on reservation Tab
    And User select the dining  location in reservation as "Cagney's"
    And User type cabin number in filter
    And User Verify that reservation is done for the guest
    And User click On name in the table
    And User click on "CANCEL"


#    And User open service "<ServiceName>" item
#    And User click on map Tab
#    And User select dining location "Cagney's"
#    And User select on table "1"
#    And User click on "REGULAR"
#    And User type the amount of guests "2"
#    Then User click on plus button
#    And User unselect the filters and select "Checked In"
#    And User select the guests
#    And User click on save
#    And User save the guest names
#    And User select the product dropdown as "BREAKFAST"
#    Then User select time from dropdown
#    And user click on inventory slots
#    And User click on "Save / Next"
#    And User confirm if guest is already reserved
#    And User select table "5"
#    And User click on "Save / Next" in select table
#    And User click on "Add" reservation
#    And User confirm if guest is already reserved
#    And User click on reservation Tab
#    And User open hidden filters
#    #And User select location "DNU-Manhattan" in reservations
#    And verify the Reservation
#    And User click on guest
#    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       | GuestStatus |
      | Dining  | Reservations List | Checked In  |


  @FAndBDinning @FB2 @Smoke @NotDependent @C1051097 @FB @Bug
  Scenario Outline:Move the party from one table to another
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location "Cagney's"
    And User select on table "2"
    And User click on "REGULAR"
    And User type the amount of guests "2"
    Then User click on plus button
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select the guests
    And User click on save
    And User save the guest names
    Then User select time from dropdown
    And user click on inventory slots
    And User click on "Save / Next"
    And User confirm if guest is already reserved
    And User select table "5"
    And User click on "Save / Next" in select table
    And User click on "Add" reservation
    And User click on reservation Tab
    #And User open hidden filters
    #And User select location "DNU-Manhattan" in reservations
    And verify the Reservation
    And User click on guest
    And User click on "CANCEL"

    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB3 @Smoke @NotDependent  @FB @Bug
  Scenario Outline:Cancel a guest's upcoming dining reservation
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location " Cagney's"
    And User select on table "1"
    And User click on "REGULAR"
    And User type the amount of guests "2"
    Then User click on plus button
    And User select the guests
    And User click on save
    And User save the guest names
    Then User select time from dropdown
    And user click on inventory slots
    And User click on "Save / Next"
    And User confirm if guest is already reserved
    And User select table "5"
    And User click on "Save / Next" in select table
    And User click on "Add" reservation
    And User click on reservation Tab
    And User open hidden filters
    #And User select location "DNU-Manhattan" in reservations
    And verify the Reservation
    And User click on guest
    Then User click on edit icon
    And User click on "Save / Next"
    And User select table "6"
    And User get the selected table number
    And User click on "Save / Next" in select table
    And User click on "Finish" reservation
    And User get the "Table(s)" number from table
    Then Verify the table number
    And User click on guest
    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB4  @Smoke  @FB @Bug
  Scenario Outline:Transfer a guest from one table to the other from Dining - > Maps view
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location "Cagney's"
    And User select on table "2"
    And User click on "REGULAR"
    And User type the amount of guests "2"
    Then User click on plus button
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select the guests
    And User click on save
    And User save the guest names
    Then User select time from dropdown
    And user click on inventory slots
    And User click on "Save / Next"
    And User confirm if guest is already reserved
    And User select table "4"
    And User get the selected table number
    And User click on "Save / Next" in select table
    And User click on "Add" reservation
    And User click on reservation Tab
    And User open hidden filters
    #And User select location "DNU-Manhattan" in reservations
#    And verify the Reservation
    And User click on map Tab
    And User select dining location " Cagney's"
    Then User  select the reserved table
    And User click on "Reservation List (1)"
    And User click on reservation card
    Then User click on edit icon
    And User click on "Save / Next"
    And User select table "6"
    And User get the selected table number
    And User click on "Save / Next" in select table
    And User click on "Finish" reservation
    And User click on reservation Tab
    And User open hidden filters
    And User get the "Table(s)" number from table
    Then Verify the table number
    And User click on guest
    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB5  @Smoke   @C1051109 @C1218661 @FB
  Scenario Outline:Display table status
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location " Cagney's"
    And User select on table "1"
    And User click on "REGULAR"
    And User type the amount of guests "2"
    Then User click on plus button
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select the guests
    And User click on save
    And User save the guest names
    Then User select time from dropdown
    And user click on inventory slots
    And User click on "Save / Next"
    And User confirm if guest is already reserved
    And User select table "3"
    And User get the selected table number
    And User click on "Save / Next" in select table
    And User click on "Add" reservation
    And User click on reservation Tab
    And User open hidden filters
    #And User select location "DNU-Manhattan" in reservations
    And verify the Reservation
    And User click on map Tab
    And User select dining location " Cagney's"
    Then User  select the reserved table
    And Verify the status of the table is "Reserved"
    And User click on reservation Tab
    And User open hidden filters
    And User click on guest
    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @Smoke @FB6 @Bug
  Scenario Outline:Combine one or more tables together
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location " Cagney's"
    And User select on table "1"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB7 @Smoke @NotDependent @C1119379 @FB @Sanity
  Scenario Outline:Search for a guest by Last Name
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "<ServiceName>" item
    And User click on reservation Tab
    And User select the dining  location in reservation as "Cagney's"
    And User type the last name in search
    And User Verify that reservation is done for the guest
    And User click On name in the table
    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB8 @Smoke @NotDependent @C1051093  @C1152252 @FB @Sanity @C1050113 @ @C1050112
  Scenario Outline:View guest's dining reservation history
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "<ServiceName>" item
    And User click on reservation Tab
    And User select the dining  location in reservation as "Cagney's"
    And User type cabin number in filter
    #And User type the last name in search
    And User Verify that reservation is done for the guest
    And User refresh the page
    And User logout from nclh
    Given Login page is displayed
    When Input credentials to login into nclh
     #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User type cabin number in filter
    And User search for specific "GuestNameLastName" and navigated to profile
    And User open the calender icon on the right panel
    And User get the dining reservation history
    And User verify that the list has "Cagney's"

    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB9 @Smoke @NotDependent @C1051101 @C1172052 @C1172053 @FB @Sanity
  Scenario Outline:Add a guest to the waitlist
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User again select the cagney's
    And User click on Cagney hyper link
    Then User click on chain icon at top right pane.
    And User click on " RESET TABLE MAP "
    And User click on " CONFIRM "
    And User close the right panel
    And User click on single file icon on the right pane
    Then User click on waiting list
    And User select the pager
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
#    And User clear reservation status of the crew
#    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on "Add"
    And User click on reservation Tab
    And User open hidden filters
    And User select "Dining Status" filter  select option as "WaitList"
    And User type the last name in search
    And User Verify that reservation is done for the guest
    And User click On name in the table
    And User click on " ASSIGN "
    And User click on table "1" for assign
    And User click on " Apply "
    #we need to login with QAautoperform to do this
#    And User click on dining
#    And User click on " Pager Management "
#    And User return the pager
#    And User click on " Confirm "

    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB10 @Smoke @NotDependent @C1051096
  Scenario Outline:Block Table(s)
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location "Cagney's"
    And User select on table "1"
    And User click on " BLOCK "
    And User verify that same table is showing blocked Status
    And User click on " UNBLOCK "
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB11 @Smoke @NotDependent @C1051110 @C1051111
  Scenario Outline:Combine one or more tables together
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location "Cagney's"
    And User select on table "1"
    And User clicks on Table Join and joins table "6"
    And User click on "SAVE"
    And User Verify that table is joined
    And User click on UnJoin

    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB12 @Smoke @NotDependent @C1051098 @FB @Sanity
  Scenario Outline:Search for a guest by First Name
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "<ServiceName>" item
    And User click on reservation Tab
    And User select the dining  location in reservation as "Cagney's"
    And User type the first name in search
    And User Verify that reservation is done for the guest
    And User click On name in the table
    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB4  @Smoke @C1191474 @FB @Bug
  Scenario Outline:Transfer a guest from one table to the other from Dining - > Maps view
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location " Cagney's"
    And User select on table "2"
    And User click on "WALKIN"
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on reservation Tab
    And User select the dining  location in reservation as "Cagney's"
    And User type the first name in search
    And User Verify that reservation is done for the guest
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB14 @C1051087
  Scenario Outline:Verify that the specialty dining reservation reflects on the guest's profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In" option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "<ServiceName>" item
    And User click on reservation Tab
    And User select the dining  location in reservation as "Cagney's"
    And User type cabin number in filter
    #And User type the last name in search
    And User Verify that reservation is done for the guest
    And User refresh the page
    And User logout from nclh
    Given Login page is displayed
    When Input credentials to login into nclh
      #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User type cabin number in filter
    And User search for specific "GuestNameLastName" and navigated to profile
    And User open the calender icon on the right panel
    And User get the dining reservation history
    And User verify that the list has "Cagney's"

    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB15 @C1051094
  Scenario Outline: Assign a reservation to a table for today's booking
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User click on single file icon on the right pane
    And User double tap on the reservation
    And User select dining location "Cagney's"
    And User select on table "1"
    And User click on "ASSIGN"
    And User click on "ASSIGN on 1"
    And Verify the status of the table is "Reserved"
    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB16 @C1051098
  Scenario Outline:Search for a guest reservation by guest last name
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "<ServiceName>" item
    And User click on reservation Tab
    And User select the dining  location in reservation as "Cagney's"
    And User type the last name in search
    And User Verify that reservation is done for the guest
    And User click On name in the table
    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB17 @C1184518
  Scenario Outline: Assign a table to an existing expected reservation
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "<ServiceName>" item
    And User type the last name in search
    And User Verify that reservation is done for the guest
    And User click On name in the table
    And User click on " ASSIGN "
    And User click on table "1" for assign
    And User click on " Apply "
    And User click on "ASSIGN on 1"
    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB18 @C1196581
  Scenario Outline:Unseat a guest from a table from the dining map
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location " Cagney's"
    And User select on table "2"
    And User click on "WALKIN"
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on "Override" if there is previous reservation
    And Verify the status of the table  "InHouse"
    And User click on "UNSEAT"
    And Verify the status of the table  "Reserved"
    And User click on "UNASSIGN"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB19 @C1196584
  Scenario Outline:Mark a table as clean from the dining map
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location " Cagney's"
    And User select on table "2"
    And User click on "WALKIN"
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on "Override" if there is previous reservation
    And Verify the status of the table  "InHouse"
    And User click on "FINISH"
    And User click on "CLEAN"
    And Verify the status of the table is changed to  "Open"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB20 @C1196585
  Scenario Outline: Mark a table as closed from the dining map
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location " Cagney's"
    And User select on table "34"
    And User click on "CLOSE"
    And User select the reason for closing table as "TEST"
    And User enter the additional comment as "test"
    And User click on "CONFIRM"
    And Verify the status of the table is changed to  "Closed"
    And User click on "OPEN"
    And Verify the status of the table is changed to  "Open"

    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB21 @C1218662
  Scenario Outline: Add a comment while making the reservation for the guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User enter the comment filed for guest reservation as "TestAutomation"
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "<ServiceName>" item
    And User type the last name in search
    And User Verify that reservation is done for the guest
    And User Verify the comment is "TestAutomation"
    And User click on "CANCEL"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB22 @C1276318
  Scenario Outline:Reset a table map for a particular restaurant venue
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User again select the cagney's
    And User click on Cagney hyper link
    Then User click on chain icon at top right pane.
    And User click on " RESET TABLE MAP "
    And User click on " CONFIRM "
    And User close the right panel
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @FB23 @C1172152
  Scenario Outline:Guest has completed the reservation, the table color status is updated
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | F&B dining | Welcome2024! | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location " Cagney's"
    And User select on table "2"
    And User click on "WALKIN"
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on "Override" if there is previous reservation
    And Verify the status of the table  "InHouse"
    And User click on "FINISH"
    And User click on "CLEAN"
    And Verify the status of the table is changed to  "Open"
    And Verify the table colour is updated to green
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @FAndBDinning @C1051086
  Scenario: Verify that the specialty dining reservation reflects on the guest's profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Restaurant Manager | Welcome2024! | Dining |
    Then User navigate to menu "Dining" service
    And User open service "Reservation Manager" item
    And User click on plus button to add guest
    And User clear reservation status filter of the guest
    Then User select "Checked In " option from  filter
    And User select a guest in dining
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click the first available slot
    And User enter the comment filed for guest reservation as "TestAutomation"
    And User save the restaurants name
    And User click on confirm
    And User click on dining
    And User again select the cagney's
    And User open service "Reservations List" item
    And User type the last name in search
    And User Verify that reservation is done for the guest