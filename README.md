# API Automation Project

## Overview

This project demonstrates the automation of REST API tests using Java, Rest Assured, TestNG, and Allure for reporting. The automation framework is designed to perform API testing for different HTTP methods such as GET, POST, PUT, and DELETE.

## Technologies Used

- **Java**: Programming language used for writing the test cases.
- **Rest Assured**: For automating RESTful API testing.
- **TestNG**: Test framework used for structuring and executing the test cases.
- **Allure**: For generating detailed reports.
- **Maven**: Build automation tool for managing dependencies and running tests.
  
## Project Structure

- **src/test/java/tests**: Contains all test cases (GET, POST, PUT, DELETE).
- **src/test/resources**: Contains any test data if needed.
- **target/allure-results**: Stores Allure results after test execution.
- **pom.xml**: Maven configuration file that includes all dependencies and plugin configurations.

## Test Scenarios

The following test cases are automated:

- **GET User**: Fetches user details based on a provided user ID.
- **POST User**: Creates a new user using a JSON payload.
- **PUT User**: Updates user information for a given user ID.
- **DELETE User**: Deletes a user based on a provided user ID.

Each test case includes assertions for validating status codes and responses.

## Running the Tests

1. Clone the repository to your local machine:
   ```bash
   git clone <repository-url>
   ```

2. Open the project in **IntelliJ** or your preferred IDE.

3. Ensure that **Maven** is installed and configured correctly.



4. Run the provided batch file to execute tests and view the report automatically:

   ```bash
   run_tests_and_open_report.bat
   ```
