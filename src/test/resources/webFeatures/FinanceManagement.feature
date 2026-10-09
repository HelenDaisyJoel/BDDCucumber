Feature: Finance Management

  @FinanceManagement @FM1 @Smoke @C1542674 @NCL @OCI @NCLH
  Scenario Outline: Validate that user is able to view the failed reason for the failed posting guests
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Financial Management |
      |O&R  | 2nd Purser Pax | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    Then User clear status of Pending Postings Overview
    And User select "Failed To Post" status on Pending Postings Overview
    And User get text from the status
    Then User verify the filter guest by status as "Failed To Post"

    Examples:
      | Service              | ServiceName               |
      | Financial Management | Pending Postings Overview |

  @FinanceManagement @FM2 @Smoke @C2956494 @NCL @OCI @NCLH
  Scenario Outline: Validate that user is able to view pending postings overview all statuses (unposted/posted)
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Financial Management |
      |O&R  | 2nd Purser Pax | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    Then User clear status of Pending Postings Overview
    And User select "Posted" status on Pending Postings Overview
    And User get text from the status
    Then User verify the filter guest by status as "Posted"

    Examples:
      | Service              | ServiceName               |
      | Financial Management | Pending Postings Overview |

  @FinanceManagement @FM3 @Smoke @OCI @C2956497
  Scenario Outline: Validate that user is able to view the guest postings on respective date by using posting date filter
#    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Financial Management |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    Then User clear status of Pending Postings Overview
    And User select posting date in "Date Range"
    Then User enter "startDate" date range
    Then User enter "endDate" date range
    And Validate that user able to filter by posting date

    Examples:
      | Service              | ServiceName               |
      | Financial Management | Pending Postings Overview |

  @FinanceManagement @FM4 @Smoke @C4305886 @NCL @OCI @NCLH
  Scenario Outline: Validate That User Is Able to View Failed Postings by Status and with corresponding Reasons
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Financial Management |
      |O&R  | 2nd Purser Pax | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    And User click on "<ColumnName>" column of the system account
    Then Validate that user is able to sort all the system accounts by the column.

    Examples:
      | Service              | ServiceName           | ColumnName      |
      | Financial Management | System Account Search | Description     |

  @FinanceManagement @FM5 @Smoke @C1486895 @NCL
  Scenario Outline: Validate that user is able to add routing for all the charges by a crew member to a guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Crew Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Crew Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
#    And User clear reservation status of the crew
#    Then User select "Leaving Today" option from the filter
    And User search guest with "HKUser"
    Then User save crew first name
    Then User save crew full name on crew page
    And User click "<CrewFirstName>" navigate to profile page
    And User enable  toggle "Posting Status"
    Then User verify the Posting Status toggle button is on
    And User open submenu "Financial Information" details
    Then User click on "Routing" tab and click on "ADD ROUTING" on financial information
    Then User select "Is Paying For" radio button
    And User click on "ADD"
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User select "All charges" radio button
    And User enable  toggle "Include Current Postings"
    And User enable  toggle "Include Current Payments"
    Then User click on "save & view profile"
    And User click on "Yes"
    Then User verify the add routing
#    And  User click on cross button
#    And User click on "Delete"
#    Then Validate the added routing is deleted

    Examples:
      | Service       | ServiceName | CrewFirstName    |
      | Crew Services | Crew Search | DynamicFirstName |

  @FinanceManagement @FM6 @Smoke @C1486922 @NCL
  Scenario Outline: Validate that the user is able to view all voyage details by date and port
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Financial Management |
      |O&R  | 2nd Purser Pax | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    And User verify the current and next voyage details

    Examples:
      | Service              | ServiceName   |
      | Financial Management | System Voyage |

  @FinanceManagement @Smoke @FM7 @C1497569 @NCL
  Scenario Outline: Validate that user is able to account posting status for the guest financial operations in bulk
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | 2nd Purser Pax | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Click on three dots and select Bulk Operation on guest page
    Then Unselect all crew member and select <UserCount> crew members
    And User click on Next button
    When User select radio button "Apply bulk Financial Actions"
    And User click on Next button
    And User click on "<OperationDetails>" checkbox
    Then User select the "<EnableDisable>" account postings operation details
    Then User type the comment "Test"
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User navigate to menu "Financial Management" service
    And User open service "Bulk Log" item
    Then User switch to new tab
    Then  User validate the finance bulk log

    Examples:
      | Service        | ServiceName  | UserCount | OperationDetails       | EnableDisable   |
      | Guest Services | Guest Search | 2         | Account posting status | Enable postings |

  @FinanceManagement @Smoke @FM8 @C1497570 @NCL
  Scenario Outline: Validate that user is able to add deposit for required guest of financial operations in bulk
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | 2nd Purser Pax | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Click on three dots and select Bulk Operation on guest page
    Then Unselect all crew member and select <UserCount> crew members
    And User click on Next button
    When User select radio button "Apply bulk Financial Actions"
    And User click on Next button
    And User click on "Add Deposit" checkbox
