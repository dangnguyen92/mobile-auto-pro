package utils;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.DriverManager.getDriver;

public interface ElementUtils {
    List<Class<? extends WebDriverException>> EXCEPTIONS_TO_IGNORE = Arrays.asList(TimeoutException.class, StaleElementReferenceException.class, NoSuchElementException.class);
    Duration zeroTimeout = Duration.ofSeconds(0);
    Duration shortTimeout = Duration.ofSeconds(5);
    Duration defaultTimeout = Duration.ofSeconds(15);
    Duration longTimeout = Duration.ofSeconds(20);
    Duration poolingInterval = Duration.ofMillis(200);

    default void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    default void waitForVisibility(WebElement element) {
        waitForVisibility(element, defaultTimeout);
    }

    default void waitForVisibility(WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
            wait.ignoreAll(EXCEPTIONS_TO_IGNORE);
            wait.pollingEvery(poolingInterval);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ignore) {
        };
    }

    default WebElement getElement(By by, Duration timeout) {
        WebElement element = null;
        Duration existingTimeout = getDriver().manage().timeouts().getImplicitWaitTimeout();

        try {
            getDriver().manage().timeouts().implicitlyWait(timeout);
            element = getDriver().findElement(by);
        } finally {
            getDriver().manage().timeouts().implicitlyWait(existingTimeout);
        }
        
        return element;
    }

    default WebElement getElement(By by) {
        return getElement(by, defaultTimeout);
    }

    default boolean isElementPresent(WebElement element) {
        boolean status;
        Duration existingTimeout = getDriver().manage().timeouts().getImplicitWaitTimeout();
        try {
            getDriver().manage().timeouts().implicitlyWait(zeroTimeout);
            element.isDisplayed();
            status = true;
        } catch (Exception e) {
            status = false;
        } finally {
            getDriver().manage().timeouts().implicitlyWait(existingTimeout);
        }
        return status;
    }
}
