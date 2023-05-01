package com.zebrunner.demo;

import com.zebrunner.demo.pages.mobile.CatalogPage;
import com.zebrunner.demo.pages.mobile.ProductDetailsPage;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MobileTest extends BaseTest {

    @BeforeClass
    @Parameters({"platformName"})
    public void setUp(String platformName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
            switch (platformName) {
                case "ios":
                    capabilities.setPlatform(Platform.IOS);
                    capabilities.setCapability("appium:app", "https://github.com/saucelabs/my-demo-app-ios/releases/download/1.3.0/SauceLabs-Demo-App.Simulator.zip");
                    capabilities.setCapability("appium:deviceName", "iPhone 12 Pro");
                    capabilities.setCapability("appium:automationName", "XCUITest");
                    break;
                case "android":
                    capabilities.setPlatform(Platform.ANDROID);
                    capabilities.setCapability("appium:app", "https://github.com/saucelabs/my-demo-app-android/releases/download/1.0.13/mda-1.0.13-15.apk");
                    capabilities.setCapability("appium:deviceName", "Galaxy S8");
                    capabilities.setCapability("appium:automationName", "uiautomator2");
                    capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", "40000");
                    break;
                default:
                    throw new RuntimeException("Unrecognized platform name: " + platformName);
            }
            initDriver(capabilities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while creating a session", e);
        }
    }

    @Test
    public void testAddToCartAndVerifyCounter() {
        LOGGER.info("Open first product");
        String productToOpen = new CatalogPage(driver).getAllProductNames().get(0);
        ProductDetailsPage productDetailsPage = new CatalogPage(driver).openProductByName(productToOpen);

        LOGGER.info("Verify first product with name " + productToOpen + " is opened");
        assertTrue(productDetailsPage.isProductOpened(productToOpen), "Product " + productToOpen + " is not opened");

        LOGGER.info("Add to cart opened product");
        productDetailsPage.clickAddToCartButton();

        LOGGER.info("Verify cart products amount");
        assertEquals(productDetailsPage.getProductsAmountInCart(), 1, "Incorrect amount of products in the cart");
    }
}