#    Then User select the "Sub Departments" as "990001 - Cash Payment"
   # Then User select the "Sub Departments" as "Rounding Credit"
    Then User select the "Sub Departments" and select value
    And  User type amount "10"
    Then User type the payment comment "Test"
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User navigate to menu "Financial Management" service
    And User open service "Bulk Log" item
    Then User switch to new tab
    Then  User validate the finance bulk log

    Examples:
      | Service        | ServiceName  | UserCount |
      | Guest Services | Guest Search | 2         |

  @FinanceManagement @Smoke @FM9 @NCL @C1497571
  Scenario Outline: Validate that user is able to account posting status for the guest financial operations in bulk
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Crew Services |
      |O&R  | 2nd Purser Pax | Welcome2024! | Crew Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Click on three dots and select Bulk Operation on guest page
    Then Unselect all crew member and select <UserCount> crew members
    And User click on Next button
    When User select radio button "Apply bulk Financial Actions"
    And User click on Next button
    And User click on "<OperationDetails>" checkbox
    Then User select the "<EnableDisable>" account postings operation details
    Then User type the comment "Test"
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User navigate to menu "Financial Management" service
    And User open service "Bulk Log" item
    Then User switch to new tab
    Then  User validate the finance bulk log

    Examples:
      | Service        | ServiceName  | UserCount | OperationDetails       | EnableDisable   |
      | Crew Services | Crew Search  | 2         | Account posting status | Enable postings  |

  @FinanceManagement @FM10 @C1497573 @NCL @Smoke
  Scenario Outline: Validate that Application is able to edit the added departments (main department, sub department) in the disallowed departments.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And Navigates to onboard guest profile
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page
#    Then User save the guest details
    And User open submenu "Financial Information" details
    Then User click on "Disallowed Departments" tab and click on "ADD DEPARTMENTS" in financial information
    And User click "Pre-Selection Group" on disallowed departments
    And User select the pre selection group item
    And User click "Main Department" on disallowed departments
    Then User select main department item
    And User click "Sub Department" on disallowed departments
    Then User select sub department item
    And User click on "Save"
    Then User verify the disallowed departments details
    And User click on "Edit Departments"
    And User click "Main Department" on disallowed departments
    Then User select main department item
    And User click "Sub Department" on disallowed departments
    Then User select sub department item
    And User click on "Save"
    Then User verify the disallowed departments details

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @FM11 @C1497574 @NCL @Smoke
  Scenario Outline: Validate that Application is able to edit the added departments (main department, sub department) in the disallowed departments.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And Navigates to onboard guest profile
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page
#    Then User save the guest details
    And User open submenu "Financial Information" details
    Then User click on "Disallowed Departments" tab and click on "ADD DEPARTMENTS" in financial information
    And User click "Pre-Selection Group" on disallowed departments
    And User select the pre selection group item
    And User click "Main Department" on disallowed departments
    Then User select main department item
    And User click "Sub Department" on disallowed departments
    Then User select sub department item
    And User click on "Save"
    Then User verify the disallowed departments details

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @FM12 @Smoke @C1497575 @NCL
  Scenario Outline: Validate that end of sailing disembarkation date, payment method, print or email the account balances to the guests
#    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
#    And User search guest with "GuestUser"
    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page
    And Navigates to onboard guest profile
    And User open submenu "Account Statement" details
    And User click on "Print Statement"
    Then User select the Account Statement Type and select port
    And User click on "Confirm"
    And User switch to pdf page and get pdf content
    Then User verify guest full name present in the PDF content

    Examples:
      | Service       | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @FM13 @C1518438 @NCL @Smoke
  Scenario Outline: Validate that user is able to perform settlements and refunds in real time including multiple settlement types.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page
    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And Navigates to onboard guest profile
    Then User verify the payment method on account statement section

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @RetailPointOfSale @POS @Smoke @C2564170 @NCL @OCI @@RPOSAll
  Scenario: Ability to split transaction into multiple payments by using Split by Consumer button
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    And User Search two guests and select having min sufficient funds greater than 0
#    And User save selected guestNames list
    And User save the selected guestNames from the list
    And User click on "CREATE CHECK" button
    And User select "1st" guest for selecting product
    And User click on label "EFFY"
