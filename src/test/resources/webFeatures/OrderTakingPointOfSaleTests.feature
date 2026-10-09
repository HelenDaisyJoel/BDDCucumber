Feature: Order taking Point of Sale

  @OrderPointOfSale @Smoke @OPOS1 @C1042012 @NCL @OCI @NCLH
  Scenario: Verify if the waiter is able to view the information such as birthday,special requests,posting status,age group on the order taking device
    Given Create a guest for today's birthday
    When Input credentials to login into nclh portal
     | NCL | Waiter | Welcome2024! | Point of Sales |
     | O&R | Waiter | Welcome2024! | Point of Sales |
#    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
    And User search for guest and select guest
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User save the selected guest Details
    And User double click on guest profile
    Then User verify the guest details as birthday,special requests,posting status,age group on the order taking device

  @OrderPointOfSale @Smoke @OPOS2 @C1042013 @NCL @OCI @NCLH
  Scenario: Verify if the waiter is able to view guest information such as guest photo, guest name, cabin details, and packages
    Given Create a guest for today's birthday
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
#    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
    And User search for guest and select guest
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User save the selected guest Details
    And User double click on guest profile
    Then User verify the guest information such as guest photo, guest name, cabin details, and packages

  @OrderPointOfSale @Smoke @OPOS3 @C1042014 @NCL @OCI @NCLH
  Scenario: Verify if the waiter is able to view the guest's birthday indicator on the order taking device
    Given Create a guest for today's birthday
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
#    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
    And User search for guest and select guest
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User save the selected guest Details
    And User double click on guest profile
    Then User verify the guest details as birthday,special requests,posting status,age group on the order taking device

  @OrderPointOfSale @Smoke @OPOS4 @C1042015 @NCL @OCI @NCLH
  Scenario: Verify if the waiter is able to view the special needs indicator on the order taking device
    Given Create a guest for today's birthday
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
#    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
    And User search for guest and select guest
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User save the selected guest Details
    And User double click on guest profile
    Then User verify the guest guest if there are any special needs on the order taking device

  @OrderPointOfSale @Smoke @OPOS5 @C1042016 @NCL @OCI @NCLH
  Scenario: Verify if the waiter is able to view the posting status indicator on the order taking device
    Given Create a guest for today's birthday
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
#    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
    And User search for guest and select guest
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User save the selected guest Details
    And User double click on guest profile
    Then User verify the guest is able to view the posting status indicator on the order taking device

  @OrderPointOfSale @POS @Smoke @OPOS6 @C1042017 @NCL @OCI @NCLH
  Scenario: Verify if the user can create a check and view the guest's name / stateroom in the receipt
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "FOOD"
    And User select gift item "FAS STEAK COVER"
    And User add price if the product not having price
    Then User save the total price displayed on the cart
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify saved guest having purchased price under "Total Sum"

  @OrderPointOfSale @POS @Smoke @OPOS7 @C1042020 @NCL
  Scenario: Check is created by table number in selected waiter station
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "FOOD"
    And User select gift item "FAS STEAK COVER"
    And User add price if the product not having price
    Then User save the total price displayed on the cart
    And User open link "check-pos"
    And User verify the open checks in the POS Dashboard associated to the table

  @OrderPointOfSale @POS @Smoke @C1042021 @NCL @OCI @OPOS8 @NCLH
  Scenario: View that party size is associated to the check
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And User Search two guests and select having min sufficient funds greater than 0
    And User save selected guestNames list
    And User click on "CREATE CHECK" button
    And User click on pos label "FOOD"
    And User select gift item "FAS STEAK COVER"
    And User add price if the product not having price
    Then User save the total price displayed on the cart
    And User open link "check-pos"
    And User verify the table number, check number and the number of people part of the group should get displayed in the POS

  @OrderPointOfSale @POS @Smoke @C1042023 @NCL @OCI @OPOS9 @NCLH
  Scenario: Verify that the server name is associated to the check
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    Then User save the total price displayed on the cart
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    And User navigated to latest check on the list
    Then User verify the print receipt having guest name and comment
    Then User verify the print receipt having guest name

  @OrderPointOfSale @POS @Smoke @OPOS10 @C1042024 @NCL @OCI @NCLH
  Scenario: Upon closing the check, the guest is able to sign via electronic signature
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "FOOD"
    And User select gift item "FAS STEAK COVER"
    And User add price if the product not having price
    Then User save the total price displayed on the cart
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button

  @OrderPointOfSale @POS @Smoke @C1042025 @NCL @OCI @NCLH @OPOS11
  Scenario: Verify if the user can view the Beverage list in POS device
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Beverage Manager | Welcome2024! | Point of Sales |
      | O&R | Beverage Manager | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on label "BAR"
    Then User verify the Proper retail articles should appear for that selected RVC

  @OrderPointOfSale @POS @Smoke @OPOS11 @C1042030 @NCL @OCI @NCLH
  Scenario: Search for article items via item lookup by item name
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | POS | Welcome2024! | Point of Sales Management |
      | O&R | Beverage Manager | Welcome2024! | Point of Sales Management |
    Then User navigate to menu "Point of Sales Management" service
    Then User click on link "Article Setup"
    And User click on "RVC Category" filter and select option "Category - Bar" on POS article setup page
    Then User View all articles associated to that category


  @OrderPointOfSale @POS @Smoke @OPOS13 @C1042034 @NCL @OCI @NCLH
  Scenario: Ensure comments of the items are added to the guest receipt
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
    And User navigate to comment page
    And User write comment "250Chars" for thr product in the cart
    Then User verify save button is "enabled"
    Then User click on save button after comment
    Then User verify orange circle appeared at product description at cart
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    And User navigated to latest check on the list
    Then User verify the print receipt having guest name and comment

  @OrderPointOfSale @POS @Smoke @C1042041 @NCL @OPOS14
  Scenario: Verify if the user can modify the seating arrangement of the guests if required
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on three dots and select "Seat Assignment" option
    And User selects the guest tile card to another seat number by drag and drop
    Then User click on "SAVE"
    And User view the success pop up

  @OrderPointOfSale @POS @Smoke @C1042046 @NCL @OCI @NCLH @OPOS15
  Scenario: Verify if the user can re-order a previously ordered favorite item for the guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "FAV"
    And User select items for purchase "Guest Favorites"
    And User View that favorite item was added to the guest's cart

  @OrderPointOfSale @Smoke @C1042056 @NCL @POS @OPOS16
  Scenario: Sort through items by courses
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
#    And User Search two guests and select having min sufficient funds greater than 0
#    And User save selected guestNames list
    And User click on "CREATE CHECK" button
    And User click on pos label "FOOD"
    And User verify the View various course categories such as: cover charge, appetizer, entree, side, dessert, kids menu

  @OrderPointOfSale @POS @Smoke @C1042073 @NCL @OCI @NCLH @OPOS17
  Scenario: Input any modifications to the beverage
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
    And User navigate to comment page
    And User verify the page should contain Condiments
    And User Add desired modifiers
    Then User click on "Save"
    And User verify the selected modifiers are added to the guest check under the liquor selected

  @OrderPointOfSale @POS @Smoke @C2462513 @NCL @OCI @NCLH @OPOS18
  Scenario: Validate that the user is able to view the number of open checks and closed checks of a respective RVC in their POS dashboard
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest Profile page on POS
    And User verify the Open checks and closed checks count of the RVC should be displayed

  @OrderPointOfSale @POS @Smoke @C2462516 @NCL @OPOS19
  Scenario: Validate that user is able to view the Additional Gratuity Total on POS Dashboard
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest Profile page on POS
    And User verify the Additional Gratuity Total of the RVC should be displayed

  @OrderPointOfSale @POS @Smoke @C2462518 @NCL @OPOS20 @OCI @NCLH
  Scenario: Validate that user is able to view the total revenue amount of the RVC in POS Dashboard
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest Profile page on POS
    And User verify the Total revenue of the RVC should be displayed.

  @OrderPointOfSale @POS @Smoke @C2470978 @NCL @OPOS21
  Scenario Outline: Ability to see a real-time floor plan of the restaurant, showing table statuses
    Given Login page is displayed
    When Input credentials to login into nclh
      | F&B dining | Welcome!2024 | Dining |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User click on map Tab
    And User select dining location " Cagney's"
    And User select on table "1"
    Examples:
      | Service | ServiceName       |
      | Dining  | Reservations List |

  @OrderPointOfSale @POS @Smoke @C2470979 @NCL @OPOS22
  Scenario Outline:Ability to update the status of a table
    Given Login page is displayed
    When Input credentials to login into nclh
      | F&B dining | Welcome!2024 | Dining |
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

  @RetailPointOfSale @POS @Smoke @C2478794 @NCL @OCI @NCLH @OPOS23
  Scenario: Ability to display the total amount of the guest(s) order
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    And User Search two guests and select having min sufficient funds greater than 0
    And User save selected guestNames list
    And User click on "CREATE CHECK" button
    And User select "1st" guest for selecting product
    And User click on label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
