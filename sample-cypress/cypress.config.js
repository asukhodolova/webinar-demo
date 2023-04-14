const { defineConfig } = require('cypress');
const zbrPlugin = require('@zebrunner/javascript-agent-cypress/lib/plugin');

module.exports = defineConfig({
  chromeWebSecurity: false,
  reporter: '@zebrunner/javascript-agent-cypress',
  reporterOptions: {
    reportingServerHostname: 'https://yourworkspace.zebrunner.com/',
    reportingServerAccessToken: 'yourtokenhere',
    reportingProjectKey: 'DEF',
    reportingRunDisplayName: 'Cypress Demo',
    reportingRunEnvironment: 'LOCAL',
    reportingRunBuild: '1.0-alpha',
  },
  e2e: {
    setupNodeEvents(on, config) {
      zbrPlugin(on, config);
    },
  },
});
