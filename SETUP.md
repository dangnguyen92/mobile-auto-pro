# Setup Guide

This guide explains how to prepare a local machine to run the `mobile-auto-pro` framework.

## 1. Install Java and Maven

The project is configured for Java 22 in `pom.xml`.

Verify Java:

```bash
java -version
```

Expected major version:

- `22`

Verify Maven:

```bash
mvn -version
```

## 2. Install Node.js and Appium

Verify Node.js:

```bash
node -v
npm -v
```

Install Appium 2 globally:

```bash
npm install -g appium
```

Verify Appium:

```bash
appium -v
```

## 3. Install Appium Drivers

Install Android driver:

```bash
appium driver install uiautomator2
```

Install iOS driver:

```bash
appium driver install xcuitest
```

List installed drivers:

```bash
appium driver list --installed
```

## 4. Android Environment Setup

Install and configure:

- Android Studio
- Android SDK
- Platform tools
- Emulator or a connected Android device

Useful verification:

```bash
adb devices
emulator -list-avds
```

Update `src/test/resources/application-android.properties` with:

- `device.name`
- `platform.version`
- `app`
- optional `appium.server.url`

Current Android app path format:

```properties
app=apps/android/v8.21-googlePlay-debug.apk
```

The referenced file must exist under:

```text
src/test/resources/apps/android/
```

## 5. iOS Environment Setup

Install and configure:

- Xcode
- Xcode command line tools
- iOS Simulator or a real iOS device

Useful verification:

```bash
xcodebuild -version
xcrun simctl list devices
```

Update `src/test/resources/application-ios.properties` with:

- `udid`
- `platform.version`
- `app`
- `bundle.id`
- optional `appium.server.url`

If you plan to run against a real device, make sure signing, trust, and device provisioning are already configured in your Appium/Xcode environment.

## 6. Configure Spring Profile and Suites

The project uses two TestNG suite files:

- `src/test/resources/testng-android.xml`
- `src/test/resources/testng-ios.xml`

Maven selects the suite through the `platform` property in `pom.xml`:

- default: `android`
- override with `-Dplatform=ios`

The suite also sets `spring.profiles.active`, which tells Spring which `application-*.properties` file to use.

## 7. Local Appium vs External Appium

If `appium.server.url` is empty:

- the framework starts Appium automatically using `AppiumDriverLocalService`

If `appium.server.url` is provided:

- the framework connects to that server instead of starting a local one

Examples:

```properties
appium.server.url=
```

```properties
appium.server.url=http://127.0.0.1:4723/wd/hub
```

## 8. Run the Project

Run the default Android suite:

```bash
mvn test
```

Run the iOS suite:

```bash
mvn test -Dplatform=ios
```

Run with extra TestNG logging:

```bash
mvn clean test -Dsurefire.testng.verbose=3
```

Run with Maven debug logs:

```bash
mvn clean test -X
```

## 9. Common Problems

### Appium driver not installed

Symptoms:

- session creation fails immediately
- Appium says the automation driver is unavailable

Fix:

```bash
appium driver list --installed
appium driver install uiautomator2
appium driver install xcuitest
```

### Bad app path

Symptoms:

- session fails during startup
- app cannot be found

Fix:

- check the `app` property value
- confirm the binary exists under `src/test/resources/`

### Missing Spring property values

Symptoms:

- unresolved placeholders
- invalid or empty platform/device configuration

Fix:

- confirm `spring.profiles.active`
- confirm the selected suite matches the target platform
- fill all required properties in the corresponding `application-*.properties`

### Selenium and Appium version mismatch

Symptoms:

- `NoSuchMethodException` from `ProtocolHandshake.createSession`
- generic `Could not start a new session`

Fix:

- keep Appium Java Client and Selenium versions compatible
- verify changes against the Appium Java Client compatibility matrix before upgrading dependencies

## 10. Recommended First Run Checklist

1. Verify Java, Maven, Node, and Appium are installed.
2. Install Appium platform drivers.
3. Verify emulator/simulator or real device availability.
4. Update the appropriate `application-*.properties`.
5. Confirm the app binary exists in `src/test/resources/apps`.
6. Run `mvn test` for Android or `mvn test -Dplatform=ios` for iOS.
