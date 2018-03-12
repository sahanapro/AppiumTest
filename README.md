# AppiumTest
This is a test project to illustrate how the mobile automation framework Appium can be used to test Android application and run parallel  tests across mutiple android devices. This includes minimalistic test suite written in Java providing simple testcases to run on OpenBikeSharing https://github.com/bparmentier/OpenBikeSharing Android app. Also this test suite uses the Appium Page Object Model to separate the test from the logic along with TestNG and Maven project dependencies.

## System Requirements
* Android SDK 26
* Windows

## Set Up
### Appium Set Up
Before running the test, install Appium Sever and Start the Server on a desired port

### Android SDK Set Up
Install Android Studio which comes along with SDK Manager.
Update Build Tools, Platform Tools and download Android Version (>= API Level 26) and configure Android path on Windows environment variables

### Virtual Device Set-up
Open Android Studio, click Tools ->A VD Manager which brings a window to choose a device to add Android Virtual Device.

### Prepare Real Device
Activate Developer option, enable USB debgging mode, connect the device to ADB(Android debugger Bridger) to find the Device ID and find the app package, launchable app activity and get the Android version number

### Java and IDE Set Up
Download Java JDK and set enviroment variable. Download IntelliJ IDEA and create new project

# TODO
* Implement BDD testcases using Cucumber
* Implement parallel testing using Appium Grid

# Note
### APK
The APK is currently available at https://f-droid.org/repo/be.brunoparmentier.openbikesharing.app_23.apk
However the build instructions are available at the application homepage https://github.com/bparmentier/OpenBikeSharing to build upto date app



