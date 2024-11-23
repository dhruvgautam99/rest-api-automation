package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.equalTo;

public class ExampleStepDefinitions {

    private String endpoint;
    private int statusCode;

    @Given("the API endpoint {string}")
    public void the_api_endpoint(String url) {
        endpoint = url;
    }

    @When("I send a GET request")
    public void i_send_a_get_request() {
        statusCode = RestAssured.get(endpoint).getStatusCode();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        if (statusCode != expectedStatusCode) {
            throw new AssertionError("Expected: " + expectedStatusCode + " but got: " + statusCode);
        }
    }
}
