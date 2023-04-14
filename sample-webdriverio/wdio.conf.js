exports.config = {
    specs: [
        './test/specs/**/*.js'
    ],

    runner: 'local',
    reporterSyncInterval: 60 * 1000,

    //----------------------- Selenium Grid configuration-----------------------
    protocol: 'https',
    hostname: 'localhost',
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


    services: [],
    reporters: [
        'spec',
    ],

    framework: 'mocha',
    mochaOpts: {
        ui: 'bdd',
        timeout: 60000
    },
}
