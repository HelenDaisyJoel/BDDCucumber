Feature: Guest Service

  @GS1
  Scenario Outline: View guest search information data
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User navigated to "<GuestFirstName>" profile page
    Then Verify guest details
      | Name              | Country                  | Language |
      | NICOLE XXXABAUNZA | United States Of America | No Data  |
    Examples:
      | Service        | ServiceName  | GuestFirstName |
      | Guest Services | Guest Search | XXXABAUNZA     |

  @GuestService @GS2 @C1050140
  Scenario Outline: Verify guest updated loyalty status on guest information screen
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User verify and save status of the guest
    And User open submenu "Status" details
    And User Verify loyalty tire of the guest

#    Then User change loyalty status of the guest "<LoyaltyProgram>" and "<LoyaltyTier>"
#    Then User verify loyalty status of the guest
#    And User refresh the page
#    And User verify and save status of the guest
#    Then User change loyalty status of the guest "<LoyaltyProgram1>" and "<LoyaltyTier1>"
#    Then User verify loyalty status of the guest

    Examples:
      | Service        | ServiceName  | GuestFirstName   | LoyaltyProgram   | LoyaltyTier |  |  |
      | Guest Services | Guest Search | DynamicFirstName | OCI Oceania Club | Gold        |  |  |
     # | Guest Service | Guest Search | DynamicFirstName     | OCI Loyalty    | Blue (OCI)  | RSSC Loyalty    | Bronze (RSSC) |

  @GuestService @Smoke @GS3  @Sanity @C1050122 @C1050147 @C1050148 @C2956708
  Scenario Outline: Validate that Guest Services representative edit's cabin assignment change
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    And User search "Disembarkation" in add filter and click on "Disembarkation Date"
#    And User click on disembarkation date arrow down icon and select "Date Range"
#    Then User enter "startDate" date range
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open guest "<DetailInformation>" information details
    Then User change the current cabin
    And User save the new cabin number
    And User click on "SAVE"
    And User need to enter "Reason"
    And User click on "Confirm"
    And User click on "SAVE"
    And User refresh the page
    And User need to verify the cabin number is updated
    And User validate same cabin guests cabins are also updated


    Examples:
      | Service        | ServiceName  | GuestFirstName   | DetailInformation  |
      | Guest Services | Guest Search | DynamicFirstName | Cruise Information |


  @GuestService @Smoke @GS5 @C2956692 @C2956709 @C2956707
  Scenario Outline:Validate that test Cabin Assignment Change by Guest Service Representative with Reason and Confirmation
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search guest with "baker"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Cruise Information" details
    Then User change the current cabin
    And User save the new cabin number
    And User click on "SAVE"
    And User need to enter "Reason"
    And User click on "Confirm"
    And User click on "SAVE"
    And User refresh the page
    And User need to verify the cabin number is updated

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS6    @C1050149
  Scenario Outline: Validate that guest service representative has the ability to view and update Guest Reservation and Profile Information with User Permissions
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search "Disembarkation" in add filter and click on "Disembarkation Date"
    And User click on disembarkation date arrow down icon and select "Date Range"
    Then User enter "startDate" date range
    #Then User save guest first name
    #Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Personal Information" details
    And User should change actual "Preferred Language" and update to "Spanish"
    Then User save the country "Spanish"
    And User click on "Save"
    And User open submenu "Cruise Information" details
    Then User change the current cabin
    And User save the new cabin number
    And User click on "SAVE"
    And User need to enter "Reason"
    And User click on "Confirm"
    And User click on "SAVE"
    And User refresh the page
