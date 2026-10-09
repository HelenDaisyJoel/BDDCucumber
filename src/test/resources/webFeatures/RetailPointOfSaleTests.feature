Feature: Retail Point of Sale

#  Background:
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Financial Management | Welcome2024! | Guest Service |
#    Then User navigate to menu "Guest Service" service
#    And User open service "Guest Search" item
#    And User clear reservation status of the crew
#    And User get "2" number of "guest" full name from saved list
#    And User search for specific "guest1" and navigated to profile
#    And User enables posting status "posting-status" for guest
#    And User open crew "Financial Information" information details
#    And User click "Disallowed Departments" on Personal Information
#    And User delete if there are any disallowed sub-departments
#    Then User click on "Credit Limits" tab and click on "Add Limit"
#    And User click on "Add Limit"
#    And User Enter "10000"
#    Then User click on "Save"
#    Then User navigate to menu "Guest Service" service
#    And User open service "Guest Search" item
#    And User clear reservation status of the crew
#    And User search for specific "guest2" and navigated to profile
#    And User enables posting status "posting-status" for guest
#    And User open crew "Financial Information" information details
#    And User click "Disallowed Departments" on Personal Information
#    And User delete if there are any disallowed sub-departments
#    Then User click on "Credit Limits" tab and click on "Add Limit"
#    And User click on "Add Limit"
#    And User Enter "10000"
#    Then User click on "Save"
#    And User logout from nclh

  @RetailPointOfSale @POS @Smoke @C1050179 @NCL @OCI @NCLH @RPOS1
  Scenario: Verify if the sales associate can preview the POS article data
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "EFFY"
    And User select gift item "Effy Jewelry"
    And User verify the RVC related articles should be appear

  @RetailPointOfSale @POS @Smoke @C1050187 @RPOS2 @NCL @OCI @NCLH
  Scenario: Verify if the POS user is able to ring a check in the POS device and add desired item to the POS
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
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
#    Then User verify the success message
    And User navigated to guest list
    Then User verify saved guest having purchased price under "Total Sum"

  @RetailPointOfSale @POS @Smoke @C1050188 @RPOS3 @NCL @OCI @NCLH
  Scenario: Ensure that all the respective details are displayed on the receipt printed
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

  @RetailPointOfSale @POS @Smoke @C1050189 @RPOS4 @NCL @OCI @NCLH
  Scenario: Ability to reprint a receipt
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
    Then User verify able to reprint the statement

  @RetailPointOfSale @POS @Smoke @C1050195 @RPOS5 @NCL @OCI @NCLH
  Scenario: Validate if the transaction is posted and added to guest folio
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Boutique Manager | Welcome2024! | Point of Sales |
      | O&R | Boutique Manager | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "EFFY"
    Then User verify the Proper retail articles should appear for that selected RVC

  @RetailPointOfSale @POS @Smoke @C1050198 @NCL @OCI @NCLH @RPOS6
  Scenario: Validate if the barcodes of the retail items are functional
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Boutique Manager | Welcome2024! | Point of Sales Management |
      | O&R | Boutique Manager | Welcome2024! | Point of Sales Management |
    Then User click on link "Article Setup"
    And User click on add more filters
    Then User search "Article Group" filters
    And User click on "Article Group" filter and select option "Effy Jewelry" on POS article page
