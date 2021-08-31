package com.browserstack.waits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheckElementNotExistTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    /**
     * Doesn't work
     */
    @Test
    public void waitForSuccessMessage() {
        driver.get("http://www.seleniuminaction.com/examples/FormWithDelay.html");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        Assertions.assertThat(driver.findElement(By.tagName("non-existing-tag"))).isNotNull();

        Assertions.assertThat(driver.findElements(By.tagName("non-existing-tag")).size()).isEqualTo(0);

        Assertions.assertThat(driver.findElement(By.tagName("non-existing-tag")).isDisplayed()).isEqualTo(false);

        driver.findElement(By.cssSelector("input#firstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#lastName")).sendKeys("Doe");
        driver.findElement(By.cssSelector("input#email")).sendKeys("foo@bar.com");

        driver.findElement(By.cssSelector("button")).click();

        Assertions.assertThat(driver.findElement(By.cssSelector(".alert-success")).isDisplayed()).isEqualTo(true);
    }
}
