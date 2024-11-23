package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    private static final String BASE_URI = "https://api.example.com"; // Replace with your actual base URI

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public static RequestSpecification getBearerRequestSpecification(String token) {
        return getRequestSpecification().auth().oauth2(token);
    }
}