#    And User save the code and barcode details on POS articles
    And User save the barcode details on POS articles
    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User search for item code in article search
    And User verify the barcode article details

  @RetailPointOfSale @POS @Smoke @C1050208 @RPOS7 @NCL @OCI @NCLH
  Scenario: Validate if the retail related inventory is available to be added to the guest's cart
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

  @RetailPointOfSale @POS @Smoke @C1050209 @RPOS8 @NCL @OCI @RPOSAll
  Scenario: Validate if any unwanted item can be removed from the check detailed area in the Retail POS
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "1200" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    And User swipe "right" on item added to cart
    And User enter quantity "999" in the final quantity inputBox
    Then User verify product quantity able to update in the cart with "999"
    And User swipe "left" on item added to cart
    Then User verify product disappear from the cart

  @RetailPointOfSale @POS @Smoke @C1050211 @RPOS9 @NCL @OCI @NCLH
  Scenario: Validate if the transaction is posted and added to guest folio
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
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
    Then User verify saved guest having purchased price under "Total Sum"
    #Then User verify associate name "QA POS Shop User" in the table
    And User save the check number
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

  @RetailPointOfSale @POS @Smoke @C1050214 @RPOS10 @NCL @OCI @NCLH
  Scenario: Validate the open price articles are functional
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
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

  @RetailPointOfSale @POS @Smoke @C1050216 @RPOS11 @NCL @OCI @NCLH
  Scenario: Transaction is posted and details are attached to the receipt for the open priced items
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
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
    Then User verify saved guest having purchased price under "Total Sum"

  @RetailPointOfSale @POS @Smoke @C1050217 @RPOS12 @NCL @OCI @NCLH
  Scenario:	Void a purchased item
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Boutique Manager | Welcome2024! | Point of Sales |
      | O&R | Boutique Manager | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User navigated to guest list
    And User save the guestName
    And User navigated to check number having greater than "0.0"
    And User return already purchased item
    And User navigated to guest list
    Then User verify check amount displayed as "0.00" after returning item

  @RetailPointOfSale @POS @Smoke @C1050220 @RPOS13 @NCL @OCI @NCLH
  Scenario: Verify if the Sales Representative can view current buyer on change and modify the buyer
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
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
    And User open the latest check on the list
    And User navigated to add another buyer screen
#    And User search for the "Guest2" user and select
    And User search and add another buyer
#    And Search guest and select having min sufficient "50" funds
    And User add another buyer
    And User save new buyer name
    Then User save the total price displayed on the cart
    And User click on "CONFIRM" button
    And User click on "CONFIRM" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify saved new guest having purchased price under "Total Sum"

  @RetailPointOfSale @POS @Smoke @C1050221 @NCL @OCI @NCLH @RPOS14
  Scenario: Verify that the check reflects in the new buyer's guest folio
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
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
    And User open the latest check on the list
    And User navigated to add another buyer screen
#    And User search for the "Guest2" user and select
    And User search and add another buyer
#    And Search guest and select having min sufficient "50" funds
    And User add another buyer
    And User save new buyer name
    Then User save the total price displayed on the cart
    And User click on "CONFIRM" button
    And User click on "CONFIRM" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify saved new guest having purchased price under "Total Sum"
    And User save the check number
    And User logout from pos

    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Guest Service Manager | Welcome2024! | Guest Services |
      | O&R | Concierge | Welcome2024! | Guest Services |
    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
    And User clear reservation status of the crew
    And User search for the guest and open the profile
    And User click on Account statement details
    Then User verify transaction is posted and added to guest folio

  @RetailPointOfSale @POS @Smoke @C1050225 @@RPOS15 @NCL @OCI @NCLH
  Scenario: Validate the functionality of adding multiple quantities to a retail item
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "1200" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    And User swipe "right" on item added to cart
    And User enter quantity "999" in the final quantity inputBox
    Then User verify product quantity able to update in the cart with "999"

  @RetailPointOfSale @POS @Smoke @RPOS16 @C1050226 @NCL @OCI @NCLH
  Scenario: Search for article items via item lookup
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | POS | Welcome2024! | Point of Sales Management |
      | O&R | Sales Associate | Welcome2024! | Point of Sales Management |
