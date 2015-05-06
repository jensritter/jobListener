@echo off
nssm.exe install joblistener c:\temp\bin\joblistener.cmd
nssm.exe set joblistener AppDirectory c:\temp\bin\

nssm.exe set joblistener AppStderr c:\temp\bin\logs\stderr.txt
nssm.exe set joblistener AppStdout C:\temp\bin\logs\stdout.txt

nssm.exe set joblistener AppStdoutCreationDisposition 2
nssm.exe set joblistener AppStderrCreationDisposition 2