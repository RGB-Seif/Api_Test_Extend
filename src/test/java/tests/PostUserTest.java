package tests;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostUserTest extends BaseTest {

    @Test
    @Description("TC002: Post User Test")
    public void createUser() {
        // Create the test data manually instead of reading from a file
        JSONObject testData = new JSONObject();
        testData.put("email", "seif.ragab@gmail.com");
        testData.put("first_name", "Seif");
        testData.put("last_name", "Ragab");
        testData.put("job", "Software Tester");

        String requestBody = testData.toJSONString();

        // Send the POST request and get the response
        Response response = sendPostRequest(requestBody);

        // Log and validate status code
        validateStatusCode(response);

        // Capture and log the created user ID
        captureCreatedId(response);

        // Validate response body
        validateResponseBody(response);

        // Validate response time (should be less than 2000 ms)
        validateResponseTime(response);
    }

    @Step("Sending POST request with user data and image")
    private Response sendPostRequest(String requestBody) {
        return given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Validating the response status code")
    private void validateStatusCode(Response response) {
        int actualStatusCode = response.getStatusCode();
        Allure.addAttachment("Response Status Code", String.valueOf(actualStatusCode));

        Assert.assertEquals(actualStatusCode, 201,
                "Expected status code " + 201 + " but got " + actualStatusCode);

        Allure.addAttachment("Status", "PASS - Status Code: " + actualStatusCode);
    }

    @Step("Capturing and logging the created user ID")
    private void captureCreatedId(Response response) {
        String userId = response.path("id").toString();
        Allure.addAttachment("Created User ID", userId);
        Assert.assertFalse(userId.isEmpty(), "User ID should not be blank.");
    }

    @Step("Validating the response body content")
    private void validateResponseBody(Response response) {
        response.then()
                .body("email", equalTo("seif.ragab@gmail.com"))
                .body("first_name", equalTo("Seif"))
                .body("last_name", equalTo("Ragab"))
                .body("job", equalTo("Software Tester"));

        Allure.addAttachment("Response Body", response.getBody().asString());
    }

    @Step("Validating the response time")
    private void validateResponseTime(Response response) {
        long responseTime = response.getTime();
        Allure.addAttachment("Response Time (ms)", String.valueOf(responseTime));
        Assert.assertTrue(responseTime < 2000,
                "Response time exceeded 2000ms. Actual: " + responseTime + "ms.");
    }
}
