package com.browserstack.browserstack_integration.app.pages;

import com.browserstack.webdriver.CustomWebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrdersPage extends BasePage {

    @FindBy(css = ".shipment .a-fixed-right-grid > div")
    private List<WebElement> orderItems;

    public OrdersPage(CustomWebDriver driver) {
        super(driver);
    }

    @Step("Get amount of items from order")
    public int getItemsFromOrder() {
        wait.until(ExpectedConditions.visibilityOfAllElements(orderItems));
        return orderItems.size();
    }
}
