Contents
Getting started
Overview
Website configuration
Contributing
License
✈️ Getting started
Prerequisites
Git.
Node (version 12 or greater).
Yarn (version 1.5 or greater).
A fork of the repo (for any contributions).
A clone of the react-native-website repo.
Installation
cd react-native-website to go into the project root.
yarn to install the website's workspace dependencies.
Running locally
cd website to go into the website portion of the project.
yarn start to start the development server (powered by Docusaurus).
open http://localhost:3000/ to open the site in your favorite browser.
📖 Overview
If you would like to contribute an edit or addition to the docs, read through our style guide before you write anything. All our content is generated from markdown files you can find in the docs directory.

To edit the internals of how the site is built, you may want to get familiarized with how the site is built. The React Native website is a static site generated using Docusaurus. The website configuration can be found in the website directory. Visit the Docusaurus website to learn more about all the available configuration options.

Directory Structure
The following is a high-level overview of relevant files and folders.

react-native-website/
├── docs/
│   ├── accessibility.md
│   └── ...
└── website/
    ├── blog/
    │   ├── 2015-03-26-react-native-bringing-modern-web-techniques-to-mobile.md
    │   └── ...
    ├── core/
    ├── pages/
    │   └── en/
    ├── src/
    │   ├── css/
    │   │   ├── customTheme.scss
    │   │   └── ...
    │   ├── pages/
    │   │   ├── index.js
    │   │   └── ...
    │   └── theme/
    ├── static/
    │   ├── blog/
    │   │   └── assets/
    │   ├── docs/
    │   │   └── assets/
    │   ├── img/
    │   └── js/
    ├── versioned_docs/
    │   ├── version-0.60/
    │   └── ...
    ├── versioned_sidebars/
    │   ├── version-0.60-sidebars.json
    │   └── ...
    ├── docusaurus.config.js
    ├── package.json
    ├── showcase.json
    ├── sidebars.json
    └── versions.json
Documentation sources
As mentioned above, the docs folder contains the source files for all of the docs in the React Native website. In most cases, you will want to edit the files within this directory. If you're adding a new doc or you need to alter the order the docs appear in the sidebar, take a look at the sidebars.json file in the website directory. The sidebars file contains a list of document ids that should match those defined in the header metadata (aka frontmatter) of the docs markdown files.

Versioned docs
The React Native website is versioned to allow users to go back and see the API reference docs for any given release. A new version of the website is generally generated whenever there is a new React Native release. When this happens, any changes made to the docs and website/sidebars.json files will be copied over to the corresponding location within website/versioned_docs and website/versioned_sidebars.

Note: Do not edit the auto-generated files within versioned_docs or versioned_sidebars unless you are sure it is necessary. Edits made to older versions will not be propagated to newer versions of the docs.

Docusaurus keeps track of the list of versions for the site in the website/versions.json file. The ordering of the versions in this file should be in reverse chronological order.

Cutting a new version
cd react-native-website to go into the project root.
cd website to go into the website portion of the project.
Run yarn version:cut <newVersion> where <newVersion> is the new version being released.
🔧 Website configuration
The main config file for the website can be found at website/docusaurus.config.js. This file tells Docusaurus how to build the website. Edits to this file are rarely necessary.

The core subdirectory contains JavaScript and React components that are the core part of the website.

The src/pages subdirectory contains the React components that make up the non-documentation pages of the site, such as the homepage.

The src/theme subdirectory contains the swizzled React components from the Docusaurus theme.

The showcase.json file contains the list of users that are highlighted in the React Native showcase.

👏 Contributing
Create a branch
git checkout master from any folder in your local react-native-website repository.
git pull origin master to ensure you have the latest main code.
git checkout -b the-name-of-my-branch to create a branch.
replace the-name-of-my-branch with a suitable name, such as update-animations-page

Make the change
Follow the "Running locally" instructions.
Save the files and check in the browser.
Some changes may require a server restart to generate new files. (Pages in docs always do!)
Edits to pages in docs will only be visible in the latest version of the documentation, called "Next", located under the docs/next path.
Visit http://localhost:3000/docs/next/YOUR-DOCS-PAGE to see your work.

Visit http://localhost:3000/versions to see the list of all versions of the docs.

Test the change
If possible, test any visual changes in all latest versions of the following browsers:

Chrome and Firefox on the desktop.
Chrome and Safari on mobile.
Push it
Run yarn prettier and yarn language:lint in ./website directory to ensure your changes are consistent with other files in the repo.
git add -A && git commit -m "My message" to stage and commit your changes.
replace My message with a commit message, such as Fixed header logo on Android

git push my-fork-name the-name-of-my-branch
Go to the react-native-website repo and you should see recently pushed branches.
Follow GitHub's instructions.
Describe briefly your changes (in case of visual changes, please include screenshots).
📄 License
React Native is MIT licensed.

React Native documentation is Creative Commons licensed.