#    And User select item "Effy Jewelry" for adding tip
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    And User select "2nd" guest for selecting product
    And User click on label "EFFY"
#    And User select item "Effy JewelryE" for adding tip
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    And User open link "payment-pos"
    And User click on split Check By Consumer
    And User perform signature and confirm "POST" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify guest names displayed check confirmation page
    And User save the check number for the selected guest list
    And User logout from pos

    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    And User search for the guest and open the profile
    And User click on Account statement details
    Then User verify transaction is posted and added to guest folio


  @FinanceManagement @FM50 @NotDependent @C2956491 @Smoke @C2956490 @C2956488 @C2956489 @C2956492
  Scenario Outline: Validate that user is able to Display all guest charges and payments on one folio
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User verify the reservation status

    Examples:
      | Service        | ServiceName  |
      | Guest Services | Guest Search |

  @FinanceManagement @FM1 @NotDependent @C1707494 @Smoke @C1088355 @C1050134 @C1529463 @C1518540  @C1553869 @C1518539 @C1553876 @C2956560
  Scenario Outline: Validate that user is able to Display all guest charges and payments on one folio
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User verify the reservation status
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page

    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And Navigates to onboard guest profile
#    Then  User save the guest details
#    Then  User verify the  Package counts, Shore Excursion counts, Spa counts
    And User enable  toggle "Posting Status"
    Then User verify the Posting Status toggle button is on
    And User enable  toggle "Exclude from Bulk Settlement"
    Then User verify the Exclude from Bulk Settlement toggle button is on
    And User open submenu "Cruise Information" details
#    Then User verify the view all voyage details by date and port
    And User open submenu "Account Statement" details
    And User navigate to folio "Folio 1"
    Then User click on "Add Deposit"
    And User select financial department "990001 - Cash Payment"
    And User type the amount
    And User save the amount
    And User click on "Add"
    And User verify the folio detail

    Examples:
      | Service        | ServiceName  |
      | Guest Services | Guest Search |

  @FinanceManagement @FM4 @Smoke @C2956496
  Scenario Outline: Validate That User Is Able to View Failed Postings by Status and with corresponding Reasons
    Given Login page is displayed
    When Input credentials to login into nclh portal
    |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    |O&R  | 2nd Purser Pax | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    Then User clear status of Pending Postings Overview
    And User select "Failed To Post" status on Pending Postings Overview
