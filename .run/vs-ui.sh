#!/bin/bash

# Move into the project directory
cd ../visitscotland-dot-com-frontend || exit 1

# Update to the latest version of the main branch
git reset --hard
git checkout main
git pull

# Create or overwrite the .env file
echo "File created at: $(date +"%Y-%m-%d")" > .env
echo "BR_RESOURCE_API_ENDPOINT=http://localhost:8080/site/resourceapi" > .env
echo "BR_CMS_ORIGIN_LOCATION=*" >> .env
echo "BR_NUXT_APP_DEBUG=false=*" >> .env


# Default: do not rebuild
rebuild=false

# Parse arguments
for arg in "$@"; do
  case $arg in
    -r|--rebuild)
      rebuild=true
      ;;
  esac
done

# Run yarn install only if rebuild flag is set
if [ "$rebuild" = true ]; then
  echo "Running yarn install..."
  yarn install
else
  echo "Skipping yarn install."
fi

# Start dev server
yarn dev
