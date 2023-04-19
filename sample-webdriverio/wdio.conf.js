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
    hostname: 'engine.zebrunner.com',
    port: 443,
    path: '/wd/hub',
    user: 'webinar',
    key: '5ktR24eatjJV2g1p',

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
                projectKey: 'DEMO',
                server: {
                    hostname: 'https://webinar.zebrunner.com/',
                    accessToken: 'zs34JCuRcdn3ynwx3PY2rAHFkOknpcknlAgYWq9CNZcjtFtg7h',
                },
                launch: {
                    displayName: 'WDIO Demo with TCM integration',
                    build: '2.41.2.2431-SNAPSHOT',
                    environment: 'LOCAL',
                },
                tcm: {
                    zebrunner: {
                        pushResults: true,
                        pushInRealTime: true,
                        testRunId: 1
                    }
                }
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