#    And User select item "Effy Jewelry" for adding tip
    And User select "2nd" guest for selecting product
    And User click on label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price

  @OrderPointOfSale @POS @Smoke @C2478796 @NCL @OCI @NCLH @OPOS24
  Scenario: Ability to view a confirmation screen once the payment has been successfully processed
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "FOOD"
    And User select gift item "FAS STEAK COVER"
    And User add price if the product not having price
    Then User save the total price displayed on the cart
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button

  @OrderPointOfSale @Smoke @POS @C1438812 @NCL @OCI @NCLH @OPOS25
  Scenario: Verify user can access and filter the closed checks in Check Overview screen in the POS module
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Restaurant Manager | Welcome2024! | Point of Sales |
      | O&R | Restaurant Manager | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest list
    And User should select the filter "Check Status" as "Closed"

  @POSAdministration @POS @Smoke @C3642522 @NCL @OCI @NCLH @OPOS26
  Scenario: Verify user can access and filter the open checks in Check Overview screen in the POS module
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Restaurant Manager | Welcome2024! | Point of Sales |
      | O&R | Restaurant Manager | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest list
    And User should select the filter "Check Status" as "Open"

  @POSAdministration @POS @Smoke @OPOS27 @NCL @OCI @C3661151 @NCLH
  Scenario: Validate user is able to void entire guest check
    When Input credentials to login into nclh portal
      | NCL | Restaurant Manager | Welcome2024! | Point of Sales |
      | O&R | Restaurant Manager | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest list
#    And User save the guestName
    And User navigated to check number having greater than "0.0"
    And User return already purchased item
    And User navigated to guest list
    Then User verify check amount displayed as "0.00" after returning item


#  @OrderPointOfSale @UpdateFirstAndLastName
#  Scenario Outline: Validate that Guest/Pier Agent can update guest's last name in personal information
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Guest Services | Welcome2024! | Guest Service |
#    Then User navigate to menu "<Service>" service
#    And User open service "<ServiceName>" item
#    And User save guest firstName excluding sensitive cabins
#    Then User save guest first name
#    Then User save guest full name
#    And User navigated to "<GuestFirstName>" profile page
#    And User open submenu "Personal Information" details
#    Then User update the first name and last name
#    And User click on "Save"
#    Then User refresh the page
#    And User verify name in profile page
#
#    Examples:
#      | Service       | ServiceName  | GuestFirstName   |
#      | Guest Service | Guest Search | DynamicFirstName |










