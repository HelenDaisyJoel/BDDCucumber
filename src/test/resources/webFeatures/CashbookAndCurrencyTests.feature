Feature: CashBook and Currency

  @CashbookAndCurrency @Smoke @ACC1 @C1538019
  Scenario: Verify Ability to view all open cashier session
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
#    And User select "Status" filter option to "Open"C1538017
#    And User verify the column "Status" is "Open" in the table
    And User select "Status" filter option to "Open"
    And User save open session
    And User verify the column "Status" is "Open" in the table

  @CashbookAndCurrency @Smoke @ACC2 @C1541979  @C3156317
  Scenario: Verify Ability to view all close cashier session
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Close"
    And User verify the column "Status" is "Close" in the table


  @CashbookAndCurrency @Smoke @ACC3 @C1553044
  Scenario: Validate user can perform transaction without assigning a crew member to cashier session
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |

    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    And User save the open cashier session
    And User open "Cashbook" link
    And User navigated to cashbook over view
    And User open link "quick-links"
    And User navigate to "Add Transactions"
    And User click on cash out
    And User goto dropdown "Transaction Type" and select value "Transfer"
    And User goto dropdown "Transaction Code" and select value "Cash Transfer"
    And User goto dropdown "To Cashbook" and select random cashbook
    And User enter amount "1"
    And User click on "SAVE"
    Then User verify error toast message appears on the screen with message "Cashier session is not open for the cashbook"


  @CashbookAndCurrency @Smoke @ACC4 @C1699412
  Scenario: Verify user can print the Cash transaction receipt
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    And User save the open cashier session
    And User open "Cashbook" link
    And User navigated to cashbook over view
    And User select the cashbook record from the table
    And User click on "Select Report"
    And User select an option from dropdown
    And User click on "Confirm"
    And User switch to pdf page and get pdf content
#Then User verify pdf content of cash receipt

  @CashbookAndCurrency @Smoke @ACC5 @C1541977 @C3156315
  Scenario Outline: Verify user can sort the list of Open cashier session by list of available filters
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "<Status>"
    And User verify the column "Status" is "<Status>" in the table
    Then User verify chasBook cashier session table
      | Session Name | Assigned Users | IAM user | Status | Creation Date - Ship | End Date | Start Business Date | End Business Date | Closing Method | Cash Activity |
    Examples:
      | Status |
      | Open   |

  @CashbookAndCurrency @Smoke @ACC6 @C1541978  @C1552944 @C3156316
  Scenario Outline: Verify user can sort the list of Open cashier session by list of available filters
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "<Status>"
    And User verify the column "Status" is "<Status>" in the table
    Then User verify chasBook cashier session table
      | Session Name | Assigned Users | IAM user | Status | Creation Date - Ship | End Date | Start Business Date | End Business Date | Closing Method | Cash Activity |

    Examples:
      | Status |
      | Close   |
  @CashbookAndCurrency @Smoke @ACC7 @C1549862 @C1582676
  Scenario Outline: Validate a user can transfer from guest service cashbook to CFA account
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    #And User add "Cash Activity" filter
    And User add "Cash Activity" filter
    ##And User select "Cash Activity" filter option to "YES"
    And User save the open cashier session
    And User open "Cashbook" link
    And User navigated to cashbook over view
    And User open link "quick-links"
    And User navigate to "Add Transactions"
    And User click on cash out
    And User goto dropdown "Transaction Type" and select value "Transfer"
    And User goto dropdown "Transaction Code" and select value "Cash Transfer"
    And User goto dropdown "To Cashbook" and select open cashbook
    And User enter amount "10"
    And User click on "SAVE"
    And User verify that transaction type as "<Type>"

    Examples:
      | Type |
      | Transfer     |

  @CashbookAndCurrency @Smoke @ACC8 @C1553864 @C1553865
  Scenario:Verify the ability to start a cashier session
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User click on Add cashier button
    And User goto dropdown "Cashbook" select
    Then User select the dropdown "Session Name" and select session
    And User enables  Open Linked Session toggle for guest
    And User click on "Add"
