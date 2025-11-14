@REM @echo off

cd ../business-support-hub-front-end

# Update to the latest version of the main branch
git reset --hard
git checkout main
git pull

@REM set rebuild = false;
@REM
@REM for arg in "$@"; do
@REM   case $arg in
@REM     -r|--rebuild)
@REM       set rebuild=true
@REM       ;;
@REM   esac
@REM done
@REM
@REM if $rebuild; then
@REM   echo "Running yarn install..."
@REM   yarn install
@REM else
@REM   echo "Skipping yarn install."
@REM fi

@REM yarn install

yarn dev