#    Then User navigate to menu "Point of Sales Management" service
    Then User click on link "Article Setup"
    And User click on add more filters
    Then User search "Article Group" filters
    And User click on "Article Group" filter and select option "Effy Jewelry" on POS article page
    And User save the code and barcode details on POS articles
    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User search for item code in article search
    And  User verify the article details

  @RetailPointOfSale @POS @Smoke @RPOS17 @C1050227 @NCL @OCI @NCLH
  Scenario: Search for article items via item lookup by item name
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales Management |
      | O&R | Sales Associate | Welcome2024! | Point of Sales Management |
    Then User navigate to menu "Point of Sales Management" service
    Then User click on link "Article Setup"
    And User click on add more filters
    Then User search "Article Group" filters
    And User click on "Article Group" filter and select option "Effy Jewelry" on POS article page
    And User save the code and barcode details on POS articles
    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guest"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User search for item name in article search
    And  User verify the article details

  @RetailPointOfSale @POS @Smoke @C1050228 @RPOS18 @NCL @OCI @NCLH
  Scenario: Search for article items via item lookup by barcode
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | POS | Welcome2024! | Point of Sales Management |
      | O&R | Sales Associate | Welcome2024! | Point of Sales Management |
    Then User navigate to menu "Point of Sales Management" service
    Then User click on link "Article Setup"
    And User click on add more filters
    Then User search "Article Group" filters
    And User click on "Article Group" filter and select option "Effy Jewelry" on POS article page
    And User save the code and barcode details on POS articles
    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User search for item code in article search
    And  User verify the article details

  @RetailPointOfSale @POS @Smoke @C1050229 @RPOS19 @NCL @OCI @NCLH
  Scenario: Validate if the guest can make the purchase using respective payment options for general retail purchase using the Otalio POS
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS User | Welcome2024! | Point of Sales Management |
    Then User navigate to menu "Point of Sales Management" service
    Then User click on link "Article Setup"
    And User click on add more filters
    Then User search "Article Group" filters
    And User click on "Article Group" filter and select option "Effy Jewelry" on POS article page
    And User save the code and barcode details on POS articles
    Then User navigate to menu "Point of Sales" service
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guest"
#    And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User search for item name in article search
    And  User verify the article details

  @RetailPointOfSale @POS @Smoke @RPOS20 @C1050230 @NCL @OCI @NCLH
  Scenario: Validate the ability to view respective details in personal dashboard page
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User navigated to guest Profile page on POS
    Then User verify the Pricelookup, RVC, and Waiter Station is displayed

#  @RetailPointOfSale @POS @Smoke @RPOS21 @C1050231 @NCL @OCI @NCLH
#  Scenario: Exchange a purchased item
#    Given Login page is displayed
#    When Input credentials to login into nclh portal
#      | NCL | Boutique Manager | Welcome2024! | Point of Sales |
#      | O&R | Boutique Manager | Welcome2024! | Point of Sales |
#    And User clear checks if any present on home page
#    And User select revenue center "LN1-Effy Jewelry"
#    And User navigated to guest list
#    And User save the guestName
#    And User navigated to check number having greater than "0.0"
#    And User return already purchased item
#    And User navigated to guest list
#    Then User verify check amount displayed as "0.00" after returning item

  @RetailPointOfSale @POS @Smoke @RPOS22 @C1050259 @NCL @OCI @NCLH
  Scenario: Verify if comments can be added to a retail item
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "150" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on label "EFFY"
#    And User select item "Effy Jewelry" for adding tip
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    And User navigate to comment page
    And User write comment "250Chars" for thr product in the cart
    Then User verify save button is "enabled"
    Then User click on save button after comment
    Then User verify orange circle appeared at product description at cart

  @RetailPointOfSale @POS @Smoke @RPOS23 @C1050260 @NCL @OCI @NClH
  Scenario: Verify if the added comments are getting reflected in the check detail area
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "150" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on label "EFFY"
#    And User select item "Effy Jewelry" for adding tip
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
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

  @RetailPointOfSale @POS @Smoke @RPOS24 @C1518548 @NCL @OCI @NClH
  Scenario: Validate if the guest's posting status is disabled, the transaction does not post successfully
    Given Create a guest with posting status is disabled
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    And User search for the "Guest3" user and select
#    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    Then User save the selected guest Details
    And User double click on guest profile
    Then User verify the posting status is closed
    And User clicks the close icon in the guest profile container.
    And User click on label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    Then User save the total price displayed on the cart
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button
    Then User verify error toast message appears on the screen "There is no valid buyer"

  @RetailPointOfSale @POS @Smoke @C1050212 @RPOS25 @NCL @OCI @NCLH
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
    And User save selected guestNames list
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

  @RetailPointOfSale @POS @Smoke @RPOS26 @C1634997 @NCL @OCI @NCLH
  Scenario: Ability for another guest to pay for someone else's transaction
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
#    And User select item "Effy Jewelry" for adding tip
    And User open link "payment-pos"
    And User click on split Check By Consumer
    And User perform signature and confirm "POST" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify guest names displayed check confirmation page

  @RetailPointOfSale @POS @Smoke @RPOS27 @C1633614 @NCL
  Scenario: Ability to split postings equally between all guests in a check
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
#    And User select item "LIQUOR BOTTLE" for adding tip
    And User select "2nd" guest for selecting product
    And User click on label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
