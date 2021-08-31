package com.browserstack.browserstack_integration;

import com.browserstack.utils.CapabilityHelper;
import com.browserstack.utils.ScreenshotListener;
import com.browserstack.webdriver.CustomWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
@Listeners({ScreenshotListener.class})
public class TestBase {
    private static final ThreadLocal<CustomWebDriver> driver = new ThreadLocal<>();
    private static final String BROWSERSTACK_HUB_URL = "https://hub.browserstack.com/wd/hub";
    protected WebDriverWait wait;

    public CustomWebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    @Parameters(value = {"env_cap_id"})
    public void setUp(@Optional("0") int env_cap_id, Method m) throws Exception {
        DesiredCapabilities caps = CapabilityHelper.generateCapabilities(env_cap_id, m.getName());
        driver.set(new CustomWebDriver(new RemoteWebDriver(new URL(BROWSERSTACK_HUB_URL), caps)));
        getDriver().get("https://www.bstackdemo.com");
        wait = new WebDriverWait(getDriver(), 25, 750);
        getDriver().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            getDriver().quit();
        }
    }
}
