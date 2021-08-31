package com.browserstack.browserstack_integration.app.pages;

import com.browserstack.webdriver.CustomWebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Bag extends BasePage {

    @FindBy(className = "float-cart__close-btn")
    private WebElement closeButton;

    @FindBy(css = ".bag")
    private WebElement bagButton;

    @FindBy(css = ".bag--float-cart-closed")
    private WebElement bagClosed;

    @FindBy(css = ".float-cart--open")
    private WebElement bagOpen;

    @FindBy(className = "buy-btn")
    private WebElement buyButton;

    @FindBy(css = ".bag__quantity")
    private WebElement quantityContainer;

    public Bag(CustomWebDriver driver) {
        super(driver);
    }

    @Step("Proceed to checkout")
    public CheckoutPage proceedToCheckout() {
        wait.until(ExpectedConditions.visibilityOf(bagClosed));
        waitAndClick(bagButton);
        wait.until(ExpectedConditions.visibilityOf(bagOpen));
        wait.until(ExpectedConditions.elementToBeClickable(buyButton)).click();
        return new CheckoutPage(driver);
    }

    @Step("Close shopping bag")
    public void close() {
        waitAndClick(closeButton);
    }

    @Step("Wait for {i} items in shopping bag")
    public HomePage waitForItemsInBag(int i) {
        waitFortextToBePresentInElement(quantityContainer, Integer.toString(i));
        return new HomePage(driver);
    }
}
