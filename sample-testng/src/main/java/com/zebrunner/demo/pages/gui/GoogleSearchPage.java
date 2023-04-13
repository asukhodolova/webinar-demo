package com.zebrunner.demo.pages.gui;

import com.zebrunner.demo.BasePage;
import com.zebrunner.demo.utils.ScreenshotUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchPage extends BasePage {

    private static final String URL = "https://www.google.com/";
    private static final String COOKIES_DIALOG_TEXT = "cookies";

    @FindBy(xpath = "//*[@name='q']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[.='Accept all']")
    private WebElement acceptAllCookiesButton;

    public GoogleSearchPage(RemoteWebDriver driver) {
        super(driver);
    }

    public GoogleSearchPage open() {
        LOGGER.info("Navigating to url: " + URL);
        driver.get(URL);
        ScreenshotUtils.takeScreenshot(driver);

        if (driver.getPageSource().contains(COOKIES_DIALOG_TEXT)) {
            LOGGER.info("Cookies use popup is displayed, necessary to click 'Accept all'");
            acceptAllCookiesButton.click();
            ScreenshotUtils.takeScreenshot(driver);
        }
        return new GoogleSearchPage(driver);
    }

    public GoogleSearchResultsPage performSearch(String searchValue) {
        LOGGER.info("Performing search with value: " + searchValue);
        searchInput.sendKeys(searchValue);
        searchInput.sendKeys(Keys.ENTER);
        ScreenshotUtils.takeScreenshot(driver);
        return new GoogleSearchResultsPage(driver);
    }
}

