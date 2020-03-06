# Visual Studio Code Cheats

## Remote connections

VSCode can connect to remote filesystems over SSH using Microsoft's own [Remote - SSH](https://code.visualstudio.com/docs/remote/ssh) extension.

To connect to a locally Vagrant-provisioned VM:

1.  `cd` to the `Vagrantfile`'s path.
2.  Start the VM with `vagrant up`.
3.  With the VM up, run `vagrant ssh-config`. This command outputs the SSH configuration that Vagrant uses to SSH into the guest machine.
4.  Copy the output to `~/.ssh/config` in your home directory.

    ```
    Host default
      HostName 127.0.0.1
      User vagrant
      Port 2222
      UserKnownHostsFile /dev/null
      StrictHostKeyChecking no
      PasswordAuthentication no
      IdentityFile C:/Users/Kieran/.vagrant.d/insecure_private_key
      IdentitiesOnly yes
      LogLevel FATAL
    ```

5.  Change the hostname to something meaningful that represents the VM. Example:

    ```
    Host api.example.local
    ```

    Multiple hosts may be included in the same `config` file.

6.  Test the configuration works. With the configuration in place in your home directory, you should be able to use the `ssh` client to connect to the VM from any location on your host machine:

    ```
    $ ssh api.example.local
    ```

7.  The Visual Studio Code Remote SSH extension will automatically detect the new configuration. Go to **Remote Explorer** and click the **SSH Targets Configuration** option (represented by a gear icon). In the popup, select the path to the `.ssh/config` file in your home directory.

    The list of SSH targets will be imported from your `.ssh/config` file into the VSCode's Remote Explorer panel.

8.  Click **Connect to Host in New Window**. 

9.  Click **Open Folder** and navigate to your project's root directory on the guest machine's filesystem.