#    And User get text from the status
#    Then User verify the filter guest by status as "Failed To Post"

    Examples:
      | Service              | ServiceName               |
      | Financial Management | Pending Postings Overview |


  @FinanceManagement @NotDependent @Regression @Smoke
  Scenario Outline: Validate that user is able to split and transfer charges to other cabins/guests
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
#    Then User verify the account statement
#    Then User save the guest details
    And User enable  toggle "Posting Status"
    Then User verify the Posting Status toggle button is on
    And User open submenu "Financial Information" details
    Then User click on "Routing" tab and click on "ADD ROUTING" on financial information
    Then User select "Is Paying For" radio button
    And User click on "ADD"
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User select "Selected charges" radio button
    And User click "Pre-Selection Group" on disallowed departments
    And User select the pre selection group item
    And User click "Main Department" on disallowed departments
    Then User select main department item
    And User click "Sub Department" on disallowed departments
    Then User select sub department item
    And User enable  toggle "Include Current Postings"
    And User enable  toggle "Include Current Payments"
    Then User click on "save & view profile"
    And User click on "Yes"
    Then User verify the add routing
    And  User click on cross button
    And User click on "Delete"
    Then Validate the added routing is deleted

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @Smoke @FM8 @C1518542
  Scenario Outline: Validate that user is able to perform disallow departments for required guest of financial operations in bulk
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Click on three dots and select Bulk Operation on guest page
    Then Unselect all crew member and select <UserCount> crew members
    And User click on Next button
    When User select radio button "Apply bulk Financial Actions"
    And User click on Next button
    And User click on "Disallowed departments" checkbox
    And User add "CC" on Available Pre-Selection Groups
    Then User type the disallowed departments comment "Test"
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User navigate to menu "Financial Management" service
    And User open service "Bulk Log" item
    Then User switch to new tab
    Then  User validate the finance bulk log

    Examples:
      | Service        | ServiceName  | UserCount |
      | Guest Services | Guest Search | 2         |

  @FinanceManagement @Smoke @C1518549 @Regression @FM9
  Scenario Outline: Validate that user is able to add discount by template for required guest of financial operations in bulk
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Click on three dots and select Bulk Operation on guest page
    Then Unselect all crew member and select <UserCount> crew members
    And User click on Next button
    When User select radio button "Apply bulk Financial Actions"
    And User click on Next button
    And User click on "Add Discount by Template" checkbox
    Then User select the "Discount Template" and select value
    Then User type the discount templates comment "Test"
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User navigate to menu "Financial Management" service
    And User open service "Bulk Log" item
    Then User switch to new tab
    Then  User validate the finance bulk log

    Examples:
      | Service       | ServiceName  | UserCount |
      | Guest Services | Guest Search | 2         |

  @FinanceManagement @Smoke @FM10 @C1518538 @Regression
  Scenario Outline: Validate that user is able to add routing for all the charges to the guest by crew member
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Click on three dots and select Bulk Operation on guest page
    Then Unselect all crew member and select <UserCount> crew members
    And User click on Next button
    When User select radio button "Apply bulk Financial Actions"
    And User click on Next button
    And User click on "Add Routing" checkbox
    Then User click add user icon on payer account
    And User select a guest
    And User save the guest name
    And User click on "Select"
    And User select "Route All charges" radio button
    Then User select route to folio as "Folio 1"
    Then User type the routing comment "Test"
    And User click on Confirm button
    Then User save the finance bulk log
    Then User navigate to menu "Financial Management" service
    And User open service "Bulk Log" item
    Then User switch to new tab
    Then  User validate the finance bulk log

    Examples:
      | Service       | ServiceName  | UserCount |
      | Guest Services | Guest Search | 2         |

  @FinanceManagement @FM11 @NotDependent @Smoke @C1326859 @C1518536 @Regression  @C3515427
  Scenario Outline: Validate that checks in guest that did not complete OCN
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Service |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
#    Then User save the guest details
    And User open submenu "Financial Information" details
    Then User click on "Routing" tab and click on "ADD ROUTING" on financial information
    Then User select "<PayType>" radio button
    And User click on "ADD"
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on "save"
    And User click on "Yes"
    Then User verify the account statement as "<Status>"
    Then User verify the add routing
    And  User click on cross button
    And User click on "Delete"
    Then Validate the added routing is deleted

    Examples:
      | Service       | ServiceName  | GuestFirstName   | PayType       | Status |
      | Guest Service | Guest Search | DynamicFirstName | Is Paying For | Payer  |
#      | Guest Service | Guest Search | DynamicFirstName | Is Paid By    | Buyer  |

  @FinanceManagement @FM14 @NotDependent @Smoke @C1518545 @C1518546 @Regression
  Scenario Outline: Validate if the credit limit bar is getting updated when adding any limit in the credit limit section
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Financial Information" details
    Then User click on "Credit Limits" tab and click on "Add Limit"
    And User click on "Add Limit"
    Then User switch to new tab
    And User Enter "<LimitAmount>"
    Then User click on "Save"
    Then User validate the "<LimitAmount>" on credit limits
    And User click on "Edit Limit"
    Then User switch to new tab
    And User Enter "<EditLimitAmount>"
    Then User click on "Save"
    Then User validate the "<EditLimitAmount>" on credit limits tab

    Examples:
      | Service        | ServiceName  | GuestFirstName   | LimitAmount | EditLimitAmount |
      | Guest Services | Guest Search | DynamicFirstName | 150.00      | 200.00          |

  @FinanceManagement @FM15 @NotDependent @Smoke @C2396067 @Regression @C2396068  @C1050135 @C1050136
  Scenario Outline: Validate that user is able to view the updated account statement for a guest.
#    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |

    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
#    And User search guest with "GuestUser"
    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page
    And Navigates to onboard guest profile
    And User open submenu "Account Statement" details
    And User click on "Print Statement"
    Then User select the Account Statement Type and select port
    And User click on "Confirm"
    And User switch to pdf page and get pdf content
#    Then User verify guest full name present in the PDF content

    Examples:
      | Service       | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |


  @FinanceManagement @FM16 @NotDependent @Smoke @C1498881 @Regression @C1518543 @C1518541 @C1518544
  Scenario Outline: Validate that service charge application for selected guests
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User enable  toggle "Prepaid Service Charge"
    Then User verify the Exclude from Bulk Settlement toggle button is on
    And User enable  toggle "Exclude from Bulk Settlement"
    Then User verify the Exclude from Bulk Settlement toggle button is on
    And User enable  toggle "Paperless Invoice"
    Then User verify the Paperless Invoice toggle button is on
    And User enable  toggle "Service Charge Exempted"
    Then User verify the Service Charge Exempted toggle button is on

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @Smoke @C1518550  @Regression @FM36
  Scenario Outline: Validate that user is able to perform credit limit templates for required guest of financial operations in bulk
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Click on three dots and select Bulk Operation on guest page
    Then Unselect all crew member and select <UserCount> crew members
    And User click on Next button
    When User select radio button "Apply bulk Financial Actions"
    And User click on Next button
    And User click on "Add Onboard Credit Template" checkbox