#    And User select item "LIQUOR BOTTLE" for adding tip
    And User open link "payment-pos"
    And User click on split Check By Consumer
    And User perform signature and confirm "POST" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify guest names displayed check confirmation page

  @RetailPointOfSale @POS @Smoke @C1663412 @NCL @OCI @NCLH
  Scenario: View split postings are reflected in the guest check
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
#    And User select item "LIQUOR BOTTLE" for adding tip
    And User select "2nd" guest for selecting product
    And User click on label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
#    And User select item "LIQUOR BOTTLE" for adding tip
    And User open link "payment-pos"
    And User click on split Check By Consumer
    And User perform signature and confirm "POST" button
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    Then User verify guest names displayed check confirmation page

  @POSAdministration @POS @Smoke @NCL @OCI @NCLH @C1050218
  Scenario: Verify if the user can search and select a check to void
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Beach Bar"
    And User navigated to guest list
#    And User save the guestName
    And User navigated to check number having greater than "0.0"
    And User return already purchased item
    And User navigated to guest list
    Then User verify check amount displayed as "0.00" after returning item

  @RetailPointOfSale @POS @Smoke @C1050256 @RPOS29 @NCL @OCI @NCLH
  Scenario: Validate if the guest can digitally sign a retail check before the payment is processed
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "50" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    Then User save the total price displayed on the cart
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button

  @RetailPointOfSale @Smoke @RPOS30 @C1050215 @NCL @OCI @NCLH
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

  @RetailPointOfSale @POS @Smoke @C1042125 @C1707494 @C1483380 @C1483379 @C1042135 @C1457370 @C1663398 @C1663398
  Scenario: Verify if the POS user can initiate a check in the POS System, Server name is associated to the table number, Upon closing the check, the guest is able to sign via electronic signature
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
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
    Then User verify saved guest having purchased price under "Total Sum"

    Given Login page is displayed
    When Input credentials to login into nclh
      | Guest Services | Welcome2024! | Guest Services |
#    Then User navigate to menu "Guest Services" service
    And User open service "Guest Search" item
#    And User clear reservation status of the crew
    And User search for the guest and open the profile
    And User click on Account statement details
    Then User verify transaction is posted and added to guest folio
    Then User click on chain icon at top right pane.
    And User click on "Logs"
    Then User verify Application Logs, Database Logs, Synchronization Logs displayed for the user with the log activity


  @RetailPointOfSale @POS @Smoke @C1042101 @C1050202
  Scenario: Transaction is posted in Otalio and attached to guest folio for the SKU99 items
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
   # And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "150" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "EFFY"
    And User select sku item "Effy Jewelry"
    And User swipe right direction on item added to cart
    And User enter quantity "2" in the final quantity inputBox
    And User perform reorder from the more options
    Then User verify products count doubled in the cart

  @RetailPointOfSale @POS @Smoke @C1050253  @C1042124 @C1042120 @C1050253
  Scenario: Verify if the quantity can be added by right swipe on the item in the check detailed area, Verify the maximum quantity of a retail item to be ordered in the Retail RVC (> 999), Verify if the selected article is removed by left swipe on the item in the check detailed area
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "1200" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    And User swipe "right" on item added to cart
    And User enter quantity "999" in the final quantity inputBox
    Then User verify product quantity able to update in the cart with "999"
    And User swipe "left" on item added to cart
    Then User verify product disappear from the cart
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    And User swipe "right" on item added to cart
    And User enter quantity "1000" in the final quantity inputBox
    Then User verify error toast message appears on the screen with message "articleQuantityDTOs[0].quantity maximum value=999"

  @RetailPointOfSale @POS @Smoke @NotAllura @C2956340
  Scenario: View that tip amount can be added by dollar amount or percentage
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales |
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

  @RetailPointOfSale @POS @Smoke @C1472021 @C1042039 @C1042038 @C1042035 @C1042036 @C1042037 @C1472016 @C1438821
  Scenario: Ensure comments of the items are added to the guest receipt
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales |
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
    And User write comment "251Chars" for thr product in the cart
    Then User verify save button is "disabled"
    And User write comment "250Chars" for thr product in the cart
