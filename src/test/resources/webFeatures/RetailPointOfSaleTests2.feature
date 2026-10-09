Feature: Retail Point of Sale part2

  @POSAdministration @POS @Smoke @POSA1 @C1429673 @NCLH @OCI @NCL
  Scenario: 283	Validate if the user is able to login into the assigned RVC in the POS
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Beach Bar" and save the selected revenue center name
    And User click on "Save"
    Then User verify the rvc is updated to the profile

  @POSAdministration @POS @Smoke @POSA2 @C1429674 @NCL @OCI @NCLH
  Scenario: Validate if the user can create a check for a crew account type
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

  @POSAdministration @POS @Smoke @POSA3 @C1429851 @NCL @OCI @NCLH
  Scenario: Validate if the user can create a check for a crew account type
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Crew"
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

  @POSAdministration @POS @Smoke @POSA4 @C1430006 @NCL @OCI @NCLH
  Scenario: Validate if the user can enable the Prevent Alcohol Order flag for a guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User double click on guest profile
    And User enable the toggle "toggle"
    And User click on "Confirm"
    And User clicks the close icon in the guest profile container.
    And User click on label "BAR"
    And User select gift item "WINE BOTTLE"
    Then User verify error toast message appears on the screen "Unfortunately we are not allowed to serve"

  @POSAdministration @POS @Smoke @POSA5 @C1430007 @NCL @OCI @NCLH
  Scenario: Validate that the user can disable Prevent Alcohol Order flag for a guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    #And User clear checks if any present on home page
    And User click on "Add Check" button
    And User select tab "Guests"
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User double click on guest profile
    And User enable the toggle "toggle"
    And User disable toggle for "Prevent Alcohol Order"
   Then User verify the "Prevent Alcohol Order" toggle button off

  @POSAdministration @POS @Smoke @POSA6 @C1430010 @NCL @OCI @NCLH
  Scenario: Ensure that if intoxication flag were toggled "on" POS user would view error message if alcoholic beverage were to be added to guest check
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    #And User clear checks if any present on home page
    And User click on "Add Check" button
    And User select tab "Guests"
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User double click on guest profile
    And User enable the toggle "toggle"
    And User click on "Confirm"
    And User clicks the close icon in the guest profile container.
    And User click on label "BAR"
    And User select gift item "WINE BOTTLE"
    Then User verify error toast message appears on the screen "Unfortunately we are not allowed to serve"

  @POSAdministration @POS @Smoke @POSA7 @C1430011 @NCL @NCLH
  Scenario: Verify the user cannot add an alcoholic beverage to the order for a minor
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    And User search for the "MINOR" user and select
    And User click on "CREATE CHECK" button
    And User click on label "BAR"
    And User select gift item "WINE BOTTLE"
    Then User verify item is disabled

  @POSAdministration @POS @Smoke @POSA8 @NCL @OCI @C1438814 @NCLH
  Scenario: Verify if the user can select a closed check and view closed check details
    Given Create a closed check with tip for guest
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User navigated to guest list
    And User should select the filter "Check Status" as "Closed"
    Then User verify saved guest having purchased price with tip  under "Total Sum"

  @POSAdministration @POS @Smoke @POSA9 @C1438817 @NCL @OCI @NCLH
  Scenario: Verify if the user has the ability to view a closed check and reprint the check
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
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
    Then User verify able to reprint the statement

  @POSAdministration @POS @Smoke @POSA10 @C1438819 @NCL @OCI @NCLH
  Scenario: Verify if the user has the ability to view a closed check and change the buyer of the check
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest list
    And User open the latest check on the list
    And User navigated to add another buyer screen
#    And User search for the "Guest2" user and select
    And Search guest and select having min sufficient "50" funds
    And User add another buyer
    And User save new buyer name
    Then User save the total price displayed on the cart
    And User click on "CONFIRM" button
    And User click on "CONFIRM" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify saved new guest having purchased price under "Total Sum"

  @POSAdministration @POS @Smoke @POSA11 @C1438820 @NCL @OCI @NCLH
  Scenario: Verify if the user can add a tip to a closed check
    Given Create a closed check for guest
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User clear checks if any present on home page
    And User navigated to guest list
    And User clear check status dropdown
    And User set check status dropdown value to "Closed"
    And User open the latest check on the list
    And User open tips page
    And User add tip amount "5" to the check
    Then User save the total price displayed on the cart after adding tip
    And User click on "POST"
