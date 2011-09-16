
SET CONF_DIR=../conf

# Java Binary Location. Should be 6.0 or higher
SET JAVA_BIN="D:/j2sdk1.6.0/bin/java"

# JVM Options
SET JAVA_OPTS=-Xmx64M

SET CP=../jcommander-guice-sample.jar;%CONF_DIR%

%JAVA_BIN% %JAVA_OPTS% -classpath %CP% OneJar %*