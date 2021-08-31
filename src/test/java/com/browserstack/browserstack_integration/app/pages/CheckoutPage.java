package com.browserstack.browserstack_integration.app.pages;

import com.browserstack.webdriver.CustomWebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "firstNameInput")
    private WebElement firstnameInput;

    @FindBy(id = "lastNameInput")
    private WebElement lastnameInput;

    @FindBy(id = "addressLine1Input")
    private WebElement addressInput;

    @FindBy(id = "provinceInput")
    private WebElement stateInput;

    @FindBy(id = "postCodeInput")
    private WebElement postcodeInput;

    @FindBy(id = "checkout-shipping-continue")
    private WebElement checkoutButton;

    public CheckoutPage(CustomWebDriver driver) {
        super(driver);
    }

    @Step("Enter shipping details")
    public ConfirmationPage enterShippingDetails(String firstname, String lastname, String address, String state, String postcode) {
        firstnameInput.sendKeys(firstname);
        lastnameInput.sendKeys(lastname);
        addressInput.sendKeys(address);
        stateInput.sendKeys(state);
        postcodeInput.sendKeys(postcode);
        checkoutButton.click();
        return new ConfirmationPage(driver);
    }

}
