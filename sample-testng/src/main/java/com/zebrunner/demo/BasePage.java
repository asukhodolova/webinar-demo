package com.zebrunner.demo;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.Duration;

public abstract class BasePage {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    protected RemoteWebDriver driver;

    private static final Integer DEFAULT_WAIT = 10;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        if (driver instanceof IOSDriver || driver instanceof AndroidDriver) {
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        } else {
            PageFactory.initElements(driver, this);
        }
    }

    protected void waitForElementPresent(String xpathLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathLocator)));
    }
}