#    Then User select the "Category" as "Gifts or Promotions"
#    Then User select the "Category" and select value
#    Then User select the "Onboard Credit template" and select value
#    Then User type the onboard credit comment as "Test"
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User navigate to menu "Financial Management" service
    And User open service "Bulk Log" item
    Then User switch to new tab
    Then  User validate the finance bulk log

    Examples:
      | Service        | ServiceName  | UserCount |
      | Guest Services | Guest Search | 2         |

  @FinanceManagement @Smoke @FM18 @C1529398  @Regression
  Scenario Outline: Validate that user is able to add postings for required guest of financial operations in bulk
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Click on three dots and select Bulk Operation on guest page
    Then Unselect all crew member and select <UserCount> crew members
    And User click on Next button
    When User select radio button "Apply bulk Financial Actions"
    And User click on Next button
    And User click on "Add Posting" checkbox
    When User select radio button "Post Articles"
    Then User select the "Revenue Center" as "LN1-Housekeeping" on posting
    Then User select the "Request" as "Air Conditioning Not Working"
    Then User select the "Article" and select value
    Then User type the add posting comment as "Test"
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User navigate to menu "Financial Management" service
    And User open service "Bulk Log" item
    Then User switch to new tab
    Then  User validate the finance bulk log

    Examples:
      | Service       | ServiceName  | UserCount |
      | Guest Services | Guest Search | 2         |

  @FinanceManagement @FM20 @NotDependent @Regression @C1497572 @C1497574 @Smoke
  Scenario Outline: Validate that Application is able to add the departments (main department, sub department) to the disallowed departments.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |

    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And Navigates to onboard guest profile
#
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page
#    Then User save the guest details
    And User open submenu "Financial Information" details
    Then User click on "Disallowed Departments" tab and click on "ADD DEPARTMENTS" in financial information
    And User click "Pre-Selection Group" on disallowed departments
    And User select the pre selection group item
    And User click "Main Department" on disallowed departments
    Then User select main department item
    And User click "Sub Department" on disallowed departments
    Then User select sub department item
    And User click on "Save"
    Then User verify the disallowed departments details
    And User click on "EDIT DEPARTMENTS"
    And User click "Main Department" on disallowed departments
    Then User select main department item
    And User click "Sub Department" on disallowed departments
    Then User select sub department item
    And User click on "Save"
    Then User verify the disallowed departments details
    And User click on "DELETE"
    And User click on "Delete"
    Then User validate the add disallowed departments is delete

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @FM21 @NotDependent @Regression @C1518547 @Smoke
  Scenario Outline: Validate that user is able to perform credit limit templates for required guest of financial operations in bulk
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And Click on three dots and select Bulk Operation on guest page
    Then Unselect all crew member and select <UserCount> crew members
    And User click on Next button
    When User select radio button "Apply bulk Financial Actions"
    And User click on Next button
    And User click on "Credit limit templates" checkbox
    Then User select the "Credit Limit template" and select value
    Then User type the credit limit comment as "Test"
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User save the finance bulk log
    Then User navigate to menu "Financial Management" service
    And User open service "Bulk Log" item
    Then User switch to new tab
    Then  User validate the finance bulk log

    Examples:
      | Service       | ServiceName  | UserCount |
      | Guest Services | Guest Search | 2         |


#  @FinanceManagement @FM23 @NotDependent @Regression @C1518769 @Smoke @C1518536  @C1483378  @C3515427
  #@FinanceManagement @FM23 @NotDependent @Regression @Smoke @C1518536  @C351542
  @FinanceManagement @FM23 @NotDependent @Regression @Smoke @C1483378

  Scenario Outline: Validate that the Add routing to guest user through the Finance user
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    And User search guest with "GuestUser"
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
#    Then User verify the account statement
#    Then User save the guest details
    And User enable  toggle "Posting Status"
    Then User verify the Posting Status toggle button is on
    And User open submenu "Financial Information" details
    Then User click on "Routing" tab and click on "ADD ROUTING" on financial information
    Then User select "<PayType>" radio button
    And User click on "ADD"
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on "save" button on routing
    And User click on "Yes"
    Then User switch to new tab
    And User click on "GO BACK"
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search for guest and open guest profile page
#    And User open submenu "Financial Information" details
#    Then User verify the account statement as "<Status>"
    And User open submenu "Financial Information" details
    Then User verify the add routing
    And  User click on cross button
    And User click on "Delete"
    Then Validate the added routing is deleted

    Examples:
      | Service        | ServiceName  | GuestFirstName   | PayType    | Status |
