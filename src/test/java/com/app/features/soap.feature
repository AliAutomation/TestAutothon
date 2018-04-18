@GetApi
Feature: Test Soap response


  @testStatusCode
  Scenario Outline: Get response and validate status code
    Given I create request for "<testAPIName>" With data "<inputData>"
    When I send request to the API
    Then I validate Soap Response and Status Code

    Examples: 
      | testAPIName |inputData | 
      | TestAPI0    |//intA=4;//intB=8 |
 

  
 @testSoapKey
  Scenario Outline: Get response and validate specific json key
    Given I create request for "<testAPIName>" With data "<inputData>"
    When I send request to the API
    Then I validate soapKey "<soapKey>" of the received response With Expected Data "<soapValue>"
    

   Examples:
      | testAPIName | inputData | soapKey   | soapValue |
      | TestAPI1    |//intA=9;//intB=4 | //SubtractResult | 5 |