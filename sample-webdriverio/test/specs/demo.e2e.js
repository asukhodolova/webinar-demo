const log = require('loglevel');
const { Key } = require('webdriverio');
const { currentTest, zebrunner } = require('@zebrunner/javascript-agent-webdriverio');

describe('WDIO Google search', () => {

  const logger = log.getLogger('LOGGER');
  const url = 'https://www.google.com/';

  before(function () {
    browser.maximizeWindow();
  });

  it('Should return first result that contains search value', async () => {
    zebrunner.testCaseKey('DEMO-1', 'DEMO-2');

    const searchValue = 'Zebrunner';

    logger.info(`Navigating to ${url}`);
    await browser.url(url);

    currentTest.saveScreenshot(await browser.takeScreenshot());

    logger.info(`Performing search with value: ${searchValue}`);
    await $("//*[@name='q']").setValue(searchValue);
    await browser.keys(Key.Enter);

    logger.info(`Verify first search result contains ${searchValue}`);
    currentTest.saveScreenshot(await browser.takeScreenshot());

    await expect($("//*[@id='search']//a")).toHaveTextContaining(searchValue);
  });

  it('Should return first result that equals to search value', async () => {
    zebrunner.testCaseKey('DEMO-3');

    const searchValue = 'Webdriver.io';

    logger.info(`Navigating to ${url}`);
    await browser.url(url);

    currentTest.saveScreenshot(await browser.takeScreenshot());

    logger.info(`Performing search with value: ${searchValue}`);
    await $("//*[@name='q']").setValue(searchValue);
    await browser.keys(Key.Enter);

    logger.info(`Verify first search result equals to ${searchValue}`);
    currentTest.saveScreenshot(await browser.takeScreenshot());

    await expect($("//*[@id='search']//a")).toHaveText(searchValue);
  });
});