#      | Guest Service | Guest Search | DynamicFirstName | Is Paying For | Payer  |
      | Guest Services | Guest Search | DynamicFirstName | Is Paid By | Buyer  |


  @FinanceManagement @FM24 @NotDependent @Regression @C1542772 @Smoke
  Scenario Outline: Validate That User Is Able to Add a Comment to a Payment or Manual Posting.
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Financial Information" details
    And User click on "Payment Methods" tab
    Then User type the payment method comment as "Test"
    And User click on "Save"
#    Then User get text from the comment box image
#    And User verify the comment as "Test"

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @FM25 @NotDependent @Regression @C1529460 @C1529399 @C1542770 @C1529462 @C1529461 @Smoke @C1542771 @C1553872 @C1671399 @C2956498 @C2956512 @C2956513 @C2956514
  Scenario Outline: Validate that user is able to see the  following details per guest posting in the cruise information deliverables Tab(Type, Code, Item, Reason, Responsible Department)
#    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | 2nd Purser Pax | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    And User search guest with "GuestUser"
    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And Navigates to onboard guest profile
    And User open submenu "Cruise Information" details
    And User click on "Deliverables" tab
    Then User verify deliverables table
      | Type | Code | Item | Reason | Responsible Department | Posting Status | Posting Status Comment | Prepaid |
#    Then User verify the posting status on deliverables tab
    And User click on three button and select "Add New Amenity"
    Then User add new amenity details
    And User click on "Add"
    Then User navigate to menu "Financial Management" service
    Then User switch to new tab
    And User navigated to module "Pending Postings Overview"
    Then User switch to new tab
    Then User clear status of Pending Postings Overview
    And User search for guest in pending posting overview
    Then User select the amenity checkbox and click on three dot button select the "Post"
    And User click on "Confirm"
#    Then User verify the status as posted

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @FM28 @NotDependent @Regression @C1579861 @Smoke
  Scenario Outline: Validate that application is able to indicate the guest as account payer when available in account statement.
#    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | 2nd Purser Pax | Welcome2024! | Financial Management |

    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User select "Payment Type" option from the add more filter
    And User select "Visa" from payment type
    And User search for a checked in guest
#    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page
    And Navigates to onboard guest profile
    And User open submenu "Financial Information" details
    And User click on "Payment Methods" tab
    Then User click on checkbox and select the payment method
    Then User type the payment method comment as "Test"
    And User click on "Save"
#    Then User verify the selected payment method

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @FM29 @NotDependent @Regression @C1566433 @C1542578  @C1518439 @C1518440 @Smoke
  Scenario Outline: Validate that user is able to perform settlements and refunds in real time including multiple settlement types.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page
    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And Navigates to onboard guest profile
    Then User verify the payment method on account statement section
    And User open submenu "Account Statement" details
    And User navigate to folio "Folio 1"
    Then User click on "Add Deposit"
    And User select financial department "990001 - Cash Payment"
    And User type the amount
    And User save the amount
    And User click on "Add"
    Then User select one or more <Account> on account statement
    And User click on "Settle Account"
#    And User select the "Financial Department" as "Cash Payment"
#    And User select financial department "990001 - Cash Payment"
#    And User type the amount settle amount
    And User click on "Add"
#    Then user verify the credit amount as "0.00"

    Examples:
      | Service       | ServiceName  | GuestFirstName   | Account |
      | Guest Services | Guest Search | DynamicFirstName | 1       |

  @FinanceManagement @NotDependent @Regression @Pending @FM30
  Scenario Outline: Validate that user is able to verify embarkation and disembarkation times for Same-Day cruises
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Service |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User add "Embarkation Date" filter to guest service page
    And User add "Disembarkation Date" filter to guest service page
    Then User clears embarkation date and disembarkation date on newly selected filters
    Then User save the embarkation date and disembarkation date
    And User enter embarkation date and disembarkation date
    Then User verify the embarkation date and disembarkation date

    Examples:
      | Service       | ServiceName  |
      | Guest Service | Guest Search |

  @FinanceManagement @FM31 @NotDependent @Regression @Smoke @C1579860
  Scenario Outline: Validate that user is able to split and transfer charges to other cabins/guests
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
#    And User navigated to user "GuestUser" profile page
    And User search for a checked in guest
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And Navigates to onboard guest profile
#    Then User verify the account statement
#    Then User save the guest details
    And User enable  toggle "Posting Status"
    Then User verify the Posting Status toggle button is on
    And User open submenu "Financial Information" details
    Then User click on "Routing" tab and click on "ADD ROUTING" on financial information
    Then User select "Is Paying For" radio button
    And User click on "ADD"
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User select "Selected charges" radio button
    And User click "Pre-Selection Group" on disallowed departments
    And User select the pre selection group item
    And User click "Main Department" on disallowed departments
    Then User select main department item
    And User click "Sub Department" on disallowed departments
    Then User select sub department item
    And User enable  toggle "Include Current Postings"
    And User enable  toggle "Include Current Payments"
    Then User click on "save & view profile"
    And User click on "Yes"
    Then User verify the add routing
    And  User click on cross button
    And User click on "Delete"
    Then Validate the added routing is deleted

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @FM32 @Smoke @C1566323 @C2956493
  Scenario Outline: Validate that user is able to filter guest by status for pre-paid and post at check-in
