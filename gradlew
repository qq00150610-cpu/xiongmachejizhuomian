#!/bin/sh
#
# Gradle wrapper script for UNIX systems
#

# Determine the Java command to use
if [ -n "$JAVA_HOME" ] ; then
    JAVA_CMD="$JAVA_HOME/bin/java"
else
    JAVA_CMD="java"
fi

# Setup the classpath
APP_HOME=$( cd "${0%/*}" && pwd -P )
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Execute Gradle
exec "$JAVA_CMD" -jar "$CLASSPATH" "$@"
