Feature: Maintenance

  1.Verify If a guest is assigned to the ticket, ensure that a service request is not created when check box is unselected
  2.Verify the Ability to toggle button on if the ticket created is related to housekeeping
  3.Verify the Ability to toggle button off if the ticket created is related to housekeeping


  @HotelMaintenance @HM1 @Smoke @NotDependent @C1326978 @C1326926 @Sanity @SC
  Scenario Outline:Verify If a guest is assigned to the ticket, ensure that a service request is not created when check box is unselected
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on add icon button
    And User select request as "<RequestName>"
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click on "Add"
    #And Verify if error message occurred select other guest
    And user again got error message then click on add
    And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "<RequestName>"
    And User close the request
    And User enter the comment as "test"
    And User click on "Confirm"

    Examples:
      | Service     | ServiceName          | RequestName|
      | Maintenance | Maintenance Overview |  A/C Diffuser Dirty |

  @HotelMaintenance @HM2 @Smoke @NotDependent @C1326979 @SC @C2968069
  Scenario Outline:Verify the Ability to toggle button on if the ticket created is related to housekeeping
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "A/C Diffuser Dirty"
    And User enable the toggle "inform-housekeeping"
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click on create housekeeping task checkbox
    #And User select cleaning task as "A/C Vents"
    And User click on "Add"
    #And Verify if error message occurred select other guest and enable toggle
    And user again got error message then click on add
    And User logout from nclh
    When Input credentials to login into nclh
      | Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "Housekeeping" service
    And User open service "Task Overview" item
    And User open hidden filters
    And User type cabin number in cleaning location filter
    #And User save the "Task Code" in housekeeping
#And User verify the task code is "A/C Vents"
#
    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM3 @Smoke @NotDependent @C1326980 @SC @C2968070
  Scenario Outline:Verify the Ability to toggle button off if the ticket created is related to housekeeping
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as " A/C Diffuser Dirty "
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click on "Add"
    #And Verify if error message occurred select other guest
    And User logout from nclh
    When Input credentials to login into nclh
      | Housekeeping | Welcome2024! | Housekeeping |
    Then User navigate to menu "Housekeeping" service
    And User open service "Task Overview" item
    And User open hidden filters
    And User type cabin number in cleaning location filter
    And User save the text message
    Then verify text message is "No Data"

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM4 @Smoke @NotDependent @C1326982 @SC @C2968072  @C2968067
  Scenario Outline:Submit ticket and ensure that ticket is added to "Maintenance Overview"
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "A/C Diffuser Dirty"
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User type comment "test comment"
#    And User select the target completion
#    And User click on "OK"
    And User click on both add buttons
    And User click on "Add"
    And user again got error message then click on add
    #And Verify if error message occurred select other guest
    And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "A/C Diffuser Dirty"
    Then User close the record

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM5 @Smoke @NotDependent @C1326983 @C1326990 @SC  @C2968073
  Scenario Outline:Verify the display of error message if required details are missing or if the ticket cannot be created
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User click on request dropdown and don't fill
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User save the mandatory field error message
    And User Verify the error text as "Request is required"

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM6 @Smoke @NotDependent @C2968063 @C2968077
  Scenario Outline:Verify the System automatically adds priority and actioning positions when the issue type is selected
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    Then User click on "Fast Creation" action box
    And User click on "Advanced"
    Then User switch window to add new workorder
    And User select request as "Air Conditioning Not Working"
    Then User verify the priority is updated automatically
    Then User verify the team field is updated automatically
    #And User check the "Priority" text

    Examples:
      | Service     |
      | Maintenance |

  @HotelMaintenance @HM7 @Smoke @NotDependent @C1326989 @C1326985 @SC @C2968079
  Scenario Outline:Verify the Submitted ticket is added to "Maintenance Overview" dashboard
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    Then User click on "Fast Creation" action box
    And User select request as "A/C Diffuser Dirty"
    And User add location in where
    Then User enter text in detail field as "test comment"
    And User click on "Create"
    And User open service "<ServiceName>" item
   # And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "A/C Diffuser Dirty"

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM8 @Smoke @NotDependent  @C1327001 @SC @C2968090
  Scenario Outline:Verify the Ability to search, select, and view all tickets created and their respective statuses: open, closed, in progress, on hold/pending
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Service |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User open hidden filters
    Then User clear status of the request overview
    And User select "<Status>" status on request overview
    And User verify that open status list do not contain "<StatusCheck>"

    Examples:
      | Service     | ServiceName          | Status      | StatusCheck |
    #  | Maintenance | Maintenance Overview | Open   | CLOSED      |
      | Maintenance | Maintenance Overview | Closed      | OPEN        |
      | Maintenance | Maintenance Overview | In Progress | OPEN        |


  @HotelMaintenance @HM9 @Smoke @NotDependent @C1327000 @SC @C2968089
  Scenario Outline:Verify the Ability to select the tickets created and their respective statuses: open, closed, in progress, on hold/pending
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Service |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User open hidden filters
    Then User clear status of the request overview
    And User select "<Status>" status on request overview
    And User verify select any open request

    Examples:
      | Service     | ServiceName          | Status |
      | Maintenance | Maintenance Overview | Open   |

  @HotelMaintenance @HM10 @Smoke @NotDependent @C1326995 @SC @C2968084 @OCI @OCIHM
  Scenario Outline: Validate that ticket history and view that all actions on the ticket have been completed
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User open hidden filters
    Then User clear status of the request overview
    And User select "<Status>" status on request overview
    Then User click edit icon on request overview
    And User click "History" on request overview
    And User verify that ticket history and view all actions on the ticket have been completed


    Examples:
      | Service     | ServiceName          | Status |
      | Maintenance | Maintenance Overview | Open   |

  @HotelMaintenance @HM11 @Smoke @OCI @OCIHM @NotDependent @C1326972 @Regression @SC
  Scenario Outline:Verify the guest service operators can add location,images and issue details to the ticket.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |

    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User open hidden filters
    Then User clear status of the request overview
    And User select "<Status>" status on request overview
    Then User click edit icon on request
