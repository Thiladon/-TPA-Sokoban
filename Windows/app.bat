:restart
@echo off

set __path__=%~dp0
set __path__=%__path__:~0,-21%
set __SVN__=%__path__%projet-tpa-godement-marchand-montaine-brillet-menard\
set __GIT_FORGE__=%__path__%-TPA-Sokoban-forge\
set __GIT__=%__path__%-TPA-Sokoban\

set isStartingAfterCompile=False

:gui

cd /d "%__GIT__%/Windows"

cls
echo  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$                                                                           $
echo $$                          /*/  Pick a choice  \*\                          $
echo $$                                                                           $
echo $$   [SR] Status of repo   [CS] Compile and start    [C] Compile             $
echo $$                                                                           $
echo $$    [G] Git commit Files  [S] Start                [Q] Quit cmd            $
echo $$                                                                           $
echo $$    [M] Modify ReadMe.md                                                   $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo.
echo.
set /p clientKey=

if /i "%clientKey%"=="C" (
	set isStartingAfterCompile=False
	GOTO compile
) else if /i "%clientKey%"=="Compile" (
	set isStartingAfterCompile=False
	GOTO compile
) else if /i "%clientKey%"=="CS" (
	set isStartingAfterCompile=True
	GOTO compileandstart
) else if /i "%clientKey%"=="Compile&Start" (
	set isStartingAfterCompile=True
	GOTO compileandstart
) else if /i "%clientKey%"=="S" (
	GOTO launch
) else if /i "%clientKey%"=="Start" (
	GOTO launch
) else if /i "%clientKey%"=="G" (
	GOTO git
) else if /i "%clientKey%"=="Git" (
	GOTO git
) else if /i "%clientKey%"=="SR" (
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

GOTO recompile

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

:recompile

cd /d "%__GIT__%/Windows/"
javac -d class src/*.java

pause

echo.
echo.
echo Re-compile ?: 
echo.
set /p clientKey=
echo.
echo.

if /i "%clientKey%"=="Yes" (
	if /i "%isStartingAfterCompile%"=="False" (
		GOTO compile
	) else (
		GOTO compileandstart
	)
) else if /i "%clientKey%"=="Y" (
	if /i "%isStartingAfterCompile%"=="False" (
		GOTO compile
	) else (
		GOTO compileandstart
	)
) else if /i "%clientKey%"=="No" (
	if /i "%isStartingAfterCompile%"=="False" (
		GOTO gui
	) else (
		GOTO launch
	)
) else if /i "%clientKey%"=="N" (
	if /i "%isStartingAfterCompile%"=="False" (
		GOTO gui
	) else (
		GOTO launch
	)
) else (
	echo Not a valid Key
	GOTO recompile
)

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

cd /d "%__GIT__%/Windows/class"
java src.Main
cd ..

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

cd /d "%__GIT__%" && git add * && git commit -m "%gitMessage%" && git push --all -f && cd Windows

GOTO gui

:modify

cd /d "%__GIT__%"
call vim "README.md"

GOTO git

:gitStatus
cls
echo  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$                                                                           $
echo $$                          /*/  Pick a choice  \*\                          $
echo $$                                                                           $
echo $$   [SR] Status of repo   [CS] Compile and start    [C] Compile             $
echo $$                                                                           $
echo $$    [G] Git commit Files  [S] Start                [Q] Quit cmd            $
echo $$                                                                           $
echo $$    [M] Modify ReadMe.md                                                   $
echo $$                                                                           $
echo $$                                                                           $
echo $$                                                                           $
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
echo.
echo.

cd "%__GIT_FORGE__%"
echo git forge status:
git status
echo.
echo.

cd "%__GIT__%"
echo git status:
git status
echo.
echo.


rem cd "%__SVN__%"
rem echo svn update:
rem svn update
rem echo.
rem echo.

cd /d "%__GIT__%/Windows"

pause

GOTO gui

:quit
exit