#    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    Then User clear status of Pending Postings Overview
    And User select posting date in "Date Range"
    Then User enter "startDate" date range
    Then User enter "endDate" date range
    And Validate that user able to filter by posting date

    Examples:
      | Service              | ServiceName               |
      | Financial Management | Pending Postings Overview |

  @FinanceManagement @C1566324 @FM33 @Smoke @C2956495
  Scenario Outline: Validate that user is able to filter guest by status
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    Then User clear status of Pending Postings Overview
    And User select "Unposted" status on Pending Postings Overview
    And User get text from status
    Then User verify the filter guest by status "Unposted"

    Examples:
      | Service              | ServiceName               |
      | Financial Management | Pending Postings Overview |

  @FinanceManagement @FM34 @NotDependent @Regression @Smoke @C1608183 @C1608184 @C1542577
  Scenario Outline: Validate that user is able to prioritize OBC packages within the Credit Erosion Priority View
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
#    Then User verify the account statement
#    Then User save the guest details
    And User open submenu "Financial Information" details
    Then User click on "Onboard Credits" tab and click on "Add Template" on financial information
#    Then User select the "Category" and select value
#    Then User select the "Non Refundable OnBoard Credits" and select value
    And User save the details in add onboard template
    Then User click on "Add"
    Then User verify created onboard credit template details

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @FinanceManagement @Smoke @Regression @FM35 @C1566435 @C1729185 @C1729284 @C1729287
  Scenario Outline: Validate that user is able to add new group
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service link "<ServiceItem>"
    And User add group on the Group screen
    And User save group code
    And User click on "Cancel"
    And User click on "Yes"
    And User add group on the Group screen
    And User save group code
    And User click on "Save"
    And User verify group is created
    Then User delete new created group
    Then User click on "Delete"
    Then User verify the created group is delete

    Examples:
      | Service        | ServiceItem    |
      | Guest Services | Group Overview |

  @FinanceManagement @FM40 @Smoke  @C2056007
  Scenario Outline: Validate That User Is Able to View Failed Postings by Status and with corresponding Reasons
    Given Login page is displayed
    When Input credentials to login into nclh
      | Financial Management | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    And User click on "<ColumnName>" column of the system account
    Then Validate that user is able to sort all the system accounts by the column.

    Examples:
      | Service              | ServiceName           | ColumnName      |
      | Financial Management | System Account Search | Code            |
      | Financial Management | System Account Search | Description     |
      | Financial Management | System Account Search | Current Balance |

  @FinanceManagement @FM40 @Smoke @C4305887
  Scenario Outline: Validate That User Is Able to View Failed Postings by Status and with corresponding Reasons
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      |O&R  | Assistant Financial Accountant | Welcome2024! | Financial Management |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User switch to new tab
    And User click on "<ColumnName>" column of the system account
    Then Validate that user is able to sort all the system accounts by the column.

    Examples:
      | Service              | ServiceName           | ColumnName      |
      | Financial Management | System Account Search | Current Balance |

  @FinanceManagement @Smoke @C2956525
  Scenario: Validate that user is able to create the check in POS for a guest.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "150" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on label "BAR"
    And User select item "LIQUOR" for adding tip
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    And User navigated to latest check on the list
    Then User verify the print receipt having guest name and comment

  @FinanceManagement @Smoke @C2956516
  Scenario: Validate that application is able to display all the charges and payments done by guests in one folio
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |O&R  | 2nd Purser Pax | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Prepaid" filter to guest service page
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Account Statement" details
    And User click on "Print Statement"
    Then User select the Account Statement Type and select port
    And User click on "Confirm"
    And User switch to pdf page and get pdf content
    Then User verify guest full name present in the PDF content

  @FinanceManagement @Smoke @C2670505
    Scenario: Validate that user is able to view all the categories using category filter for system accounts.
    Given Login page is displayed
    When Input credentials to login into nclh portal
