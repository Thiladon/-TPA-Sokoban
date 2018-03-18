:gui

set isStartingAfterCompile=0
set gotoManager=0

@echo off
cls
echo  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$                                                                           $
echo $$                          /*/  Pick a choice  \*\                          $
echo $$                                                                           $
echo $$   [C] Compile           [CS] Compile and start   [S] Start                $
echo $$                                                                           $
echo $$   [G]  Git commit Files [M] Modify ReadMe.md     [Q] Quit cmd             $
echo $$                                                                           $
echo $$   [GS] Git status                                                         $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo.
echo.
set /p clientKey=

if /i "%clientKey%"=="C" (
	GOTO compile
) else if /i "%clientKey%"=="Compile" (
	GOTO compile
) else if /i "%clientKey%"=="CS" (
	GOTO compileandstart
) else if /i "%clientKey%"=="Compile&Start" (
	GOTO compileandstart
) else if /i "%clientKey%"=="S" (
	GOTO launch
) else if /i "%clientKey%"=="Start" (
	GOTO launch
) else if /i "%clientKey%"=="G" (
	GOTO git
) else if /i "%clientKey%"=="Git" (
	GOTO git
) else if /i "%clientKey%"=="GS" (
	GOTO gitStatus
) else if /i "%clientKey%"=="Status" (
	GOTO gitStatus
) else if /i "%clientKey%"=="M" (
	GOTO modify
) else if /i "%clientKey%"=="Modify" (
	GOTO modify
) else if /i "%clientKey%"=="Q" (
	GOTO quit
) else if /i "%clientKey%"=="Quit" (
	GOTO quit
) else (
	GOTO gui
)

:compile
cls
echo  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$                               Compiling ...                               $
echo $$                                                                           $
echo $$                           See content below ...                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo.
echo.

javac sample/*.java

pause
GOTO gui

:compileandstart
cls
echo  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$                          Compiling then starting                          $
echo $$                                                                           $
echo $$                             See content below                             $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo.
echo.

javac sample/*.java

pause

:launch
cls
echo  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$                               Executing ...                               $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo.
echo.

java sample.Main

pause
GOTO gui

:git
cls
echo  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                [Git Batch]                                $
echo $$                                                                           $
echo $$                          Write message to commit                          $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo.
echo.

set /p gitMessage=

git add *
git commit -m "%gitMessage%"
git push --all -f

GOTO gui

:modify

call vim "README.md"

GOTO git

:gitStatus
cls
echo  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$                                                                           $
echo $$                          /*/  Pick a choice  \*\                          $
echo $$                                                                           $
echo $$   [C] Compile           [CS] Compile and start   [S] Start                $
echo $$                                                                           $
echo $$   [G]  Git commit Files [M] Modify ReadMe.md     [Q] Quit cmd             $
echo $$                                                                           $
echo $$   [GS] Git status                                                         $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo.
echo.
git status
pause

GOTO gui

:quit
exit