#    And User logout from nclh
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Guest Services | Welcome2024! | Guest Service |
#    Then User navigate to menu "<Service>" service
#    And User open service "<ServiceName>" item
#    And User navigated to "<GuestFirstName>" profile page
    And User need to verify the cabin number is updated
    And User need to verify the speaking language is updated

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS8 @C1050152 @C2956689
  Scenario Outline: Guest Service Representative notifies users of upcoming guest milestones (i.e., birthday, etc.)
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User navigated to user "<GuestFirstName>" profile page
    And User open submenu "Personal Information" details
    Then User click on eye opened if its closed
    And User check the Date of Birth
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |


  @GuestService @Smoke @GS9 @C1050151 @C1438751 @C1438813 @NotDependent @GT
  Scenario Outline:Guest Service Representative updates guest preferences during voyage
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Cruise Information" details
    And User click on special request
    Then User click on add icon in special request
    And User select Special Request
    And User click on "Add"
    And User verify the special request is added to the list
    And User save the "Quantity" of the first request
    And User click on edit and edit the quantity
    And User click on "Save"
    Then verify that "Quantity" is updated
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS10 @C1050117 @C1050138 @C1050139
  Scenario Outline:Validate that guest service representative Adds Cash Allocation to Guest Onboard Accounts for selected Department
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Chief Purser   | Welcome2024! | Guest Services |
    #Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Financial Information" details
    Then the user should click the "Onboard Credits" tab, and select "Add Template" under Financial Information
    Then User select the "Category" dropdown as "Compensation"
    Then User select the "Non Refundable OnBoard Credits" and select
    And User save the details in add onboard template
    Then User click on "Add"
    Then User verify created onboard credit template details

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS11 @NotDependent @C1050156 @GT
  Scenario Outline:Validate that notification of Cabin Readiness to Guest upon Housekeeping Alert
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
#    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User save the cabin number
    And User logout from nclh
    When Input credentials to login into nclh
      | Housekeeping | Welcome!2024 | Housekeeping |
    And User type cabin number in filter in housekeeping
    And User search for guest in cabin readiness search box
    And User click on arrow button
    And User verify the guest first name
    And User Verify the cabin number

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS12 @NotDependent @C1050158 @GT @C1050157
  Scenario Outline:Validate to notify crew member of Guest Cabin Issues
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Service Requests" details
    Then User click on add icon
    And User select Category for special request
    And User select code
    And User select origin
    And User click on "Add"
    And User verify the special request is added to the  complaint list

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS13 @C2956693 @1050136
  Scenario Outline: Validate that guest service representative view guest their folio
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Account Statement" details
    And User navigate to folio "Folio 3"
    Then User click on "Add Deposit"
    And User select financial department "990001 - Cash Payment"
    And User type the amount
    And User save the amount
    And User click on "Add"
    Then User verify the "Department" folio
    #And User verify that folio "451001 - Service Charge Adjustments" is added to table
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService  @Smoke @GS14 @C1050124 @todo @C1050125 @C1050153 @C2956695
  Scenario Outline:Validate that guest service representative Identifies Connecting and Adjoining Cabins
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Cruise Information" details
    And User select the <selectedCabins> linked cabins
    And User click on "Save"
    And User verify the saved linked cabins

    Examples:
      | Service        | ServiceName  | GuestFirstName   | selectedCabins |
      | Guest Services | Guest Search | DynamicFirstName | 3              |

  @GuestService @Smoke @GS15  @Regression @C2110366 @C2956704
  Scenario Outline: Validate that user is able to Remove Guests from the Ashore Denied List
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open crew "<DetailInformation>" information details
    Then user select "Add Ashore Denied" in security flags tab
    And  User add the security flags details
    And User click on "Save"
    Then User verify the "ASHORE DENIED" warning to guest profile
    #And User open submenu "Cruise Information" details
    #And User enables "ashore-denied" for crew
    #And User should provide "Ashore Denied Reason" reason and reasonType "Immigration Reasons"
    #And User click on "Save"
    #Then User verify the "ASHORE DENIED" warning to guest profile

    Examples:
      | Service        | ServiceName  | GuestFirstName   | DetailInformation                |
      | Guest Services | Guest Search | DynamicFirstName | Lost & Found, Security Incidents |

  @GuestService @Smoke @GS16 @C2110361 @sc @Regression
  Scenario Outline: Valiadate that user is able to Change Guest Reservation Posting Status in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Account Statement" details
    And User enable toggle "Posting Status" on account statement
    Then User verify the posting status toggle button on in account statement
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS17 @C2110359 @SC @Regression
  Scenario Outline: Validate that user is able to cancel a Guest Reservation in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Guest Services | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "Check-in" item
    Then User click on chain icon at top right pane.
    And User click on "CREATE GUEST RESERVATION"
    Then User enter the crew first name and last name
    Then User set date for "Embarkation Date" in add new guest reservation
    Then User set disembarkation date for "Disembarkation Date" in add new guest reservation
    And User click on "Save & Next (optional)"
    And User click on add document in create new reservation
    Then User enter document number
    #Then User set date for "expiryDate" in add new document on guest reservation
    Then User set date for "Expiry Date" in add new document on crew reservation
    Then User set date for "Birth Date" in add new document guest reservation
    #Then User set date for "documentHolderBirthDate" in add new document  guest reservation
    Then User select the gender dropdown as "Male"
    Then User enter document number
    And User click on "Save & Next"
    And User click on "Confirm"
    And User click on "Save & Next (optional)" in add photo page
    And User click on "Save & finish" in more information page
    #Then User verify create a new reservation for guest member.
    Then User click on chain icon at top right pane.
    And User click on " Cancel Reservation "
    Then User verify the crew status as "Cancelled"

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |


  @GuestService @GS18 @C1050108 @SC @Regression @C1050131 @C2956712
  Scenario Outline: Validate that Board Card Printing wizard has the ability to print with Prospect Guest and Guest type is Non Haven
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    Then User click on chain icon at top right pane.
    And User click on " Print Boardcard "
    And User select the printer
    And User click on "Confirm"

    Examples:
      | Service        | ServiceName  | GuestFirstName  |
      | Guest Services | Guest Search | XXXHENDRICKSZZZ |

  @GuestService @Smoke @GS19 @C1050116
  Scenario Outline: Validate that Guest Service Representative has Guest Profile test with Zip Code Display
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Personal Information" details
    And User click on Addresses
    And User verify that zip code is displayed
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService  @Smoke @GS20 @C2435539 @C2956690  @C3896352
  Scenario Outline:Validate that user is able to view Flight Departure Airport and Flight Arrival Airport dropdown
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Cruise Information" details
    Then User click on Flight Info tab
    And User click on Add flight info if present
    And User verify "departureAirportId" dropdown is present
    And User verify "arrivalAirportId" dropdown is present

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |


  @GuestService @Smoke @GS21 @C1806790
  Scenario Outline: Validate that user is able to update the salutation for a guest after modification.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |

    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Personal Information" details
    And User select the salutation dropdown
    And User click on "Save"
    And User refresh the page
    And User refresh the page
    And User verify get the guest full name with salutation

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS22 @C1050175
  Scenario Outline:View a guest's personal information (such as: birthday, nationality, gender, etc)
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User navigated to user "<GuestFirstName>" profile page
    And User open submenu "Personal Information" details
    Then User click on eye opened if its closed
    And User check the Date of Birth
    And User edit the guest last name
    And User select the salutation dropdown
    And User should change actual "Preferred Language" and update to "Spanish"
    Then User save the country "Spanish"
    And User click on "Save"

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS23 @C3156414 @C2956688
  Scenario Outline:2.16_OCSM_18596_Verify user is able to search using Loyalty ID
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search guest with "GuestUser"
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User navigated to user "<GuestFirstName>" profile page
    And User save the loyalty ID of guest
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User add Loyalty Number "Loyalty" filter to guest service page
    And User select "Loyalty Number" filter and type loyalty number
    Then User save guest first name
    And User verify the guest
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS24 @C1050129
  Scenario Outline: Validate that lost luggage are applied and respective departments are notified when a guest reports lost luggage
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on Plus button to add lost item
    And User select item dropdown
    And User click on lost by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on "Create"
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User type cabin number in filter
    And User search for the guest and open the profile
   # And User search for specific "GuestLastName" and navigated to profile
    And User open submenu "Lost & Found, Security Incidents" details
    And User click on lost and found tab
    And User Verify lost and found item visible in guest page

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Lost & Found | DynamicFirstName |

  @GuestService @Smoke @GS26 @C3052125
  Scenario Outline:Validate that the ability to mark Guest as 'Report as Missing'/'Report as Found' without requiring signature
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open link "quick-links"
    And User navigate to "Report as missing"
    And User set missing date and time
    Then User select crew for reporting
    And User click on button "report-as-missing"
    #And User confirm and perform signature
    And User confirm and perform signature and delete it
    And User click on "Yes"
    Then User can see "Missing" flag on guest profile
    Examples:
      | Service        | ServiceName  |
      | Guest Services | Guest Search |

  @GuestService  @Smoke @GS29 @C1050121
  Scenario Outline:Validate that Guest Services representative generate VIP report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Cruise Information" details
    And User can get the embarkation date and disembarkation date

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService  @Smoke @GS30 @C2956711
  Scenario Outline: Validate that the user is able to select "Newspaper Request" as the request type when creating a service request and view the ticket
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
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
    And User open submenu "Service Requests" details
    Then User click on add icon button in service request tab
    And  user click on "<TabName>" in add service request
    Then User select the "Code" and select code "Newspaper"
    Then User select the "Origin" and select origin
    Then User save all details in add service request page
    And User click on "Add"
    Then User verify the added service request details
    Examples:
      | Service        | ServiceName  | GuestFirstName   | TabName |
      | Guest Services | Guest Search | DynamicFirstName | Hotel   |


  @GuestService  @Smoke @GS31 @C2956703 @GS
  Scenario: Validate that user is able to view list of visitors
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Service" service
    And User save "Checked In Onboard" guests count on guest service
    And User click on link "Visitor Overview"
    And User switch to new tab
    And User clears valid from filter
    And User saves "Visitors" count on guest service

  @GuestService @Smoke @GS32 @C2956706
  Scenario Outline: Validate that user is able to define VIP guest status
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Status" details
    And User select the classification type as VIP and classificationsId
    And User click on "Save"
    And User refresh the page
    And User open link "quick-links"
    And User navigate to "Refresh Index"
    And User verify that status is updated in guest profile

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS33 @C4328910
  Scenario Outline: Validate that lost luggage are applied and respective departments can be edited when a guest reports lost luggage
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on Plus button to add lost item
    And User select item dropdown
    And User click on lost by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on "Create"
    #And User add "Lost Cabin" filter to guest service page
    And User search "Lost" in add filter and click on "Lost Cabin"
    And User select "Lost Cabin" filter and type lost cabin number
    And User click on edit and change the lost item
    And User click on "Save"

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Lost & Found | DynamicFirstName |


  @GuestService @Smoke @GS25 @C2956691
  Scenario Outline: Validate that user is able to view Guest travel documents
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service link "<ServiceItem>"
    Then User switch to new tab
    And User Verify travel documents are visible in documents dashboard

    Examples:
      | Service        | ServiceName  | ServiceItem         |
      | Guest Services | Guest Search | Documents Dashboard |

  @GuestService @Smoke @GS34 @C3896360
  Scenario Outline: Verify Posted Amenities Are Displayed Accurately in Guest Profile
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
    And User open submenu "Cruise Information" details
    And User click on "Deliverables" tab
    And User click on three button and select "Add New Amenity"
    Then User add new amenity details
    And User click on "Add"
    And User verify amenity is added to guest profile


    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @GS35 @NotDependent @Smoke @Regression @C1050127
  Scenario Outline: Print guest folio with different search methods
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Account Statement" details
    And User click on "Print Statement"
    Then User select the Account Statement Type and select port
    And User click on "Confirm"
    And User switch to pdf page and get pdf content
    #Then User verify guest full name present in the PDF content

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS36 @C2956685
  Scenario Outline:Validate that the user is able to view the "Guest Information" tab with existing guest details.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    And User search "Disembarkation" in add filter and click on "Disembarkation Date"
