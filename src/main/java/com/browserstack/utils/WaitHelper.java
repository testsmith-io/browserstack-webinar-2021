package com.browserstack.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class WaitHelper {

    private static final Logger logger = Logger.getLogger(WaitHelper.class.getName());
    private final static long DEFAULT_TIME_OUT_IN_SECONDS = 25;
    private final static long DEFAULT_SLEEP_IN_MILLIS = 500;
    private final WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this(driver, DEFAULT_TIME_OUT_IN_SECONDS, DEFAULT_SLEEP_IN_MILLIS);
    }

    public WaitHelper(WebDriver driver, long timeOutInSeconds) {
        this(driver, timeOutInSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    public WaitHelper(WebDriver driver, long timeOutInSeconds, long sleepInMillis) {
        wait = new WebDriverWait(driver, timeOutInSeconds, sleepInMillis);
    }

    public void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public WebElement waitForVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
