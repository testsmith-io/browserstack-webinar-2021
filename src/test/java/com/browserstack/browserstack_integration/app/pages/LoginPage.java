package com.browserstack.browserstack_integration.app.pages;

import com.browserstack.webdriver.CustomWebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "#username input")
    private WebElement usernameInput;

    @FindBy(css = "#password input")
    private WebElement passwordInput;

    @FindBy(id = "login-btn")
    private WebElement logInButton;

    @FindBy(css = ".username")
    private WebElement signedInUsername;

    public LoginPage(CustomWebDriver driver) {
        super(driver);
    }

    @Step("Login with username {username} and password {password}")
    public HomePage loginWith(String username, String password) {
        usernameInput.sendKeys(username + Keys.ENTER);
        passwordInput.sendKeys(password + Keys.ENTER);
        logInButton.click();
        return new HomePage(driver);
    }

    @Step("Get signed in username")
    public String getSignedInUsername() {
        return signedInUsername.getText();
    }
}
