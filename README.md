# Timesheets

A program that can keep track of working hours of employees. By logging start- and end-times the program is able to determine how long the employee has worked, and at what times the employee has clocked in and out.

The program tracks its data via a encrypted MySQL database in order to keep its data secure. Every start and exit of the program will backup its database. If neccesary, a backup can be reversed from the settings menu

All program activities are logged locally in order to track program errors when neccesary, ensuring the ability to quickly fix errors.

The program offers a few settings that can be tweaked by the user, such as the amount of logs/backups that have to be kept; whether or not logs should be deleted; etc.

## Getting Started

The application can be downloaded as a .jar file, or as an executable from the build folder. 
* For the .exe file: Simply double click the file to open the program.

* For the .jar file: The application can then be launched by opening a command line and typing:
	```
	java -jar Timesheets.jar
	```
	The program also has a few command line arguments that can be passed trough, such as:
	```
	--version	Prints program version
	--help or -h	List all possible launch configurations
	-debug or -d	Enables debugging mode (prints logs to command line, etc)
	```

## Prerequisites

The program is built in Java 8. In order for the application to work, a working installation of Java version 8 or higher is therefore necessary.

## Special Thanks

* **Program Icon** - Adapted from [Skoll](https://game-icons.net/1x1/skoll/atom.html), under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/)
* **JDBC SQLite Driver** - by [Taro L. Saito](https://bitbucket.org/xerial/sqlite-jdbc/downloads/), under [Apache License Terms](https://bitbucket.org/xerial/sqlite-jdbc/src/default/LICENSE)
* **SpinnerTemporalModel** - Adapted from [Darryl Burke](https://sites.google.com/site/anglogoa/), from [Java Tips Weblog](https://tips4java.wordpress.com/2015/04/09/temporal-spinners/)

## Authors

* **Danick Triantis** - Creator and Developer - [CaptainKills](https://github.com/CaptainKills)
