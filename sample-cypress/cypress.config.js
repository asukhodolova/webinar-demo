const { defineConfig } = require('cypress');
const zbrPlugin = require('@zebrunner/javascript-agent-cypress/lib/plugin');

module.exports = defineConfig({
  chromeWebSecurity: false,
  reporter: '@zebrunner/javascript-agent-cypress',
  reporterOptions: {
    reportingServerHostname: 'https://webinar.zebrunner.com/',
    reportingServerAccessToken: 'lNr5hMHxy6QRfIwKEGvOFTDyNsqWCjAqoUhkveXtxpkUP0BsnC',
    reportingProjectKey: 'DEMO',
    reportingRunEnvironment: 'STAGE',
    reportingRunBuild: '1.0-alpha',
    reportingRunDisplayName: 'Cypress demo',
  },
  e2e: {
    setupNodeEvents(on, config) {
      zbrPlugin(on, config);
    },
  },
});
