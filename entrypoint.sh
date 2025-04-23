#!/bin/bash

# This script is used to run the application in a Docker container.

java \
  -Xms256m -Xmx512 \                # Set initial and max heap size
  -XX:+UseG1GC \                   # Use G1 Garbage Collector (good for most apps)
  -XX:MaxGCPauseMillis=200 \       # Target max GC pause time (tune as needed)
  -XX:+HeapDumpOnOutOfMemoryError \ # Dump heap on OOM for diagnostics
  -XX:HeapDumpPath=/tmp/heapdump.hprof \ # Path for heap dump
  -XX:+ExitOnOutOfMemoryError \    # Exit JVM on OOM
  -Dspring.profiles.active=prod \  # Use production Spring profile the properties use env params
  -Dfile.encoding=UTF-8 \          # Set file encoding
  -jar /app.jar