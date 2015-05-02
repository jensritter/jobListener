#!/bin/sh

TODO !

basedir=.
libdir=libs/
configdir=etc/


cp=""
for loop in $libdir/*.jar
do
  cp=$cp:$loop
done

java=$JAVA_HOME/bin/java
logback=$configdir/logback.xml
config=$configdir/joblistener.properties
mainclass=org.jens.test.JobListenerApplication

springconfig=classpath:/application.properties,file:$config

java -cp $cp \
  -Dlogging.config=$logback \
  -Dspring.config.location=$springconfig \
  $mainclass
