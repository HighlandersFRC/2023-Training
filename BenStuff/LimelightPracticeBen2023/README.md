PhotoVision LimeLight Setup

1:Download belena etcher (flash tool) and the latest image (gloworm-Release_.zip) from this website [https://docs.photonvision.org/en/latest/docs/getting-started/installation/install/gloworm.html]

2: Run balena etcher (which you downloaded earlier) as admininstrator

3: Connect to the LimeLight via USB to microUSB (no ethernet cable yet)

4: Click the "flash from file" button, and select gloworm-Release_.zip (which you downloaded earlier, it should appear in the downloads folder)

5: Click "select target", and then click "show hidden"

6: It will show a target, labeled something like "LITEON LCH-256V2S-HP". This is your system drive, do NOT flash this. Wait 20-30 seconds, and eventually another target will appear. This is the LimeLight, it will initialize it for a bit and then you can select it.

7: Flash! click the flash button, and wait for your LimeLight to flash.

8: Unplug the USB-microUSB from the LimeLight, and plug it in to the robot radio via ethernet cable.

9: Now go to http://gloworm.local:5800/

10: Click on the Settings tab

11: Go to Networking, and for Team Number, enter you team number (example 1234)

12: In IP Assignment Mode, select static

13: In IP, enter your desired IP address as "10.Team.Number.11" (example 10.12.34.11)

14: In HostName, enter your desired name for the LimeLight (this will be viewable to others)

15: Go to [https://docs.photonvision.org/en/latest/docs/getting-started/installation/limelight.html] and go to Hardware Specific Steps. Download the hardwareConfig.json for your LimeLight (either LimeLight version 2 or LimeLight version 2+, depending on your version of limelight). make sure the file is named "hardwareConfig", or else the next step won't work.

16: go back to the website in steps 10-14 (LimeLight IP address) and click "Import Settings". select "hardwareConfig" (which you just downloaded).

17: Scroll to the bottom and click "save". This should be the final setup step, if everthing worked.

18: To check that it worked, go to "(LimeLight IP):5800/#/dashboard". This should come up with a video feed of you limelight.

                     If you had any trouble with any of this, try following the setup instructions from these sites 
[https://docs.limelightvision.io/en/latest/getting_started.html#imaging] - basic setup and downloads, don't use the image on the download site, use the image from the photovison site (below)

[https://docs.photonvision.org/en/latest/docs/getting-started/installation/limelight.html]