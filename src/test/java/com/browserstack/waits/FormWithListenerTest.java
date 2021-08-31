package com.browserstack.waits;

import com.browserstack.listeners.WebDriverWaitListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FormWithListenerTest {
    private EventFiringWebDriver eventDriver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        eventDriver = new EventFiringWebDriver(driver);
        eventDriver.register(new WebDriverWaitListener(driver));
    }

    @AfterClass
    public void tearDown() {
        eventDriver.quit();
    }

    @Test(invocationCount = 3)
    public void waitForSuccessMessage() {
        eventDriver.get("http://www.seleniuminaction.com/examples/FormWithDelay.html");

        eventDriver.findElement(By.cssSelector("input#firstName")).sendKeys("John");
        eventDriver.findElement(By.cssSelector("input#lastName")).sendKeys("Doe");
        eventDriver.findElement(By.cssSelector("input#email")).sendKeys("foo@bar.com");

        eventDriver.findElement(By.cssSelector("button")).click();

        Assertions.assertThat(eventDriver.findElement(By.cssSelector(".alert-success")).getText()).isEqualTo("Well done! Thanks for submitting the information!");
    }

}