#    And User click on disembarkation date arrow down icon and select "Date Range"
#    Then User enter "startDate" date range
    #Then User save guest first name
    #Then User save guest full name
    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Personal Information" details
    Then User click on eye opened if its closed
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS37 @@C2956701
  Scenario Outline:Validate that user is able to cancel a Guest Reservation in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Expect Future" option from the filter
#    Then User select "Checked In" option from the filter
#    And User add "Onboard" filter to guest service page
#    And User select "Onboard" filter option to "Yes"
    And User search guest with "xxx"
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "xxx" profile page
    Then User click on chain icon at top right pane.
    And User verify the checkout button not available in quick action groups panel
    And User click on "Cancel Reservation"
    Then User verify the crew status as "Cancelled"
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS38 @C2956697
  Scenario Outline:Validate that user is able to cancel a Guest Reservation in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item in guest page
    And User click on "Advanced"
    Then User switch window to add new workorder
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
      | Service        | ServiceName                | GuestFirstName   |
      | Guest Services | Create Maintenance Request | DynamicFirstName |

  @GuestService @Smoke @GS21 @C1050114
  Scenario Outline: Update guest information and view payment details from embarkD check in
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Personal Information" details
    And User edit the guest last name
    And User select the salutation dropdown
    And User click on "Save"
    And User refresh the page
    And User refresh the page
    And User verify get the guest full name with salutation
    And User open submenu "Financial Information" details
    Then the user should click the "Payment Methods" tab, and get the payment methods
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS22 @C1050120
  Scenario Outline: Edit a guest's cabin assignment (singular guest)
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open guest "<DetailInformation>" information details
    Then User change the current cabin
    And User save the new cabin number
    And User click on "SAVE"
    And User unselects same cabin guests
    And User need to enter "Reason"
    And User click on "Confirm"
    And User click on "SAVE"
    And User refresh the page
    And User need to verify the cabin number is updated

    Examples:
      | Service        | ServiceName  | GuestFirstName   | DetailInformation  |
      | Guest Services | Guest Search | DynamicFirstName | Cruise Information |


  @GuestService @Smoke @GS23 @C1050123
  Scenario Outline: View and add a guest folio section
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Account Statement" details
    And User save the folio header count before clicking on add
    And User click on add folio header
    And User Verify the folio is added

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |


  @GuestService @Smoke @GS24  @Regression @C2110367
  Scenario Outline: Validate that user is able to Remove Guests from the Ashore Denied List
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
#    And User add "Onboard" filter to guest service page
#    And User select "Onboard" filter option to "Yes"
    And User add "Denied" filter to guest service page
    And User select "Denied" filter option to "Ashore"
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    Then User verify the "ASHORE DENIED" warning to guest profile
    And User open crew "<DetailInformation>" information details
    And User click on delete icon
    And User click on "Confirm"
   # Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

    Examples:
      | Service        | ServiceName  | GuestFirstName   | DetailInformation                |  |
      | Guest Services | Guest Search | DynamicFirstName | Lost & Found, Security Incidents |  |

  @GuestService @Smoke @GS25 @C1050146
  Scenario Outline:View package and package consumption information for a guest that has an eligible package
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Packages" details
    And User Verify able to view package consumption data

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |


  @GuestService @Smoke @GS27 @C1050118 @VoyagerGS @OCI
  Scenario Outline:View and update a guest's onboard status from shoreside to onboard
    Given Login page is displayed
    When Input credentials to login into nclh
      | Concierge | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "No"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
    And User search guest with "xxx"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    Then User verifies board status "Checked In / Shoreside"
    And User open submenu "Cruise Information" details
    And User click on onboard toggle
    And User click on "Confirm"
    And User click on "Save"
    Then User verifies board status "Checked In / On Board"

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS28 @C1050119 @VoyagerGS @OCI
  Scenario Outline: View routing information for a guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "xxx"
