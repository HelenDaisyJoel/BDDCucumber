Feature: Entertainment

  @ET111 @NotDependent
  Scenario Outline:Validate that the user is able to add all the required event type details while creating event type
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Event Types"
    And User save the parent window id
    And User switch to new window
   # And User add an event
    And User verify the error page is displayed
    Examples:
      | Service          |
      | Activity Planner |

   @ET1  @Smoke @NotDependent @C1409507 @Sanity @C2956325
  Scenario Outline:Validate that the user is able to add all the required event type details while creating event type
    Given Login page is displayed
     When Input credentials to login into nclh portal
    #NCL
       | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Event Types"
    And User save the parent window id
#    Given Login page is displayed
#    When Input credentials to login into nclh
#      | Entertainment | Perform2025! | Activity Planner |
    And User add an event
    And User enter the code
    And User Enter the Description field as "test"
    And User select category as "Events"
    And User enable  toggle "Is Event"
    And User switch to Events tab
    Then user set the default time as "00:30"
    Then user set the default  cleanup time as "01:30"
    And User enter the entertainment options as "test option"
    And User add default required personal as "1st Bosun"
    And User enter the min value as "2" and max value as "10"
    And User click on "Save"
    And User click on "Add"
    And User switch back to "Otalio - Setup Service" window
    And User set the description filter
    And User verify the code

    Examples:
      | Service          |
      | Activity Planner |

   @ET2  @Smoke @NotDependent @C1409514
  Scenario Outline:Validate that user is able to add item codes for services and features.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Item Codes"
    And User save the parent window id
    And User add an icon
    And User enter the code
    And User Enter the Description EN as "test"
    And User click on "Create"
    And User switch back to "Otalio - Setup Service" window
    And User set the description EN filter
    And User verify the code

    Examples:
      | Service          |
      | Activity Planner |

   @ET3  @Smoke @NotDependent @C1409509
  Scenario Outline:Verify If a guest is assigned to the ticket, ensure that a service request is not created when check box is unselected
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Event Types"
    And User save the parent window id
    And User add an event
    And User enter the code
    And User Enter the Description field as "test"
    And User select category as "Events"
    And User enable  toggle " Is Event "
    And User click on "Add"
    And User switch back to "Otalio - Setup Service" window
    And User set the description filter
    And User verify the code

    Examples:
      | Service          |
      | Activity Planner |

   @ET4   @Smoke @NotDependent @C1409510
  Scenario Outline:Validate that user is able to add entertainment options and requirements for an event setup
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Event Types"
    And User save the parent window id
    And User add an event
    And User enter the code
    And User Enter the Description field as "test"
    And User select category as "Events"
    And User enable  toggle "Is Event"
    And User switch to Events tab
    And User enter the entertainment options as "test option"
    And User enter the venue Requirements as "test venue"
    And User click on "Add"
    And User switch back to "Otalio - Setup Service" window
    And User set the description filter
    And User verify the code
    And User click on the code
    And User switch to Events tab
    And User Verify entertainment options and venue Requirements

    Examples:
      | Service          |
      | Activity Planner |

   @ET5  @Smoke @NotDependent @C1409511
  Scenario Outline:Validate that the user is able to add special instructions for an event setup
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Event Types"
    And User save the parent window id
    And User add an event
    And User enter the code
    And User Enter the Description field as "test"
    And User select category as "Events"
    And User enable  toggle "Is Event"
    And User switch to Events tab
    And User enter the special Instruction field as "test Instruction"
    And User click on "Add"
    And User switch back to "Otalio - Setup Service" window
    And User set the description filter
    And User verify the code
    And User click on the code
    And User switch to Events tab
    And User Verify special Instructions

    Examples:
      | Service          |
      | Activity Planner |

  @ET6  @Smoke @NotDependent @C1409512
  Scenario Outline:Validate that user is able to add clean-up and setup time while setting up an event type
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Event Types"
    And User save the parent window id
    And User add an event
    And User enter the code
    And User Enter the Description field as "test"
    And User select category as "Events"
    And User enable  toggle "Is Event"
    And User switch to Events tab
    Then user set the default time as "00:30"
    Then user set the default  cleanup time as "01:30"
    And User click on "Add"
    And User switch back to "Otalio - Setup Service" window
    And User set the description filter
    And User verify the code
    And User click on the code
    And User switch to Events tab

    Examples:
      | Service          |
      | Activity Planner |


   @ET8  @Smoke @NotDependent @C1409515  @C1409508
  Scenario Outline:Validate that user is able to add if item is fixed to a location while adding the item code.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Item Codes"
    And User add an icon
    And User enter the code
    And User Enter the Description EN as "test"
    And User switch to Events tab
    And User enable  toggle "Fixed to Location"
    And User select location as "Bullseye"
    And User click on "Create"
    And User switch back to "Otalio - Setup Service" window
    And User set the description EN filter
    And User verify the code

    Examples:
      | Service          |
      | Activity Planner |

   @ET9  @Smoke @NotDependent @C1409516
  Scenario Outline:Validate that user is able to view/define priced by while creating the item code.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Item Codes"
    And User add an icon
    And User enter the code
    And User Enter the Description EN as "test"
    And User switch to Events tab
    And User enable  toggle "Fixed to Location "
    And User select location as "Bullseye"
    And User select priced by as "Calculated Persons"
    And User type the Quantity Available as "4"
    And User click on "Create"
    And User switch back to "Otalio - Setup Service" window
    And User set the description EN filter
    And User verify the code

    Examples:
      | Service          |
      | Activity Planner |

   @ET10  @Smoke @NotDependent @C1409520
  Scenario Outline:Validate that user is able to add the item code details in Otalio Application.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Item Codes"
    And User add an icon
    And User enter the code
    And User Enter the Description EN as "test"
    And User switch to Events tab
    And User enable  toggle "Fixed to Location "
    And User select location as "Bullseye"
    And User select priced by as "Calculated Persons"
    And User click on "Create"
    And User switch back to "Otalio - Setup Service" window
    And User set the description EN filter
    And User verify the code
    And User click on the code
    And User switch to Events tab

    Examples:
      | Service          |
      | Activity Planner |


  @Entertainment @ET11  @Smoke  @NotDependent @C2110428 @Smoke @C2956333 @C2956335
  Scenario Outline:Validate that user is able to view the created events in the calendar view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Quick Add"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User delete the event
    And User click on "Confirm"
    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @ET12 @Entertainment @NotDependent @C1409493 @Smoke
  Scenario Outline:Validate that user is able to select the free seating option in ticketing type of event while adding the new event.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Add"
    And User switch to new window
    #And User click on "close"
    #And User wait for one minute
    And User enable  toggle "Ticketing Event"
    And User verify user is able to select the "Free Seating" option in ticketing type of event while adding the new event.
    And User click on "Add & Finish"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User delete the event
    And User click on "Confirm"


    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @ET13 @Entertainment @NotDependent @C1409521 @Smoke @C1409522
  Scenario Outline:Validate that user is able to add an event via quick add in the calendar view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
   # And User selects a past time should see an error message
    And User Select an unoccupied square on the calendar view
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User enable  toggle "Private "
    And User click on "Quick Add"
    #And User get timeList of all events
    Then User delete the event
    And User click on "Confirm"
    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET14  @NotDependent @C1409492    @Smoke
  Scenario Outline:Validate that user is able to add an event via add in the calendar view.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User enable  toggle "Private "
    And User click on "Add"
    And User switch to new window
    And User Enter the Description field as "test"
    And User enter the Expected Attendance count as "5"
    And User click on "Event Owner (Group)"
    And User enable  toggle "Ticketing Event "
    And User verify user is able to select the "Free Seating" option in ticketing type of event while adding the new event.
    And User click on " Add & Finish "
    And User open service "<ServiceName>" item
    Then User delete the event
    And User click on "Confirm"


    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @ET15  @NotDependent  @C1409518 @Smoke @C2956337
  Scenario Outline:Validate that user is able to delete/Cancel the created events in the event overview.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User enable  toggle "Private "
    And User click on "Quick Add"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User delete the event
    And User click on "Confirm"
    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET16  @NotDependent @C1409527  @Smoke  @C1409517
  Scenario Outline:Validate that user is able to add the new note to an existing event while adding the event.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! |Activity Planner|
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Quick Add"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User double click on event
    And User switch to new window
    Then User click on Notes & History
    And User type the note field as "test" and check the client note checkbox
    And User click on "Save" for add note
    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET17  @NotDependent  @Smoke @C1409495
  Scenario Outline:Validate that user is able to add the participants for the event while adding the event
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Add"
    And User switch to new window
    #And User click on "close"
    #And User wait for one minute
    And User enable  toggle "Ticketing Event"
    And User verify user is able to select the "Free Seating" option in ticketing type of event while adding the new event.
    And User click on "Add & Finish"

    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET18  @Smoke @NotDependent @C1409530 @C2956309 @C2956310 @C1409497
  Scenario Outline:Validate that user is able to view the available event locations.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on " Event Locations "
    And User switch to new window
    And Verify user is able to view the available event locations.

    Examples:
      | Service          |
      | Activity Planner |

  @Entertainment @ET19  @Smoke  @NotDependent @C1409523	 @Smoke
  Scenario Outline:Validate that user is able to add the participants for the event while adding the event
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Quick Add"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User double click on event
    And User switch to new window
    And User click on Participants
    And User add the participants
    And User click on "Add Selected"
    And User click on "Save & Finish"

    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |



  @Entertainment @ET20  @Smoke @NotDependent @C1409531
  Scenario Outline:Validate that user is able to view the Event types
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Event Types"
    And User switch to new window
    And Verify user is able to view the available event types.

    Examples:
      | Service          |
      | Activity Planner |

  @Entertainment @ET21 @C1409524 @C2956338 @C3094160
  Scenario Outline:Validate that user is able to view all the timeslots and events in the Calendar View with respect to day/week/month.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Quick Add"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    And User click on "Timeline Week"
    And User get timeList of all events
    And User click on "Timeline Month"
    And User get timeList of all events
    Then User delete the event
    And User click on "Confirm"

    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |


  @Entertainment @ET22 @C2956341
  Scenario Outline:Validate that application is able to cancel the group reservation after creating the group events.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category as "group"
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Quick Add"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User delete the event
    And User click on "Confirm"

    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET23  @NotDependent  @Smoke @C1409491
  Scenario Outline:Validate that user is able to create event and add item details
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Add"
    And User switch to new window
    And User click on "close"
    And User wait for one minute
    And User click on Items
    And User click on Add item
    And User select Item Dropdown
    And User select Quantity
    And User click on "Save"
    And User click on "Add & Finish"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User delete the event
    And User click on "Confirm"
