package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import utils.RequestSpec;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class PostRequestStepDefinitions {

    private String endpoint;
    private String payload;
    private Response response;

    private  RequestSpecification requestSpec = RequestSpec.getRequestSpecification();

    @Given("I have a valid token for user {string} service")
    public void i_have_a_valid_token_for_user_service(String service) {
        // Retrieve the token dynamically (replace with actual API if available)
        String acessTokenKey='access_token';
        String token = getAccessToken(service);
        this.getToken();

        // Add the Bearer token to the RequestSpecification
        requestSpec = RequestSpec.getRequestSpecification().auth().oauth2(token);
    }

    private void getToken () {
    }

    private String getAccessToken(String service) {
        // Mock dynamic token retrieval for the service (replace with actual token endpoint)
        if ("test".equalsIgnoreCase(service)) {
            Response authResponse = given()
                    .baseUri("https://auth.example.com")  // Replace with actual auth URL
                    .contentType("application/json")
                    .body("{ \"username\": \"test\", \"password\": \"password\" }")
                    .when()
                    .post("/token"); // Replace with actual token endpoint

            // Extract and return the access token
            return authResponse.jsonPath().getString("access_token");
        } else {
            throw new IllegalArgumentException("Unsupported service: " + service);
        }
    }
    @Given("the API endpoint for post {string}")
    public void the_api_endpoint(String url) {
        endpoint = url;
    }

    @Given("the following payload:")
    public void the_following_payload(String jsonPayload) {
        payload = jsonPayload;
    }

    @When("I send a POST request")
    public void i_send_a_post_request() {
        response = given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(endpoint);
    }

//    @Then("the response status code should be {int}")
//    public void the_response_status_code_should_be(int expectedStatusCode) {
//        response.then().statusCode(expectedStatusCode);
//    }

    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String key) {
        response.then().body(key, notNullValue());
    }
}
