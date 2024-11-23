Feature: Test POST API

  Scenario: Send a POST request with payload
    Given the API endpoint for post "/resource"
    And the following payload:
    """
    {
      "name": "John Doe",
      "email": "john.doe@example.com",
      "age": 30
    }
    """
    When I send a POST request
    Then the response status code should be 201
    And the response body should contain "id"