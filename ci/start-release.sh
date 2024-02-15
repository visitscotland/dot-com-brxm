#!/usr/bin/bash

#Shelve current workspace
branch=$(git branch --show-current)
git stash > /dev/null

git checkout main
git pull
git checkout develop
git pull
mvn gitflow:release-start --batch-mode
mvn versions:use-releases scm:checkin -Dmessage="Updated snapshot dependencies to release versions" -DpushChanges=false

#Recover the workspace to the state previous to run the command
git checkout "$branch"
git stash apply > /dev/null
