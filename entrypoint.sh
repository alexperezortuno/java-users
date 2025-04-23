#!/bin/bash

# This script is used to run the application in a Docker container.

java -Xms256m -Xmx512m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof -XX:+ExitOnOutOfMemoryError -Dspring.profiles.active=prod -Dfile.encoding=UTF-8 -jar /app/app.jar