#    Then User verify save button is "enabled"
#    Then User click on save button after comment
    Then User verify orange circle appeared at product description at cart
    And User open link "payment-pos"
    And User perform signature and confirm "POST" button
    And User navigated to guest list
    And User navigated to latest check on the list
    Then User verify the print receipt having guest name and comment
    Then User verify able to reprint the statement

  @RetailPointOfSale @POS @Smoke @C1050219 @C1050210
  Scenario: Validate if the guest can make the purchase using respective payment options for general retail purchase using the Otalio POS
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    Then User verify no check and home page is clear
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guests"
    #And User search for the "Guest1" user and select
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
    Then User verify saved guest having purchased price under "Total Sum"
    #Then User verify associate name "QA POS Shop User" in the table
    And User save the check number
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
    Then User click on chain icon at top right pane.
    And User click on "Logs"
    Then User verify Application Logs, Database Logs, Synchronization Logs displayed for the user with the log activity

  @RetailPointOfSale @POS @Smoke @C1050199 @C1050207
  Scenario: Validate if the guest can make the purchase using respective payment options for general retail purchase using the Otalio POS
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales Management |
#    Then User navigate to menu "Point of Sales Management" service
    Then User click on link "Article Setup"
    And User save the code and barcode details on POS articles
    Then User click on code in POS articles
    And User enable  toggle "Enabled"
    Then User verify the Enabled toggle button status
#    Then User verify the code in articles setup page

  @RetailPointOfSale @POS @Smoke
  Scenario: Link RVC associated to each Retail article
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS User | Welcome2024! | Point of Sales Management |
    Then User navigate to menu "Point of Sales Management" service
    Then User click on link "Article Setup"
    And User click "Add" plus button on POS article page
    Then User enter article details on article creation page
    And User click on "Create"
    And User click on "code" and search for article code
    Then User verify created new article details

  @RetailPointOfSale @POS @Smoke
  Scenario: Validate if the SKU99 article information is available in the Otalio Management system for the sales representative
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS User | Welcome2024! | Point of Sales Management |
    Then User navigate to menu "Point of Sales Management" service
    Then User click on link "Article Classes"
    And User click on code in article class page
    And User click on "Parameter Settings"
    Then User verify articles should be tagged as Available:Priced Article Selected: Open Price under the parameter settings

  @RetailPointOfSale @POS @Smoke
  Scenario: Validate if discounts can be applied to the retail items
    Given Login page is displayed
    When Input credentials to login into nclh
      | POS | Welcome2024! | Point of Sales |
    And User clear checks if any present on home page
    And User select revenue center "LN1-Effy Jewelry"
    And User click on "Add Check" button
    And User select tab "Guest"
    #And User search for the "Guest1" user and select
    And Search guest and select having min sufficient "1200" funds
    And User save selected guestName
    And User click on "CREATE CHECK" button
    And User click on pos label "EFFY"
    And User select gift item "Effy Jewelry"
    And User add price if the product not having price
    And User double click on the retail item in the check detailed area
    Then user click on "Discount" tab

  @RetailPointOfSale @Smoke @RPOS31 @C1050178
  Scenario Outline: Verify if the Boutique Manager can view the gift shop reports folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Gift Shop"
    And User click on "Gift Shop - Daily Postings" code link

    Examples:
      | Service   |
      | Reporting |

  @RetailPointOfSale @Smoke @RPOS32 @C1050180
  Scenario Outline: Verify if the user can export the gift shop report
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Gift Shop"
    And User click on "Gift Shop - Daily Postings" code link

    Examples:
      | Service   |
      | Reporting |

  @RetailPointOfSale @Smoke @RPOS32 @C1050181
  Scenario Outline: Verify if the user can email the gift shop report
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Sales Associate | Welcome2024! | Point of Sales |
      | O&R | Sales Associate | Welcome2024! | Point of Sales |
    Then User navigate to menu "<Service>" service
    And User click on "Report Overview"
    Then User select the Folder as "Gift Shop"
    And User click on "Gift Shop - Daily Postings" code link

    Examples:
      | Service   |
      | Reporting |










