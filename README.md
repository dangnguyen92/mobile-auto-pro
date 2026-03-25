# mobile-auto-pro

Java mobile automation framework for Android and iOS using Appium, Selenium, TestNG, and Spring test context.

## Overview

This project is a Maven-based test framework for mobile UI automation. It uses:

- Appium to create Android and iOS driver sessions
- Selenium WebDriver APIs for driver and element interaction
- TestNG for suite and test execution
- Spring test context for configuration and bean wiring
- Page Object Model for reusable screen interactions

## Requirements

- Java 22
- Maven 3.9+
- Node.js 18+ recommended
- Appium 2
- Installed Appium drivers:
  - `uiautomator2` for Android
  - `xcuitest` for iOS
- Android SDK and emulator/device for Android runs
- Xcode, Simulator, or real device tooling for iOS runs
- read SETUP.md 

## Project Structure

```text
src/main/java/
  configs/   Platform-specific capabilities
  pages/     Page objects and shared page base
  utils/     Driver lifecycle, Spring-backed config, Appium service

src/test/java/tests/
  BaseTest.java
  CreateWalletTest.java

src/test/resources/
  application.properties
  application-android.properties
  application-ios.properties
  testdata.properties
  testng-android.xml
  testng-ios.xml
  apps/
```

## How Test Execution Works

1. Maven runs TestNG through `maven-surefire-plugin`.
2. Suite file is selected by the Maven property `platform`.
3. `testng-android.xml` or `testng-ios.xml` sets `spring.profiles.active`.
4. Spring loads the matching properties file.
5. `BaseTest` calls `AUT` to create capabilities and start the driver.
6. Tests use page objects to interact with the app.

Default suite selection in `pom.xml`:

- `mvn test` runs Android
- `mvn test -Dplatform=ios` runs iOS


See `SETUP.md` for full installation steps.

## Running Tests

Run Android suite:

```bash
mvn test
```

Run iOS suite:

```bash
mvn test -Dplatform=ios
```

Run a clean build with verbose Maven logs:

```bash
mvn clean test -X
```

## Troubleshooting

### `Could not start a new session`

This is often caused by dependency mismatches or invalid device/app configuration, not just a bad server URL.

Check:

- Appium server is installed and runnable
- Required Appium driver is installed
- Device/emulator is available
- App path is correct if it is provided
- `platform.name` matches the selected suite
- Selenium/Appium/TestNG/Spring dependencies versions are compatible

Current project versions in `pom.xml`:
- Spring Boot Dependencies `3.2.3`
- TestNG `7.8.0`
- Selenium `4.15.0`
- Appium Java Client `9.0.0`

If you change Appium or Selenium versions, verify compatibility against the Appium Java Client, testNG, Spring dependencies compatibility matrix before upgrading.

