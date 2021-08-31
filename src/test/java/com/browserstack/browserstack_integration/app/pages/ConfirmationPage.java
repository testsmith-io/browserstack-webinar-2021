package com.browserstack.browserstack_integration.app.pages;

import com.browserstack.webdriver.CustomWebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmationPage extends BasePage {

    @FindBy(css = ".continueButtonContainer button")
    private WebElement continueShoppingButton;

    private By confirmationMessage = By.id("confirmation-message");

    public ConfirmationPage(CustomWebDriver driver) {
        super(driver);
    }

    @Step("Is confirmation displayed")
    public boolean isConfirmationDisplayed() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(confirmationMessage, "Your Order has been successfully placed."));
    }

    @Step("Continue shopping")
    public HomePage continueShopping() {
        continueShoppingButton.click();
        return new HomePage(driver);
    }
}
