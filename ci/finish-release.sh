#!/usr/bin/bash

#Shelve current workspace
branch=$(git branch --list 'release/*' | head -1)
git stash > /dev/null

mvn gitflow:release-finish -DskipTestProject=true

#Recover the workspace to the state previous to run the command
git checkout "$branch"
git stash apply > /dev/null
