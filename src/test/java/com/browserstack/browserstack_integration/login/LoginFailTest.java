package com.browserstack.browserstack_integration.login;

import com.browserstack.browserstack_integration.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginFailTest extends TestBase {

    @Test
    public void loginSuccess() {
        getDriver().findElement(By.id("signin")).click();
        getDriver().findElement(By.cssSelector("#username input")).sendKeys("fav_user" + Keys.ENTER);
        getDriver().findElement(By.cssSelector("#password input")).sendKeys("testingisfun99" + Keys.ENTER);
        getDriver().findElement(By.id("login-btn")).click();

        Assert.assertEquals(getDriver().findElement(By.className("username")).getText(), "fav");
    }

}