#    Then User save guest first name
#    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
#    Then User save the guest details
#    And user verify the guest name
#    And User open submenu "Personal Information" details
#    Then  User verify the guest details on personal Information
    And User open submenu "Financial Information" details
    And User click on " ADD ROUTING "
    Then User select "Is Paid By" radio button
    And User click on "ADD"
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    Then User click on "add & view profile"
    And User click on "Yes"
    Then User verify the add routing

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS29 @C3094166 @VoyagerGS @OCI
  Scenario Outline:Verify the 'Excursion' radio button is displaying correctly in Absences tab in cruise information section
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
#    Then User save guest first name
#    Then User save guest full name
#    And User save guest firstName excluding sensitive cabins
    And User search guest with "xxx"
#    Then User save guest first name
#    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Cruise Information" details
    And User click on Absences tab
    And User Verify the excursion radio button is selected


    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS30 @C3700852
  Scenario Outline: Validate user is able to view lost item in the linked guest's profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on Plus button to add lost item
    And User select item dropdown
    And User click on lost by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on "Create"
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User type cabin number in filter
    And User search for the guest and open the profile
   # And User search for specific "GuestLastName" and navigated to profile
    And User open submenu "Lost & Found, Security Incidents" details
    And User click on lost and found tab
    And User Verify lost and found item visible in guest page

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Lost & Found | DynamicFirstName |

  @GuestService @Smoke @GS31 @C4391526
  Scenario Outline: Validate manifest load details
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
#    Then User save guest first name
#    Then User save guest full name
#    And User search guest with "GuestUser"
#    Then User save guest first name
#    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User navigated to user "<GuestFirstName>" profile page
    And User get the guest full name
    And User get guest age, gender
    And User save the loyalty ID of guest
    And User open submenu "Personal Information" details
    Then User click on eye opened if its closed
    And User check the Date of Birth
    And User edit the guest last name
    And User select the salutation dropdown
    And User should change actual "Preferred Language" and update to "Spanish"
    Then User save the country "Spanish"
    And User click on "Save"
    And User open submenu "Cruise Information" details
    And User can get the embarkation date and disembarkation date

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS32 @C3700850
  Scenario Outline: Validate that the user is able to add a lost mobile device to the system
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on Plus button to add lost item
    And User select item as "Mobile"
    And User click on lost by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on "Create"
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User type cabin number in filter
    And User search for the guest and open the profile
   # And User search for specific "GuestLastName" and navigated to profile
    And User open submenu "Lost & Found, Security Incidents" details
    And User click on lost and found tab
    And User Verify lost and found item visible in guest page

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Lost & Found | DynamicFirstName |

  @GuestService @Smoke @GS33 @C3700851
  Scenario Outline:Validate that the user is able to mark a previously lost phone as found
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on Plus button to add lost item
    And User select item dropdown
    And User click on lost by icon
    And User select a guest
    And User save the guest name
    And User save the cabin number of guest
    And User click on "Select"
    And User click on "Create"
    #And User add "Lost Cabin" filter to guest service page
    And User search "Lost" in add filter and click on "Lost Cabin"
    And User select "Lost Cabin" filter and type lost cabin number
    And User click On Kebab icon and select return
    And User click on found by icon
    And User select a guest
    And User save the guest name
    And User perform signature and confirm " SIGN & CONFIRM " button

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Lost & Found | DynamicFirstName |

  @GuestService @Smoke @GS34 @C2110362
  Scenario Outline:Validate that the user is able to view the expected today guest status list
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Expect Today" option from the filter

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS35 @C2110363
  Scenario Outline: Validate that the user is able to view guest travel documents
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Personal Information" details
    Then User click on document tab
    And User verify that travel document is visible

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS36 @C2110358
  Scenario Outline: Validate that the user is able to create a new reservation in Otalio
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service link "<ServiceItem>"
    Then User switch to new tab
    Then User enter the crew first name and last name
    Then User set date for "Embarkation Date" in add new guest reservation
    Then User set disembarkation date for "Disembarkation Date" in add new guest reservation
    And User click on "Save & Next (optional)"
    And User click on "Save & Next (optional)"
    And User click on add document in create new reservation
    Then User enter document number
    #Then User set date for "expiryDate" in add new document on guest reservation
    Then User set date for "Expiry Date" in add new document on crew reservation
    Then User set date for "Birth Date" in add new document guest reservation
    #Then User set date for "documentHolderBirthDate" in add new document  guest reservation
    Then User select the gender dropdown as "Male"
    Then User enter document number
    And User click on "Save & Next"
    And User click on "Confirm"
    And User click on "Save & Next (optional)" in add photo page
    And User click on "Save & finish" in more information page

    Examples:
      | Service        | ServiceName  | ServiceItem     |
      | Guest Services | Guest Search | New Reservation |

  @GuestService @Smoke @GS37 @C3678930
  Scenario Outline:Validate that the user can view quick actions on a guest profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open link "quick-links"
    And User Verify that quick-links is opened
    Examples:
      | Service        | ServiceName  |
      | Guest Services | Guest Search |

  @GuestService @Smoke @GS38 @C3700813
  Scenario Outline: Validate that user is able to define VIP guest status
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Status" details
    And User select the classification type and classificationsId
    And User click on "Save"
    And User refresh the page
    And User open link "quick-links"
    And User navigate to "Refresh Index"
    And User verify that status is updated in guest profile

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS39 @C3442044
  Scenario Outline: Verify user can search for guest(s) by selecting desired loyalty filter tiers in guest search bar
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User add Loyalty Tire "Loyalty" filter to guest service page
    And User select "Loyalty Tier" filter option to "Ambassador"
    Then User verify loyalty tire is updated to " Ambassador (Latitudes)"
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS40 @C2408011
  Scenario Outline: Validate that the user is able to delete guest preferences in the dedicated "Special Requests" section
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Cruise Information" details
    And User click on special request
    Then User click on add icon in special request
    And User select Special Request
    And User click on "Add"
    And User verify the special request is added to the list
    And User click on delete special request icon

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS41  @Regression @C2869355
  Scenario Outline: Validate that the Guest Services user cannot check out a guest who has 'Ashore Denied' is toggled on their profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
