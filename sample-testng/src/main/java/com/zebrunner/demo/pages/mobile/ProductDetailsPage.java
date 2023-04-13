package com.zebrunner.demo.pages.mobile;

import com.zebrunner.demo.BasePage;
import com.zebrunner.demo.utils.ScreenshotUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductDetailsPage extends BasePage {

    @AndroidFindBy(xpath = "//*[contains(@resource-id,'product')]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeScrollView/**/XCUIElementTypeStaticText")
    private WebElement nameLabel;

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'add product to cart')]")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Add To Cart'")
    private WebElement addToCartButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'cart')]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[-1]/XCUIElementTypeStaticText")
    private WebElement cartAmountLabel;

    public ProductDetailsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isProductOpened(String productName) {
        return nameLabel.getText().equals(productName);
    }

    public ProductDetailsPage clickAddToCartButton() {
        LOGGER.info("Clicking 'Add to cart' button");
        addToCartButton.click();
        ScreenshotUtils.takeScreenshot(driver);
        return this;
    }

    public int getProductsAmountInCart() {
        LOGGER.info("Fetching added to cart products amount");
        return Integer.valueOf(cartAmountLabel.getAttribute("name"));
    }
}

