const log = require('loglevel');
const { Key } = require('webdriverio');

describe('WDIO Google search', () => {

  const logger = log.getLogger('LOGGER');
  const url = 'https://www.google.com/';

  before(function () {
    browser.maximizeWindow();
  });

  it('Should return first result that contains search value', async () => {
    const searchValue = 'Zebrunner';

    logger.info(`Navigating to ${url}`);
    await browser.url(url);

    await browser.takeScreenshot();

    logger.info(`Performing search with value: ${searchValue}`);
    await $("//*[@name='q']").setValue(searchValue);
    await browser.keys(Key.Enter);

    logger.info(`Verify first search result contains ${searchValue}`);
    await browser.takeScreenshot();

    await expect($("//*[@id='search']//a")).toHaveTextContaining(searchValue);
  });
});