#      | NCL  | Financial Management | Welcome2024! | Financial Management |
      | NCL  | Assistant Financial Accountant | Welcome2024! | Financial Management |
      | O&R  | Assistant Financial Accountant | Welcome2024! | Financial Management |
    Then User navigate to menu "Financial Management" service
    And User open service "System Account Search" item
    Then User switch to new tab
    And User selects filters "Category"
    And User select "Category" filter option to "Accounting Processes" in financial page
    Then User verifies that the "Category" column contains "Accounting Processes"

  @FinanceManagement @Smoke @C1707493
  Scenario: Validate that user is able to change method of payment between sailings for a guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
#      | NCL  | Financial Management | Welcome2024! | Guest Services |
      | NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      | O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Financial Information" details
    And User clicks on the "Payment Methods" section
    Then User ensures a payment method is available by using an existing payment method or creating a new one
    Then The checked box on new payment method is to set it to default, deactivating the previous 'active' payment method
    And User click on "Save"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @FinanceManagement @Smoke @C1542481
  Scenario:Validate that user is able to view Package Display in Guest Package Section
    Given Login page is displayed
    When Input credentials to login into nclh portal
#      | NCL  | Financial Management | Welcome2024! | Guest Services |
      | NCL  | Assistant Financial Accountant | Welcome2024! | Guest Services |
      | O&R  | Assistant Financial Accountant | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Prepaid" filter to guest service page
    And User able to click "lastName" in the detail table
    And User open guest "Packages" information details
    Then User view current "Eligible Package Components" details

  @FinanceManagement @Smoke @C2056008
  Scenario: Validate that user is able to view the system accounts through category filter.
    Given Login page is displayed
    When Input credentials to login into nclh portal
#     | NCL  | Financial Management | Welcome2024! | Financial Management |
      | NCL  | Assistant Financial Accountant | Welcome2024! | Financial Management |
      | O&R  | Assistant Financial Accountant | Welcome2024! | Financial Management |
    Then User navigate to menu "Financial Management" service
    And User open service "System Account Search" item
    Then User switch to new tab
    And User selects filters "Category"
    And User select "Category" filter option to "Accounting Processes" in financial page
    Then User verifies that the "Category" column contains "Accounting Processes"

  @FinanceManagement @Smoke @C1486918
  Scenario: Validate that user is able to perform business date change to view open checks, reservations and green check mark for completed transactions
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL  | Financial Management | Welcome2024! | Financial Management |
      #| NCL  | Assistant Financial Accountant | Welcome2024! | Financial Management |
      | O&R  | Night Auditor | Welcome2024! | Financial Management |
    Then User navigate to menu "Financial Management" service
    And User open service "Business Date" item
    Then User switch to new tab
    And User click on "Validate"
    And User click on the "Checks (Open, In Progress)"
    Then User should see the green check mark icon
    And User click on "Change Business Date"
    And User click on "Continue"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @FinanceManagement @Smoke @C3700814
  Scenario: Validate that the user is able to delete a guest's classification
    Given Login page is displayed
    When Input credentials to login into nclh portal
#     | NCL | Guest Services | Welcome2024! | Guest Services |
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Classifications" filter to guest service page
    And User able to click "lastName" in the detail table
    And User open guest "Status" information details
    And Scroll to the Classification table
    And User deletes the last Classification
    Then Classification count should be reduced by one
    And User click on "Save"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @FinanceManagement @Smoke @C2956486
  Scenario: Validate that application is able to display the packages (RSSC Phone minutes package etc..)
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | O&R  | 2nd Purser Pax | Welcome2024! | Financial Management |
    Then User navigate to menu "Financial Management" service
    And User open service "Pending Postings Overview" item
    Then User switch to new tab
    And User open hidden filters
    And User select "Status" filter option to "Posted" in financial page
    And User add "Package" filter to guest service page
    And User select "Package" filter and search "Phone Minutes Package - RSSC Concierge Level"
    Then User verifies that the "Package" column contains "Phone Minutes Package - RSSC Concierge Level" text

  @FinanceManagement @Smoke @C3156415
  Scenario:Verify the user is able to search for a guest using Golden ID
    Given Login page is displayed
    When Input credentials to login into nclh portal
      |NCL| Guest Service Representative | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And Search the golden ID
    Then user verifies that the guest is available using the Golden ID