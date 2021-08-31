package com.browserstack.browserstack_integration.app.pages;

import com.browserstack.webdriver.CustomWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected CustomWebDriver driver;
    protected WebDriverWait wait;

    public BasePage(CustomWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10, 1000);
        PageFactory.initElements(driver, this);
    }

    protected void waitFortextToBePresentInElement(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

}
