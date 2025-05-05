:: ==============================================================================
:: Script:        release-process.bat
:: Description:   Unified batch script to automate the Maven Gitflow release process.
::                Supports both the 'start' and 'finish' phases, handling Git branching,
::                Maven commands, dependency updates, and workspace preservation.
::
:: Usage:         release-process.bat [start | finish]
::   start        - Initiates a Maven Gitflow release:
::                   - Switches to 'develop' and 'main' branches
::                   - Pulls latest changes
::                   - Starts a new release via Maven
::                   - Updates snapshot dependencies to their release versions
::                   - Returns to the original working branch
::
::   finish       - Completes an active Maven Gitflow release:
::                   - Detects the existing release/* branch
::                   - Completes the release via Maven
::                   - Returns to the original working branch
::
:: Features:
::   - Automatically stashes and reapplies local changes
::   - Uses a temporary self-copy to avoid mutation during stash
::   - Displays Maven-style color-coded messages ([INFO], [WARN], [ERROR])
::   - Cleans up the temporary script copy after execution
::
:: Requirements:
::   - Git must be installed and available in PATH
::   - Maven must be installed and available in PATH
::   - Project must be configured for Gitflow (via Maven plugin)
::
:: Author:        Matthew Syrigos
:: Created:       April 2025
:: Last Updated:  April 2025
:: ==============================================================================
@echo off
setlocal enabledelayedexpansion

REM Call the colour codes function for a more mvn-like display of messages
call :setupColours

REM Reassign original_script if passed from parent
REM command %~f0 expands to the full path of the current script file
if not defined original_script (
    set "original_script=%~2"
)

REM === Self-reinvocation safeguard to avoid mutation during stash ===
REM Issue: If the batch script stashes itself mid-execution (i.e., it has local changes that are included in git stash),
REM then those changes may get unloaded or reverted mid-run, potentially altering script behavior or logic.
REM Solution: Ensure that the script always runs with its original content, even if it stashes or applies stashes that
REM affect itself by running a copy of the script (clean clone) in a temporary location.
set "this_script=%~f0"
set "temp_copy=%TEMP%\release_process_copy_%RANDOM%.bat"

if "%~1"=="" (
    echo [%ERROR%ERROR%RESET%] Missing required argument: start or finish
    echo [%INFO%INFO%RESET%] Usage: release-process.bat [start ^| finish]
    exit /b 1
)

if /i not "%~1"=="start" if /i not "%~1"=="finish" (
    echo [%ERROR%ERROR%RESET%] Invalid argument: %~1
    echo [%INFO%INFO%RESET%] Usage: release-process.bat [start ^| finish]
    exit /b 1
)
if not "%__SELF_COPY__%"=="1" (
    echo [%INFO%INFO%RESET%] Running from original script. Creating a temporary self-copy...
    copy "%this_script%" "%temp_copy%" >nul
    if errorlevel 1 (
        echo [%ERROR%ERROR%RESET%] Failed to create a temp copy of the script.
        exit /b 1
    )
    echo [%INFO%INFO%RESET%] Self-copy script created and will be called/executed: %SUCCESS%%temp_copy%%RESET%
    set "__SELF_COPY__=1"
    REM Suppress any leftover output (“The batch file cannot be found”) after the self-deleting script finishes
    call "%temp_copy%" %1 "%this_script%" 2>nul
    REM Reset flag so that the original script can delete the copied one from the temp folder
    set "__SELF_COPY__="
    set "exitCode=!ERRORLEVEL!"
    call :cleanupTempAndExit "%temp_copy%" "!exitCode!"
    exit /b !ERRORLEVEL!
)

REM ==== Main Logic ====
REM Save the current branch
REM processes text line by line with for /f
REM "delims=" prevents splitting the line into tokens — it treats the whole line as a single string
REM %%b the loop variable that stores each line
for /f "delims=" %%b in ('git rev-parse --abbrev-ref HEAD') do set "branch=%%b"
set hasStashedChanges=0

REM Check for local changes
for /f %%a in ('git status --porcelain') do (
    set hasStashedChanges=1
    goto :stashChanges
)
goto :noChanges

:stashChanges
REM Issue: standard %branch% expansion, works only if branch var was already set before script parsing — not inside a for loop.
REM Because %branch% was set inside a loop using setlocal enabledelayedexpansion, it won't expand correctly here as %branch%
REM Solution: In batch scripts, variables assigned inside a for loop do not update %var% immediately. We must use !var! for delayed expansion.
echo [%INFO%INFO%RESET%] You were working on branch !branch!
echo [%INFO%INFO%RESET%] Local changes detected. Stashing your work...
git stash
if errorlevel 1 (
    echo [%ERROR%ERROR%RESET%] Failed to stash changes
    exit /b 1
)
goto :continueScript

:noChanges
echo [%INFO%INFO%RESET%] No local changes to stash.

:continueScript
REM both the start and finish phases depend on the integrity and freshness of main and develop
echo [%INFO%INFO%RESET%] Fetching latest updates from origin...
git fetch origin || exit /b 1

echo [%INFO%INFO%RESET%] Syncing local branches...
echo [%INFO%INFO%RESET%] checking out main branch and pulling the latest updates (if any)
git checkout main || exit /b 1
git pull origin main || exit /b 1

echo [%INFO%INFO%RESET%] checking out develop branch and pulling the latest updates (if any)
git checkout develop || exit /b 1
git pull origin develop || exit /b 1

if /i "%1"=="start" goto :startRelease
if /i "%1"=="finish" goto :finishRelease

:startRelease
echo [%INFO%INFO%RESET%] %SUCCESS%===%RESET% Starting release %SUCCESS%===%RESET%
call mvn gitflow:release-start || exit /b 1

for /f "delims=" %%r in ('git rev-parse --abbrev-ref HEAD') do set "releaseBranch=%%r"
REM using delayed expansion version of the branch var, since it was created within the script
echo [%INFO%INFO%RESET%] Release branch created: !releaseBranch!

call mvn versions:use-releases scm:checkin -Dmessage="Updated snapshot dependencies to release versions" -DpushChanges=false || exit /b 1

echo [%INFO%INFO%RESET%] Returning to original branch: !branch!
git checkout !branch! || exit /b 1

echo.
echo [%INFO%INFO%RESET%] %SUCCESS%============================================================%RESET%
echo [%INFO%INFO%RESET%]      Release branch successfully created: %SUCCESS%!releaseBranch!%RESET%
echo [%INFO%INFO%RESET%] You are now back on your original branch: %SUCCESS%!branch!%RESET%
echo [%INFO%INFO%RESET%] %SUCCESS%============================================================%RESET%
echo.

goto :applyStash

:finishRelease
echo [%INFO%INFO%RESET%] %SUCCESS%===%RESET% Finishing release %SUCCESS%===%RESET%

set "releaseBranch="

for /f "delims=" %%r in ('git branch --list release/*') do (
    set "releaseBranch=%%r"
)

if not defined releaseBranch (
    echo [%ERROR%ERROR%RESET%] No release/* branch found
     exit /b 1
)

if "!releaseBranch:~0,2!"=="* " set "releaseBranch=!releaseBranch:~2!"

git checkout !releaseBranch! || exit /b 1
git pull origin !releaseBranch! || exit /b 1

call mvn gitflow:release-finish -DskipTestProject=true || exit /b 1

REM the release branch should be deleted by now, as part of the mvn gitflow:release-finish process
git show-ref --verify --quiet "refs/heads/!branch!"
REM ... which means that the above command should fail, and the errorlevel should be raised to 1
if errorlevel 1 (
    echo [%INFO%INFO%RESET%] Original branch %WARN%!branch!%RESET% was deleted as part of the process.
    for /f "delims=" %%c in ('git rev-parse --abbrev-ref HEAD') do set "branch=%%c"
    echo [%INFO%INFO%RESET%] You are on branch: %SUCCESS%!branch!%RESET%
) else (
    echo [%INFO%INFO%RESET%] Returning to original branch: %SUCCESS%!branch!%RESET%
    git checkout !branch! || exit /b 1
)

:applyStash
if "%hasStashedChanges%"=="1" (
    echo [%INFO%INFO%RESET%] Re-applying stashed work...
    git stash apply || exit /b 1
) else (
    echo [%INFO%INFO%RESET%] No stashed work to apply.
)

:cleanupTempAndExit
set "tempFile=%~1"
set "exitCode=%~2"
REM If no exit code was passed in, use the current ERRORLEVEL
if "%exitCode%"=="" set "exitCode=!ERRORLEVEL!"

REM Only the original script should attempt cleanup
if not "%__SELF_COPY__%"=="1" (
    echo [%INFO%INFO%RESET%] Checking for temp script
    if exist "!tempFile!" (
        echo [%INFO%INFO%RESET%] Deleting temp script !WARN!!tempFile!!RESET! after a short delay...
        ping -n 3 127.0.0.1 >nul
        del "!tempFile!" >nul 2>&1
        echo [%INFO%INFO%RESET%] Checking if file exists after deletion...

        REM Issue: When using delayed expansion, the variable !tempFile! must already have a value at parse time,
        REM or the if exist expression may become invalid — particularly if the variable contains spaces or quotes,
        REM or if it's briefly empty or unset due to scope issues.
        REM Solution: Call separate logic to ensure delayed expansion is parsed correctly at runtime.
        REM call :checkTempFileDeletion defers the evaluation of !tempFile! until runtime and this
        REM avoids weird parse-time failures due to how cmd.exe processes blocks before expansion.
        call :checkTempFileDeletion
    )
)
exit /b %exitCode%

:checkTempFileDeletion
if exist "!tempFile!" (
    echo [!WARN!WARN%RESET%] Unable to delete temp file (please delete manually if needed)
) else (
    echo [!INFO!INFO%RESET%] Temp file !SUCCESS!successfully%RESET% deleted by original script
)
exit /b

:setupColours
:: ANSI Escape Sequence Setup (Windows 10+)
for /f %%a in ('echo prompt $E ^| cmd') do set "ESC=%%a"

:: Color Codes
set "RESET=%ESC%[0m"
set "INFO=%ESC%[94m"   :: Bright Blue (like mvn [INFO])
set "WARN=%ESC%[93m"   :: Bright Yellow (like mvn [WARNING])
set "ERROR=%ESC%[91m"  :: Bright Red (like mvn [ERROR])
set "SUCCESS=%ESC%[92m":: Bright Green (for success messages)
exit /b