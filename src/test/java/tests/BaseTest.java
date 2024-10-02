package tests;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.lang.reflect.Method;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        Allure.step("Starting tests in BaseTest");
    }
    @BeforeMethod
    public void beforeTestMethod(Method method) {
        Allure.step("Starting test: " + method.getName());
    }

    @AfterMethod
    public void afterTestMethod(Method method) {
        Allure.step("Test completed: " + method.getName());
    }
    @AfterClass
    public void tearDown() {
        Allure.step("All tests in BaseTest have been completed");
    }

}