#    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify saved guest having purchased price with tip  under "Total Sum"

  @POSAdministration @POS @Smoke @POSA12 @C1438822 @NCL @OCI @NCLH
  Scenario: Verify if the user can make changes to a closed check and view that the closed check reflects the updates made
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Bartender | Welcome2024! | Point of Sales |
      | O&R | Bartender | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest list
    And User open the latest check on the list
    And User navigated to add another buyer screen
#    And User search for the "Guest2" user and select
    And Search guest and select having min sufficient "50" funds
    And User add another buyer
    And User save new buyer name
    Then User save the total price displayed on the cart
    And User click on "CONFIRM" button
    And User click on "CONFIRM" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify saved new guest having purchased price under "Total Sum"

  @POSAdministration @POS @Smoke @POSA13 @NCL @OCI @C1438825 @NCLH
  Scenario: Verify if the user has the ability to split the transaction by consumer
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    And User Search two guests and select having min sufficient funds greater than 0
    And User save selected guestNames list
    And User click on "CREATE CHECK" button
    And User select "1st" guest for selecting product
    And User click on label "BAR"
    And User select item "LIQUOR BOTTLE" for adding tip
    And User select "2nd" guest for selecting product
    And User click on label "BAR"
    And User select item "LIQUOR BOTTLE" for adding tip
    And User open link "payment-pos"
    And User click on split Check By Consumer
    And User perform signature and confirm "POST" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify guest names displayed check confirmation page

  @POSAdministration @POS @Smoke @POSA14 @NCL @OCI @C1438831 @NCLH
#  @PointOfSale @POS @Smoke @C1438831 @C1663401
  Scenario: View separate transactions for each guest in one check and that specific items are associated to the correct guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    And User Search two guests and select having min sufficient funds greater than 0
    And User save selected guestNames list
    And User click on "CREATE CHECK" button
    And User select "1st" guest for selecting product
    And User click on label "BAR"
    And User select item "LIQUOR BOTTLE" for adding tip
    And User select "2nd" guest for selecting product
    And User click on label "BAR"
    And User select item "LIQUOR BOTTLE" for adding tip
    And User open link "payment-pos"
    And User click on split Check By Consumer
    And User perform signature and confirm "POST" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify guest names displayed check confirmation page

  @POSAdministration @POS @Smoke @NCL @OCI @NCLH @C1438832 @POSA15
  Scenario: Verify if the user can search and select a check to void
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

  @POSAdministration @POS @Smoke @POSA16 @NCL @OCI @C1438835 @NCLH
  Scenario: Verify the user must select a reason to void the check or item
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Beverage Manager | Welcome2024! | Point of Sales |
      | O&R | Beverage Manager | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest list
#    And User save the guestName
    And User navigated to check number having greater than "0.0"
    And User return already purchased item
    And User navigated to guest list
    Then User verify check amount displayed as "0.00" after returning item

  @POSAdministration @POS @Smoke @POSA17 @NCL @C1457372
  Scenario: Validate a waiter can add items to an open check
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "BAR"
    And User select gift item "LIQUOR"
    And User add price if the product not having price
    Then User save the total price displayed on the cart
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify saved guest having purchased price under "Total Sum"

  @POSAdministration @POS @Smoke @NCL @C1457373 @NCLH @POSA18 @OCI
  Scenario: Validate the user has the ability to cancel the check prior to it being serviced
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    And Search guest and select having min sufficient "200" funds
    #And User Search adult guest and select having min sufficient funds greater than 0
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on label "BAR"
    And User select item "LIQUOR BOTTLE" for adding tip
    And User should click on the Back arrow button which is on the left top side of the screen
