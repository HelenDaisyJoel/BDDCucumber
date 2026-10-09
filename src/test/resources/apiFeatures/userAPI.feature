Feature: User API

  Scenario: Get a user by ID
    Given the user ID is "1"
    When I send a GET request to "/users/{userId}"
    Then the response status code should be 200
    And the response should contain user ID "1"

  Scenario: Create a new user
    Given the user details are:
      | name      | email               |
      | John Doe  | john.doe@example.com|
    When I send a POST request to "/users"
    Then the response status code should be 201
    And the response should contain the user name "John Doe"