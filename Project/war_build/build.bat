rmdir /Q /S .\META-INF
rmdir /Q /S .\libs
rmdir /Q /S .\jars

mkdir .\META-INF
mkdir .\libs
mkdir .\jars

pushd c:\%HOMEPATH%\.m2
for /r %%x in (*.jar) do copy "%%x" E:\OBSTextEditor\Project\war_build\jars\
popd

copy ..\META-INF\MANIFEST.MF .\META-INF\
copy ..\out\artifacts\Project_jar\Project.jar .
copy ..\src\main\resources\flatlaf-2.1.jar .\libs
copy .\jars\hamcrest*.jar .\libs\
copy .\jars\jackson*.jar .\libs\
copy .\jars\json*.jar .\libs\

rmdir /Q /S .\jars

jar cfm GUI.war META-INF\MANIFEST.MF

echo run gui.bat next