#    Then User click on reported by icon
#    And User select a guest
#    And User save the guest name
#    And User save the cabin number of guest
#    And User click on "Select"
    And User click "Images" in who
    And User upload image
    And User click on "Confirm"
    And User save the where location
#    And User select the target completion
#    And User click on "OK"
    And User click on "Save"
    #Then user search in location filter
    Then User click edit icon on request
    And User click "Images" in who
    And User verify that image is add by deleting it

    Examples:
      | Service     | ServiceName          | Status |
      | Maintenance | Maintenance Overview | Open   |

  @HotelMaintenance @HM12 @Smoke @OCI @OCIHM @Regression @C1326977 @SC @C1326971 @C2968061 @C2968099
  Scenario Outline:Verify If a guest is assigned to the ticket, ensure that a service request is created once the ticket is submitted
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      |NCL| Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |

    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "<RequestName>"
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click service request checkbox
    #And User select the target completion
    #And User click on "OK"
    And User click on "Add"
    And user again got error message then click on add
    #And Verify if error message occurred select other guest
    #And User select service rquest as "A/C Diffuser Dirty"
    And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "<RequestName>"
    Then User close the record

    Examples:
      | Service     | ServiceName          | RequestName|
      | Maintenance | Maintenance Overview |  A/C  Compartment Noise |

  @HotelMaintenance @HM13 @Smoke @NotDependent @Regression @C1326975 @SC @OCI @OCIHM
  Scenario Outline:verify  the guest user can edit  reporting person by searching for crew name.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
  #  And User open Maintenance overview
    And User click on add icon button
    And User select request as "<RequestName>"
    Then User click on reported by icon
    And User click on crew list
    And User select a crew
    And User save the guest name
    #And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    #And User type cabin number
    And User click on add symbol
    And User save the cabin number after adding
    And User click on "Add"
    #And User save the cabin number after adding
    #And Verify if error message occurred select other guest
    And user again got error message then click on add
   # And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "<RequestName>"
    And User close the request
    And User enter the comment as "test"
    And User click on "Confirm"

    Examples:
      | Service     | ServiceName          | RequestName           |
      | Maintenance | Maintenance Overview |  A/C Diffuser Dirty  |

  @HotelMaintenance @HM14 @NotDependent @Regression  @SC @C2968071
  Scenario Outline:verify the Ability to assign the ticket to the person who is logging the ticket by selecting "assign to me"
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Hotel Maintenance | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "<RequestName>"
    Then User click on assignee icon
