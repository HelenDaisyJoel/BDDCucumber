Feature: Excursion Management

  @ExcursionManagement @Smoke @C1088320
  Scenario: Validate that user can create a new tour activity
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User clicks on the "Tour Activities Setup" link
    And User "view" Add new Tour Activity plus button on Tour Activities
    And User "click" Add new Tour Activity plus button on Tour Activities
    And User enters "General Details" details in the "Article General Information" section on Tour Activity
    And User enters "General Details" details in the "Operations" section on Tour Activity
    Then User save the general details
    And User enters "Departures, Prices, Sales & Fees Details" details in the "departure" section on Tour Activity
    And User views the "Save & View Tour Activity" button
    And User click on "Save & View Tour Activity"
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

  @ExcursionManagement @Smoke @C1088321
  Scenario: Validate that user can create a bookable tour using a template
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User able click on the "Tour Activities Setup"
    And User view "Add new Tour Activity" plus button on Tour Activities
    And User click "Add new Tour Activity" plus button on Tour Activities
    Then  User enter general details on tour activity new
    And User click "Departures, Prices, Sales & Fees Details" tab on Tour Activity New
    Then User enter departure, price, sales,and feed details on tour activity new
    And User view save button on Tour Activity New
    And User click save button on Tour Activity New
    And User click on "code" and search for template code
    Then User verify created new template details

  @ExcursionManagement @Smoke @C1088323
  Scenario: Validate that posted excursion are displayed in guest folio after a booking
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User "Book" tour for guest
    Then Guest must have a posted booked excursion
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Account Statement" details
    Then User verifies the booking details

  @ExcursionManagement @Smoke @C1088324
  Scenario: Verify booking details in the Excursions booking overview
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "Book" tour for guest
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    Then User select booked date and click booked tours
    Then User search guest first name
    Then the user views the booking information

  @ExcursionManagement @Smoke @C1088328
  Scenario: Validate that user can book a tour for one guest and additional guests from the same reservation
    Given multiple new guest users are created as "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User selects multi guest members
    And User click on "Select"
    And User clears the date from the calendar field
    And User select the tour and hit Book
    And User view post button
    And User click on "Post"
    And User draws signature if status label exists
    Then User validates the booking for multiple guests
    Then User search multi-guest "firstguestFirstName" name
    Then User search multi-guest "secondguestFirstName" name

  @ExcursionManagement @Smoke @C1088331
  Scenario: Validate that search and view booking information for guests by last name, reservation number, or cabin number
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "Book" tour for guest
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Participants Profile"
    And User click on "Participants Profile"
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User select a guest name
    And User save selected guest name from the guest search
    And User views the "Select" button
    And User click on "Select"
    Then User views the respective guest booking information by last name, reservation number, or cabin number

  @ExcursionManagement @Smoke @C1088322
  Scenario: Verify that the user is able to add the transportation management language to already created tour template
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Tour Activities Setup"
    And User able click on the "Tour Activities Setup"
    And User Click on the Edit icon in Tour Activities page
    Then User scrolls to Transportation Management
    And User checks edit icon and handles language
    And User clicks on Edit pencil icon
    And User edits the selected language
    Then User View language Save button
    And User click language Save button
    Then User validates the language is edited

  @ExcursionManagement @Smoke @C1088334
  Scenario: Validate that user can view a guest special needs at time of booking an excursions
    Given A new guest user is created "Onboard"
    Then User adds a special needs guest member
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User searches for a special needs guest
    Then User verifies the special needs information on the guest tile
    And User refresh the page
    And Clicks on "Guest Services" module
    Then User navigate to menu "Guest Services" service
    Then User wait for "Guest Search" display
    And User open service "Guest Search" item
    And User search for the special guest
    And User able to click "lastName" in the detail table
    And User open submenu "Personal Information" details
    And User clicks on the "Special Needs" section

  @ExcursionManagement @Smoke @C1088342
  Scenario: Validate that user cannot create a new tour activity without filling in all mandatory fields
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Tour Activities Setup"
    And User able click on the "Tour Activities Setup"
    And User view "Add new Tour Activity" plus button on Tour Activities
    And User click "Add new Tour Activity" plus button on Tour Activities
    Then User enter unmandatory fields in  new tour activity
    And User views the "Save" button
    And User views the "Save & View Tour Activity" button
    Then User verify the Save button is disabled

  @ExcursionManagement @Smoke @C1699420
  Scenario: Validate prices for tour by age group (Infant, Minor, Adult)
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    And User clears the date from the calendar field
    And User click on view icon
    And User check the pricing by age group of each tour

  @ExcursionManagement @Smoke @C3194619
  Scenario: Verify that the user is able to delete the transportation management language to already created tour template
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Tour Activities Setup"
    And User able click on the "Tour Activities Setup"
    And User Click on the Edit icon in Tour Activities page
    Then User checks the transportation management language
    Then User scrolls to Transportation Management
    Then User stores delete button count
    Then User view delete button icon
    And User delete the language in the Transportation Management
    Then User verifies the language is deleted

  @ExcursionManagement @Smoke @C1906493
  Scenario: Validate that tour booking is created for minor with correct age group pricing
    Given a new minor guest user is created with status "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User select a guest name
    And User save selected guest name from the guest search
    Then User verifies that "Minor" guest type is displayed in the selected guest section
    And User views the "Select" button
    And User click on "Select"
    And User clears the date from the calendar field
    And User Book a tour for selected "Minor" guest
    And User click on "Post"
    And User draws signature if status label exists
    Then User validates the minor tour booking details
    Then User verifies that "Minor" value is displayed in the table

  @ExcursionManagement @Smoke @C1906495
  Scenario: Validate that tour booking is created for adult with correct age group pricing
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User select a guest name
    And User save selected guest name from the guest search
    Then User verifies that "Adult" guest type is displayed in the selected guest section
    And User views the "Select" button
    And User click on "Select"
    And User clears the date from the calendar field
    And User Book a tour for selected "Adult" guest
    And User click on "Post"
    And User draws signature if status label exists
    Then User validates the minor tour booking details
    Then User verifies that "Adult" value is displayed in the table

  @ExcursionManagement @Smoke @C2110374
  Scenario: Verify that the user can successfully overbook a waitlisted shore excursion
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "overbook" tour for guest
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    Then User select booked date and click booked tours
    And User select tab "Waitlist Overview"
    Then User search guest first name
    Then the user views the booking information
    And Guest name should be displayed and the guest checkbox is selected
    Then User clicks on the three-dot button and views the "Book" option
    And User click on "Book"
    And User views the "Confirm" button
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

  @ExcursionManagement @Smoke @C2956474
  Scenario: Verify that the Shore Excursions Manager can cancel a guest's tour reservation
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User books a tour for a future date
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Participants Profile"
    And User click on "Participants Profile"
    Then User view on bookings guests add button
    Then User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User searches for a created tour guest
    And User save selected guest name from the guest search
    And User click on "Select"
    And User able to click "checkBox" in the detail table
    Then User clicks on the three-dot button and views the "Cancel Direct Not-Posted" option
    And User click on "Cancel Direct Not-Posted"
    And User views the "Confirm" button
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Records Voided successfully"

  @ExcursionManagement @Smoke @C1088325
  Scenario: Validate that user can view booking information including participant, price type, booking status, booking number and port details
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "Book" tour for guest
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    Then User select booked date and click booked tours
    Then User search guest first name
    Then the user views the booking information

  @ExcursionManagement @Smoke @C2110375
  Scenario: Validate that the user is able to manage the waitlist by viewing and tracking guests with tours in waitlist status
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "overbook" tour for guest
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    Then User select booked date and click booked tours
    And User select tab "Waitlist Overview"
    Then User search guest first name
    Then the user views the booking information
    And Guest name should be displayed and the guest checkbox is selected
    Then User clicks on the three-dot button and views the "Book" option
    And User click on "Book"
    And User views the "Confirm" button
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record created successfully"

  @ExcursionManagement @Smoke @C1088327
  Scenario: Validate that system must contain information on guest excursions, including the list of guests booked
    Given multiple new guest users are created as "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "overbook" tour for multi guest
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    Then User select booked date and click booked tours
    And User select tab "Waitlist Overview"
    Then User search multi-guest "firstguestFirstName" name
    Then User search multi-guest "secondguestFirstName" name

  @ExcursionManagement @Smoke @C1088340
  Scenario: Validate that user can see which sales channels are enabled from the Tour activities setup
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User clicks on a tour from the list after filtering by date
    And User navigates to the "Departures, Prices, Sales & Fees Details" tab
    Then User scrolls down to the Sales section of the page
    Then User validates that the enabled sales channels are displayed from the Tour Activities setup

  @ExcursionManagement @Smoke @C1088353
  Scenario: Validate that the user can see cancelled reservations for a tour
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User books a tour for a future date
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    Then User select booked date and click booked tours
    And User navigates to the "Bookings Overview" tab
    Then User changes the guest status to "Cancel Direct Not-Posted"
    Then User should be able to view the "Cancelled" reservations

  @ExcursionManagement @Smoke @C2110376
  Scenario: Validate that the user is able to reconcile tour booking numbers
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User clicks on a tour from the list after filtering by date
    And User navigates to the "Departures, Prices, Sales & Fees Details" tab
    Then User view on the edit icon for the departure time
    And User click on the edit icon for the departure time
    And User add the Actual Agent Counts by age groups for the departure
    And User click on the "Save"
    And User navigates to the "Departures, Prices, Sales & Fees Details" tab
    Then User is able to reconcile actual tour attendance numbers

  @ExcursionManagement @Smoke @C3315748
  Scenario: Validate that tour information is displayed from selecting a tour in the bookings manager screen
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    And User clears the date from the calendar field and selects the date range
    And User click on the full width block
    Then User should be able to view the tour details including reservation number, price, date, port country code, passenger counts, and persons per group

  @ExcursionManagement @Smoke @C2956464
  Scenario: Validate that tour information is displayed from selecting a tour in the bookings manager screen
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User enter the required code
    Then User verifies the searched excursion code matches the selected code

  @ExcursionManagement @Smoke @C2110380
  Scenario: Validate that user is able to post the Unposted Bookings
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Unposted Bookings Handling"
    And User click on "Unposted Bookings Handling"
    And User selects the "Booking Status:" filter, chooses "Unposted Direct", and adds a future date
    And User selects a guest from the checkbox
    And User clicks the three-dot button on the Bookings List page and view "Post"
    And User click on "Post"
    And User selects the Post Excursion Booking time
    And User click on "Confirm"
    And User views the "Confirm" button
    And User click on "Confirm"
    Then User view first next button
    And User clicks the first Next button and navigates to the second next button
    And User click on Choose Operation Group tab Next button
    And User clicks the Post Booking radio button
    And User clicks the Confirm button on the Operation Details page
    Then User verifies the Success Count and Status

  @ExcursionManagement @Smoke @C2408116
  Scenario: Validate that the user is able to view the payer information of a booked tour
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "Book" tour for guest
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    Then User select booked date and click booked tours
    Then User search guest first name
    And User scrolls to the Payer column
    Then User verifies that the payer information is displayed

  @ExcursionManagement @Smoke @C3315753
  Scenario: Validate that user is able to book an excursion for a guest
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User select a guest name
    And User views the "Select" button
    And User click on "Select"
    And User clears the date from the calendar field
    Then User verifies that the Post button is enabled

  @ExcursionManagement @Smoke @C1088329
  Scenario: Validate that user can book a tour for one guest and additional guests not in the same reservation
    Given multiple new guest users are created as "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User selects multi guest members
    And User click on "Select"
    And User clears the date from the calendar field
    And User select the tour and hit Book
    Then User verify Successfully toast message appears on the screen with message "2 records were created successfully"

  @ExcursionManagement @Smoke @C2110377
  Scenario: Validate that the user is able to move an existing guest booking to a different day/date and tour
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "Book" tour for guest
    Then Guest must have a posted booked excursion
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    Then User select booked date and click booked tours
    Then User search guest first name
    And Guest name should be displayed and the guest checkbox is selected
    Then User click on three dot button and click on "Move"
    And User click on "Move"
    Then User view first next button
    And User clicks the first Next button and navigates to the second next button
    Then User navigate Operation Details page
    And User changes the date of the selected guest tour
    And User clicks the Post Booking radio button
    And User clicks the Confirm button on the Operation Details page
    Then User verifies the Success Count and Status

  @ExcursionManagement @Smoke @C2956480
  Scenario: Verify that Shore Excursions Manager can update tour capacity
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User view before tour capacity
    And User updates the tour capacity
    And User navigate the tour list
    And User clear today date
    And User searches for the updated tour
    Then User verifies the tour capacity is updated

  @ExcursionManagement @Smoke @C2122598
  Scenario: Verify that user can add Service & Cost Details to a bookable tour
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User clicks on the "Tour Activities Setup" link
    And User "view" Add new Tour Activity plus button on Tour Activities
    And User "click" Add new Tour Activity plus button on Tour Activities
    And User enters "General Details" details in the "Article General Information" section on Tour Activity
    And User enters "General Details" details in the "Operations" section on Tour Activity
    Then User save the general details
    And User enters "Departures, Prices, Sales & Fees Details" details in the "departure" section on Tour Activity
    And User views the "Save & View Tour Activity" button
    And User click on "Save & View Tour Activity"
    And User selects the "Services & Costs Details" tab
    And User clicks the Plus icon and fills in the "Add Cost Service Type & Validity Periods" dialog details
    And User views the "Add" button
    And User click on "Add"
    And User view the success pop up message

  @ExcursionManagement @Smoke @C2878551
  Scenario: Validate that only excursions meeting locations display in meeting location dropdown in excursion departure setup
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User gets the tour details from the booking tour list
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    And User select booked date and click booked tours link
    And User switch to new tab
    And User navigates to the "Departures, Prices, Sales & Fees Details" tab
    Then User view on the edit icon for the departure time
    And User click on the edit icon for the departure time
    And User gets the current Meeting Location dropdown value
    And User clicks on the Meeting Location dropdown

  @ExcursionManagement @Smoke @C4251903
  Scenario: Validate tour weight limits display in the booking screen for an excursion with weight limit
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User updates the Tour Max Weight in Lbs
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    And User clears the date from the calendar field and selects the date range
    And User click on the full width block
    Then User verifies the weight limit is updated

  @ExcursionManagement @Smoke @C1088326
  Scenario: Validate Automatic Age Eligibility Check during tour booking
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    And User "update" a tour with a minimum age restriction
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User select a guest name
    And User click on "Select"
    And User clears the date from the calendar field
    And User selects the tour with the minimum age restriction and clicks the Book button
    And User close pop up
    And User "clear" a tour with a minimum age restriction

  @ExcursionManagement @Smoke @C4372609
  Scenario: Validate user can see availability counts when booking a tour
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    And User clears the date from the calendar field
    And User click on view icon
    Then User verifies the Max and Total Weight details are displayed
    Then User verifies the Min, Max and Available Capacity details are displayed
    Then User verifies the Available and Booked details are displayed

  @ExcursionManagement @Smoke @C3442041
  Scenario: Verify ability to add custom free text for discount reasons
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User "Book" tour for guest
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    Then User select booked date and click booked tours
    Then User search guest first name
    And Guest name should be displayed and the guest checkbox is selected
    Then User click on three dot button and click on "Discount"
    And User click on "Discount"
    And User adds a custom fee for discount reasons
    And User views the "Confirm" button
    And User click on "Confirm"

  @ExcursionManagement @Smoke @C3836219
  Scenario: Validate that prebooked excursions hyperlink in guest profile will take the user to show all prebooked bookings for the guest.
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Guest Services |
      | O&R | Shorex Staff | Welcome2024! | Guest Services |
    Then User "Book" tour for guest
    Then User selects the "Guest Services" service from the left-side panel
    Then User navigate to menu "Guest Services" service
    Then User wait for "Guest Search" display
    And User open service "Guest Search" item
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User clicks the "Shore Excursions" link
    And User switch to new tab
    Then User validates the respective guest Shore Excursions tours

  @ExcursionManagement @Smoke @C1088348
  Scenario: Validate that a booked tour with discount is correctly displayed in guest profile
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User select a guest name
    And User save selected guest name from the guest search
    And User views the "Select" button
    And User click on "Select"
    And User clears the date from the calendar field
    And User select the tour and hit Book
    And User adds a discount to the searched member
    And User view post button
    And User click on "Post"
    Then User selects the "Guest Services" service from the left-side panel
    Then User navigate to menu "Guest Services" service
    Then User wait for "Guest Search" display
    And User open service "Guest Search" item
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User open submenu "Account Statement" details
    Then User validates the applied discount

  @ExcursionManagement @Smoke @C3315762
  Scenario: Validate that user is able to Successfully Book a Tour with Weight Limit Per Person
    Given multiple new guest users are created as "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the tour from Bookings Manager
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Handling item card
    And User navigate "booking-handling" item card
    And User select booked date and click booked tours code link
    And User switch to new tab
    Then User enters the tour maximum weight and maximum weight per guest under the Operations section
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User selects multi guest members
    And User click on "Select"
    And User clears the date from the calendar field
    And User clicks on the tour that contains the added weight limit per person
    And User clicks on the "By Guest" tab
    Then User enters the weight limit for the selected guest and verifies the error message is displayed

  @ExcursionManagement @Smoke @C4372607
  Scenario: Validate implementation of capacities per departure functionality
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User clicks on a tour from the list after filtering by date
    And User navigates to the "Departures, Prices, Sales & Fees Details" tab
    Then User verifies the capacity values for each departure time

  @ExcursionManagement @Smoke @C3868895
  Scenario: Verify that the user is presented with an option to print tickets after completing a tour booking
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User views the Bookings Manager item card
    And User navigate "booking-manager" item card
    Then User view on bookings guests add button
    And User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User select a guest name
    And User click on "Select"
    And User clears the date from the calendar field
    And User select the tour and hit Book
    Then User view Print & Post button
    And User click on "Print & Post"
    Then User verify Successfully toast message appears on the screen with message "Records fetched successfully"

  @ExcursionManagement @Smoke @C2328334
  Scenario: Validate the user is able to see the dispatching of the tour
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Reporting |
      | O&R | Shorex Staff | Welcome2024! | Reporting |
    Then User selects the "Reporting" service from the left-side panel
    Then User display "Report Overview" module
    And User clicks on the "Report Overview" quick link
    And User searches for "Guest Services" report folder and selects it
    And User searches for "NCLH-3358" in the report and clicks the first matching title
    And User enters all required fields NCLH page and clicks on the "Preview" button
    Then User should be able to view the report preview
    And User click on "Print" button
    And User switch to pdf page and get pdf content
    Then User verify content present in the PDF content

  @ExcursionManagement @Smoke @C3868901
  Scenario: Verify display of confirmed guests and their excursion status (posted, unposted-prepaid, unposted-prebooked) in Guest Profile
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "Book" tour for guest
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User click on shorex excursion link
    And User switch to new tab
    Then User verifies the guest status is displayed

  @ExcursionManagement @Smoke @C1088343
  Scenario: Validate that ShoreX Manager can print tickets for a booked tour
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Bulk Ticket Printing"
    And User click on "Bulk Ticket Printing"
    And User select the date of the desired tour user wants to print ticket for
    Then User view first next button
    And User click on Next button
    And User click on Choose Operation Group tab Next button
    When User select radio button "Print Tickets"
    And User selects "Default Printer" from the dropdown
    And User clicks the "Confirm" button in the Operation Details tab
    Then User validate the 1 and print ticket

  @ExcursionManagement @Smoke @C2679786
  Scenario: Validate that user can filter tour activities by sales enabled/disabled and export all filtered records to XLS
    And User clears the downloaded folder
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Tour Activities Setup"
    And User click on "Tour Activities Setup"
    And User click on "Sales Enabled:" filter and select option to "Yes"
    And User export ticket data
    And User verify that pdf is downloaded

  @ExcursionManagement @Smoke @C3855365
  Scenario: Validate that the Add or Edit Comments on Waitlist Bookings from Participant Profile
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User books a tour for a future date
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Participants Profile"
    And User click on "Participants Profile"
    Then User view on bookings guests add button
    Then User click on bookings guests add button
    And User click "Guests" tab on Tour Activity New
    And User select a guest name
    And User click on "Select"
    And User able to click "checkBox" in the detail table
    Then User clicks the three-dot button and highlights "Booking Details"
    And User click on "Booking Details"
    And User navigates to the "Comments" tab
    And User enters random text in the comment box
    And User views the "Save" button
    And User click on "Save"
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"

  @ExcursionManagement @Smoke @C3855366
  Scenario: Validate that the Remove Waitlist Booking from Bookings Handling Screen
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "overbook" tour for guest
    Then User selects the "Excursions" service from the left-side panel
    Then User is able to view "Bookings Handling"
    And User click on "Bookings Handling"
    Then User select booked date and click booked tours
    And User select tab "Waitlist Overview"
    Then User search guest first name
    Then the user views the booking information
    And Guest name should be displayed and the guest checkbox is selected
    Then User clicks on the three-dot button and views the "Remove" option
    And User click on "Remove"
    And User views the "Confirm" button
    And User click on "Confirm"
    Then User verify Successfully toast message appears on the screen with message "Record deleted successfully"

  @ExcursionManagement @Smoke @C3868902
  Scenario: Verify Guests with 'Waitlist' Status Are Displayed in Excursions on Guest Profile
    Given A new guest user is created "Onboard"
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Staff | Welcome2024! | Excursions |
      | O&R | Shorex Staff | Welcome2024! | Excursions |
    Then User "overbook" tour for guest
    Then User selects the "Guest Services" service from the left-side panel
    And User open service "Guest Search" item
    And Search the guest user
    And User able to click "lastName" in the detail table
    Then User view the profile
    And User click on shorex excursion link
    And User switch to new tab
    Then User verifies the guest status is "Waitlist" displayed

  @ExcursionManagement @Smoke @C3868900
  Scenario: Validate that user can enable a sales channel for a specific tour and activate toggle works
    Given Login page is displayed
    When Input credentials to login into nclh portal
      | NCL | Shorex Manager | Welcome2024! | Excursions |
      | O&R | Shorex Manager | Welcome2024! | Excursions |
    Then User selects the "Excursions" service from the left-side panel
    And User clicks on a tour from the list after filtering by date
    And User navigates to the "Departures, Prices, Sales & Fees Details" tab
    Then User view the "Edit" button under Sales
    And User click the "Edit" button under Sales
    And User verifies that the Active toggle is enabled and displayed in blue color
    And User after enables the toggle and clicks the "Save" button
    Then User verify Successfully toast message appears on the screen with message "Record updated successfully"