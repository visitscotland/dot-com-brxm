const _ = require("lodash")
const path = require("path")
const merge = require("webpack-merge")
const chalk = require("chalk")
const baseConfig = require("../build/webpack.base.conf.js")
const packageConfig = require("../package.json")
const parseChildComponents = require("../build/parse-child-components.js")
const { mergeIE11Fix } = require("../build/webpack.ie11-fix")

module.exports = {
    /**
   * Most of the styles are defined in /docs/styles/docs.styles.scss
   */
    version: packageConfig.version,
    theme: {
        maxWidth: "100%",
        sidebarWidth: 240,
        fontFamily: {
            base: ["'Fira Sans'", "Helvetica", "Arial", "sans-serif"],
            monospace: ["Consolas", "'Liberation Mono'", "Menlo", "monospace"],
        },
    },
    renderRootJsx: path.join(__dirname, "../docs/components/Preview.js"),
    /**
   * Define a custom code highlighting theme.
   */
    editorConfig: {
        theme: "night",
    },
    /**
   * Path to static assets directory
   */
    assetsDir: path.join(__dirname, "../src/assets"),
    /**
   * Enabling the below option will break things in Vue Design System!
   */
    skipComponentsWithoutExample: false,
    /**
   * We’re defining below JS and SCSS requires for the documentation.
   *.
    require: [
        path.join(__dirname, "../docs/docs.helper.js"),
        path.join(__dirname, "../docs/styles/docs.styles.scss"),
        path.join(__dirname, "../src/assets/fixtures/index.js"),
    ],
    /**
   * Enabling the following option splits sections into separate views.
   */
    pagePerSection: true,
    /**
   * Custom wrapper template for the documentation.
   */
    template: {
        title: "Example — Vue Design System",
        lang: "en",
        trimWhitespace: true,
        head: {
            meta: [
                {
                    name: "viewport",
                    content: "width=device-width,initial-scale=1.0",
                },
                {
                    name: "format-detection",
                    content: "telephone=no",
                },
            ],
        },
        favicon: "https://www.visitscotland.com/favicon.ico",
    },
    /**
   * Ignore app.vue, tests, and example component.
   */
    ignore: [
        "**/App.vue",
        "**/__tests__/**",
        "**/*.test.js",
        "**/*.test.jsx",
        "**/*.spec.js",
        "**/*.spec.jsx",
        "**/ExampleComponent.vue",
    ],
    webpackConfig: merge(mergeIE11Fix(baseConfig), {
        resolve: {
            alias: {
                "rsg-components/ReactComponent/ReactComponentRenderer":
          "rsg-components-default/ReactComponent/ReactComponentRenderer",
                "rsg-components/Playground/PlaygroundRenderer":
          "rsg-components-default/Playground/PlaygroundRenderer",
            },
        },
        module: {
            rules: [
                {
                    test: /\.(css?|scss|sass)(\?.*)?$/,
                    use: [
                        "style-loader",
                        "css-loader",
                        "postcss-loader",
                        {
                            loader: "sass-loader",
                            options: {
                                implementation: require("sass"),
                            },
                        },
                        {
                            loader: "sass-resources-loader",
                            options: {
                                resources: [
                                    path.join(__dirname, "../src/assets/tokens/tokens.scss"),
                                    path.join(__dirname, "../src/assets/tokens/tokens.map.scss"),
                                    path.join(__dirname, "../src/styles/styles.scss"),
                                    path.join(__dirname, "../src/styles/core.styles.scss"),
                                ],
                            },
                        },
                    ],
                },
            ],
        },
    }),
    compilerConfig: {
        target: {
            ie: "11",
            chrome: "71",
            firefox: "64",
            safari: "11",
            edge: "17",
        },
    },
    styleguideDir: "../dist/docs",
    printServerInstructions() {},
    printBuildInstructions(config) {
        console.log(chalk.cyan("\n  Design System Docs build finished succesfully!\n"))
        console.log(
            chalk.yellow(
                "  Tip: You can now deploy the docs as a static website.\n"
          + "  Copy the build files from "
          + `${config.styleguideDir}\n`,
            ),
        )
    },
    styleguideComponents: {
        LogoRenderer: path.join(__dirname, "../docs/components/rsg-components/LogoRenderer"),
        Playground: path.join(__dirname, "../docs/components/rsg-components/Playground"),
        ReactComponent: path.join(__dirname, "../docs/components/rsg-components/ReactComponent"),
    },
    propsParser(filePath, source) {
        return require("vue-docgen-api").parse(filePath, {
            resolve: baseConfig.resolve,
            alias: baseConfig.resolve.alias,
            addScriptHandlers: [parseChildComponents.default],
        })
    },

    /**
   * Configure docs server to redirect asset queries
   */
    // configureServer(app) {
    //   // `app` is the instance of the express server running the docs
    //   app.get("/assets/:file", (req, res) => {
    //     res.redirect(req.params.file)
    //   })
    // },
}