#    And User open hidden filters
#    Then User clear Operation Department filter
#    And User clear the operation position
    And User select a assignee
    And User save the assignee name
    And User click on "Select"
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click on "Add"
    #And Verify if error message occurred select other guest
    And user again got error message then click on add
    And User type cabin number in location filter
    And User save the "Assignee"
    And User verify the assignee is as saved

    Examples:
      | Service     | ServiceName          | RequestName              |
      | Maintenance | Maintenance Overview | A/C Diffuser to Re-Paint |

  @HotelMaintenance @HM15 @Smoke @NotDependent @Regression @C1326986 @C1326984 @C1326988 @SC @C2968078 @C2968076 @OCI @OCIHM
  Scenario Outline:Verify the guest user can add details of the ticket including: what happened, where, comments, photos in fast section screen
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    Then User click on "Fast Creation" action box
    And User select request as "A/C Diffuser Dirty"
    And User add location in where
    #And User add cabin location in where
    Then User enter text in detail field as "test comment"
    And User upload image
    And User click on "Confirm"
    And User click on "Create"
    And user again got error message then click on create
    And User open service "<ServiceName>" item
    #And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "A/C Diffuser Dirty"
    Then User click edit icon on request
    And User click "Images" in who
    And User verify that image is add by deleting it

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM16 @Smoke @NotDependent @C1326991 @SC @C2968081 @OCI @OCIHM
  Scenario Outline:Verify if system will automatically assign operational position(s) of the ticket submitted
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "<RequestName>"
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And user verify automatically assign operational position(s) of the ticket submitted

    Examples:
      | Service     | ServiceName          | RequestName|
      | Maintenance | Maintenance Overview |  A/C Diffuser Dirty |

  @HotelMaintenance @HM17 @Smoke @NotDependent @C1326996 @Regression @SC @C2968085 @OCI @OCIHM
  Scenario Outline:Verify the guest service operators can add location,images and issue details to the ticket.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Open" status on request overview
    And User Save the first Request
    And User close the request
    And User enter the comment as "test"
    And User click on "Confirm"
    And User verify the request is closed successfully

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM18 @Smoke @NotDependent @C1326974 @Regression @OCI @OCIHM
  Scenario Outline:verify the guest user can add reporting person by searching for guest name by last or first name
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    #And User open hidden filters
    And User set age filter to be adult
    And User save guest firstName excluding sensitive cabins
    Then User save guest first name
    Then User save guest full name
    Then User navigate to menu "Guest Services" service
    And User open service "Service Request Overview" item
    And User open hidden filters
   # And User clear the reservation Status filter
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "<RequestName>"
    Then User click on reported by icon
#    And User clear reservation status of the crew
#    Then User select "Checked In " option from  filter
    And User search a guest by first name
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click on "Add"
    And user again got error message then click on add
    And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "<RequestName>"
    And User close the request
    And User enter the comment as "test"
    And User click on "Confirm"
    Examples:
      | Service     | ServiceName          | RequestName           |
      | Maintenance | Maintenance Overview |  A/C Diffuser Dirty  |

  @HotelMaintenance @HM19 @Smoke @NotDependent @Regression @C1326976 @SC
  Scenario Outline:verify the guest user can edit reporting person by searching for crew name
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "<RequestName>"
    Then User click on reported by icon
    And User click on crew list
    And User select a crew
    And User delete the selected guest
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    #
      # And User save the cabin number after adding
    And User click on add symbol
    And User click on "Add"
    And user again got error message then click on add
   # And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "<RequestName>"

    Examples:
      | Service     | ServiceName          | RequestName           |
      | Maintenance | Maintenance Overview |  A/C Diffuser Dirty  |

  @HotelMaintenance @HM20 @Smoke @NotDependent @C1326998 @Regression @SC @C2968087
  Scenario Outline:Verify the display of error message if ticket cannot be closed
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Service |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Open" status on request overview
    And User Save the first Request
    And User close the request
    And User enter the comment as "test"
    And User click on "Confirm"
    And User verify the request is closed successfully
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Closed" status on request overview
    And User search for the same request
    Then user again close the record
    Then User verify error toast message appears on the screen with message "Cannot update closed maintenance request"

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM21 @Smoke @NotDependent @C1326993 @Regression @SC @OCI @OCIHM
  Scenario Outline:Verify user is able to Search and select ticket in Maintenance Overview
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Open" status on request overview
    And User Save the first Request
    And User save the "Request"
    And User search for the same request
    And User verify that "Request" is same

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |


  @HotelMaintenance @HM22 @Smoke @NotDependent @C1326994 @Regression @C2968083
  Scenario Outline:Verify user is able to Search by request name and select ticket in Maintenance Overview
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Service |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Open" status on request overview
    And User save the "Request"
    And User search for the same request name
    And User verify that "Request" is same

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM23 @Smoke @NotDependent @C1334170 @Regression @C2968096
  Scenario Outline:verify guest service operator can search and select desired ticket to action on
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Open" status on request overview
    And User Save the first Request
    And User save the "Request"
    And User search for the same request
    And User verify that "Request" is same

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM24 @Smoke @NotDependent @C1334169 @Regression @C2968095 @OCI @OCIHM
  Scenario Outline:verify the Ability to add columns from column chooser to maintenance dashboard export bulk maintenance request into a report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on hide columns button
    And User drag "Group" and drop at the column header
    Then User verify "Group" column displayed in the header row

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM25 @Smoke @NotDependent @C1327003 @Regression @C2968092 @C1326973 @C1326999 @OCI @OCIHM
  Scenario Outline:Verify the ability to place search filters in order to view desired view of tickets
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |

    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User select "Priority" option from the add more filter
    And User select the priority filter as "4 - High Priority"
    And User verify the priority list is updated to "4 - High Priority"

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM26 @Smoke @NotDependent @C1334168 @Regression @C2968094 @OCI @OCIHM
  Scenario Outline:verify the Ability to export ticket data into a report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User export ticket data
    And User verify that file is downloaded

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |


  @HotelMaintenance @HM27 @Smoke @NotDependent @C1326992 @Sanity @C2968082
  Scenario Outline: Verify Otalio to View actions of the assigned operational position(s)
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on add icon button
    And User select request as "<RequestName>"
    And User select the Category filter as "Technical - Electric..."
    #And User verify the operationalDepartment is "ENGINE"
    And User verify the team is "IT/Systems Team"

    Examples:
      | Service     | ServiceName          | RequestName|
      | Maintenance | Maintenance Overview |  A/C Diffuser Dirty |

  @HotelMaintenance @HM28 @Smoke @Sanity @C2968088
  Scenario Outline: Verify the Ability to search, select, and view all tickets created and their respective statuses: open, closed, in progress, on hold/pending
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Maintenance |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User verifies that list of request raised is displayed from newest to old
    And User expands the filter to see the list of filters available
    Then User clear status of the request overview
    And User select "Open" status on request overview
    And User verifies status search only shows "OPEN"
    Then User clear status of the request overview
    And User select "Closed" status on request overview
    And User verifies status search only shows "CLOSED"
    Then User clear status of the request overview
    And User select "In Progress" status on request overview
    And User verifies status search only shows "IN PROGRESS"
