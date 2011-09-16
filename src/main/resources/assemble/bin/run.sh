#!/bin/sh

# Detection of the current directory
CUR_DIR=`dirname $0`

CONF_DIR=$CUR_DIR/../conf

# Java Binary Location. Should be 6.0 or higher
JAVA_BIN="/usr/bin/java"

# JVM Options
JAVA_OPTS=" -Xmx64M"

CP=$CUR_DIR/../jcommander-guice-sample.jar:$CONF_DIR

$JAVA_BIN $JAVA_OPTS -classpath $CP OneJar $*