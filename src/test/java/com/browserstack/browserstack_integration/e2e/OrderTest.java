package com.browserstack.browserstack_integration.e2e;

import com.browserstack.browserstack_integration.TestBase;
import com.browserstack.browserstack_integration.app.pages.ConfirmationPage;
import com.browserstack.browserstack_integration.app.pages.HomePage;
import com.browserstack.browserstack_integration.app.pages.OrdersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTest extends TestBase {

    @Test
    public void placeOrder() {
        ConfirmationPage page = new HomePage(getDriver())
                .navigateToSignIn()
                .loginWith("fav_user", "testingisfun99")
                .addProductToCart("5")
                .addProductToCart("9")
                .addProductToCart("10")
                .getBag().waitForItemsInBag(3)
                .getBag().proceedToCheckout()
                .enterShippingDetails("firstname", "lastname", "address", "state", "12345");
        Assert.assertTrue(page.isConfirmationDisplayed());

        OrdersPage ordersPage = page.continueShopping().navigateToOrders();

        Assert.assertEquals(ordersPage.getItemsFromOrder(), 3);
    }
}