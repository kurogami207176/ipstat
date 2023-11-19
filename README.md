# Ip stats
This parses a log file and gets some statistics on the data

## Prerequisite
1. Java 17
2. Maven. Execute below to compile.
```shell
mvn clean install
```
3. log file with the following format
```shell
177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] "GET /intranet-analytics/ HTTP/1.1" 200 3574
```

## Run
To execute using the included log file
```shell
mvn spring-boot:run
```
Manual shutdown (Ctrl+C) is required atm to shut this down.

## Result
Results will appear in the present working directory with filename `result-*,txt`


To execute with own log file (utf-8), execute the following
```shell
mvn spring-boot:run -Dlog.source=<logfile location>
```

## Technical Considerations
1. SQL was used to generate stats since it's more extensible as opposed to manually coding them in java.

