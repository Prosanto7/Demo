
@ECHO off
TITLE Search

:Start
SET currentDirectory=%CD%\
SET /P inputSrting=%currentDirectory%

FOR /F "tokens=1 delims= " %%a IN ("%inputSrting%") DO (
SET operationType=%%a
)

FOR /F "tokens=2 delims= " %%a IN ("%inputSrting%") DO (
SET fileName=%%a
)

IF %operationType% == FS (GOTO SearchByFileName)
IF %operationType% == SZ (GOTO SearchBySize)
IF %operationType% == TM (GOTO SearchByModificationTime)
IF %operationType% == C (GOTO ClearScreen)
IF %operationType% == D (GOTO ChangeDirectory)
IF %operationType% == HELP (GOTO HelpSegment) ELSE (ECHO This is Not a Valid Command 
GOTO Start)

:SearchByFileName 
DIR *%FileName%* /S /B
GOTO Start

:SearchBySize

FOR /F "tokens=3 delims= " %%a IN ("%inputSrting%") DO (
SET fileSize=%%a
)

FOR /R "%CD%" %%I IN (*%fileName%*) DO (
IF %%~zI LSS %fileSize% ( ECHO %%~fI Size : %%~zI Bytes )
)
GOTO Start

:SearchByModificationTime

FOR /F "tokens=3 delims= " %%a IN ("%inputSrting%") DO (
SET fileModificationTime=%%a
)

FORFILES /S /M *%fileName%* /D -%fileModificationTime% /C "CMD /C ECHO @path @fdate @ftime"
GOTO Start 


:ChangeDirectory
CD %fileName%
GOTO Start

:ClearScreen
CLS
GOTO Start

:HelpSegment
ECHO FS    Simple File Search by file name only.
ECHO       Syntax : FS FileName
ECHO SZ    Search Files Shorter than specific bytes.
ECHO       Syntax : SZ FileName Size
ECHO TM    Search Files Modified before a spcific period of time(days/date).
ECHO       Syntax : TM FileName Day/Date
ECHO HELP  Provides Help information.
ECHO C     Clears the screen. 
ECHO D     Changes Directory.
ECHO       Syntax : D Location 
GOTO Start

PAUSE