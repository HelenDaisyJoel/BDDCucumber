Feature: Guest Service Request

  @GuestServiceRequest @Smoke @GSR1 @NotDependent @GSR @C1326913
  Scenario Outline:Validate to notify crew member of Guest Cabin Issues
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "XXX"
#    Then User save guest first name
#    Then User save guest full name
#    And User navigated to "<GuestFirstName>" profile page
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Service Requests" details
    Then User click on add icon
    And User select Category for special request
    Then User select the "Code" and select code
    Then User select the "Origin" and select origin
    Then User save all details in add service request page
    And User click on "Add"
    Then User verify the added service request details

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestServiceRequest @Smoke  @C1326914 @NotDependent @GSR @Sanity
  Scenario Outline: Validate that the user is able to follow up/Pending ticket for the guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User set date for service request
#    Then User get "Service Request" index and select last name "Ashley"
    And User click on service request
    And User click on "Add new Resolution"
    Then User select the category "Action" on resolution
    Then User change the resolutions code as "014 - 04 Gift Order Renewal Of Vows Package"
    And  User click on "Activate"
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open submenu "Resolutions" details
    Then User search for cabin number
    And  User Verify the resolution status

    Examples:
      | Service        | ServiceName              |
      | Guest Services | Service Request Overview |

  @GuestServiceRequest @Smoke @GSR2 @C1326916 @NotDependent @GSR @C2956697
  Scenario Outline: Validate that the user is able to create the new work order request for the guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    Then User click on "Fast Creation" action box
    And User click on "Advanced"
    Then User switch window to add new workorder
    And User select request as "Air Conditioning Not Working"
#    Then User select the "Priority" as "1 - Very Low Priority"
    Then User select the "Priority" and select port
    Then User click on reported by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on location
    And User type cabin number
    And User click on add symbol
    Then User set "Target Completion" date for add new workorder
    And  User set Target Completion date for add new workorder
    Then User click on "OK"
#    Then User select the "Team" as "Bar"
#    Then User select the "Team" and select port
#    And User select the location and click on "Add"
    And User click on "Add"
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on location filter and select location
    And User save the "Request"
    And User verify the request is "Air Conditioning Not Working"

    Examples:
      | Service     | ServiceName          |
      | Maintenance | Maintenance Overview |

  @GuestServiceRequest @Smoke @GSR3 @C1326915 @NotDependent @GSR
  Scenario Outline: Validate that the user is able to follow up/In progress the new work order request for the guest.
    Given Login page is displayed
    When Input credentials to login into nclh
      | Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Resolved" status on service request overview
    And User click on service request
    #And User click on service request link
    Then User select the status as "Closed" on resolution
    And User save the update status details
    Then User verify updated staus in service request

    Examples:
      | Service        | ServiceName              |
      | Guest Services | Service Request Overview |

  @GuestServiceRequest @Smoke @GSR3 @C1326917 @NotDependent @GSR
  Scenario Outline: Validate that the user is able to follow up/In progress the new work order request for the guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Pending" status on service request overview
    And User click on service request
    And User click on service request link
    Then User select the status as "Resolved" on resolution
    And User save the update status details
    Then User verify updated staus in service request

    Examples:
      | Service        | ServiceName              |
      | Guest Services | Service Request Overview |

  @GuestServiceRequest @Smoke @GSR3 @C1326920 @NotDependent @GSR
  Scenario Outline: Validate that the user is able to follow up/In progress the new work order request for the guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Closed" status on service request overview
    And User click on service request
    And User save the update status details
#    Then User verify updated staus in service request

    Examples:
      | Service        | ServiceName              |
      | Guest Services | Service Request Overview |

#  @GuestServiceRequest @Smoke @GSR3 @C1326917 @NotDependent @GSR
#  Scenario Outline: Validate that the user is able to follow up/In progress the new work order request for the guest.
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Guest Services | Welcome2024! | Guest Services |
#    Then User navigate to menu "<Service>" service
#    And User open service "<ServiceName>" item
#    And User open hidden filters
#    Then User clear status of the request overview
#    And User select "Open" status on request overview
#    And User click on "Open" status and select "In Progress"
#    Then User enter comments "Progress" and click on "Confirm" button
#    And User open hidden filters
#    Then User clear status of the request overview
#    And User select "In Progress" status on request overview
#    And User verify the status is "IN PROGRESS"
#
#    Examples:
#      | Service     | ServiceName          |
#      | Maintenance | Maintenance Overview |

#  @GuestServiceRequest @Smoke @GSR4 @C1326919 @NotDependent @GSR @Bug @OPM-982
#  Scenario Outline: Validate that Automatic Assignment of Ticket to Correct Group Based on Issue Type
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Guest Services | Welcome2024! | Guest Service |
#    Then User navigate to menu "<Service>" service
#    And User click on "My assigned Requests"
#    Then  User select my assigned request as priority
#    Then User verify the request overview table
#      | Number | Request | Request Detail | Location | Team | Assignee | Priority | Created | Completion |

