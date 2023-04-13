package com.zebrunner.demo;

import com.zebrunner.agent.core.webdriver.RemoteWebDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class BaseTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    protected RemoteWebDriver driver;

    private static final String SELENIUM_HUB_URL = "http://localhost:4444/";

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void initDriver(Capabilities capabilities) {
        URL hubUrl = getSeleniumHubUrl();
        Capabilities launcherCapabilities = RemoteWebDriverFactory.getCapabilities();
        Capabilities remoteCapabilities = launcherCapabilities.asMap().isEmpty() ? capabilities : launcherCapabilities;

        if (capabilities.getCapability("platformName") != null) {
            String platformName = capabilities.getCapability("platformName").toString();
            if (platformName.equalsIgnoreCase("ios")) {
                driver = new IOSDriver(getSeleniumHubUrl(), capabilities);
            } else if (platformName.equalsIgnoreCase("android")) {
                driver = new AndroidDriver(getSeleniumHubUrl(), capabilities);
            }
        } else {
            driver = new RemoteWebDriver(hubUrl, remoteCapabilities);
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT);
    }

    protected URL getSeleniumHubUrl() {
        URL launcherHubUrl = RemoteWebDriverFactory.getSeleniumHubUrl();
        if (launcherHubUrl != null) {
            return launcherHubUrl;
        }
        try {
            return new URL(SELENIUM_HUB_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Incorrect Selenium Grid URL", e);
        }
    }
}
