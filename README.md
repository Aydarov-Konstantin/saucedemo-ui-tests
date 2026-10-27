<div align="center">

# SauceDemo UI Test Automation

Portfolio project for end-to-end UI test automation of the [SauceDemo](https://www.saucedemo.com/) online store

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-43B02A?logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![JUnit](https://img.shields.io/badge/JUnit-5.11.3-25A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure_Report-3.17.0-FFB400?logo=allure&logoColor=white)](https://allurereport.org/)

</div>

> The project validates the complete purchase journey in Google Chrome: authentication, adding products to the cart, checkout, and order confirmation.

## Tech Stack

- Java 17
- Selenium WebDriver
- JUnit 5
- Maven
- WebDriverManager
- Allure Report
- Page Object Model
- GitHub Actions

## Test Scenarios

1. `Successful purchase`

   Opens SauceDemo, signs in, adds Sauce Labs Backpack to the cart, completes checkout, and verifies the `Thank you for your order!` confirmation.

2. `Multiple-product purchase`

   Adds Sauce Labs Backpack and Sauce Labs Bike Light, completes checkout, and verifies the order confirmation.

3. `Invalid credentials`

   Verifies the error message shown after signing in with an incorrect password.

4. `Locked-out user`

   Verifies that the `locked_out_user` account cannot sign in.

5. `Product sorting by price`

   Verifies product order after selecting low-to-high price sorting.

6. `Remove product from cart`

   Verifies removal of Sauce Labs Backpack and the cart item count update.

7. `Checkout form validation`

   Verifies the error message shown when the user continues without entering a first name.

## Project Structure

```text
src/test/java/com/saucedemo
├── pages/        # Page Object classes for each screen
├── tests/        # Test setup and end-to-end scenarios
└── utils/        # WebDriver configuration and lifecycle management

src/test/resources
└── allure.properties
```

## Prerequisites

- JDK 17 or later
- Maven 3.9 or later
- Google Chrome

ChromeDriver is downloaded and configured automatically by WebDriverManager.

## Run Tests

Run the following command after cloning the repository:

```bash
mvn clean test
```

The browser runs in incognito mode, maximizes its window, and closes after each test.

## Allure Report

Start a local Allure report server after running the tests:

```bash
mvn allure:serve
```

Generate static report files instead:

```bash
mvn allure:report
```

## Continuous Integration

GitHub Actions runs the complete UI test suite on every push, pull request, and manual workflow dispatch. Allure results are saved as workflow artifacts.

## Implementation Details

- Page Object Model separates locators and page actions from test logic.
- `BaseTest` manages browser setup and teardown for every test.
- `DriverFactory` configures Chrome, timeouts, and WebDriver lifecycle management.
- Tests run in headless mode in CI environments.
- Allure annotations add feature, story, severity, and scenario descriptions to test reports.

## Test Results

<div align="center">

| Tests | Result | Errors | Failures |
| :---: | :----: | :----: | :------: |
| 7 | ✅ Passed | 0 | 0 |

</div>

The latest test run completed successfully.

## Author

Konstantin
