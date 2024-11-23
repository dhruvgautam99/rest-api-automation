Feature: Example REST API Test

  Scenario: Verify API response
    Given the API endpoint "https://api.example.com/resource"
    When I send a GET request
    Then the response status code should be 200