#    And User select "On hold / pending" status on request overview
#    And User verifies status search only shows "ON HOLD / PENDING"

    Examples:
      | Service        |ServiceName|
      | Maintenance |Maintenance Overview|

  @HotelMaintenance @HM28 @Smoke @NotDependent  @SC @C2968068
  Scenario Outline:Verify the Ability to toggle button off if the ticket created is related to housekeeping
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as " A/C Diffuser Dirty "
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click on "Add"
    #And Verify if error message occurred select other guest
    Then User navigate to menu "Guest Services" service
    And User open service "Service Request Overview" item
    And User search with guest cabin number
    And User save the text message
    Then verify text message is "No matching Service requests found"

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM29 @Smoke @NotDependent  @SC @C2968067
  Scenario Outline:Verify If a guest is assigned to the ticket, ensure that a service request is created once the ticket is submitted.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "A/C Diffuser Dirty"
#    And User enable the toggle "inform-housekeeping"
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click service request checkbox
    And User select service rquest as "A/C Diffuser Dirty"
    And User click on "Add"
    #And Verify if error message occurred select other guest and enable toggle
    Then User navigate to menu "Guest Services" service
    And User open service "Service Request Overview" item
    And User search with guest cabin number
    And User save the "Task Code" in housekeeping
    And User verify the task code is "A/C Vents"
