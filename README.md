# AuthMeAdvanced

This is the first version of AuthMe AdvancedGui Login system.
He was made for the AdvancedGui community by myself.


## Features:
- Fully working gui from AdvancedGui
- Working 10 digits pads
- Register/Login on the same Ui
- Easy to use no need tutorial for player
- Work with Left-Click and Right-Click (thx AdvancedGui)

## Maybe:
- Masked input like "*" ?
 
## You need:
- AuthMe Reloaded
- AdvancedGui

## Installation:
- Need to have started your server with AdvancedGui first

- Put "authme_login.json" into AdvancedGui "layouts" folder

-  drop .jar file into your plugins folder

- After successful starting; Stop the server

- Go on AuthMe config file

- set kick timeout to 0 (or an high amount because basic config is not really adapted for ui interface)
```
# After how many seconds should players who fail to login or register

# be kicked? Set to 0 to disable.
timeout: 0
```

- set ProtectInventoryBeforeLogIn to false ( i made a custom recover )
```
# Should we protect the player inventory before logging in? Requires ProtocolLib.
```

ProtectInventoryBeforeLogIn: false[/code]


*The only important think in AuthMe config is to disable ProtectInventoryBeforeLogin (if not set to false, gui item are not going to be given).
And all think are successful setup, just need to login and have fun ;)
