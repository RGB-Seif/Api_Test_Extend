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

public class PutUserTest extends BaseTest {

    @Test
    @Description("TC003: Update User Test")
    public void updateUser() {
        // Create the test data manually instead of using a Data Provider
        JSONObject testData = new JSONObject();
        testData.put("email", "seif.ragab@gmail.com");
        testData.put("first_name", "Seif");
        testData.put("last_name", "Ragab");
        testData.put("job", "Software Tester");

        String requestBody = testData.toJSONString();

        // Send the PUT request and get the response
        Response response = sendPutRequest(requestBody);

        // Log and validate status code
        validateStatusCode(response);

        // Validate response body
        validateResponseBody(response);

        // Validate response time (should be less than 2000 ms)
        validateResponseTime(response);
    }

    @Step("Sending PUT request with user data")
    private Response sendPutRequest(String requestBody) {
        return given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/api/users/9")  // Assuming the user with ID 9 is being updated
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Validating the response status code")
    private void validateStatusCode(Response response) {
        int actualStatusCode = response.getStatusCode();
        Allure.addAttachment("Response Status Code", String.valueOf(actualStatusCode));

        Assert.assertEquals(actualStatusCode, 200,
                "Expected status code " + 200 + " but got " + actualStatusCode);

        Allure.addAttachment("Status", "PASS - Status Code: " + actualStatusCode);
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