#    And User enable  toggle "Ticketing Event"
#    And User verify user is able to select the "Free Seating" option in ticketing type of event while adding the new event.
    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET24 @C1409528
  Scenario Outline:Validate that user is unable to add an event before the location opening timings
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category as "group"
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    Then User enter "Start Date Time" date range
#    And User save time of the event
#    And User click on "Quick Add"
#    And User open service "<ServiceName>" item
#    And User get timeList of all events
#    Then User delete the event
#    And User click on "Confirm"

    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET25  @Smoke  @NotDependent @C2858498	 @Smoke
  Scenario Outline:Validate that user is able to create tendering event and add guests
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option as Tender operation
    And User select the location dropdown
    And User save time of the event
    And User click on "Add"
    And User switch to new window
#    And User click on "Quick Add"
#    And User open service "<ServiceName>" item
#    And User get timeList of all events
#    Then User double click on event
#    And User switch to new window
    And User click on Participants
    And User add the participants
    And User click on "Add Selected"
    And User click on " Add & Finish "
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User delete the event
    And User click on "Confirm"

    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET26  @NotDependent  @Smoke @C2679793
  Scenario Outline:Validate that user is able to set prices when adding selected items to existing event
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Add"
    And User switch to new window
#    And User click on "close"
#    And User wait for one minute
    And User click on Items
    And User click on Add item
    And User select Item Dropdown
    And User select Quantity
    And User click on "Save"
    And User click on "Add & Finish"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User delete the event
    And User click on "Confirm"
    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET27 @C1409525
  Scenario Outline: Validate that user is able to view existing notes in the history while adding the event.
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! |Activity Planner|
    Then User navigate to menu "<Service>" service
    And User click on "Add Event"
    And User click on "Event Category"
    And user click on option for category
    And User click on "Event Type"
    And user click on option
    And User select the location dropdown
    And User save time of the event
    And User click on "Quick Add"
    And User open service "<ServiceName>" item
    And User get timeList of all events
    Then User double click on event
    And User switch to new window
    Then User click on Notes & History
    And User type the note field as "test" and check the client note checkbox
    And User click on "Save" for add note
    Examples:
      | Service          | ServiceName    |
      | Activity Planner | Event Overview |

  @Entertainment @ET28 @C2393362
  Scenario Outline: Validate that user is able to view existing reservation for guest
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Cruise Information" details
    And User click on booking info

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |

  @Entertainment @ET29 @C2679791
  Scenario Outline: Validate that user is able to view guests and filter on age
    Given Login page is displayed
    When Input credentials to login into nclh portal
    #NCL
      | NCL | Entertainment | Welcome2024! | Activity Planner |
    Then User navigate to menu "<Service>" service
    And User open service "<ServiceName>" item
    And User search guest with "GuestUser"
    Then User save guest first name
    Then User save guest full name
    And User set age filter to be adult
    And User save guest firstName excluding sensitive cabins
    And User navigated to user "GuestUser" profile page
    And User open submenu "Cruise Information" details
    And User click on booking info

    Examples:
      | Service        | ServiceName  | GuestFirstName   |
      | Guest Services | Guest Search | DynamicFirstName |




