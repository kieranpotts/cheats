# Pageant cheats

#### Load keys automatically on Windows startup

1. Go to `C:\Users\[User]\AppData\Roaming\Microsoft\Windows\Start Menu\Programs`
2. Open the context menu for the folder, select `New` > `Shortcut`
3. Browse to `C:\Program Files\PuTTY` and select `pageant.exe`
4. Name the Shortcut `Pageant with Preloaded Keys`
5. Open the context menu for the newly-created shortcut and select `Properties`
6. Change the value of the `Start in` field to the path of the directory that contains your private keys, eg:

```
"C:\Users\Kieran\.ssh"
```

7. After the `Target` path, list all the names of the Putty-compatible private keys that you want to preload, eg:

```
"C:\Program Files\PuTTY\pageant.exe" id_rsa.ppk id_rsa_2.ppk id_rsa_3.ppk
```

Add the shortcut to you Start menu or copy it to `C:\Users\[User]\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup`.
