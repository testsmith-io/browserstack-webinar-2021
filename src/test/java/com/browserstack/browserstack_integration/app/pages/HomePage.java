package com.browserstack.browserstack_integration.app.pages;

import com.browserstack.webdriver.CustomWebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "signin")
    private WebElement signInLink;

    @FindBy(css = "a#orders")
    private WebElement ordersLink;

    public HomePage(CustomWebDriver driver) {
        super(driver);
    }

    @Step("Navigate to the Sign In page")
    public LoginPage navigateToSignIn() {
        signInLink.click();
        return new LoginPage(driver);
    }

    @Step("Add {productName} to cart")
    public HomePage addProductToCart(String productName) {
        WebElement el = driver.findElement(By.cssSelector("div[id='" + productName + "'] div[class='shelf-item__buy-btn']"));
        waitAndClick(el);
        getBag().close();
        return this;
    }

    @Step("Navigate to orders page")
    public OrdersPage navigateToOrders() {
        ordersLink.click();
        return new OrdersPage(driver);
    }

    public Bag getBag() {
        return new Bag(driver);
    }
}
