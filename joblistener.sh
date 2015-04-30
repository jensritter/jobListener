#!/bin/sh

JARDIR=target

CP=""
for loop in $JARDIR/*.jar
do
	echo $loop | grep "sources" || \
	CP="$CP:$loop"
done

java -cp $CP \
	org.jens.test.SpringMain $@
