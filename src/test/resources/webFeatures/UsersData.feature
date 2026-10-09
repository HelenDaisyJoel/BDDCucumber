Feature: UsersData

@UsersData
Scenario: Get Users data
Given Login page is displayed
When Input credentials to login into nclh
| Guest Services | Welcome2024! | Guest Service |
Then User navigate to menu "Guest Service" service
And User save the port name
And User open service "Guest Search" item
And User clear reservation status of the crew
Then User select "Checked In" option from the filter
And User add "Onboard" filter to guest service page
And User select "Onboard" filter option to "Yes"
And User open hidden filters
And User set age filter to be adult
Then User save list of guest full name by excluding prefix
Then User navigate to menu "Crew Services" service
And User open service "Crew Search" item
Then User save list of crew full name by excluding prefix
And User get "guest" full name from saved list
And User get "crew" full name from saved list