#
    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |


  @HotelMaintenance @HM30 @Smoke @NotDependent  @C2968080
  Scenario Outline:Verify the display of error message if required details are missing or if the ticket cannot be created
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    Then User click on "Fast Creation" action box
    And User click on request and don't fill
    And User add location in where and don't fill
    Then User enter text in detail "test comment"
    And User save the mandatory field error message for request
    And User Verify the error text as "Request is required" for what
    And User save the mandatory field error message for location
    And User Verify the error text as "Location is required" for where

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM31 @Smoke @NotDependent @Regression @C1326987 @OCI @OCIHM
  Scenario Outline:Validate that Otalio automatically adds priority and operational position(s) when issue type is selected in 'Fast Creation'
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    Then User click on "Fast Creation" action box
    And User select request as "A/C Diffuser Dirty"
    And User add location in where
    #And User add cabin location in where
    Then User enter text in detail field as "test comment"
    And User click on "Create"
    And user again got error message then click on create
    And User open service "<ServiceName>" item
    #And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "A/C Diffuser Dirty"
    Then User verify the priority is updated automatically
    Then User click edit icon on request
    Then User verify the team field is updated automatically


    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM32 @Smoke @C1334171 @VoyagerHM @OCI @OCIHM
  Scenario Outline: Validate that the user is able to close the new work order request for the guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Open" status on request overview
    And User click on "Open" status and select "<Status>"
    Then User enter comments "<Comments>" and click on "Confirm" button
    And User open hidden filters
    Then User clear status of the request overview
    And User select "<Status>" status on request overview
    And User verify the status is "<Status>"

    Examples:
      | Service     | ServiceName          | Status      | Comments  |
      | Maintenance | Maintenance Overview | Closed      | Completed |
      | Maintenance | Maintenance Overview | In Progress | Pending   |
      | Maintenance | Maintenance Overview | Open        | New       |

  @HotelMaintenance @HM33 @C4347749
  Scenario Outline:Validate that user can sort by priority and status in Maintenance Overview
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Hotel Maintenance | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |

    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User select "Priority" option from the add more filter
    And User select the priority filter as "4 - High Priority"
    And User verify the priority list is updated to "4 - High Priority"
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Open" status on request overview
    And User click on "Open" status and select "<Status>"
    Then User enter comments "<Comments>" and click on "Confirm" button
    And User open hidden filters
    Then User clear status of the request overview
    And User select "<Status>" status on request overview
    And User verify the status is "<Status>"

    Examples:
      | Service     | ServiceName          | Status      | Comments  |
      | Maintenance | Maintenance Overview | Closed      | Completed |
      | Maintenance | Maintenance Overview | In Progress | Pending   |
      | Maintenance | Maintenance Overview | Open        | New       |


  @HotelMaintenance @HM34 @C3642538
  Scenario Outline:Validate that the user can filter out the tickets based on their Operational Departments
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Hotel Maintenance | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |

    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User select the operation department as "ENGINE"
    And User verify the operation department list is updated to "ENGINE"

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM35 @C3642546
  Scenario Outline:Validate that user is able to change team for an existing ticket
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Hotel Maintenance | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |

    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User select the operation department as "ENGINE"
    Then User click edit icon on request overview
    Then User move to the "Who" field
    And User change the team field and save the team
    And User click on "Save"
    And User Verify The Team field is updated

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM36 @NotDependent @C4347752
  Scenario Outline:Validate that requests display correctly when filtering by multiple assignees
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Hotel Maintenance | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "<RequestName>"
    Then User click on assignee icon
#    And User open hidden filters
#    Then User clear Operation Department filter
#    And User clear the operation position
    And User select a assignee
    And User save the assignee name
    And User click on "Select"
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click on "Add"
    #And Verify if error message occurred select other guest
    And user again got error message then click on add
    And User open hidden filters
    And User Click on Assignee filter
    And User save the "Assignee"
    And User verify the assignee is as saved

    Examples:
      | Service     | ServiceName          | RequestName              |
      | Maintenance | Maintenance Overview | A/C Diffuser to Re-Paint |

  @HotelMaintenance @HM37 @C3642548
  Scenario Outline:Validate that user is able to raise new guest service request using an existing maintenance request
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Hotel Maintenance | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "A/C Diffuser Dirty"
#    And User enable the toggle "inform-housekeeping"
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click service request checkbox
    And User select service rquest as "A/C Diffuser Dirty"
    And User click on "Add"
    #And Verify if error message occurred select other guest and enable toggle
    Then User navigate to menu "Guest Services" service
    And User open service "Service Request Overview" item
    And User search with guest cabin number
    And User save the "Task Code" in housekeeping
    And User verify the task code is "A/C Vents"
#
    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @HotelMaintenance @HM38 @C1326981
  Scenario Outline: Validate that user can assign maintenance request to the logger
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Hotel Maintenance | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open Maintenance overview
    And User click on add icon button
    And User select request as "<RequestName>"
    Then User click on reported by icon
    And User click on crew list
    And User select a crew
    And User delete the selected guest
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    And User click on "Add"
    And user again got error message then click on add
   # And User type cabin number in location filter
    And User save the "Request"
    And User verify the request is "<RequestName>"

    Examples:
      | Service     | ServiceName          | RequestName           |
      | Maintenance | Maintenance Overview |  A/C Diffuser Dirty  |

  @HotelMaintenance @HM39 @C1334174
  Scenario Outline: Validate that user can view maintenance request code setup and details
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Hotel Maintenance | Welcome2024! | Guest Services |
      |O&R| Housekeeping | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User click on "Maintenance Request Codes"
    And User click  "HK - A/C Diffuser Dirty" code link



    Examples:
      | Service     | ServiceName          | RequestName           |
      | Maintenance | Maintenance Overview |  A/C Diffuser Dirty  |