#    And User add "Onboard" filter to guest service page
#    And User select "Onboard" filter option to "Yes"
    And User add "Denied" filter to guest service page
    And User select "Denied" filter option to "Ashore"
    Then User save guest first name
    Then User save guest full name
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    Then User verify the "ASHORE DENIED" warning to guest profile
    And User open link "quick-links"
    And User navigate to " Check Out "
   # Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

    Examples:
      | Service        | ServiceName  | GuestFirstName   | DetailInformation                |  |
      | Guest Services | Guest Search | DynamicFirstName | Lost & Found, Security Incidents |  |

  @GuestService  @Smoke @GS42 @C2110369 @GS
  Scenario: Validate that the user is able to view the list of visitors in Visitor Overview
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Service" service
    And User save "Checked In Onboard" guests count on guest service
    And User click on link "Visitor Overview"
    And User switch to new tab
    And User clears valid from filter
    And User saves "Visitors" count on guest service

  @GuestService @Smoke @GS43 @C2110365
  Scenario Outline: Validate that user is able to manually check-in a guest with an expected status
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service link "<ServiceItem>"
    Then User switch to new tab
    Then User enter the crew first name and last name
    Then User set date for "Embarkation Date" in add new guest reservation
    Then User set disembarkation date for "Disembarkation Date" in add new guest reservation
    And User click on "Save & Next (optional)"
    And User click on "Save & Next (optional)"
    And User click on add document in create new reservation
    Then User enter document number
    #Then User set date for "expiryDate" in add new document on guest reservation
    Then User set date for "Expiry Date" in add new document on crew reservation
    Then User set date for "Birth Date" in add new document guest reservation
    #Then User set date for "documentHolderBirthDate" in add new document  guest reservation
    Then User select the gender dropdown as "Male"
    Then User enter document number
    And User click on "Save & Next"
    And User click on "Confirm"
    And User click on "Save & Next (optional)" in add photo page
    And User click on "Save & finish" in more information page
    Then User click on chain icon at top right pane.
    And User click on "Check In"
    And User click on "Confirm"
    Then User verify the crew status as "Checked In"

    Examples:
      | Service        | ServiceName  | ServiceItem     |
      | Guest Services | Guest Search | New Reservation |

  @GuestService @Smoke @GS44 @C2509080
  Scenario Outline: Validate that the user is able to view guest allergies and add a guest allergy to the guest's profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Personal Information" details
    And User clicks on the "Special Needs" section
    Then user add the data in special fileds
    And User click on "Save"

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS45 @C2859752
  Scenario Outline: Validate the user can add a POI flag to the guest's profile and specify the reason
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open guest "Lost & Found, Security Incidents" information details
    And User enable toggle for "add-boarding-denied" to guest with reason as POI
    Then User verify the "BOARDING DENIED" warning to guest profile

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS46 @C3156416
  Scenario Outline:Verify the user is able to search for a guest using Booking Number
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search guest with "GuestUser"
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User navigated to user "<GuestFirstName>" profile page
    And User save the booking number of guest
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with booking number
    Then User save guest first name
    And User verify the guest
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS47 @C3123472
  Scenario Outline: Verify user can bulk change embarkation dates for multiple guests
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
#    And Click on three dots and select Bulk Operation
#    Then User verify error message on bulk operation "You need to apply at least 1 filter to perform Bulk Operations"
    Then User select "Checked In" option from the filter
    And Click on three dots and select Bulk Operation
    Then Unselect all crew member and select 2 crew members
    And User click on Next button
    When User select radio button "Apply bulk Reservation Actions"
    And User click on Next button
    And User click on "Update Embarkation Date" checkbox
    Then User set "future" date for Disembarkation
    And User click on Confirm button
    And User click on menu button
    And User click on "Start" in menu button
    Then User validate the 2 and disembarkation status
    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS48 @C1050171
  Scenario Outline: Toggle on the paperless invoice option for a guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
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

  @GuestService @GS49 @C3678929
  Scenario Outline:Validate that the user can export an excel page report on the guest search results (based on selected filters)
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User export ticket data
    And User verify that file is downloaded

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @GS50 @C3678931
  Scenario Outline:Validate that the user can view guest calendar on a guest profile
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User open the calender icon on the right panel

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS51 @C4391529
  Scenario Outline: Travel documents verification
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service link "<ServiceItem>"
    Then User switch to new tab
    And User Verify travel documents are visible in documents dashboard

    Examples:
      | Service        | ServiceName  | ServiceItem         |
      | Guest Services | Guest Search | Documents Dashboard |

  @GuestService @Smoke @GS52    @C4391530
  Scenario Outline: Real-time updates when guest information changes
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User open hidden filters
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User search "Disembarkation" in add filter and click on "Disembarkation Date"
    And User click on disembarkation date arrow down icon and select "Date Range"
    Then User enter "startDate" date range
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Personal Information" details
    And User should change actual "Preferred Language" and update to "Spanish"
    Then User save the country "Spanish"
    And User click on "Save"
    And User open submenu "Cruise Information" details
    Then User change the current cabin
    And User save the new cabin number
    And User click on "SAVE"
    And User need to enter "Reason"
    And User click on "Confirm"
    And User click on "SAVE"
    And User refresh the page
    And User need to verify the cabin number is updated
    And User need to verify the speaking language is updated

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @GuestService @Smoke @GS53    @C1050167
  Scenario Outline: Validate that users can generate the VIP report
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Guest Manifests"
    And User click on "Checked-in, Expected Guests by Category, VIP Code, Suites; Expected VIP Guests" code link

    Examples:
      | Service   |
      | Reporting |

  @GuestService @Smoke @GS54 @C1050172
  Scenario Outline:Ability to report issues via Service Request and Maintenance Request via Guest Services module
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
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

  @GuestService@Smoke @GS48 @C1050144
  Scenario Outline: View if guest has a credit card on file or if they are a cash payment account
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Guest Services | Welcome2024! | Guest Services |
      | O&R | Receptionist   | Welcome2024! | Guest Services |
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

  @GuestService@Smoke @GS49 @C1050132
  Scenario: 283	Validate if the user is able to login into the assigned RVC in the POS
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Representative | Welcome2024! | Point of Sales |
      | O&R | Guest Service Representative | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Reception" and save the selected revenue center name
    And User click on "Save"
    Then User verify the rvc is updated to the profile