#    And User view the success pop up
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

  @POSAdministration @POS @Smoke @NCL @C1457376 @NCLH @POSA19 @OCI
  Scenario: Validate if the user can view the employee name / ID who had opened the check
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
#    And User select tab "Guests"
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
    Then User verify associate name "Dining 3 QA Testing" in the table

  @POSAdministration @POS @Smoke @NCL @C1457377 @POSA20
  Scenario: Validate if condiments are configured in the POS for respective items
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

  @POSAdministration @POS @Smoke @POSA21 @C1457379 @NCL @OCI @NCLH
  Scenario: Validate if the user can view all the respective check details in the guest receipt
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

  @POSAdministration @POS @Smoke @POSA22 @NCL @C1457381 @NCLH @OCI
  Scenario: View that tip amount can be added manually to a check before it is closed
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    And Search guest and select having min sufficient "200" funds
    #And User Search adult guest and select having min sufficient funds greater than 0
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on label "BAR"
    And User select item "LIQUOR BOTTLE" for adding tip
    And User open link "payment-pos"
    And User add tip amount "5" to the check
    Then User save the total price displayed on the cart after adding tip
    Then User verify price should be increased after applying tip to check

  @POSAdministration @POS @Smoke @POSA23 @NCL @C1457382 @NCLH @OCI
  Scenario: View that the selected tip amount is reflected on the closed check
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    And Search guest and select having min sufficient "200" funds
    #And User Search adult guest and select having min sufficient funds greater than 0
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on label "BAR"
    And User select item "LIQUOR BOTTLE" for adding tip
    And User open link "payment-pos"
    And User add tip amount "5" to the check
    Then User save the total price displayed on the cart after adding tip
#    Then User verify price should be increased after applying tip to check
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify saved guest having purchased price with tip  under "Total Sum"

  @POSAdministration @POS @Smoke @POSA24 @C1472017 @NCL @OCI @NCLH
  Scenario: Verify if that the following receipt details are displayed correctly: gratuity line, items, reservation details, etc.
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Restaurant Manager | Welcome2024! | Point of Sales |
      | O&R | Restaurant Manager | Welcome2024! | Point of Sales |
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

  @POSAdministration @POS @Smoke @POSA25 @C1472028 @NCL @OCI @NCLH
  Scenario: Validate if service charge is applied to the respective items in the check detail area
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

  @POSAdministration @POS @Smoke @POSA26 @NCL @OCI @C1472029 @NCLH
  Scenario: Validate that by swiping right on a item in the check detailed area, removes the item
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
#    And User apply filter "Checked In"
#    And User clear reservation status of the crew
#    Then User select "Checked In" option from the filter
    And Search guest and select having min sufficient "1200" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "BAR"
    And User select gift item "LIQUOR"
    And User add price if the product not having price
    And User swipe "right" on item added to cart
    And User enter quantity "9" in the final quantity inputBox
    Then User verify product quantity able to update in the cart with "9"
    And User swipe "left" on item added to cart
    Then User verify product disappear from the cart

  @POSAdministration @POS @Smoke @POSA27 @C2147385 @NCL @OCI @NCLH
  Scenario: 283	Validate if the user is able to login into the assigned RVC in the POS
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Restaurant Manager | Welcome2024! | Point of Sales |
      | O&R | Restaurant Manager | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Crew Bar" and save the selected revenue center name
    And User click on "Save"
    Then User verify the rvc is updated to the profile

  @POSAdministration @POS @Smoke @POSA28 @C2279522 @NCL
  Scenario: Validate if the Regular price and Promo price options are getting displayed under Price Levels
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Restaurant Manager | Welcome2024! | Point of Sales |
      | O&R | Restaurant Manager | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "200" funds
    #And User Search adult guest and select having min sufficient funds greater than 0
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on label "BAR"
    And User select gift item "WINE BOTTLE"
    And User save initial product "CHP DOM PERIGNON" price before applying promoCode
    And User click on label "FAV"
    And User verify the Regular and Promo options should get displayed under the Price Level dropdown

