# Melissa MatchUp Object Global Windows Java


## Purpose

This code showcases the Melissa MatchUp Object Global using Java.

Please feel free to copy or embed this code to your own project. Happy coding!

For the latest Melissa MatchUp Object Global release notes, please visit: https://releasenotes.melissa.com/on-premise-api/matchup-object-global/

For further details, please visit: https://docs.melissa.com/on-premise-api/matchup-object-global/matchup-object-global-quickstart.html

The console will ask the user for:

- A txt file that contains global data you would like to matchup
- A txt file that contains US data you would like to matchup

And return 

- A txt file of global duplicate groups
- A txt file of US duplicate groups



## Tested Environments

- Windows 64-bit Java 19
- Powershell 5.1
- Melissa data files for 2023-Q4



## Required File(s) and Programs

#### Binaries
This is the c++ code of the Melissa Object.

- libmdMatchup.so
- libmdGlobalParse.so

#### Data File(s)
- icudt52l.dat
- mdMatchup.dat
- mdMatchup.mc
- mdMatchup.sac
- mdMatchup.cfg
 
## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
This project is compatible with Java 19

#### Install Java

Before starting, make sure that Java has been correctly installed on your machine and your environment paths are configured. 

You can download Java here: 
https://www.oracle.com/java/technologies/downloads/

You can check that your environment is set up correctly by opening a command prompt window and typing the following:
`java --version`

![alt text](/screenshots/java_version.PNG)

If you see the version number then you have installed Java and set up your environment paths correctly!


#### Set up Powershell settings

If running Powershell for the first time, you will need to run this command in the Powershell console: `Set-ExecutionPolicy RemoteSigned`.
The console will then prompt you with the following warning shown in the image below. 
 - Enter `'A'`. 
 	- If successful, the console will not output any messages. (You may need to run Powershell as administrator to enforce this setting).
	
 ![alt text](/screenshots/powershell_executionpolicy.png)

----------------------------------------

#### Download this project
```
$ git clone https://github.com/MelissaData/MatchUpObjectGlobal-Java
$ cd MatchUpObjectGlobal-Java
```

#### Set up Melissa Updater 

Melissa Updater is a CLI application allowing the user to update their Melissa applications/data. 

- Download Melissa Updater here: <https://releases.melissadata.net/Download/Library/WINDOWS/NET/ANY/latest/MelissaUpdater.exe>
- Create a folder within the cloned repo called `MelissaUpdater`.
- Put `MelissaUpdater.exe` in `MelissaUpdater` folder you just created.

----------------------------------------

#### Different ways to get data file(s)
1.  Using Melissa Updater
	- It will handle all of the data download/path and dll(s) for you. 
2.  If you already have the latest zip, you can find the data file(s) and dll(s) in there
	- Use the location of where you copied/installed the data and update the "$DataPath" variable in the powershell script.
	- Copy all the dll(s) mentioned above into the `MelissaMatchupObjectGlobalWindowsJava` project folder.
	
## Run Powershell Script
Parameters:
- -global: a test global txt file
- -us: a test US txt file

  These are convenient when you want to get results for specific txt files in one run instead of testing multiple txt files in interactive mode.

- -license (optional): a license string to test the MatchUp Object Global
- -quiet (optional): add to the command if you do not want to get any console output from the Melissa Updater

  When you have modified the script to match your data location, let's run the script. There are two modes:
- Interactive 

    The script will prompt the user for a global txt file and a US txt file, then use the provided txt files to test MatchUp Object Global.  For example:
    ```
    $ .\MelissaMatchupObjectGlobalWindowsJava.ps1
    ```
    For quiet mode:
    ```
    $ .\MelissaMatchupObjectGlobalWindowsJava.ps1 -quiet
    ```
- Command Line 

    You can pass a global txt file, US txt file, and a license string into the `-global`, `-us`, and `-license` parameters respectively to test MatchUp Object Global. For example:
    ```
    $ .\MelissaMatchupObjectGlobalWindowsJava.ps1 -global "MelissaMatchupGlobalSampleInput.txt" -us "MelissaMatchupUSSampleInput.txt"
    $ .\MelissaMatchupObjectGlobalWindowsJava.ps1 -global "MelissaMatchupGlobalSampleInput.txt" -us "MelissaMatchupUSSampleInput.txt" -license "<your_license_string>"
    ```

	For quiet mode:
    ```
    $ .\MelissaMatchupObjectGlobalWindowsJava.ps1 -global "MelissaMatchupGlobalSampleInput.txt" -us "MelissaMatchupUSSampleInput.txt" -quiet
    $ .\MelissaMatchupObjectGlobalWindowsJava.ps1 -global "MelissaMatchupGlobalSampleInput.txt" -us "MelissaMatchupUSSampleInput.txt" -license "<your_license_string>" -quiet
    ```
This is the expected output from a successful setup for interactive mode:

![alt text](/screenshots/output.png)

## Contact Us

For free technical support, please call us at 800-MELISSA ext. 4
(800-635-4772 ext. 4) or email us at tech@Melissa.com.

To purchase this product, contact Melissa sales department at
800-MELISSA ext. 3 (800-635-4772 ext. 3).
