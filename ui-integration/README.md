# This package is a webpack build that consumes assets generated by the visitscotland.com Component Library and integrates them into the parent Hippo CMS site.

## Setup
This script installs this package's dependencies.
```
yarn install

// OR

yarn
```

## Use

### Building frontend assets and generating freemarker templates

The following script runs the integration build to integrate production assets into the Hippo site.

It uses assets produced by the VS Component Library (production assets, with SSR) that have been installed via yarn and runs this package's webpack build to integrate existing assets into freemarker templates.
```sh
yarn build:ssr
```
The integration build:
   - Copies all assets contained in one of the frontend package's dist folders into the parent Hippo site's webfiles folder (at `/repository-data/webfiles/src/main/resources/site/frontend`).
   - Generates Freemarker templates for all Vue component modules, VueX store modules and the core VsApp module listed in the `manifest.json` file to the `components` subfolder, `stores` subfolder, and root, respectively, of the `frontend` Freemarker directory (`/repository-data/webfiles/src/main/resources/site/freemarker/frontend`).

By default, the assets are copied from the `./node_modules/@visitscotland/component-library/dist/ssr/client` folder. Passing the `src-path` flag to `yarn build:ssr` informs webpack to use a different root path for the source of the frontend assets if needed.



### Using the frontend components in the Hippo site

```html
<!-- 1. Include the Vue freemarker template for the component you want to include -->
<#include "../include/frontend/container.ftl">

<!-- 2. Include the relevant elements in the base template  -->

<head>
   <!-- These headContributions belong in the head part of the page - resource hints and style links will be inserted here -->
   <@hst.headContributions categoryIncludes="htmlHeadPreload" xhtml=true/>
   <@hst.headContributions categoryIncludes="htmlHeadStyles" xhtml=true/>
</head>

<body>
   <!-- An element bearing the data-vue-app-init attribute is the Vue app mount point (it should not be the body tag) -->
   <!-- The no-js class needs to be on the mount point or a parent element for proper rendering of styles for no-js users -->
   <div class="no-js" data-vue-app-init>
   </div>

   <!-- These headContributions belong at the end of the body - module scripts will be inerted here -->
   <@hst.headContributions categoryIncludes="htmlBodyEndScriptsFirst" xhtml=true/>
   <@hst.headContributions categoryIncludes="htmlBodyEndScripts" xhtml=true/>
   <@hst.headContributions categoryIncludes="htmlBodyEndAppInit" xhtml=true/>
</body>

<!-- 3. Reference a Vue component in the HTML markup *inside the Vue app mount element - i.e. data-vue-app-init above* -->
<vs-container fluid></vs-container>
```