#  @POSAdministration @POS @Smoke @POSA29 @C3591943 @NCL @OCI @NCLH
#  Scenario: Validate if the Regular price and Promo price options are getting displayed in the POS Client device under Price Levels
#    Given Login page is displayed
#    When Input credentials to login into nclh portal
#      | NCL | Restaurant Manager | Welcome2024! | Point of Sales |
#      | O&R | Restaurant Manager | Welcome2024! | Point of Sales |
#    And User clear checks if any present on home page
#    And User select revenue center "LN1-Beach Bar"
#    And User click on "Add Check" button
#    And User select tab "Guests"
#    #And User search for the "Guest1" user and select
#    And Search guest and select having min sufficient "200" funds
#    #And User Search adult guest and select having min sufficient funds greater than 0
#    And User save selected guestName
#    And User click on "CREATE CHECK" button
#    And User click on label "BAR"
#    And User select gift item "WINE BOTTLE"
#    And User save initial product "CHP DOM PERIGNON" price before applying promoCode
#    And User click on label "FAV"
#    And User perform applying promoCode "BAR-PROMO"
#    And User click on label "BAR"
#    And User select gift item "WINE BOTTLE"
#    And User save initial product "CHP DOM PERIGNON" price after applying promoCode
#    Then User verify price should be reduced after applying promo code

  @POSAdministration @POS @Smoke @POSA30 @C1457374 @NCL
  Scenario: Validate if the user can add comments to a table
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
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

  @POSAdministration @POS @Smoke @POSA31 @C1457375 @NCL
  Scenario: Validate if the user can view the table status and check association
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
    Then User verify the guest "Table" number under check menu

  @POSAdministration @POS @Smoke @POSA32 @C2309742 @NCL
  Scenario: Ability to view guest allergy as a special need on the guest account
    Given User adds a special needs guest member
    When Input credentials to login into nclh portal
      | NCL | Waiter | Welcome2024! | Point of Sales |
      | O&R | Waiter | Welcome2024! | Point of Sales |
#    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Cagney"
    Then User select the table and click on add check button
    And User select tab "Guests"
    And User search for special needs guest and select guest
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User save the selected guest Details
    And User double click on guest profile
    Then User verify the guest guest if there are any special needs on the order taking device

  @RetailPointOfSale @Smoke @RPOS33 @C4352524 @NCL @OCI @NCLH
  Scenario: Ability to view guest information in the POS
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


  @RetailPointOfSale2 @POS @Smoke @C1438837 @C1438833 @C2980500
  Scenario: Select exchange, return, or void for an already purchased item via guest receipt information
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest list
    And User save the guestName
    And User navigated to check number having greater than "0.0"
    And User return already purchased item
    And User navigated to guest list
    Then User verify check amount displayed as "0.00" after returning item

  @RetailPointOfSale2 @POS @Smoke @RPOS23 @C1042074 @NotAllura
  Scenario: Verify if the POS user is not able to serve Alcoholic beverages for Minor,Child,Infant age groups
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS User | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guests"
    And User search for the "MINOR" user and select
    And User click on "CREATE CHECK" button
    And User click on label "BAR"
    And User select gift item "WINE BOTTLE"
    Then User verify item is disabled

  @RetailPointOfSale2
  Scenario: Verify if the POS user is not able to serve Alcoholic beverages for Minors
    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Service |
    Then User navigate to menu "Guest Service" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    Then User select "Checked In" option from the filter
    And User add "Onboard" filter to guest service page
    And User select "Onboard" filter option to "Yes"
    And User open hidden filters
    And User set age filter to be "child"
    And User save guest firstName excluding sensitive cabins
    Then User save guest first name
    Then User save guest full name
    And User logout from nclh

    Given Login page is displayed
    When Input credentials to login into nclh
      | POS User | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User click on "Add Check" button
    And User select tab "Guest"
    And User search for the "MINOR" user and select
    And User click on "CREATE CHECK" button
    And User click on label "BAR"
    And User select gift item "WINE BOTTLE"
    Then User verify item is disabled

  @RetailPointOfSale2
  Scenario: Validate if the POS User has the Ability to cancel the check prior to it being serviced
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guest"
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on label "EFFY"
    And User select gift item "Effy Jewelry"
    And User clear checks if any present on home page
    Then User verify no check and home page is clear

  @RetailPointOfSale2
  Scenario: Ability to search and select another RVC to view RVC details
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS User | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User search revenue center "LN1-Cagney's" and select
    Then User verify RVC should be updated as "Cagney"




