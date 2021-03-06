@echo off
REM needed to overcome weird loop behavior
REM in conjunction with variable expansion
SETLOCAL enabledelayedexpansion

REM Settings
set basedir=.
set libdir=libs
set configdir=etc
set mainclass=org.jens.test.JobManagerApplication

REM construct classpath of seperate jars
set cp=""

FOR %%F IN (%libdir%/*.jar) DO (
  SET cp=!cp!;%libdir%/%%F%
)


set java="%JAVA_HOME%\bin\java.exe"
set config=%configdir%/jobmanager.properties
set logback=%configdir%/logback.xml

%java% ^
-cp %cp% ^
-Dspring.config.location=classpath:/application.properties,file:./%config% ^
-Dlogging.config=%logback% ^
%mainclass%
	
exit /B %ERRORLEVEL%
