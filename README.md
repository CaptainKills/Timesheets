# Timesheets

A program that can keep track of working hours of employees. By logging start- and end-times the program is able to determine how long the employee has worked, and at what times the employee has clocked in and out.

The program tracks its data via an encrypted SQL database in order to keep its data secure. The program will backup its database at the start and exit of the program, as well as on an horuly basis. If neccesary, a backup can be reversed from the settings menu

All program activities are logged locally in order to track program errors when neccesary, ensuring the ability to quickly debug errors.

## Getting Started

The latest version (Release 2.0.0) of the application can be downloaded as a .jar file, including an executable wrapper from the build folder. 
* From Executable: Simply double click the .exe file to open the program. (.jar file has to be in the same folder as the .exe wrapper)

* From Console: The application can then be launched by opening your command line and typing:
	```
	java -jar Timesheets.jar
	```
	The program also has a few command line arguments that can be passed trough, such as:
	```
	--version	Prints program version.
	--help or -h	List all possible launch configurations.
	-debug or -d	Enables debugging mode (prints logs to command line, etc).
	-edit or -e	Eneters program in database editing mode.
	```

## Configuring The Settings

The program offers a few settings that can be tweaked by the user, such as the amount of logs/backups that have to be kept; whether or not logs should be deleted; etc.

Below you can find a list of all the settings and their possible configurations:
* `fontsize`,			the font size of text displayed. Can be any number.
* `width`,			the width of the program window in pixels. Can be any number.
* `height`,			the height of the program window in pixels. Can be any number.
* `delete_logs`, 		enables the program to delete any log files. Can be `true` or `false`.
* `number_of_logs`, 		the minimum number of logs that will not be deleted if `delete_logs` is enabled. Must be > 0.
* `number_of_backups`,		the maximum number of database backups that will be kept. Can be any number.
* `language`, 			the language of all text displayed by the program. Can be `en` (English) or `nl` (Dutch).
* `exit_on_close`, 		enables the program to close via closing the window. Can be `true` of `false`
* `round_off_time`, 		enables the recorded time to be rounded to quarters of an houre. Can be `true` or `false`.

All settings can be adjusted in the `settings.properties` file in the root folder of your Timesheets installation.

## Prerequisites

The program is built in Java 8. In order for the application to work, a working installation of Java version 8 or higher is therefore necessary.

## Special Thanks

* **Program Icon** - Adapted from [Skoll](https://game-icons.net/1x1/skoll/atom.html), under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/)
* **JDBC SQLite Driver** - by [Taro L. Saito](https://bitbucket.org/xerial/sqlite-jdbc/downloads/), under [Apache License Terms](https://bitbucket.org/xerial/sqlite-jdbc/src/default/LICENSE)
* **Apache POI** - by [The Apache Software Foundation](https://poi.apache.org/), under [Apache License Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)
* **SpinnerTemporalModel** - Adapted from [Darryl Burke](https://sites.google.com/site/anglogoa/), from [Java Tips Weblog](https://tips4java.wordpress.com/2015/04/09/temporal-spinners/)

## Authors

* **Danick Triantis** - Creator and Developer - [CaptainKills](https://github.com/CaptainKills)
