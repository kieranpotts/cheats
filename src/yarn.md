# Yarn cheats

[Yarn](https://yarnpkg.com/) is a fast, intuitive dependency management tool that runs on Node. It is a popular alternative to NPM, the default package manager for the Node runtime environment.

The primary purpose of NPM, Yarn and other such package managers is to be able to check-in vendor packages to source control without actually committing all their files. However, both NPM and Yarn provide other useful development utilities, such as shortcuts to custom development scripts.

This cheat sheet lists the most useful of Yarn's CLI commands. For the full list see https://yarnpkg.com/en/docs/cli.

#### `yarn add`

The equivalent of `npm install`, this command adds a package as a dependency of the current library or application. This will update both the `package.json` and `yarn.lock` files, so other developers working on the project will get the exact same dependencies when they run `yarn install`.

By default, packages are installed from the [NPM registry](https://www.npmjs.com/), but other sources can be specified.

```
# Install the very latest version of `pkg` from the NPM registry.
yarn add pkg

# Install multiple packages simultaneously.
yarn add pkg-1 pkg-2 pkg-3

# By default, packages will be installed as production dependencies (listed under
# `dependencies` in `package.json`). To install as a development dependency (`devDependencies`):
yarn add pkg --dev|-D

# To add to `peerDependencies`:
yarn add pkg --peer|-P

# To add to `optionalDependencies`:
yarn add pkg --optional|-O

# Specify a minimum version number.
yarn add pkg@1.2.3

# The default behaviour is to add the most recent release with the same 
# major version number. For example, `yarn add pkg@1.2.3` could add version
# 1.9.12. To install an exact version:
yarn add pkg@1.2.3 --exact|-E

# Accept newer releases of the minor version: `yarn add pkg@1.2.3` may install
# v1.2.9 but not v1.3.0.
yarn add pkg@1.2.3 --tilde|-T

# Install a specific tag, eg `latest`, `next`, `beta`.
yarn add pkg@tag

# Install a local package. This is useful to test packages under development,
# before they are published to a package registry.
yarn add file:/path/to/local/folder

# Install a package from a local gzipped tarball.
yarn add file:/path/to/local/tarball.tgz

# Install a package from a remote gzipped tarball.
yarn add https://my-project.org/pkg.tgz

# Install a symlink to a local package. This is useful in developing 
# related packages in monorepo environments. See `yarn link` for another 
# methodology to implement this.
yarn add link:/path/to/local/folder

# Install a package directly from a Git repository.
yarn add <git-remote-url>

# Install a package from a Git repository, pulling from a specific
# branch, commit or tag.
yarn add <git-remote-url>#<branch/commit/tag>
```

`yarn global add pkg` installs a package globally, so it is available throughout your local Node environment. This should be used only for installing system tools (like Yarn itself) rather than application dependencies, because globally-installed packages will not be explicitly defined in `package.json` files.

#### `yarn audit`

```
yarn audit [--level info|low|moderate|high|critical] [--json]
```

Checks for known security issues in any installed packages. You must be online to perform audits. The command will exit with a non-zero exit code if there are issues of any severity found. The exit code will be a mask of the severities. For example, if only INFO and MODERATE vulnerabilities are found, the exit code will be `5` (1+4).

- `1` = INFO
- `2` = LOW
- `4` = MODERATE
- `8` = HIGH
- `16` = CRITICAL

The `--level` flag will limit the contents of the outputted audit table to listing only the vulnerabilities of the given level and above. This does not affect the exit code of the command, however.

For automation purposes, the output can be JSON-formatted using the `--json` flag.

#### `yarn bin`

Prints the directory where Yarn will install executable files for the current package.

`yarn bin <executable>` will print the path to a specific executable file.

#### `yarn cache`

For performance, Yarn stores every installed package in a global cache in the user directory. This command is used for querying and managing that cache.

```
# Print the path to Yarn's global cache directory.
yarn cache dir

# Print out every cached package.
yarn cache list

# Print packages that match the given pattern.
yarn cache list --pattern "gulp-(match|newer)"

# Clear Yarn's global cache.
yarn cache clean

# Remove one or more specific packages from the cache.
yarn cache clean <pkg...>
```

To change Yarn's cache path, set the `cache-folder` config:

```
yarn config set cache-folder <path>
```

#### `yarn check`

Verifies that versions of the package dependencies in the current project's `package.json` match those in the `yarn.lock` file.

`yarn check --integrity` verifies that versions and hashed values of the package contents in the project's `package.json` match those in `yarn.lock`. This helps to verify that the package dependencies have not been altered.

`yarn check --verify-tree` recursively verifies that the dependencies in `package.json` are present in `node_modules` and have the right version. This check does not consider `yarn.lock`.

The switches `--integrity` and `--verify-tree` are mutually exclusive.

#### `yarn config`

Manages Yarn's configuration.

```
yarn config set <key> <value> [-g|--global]
yarn config get <key>
yarn config delete <key>
yarn config list
```

The `get` option echoes the value for the given key to `stdout`.

#### `yarn create`

A handy shortcut to create new projects from `create-*` starter kits.

```
yarn create <pkg> [<args>]
```

It does two things:

- Installs the `create-<pkg>` package globally, or updates it to the latest version.
- Runs the package's executable, as specified in the `bin` field of the kit's `package.json` file, forwarding any `<args>` to the script.

For example, `yarn create react-app my-app` is shorthand for:

```
$ yarn global add create-react-app
$ create-react-app my-app
```

#### `yarn global`

`global` is an optional prefix for the following commands:

- `add`
- `bin`
- `list`
- `remove`
- `upgrade` and `upgrade-interactive`

This option is used to install and manage packages in a shared global directory, rather than in the current project. The executables of globally-installed packages are available throughout your operating system.

It is best practice to install globally only packages that are general-purpose developer tools, and not dependencies of any particular project. Starter kits, project templates, and Yarn itself are good examples of developer tools that are candidates for global installation.

#### `yarn help`

Prints a list of available commands and flags, with a brief explanation of each.

#### `yarn import`

This tool is useful for migrating between NPM and Yarn for dependency management. It generates a `yarn.lock` file from any existing `package-lock.json` (which is generated by `npm`), minimising the differences between the `yarn.lock` file and the existing dependency tree as best it can.

If no `package-lock.json` file exists, a `yarn.lock` file will be generated by reverse engineering the contents of the `node_modules` directory.

It is recommended that projects use _either_ `yarn.lock` or `package-lock.json`, but not both, because NPM and Yarn can produce wildly different logical dependency trees. Thus, all contributors on a project should agree to use one of the two dependency management tools.

#### `yarn info`

```
yarn info <package>[@version] [<field>]
```

Fetches information about a package (which does not have to be installed). You can fetch a specific piece of information by providing the optional `<field>` argument.

#### `yarn init`

Initialises the development of a library or application. It launches an interactive session to create a new `package.json` file.

Some defaults, such as the license and initial version, are taken from Yarn's `init-*` config settings. `yarn init --yes|-y` skips the interactive session and generates a `package.json` based on the defaults.

`yarn init --private|-p` adds `"private": true` to the package configuration. The purpose of this field is to prevent the accidental publication of private repositories to public package registries such as NPM's.

#### `yarn install`

Installs all dependencies defined in the `package.json` file. This command is often the first you will call after checking out a project, or when another developer has added a new dependency that you need to pick up.

This is the default command if `yarn` is run without any explicit commands (any provided flags are passed through).

By default, `yarn install` will install _all_ dependencies: production and development. However, if the `NODE_ENV` environment variable has a value of "production", packages listed in `devDependencies` will be ignored. To override the environment configuration, pass the `--production=[true|false]` flag.

`yarn install --ignore-optional` will skip optional dependencies.

Note, the behaviour of installing packages differs between NPM and Yarn. In NPM, you can install a package locally without adding it to `package.json`; before NPM 5.0.0, you would need to pass to `npm install` the `--save` or `--save-dev` flags to register in `package.json` the installed package as a production or development dependency (`--save` became the default behaviour from `npm` v5.0.0). You cannot do this with Yarn. Packages must be added to `package.json` before they are installed. Thus, `yarn install` command has no equivalent of `npm`'s `--save` or `--save-dev` flags; the equivalent commands are `yarn add` and `yarn add --dev`.

This is how Yarn resolves the `package.json` and `yarn.lock` files:

- If `yarn.lock` is present and satisfies all the dependencies listed in `package.json`, the exact versions recorded in `yarn.lock` are installed. Yarn will not check for newer versions and `yarn.lock` will not change as a result of the operation.
- If `yarn.lock` is absent or does not satisfy all the dependencies listed in `package.json` (this can happen, for example, if a dependency is manually added to `package.json`), Yarn looks for the newest versions for all dependencies to satisfy the constraints of `package.json` and updates `yarn.lock` accordingly.

`yarn install` automatically prunes extraneous packages, and "dedupes" — ie, attempting to simplify the overall dependency structure by moving dependencies further up the tree where they can be more effectively shared by multiple dependent packages.

If you want to ensure that `yarn.lock` is not updated, supply the `--frozen-lockfile` flag. You should consider using this flag in CI services and in production deployment scripts. `yarn install --no-lockfile` does not read or generate a `yarn.lock` file; use this option if you want your `package.json` file to be the "source of truth".

`yarn install --force` reinstalls all packages, even ones that are already installed.

`yarn install --flat` installs all dependencies but only allows one version of each package. On the first run this will prompt you to choose versions of packages that are depended on with differing version ranges. These packages will get listed in `package.json` under a `resolutions` field.

`yarn install --check-files` verifies that packages that were previously installed have not been manually removed from `node_modules`.

`yarn install --module-folder <path>` specifies an alternative target location for the installed packages, rather than the traditional `./node_modules` directory.

The `--silent` option will not print the installation log. The `--non-interactive` flag disables interactive prompts — for example, when there's an invalid version of a dependency.

#### `yarn link`

Symlinks a package folder. This is useful in development to test new features of a package that is not yet released, or to debug an issue in a package that manifests itself in another project.

You run the `yarn link` in the folder of the package that you want to link. The output will be something like:

```
info You can now run `yarn link <pkg-name>` in the projects where you want to use this module and it will be used instead.
```

Next, you navigate to the project in which you want to use the package. Then you install the symlink by issuing the command:

```
yarn link pkg-name
```

To reverse the process, use `yarn unlink` or `yarn unlink pkg-name`.

Alternatively, you can use `yarn add link:/path/to/local/folder` from the consuming package, with the path pointing to the root directory of the dependency to import. The end result is the same.

Note, symlinking packages creates symlinks in the `node_modules` directory but does not modify `package.json`. So, you will be able to consume local packages, existing elsewhere on your filesystem, for the purpose of development and testing, without needing to explicitly declare the import has a hard dependency.

#### `yarn list`

Mimics Unix listing behaviour. In Yarn, `list` lists all dependencies for the current working directory.

#### `yarn login`

This command will prompt you for your username and email to login to the [NPM registry](https://www.npmjs.com/).

You will not be asked for a password until you run a subsequent command that requires authentication, such as `yarn publish`.

Use `yarn logout` to clear your username and email from memory.

#### `yarn outdated`

Lists version information for all package dependencies. Information includes the currently installed version, the desired version based on semver, and the latest available version.

You can limit the information to one or more packages with `yarn outdated [package...]`.

#### `yarn owner`

Package owners are users who can publish new versions of the package to the NPM registry.

```
# List all owners of a package.
yarn owner list <package>

# Add a user.
yarn owner add <user> <package>

# Remove a user.
yarn owner remove <user> <package>
```

#### `yarn pack`

Creates a compressed gzip archive of package dependencies. Pass in `--filename <filename>` to specify a filename for the generated archive.

#### `yarn policies`

It is possible that different developers within a team can end up using different versions of Yarn. While this is usually inconsequential, this could be a source of unexpected problems. So you can specify the required Yarn version inside the `package.json` file. However, this requires all contributors to install the same version of Yarn globally, which may not always be practical if some developers work on other projects with different requirements.

So, `yarn policies set-version` offers a simple way to check in your Yarn release within your repository. Once you run this command, the configuration will be updated in such a way that anyone running a Yarn command inside the project will always use the same version.

Under the hood, the command will simply download the single-file release from the GitHub repository, store it inside your project (inside the `.yarn/releases` folder), then finally update your configuration to point to the new file (using `yarn-path`).

The command accepts various ways to set which version you want to use:

```
# Use the latest stable version.
yarn policies set-version

# Use the latest release candidate.
yarn policies set-version --rc

# Use a specific version.
yarn policies set-version 1.13.0

# Use the latest patch for v1.12.
yarn policies set-version '^1.12.0'
```

#### `yarn publish`

Publishes the current package to the NPM registry.

**Once a package is published, you can never modify that specific version.**

```
# Skip the prompt for the version number.
yarn publish --new-version <version>

# Add a tag, such as "beta".
yarn publish --tag <tag>

# Publish the package with public or restricted access.
yarn publish --access <public|restricted>
```

#### `yarn remove`

`yarn remove <pkg>` removes an unused package from the current library or application. The `package.json` and `yarn.lock` files are updated accordingly. Other developers working on the project will need to run `yarn install` to sync their own `node_modules`.

#### `yarn run`

Runs a script that is defined in `package.json`:

```
{
  "name": "my-package",
  "scripts": {
    "build": "babel src -d lib",
    "test": "jest"
  }
}
```

Example:

```
yarn run test
```

Additional arguments can be passed at runtime:

```
yarn run test -o --watch
```

#### `yarn tag`

Distribution tags are a way of marking published versions of your package with a label. Users of your package can install a specific version using the label instead of the version number.

For example, if you had a "stable" release channel and a "canary" release channel, tags would allows users to type:

```
yarn add pkg@stable
yarn add pkg@canary
```

You can make up your own tags, but by convention the following tags have the following meanings:

- `latest`: The highest available version number.
- `stable`: This is usually the same as `latest` but could refer to a slightly older release with long-term support (LTS).
- `rc`: A release candidate that is expected to be stable and not likely to change before being fully released.
- `beta`: A release that previews upcoming changes before they are finished.
- `alpha`: A pre-beta release that is even more unstable.
- `canary`: A "nightly" build for frequently updated projects.
- `dev`: For testing a single revision though the registry.

`beta` or `rc` tags are sometimes replaced with `next`.

To add a tag:

```
yarn tag add <package>@<version> <tag>
```

Tags are added against specific version numbers. However, tags themselves cannot be semver strings, since tags and version numbers share a namespace:

```
yarn add pkg@<version>
yarn add pkg@<tag>
```

So, any valid semver string will be rejected as a tag name. You cannot have tags named `v2.3`, for example.

To remove a tag:

```
yarn tag remove <package> <tag>
```

You do not need to delete a tag before moving it to another version. In fact, it's better _not_ to do this, but let Yarn sort it out.

#### `yarn team`

The creation and management of organisation members and team admins must be done via the NPM registry website, not the CLI. You can, however, use the CLI to manage teams in organisations, and to change team memberships. The `yarn team` command operates on the current registry, which is configurable from the command line using `--registry=<url>`.

Only team admins — under the given organisation — can manage teams and their memberships.

```
# Create a new team:
yarn team create <scope:team>

# Destroys an existing team:
yarn team destroy <scope:team>

# Add a user to an existing team:
yarn team add <scope:team> <user>

# Remove a user from a team they belong to:
yarn team remove <scope:team> <user>

# Return a list of existing teams under that organisation:
yarn team list <scope>|<scope:team>
```

#### `yarn test`

Shorthand for `yarn run test`.

#### `yarn upgrade`

```
yarn upgrade [package | package@tag | package@version]
```

Updates dependencies to their latest versions based on the version range specified in `package.json`. 

`yarn.lock` will be updated as necessary.

```
yarn upgrade
```

Optionally, one or package names can be specified, to update only those specific dependencies. Target versions and tags for the packages can be provided, too.

```
yarn upgrade left-pad
yarn upgrade left-pad@^1.0.0
yarn upgrade left-pad grunt
yarn upgrade @angular
```

You can also target packages within an `@` scope:

```
yarn upgrade --scope @angular
```

Pattern matching is another way to target specific packages for upgrade:

```
yarn upgrade --pattern "gulp|grunt"
```

The `yarn upgrade --latest` command ignores the version range specified in `package.json`. Instead, it targets the version specified by the `latest` tag. Careful. This can upgrade packages across major versions. `package.json` will be updated.

The `yarn upgrade-interactive` command takes the same parameters as `yarn upgrade`, but this will first display the outdated packages before performing the upgrade, allowing the user to select which packages to upgrade. It is equivalent to running `yarn outdated` followed by `yarn upgrade [package...]`.

#### `yarn version`

Use this command to update the current package's version number, as defined in `package.json`. You will be prompted for the new version number, which must be a valid SemVer version. Or you can provide this via `yarn version --new-version <version>`. Other methods to bump the version number are:

```
# Increment the major, minor or patch number of the current version:
yarn version --major
yarn version --minor
yarn version --patch

# Increment the major, minor or patch number of the current version
# and add a pre-release number.
yarn version --premajor
yarn version --preminor
yarn version --prepatch

# Increment the current pre-release version number, 
# keeping the current target release number.
# yarn version --prerelease
```

For all of the pre-release commands, optionally you can provide a prefix (`<pre-identifier>`) for the pre-release version number.

```
yarn version [--premajor | --preminor | --prepatch | --prerelease] --preid <pre-identifier>
```

If you run `yarn version` within a Git repository, an [annotated Git tag](https://git-scm.com/book/en/v2/Git-Basics-Tagging) will be created in the format `v0.0.0`. You can modify this behaviour via the following `yarn config` options:

```
yarn config set version-tag-prefix "v"
yarn config set version-git-message "v%s"
yarn config set version-sign-git-tag false
yarn config set version-git-tag true
yarn config set version-commit-hooks false
```

To disable this behaviour, run `yarn version --no-git-tag-version`. Also, `yarn version --no-commit-hooks` will bypass running commit hooks when committing the new Git version tag.

In addition, running `yarn version` will run the following lifecycle methods, if they are defined in the package's `scripts` property:

- `preversion`
- `version`
- `postversion`

The scripts will be passed some variables holding version information. The `preversion` script gets `npm_package_version` will hold the version before the change, while in `postversion` script the same variable will hold the new version number. This can be useful to push new tags to the upstream Git repository. In the following example, the lifecycle scripts ensures that the new version is published to the NPM registry and that the remote Git repository reflects the updated version.

```
{
  "name": "example-package",
  "version": "1.0.2",
  "scripts": {
    "test": "echo \"Running tests for version $npm_package_version...\"",
    "preversion": "yarn test",
    "postversion": "git push --tags && yarn publish . --tag $npm_package_version && git push"
  }
}
```

The `yarn versions` command is not related to the current package, but rather displays the version information for the currently-installed version of Yarn, Node and its dependencies.

#### `yarn why`

Use this command to identify why a certain package has been installed. Example:

```
yarn why jest
```

The output will explain which other packages depend on it, or whether it was explicitly marked as an application dependency in the `package.json` manifest.

The mandatory argument can be a package name, a package folder (e.g. `node_modules/jest`) or a file within a package.

#### `yarn workspace(s)`

Large software projects, that are composed from lots of smaller packages, tend to be developed as monorepos. A monorepo is a single source repository that stores the source for multiple independent packages, which are published to package registries separately but together make up a complete software system. The monorepo approach reduces the burden of writing and maintaining code across packages — particularly things like dependency management, compilation and testing, the scripting for which tends to be consistent from one package to another.

Projects such as Babel, React, Jest, Vue and Angular are managed as monorepos.

Some monorepo projects have adopted tools such as [Lerna](https://lernajs.io/) to optimise the workflow around managing multi-package repositories. In a nutshell, Lerna calls `yarn/npm install` for each package inside a monorepo project, and then creates symlinks between packages that depend on one another. It means that packages that are developed in parallel and depend on one another are always using the most up-to-date code available.

Yarn has incorporated this functionality directly into its package management system. The feature is called "workspaces". This feature is enabled by default since Yarn 1.0.

A monorepo project using Yarn will have a filesystem structure something like this:

```
| my-project/
| -- package.json
| -- packages/
| ---- pkg-1/
| ------ package.json
| ---- pkg-2/
| ------ package.json
```

The top-level `package.json` defines the root of the project. Sub-folders with other `package.json` files are the workspaces within the project. Workspaces usually (but not necessarily) represent packages that will be published to a registry like NPM. The project root, while shared via a central repository, is not supposed to be consumed as a package and will be marked as `private`. The following root `package.json` enables workspaces and defines some third-party packages that are needed for the shared build and test environment.

```
{
  "private": true,
  "name": "my-project",
  "devDependencies": {
    "chalk": "^2.0.1"
  },
  "workspaces": [
    "packages/*"
  ]
}
```

Individual packages can reference each other as `dependencies`. A tool such as Lerna would create symlinks between those packages in their respective `node_modules` folder. Yarn works differently. Shared dependencies and intra-project symlinks are hoisted to a root-level `node_modules` directory. This is a more optimal dependency structure and makes installation faster and lighter, by lessening package duplication.

Workspaces (packages) don't have their own `yarn.lock` files. Instead, a single root-level `yarn.lock` contains all the dependencies for the whole project. However, workspaces do have their own `package.json` manifests. When you change a dependency using the normal commands from within a workspace, the workspace's `package.json` and the project's `yarn.lock` will be updated.

Further reading: https://yarnpkg.com/en/docs/workspaces

#### `--verbose`

The `--verbose` flag can be added to any `yarn` command. It will print more verbose info about the result of the command, such as created directories, copied files, HTTP endpoints requested, etc.
