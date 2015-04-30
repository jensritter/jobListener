#!/bin/sh

JARDIR=target

CP=""
for loop in $JARDIR/*.jar
do
	echo $loop | grep "sources" || \
	CP="$CP:$loop"
done 
#	-XX:StartFlightRecording=duration=60s,filename=myrecording.jfr \
#	-XX:+UseG1GC \


java \
	-XX:MaxMetaspaceSize=100M \
	-XX:+UnlockCommercialFeatures \
	-XX:+FlightRecorder \
	-XX:StartFlightRecording=maxsize=10485760 \
	-cp $CP \
	org.jens.test.JobListenerApplication $@
