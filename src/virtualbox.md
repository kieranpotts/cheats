# VirtualBox

## Troubleshooting

### Broken drivers

If you get the following error on `vagrant up` on Windows, you will need to reinstall the driver for the ethernet adapter. This can happen sometimes when you upgrade VirtualBox.

```txt
Stderr: VBoxManage.exe: error: Failed to open/create the internal network 'HostInterfaceNetworking-VirtualBox Host-Only 
Ethernet Adapter #2' (VERR_INTNET_FLT_IF_NOT_FOUND).
```

1. Go to **Control Panel** > **Network and Sharing Center**.
2. Click **Change adapter settings**.
3. Right-click on the adapter whose device name matches that given in the error message.
4. Go to **Properties** > **Configure** > **Driver**.
5. Press the button to **Update Driver**. Select **Browse my computer for drivers**, then **Let me pick from a list of available drivers on my computer**. Select the choice you get, then click **Next**.

Now try running `vagrant up` again.