#    And User click on "Cancel"
#    And User click on "Yes"


  @CashbookAndCurrency @Smoke @ACC9 @C1538017
  Scenario:Verify the display of transaction screen in cashbook screen
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    And User add "Cash Activity" filter
    #And User select "Cash Activity" filter option to "SHARED.FILTER.YES"
    And User save the open cashier session
    And User open "Cashbook" link
    And User navigated to cashbook over view
    And User select the cashbook record from the table to open
    Then User close the transaction screen

  @CashbookAndCurrency @Smoke @ACC10 @C1566035
  Scenario: verify the Ability to view the Cashbook entries by the list of filters available.
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashbook" link
    And User click on add more filters
    Then USer verify that cashbook has the  filter
      | Active Session | Cashbook Type | Default Currency | Discrepancy In Session | Enabled | IAM User | Locked | Require Session | Security Level | Deleted |

  @CashbookAndCurrency @Smoke @ACC11 @C1566036
  Scenario:Verify user can edit the cashbook entries
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    And User add "Cash Activity" filter
    #And User select "Cash Activity" filter option to "SHARED.FILTER.YES"
    And User save the open cashier session
    And User open "Cashbook" link
    And User click on add more filters
    Then USer verify that cashbook has the  filter
     # | Active Session | Cashbook Type | Default Currency | Deleted                | Discrepancy In Session | Enabled | IAM User | Locked | Require Session | Security Level |
      | Active Session | Cashbook Type | Default Currency | Discrepancy In Session | Enabled | IAM User | Locked | Require Session | Security Level | Deleted |
    And User click on first edit icon
    And User click on "Update"

  @CashbookAndCurrency @Smoke @ACC12 @C1566037
  Scenario Outline:Verify the display of transaction screen in cashbook screen
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    And User add "Cash Activity" filter
    #And User select "Cash Activity" filter option to "SHARED.FILTER.YES"
    And User save the open cashier session
    And User open "Cashbook" link
    And User navigated to cashbook over view
    And User open link "quick-links"
    And User navigate to "Add Transactions"
    And User click on cash out
    And User goto dropdown "Transaction Type" and select value "Transfer"
    And User goto dropdown "Transaction Code" and select value "Cash Transfer"
    And User goto dropdown "To Cashbook" and select open cashbook
    And User enter amount "10"
    And User click on "SAVE"
    And User verify that transaction type as "<Type>"
    And User select the cashbook record from the table to open
    Then User click on "VOID"
    And User enter the comments field as "test"
    And User click on "Confirm"
    And User select the cashbook record from the table to open
    And User verify that record has "Void Id"
    Examples:
      | Type |
      | Transfer     |

    @Smoke @ACC13 @C1569166 @C1572398 @C1572299
  Scenario Outline: Verify the ability to close cashbook session via Close session (count ) option to view the details
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
     And User add "Status" filter to guest service page
     And User select "Status" filter option to "Open"
     And User add "Cash Activity" filter
     #And User select "Cash Activity" filter option to "SHARED.FILTER.YES"
    And User navigated to cashier session overview
    And User click on cashbook transaction
    And User open link "quick-links"
    And User navigate to "<Type>"
    And User click on " Close Session "
    And User click on "Yes"
    Examples:
      | Type                         |
     # | Close Session (Count)        |
      | Close Session (auto balance) |

  @CashbookAndCurrency @Smoke @ACC14 @C1584791
  Scenario Outline: Verify user can perform cash to cashbook transfer
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    And User add "Cash Activity" filter
    #And User select "Cash Activity" filter option to "YES"
    And User save the open cashier session
    And User open "Cashbook" link
    And User navigated to cashbook over view
    And User open link "quick-links"
    And User navigate to "Add Transactions"
    And User click on cash in
    And User save the current balance
    And User goto dropdown "Transaction Type" and select value "Transfer"
    And User goto dropdown "Transaction Code" and select value "Cash Transfer"
    And User goto dropdown "From Cashbook" and select open cashbook
    And User save the current balance
    And User enter amount "1"
    And User click on "SAVE"
    And User verify that transaction type as "<Type>"
    And User open link "quick-links"
    And User navigate to "Add Transactions"
    And User verify that balance is updated

    Examples:
      | Type |
      | Transfer     |

  @CashbookAndCurrency @Smoke @ACC15 @C1697604
  Scenario Outline:Verify user can perform a cashout transactions at cashbook level
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    And User add "Cash Activity" filter
    #And User select "Cash Activity" filter option to "SHARED.FILTER.YES"
    And User save the open cashier session
    And User open "Cashbook" link
    And User navigated to cashbook over view
    And User open link "quick-links"
    And User navigate to "Add Transactions"
    And User click on cash out
    And User goto dropdown "Transaction Type" and select value "Transfer"
    And User goto dropdown "Transaction Code" and select value "Cash Transfer"
    And User goto dropdown "To Cashbook" and select open cashbook
    And User save the current balance
    And User enter amount "10"
    And User click on "SAVE"
    And User verify that transaction type as "<Type>"
    And User open link "quick-links"
    And User navigate to "Add Transactions"
    And User verify that balance is updated

    Examples:
      | Type     |
      | Transfer |

  @CashbookAndCurrency @Smoke @ACC16 @C1699413
  Scenario: Verify the cash transaction receipt has the account details of the money transfer
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    And User add "Cash Activity" filter
    #And User select "Cash Activity" filter option to "SHARED.FILTER.YES"
    And User save the open cashier session
    And User open "Cashbook" link
    And User navigated to cashbook over view
    And User select the cashbook record from the table to open
    Then User get the "Transaction Type" details
    Then User get the "Transaction Code" details
    Then User get the "Transaction Description" details
    Then User get the "Session" details
    Then User get the "User"
    Then User get "Invoice No." details


  @CashbookAndCurrency @Smoke @ACC17 @C1553235
  Scenario Outline: Validate the transfer from one cashbook to another cashbook
    Given Login page is displayed
   When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashier Sessions" link
    And User add "Status" filter to guest service page
    And User select "Status" filter option to "Open"
    #And User add "Cash Activity" filter
    And User add "Cash Activity" filter
    #And User select "Cash Activity" filter option to "SHARED.FILTER.YES"
    And User save the open cashier session
    And User open "Cashbook" link
    And User navigated to cashbook over view
    And User open link "quick-links"
    And User navigate to "Cashbook Transfer"
    And User click on transfer out
    And User goto dropdown "Transfer From Cashbook" and select from cashbook
    And User goto dropdown "Transfer To Cashbook" and select to cashbook
    #And User goto dropdown "Transaction Name" and select value "Cash Transfer"
    And User click on "SAVE"
    #And User verify that transaction type as "<Type>"

    Examples:
      | Type |
      | Transfer     |

  @CashbookAndCurrency @Smoke @ACC18 @C1555325
  Scenario Outline: Verify Otalio automatically starts a cashier session upon crew member log in
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    Then User get the open cashier session and cashbook name from top blue ribbon
    Examples:
      | Type |
      | Transfer     |

  @CashbookAndCurrency @Smoke @ACC19 @C1693188
  Scenario Outline: Verify the user has the ability to create cashbook
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      |NCL| Financial Management | Welcome2024! | Financial Management |
      |O&R| Chief Purser | Welcome2024! | Financial Management |
    And User open "Cashbook" link
    And User click on Add cashbook
    Then User enter code and description fields
    And User goto dropdown "Cashbook Type" and select option

    Examples:
      | Type |
      | Transfer     |







