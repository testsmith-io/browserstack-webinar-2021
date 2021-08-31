package com.browserstack.listeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class WebDriverWaitListener extends AbstractWebDriverEventListener {

    private static final Logger logger = Logger.getLogger(WebDriverWaitListener.class.getName());
    private static final int TIMEOUT_IN_SECONDS = 30;
    private final WebDriverWait wait;

    public WebDriverWaitListener(WebDriver driver) {
        wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        long startTime = System.currentTimeMillis();
        logger.info("beforeClickOn:: start");
        wait.until(ExpectedConditions.elementToBeClickable(element));
        long endTime = System.currentTimeMillis();
        logger.info("beforeClickOn:: end, took " + (endTime - startTime) + " milliseconds");
    }

    public void beforeGetText(WebElement element, WebDriver driver) {
        long startTime = System.currentTimeMillis();
        logger.info("beforeGetText:: start");
        wait.until(ExpectedConditions.visibilityOf(element));
        long endTime = System.currentTimeMillis();
        logger.info("beforeGetText:: end, took " + (endTime - startTime) + " milliseconds");
    }

}
