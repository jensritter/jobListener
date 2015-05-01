@echo off
REM needed to overcome weird loop behavior
REM in conjunction with variable expansion
SETLOCAL enabledelayedexpansion

REM Settings
set basedir=.
set libdir=libs
set configdir=etc


REM construct classpath of seperate jars
set cp=""

FOR %%F IN (%libdir%/*.jar) DO (
  SET cp=!cp!;%libdir%/%%F%
)

set java="%JAVA_HOME%\bin\java.exe"
rem set config=%confdir%\joblistener.properties
set logback=%confdir%\logback.xml
set mainclass=org.jens.test.JobListenerApplication


%java% -cp %cp% ^
    -Dlogging.config=etc/logback.xml ^
    %mainclass%
exit /B %ERRORLEVEL%
