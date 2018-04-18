@GetApi
Feature: Get response from test API and validate status code and response body


  @testStatusCode
  Scenario Outline: Get response and validate status code
    Given I create GET request for "<testAPIName>"
    When I send GET request to the API
    Then I validate StatusCode of the received response

    Examples: 
      | testAPIName |
      | TestAPI0    |
     | TestAPI3    |



  @testJsonKey
  Scenario Outline: Get response and validate specific json key
    Given I create GET request for "<testAPIName>"
    When I send GET request to the API
    Then I validate jsonKey "<jsonKey>" of the received response

   Examples:
      | testAPIName | jsonKey      |
      | TestAPI1    | headers.host |

