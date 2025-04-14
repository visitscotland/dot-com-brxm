:: ==============================================================================
:: Script:        start-release.bat
:: Description:   Wrapper script to initiate the Maven Gitflow release process.
::                Delegates the actual logic to `release-process.bat` by passing
::                the 'start' argument.
::
:: Usage:         start-release.bat
::   - Internally calls: release-process.bat start
::   - Prepares 'main' and 'develop' branches
::   - Starts a Maven Gitflow release
::   - Updates snapshot dependencies to release versions
::   - Returns the user to their original working branch
::
:: Features:
::   - Lightweight, user-friendly entry point
::   - Supports robust release automation via `release-process.bat`
::   - Ensures exit code is preserved for CI/CD integrations or scripting
::
:: Requirements:
::   - This script must reside in the same directory as release-process.bat
::   - Git and Maven must be installed and accessible via PATH
::
:: Author:        Matthew Syrigos
:: Created:       April 2025
:: Last Updated:  April 2025
:: ==============================================================================
@echo off
setlocal

REM - call the main release script with the 'start' argument
REM - "%~dp0release-process.bat" ensures it calls release-process.bat from the same directory as start-release.bat, no matter where it's run from
REM - it uses call to properly wait for the child script to finish and preserve the exit code
call "%~dp0release-process.bat" start

REM exit /b %ERRORLEVEL% propagates the result for CI/CD or command-line context.
exit /b %ERRORLEVEL%