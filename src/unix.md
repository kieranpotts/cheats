# Unix cheats

This is a collection of useful Unix/Linux/BSD commands and tasks.

Many of these cheats are taken from the [Unix Toolbox](http://cb.vu/unixtoolbox.xhtml) which is copyright © Colin Barschel 2007-2016 and subject to the [CC BY-SA 3.0](https://creativecommons.org/licenses/by-sa/3.0/) license.


## `sudo` ("super user do")

The `sudo` command is commonly used to install, start and stop applications that require root privileges.

```sh
$ sudo -l                   # List available commands.
$ sudo [command]            # Run [command] as root.
$ sudo -u root [command]    # Alternative way to run [command] as root.
$ sudo -u [user] [command]  # Run [command] as [user]. 
```

Example:

```sh
$ sudo /etc/mongodb start
```

When you execute a command with `sudo`, you will be asked to confirm your password. If the `sudo` configuration permits access, the command is executed.

To switch the superuser (root) account:

```sh
$ sudo su           # Switch to the root account.
$ sudo su -         # Switch to root with root's environment.
$ sudo su - [user]  # Switch to [user]'s account with [user]'s environment.
```

Another way to switch to another account is to use `sudo` with the `-s` option. The command `sudo -s` will start a shell as root. To start a shell as another user, provide the `-u` option.

```sh
$ sudo -s            # Start a shell as root.
$ sudo -u root -s    # Alternative way to start a shell as root.
$ sudo -u [user] -s  # Start a shell as [user].
```

To check which account the current shell is running under:

```sh 
$ whoami
root
```

#### `sudo` configuration

The `sudo` configuration is typically controlled by the system administrator and requires root access to change. To change the `sudo` configuration, use the command `visudo`. This starts the vi editor and opens the `/etc/sudoers` file. The `visudo` command itself has to be executed with root-level privileges (eg `sudo visudo`).

There are lots of options in the sudoers file, but the most important bit is the list of users and what commands each user can run:

```
user host=(users) [NOPASSWORD:]commands
```

Examples:

```
admin ALL=(ALL) NOPASSWORD:ALL
robin servername=(root) /etc/init.d/apps
```

#### `su`

The `sudo su` command switches to the superuser (root) account. An alternative way to do this is to use the shorter `su` command.

```sh
$ su [user]
```

If you run the command `su` with no parameters at all, it will assume you are tring to become the superuser or root account, equivalent to `su root`.

By default, your current environment is passed to the new shell, unless you specify an alternative user after a hyphen character. In this case, `su` creates an environment as though the given user had logged in directly.

```sh
$ su - [user]
```

Use the `-c` option to specify a command to be executed. If the command you want to run contains spaces, encapsulate the command in quotes.

```
$ su -c 'command options'
```

Again, the `whoami` command can be used to check user you are working as.


