# Virtualization in Windows

Windows computers typically have virtualization disabled by default at the BIOS level, and it needs to be manually enabled to set up virtual machines with tools such as VirtualBox and Docker.

This process requires several restarts, so write down the instructions before proceeding:

1. Go to **Settings** > **Update & Security** > **Recovery**. Click **Restart Now** under the **Advanced startup** section.

2. From the blue advanced startup screen, go to **Troubleshoot** > **Advanced options** > **UEFI Firmware Settings**, then click the **Restart** button.

3. On restart, the system enters BIO mode. Look for the **Virtualization** option, which may be under **Advanced or System Configuration**. It will show that virtualization is disabled. Toggle to enable it. Exit, saving your changes (F10). 

Windows will restart again, this time with virtualization enabled.
