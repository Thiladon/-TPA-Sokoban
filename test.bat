@echo off
git status | FIND /v /c "" > %TMP%\git-status
SET /P _COUNT= < %TMP%\git-status

rm %TMP%\git-status

echo %_COUNT%
pause