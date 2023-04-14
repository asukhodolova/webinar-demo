const {
    ZebrunnerReporter,
    ZebrunnerService,
} = require('@zebrunner/javascript-agent-webdriverio');

exports.config = {
    specs: [
        './test/specs/**/*.js'
    ],

    runner: 'local',
    reporterSyncInterval: 60 * 1000,

    //----------------------- Selenium Grid configuration-----------------------
    protocol: 'https',
    hostname: 'engine.zebrunner.dev',
    port: 443,
    path: '/wd/hub',
    user: 'user',
    key: 'key',

    capabilities: [
        {
            maxInstances: 1,
            platformName: 'linux',
            browserName: 'chrome',
            browserVersion: '109.0',
        },
    ],
    //----------------------- Selenium Grid configuration -----------------------

    logLevel: 'info',
    bail: 0,
    baseUrl: 'http://localhost',
    waitforTimeout: 10000,
    connectionRetryTimeout: 120000,
    connectionRetryCount: 3,


    services: [[ZebrunnerService]],
    reporters: [
        [
            //----------------------- Zebrunner Reporter configuration -----------------------
            ZebrunnerReporter,
            {
                enabled: true,
                projectKey: 'DEF',
                server: {
                    hostname: 'https://yourworkspace.zebrunner.com/',
                    accessToken: 'yourtokenhere',
                },
                launch: {
                    displayName: 'WDIO Demo',
                    build: '2.41.2.2431-SNAPSHOT',
                    environment: 'LOCAL',
                },
            },
            //----------------------- Zebrunner Reporter configuration -----------------------
        ],
        'spec',
    ],

    framework: 'mocha',
    mochaOpts: {
        ui: 'bdd',
        timeout: 60000
    },
}