#    Examples:
#      | Service     |
#      | Maintenance |

  @GuestServiceRequest @Smoke @GSR5 @GSR @NotDependent @C1326918 @C1326921 @C1326922 @C1326923 @C1334171 @C1334167  @Regression   @C2968097
  Scenario Outline: Validate that the user is able to close the new work order request for the guest
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Services |
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
      | Service        | ServiceName              | Status      | Comments  |
      | Guest Services | Service Request Overview | Closed      | Completed |
      | Maintenance    | Maintenance Overview     | In Progress | Pending   |
      | Maintenance    | Maintenance Overview     | Open        | New       |

  @GuestServiceRequest @Smoke @GSR6  @C1326925 @NotDependent @GSR  @C1326997 @C2968093
  Scenario Outline: Validate that ticket history and view that all actions on the ticket have been completed
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "<Status>" status on request overview
    Then User click edit icon on request overview
    And User click "History" on request overview
    And User verify that ticket history and view that all actions on the ticket have been completed

    Examples:
      | Service     | ServiceName          | Status |
      | Maintenance | Maintenance Overview | Closed |

  @GuestServiceRequest @Smoke @GSR10 @C1326924 @NotDependent @GSR
  Scenario Outline: Verify that the guest services user must view ticket and updates information if permissions allow
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User clear status of the request overview
    And User select "Open" status on request overview
    Then User click on request number on request overview
    And User click on "Open" status and select "<Status>" on request overview
    Then User click on "Save"
    And User open hidden filters
    Then User clear status of the request overview
    And User select "In Progress" status on request overview
    And User verify the status on request overview

    Examples:
      | Service     | ServiceName          | Status      |
      | Maintenance | Maintenance Overview | In Progress |

  @GuestServiceRequest @Smoke @GSR11 @GSR @C3061055 @C3061056 @C3061057 @C3061058 @C3061059 @C3061060 @C3061061 @C3061062 @C3061063 @C3061064 @C3061065 @C3061066 @C1326927 @C3147667
  Scenario Outline: Validate that the user is able add resolution in the newly created service request
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Service Requests" details
    Then User click on add icon button in service request tab
    And  user click on "<TabName>" in add service request
    Then User select the "Code" and select code
    Then User select the "Origin" and select origin
    Then User save all details in add service request page
    And User click on "Add"
    Then User verify the added service request details
    And User click on "Add new Resolution"
    Then User select the category "Action" on resolution
    Then User change the resolutions code as "014 - 04 Gift Order Renewal Of Vows Package"
    And  User click on "Activate"
    Then User save the added new resolution details
    And User click on added new resolution
    Then User verify the added new resolution details
    Then User select the status as "Completed" on resolution
    Then User type the comments as "Test"
#    And User save the update status details
#    Then User verify updated status in resolution
#    And User click on "Add new Maintenance Request"
#    Then User select the "Request" as "Air Conditioning Not Working"
#    And  User click on "Activate"
#    Then User save the added new maintenance request details
#    And User click on added new maintenance request
#    Then User verify the added new maintenance request details
##    Then User select the status as "Closed" on maintenance request
#    Then User select the statusId as "Closed" on maintenance request
#    And  User click on "Save"
#    Then User save the update status details in maintenance request
#    And User verify updated maintenance request

    Examples:
      | Service        | ServiceName  | GuestFirstName   | TabName      |
      | Guest Services | Guest Search | DynamicFirstName | Housekeeping |

  @GuestServiceRequest @Smoke @GSR12 @GSR @C3061045 @C3061046 @C3061047 @C3061048 @C3061049 @C3061050 @C3061051 @C3061052 @C3061053 @C3061054
  Scenario Outline: Validate that user is able to create a new request for complaints from service request tab/section
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Service Requests" details
    Then User click on add icon button in service request tab
    And  user click on "<TabName>" in add service request
    Then User select the "Code" and select code
    Then User select the "Origin" and select origin
    Then User save all details in add service request page
    And User click on "Add"
    Then User verify the added service request details
    And User enable  toggle "Obsolete"
    Then User verify the Obsolete toggle button is on

    Examples:
      | Service        | ServiceName  | GuestFirstName   | TabName      |
#      | Guest Service | Guest Search | DynamicFirstName | Complaints   |
      | Guest Services | Guest Search | DynamicFirstName | Housekeeping |
#      | Guest Service | Guest Search | DynamicFirstName | Requests     |
#      | Guest Service | Guest Search | DynamicFirstName | Hotel        |

  @GuestServiceRequest @Smoke @GSR13 @C3661143 @NotDependent @GSR
  Scenario Outline: Validate that the user can pull an extract report via the service request overview page
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    Then User set date for service request
    And User export ticket data
    And User verify that file is downloaded

    Examples:
      | Service        | ServiceName              |
      | Guest Services | Service Request Overview |

