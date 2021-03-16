# SSH cheats

SSH – Secure Shell – is a secure protocol for running programs on another computer. It provides a CLI to the connected machine by spawning a remote shell. While connected, all commands that you type in your local terminal are sent to the remote computer and executed there.

SSH is commonly used to provision and maintain web servers remotely.

#### Contents

- **[Syntax](#syntax)**
- **[Basic connection](#basic-connection)**
- **[Creating SSH key pairs](#creating-ssh-key-pairs)**
- **[Changing the passphrase for private keys](#changing-the-passphrase-for-private-keys)**
- **[Editing comments](#editing-comments)**
- **[SSH authentication agents](#ssh-authentication-agents)**
- **[Get the fingerprint](#get-the-fingerprint)**
- **[Distributing public keys](#distributing-public-keys)**
- **[Revoking access](#revoking-access)**
- **[Disabling password authentication](#disabling-password-authentication)**

## Syntax

```sh
# Connect to a remote machine as <user>.
$ ssh <user>@<hostname_or_ip_address>

# Connect to a remote machine on the specified <port> as <user>.
$ ssh -p <port> <user>@<hostname_or_ip_address>

# Add an SSH key to <host> for <user>, enabling keyed or passwordless login.
$ ssh-copy-id <user>@<hostname_or_ip_address>
```

## Basic connection

```sh
$ ssh <user>@<hostname_or_ip_address>
```

The first time you connect to a remote machine, the response should be something like:

```
The authenticity of host "<hostname> (<ip_address>)" can't be established. RSA key fingerprint is <fingerprint>. Are you sure you want to continue?
```

Type `yes` and the response will be:

```
"Warning: Permanently added '<hostname>, <ip_address>' (RSA) to the list of known hosts.
```

At the prompt, enter `<user>`'s password. You should now be logged in, as represented by the command line prompt. 

To exit and return to the command line of your local machine:

```
logout
```

## Creating SSH key pairs

To login without a password, you will need to generate a password-less SSH key pair. As long as the remote server supports RSA key authentication, you will be able to get in without entering a password. Instead, you provide your private key, and your identity is verified by the corresponding public key that is pre-installed on the remote server you're connecting to.

SSH keys are useful for automated scripts – such as deployments and backups – since you will not need to hard-code passwords into the scripts.

A number of cryptographic algorithms can be used to generate SSH keys, including RSA, DSA and ECDSA. RSA keys are generally preferred and are the default key type.

Generating a new SSH public/private key pair on your local computer is the first step towards authenticating with a remote server without a password.

#### Unix-like systems

The following instructions assume your local machine is a Debian-flavoured OS such as Ubuntu. However, the instructions will be similar for all Linux distributions as well as macOS, since the `ssh-keygen` utility is installed by default on those operating systems.

Using the machine that you want to connect _from_, create the directory `.ssh` in your home directory.

```sh
$ mkdir -p ~/.ssh
$ cd ~/.ssh
```

Check the contents of the directory:

```sh
$ ls -lha
```

If the files `id_rsa` and `id_rsa.pub` exist, then an RSA key for the current user already exists. There is no need to do this again, so the next few steps can be skipped.

Otherwise, to generate an RSA key pair on your local computer, type:

```sh
$ ssh-keygen
```

This is equivalent to:

```sh
$ ssh-keygen -t rsa
```

SSH keys are 2048 bits by default. This is generally considered to be good enough for security, but you can specify a greater number of bits for a more hardened key. Use the `-b` option with the number of bits you would like. Most servers support keys with a length of 4096 bits. Longer keys may not be accepted, for DDOS protection reasons.

```sh
$ ssh-keygen -b 4096
```

You can optionally provide a comment, which will be embedded in the generated public/private keys. It is conventional to use this to provide a unique string that identifies you – or the device, if the private key will be installed on a specific client. This could be your name, a username, email address, IP address, service name, hostname or URL.

```sh
ssh-keygen -C "<comment>"
```

If you had previously created a different key, you will be asked if you wish to overwrite it:

```
Overwrite (y/n)?
```

If you choose "yes", your previous key will be overwritten and you will no longer be able to log into servers using that key. Overwrite keys with caution.

The response will be:

```
Generating public/private rsa key pair.
Enter file in which to save the key (/home/<user>/.ssh/id_rsa):
```

This prompt allows you to choose the location to store you RSA private key. Press ENTER to leave this as the default, which will store the public/private key pair in the `.ssh` directory in your home directory using the file names `id_rsa.pub` and `id_rsa` respectively. Using the default file paths means the SSH service will find your keys automatically, no configuration required.

The next prompt asks for a passphrase to secure your private key. This is optional. If you choose to protect your private key with a passphrase, that makes the key more secure – in the event of it being stolen – but you will have to input the passphrase every time you use your private key to connect to any remote service. You can improve your workflow by using a local SSH authentication agent to hold your passphrase in memory, but still passphrase-protected private keys are not practical for automation tasks.

Passphrases can be any arbitrary length.

```
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
```

After creating a new key pair, the response will be something like:

```
Your identification has been saved in /home/<user>/.ssh/id_rsa.
Your public key has been saved in /home/<user>/.ssh/id_rsa.pub.
The key fingerprint is:
8c:e9:7c:fa:bf:c4:e5:9c:c9:b8:60:1f:fe:1c:d3:8a root@here
The key's randomart image is:
+--[ RSA 2048]----+
|                 |
|                 |
|                 |
|       +         |
|      o S   .    |
|     o   . * +   |
|      o + = O .  |
|       + = = +   |
|      ....Eo+    |
+-----------------+
```

There will now be two files in the `~/.ssh` directory:

- `id_rsa`
- `id_rsa.pub`

The file `id_rsa.pub` is your public key. You will need to install this on the remote servers that you want to connect to. `id_rsa` is your private key, which needs to be stored securely on your client machine. This must not be shared.

#### Windows: ssh-keygen

Windows 10 has an OpenSSH-compatible client available as an optional feature since the April 2018 update. The easiest option is to enable this feature and follow broadly the same steps to generate keys using the `ssh-keygen` utility. Alternatively, `ssh-keygen` (and other OpenSSH utilities) are bundled with [Git Bash for Windows](https://gitforwindows.org/), or you can use the Ubuntu Bash Shell (another optional feature of Windows 10 Pro).

To enable the OpenSSH module for PowerShell, launch PowerShell with admin privileges and enter the following cmdlet. If this is the first time the module has been installed on the device, you may be prompted to download and install some additional tools. Type "Y" to allow the tools to be installed.

```
> Install-Module -Force OpenSSHUtils
```

Start the sshd service:

```
> Start-Service sshd
```

Run the `ssh-keygen` program:

```
> ssh-keygen -b 2048|4096 -C "<comment>"
```

You will be prompted to provide a directory to store the keys. Press ENTER to use the default location, which will be `%HOMEDRIVE%\ProgramData\ssh`.

At the prompt, input a passphrase to protect the private key if desired.

When the process is complete, two files will be created and the key's random art image will be displayed in the terminal.

#### Windows: PuTTYgen

Before Windows was given its own OpenSSH-compatible client, a suite of community-made utilities revolving around a terminal emulator called PuTTY was the most popular way to handle SSH processes in Windows. PuTTY is compatible with OpenSSH – the de facto standard implementation of the SSH protocol – but it uses a proprietary key file format. However, a tool called PuTTYgen can be used to convert keys between the proprietary `.ppk` format and standard OpenSSH key formats.

Download PuTTYgen from here: http://www.chiark.greenend.org.uk/~sgtatham/putty/download.html

Open PuTTYgen. To generate new public/private key pairs from scratch, leave the defaults in place and click Generate. Follow the instructions about wriggling your mouse around to create some randomness. PuTTYgen will generate a strong public key, which you can see at the top of the dialog box.

In the "Key comment" field add a comment that describes the key. This comment will be embedded within both the public and private keys. It will also be displayed to the user when logging into a remote server.

Copy the public key from the top of the PuTTYgen dialog box into a text file, and save it to your desktop. You will need it later. Do not use the "Save public key" option, which exports the public key string in PuTTY's proprietary key format instead of the standard PEM form. Be sure to copy the full text. The public key will look something like this:

```
ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAQEAklmPYUPtR8nn+pDj/LoSXG....094vw== Kieran Potts
```

To generate the private key, optionally enter a passphrase in the "Key passphrase" text field, and enter it a second time to confirm. Click on the "Save private key" button. This will save the private key, which is passphrase protected, to your desktop as a `.ppk` file.

If you have previously created valid OpenSSH keys, and you want to use the private key with any of the PuTTY utilities, you will need to convert the OpenSSH-compatible private key (`id_rsa`) to a PuTTY-compatible key format. To do that, run PuTTYgen and go to File > Load Private Key, and import your `id_rsa` private key. If you get an error that says something like "Couldn't load private key (unrecognised cipher name)", this is because when OpenSSH generated the private key it probably used a newer cipher that is not supported by PuttyGen. To work around this, run the commands below from a terminal on a Unix-like system. This creates a version of the private key with the passphrase removed. You can put the passphrase back again when you generate the Putty-compatible private key file.

```sh
$ cp ~/.ssh/id_rsa ~/.ssh/id_rsa_putty
$ ssh-keygen -p -f ~/.ssh/id_rsa_putty
```

Otherwise, if PuttyGen can handle the original private key, you'll be prompted for it's passphrase (if it has one) and then key's fingerprint and corresponding public key will be visible in the PuttyGen window.

```
Enter old passphrase: <your passphrase>
Enter new passphrase (empty for no passphrase): <press Enter>
Enter same passphrase again: <press Enter>
```

To convert the key from OpenSSH to PuTTY's proprietary key format, go to File > Save Private Key and export a copy as `id_rsa.ppk`. Remember to add the passphrase, if you removed it in the previous step (`id_rsa_putty` can now be deleted).

## Changing the passphrase for private keys

Passphrases that unlock private keys can be changed or removed from the private key at any time (as long as you know the original passphrase). The public key is unaffected, because passphrases are linked only to the private key.

To change or remove the passphrase:

```sh
$ ssh-keygen -p
```

Response:

```
Enter file in which the key is (/home/<user>/.ssh/id_rsa):
```

You can type the location of the key you wish to modify, or press ENTER to accept the default path.

```
Enter old passphrase:
```

Enter the old passphrase that you wish to change. You will then be prompted for a new passphrase, which you can leave empty if you want to remove the passphrase entirely.

```
Enter new passphrase (empty for no passphrase):
Enter same passphrase again:
```

## Editing comments

The comment at the end of an SSH RSA public key can be changed at any time. Changing the comment in the public key does not affect authorization with the corresponding private key.

To change the comment in the private key as well, use the `ssh-keygen` utility:

```sh
$ ssh-keygen -c -C "new-comment-here" -f ~/.ssh/my_ssh_key
```

## SSH authentication agents

SSH authentication agents are used to manage multiple private SSH keys on client devices, and to hold passphrases (for passphrase-protected private keys) in memory for the duration of a session, so the user does not need to keep entering the passphrases every time a passphrase-protected private key is used to authenticate with a remote computer.

To start the agent in the background:

```
# Mac/Linux:
eval "$(ssh-agent -s)"

# Git Bash for Windows:
eval $(ssh-agent -s)

# Windows PowerShell:
Start-Service ssh-agent
```

The output will be the process ID (PID) in which the agent is running. 

If you're using macOS Sierra 10.12.2 or later, you'll need to modify your `~/.ssh/config` file to automatically load keys into `ssh-agent` and store passphrases in your keychain:

```
Host *
 AddKeysToAgent yes
 UseKeychain yes
 IdentityFile ~/.ssh/id_rsa
```

Next, add your SSH private key to the SSH agent. Windows users: first copy your `id_rsa` file to `C:\Users\<username>\.ssh\id_rsa`.

```
# Mac:
ssh-add -K ~/.ssh/id_rsa

# Linux/Windows:
ssh-add ~/.ssh/id_rsa
```

The `-K` option is Apple's standard version of `ssh-add`, which stores the passphrase in your keychain when you add an SSH key to the SSH agent.

`ssh-agent` always runs in the background in Linux and Mac. For Windows 10 with the OpenSSH client enabled, run the following command from a super-user PowerShell prompt:

```
Set-Service ssh-agent -StartupType Automatic
```

For Git Bash for Windows, paste the following lines into your `~/.profile` or `~/.bashrc` file (it doesn't matter which) to have `ssh-agent` started automatically whenever you open a new Git Bash terminal. If your private key is not stored in one of the default locations (`~/.ssh/id_rsa` or `~/.ssh/id_dsa`), you'll need to tell your SSH authentication agent where to find it via the `ssh-add` commands near the bottom of the script.

```
env=~/.ssh/agent.env

agent_load_env () { test -f "$env" && . "$env" >| /dev/null ; }

agent_start () {
    (umask 077; ssh-agent >| "$env")
    . "$env" >| /dev/null ; }

agent_load_env

# agent_run_state: 0=agent running w/ key; 1=agent w/o key; 2= agent not running
agent_run_state=$(ssh-add -l >| /dev/null 2>&1; echo $?)

if [ ! "$SSH_AUTH_SOCK" ] || [ $agent_run_state = 2 ]; then
    agent_start
    ssh-add
elif [ "$SSH_AUTH_SOCK" ] && [ $agent_run_state = 1 ]; then
    ssh-add
fi

unset env
```

Now, when you launch your terminal, you will be prompted for your private key's passphrase (if it needs one). The `ssh-agent` process will continue to run until you log out, shut down your computer, or kill the process. That whole time the SSH agent will remember your passphrase, so you don't have to re-enter it every time you authenticate with a remote service using the passphrase-protected private key.

#### Pageant

On Windows, if you intend to connect to remote servers from local applications other than your terminal emulator, you may also need to use the Pageant SSH authentication agent. Windows 10 has only had a built-in OpenSSH-compatible client since April 2018. Before then, a suite of tools revolving around the [PuTTY](https://www.chiark.greenend.org.uk/~sgtatham/putty/) utility were the most popular way to manage SSH sessions. Numerous Windows applications still depend on these utilities to delegate SSH behaviours. For example, some database GUI applications (such as DBeaver) require Plink to make SSH tunnels to remote databases, and some Git GUIs (including those built-in to IDEs such as VSCode) require Pageant to be running in the background to handle passphrase-protected private keys.

Pageant is an SSH agent GUI for Windows. It can be installed as part of the [PuTTY](http://www.chiark.greenend.org.uk/~sgtatham/putty/) suite of SSH client applications.

Run Pageant and load the PuTTY-compatible `*.ppk` version of your private key. Enter the key's passphrase at the prompt, if required. The key is now available to use by the Pageant SSH agent, and it will remain available until you logout of Windows or close Pageant.

The first time you connect to a server over SSH with a new key, you will be asked to verify that the server's fingerprint is correct. The fingerprint is a short cryptographic string that identifies the server, protecting against man-in-the-middle attacks. This can cause some Git GUI clients (including Git Bash) to fail silently, so it is important to make an initial connection to your remote servers via the command line. Open PowerShell and type commands similar to the following, depending on which remote servers you want to subscribe to:

```
PS C:\Users\Username> & 'C:\Program Files\PuTTY\plink.exe' git@github.com
PS C:\Users\Username> & 'C:\Program Files\PuTTY\plink.exe' git@gitlab.com
PS C:\Users\Username> & 'C:\Program Files\PuTTY\plink.exe' git@bitbucket.org
```

You'll be prompted to add the remote server's fingerprint to the Windows registry. If you're confident that your network traffic is not being intercepted, go ahead and type "y" at the prompt. You might get an error if the remote service does not allow logins over SSH, but that's OK because the purpose of this step is only to cache the remote server's fingerprint. Run the above command again, and you should _not_ be prompted to cache the remote's fingerprint a second time.

If the terminal just hangs when you select to store the server's key in your local cache, try connecting via Putty:

- Open Putty.
- Type the hostname, eg "github.com", "bitbucket.org".
- Click open.
- Click yes in the popup to cache the host key.
- Close Putty immediately thereafter.

Finally, add an environment variable called `GIT_SSH` to your Windows User, and set the value to the path to Plink, e.g. `C:\Program Files\PuTTY\plink.exe`. VSCode will pick up on this variable and use the reference program to connect to remote Git repositories over SSH. Restart VSCode, so that is reads the new environment variable, and try synchronising with a remote repository that was cloned using SSH. It should synchronise successfully with no "permission denied" error.

Git Bash on Windows will also pay attention to the `GIT_SSH` environment variable, using the designated client for SSH clients in place of its built-in SSH agent. This means you don't need to also load your SSH private keys into Git Bash using `ssh-add`. Just load them into Pageant.

#### Automatically load SSH keys into Pageant on Windows startup

So that you don't need to start Pageant and load your SSH keys every time you restart Windows, have this done automatically:

1.  Go to `C:\Users\[User]\AppData\Roaming\Microsoft\Windows\Start Menu\Programs`
2.  Open the context menu for that directory, and select `New` > `Shortcut`
3.  Browse to `C:\Program Files\PuTTY` and select `pageant.exe`
4.  Name the Shortcut `Pageant with Preloaded Keys`
5.  Open the context menu for the newly-created shortcut and select `Properties`
6.  Change the value of the `Start in` field to the path of the directory that contains your private keys, eg:

    ```
    "C:\Users\Kieran\.ssh"
    ```

7.  After the `Target` path, list all the names of the Putty-compatible private keys that you want to preload, eg:

    ```
    "C:\Program Files\PuTTY\pageant.exe" id_rsa.ppk id_rsa_2.ppk id_rsa_3.ppk
    ```

To have Pageant with Preloaded Keys run at startup, copy the shortcut to `C:\Users\[User]\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup`.

## Get the fingerprint

Each SSH key pair shares a single cryptographic "fingerprint" that can be used to uniquely identify the keys. This can be useful in a variety of situations. You can find out the fingerprint of an SSH key at any time, with the following command:

```sh
$ ssh-keygen -l
```

Response:

```
Enter file in which the key is (/home/<root>/.ssh/id_rsa):
```

Press ENTER if the private key is stored at the default location, else type the full path to the key. You will be given a string that contains the bit-length of the key, the fingerprint, and the user and host it was created for, and the algorithm used:

```
4096 8e:c4:82:47:87:c2:26:4b:68:ff:96:1a:39:62:9e:4e  demo@example.com (RSA)
```

## Distributing public keys

To authenticate with a remote machine, your public key first needs to be installed on that remote machine.

On your local machine, you can print the contents of your public key file by typing:

```sh
$ cat ~/.ssh/id_rsa.pub
```

The output will be a single line of text, and it will look something like:

```
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQCqql6MzstZYh1TmWWv11q5O3pISj2ZFl9HgH1JLknLLx44+tXfJ7mIrKNxOOwxIxvcBF8PXSYvobFYEZjGIVCEAjrUzLiIxbyCoxVyle7Q+bqgZ8SeeM8wzytsY+dVGcBxF6N4JS+zVk5eMcV385gG3Y6ON3EG112n6d+SMXY0OEBIcO6x+PnUSGHrSgpBgX7Ks1r7xqFa7heJLLt2wWwkARptX7udSq05paBhcpB0pHtA1Rfz3K2B+ZVIpSDfki9UVKzT8JUmwW6NNzSgxUfQHGwnW7kj4jp4AT0VZk3ADw497M2G/12N0PPB5CnhHf7ovgy6nL1ikrygTKRFmNZISvAcywB9GVqNAVE+ZHDSCuURNsAInVzgYo9xgJDW8wUw2o8U77+xiFxgI5QSZX3Iq7YLMgeksaO4rBJEa54k8m5wEiEE1nUhLuJ0X/vh2xPff6SQ1BL/zkOhvJCACK6Vb15mDOeCSq54Cr7kvS46itMosi/uS66+PujOO+xt/2FWYepz6ZlN70bRly57Q06J+ZJoc9FfBCbCyYH7U/ASsmY095ywPsBo1XQ9PqhnN1/YOorJ068foQDNVpm146mUpILVxmq41Cj55YKHEazXGsdBIbXWhcrRf4G2fJLRcGUr9q8/lERo9oxRm5JFX6TCmj6kmiFqv+Ow9gI0x8GvaQ== demo@example.com
```

You can simply append this to the `~/.ssh/authorized_keys` file for the Unix user you want to SSH in as. On the remote server, create the `~/.ssh` directory if it does not already exist:

```sh
$ mkdir -p ~/.ssh
```

Make sure the file `authorized_keys` exists.

```sh
$ touch authorized_keys
```

Open the `authorized_keys` file in your favourite text editor, and paste in the public key on a new line. Finally, you need to set appropriate permissions on the `.ssh` directory and files:

```sh
$ chmod 700 .ssh
$ chmod 640 .ssh/authorized_keys
```

An easier option is to use a utility called `ssh-copy-id`. It pipes your public key directly to the remote box on which you want to install it, and sets the appropriate file permissions on the remote automatically. This utility is included in most OpenSSH packages for the major Linux distributions.

```sh
$ ssh-copy-id <user>@<hostname_or_ip_address>
```

This will prompt you for the user's password on the remote system:

```
The authenticity of host '111.111.11.111 (111.111.11.111)' can't be established.
ECDSA key fingerprint is fd:fd:d4:f9:77:fe:73:84:e1:55:00:ad:d6:6d:22:fe.
Are you sure you want to continue connecting (yes/no)? yes
/usr/bin/ssh-copy-id: INFO: attempting to log in with the new key(s), to filter out any that are already installed
/usr/bin/ssh-copy-id: INFO: 1 key(s) remain to be installed -- if you are prompted now it is to install the new keys
demo@111.111.11.111's password:
```

After typing in the password, the contents of your `~/.ssh/id_rsa.pub` key will be appended to the end of the user's `~/.ssh/authorized_keys` file:

```
Number of key(s) added: 1

Now try logging into the machine, with:   "ssh 'demo@111.111.11.111'"
and check to make sure that only the key(s) you wanted were added.
```

You can now log into the remote machine without a password:

```sh
$ ssh <user>@<hostname_or_ip_address>
```

To push a public key between Windows systems, use the OpenSSH PowerShell module to pipe the key over. Start by creating the `.ssh` directory within the target user's profile on the remote machine:

```
> ssh username@domain.com mkdir C:\Users\username\.ssh
```

Use the SCP utility to securely transfer the public key.

```
> scp C:\Users\username\.ssh\id_rsa.pub username@domain.com:C:\Users\username\.ssh\authorized_keys
```

Finally, modify the ACL on the authorized_keys file on the server:

```
> ssh --% username@domain.com powershell -c $ConfirmPreference = 'None'; Repair-AuthorizedKeyPermission C:\Users\username\.ssh\authorized_keys
```

## Revoking access

To revoke a client's access to a machine, simply delete their public key line from the `authorized_keys` file.

```sh
$ vim ~/.ssh/authorized_keys
```

## Disabling password authentication

For ultimate security, password authentication should be fully disabled on servers, forcing all users to provide valid SSH keys to authenticate. 

As root:

```sh
$ vim /etc/ssh/sshd_config
```

In that file, change the `PasswordAuthentication` parameter:

```
PasswordAuthentication no
```

Save the file, then restart the SSH service:

```sh
$ systemctl restart sshd
```
