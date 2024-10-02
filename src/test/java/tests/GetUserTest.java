package tests;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUserTest extends BaseTest {

    @Test
    @Description("TC001: Get User Test")
    @Step("Executing GET request to fetch user data")
    public void getUser() {
        Response response = given()
                .when()
                .get("/api/users/6")
                .then()
                .log().all()
                .extract().response();

        // Validate and log the status code
        validateStatusCode(response);

        // Validate and log the headers
        validateHeaders(response);

        // Validate and log the response body
        validateResponseBody(response);
    }
    @Step("Validating the response status code")
    private void validateStatusCode(Response response) {
        int actualStatusCode = response.getStatusCode();
        Allure.addAttachment("Response Status Code", String.valueOf(actualStatusCode));

        // Assert the status code fail when not equal 200
        assert actualStatusCode == 200 : "Expected status code " + 200 + " but got " + actualStatusCode;
    }

    @Step("Validating response headers")
    private void validateHeaders(Response response) {
        String contentType = response.getHeader("Content-Type");
        Allure.addAttachment("Content-Type Header", contentType);

        // Assert the Content-Type and fail if it doesn't match
        assert contentType.equals("application/json; charset=utf-8") : "Unexpected Content-Type: " + contentType;
    }

    @Step("Validating the response body")
    private void validateResponseBody(Response response) {
        // Perform validation of the response body content
        response.then()
                .body("data.id", equalTo(6))
                .body("data.email", equalTo("tracey.ramos@reqres.in"))
                .body("data.first_name", equalTo("Tracey"))
                .body("data.last_name", equalTo("Ramos"));

        // Attach the full response body to the Allure report
        String responseBody = response.getBody().asString();
        // Get the full response body as a string
        Allure.addAttachment("Response Body", "application/json", responseBody, "json");
    }
}

