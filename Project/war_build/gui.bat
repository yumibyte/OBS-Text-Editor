 @echo off
 setLocal EnableDelayedExpansion
 set CLASSPATH="
 for /R ./libs %%a in (*.jar) do (
   set CLASSPATH=!CLASSPATH!;%%a
 )
 set CLASSPATH=!CLASSPATH!"
REM echo !CLASSPATH!

java -classpath ;Project.jar;%CLASSPATH% GUI