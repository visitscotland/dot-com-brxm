:: ==============================================================================
:: Script:        finish-release.bat
:: Description:   Wrapper script to finalize a Maven Gitflow release process.
::                Delegates the core release completion logic to
::                `release-process.bat` by passing the 'finish' argument.
::
:: Usage:         finish-release.bat
::   - Internally calls: release-process.bat finish
::   - Finds the active release/* branch
::   - Executes `mvn gitflow:release-finish`
::   - Restores original working branch
::   - Applies any stashed changes
::
:: Features:
::   - Simplified entry point for finalizing releases
::   - Supports robust and safe release automation via `release-process.bat`
::   - Preserves exit codes for integration with automation pipelines
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

REM Call the main release script with the 'finish' argument
call "%~dp0release-process.bat" finish

exit /b %ERRORLEVEL%