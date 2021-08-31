package com.browserstack.waits;

import com.browserstack.utils.WaitHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FormWithWaitHelperTest {
    private WebDriver driver;
    private WaitHelper wait;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WaitHelper(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test(invocationCount = 3)
    public void waitForSuccessMessage() {
        driver.get("http://www.seleniuminaction.com/examples/FormWithDelay.html");

        driver.findElement(By.cssSelector("input#firstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#lastName")).sendKeys("Doe");
        driver.findElement(By.cssSelector("input#email")).sendKeys("foo@bar.com");

        driver.findElement(By.cssSelector("button")).click();

        String alertText = wait.waitForVisibilityOf(driver.findElement(By.cssSelector(".alert-success"))).getText();

        Assertions.assertThat(alertText).isEqualTo("Well done! Thanks for submitting the information!");
    }

}
