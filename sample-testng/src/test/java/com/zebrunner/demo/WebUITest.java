package com.zebrunner.demo;

import com.zebrunner.demo.pages.gui.GoogleSearchPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WebUITest extends BaseTest {

    private static final String SEARCH_VALUE = "Zebrunner";

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        initDriver(options);
    }

    @Test
    public void testResultContainsSearchValue() {
        LOGGER.info("Open Google and perform search with value = " + SEARCH_VALUE);
        String firstResult = new GoogleSearchPage(driver).open().performSearch(SEARCH_VALUE).getFirstSearchResult();

        LOGGER.info("Verify first search result contains " + SEARCH_VALUE);
        assertTrue(firstResult.contains(SEARCH_VALUE), "Search result does not include " + SEARCH_VALUE);
    }

    @Test
    public void testResultEqualsToSearchValue() {
        LOGGER.info("Open Google and perform search with value = " + SEARCH_VALUE);
        String firstResult = new GoogleSearchPage(driver).open().performSearch(SEARCH_VALUE).getFirstSearchResult();

        LOGGER.info("Verify first search result contains " + SEARCH_VALUE);
        assertEquals(firstResult, SEARCH_VALUE, "Search result does not equal");
    }

    @Test(dependsOnMethods = "testResultEqualsToSearchValue")
    public void testSkip() {
        LOGGER.info("This test should be skipped");
    }
}

