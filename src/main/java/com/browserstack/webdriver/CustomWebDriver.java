package com.browserstack.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class CustomWebDriver implements WebDriver, JavascriptExecutor, TakesScreenshot {

    private static final Logger logger = Logger.getLogger(CustomWebDriver.class.getName());
    private final static long DEFAULT_TIME_OUT_IN_SECONDS = 25;
    private final static long DEFAULT_SLEEP_IN_MILLIS = 500;
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CustomWebDriver(WebDriver driver) {
        this(driver, DEFAULT_TIME_OUT_IN_SECONDS, DEFAULT_SLEEP_IN_MILLIS);
    }

    public CustomWebDriver(WebDriver driver, long timeOutInSeconds) {
        this(driver, timeOutInSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    public CustomWebDriver(WebDriver driver, long timeOutInSeconds, long sleepInMillis) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIME_OUT_IN_SECONDS, DEFAULT_SLEEP_IN_MILLIS);
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeAsyncScript(script, args);
    }

    public WebElement waitForVisibilityOf(WebElement element) {
        logger.info("waitForVisibilityOf:: start");
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot) driver).getScreenshotAs(target);
    }
}
