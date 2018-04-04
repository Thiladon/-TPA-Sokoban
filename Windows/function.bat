:startOfFile
@echo off
setlocal EnableDelayedExpansion

call:displayMessage "[Q] Quit cmd" "test"

pause

goto startOfFile

:displayMessage
cls

set i=-1
for %%d in (%*) do (
	set /a i=i+1
	set vector[!i!]=%%d
)

set maxItem=%i%

set /a row=6+2*5
set /a col=9+22*4
set __tempMsg__=

for /l %%y in (0, 1, %row%) do (
	set test=0
	set /a isEven=!test!*2
	echo %isEven%

	if %%y EQU 0 (
		set __tempMsg__= 
		for /l %%x in (2,1, %col%) do (
			set __tempMsg__=!__tempMsg__!$
		)
	) else (
		set __tempMsg__=$$
		for /l %%x in (4,1, %col%) do (
			set __tempMsg__=!__tempMsg__! 
		)
		set __tempMsg__=!__tempMsg__!$
	)
	echo !__tempMsg__!
	set __tempMsg__=
)

exit /b 0

$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$