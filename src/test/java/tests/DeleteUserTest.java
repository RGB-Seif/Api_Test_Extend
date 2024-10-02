package tests;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseTest {

    @Test
    @Description("TC004: Delete User Test")
    public void deleteUser() {
        Response response = DeleteRequest();
        validateStatusCode(response);
    }

    @Step("DELETE request to remove user with ID 4")
    private Response DeleteRequest() {
        return given()
                .when()
                .delete("/api/users/4")
                .then()
                .log().all()
                .extract().response();
    }


    @Step("Validating the response status code")
    private void validateStatusCode(Response response) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, 204, "Status code should be 204 No Content");

        // Add the status code as an attachment to the Allure report
        Allure.addAttachment("Response Status Code", String.valueOf(actualStatusCode));
    }
}
