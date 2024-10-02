@echo off
cd /d C:\Users\seifr\Main\MyRepositories\Api_Automation_Extend

:: Step 1: Run tests and generate Allure results
echo Running tests...
call mvn clean test

:: Step 2: Generate Allure report
echo Generating Allure report...
call mvn allure:report

:: Step 3: Open the Allure report in the browser
echo Checking if the report exists and opening...
if exist "%cd%\target\site\allure-maven-plugin\index.html" (
    echo Report found. Opening in the browser...
    start "" "%cd%\target\site\allure-maven-plugin\index.html"
) else (
    echo Report not found. Please check the test execution logs.
)
