#!/bin/bash

COLOUR="\033[35m" # Set a colour for highlighting the output
LINE_END="\033[0m\n" # End of line and colour reset

REPO_NAME="business-support-hub-front-end"
RESOURCE_API_ENDPOINT="http://localhost:8080/site/bsh-api/resourceapi"

# Default: rebuild
rebuild=true

# Update to the latest version of the main branch
if [ -d "../${REPO_NAME}" ]; then
  printf  "${COLOUR}Updating the local repository... ${LINE_END}"
  cd ../${REPO_NAME} || exit 1
  git reset --hard
  git checkout main
  output=$(git pull)

  if echo "$output" | grep -q "Already up to date."; then
    rebuild=false
  fi
else
  printf  "${COLOUR}The repository does not exist. cloning the repository...${LINE_END}"
  cd .. || exit 1
  git clone https://github.com/visitscotland/${REPO_NAME}.git
fi

#Chekck if the CMS is running and healthy
status=$(curl -s -o /dev/null -w "%{http_code}" ${RESOURCE_API_ENDPOINT})

if [ "$status" != "200" ]; then
  if [ "$status" != "000" ]; then
    statusMessage=" (HTTP Code: ${status})"
  fi
  printf "${COLOUR}The CMS is not available${statusMessage}. Aborting launch ${LINE_END}"
  exit 1
fi

printf "${COLOUR}Creating .env file...${LINE_END}"
# Create or overwrite the .env file
echo "# File created at: $(date +"%Y-%m-%d")" > .env
echo "BR_RESOURCE_API_ENDPOINT=${RESOURCE_API_ENDPOINT}" >> .env
echo "BR_CMS_ORIGIN_LOCATION=*" >> .env
echo "BR_NUXT_APP_DEBUG=false" >> .env

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
  printf "${COLOUR}Running yarn install...${LINE_END}"
  yarn install
else
  printf "${COLOUR}Skipping yarn install.\e[0m"
fi

# Start dev server
yarn dev
