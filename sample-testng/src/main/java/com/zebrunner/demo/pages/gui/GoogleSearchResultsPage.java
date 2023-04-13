package com.zebrunner.demo.pages.gui;

import com.zebrunner.demo.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleSearchResultsPage extends BasePage {

    private static final String SEARCH_RESULT_LINK_LOCATOR = "//*[@id='search']//a";

    @FindBy(xpath = SEARCH_RESULT_LINK_LOCATOR)
    private List<WebElement> searchResultLinks;

    public GoogleSearchResultsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public String getFirstSearchResult() {
        LOGGER.info("Getting first search result");
        waitForElementPresent(SEARCH_RESULT_LINK_LOCATOR);
        String firstSearchResult = searchResultLinks.get(0).getText();
        LOGGER.info("First search result = " + firstSearchResult);
        return firstSearchResult;
    }
}
