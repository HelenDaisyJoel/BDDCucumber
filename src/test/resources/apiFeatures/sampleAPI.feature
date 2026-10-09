Feature: Sample API

  Background:
    Given I want to test on Baseurl "https://reqres.in/api"

  @SampleAPI
  Scenario Outline:  Verify response when given a valid credentials
    Given I navigated to resourcePath "/login" and send request "<requestType>"
    And I send request with following payload
      | key                | value      |
      | email              | password   |
      | eve.holt@reqres.in | cityslicka |
#    Then I should get response "200"
#    But Body should not be Null
#    Then Body should contain "token"
    Examples:
      | requestType |
      | GET         |


