#!/bin/bash

# ==== TO-DO ====
# gp: remove echo "$VS_CONTAINER_BASE_PORT" > env_port.txt AND echo "$VS_HOST_IP_ADDRESS" > env_host.txt from report section - these are now exported to be avaible to the LH script
# gp: create test routine for container/tomcat startup
# ====/TO-DO ====
# ==== DONE ====
# gp: split into functions - done
# gp: activate clean-up routine - done
# gp: update adjustatable variables to set only if they're not set already, that way the Dev can override in the Jenkinsfile - done
# gp: rename BASE_PORT to MIN_PORT - done
# gp: update port find to set VS_CONTAINER_BASE_PORT between MIN_PORT and MAX_PORT - done
#      - then check the + 100s right to the limit - done
# gp: create an array of required ports - done
#      - e.g. "VS_CONTAINER_BRXM_PORT VS_CONTAINER_SSR_PORT VS_CONTAINER_SSH_PORT"
#      - then do a FOR MAP_PORT in VS_CONTAINER_REQUIRED_PORTS and +100 knowing that the're available (from above)
# gp: if an existent container is found grab its base port - done
#     - re-use it for any new container
# gp: create routine to re-use existing container if it's there - done
#     - start it if stoppped - redeploy artifact if it's running
# gp: create notification routine using "VS_COMMIT_AUTHOR" - done
# gp: don't start tomcat with container - done
# gp: additional check to see if mySQL is required - create a CMD without mysql - done
# ====/DONE ====

# ==== SETUP ====
# ==== ADJUSTABLE VARIABLES ====
#  == VS Variables ==
if [ -z "$VS_DEBUG" ]; then VS_DEBUG=FALSE; fi
if [ -z "$VS_DOCKER_IMAGE_NAME" ]; then VS_DOCKER_IMAGE_NAME=vs-brxm; fi
if [ -z "$VS_DOCKERFILE_PATH" ]; then VS_DOCKERFILE_PATH=/home/jenkins/vs-dockerfile; fi
if [ -z "$VS_DOCKERFILE_NAME" ]; then VS_DOCKERFILE_NAME=vs-brxm; fi
if [ -z "$VS_DOCKERFILE_LOCN" ]; then VS_DOCKERFILE_LOCN=$VS_DOCKERFILE_PATH/$VS_DOCKERFILE_NAME; fi
#  ==== Hosting Environment Variables ====
if [ -z "$VS_PROXY_SERVER_SCHEME" ]; then VS_PROXY_SERVER_SCHEME=https; fi
if [ -z "$VS_PROXY_SERVER_FQDN" ]; then VS_PROXY_SERVER_FQDN=feature.visitscotland.com; fi
#  ==== Mail Variables ====
if [ -z "$VS_MAIL_DOMAIN" ]; then VS_MAIL_DOMAIN=visitscotland.net; fi
if [ -z "$VS_MAIL_HOST" ]; then VS_MAIL_HOST=10.1.1.152; fi
if [ -z "$VS_MAIL_NOTIFY_BUILD" ]; then VS_MAIL_NOTIFY_BUILD="TRUE"; fi
if [ -z "$VS_MAIL_NOTIFY_SITE" ]; then VS_MAIL_NOTIFY_SITE="TRUE"; fi
#  == brXM Instance Variables ==
if [ -z "$VS_CONTAINER_BASE_PORT_OVERRIDE" ]; then unset VS_CONTAINER_BASE_PORT_OVERRIDE; else echo "VS_CONTAINER_BASE_PORT_OVERRIDE was set to $VS_CONTAINER_BASE_PORT_OVERRIDE before $0 was called"; fi
if [ -z "$VS_BRXM_INSTANCE_HTTP_HOST" ]; then VS_BRXM_INSTANCE_HTTP_HOST=localhost; fi
if [ -z "$VS_BRXM_PERSISTENCE_METHOD" ]; then VS_BRXM_PERSISTENCE_METHOD=h2; fi
if [ -z "$VS_BRXM_TOMCAT_PORT" ]; then VS_BRXM_TOMCAT_PORT=8080; fi
if [ -z "$VS_CONTAINER_PORT_INCREMENT" ]; then VS_CONTAINER_PORT_INCREMENT=100; fi
if [ -z "$VS_CONTAINER_CONSOLE_FILE" ]; then VS_CONTAINER_CONSOLE_FILE="/tmp/console.out"; fi
if [ -z "$VS_CONTAINER_DYN_PORT_MAX" ]; then VS_CONTAINER_DYN_PORT_MAX=8999; fi
if [ -z "$VS_CONTAINER_INT_PORT_SSR" ]; then VS_CONTAINER_INT_PORT_SSR=8082; fi
if [ -z "$VS_CONTAINER_INT_PORT_SSH" ]; then VS_CONTAINER_INT_PORT_SSH=22; fi
if [ -z "$VS_CONTAINER_INT_PORT_TLN" ]; then VS_CONTAINER_INT_PORT_TLN=8081; fi
if [ -z "$VS_CONTAINER_PRESERVE" ]; then VS_CONTAINER_PRESERVE=TRUE; fi
if [ -z "$VS_CONTAINER_SSH_PASS_ROOT" ]; then VS_CONTAINER_SSH_PASS_ROOT=rootssh; fi
if [ -z "$VS_CONTAINER_SSH_PASS_HIPPO" ]; then VS_CONTAINER_SSH_PASS_HIPPO=hippossh; fi
if [ -z "$VS_CONTAINER_UPDATES_DIR" ]; then VS_CONTAINER_UPDATES_DIR="../files"; fi
#  ==== SSR Application Variables ====
if [ -z "$VS_FRONTEND_DIR" ]; then VS_FRONTEND_DIR=frontend; fi
if [ -z "$VS_SSR_PACKAGE_SOURCE" ]; then VS_SSR_PACKAGE_SOURCE="$VS_FRONTEND_DIR/ssr/server/ $VS_FRONTEND_DIR/dist/ssr/ $VS_FRONTEND_DIR/node_modules/ $VS_FRONTEND_DIR/build/"; fi
if [ -z "$VS_SSR_PACKAGE_TARGET" ]; then VS_SSR_PACKAGE_TARGET="./target"; fi
if [ -z "$VS_SSR_PACKAGE_NAME" ]; then VS_SSR_PACKAGE_NAME="vs-ssr-package.tar.gz"; fi
if [ -z "$VS_SSR_PROXY_ON" ]; then VS_SSR_PROXY_ON="TRUE"; fi
if [ -z "$VS_SSR_APP_PORT" ]; then VS_SSR_APP_PORT=8082; fi
#  ==== Other Variables ====
VS_JENKINS_LAST_ENV=jenkins-last-env
VS_VS_LAST_ENV=vs-last-env
VS_LAST_ENV_QUOTED_SUFFIX=.quoted
VS_LAST_ENV_GROOVY_SUFFIX=.groovy

# ====/ADJUSTABLE VARIABLES ====

# ==== PARSE COMMAND LINE ARGUMENTS ====
METHOD=$1
while [[ $# -gt 0 ]]; do
  argument="$1"
  THIS_VAR=`echo $argument | sed -e "s/=.*//g"`; #echo $THIS_VAR
  THIS_VAL=`echo $argument | sed -e "s/.*=//g" | sed -s "s/--.*//g"`; #echo $THIS_VAL
  if [ ! -z "$THIS_VAL" ]; then THIS_RESULT="$THIS_VAL"; elif [ ! "${2:0:2}" = "--" ]; then THIS_RESULT="$2"; else THIS_RESULT=""; fi
  if [ "$VS_DEBUG" == "TRUE" ]; then echo -en "\nread \"$THIS_VAR\" from command line"; fi
  case $THIS_VAR in
    --debug) if [ ! -z "$THIS_RESULT" ]; then VS_DEBUG=$THIS_RESULT; else VS_DEBUG=TRUE; fi;;
    --frontend-dir) if [ ! -z "$THIS_RESULT" ]; then VS_FRONTEND_DIR=$THIS_RESULT; fi;;
    --persistence) if [ ! -z "$THIS_RESULT" ]; then VS_BRXM_PERSISTENCE_METHOD=$THIS_RESULT; fi;;
    --persistence-method) if [ ! -z "$THIS_RESULT" ]; then VS_BRXM_PERSISTENCE_METHOD=$THIS_RESULT; fi;;
    --preserve-container) if [ ! -z "$THIS_RESULT" ]; then VS_CONTAINER_PRESERVE=$THIS_RESULT; else VS_CONTAINER_PRESERVE=TRUE; fi;;
    --repository-dir) if [ ! -z "$THIS_RESULT" ]; then VS_BRXM_REPOSITORY=$THIS_RESULT; fi;;
    --reuse-container) if [ ! -z "$THIS_RESULT" ]; then VS_CONTAINER_PRESERVE=$THIS_RESULT; else VS_CONTAINER_PRESERVE=TRUE; fi;;
    --single-function) if [ ! -z "$THIS_RESULT" ]; then VS_THIS_FUNCTION=$THIS_RESULT; fi;;
    --tidy-containers) if [ ! -z "$THIS_RESULT" ]; then VS_TIDY_CONTAINERS=$THIS_RESULT; else VS_TIDY_CONTAINERS=TRUE; fi;;
    --working-dir) if [ ! -z "$THIS_RESULT" ]; then VS_WORKING_DIR=$THIS_RESULT; fi;;
    *)
      if [ "$DEBUG" == "TRUE" ]; then echo -en " - not a valid argument - SKIPPING"; fi
    ;;
  esac
  shift
done
echo -en "\n"
# ====/PARSE COMMAND LINE ARGUMENTS ====
# ====/SETUP ====

# ==== FUNCTIONS ====
checkVariables() {
  if [ ! "$DEBUG" == "TRUE" ]; then clear; fi
  echo "==== Checking variables to ensure environment is set up ===="
  if [ ! "$LOGNAME" = "jenkins" ]; then
    echo "$VS_SCRIPTNAME was not called by the user Jenkins, please switch user"
    exit 3
  elif [ "$LOGNAME" = "jenkins" ] && [ ! -z "JENKINS_SERVER_COOKIE" ]; then
    echo "$VS_SCRIPTNAME appears to be running from a Jenkins job"
    echo " - exporting selected variables to ./$VS_JENKINS_LAST_ENV"
    printenv | egrep "JENKINS_(HOME|URL)|JOB_((BASE_)?NAME|(DISPLAY_)?URL)|VS_(DOCKER|BRC|COMMIT)" | tee $VS_JENKINS_LAST_ENV
  elif [ "$LOGNAME" = "jenkins" ] && [ -z "$JOB_NAME" ] && [ -e $VS_JENKINS_LAST_ENV ]; then
    echo "$VS_SCRIPTNAME was called from a Jenkins workspace but not by a Jenkins job"
    echo " - setting Jenkins environment variables from last run"
    source ./jenkins-last-env
  elif [ "$LOGNAME" = "jenkins" ] && [ -z "$JOB_NAME" ] && [ ! -d ./target ] && [ ! -z "$VS_WORKING_DIR" ]; then
    echo "$VS_SCRIPTNAME was not called from within Jenkins workspace"
    echo " - switching to $VS_WORKING_DIR"
    checkVariables
    cd $VS_WORKING_DIR
  elif [ -z "$JOB_NAME" ] && [ "$VS_WD_PARENT" = "workspace" ] && [ ! -z "$VS_WORKING_DIR" ] && [ ! -e $VS_JENKINS_LAST_ENV ] && [ ! -d ./target ]; then
    echo "$VS_SCRIPTNAME was called from "`pwd`" but this may not be a Jenkins workspace, please either:"
    echo " - switch to the workspace of a Jenkins job that has previously run this script"
    echo " - run a Jenkins job for this branch to populate $VS_JENKINS_LAST_ENV and create ./target"
    echo " - call this script with --working-dir=[workspace of a Jenkins job that has previously run this script]"
    exit 2
  else
    echo "$VS_SCRIPTNAME needs to run relative to a Jenkins workspace, please either:"
    echo " - switch to the workspace of a Jenkins job that has previously run this script"
    echo " - call this script with --working-dir=[workspace of a Jenkins job that has previously run this script]"
    exit 1
  fi
}

defaultSettings() {
  # unset variables
  unset VS_CONTAINER_LIST
  unset VS_PARENT_JOB_NAME
  unset RESERVED_PORT_LIST
  unset VS_CONTAINER_PORT_MAPPINGS
  # set container name from branch name - removing / characters
  if [ -z "$VS_CONTAINER_NAME" ]; then VS_CONTAINER_NAME=`echo $JOB_NAME | sed -e "s/\/.*//g"`"_"`basename $BRANCH_NAME`; fi
  if [ -z "$VS_BRANCH_NAME" ]; then
    if [ ! -z "$CHANGE_BRANCH" ]; then
      # this job is running against a branch in pull request status, using CHANGE_BRANCH variable
      VS_BRANCH_NAME=$CHANGE_BRANCH
    elif [ ! -z "$BRANCH_NAME" ]; then
      # this branch is a branch, using BRANCH_NAME variable
      VS_BRANCH_NAME=$BRANCH_NAME
    else
      VS_BRANCH_NAME="branch not found"
    fi
  fi
  if [ -z "$NODE_NAME" ]; then VS_THIS_SERVER=$HOSTNAME; else VS_THIS_SERVER=$NODE_NAME; fi
  if [ "$VS_CONTAINER_PRESERVE" == "TRUE" ]; then
    VS_BRXM_REPOSITORY="repository"
    VS_HIPPO_REPOSITORY_PERSIST="TRUE"
  fi
  VS_COMMIT_AUTHOR=`git show -s --pretty="%ae" ${GIT_COMMIT}`
  VS_DATESTAMP=`date +%Y%m%d`
  VS_HOST_IP_ADDRESS=`/usr/sbin/ip ad sh  | egrep "global noprefixroute" | awk '{print $2}' | sed -e "s/\/.*$//"`
  VS_PARENT_JOB_NAME=`echo $JOB_NAME | sed -e "s/\/.*//g"`
  VS_SCRIPTNAME=`basename $0`
  if [ "$VS_SSR_PROXY_ON" == "TRUE" ]; then
    VS_PROXY_QS_SSR="&vs_ssr_proxy=on"
  else
    VS_PROXY_QS_SSR="&vs_ssr_proxy=off"
  fi
  # mail settings - build
  if [ -z "$VS_MAIL_NOTIFY_BUILD_TO" ]; then VS_MAIL_NOTIFY_BUILD_TO=$VS_COMMIT_AUTHOR; fi
  VS_MAIL_NOTIFY_BUILD_SENDER="$VS_PARENT_JOB_NAME"
  VS_MAIL_NOTIFY_BUILD_MESSAGE=/tmp/$VS_CONTAINER_NAME.msg.notify.build
  VS_MAIL_NOTIFY_BUILD_MESSAGE_EXTRA=$VS_MAIL_NOTIFY_BUILD_MESSAGE.extra
  VS_MAIL_NOTIFY_BUILD_SUBJECT="environment was built for $GIT_BRANCH in $VS_PARENT_JOB_NAME"
  VS_MAIL_NOTIFY_BUILD_SENDER="$VS_PARENT_JOB_NAME@$VS_MAIL_DOMAIN"
  # mail settings - site
  if [ -z "$VS_MAIL_MESSAGE_NOTIFY_SITE_TO" ]; then VS_MAIL_MESSAGE_NOTIFY_SITE_TO=$VS_COMMIT_AUTHOR; fi
  VS_MAIL_NOTIFY_SITE_SENDER="$VS_PARENT_JOB_NAME"
  VS_MAIL_NOTIFY_SITE_MESSAGE=/tmp/$VS_CONTAINER_NAME.msg.notify.site
  VS_MAIL_NOTIFY_SITE_SUBJECT="$VS_PARENT_JOB_NAME environment was built for $GIT_BRANCH"
  # mail settings - executable
  VS_WD_PARENT="$(basename `echo ${PWD%/*}`)"
  if [ ! -z $VS_MAILER_BIN ]; then
    if [ ! -z "`which mailx`" ]; then
      VS_MAILER_BIN="`which mailx`"
    elif [ ! -z "`which mail`" ]; then
      VS_MAILER_BIN="`which mail`"
    else
      VS_MAILER_BIN="`/bin/false`"
    fi
  fi
}

reportSettings() {
  clear
  echo ""
  echo "========================================================================"
  echo "== RUNNING JENKINS SHELL COMMANDS on $VS_THIS_SERVER"
  echo "== as user " $USER
  echo "== from " `basename $0`
  echo "========================================================================"
  echo ""
  if [ "$VS_DEBUG" = "TRUE" ]; then echo "==== printenv ===="; printenv; echo "====/printenv ===="; echo ""; fi
  #if [ "$VS_DEBUG" = "TRUE" ]; then echo "==== set ===="; set; echo "====/set ====";  echo ""; fi
  echo "==== selected Jenkins environment variables ===="
  set | egrep "^(BRANCH|BUILD|CHANGE|GIT|JENKINS|JOB|RUN|WORKSPACE)"
  echo "====/selected Jenkins environment variables ===="
  echo ""
  echo "==== selected VS environment variables ===="
  set | egrep "^(VS_)"
  echo "====/selected VS environment variables ===="
  echo ""
}

checkContainers() {
  # check to see if a container called $VS_CONTAINER_NAME exists, if so set $CONTAINER_ID to Docker's CONTAINER ID
  echo "checking for containers with name $VS_CONTAINER_NAME"
  CONTAINER_ID=`docker ps -aq --filter "name=$VS_CONTAINER_NAME"`
  if [ ! -z "$CONTAINER_ID" ]; then
    echo " - container found, ID:$CONTAINER_ID, with name $VS_CONTAINER_NAME"
    echo " - checking status of container $CONTAINER_ID"
    CONTAINER_STATUS=`docker inspect --format "{{.State.Status}}" $CONTAINER_ID`
    echo " - $CONTAINER_STATUS container found with ID:$CONTAINER_ID and name $VS_CONTAINER_NAME"
    echo " - checking for base port of container $CONTAINER_ID"
    CONTAINER_PORT=`docker inspect --format='{{(index (index .NetworkSettings.Ports "8080/tcp") 0).HostPort}}' $CONTAINER_ID`
    if [ ! -z "$CONTAINER_PORT" ]; then
      VS_CONTAINER_BASE_PORT_OVERRIDE=$CONTAINER_PORT
      echo " - base port of $CONTAINER_PORT found for container $CONTAINER_ID - setting VS_CONTAINER_BASE_PORT_OVERRIDE"
      echo "  - checking other ports (for info)"
      docker inspect --format='{{range $p, $conf := .NetworkSettings.Ports}} {{$p}} -> {{(index $conf 0).HostPort}} {{end}}' $CONTAINER_ID
    else
      echo " - no base port was found for container $CONTAINER_ID"
    fi
  else
    echo " - no container found with name $VS_CONTAINER_NAME"
  fi
  echo ""
}

stopContainers() {
  echo "stopping containers with ID $CONTAINER_ID"
  for CONTAINER in $CONTAINER_ID; do
    echo " - stopping $CONTAINER"
    docker stop $CONTAINER
  done
  echo ""
}

startContainers() {
  echo "starting containers with ID $CONTAINER_ID"
  for CONTAINER in $CONTAINER_ID; do
    echo " - starting $CONTAINER"
    docker start $CONTAINER
    RETURN_CODE=$?; echo " - return code: " $RETURN_CODE
    if [ ! "$RETURN_CODE" = "0" ]; then
      SAFE_TO_PROCEED=FALSE
      FAIL_REASON="Docker failed to start container $VS_CONTAINER_NAME, command exited with $RETURN_CODE"
    fi
  done
  echo ""
}

deleteContainers() {
  echo "deleting containers with name $CONTAINER_ID"
  for CONTAINER in $CONTAINER_ID; do
    echo " - deleting $CONTAINER"
    docker container rm -f $CONTAINER
  done
  echo ""
}

deleteImages() {
  #delete existing images - does this have a purpose? will there ever be an image with the name $VS_CONTAINER_NAME?
  echo "deleting any docker images with name $VS_CONTAINER_NAME"
  docker images | egrep "$VS_CONTAINER_NAME"
  for IMAGE in `docker images | egrep "$VS_CONTAINER_NAME" | awk '{print $3}'`; do
    echo "deleting $IMAGE"
    docker image rm -f $IMAGE
  done
  echo ""
}

manageContainers() {
  # at this point we know VS_CONTAINER_NAME, VS_CONTAINER_PRESERVE, CONTAINER_ID, CONTAINER_STATUS
  # if container is RUNNING and preserve running is TRUE then - stop tomcat/undeploy app/leave alone?
  # if container is STOPPED and preserve running is TRUE then - ?
  # if container is running and preserve running is FALSE then - deleteContainers
  if [ "$VS_CONTAINER_PRESERVE" == "TRUE" ] && [ "$CONTAINER_STATUS" == "running" ]; then
    echo "VS_CONTAINER_PRESERVE is $VS_CONTAINER_PRESERVE so existing container $CONTAINER_ID will be re-used"
  elif [ "$VS_CONTAINER_PRESERVE" == "TRUE" ] && [ ! -z "$CONTAINER_ID" ] && [ ! "$CONTAINER_STATUS" == "running" ]; then
    echo "VS_CONTAINER_PRESERVE is $VS_CONTAINER_PRESERVE so existing container $CONTAINER_ID will be started and re-used"
    startContainers
  elif [ ! "$VS_CONTAINER_PRESERVE" == "TRUE" ] && [ "$CONTAINER_STATUS" == "running" ]; then
    echo "VS_CONTAINER_PRESERVE is $VS_CONTAINER_PRESERVE so existing container $CONTAINER_ID will be stopped and removed"
    stopContainers
    deleteContainers
  elif [ ! "$VS_CONTAINER_PRESERVE" == "TRUE" ] && [ ! "$CONTAINER_STATUS" == "running" ]; then
    echo "VS_CONTAINER_PRESERVE is $VS_CONTAINER_PRESERVE so existing container $CONTAINER_ID will be removed"
    deleteContainers
  else
    echo "Container status for $CONTAINER_ID could not be determined"
  fi
  echo ""
}

# check all branches to see what ports are "reserved" by existing containers
getChildBranchesViaCurl() {
  echo "checking for ports reserved by other branches in $VS_PARENT_JOB_NAME"
  #for CONTAINER in `curl -s $JENKINS_URL/job/$VS_PARENT_JOB_NAME/rssLatest | sed -e "s/type=\"text\/html\" href=\"/\n/g" | egrep "^https" | sed -e "s/%252F/\//g" | sed "s/\".*//g" | sed -e "s/htt.*\/\(.*\)\/[0-9]*\//\1/g" | egrep -v "http"`; do
  #  VS_CONTAINER_LIST="$VS_CONTAINER_LIST $CONTAINER"
  #  RESERVED_PORT=`docker inspect --format='{{(index (index .NetworkSettings.Ports "8080/tcp") 0).HostPort}}' $VS_PARENT_JOB_NAME\_$CONTAINER 2>/dev/null`
  #  if [ ! -z "$RESERVED_PORT" ]; then
  #    RESERVED_PORT_LIST="$RESERVED_PORT_LIST $RESERVED_PORT"
  #    echo " - $RESERVED_PORT is reserved by $VS_PARENT_JOB_NAME\_$CONTAINER"
  #  fi
  #done
}

getBranchListViaCurl() {
  for CONTAINER in `curl -s $JENKINS_URL/job/$VS_PARENT_JOB_NAME/rssLatest | sed -e "s/type=\"text\/html\" href=\"/\n/g" | egrep "^https" | sed -e "s/%252F/\//g" | sed "s/\".*//g" | sed -e "s/htt.*\/\(.*\)\/[0-9]*\//$VS_PARENT_JOB_NAME\_\1/g" | egrep -v "http"`; do
    BRANCH_LIST="$BRANCH_LIST $CONTAINER"
    RESERVED_PORT=`docker inspect --format='{{(index (index .HostConfig.PortBindings "8080/tcp") 0).HostPort}}' $CONTAINER 2>/dev/null`
    if [ ! -z "$RESERVED_PORT" ]; then
      RESERVED_PORT_LIST="$RESERVED_PORT_LIST $RESERVED_PORT"
      echo " - $RESERVED_PORT is reserved by $CONTAINER"
    fi
  done
  echo ""
}

getPullRequestListViaCurl() {
  echo "checking for ports reserved by pull requests in $VS_PARENT_JOB_NAME"
  for CONTAINER in `curl -s $JENKINS_URL/job/$VS_PARENT_JOB_NAME/view/change-requests/rssLatest | sed -e "s/type=\"text\/html\" href=\"/\n/g" | egrep "^https" | sed -e "s/%252F/\//g" | sed "s/\".*//g" | sed -e "s/htt.*\/\(.*\)\/[0-9]*\//$VS_PARENT_JOB_NAME\_\1/g" | egrep -v "http"`; do
    BRANCH_LIST="$BRANCH_LIST $CONTAINER"
    RESERVED_PORT=`docker inspect --format='{{(index (index .HostConfig.PortBindings "8080/tcp") 0).HostPort}}' $CONTAINER 2>/dev/null`
    if [ ! -z "$RESERVED_PORT" ]; then
      RESERVED_PORT_LIST="$RESERVED_PORT_LIST $RESERVED_PORT"
      echo " - $RESERVED_PORT is reserved by $CONTAINER"
    fi
  done;
}

getBranchListFromWorkspace() {
  echo "checking for branches and PRs for $VS_PARENT_JOB_NAME listed in workspaces.txt"
  for BRANCH in `cat $JENKINS_HOME/workspace/workspaces.txt | grep "$VS_PARENT_JOB_NAME" | sed -e "s/%2F/\//g" | sed "s/.*\//$VS_PARENT_JOB_NAME\_/g"`; do
    if [ "$VS_DEBUG" = "TRUE" ]; then echo " - found branch $BRANCH"; fi
    BRANCH_LIST="$BRANCH_LIST $BRANCH"
  done
  echo ""
}

getReservedPortList() {
  echo "checking for base ports reserved by containers in BRANCH_LIST"
  #if [ "$VS_DEBUG" == "TRUE" ]; then echo "$BRANCH_LIST"; fi
  for BRANCH in $BRANCH_LIST; do
    #if [ "$VS_DEBUG" == "TRUE" ]; then echo " - checking $BRANCH"; fi
    #RESERVED_PORT=`docker port $BRANCH 2>/dev/null| awk '{gsub(/.*:/,"");}1'`
    RESERVED_PORT=`docker inspect --format='{{(index (index .NetworkSettings.Ports "'$VS_BRXM_TOMCAT_PORT'/tcp") 0).HostPort}}' $BRANCH 2>/dev/null`
    if [ ! -z "$RESERVED_PORT" ]; then
      if [ ! -z "$RESERVED_PORT_LIST" ]; then
        RESERVED_PORT_LIST="$RESERVED_PORT_LIST $RESERVED_PORT"
      else
        RESERVED_PORT_LIST="$RESERVED_PORT"
      fi
      if [ "$VS_DEBUG" == "TRUE" ]; then echo " -- $RESERVED_PORT is reserved by $BRANCH"; fi
    fi
  done
  if [ ! -z "$RESERVED_PORT_LIST" ]; then echo " - ports $RESERVED_PORT_LIST are reserved"; fi
  echo ""
}

tidyContainers() {
  # tidy containers when building the "develop" branch
  if [ "$GIT_BRANCH" == "develop" ]||[ "$VS_TIDY_CONTAINERS" == "TRUE" ]; then
    echo "checking all containers on $NODE_NAME matching $VS_PARENT_JOB_NAME*"
    for CONTAINER in `docker ps -a --filter "name=$VS_PARENT_JOB_NAME*" --format "table {{.Names}}" | tail -n +2`; do
      CONTAINER_MATCHED=
      ALL_CONTAINER_LIST="$ALL_CONTAINER_LIST $CONTAINER"
      #echo "checking to see if there's a branch for $CONTAINER"
      if [ ! -z "$BRANCH_LIST" ]; then 
        for BRANCH_CONTAINER in $BRANCH_LIST; do
          if [ "$CONTAINER" = "$BRANCH_CONTAINER" ]; then
            echo " - there is a branch associated with container $CONTAINER"
            CONTAINER_MATCHED="TRUE"
            break
          fi
        done
        if [ ! "$CONTAINER_MATCHED" = "TRUE" ]; then
          echo " - no branch was found matching container $CONTAINER - deleting"
          docker container rm -f $CONTAINER
        fi
      else
        echo "no branches were found in BRANCH_LIST - not safe to delete containers - please confirm manually"
      fi
    done
    echo ""
  fi
}

setPortRange() {
  # gp:DONE - even if override is set we must still check to ensure it's free, move the while loop to after the if block and just add PORT/MAXPORT values into the if. If the override port if in use the job must fail
  echo "determining port range to test for available base ports"
  if [ -z "$VS_CONTAINER_BASE_PORT_OVERRIDE" ]; then
    if [ "$VS_PARENT_JOB_NAME" == "feature.visitscotland.com-mb" ] && [ "$GIT_BRANCH" == "develop" ]; then
      VS_CONTAINER_BASE_PORT_OVERRIDE=8100
      echo "GIT_BRANCH is $GIT_BRANCH, OVERRIDE PORT will be set to  $VS_CONTAINER_BASE_PORT_OVERRIDE"
    elif [ "$VS_PARENT_JOB_NAME" == "develop.visitscotland.com-mb" ] && [ "$GIT_BRANCH" == "develop" ]; then
      VS_CONTAINER_BASE_PORT_OVERRIDE=8099
      echo "GIT_BRANCH is $GIT_BRANCH for JOB $VS_PARENT_JOB_NAME, OVERRIDE PORT will be set to $VS_CONTAINER_BASE_PORT_OVERRIDE"
    elif [ "$VS_PARENT_JOB_NAME" == "develop-nightly.visitscotland.com-mb" ] && [ "$GIT_BRANCH" == "develop" ]; then
      VS_CONTAINER_BASE_PORT_OVERRIDE=8098
      echo "GIT_BRANCH is $GIT_BRANCH, OVERRIDE PORT will be set to $VS_CONTAINER_BASE_PORT_OVERRIDE"
    elif [ "$VS_PARENT_JOB_NAME" == "develop-stable.visitscotland.com-mb" ] && [ "$GIT_BRANCH" == "develop" ]; then
      VS_CONTAINER_BASE_PORT_OVERRIDE=8097
      echo "GIT_BRANCH is $GIT_BRANCH, OVERRIDE PORT will be set to $VS_CONTAINER_BASE_PORT_OVERRIDE"
    else
      echo "GIT_BRANCH is $GIT_BRANCH for JOB $VS_PARENT_JOB_NAME, NO OVERRIDE PORT will be set"
    fi
  fi
  if [ -z "$VS_CONTAINER_BASE_PORT_OVERRIDE" ]; then
    MIN_PORT=8001
    MAX_PORT=8096
  else
    MIN_PORT=$VS_CONTAINER_BASE_PORT_OVERRIDE
    MAX_PORT=$VS_CONTAINER_BASE_PORT_OVERRIDE
    echo " - MIN_PORT will be set to $VS_CONTAINER_BASE_PORT_OVERRIDE due to VS_CONTAINER_BASE_PORT_OVERRIDE"
    echo ""
  fi
}

findBasePort() {
  echo "finding a free port to map to the new container's Tomcat port - range $MIN_PORT-$MAX_PORT"
  THIS_PORT=$MIN_PORT
  while [ $THIS_PORT -le $MAX_PORT ]; do
    FREE=`netstat -an | egrep "LISTEN *$" | grep $THIS_PORT`
    if [ "$FREE" = "" ]; then
      PORT_IN_USE="FALSE"
      echo " - netstat says $THIS_PORT is free, checking it's not reserved"
      for RESERVED_PORT in $RESERVED_PORT_LIST; do
        if [ "$RESERVED_PORT" = "$THIS_PORT" ]; then
          echo " - docker says $THIS_PORT is reserved"
          PORT_RESERVED="TRUE"
        else
          PORT_RESERVED="FALSE"
        fi
      done
      if [ ! "$PORT_RESERVED" = "TRUE" ]; then
        break
      else
        THIS_PORT=$((THIS_PORT+1))
        sleep 0
      fi
    else
      PORT_IN_USE="TRUE"
      THIS_PORT=$((THIS_PORT+1))
      sleep 0
    fi
  done
  
  if [ $THIS_PORT -le $MAX_PORT ]; then
    VS_CONTAINER_BASE_PORT=$THIS_PORT
    echo " - VS_CONTAINER_BASE_PORT set to $VS_CONTAINER_BASE_PORT"
  elif [ $THIS_PORT -gt $MAX_PORT ] && [ ! -z "$VS_CONTAINER_BASE_PORT_OVERRIDE" ] && [ "$PORT_IN_USE" = "TRUE" ]; then
    echo " - override port $VS_CONTAINER_BASE_PORT_OVERRIDE is in-use - checking if it's reserved by this branch"
    HAS_PORT_ID=`docker ps -a | grep $VS_CONTAINER_BASE_PORT_OVERRIDE | tail -1 | awk '{print $1}'`
    HAS_PORT_NAME=`docker ps -a --filter="id=$HAS_PORT_ID" --format "table {{.Names}}" | tail -n +2`
    if [ "$HAS_PORT_NAME" == "$VS_CONTAINER_NAME" ]; then
      echo " -- success"
      VS_CONTAINER_BASE_PORT=$VS_CONTAINER_BASE_PORT_OVERRIDE
      echo " -- VS_CONTAINER_BASE_PORT set to $VS_CONTAINER_BASE_PORT_OVERRIDE"
    else
      FAIL_REASON="$VS_CONTAINER_BASE_PORT_OVERRIDE is in use by $HAS_PORT_NAME, setting PORT to NULL"
      THIS_PORT=NULL
      SAFE_TO_PROCEED=FALSE
      echo " - $FAIL_REASON"
    fi
  elif [ $THIS_PORT -gt $MAX_PORT ] && [ ! -z "$VS_CONTAINER_BASE_PORT_OVERRIDE" ] && [ ! "$PORT_RESERVED" = "TRUE" ]; then
    echo " - override port $VS_CONTAINER_BASE_PORT_OVERRIDE was not reserved - don't know how we got here"
    VS_CONTAINER_BASE_PORT=$VS_CONTAINER_BASE_PORT_OVERRIDE
    echo " -- VS_CONTAINER_BASE_PORT set to $VS_CONTAINER_BASE_PORT_OVERRIDE"
  else
    FAIL_REASON="port scan reached $MAXPORT, no ports are free, setting PORT to NULL"
    THIS_PORT=NULL
    SAFE_TO_PROCEED=FALSE
    echo " - $FAIL_REASON"
  fi
  echo ""
}

findDynamicPorts() {
  echo "finding free ports from $VS_CONTAINER_BASE_PORT in increments of $VS_CONTAINER_PORT_INCREMENT to dynamically map to other servies on the new container - up to $VS_CONTAINER_DYN_PORT_MAX"
  THIS_PORT=$VS_CONTAINER_BASE_PORT
  echo "" > $VS_MAIL_NOTIFY_BUILD_MESSAGE_EXTRA
  for VS_CONTAINER_INT_PORT in `set | grep "VS_CONTAINER_INT_PORT_"`; do
    VS_CONTAINER_SERVICE=`echo "$VS_CONTAINER_INT_PORT" | sed -e "s/.*_//g" | sed -e "s/=.*//g"`
    VS_CONTAINER_SERVICE_PORT=`echo "$VS_CONTAINER_INT_PORT" | sed -e "s/.*=//g"`
    VS_CONTAINER_SERVICE_LIST=$VS_CONTAINER_SERVICE_LIST" "$VS_CONTAINER_SERVICE
    VS_CONTAINER_SERVICE_LIST_PORTS=$VS_CONTAINER_SERVICE_LIST_PORTS" "$VS_CONTAINER_SERVICE_PORT
    while [ $THIS_PORT -le $VS_CONTAINER_DYN_PORT_MAX ]; do
      THIS_PORT=$((THIS_PORT+$VS_CONTAINER_PORT_INCREMENT))
      FREE=`netstat -an | egrep "LISTEN *$" | grep $THIS_PORT`
      if [ "$FREE" = "" ]; then
        #echo " - netstat says $THIS_PORT is free - using it"
	eval "VS_CONTAINER_EXT_PORT_"$VS_CONTAINER_SERVICE"="$THIS_PORT
        echo " - service $VS_CONTAINER_SERVICE on port $VS_CONTAINER_SERVICE_PORT has been mapped to external port $THIS_PORT" >> $VS_MAIL_NOTIFY_BUILD_MESSAGE_EXTRA
	THIS_DOCKER_MAP="-p $THIS_PORT:$VS_CONTAINER_SERVICE_PORT"
	VS_CONTAINER_PORT_MAPPINGS="$THIS_DOCKER_MAP $VS_CONTAINER_PORT_MAPPINGS"
	break
      elif [ ! "$FREE" = "" ] && [ "$VS_CONTAINER_PRESERVE" == "TRUE" ]; then
        echo " - netstat says $THIS_PORT is not free - checking if it's reserved by this branch "
        HAS_PORT_ID=`docker ps -a | grep $THIS_PORT | tail -1 | awk '{print $1}'`
        HAS_PORT_NAME=`docker ps -a --filter="id=$HAS_PORT_ID" --format "table {{.Names}}" | tail -n +2`
        if [ "$HAS_PORT_NAME" == "$VS_CONTAINER_NAME" ]; then
          echo " -- success"
	  eval "VS_CONTAINER_EXT_PORT_"$VS_CONTAINER_SERVICE"="$THIS_PORT
	  break
	fi
      else
        echo " - $THIS_PORT is in use, trying "$((THIS_PORT+$VS_CONTAINER_PORT_INCREMENT))
      fi
    done
  done
  unset VS_CONTAINER_INT_PORT
  for SERVICE in $VS_CONTAINER_SERVICE_LIST; do
    unset MAPPINGS
    for MAPPING in `set | egrep "VS_CONTAINER_(INT|EXT)_PORT_$SERVICE"`; do
      MAPPINGS=$MAPPING" "$MAPPINGS
    done
    echo " - for service $SERVICE: $MAPPINGS" 
  done
  if [ ! -z "$VS_CONTAINER_PORT_MAPPINGS" ]; then echo " - Docker will be presented with: $VS_CONTAINER_PORT_MAPPINGS"; fi
  echo ""
}

# search for latest Hippo distribution files if HIPPO_LATEST is not already set
findHippoArtifact() {
  if [ ! "$SAFE_TO_PROCEED" = "FALSE" ]; then
    if [ -z $HIPPO_LATEST ]; then
      # search in $WORKSPACE/target/ for files matching "*.tar.gz"
      echo "searching for latest Hippo distribution files in $WORKSPACE/target"
      HIPPO_LATEST=`ls -alht $WORKSPACE/target/visit*.tar.gz | head -1 | awk '{print $9}'` 2>&1 > /dev/null
      if [ -z "$HIPPO_LATEST" ]; then
        # recursive search in $WORKSPACE/ for files matching "visit*.tar.gz"
        echo "no archive found in $WORKSPACE/target/, widening search"
        HIPPO_LATEST=`find $WORKSPACE/ -name "visit*.tar.gz" | head -1`
      fi
      if [ ! -z "$HIPPO_LATEST" ]; then
        echo " - found $HIPPO_LATEST"
      else
        HIPPO_LATEST=NULL
        SAFE_TO_PROCEED=FALSE
        FAIL_REASON="no archive found in $WORKSPACE, giving up"
        echo " - $FAIL_REASON"
      fi
    else
      echo "search for distribution files will not be run as HIPPO_LATEST was overridden to $HIPPO_LATEST"
    fi
  else
    echo ""
    echo "search for distribution files will not be run due to previous failures"
  fi
  echo ""
}

# package SSR app files
packageSSRArtifact() {
  if [ "$VS_SSR_PROXY_ON" = "TRUE" ] && [ ! "$SAFE_TO_PROCEED" = "FALSE" ]; then
    echo "packaging SSR application"
    if [ -d "$VS_FRONTEND_DIR" ]; then
      tar -zcf $VS_SSR_PACKAGE_TARGET/$VS_SSR_PACKAGE_NAME $VS_SSR_PACKAGE_SOURCE
      RETURN_CODE=$?; echo " - return code: " $RETURN_CODE; echo ""
      if [ -a $VS_SSR_PACKAGE_TARGET/$VS_SSR_PACKAGE_NAME ]; then
        VS_SSR_PACKAGE_SIZE=`ls -alh $VS_SSR_PACKAGE_TARGET/$VS_SSR_PACKAGE_NAME | awk '{print $5}'`
        echo $VS_SSR_PACKAGE_NAME " is " $VS_SSR_PACKAGE_SIZE " in size"
      fi
      if [ ! "$RETURN_CODE" = "0" ]; then
        SAFE_TO_PROCEED=FALSE
        FAIL_REASON="Failed to package SSR app from $VS_FRONTEND_DIR, command exited with $RETURN_CODE"
      fi
    fi
  fi
}

# create Docker container
containerCreateAndStart() {
  if [ ! "$SAFE_TO_PROCEED" = "FALSE" ]; then
    VS_CONTAINER_EXPOSE_PORT=$VS_BRXM_TOMCAT_PORT
    echo ""
    echo "about to create a new Docker container with:"
    #VS_DOCKER_CMD='docker run -d --name '$VS_CONTAINER_NAME' -p '$VS_CONTAINER_BASE_PORT':'$VS_CONTAINER_EXPOSE_PORT' --env VS_SSR_PROXY_ON='$VS_SSR_PROXY_ON' --env VS_SSR_PACKAGE_NAME='$VS_SSR_PACKAGE_NAME' '$VS_DOCKER_IMAGE_NAME' /bin/bash -c "/usr/local/bin/vs-mysqld-start && /usr/local/bin/vs-hippo && while [ ! -f /home/hippo/tomcat_8080/logs/cms.log ]; do echo no log; sleep 2; done; tail -f /home/hippo/tomcat_8080/logs/cms.log"'
    if [ "$VS_BRXM_PERSISTENCE_METHOD" == "mysql" ]; then
      VS_DOCKER_CMD='docker run -d --name '$VS_CONTAINER_NAME' -p '$VS_CONTAINER_BASE_PORT':'$VS_CONTAINER_EXPOSE_PORT' '$VS_CONTAINER_PORT_MAPPINGS' --env VS_HIPPO_REPOSITORY_DIR='$VS_BRXM_REPOSITORY' --env VS_HIPPO_REPOSITORY_PERSIST=$VS_HIPPO_REPOSITORY_PERSIST --env VS_SSR_PROXY_ON='$VS_SSR_PROXY_ON' --env VS_SSR_PACKAGE_NAME='$VS_SSR_PACKAGE_NAME' --env VS_CONTAINER_NAME='$VS_CONTAINER_NAME' --env VS_BRXM_TOMCAT_PORT='$VS_BRXM_TOMCAT_PORT' --env VS_BRANCH_NAME='$VS_BRANCH_NAME' --env VS_COMMIT_AUTHOR='$VS_COMMIT_AUTHOR' --env CHANGE_ID='$CHANGE_ID' '$VS_DOCKER_IMAGE_NAME' /bin/bash -c "/usr/local/bin/vs-mysqld-start && while [ ! -f /home/hippo/tomcat_8080/logs/cms.log ]; do echo no log; sleep 2; done; tail -f /home/hippo/tomcat_8080/logs/cms.log"'
    else
      VS_DOCKER_CMD='docker run -d --name '$VS_CONTAINER_NAME' -p '$VS_CONTAINER_BASE_PORT':'$VS_CONTAINER_EXPOSE_PORT' '$VS_CONTAINER_PORT_MAPPINGS' --env VS_HIPPO_REPOSITORY_DIR='$VS_BRXM_REPOSITORY' --env VS_HIPPO_REPOSITORY_PERSIST=$VS_HIPPO_REPOSITORY_PERSIST --env VS_SSR_PROXY_ON='$VS_SSR_PROXY_ON' --env VS_SSR_PACKAGE_NAME='$VS_SSR_PACKAGE_NAME' --env VS_CONTAINER_NAME='$VS_CONTAINER_NAME' --env VS_BRXM_TOMCAT_PORT='$VS_BRXM_TOMCAT_PORT' --env VS_BRANCH_NAME='$VS_BRANCH_NAME' --env VS_COMMIT_AUTHOR='$VS_COMMIT_AUTHOR' --env CHANGE_ID='$CHANGE_ID' '$VS_DOCKER_IMAGE_NAME' /bin/bash -c "while [ ! -f /home/hippo/tomcat_8080/logs/cms.log ]; do echo no log; sleep 2; done; tail -f /home/hippo/tomcat_8080/logs/cms.log"'
    fi
    echo " - $VS_DOCKER_CMD"
    eval $VS_DOCKER_CMD
    RETURN_CODE=$?; echo " - return code: " $RETURN_CODE
    if [ ! "$RETURN_CODE" = "0" ]; then
      SAFE_TO_PROCEED=FALSE
      FAIL_REASON="Docker failed to start container $VS_CONTAINER_NAME, command exited with $RETURN_CODE"
    fi
    else
    echo ""
    echo "container will not be started due to previous failures"
  fi
  echo ""
}

containerUpdates() {
  # check files for updated versions, if the checksum matches we want to update
  TEST_FILES=("/usr/local/bin/vs-hippo" "/usr/local/bin/vs-test")
  TEST_SUMS=("5c92fa2dfbc167d0163c1dc1d8690bfa" "")
  for i in ${!TEST_FILES[@]}; do
    THIS_FILE="${TEST_FILES[$i]}"
    THIS_SUM="${TEST_SUMS[$i]}"
    echo "checking $THIS_FILE for update - md5sum must not match $THIS_SUM"
    THIS_TEST="`docker exec $VS_CONTAINER_NAME md5sum $THIS_FILE 2>/dev/null | awk '{print $1}'`"
    THIS_LOCAL_FILE="`dirname $0`/$VS_CONTAINER_UPDATES_DIR/`basename $THIS_FILE`"
    if [ "$THIS_TEST" == "$THIS_SUM" ] && [ -e "$THIS_LOCAL_FILE" ]; then
      echo " - sums match, an updated version of $THIS_FILE is available, copying to container"
      docker exec $VS_CONTAINER_NAME cp $THIS_FILE $THIS_FILE.old 2>>$VS_CONTAINER_CONSOLE_FILE
      docker cp "$THIS_LOCAL_FILE" $VS_CONTAINER_NAME:$THIS_FILE 2>>VS_CONTAINER_CONSOLE_FILE
      THIS_TEST=`docker exec $VS_CONTAINER_NAME md5sum $THIS_FILE 2>>VS_CONTAINER_CONSOLE_FILE | awk '{print $1}'`
      echo " - sum now: $THIS_TEST"
    else
      echo " - no match"
    fi
  done
  docker exec $VS_CONTAINER_NAME /bin/bash -c "find /usr/local/bin -type f | xargs chmod +x"
}

containerSSHStart() {
  if [ ! "$SAFE_TO_PROCEED" = "FALSE" ]; then
    echo ""
    echo "about to enable SSH in container $VS_CONTAINER_NAME:"
    VS_DOCKER_CMD='docker exec -d $VS_CONTAINER_NAME /bin/bash -c "ssh-keygen -A; /usr/sbin/sshd; echo hippo:$VS_CONTAINER_SSH_PASS_HIPPO | chpasswd; echo root:$VS_CONTAINER_SSH_PASS_ROOT | chpasswd"'
    echo " - $VS_DOCKER_CMD"
    eval $VS_DOCKER_CMD
    RETURN_CODE=$?; echo $RETURN_CODE
    if [ ! "$RETURN_CODE" = "0" ]; then
      SAFE_TO_PROCEED=TRUE
      FAIL_REASON="Docker failed to run command in container $VS_CONTAINER_NAME, command exited with $RETURN_CODE. Script will continue."
    fi
  else
    echo ""
    echo "container will not be started due to previous failures"
  fi
}


# copy build artefacts to container
containerCopyHippoArtifact() {
    if [ ! "$SAFE_TO_PROCEED" = "FALSE" ]; then
    echo ""
    echo "about to copy $HIPPO_LATEST to container $VS_CONTAINER_NAME:/home/hippo"
    docker cp $HIPPO_LATEST $VS_CONTAINER_NAME:/home/hippo
    RETURN_CODE=$?; echo $RETURN_CODE
    if [ ! "$RETURN_CODE" = "0" ]; then
      SAFE_TO_PROCEED=FALSE
      FAIL_REASON="Docker failed to run cp command against $VS_CONTAINER_NAME, command exited with $RETURN_CODE"
    fi
    else
    echo ""
    echo "docker cp will not be run due to previous failures"
  fi
}

containerCopySSRArtifact() {
  if [ "$VS_SSR_PROXY_ON" = "TRUE" ] && [ ! "$SAFE_TO_PROCEED" = "FALSE" ]; then
    echo ""
    echo "about to copy $VS_SSR_PACKAGE_NAME to container $VS_CONTAINER_NAME:/home/hippo"
    docker cp $VS_SSR_PACKAGE_TARGET/$VS_SSR_PACKAGE_NAME $VS_CONTAINER_NAME:/home/hippo
    RETURN_CODE=$?; echo $RETURN_CODE
    if [ ! "$RETURN_CODE" = "0" ]; then
      SAFE_TO_PROCEED=FALSE
      FAIL_REASON="Docker failed to run cp command against $VS_CONTAINER_NAME, command exited with $RETURN_CODE"
    fi
  elif [ ! "$VS_SSR_PROXY_ON" = "TRUE" ] && [ ! "$SAFE_TO_PROCEED" = "FALSE" ]; then
    echo ""
    echo "docker cp of VS_SSR_PACKAGE_NAME:$VS_SSR_PACKAGE_NAME will not be run due VS_SSR_PROXY_ON:$VS_SSR_PROXY_ON"
  else
    echo ""
    echo "docker cp will not be run due to previous failures"
  fi
}

containerStartHippo() {
  if [ ! "$SAFE_TO_PROCEED" = "FALSE" ]; then
    echo ""
    # temporary mamangement of node app here until changes can be made in vs-hippo
    echo "about to execute "/usr/bin/pkill node" in container $VS_CONTAINER_NAME"
    docker exec -d $VS_CONTAINER_NAME /usr/bin/pkill node
    #VS_DOCKER_CMD='docker exec -d $VS_CONTAINER_NAME /bin/bash -c "for PID in `ps -ef | grep "java" | grep "$VS_BRXM_TOMCAT_PORT" | awk \'{print $2}\'`; do echo "terminating $PID"; kill -9 $PID; done"'
    #echo "about to execute $VS_DOCKER_CMD in container $VS_CONTAINER_NAME"
    #eval $VS_DOCKER_CMD 
    if [ "$VS_BRXM_PERSISTENCE_METHOD" == "mysql" ]; then
      VS_DOCKER_CMD='docker exec -d $VS_CONTAINER_NAME /bin/bash -c "/usr/local/bin/vs-hippo >> $VS_CONTAINER_CONSOLE_FILE"'
    else
      VS_DOCKER_CMD='docker exec -d $VS_CONTAINER_NAME /usr/local/bin/vs-hippo nodb'
    fi
    echo "about to execute VS_DOCKER_CMD in container $VS_CONTAINER_NAME"
    echo " - $VS_DOCKER_CMD"
    eval $VS_DOCKER_CMD
    RETURN_CODE=$?; echo $RETURN_CODE
    if [ ! "$RETURN_CODE" = "0" ]; then
      SAFE_TO_PROCEED=FALSE
      FAIL_REASON="Docker failed to run exec command in $VS_CONTAINER_NAME, command exited with $RETURN_CODE"
    fi
  else
    echo ""
    echo "docker exec will not be run due to previous failures"
  fi
}

exportVSVariables() {
  echo " - exporting VS variables to $VS_VS_LAST_ENV and $VS_VS_LAST_ENV$VS_LAST_ENV_QUOTED_SUFFIX and $VS_VS_LAST_ENV$VS_LAST_ENV_GROOVY_SUFFIX to $PWD"
  set | egrep "^(VS_)" | tee $VS_VS_LAST_ENV | sed -e "s/^/env./" -e "s/=\([^'$]\)/=\"\1/" -e "s/\([^'=]\)$/\1\"/" | tee $VS_VS_LAST_ENV$VS_LAST_ENV_QUOTED_SUFFIX | sed -e "s/=/ = /" > $VS_VS_LAST_ENV$VS_LAST_ENV_GROOVY_SUFFIX
}

copyVSVariables() {
  VS_TARGET=$WORKSPACE/site/webapp/src/main/webapp/vs-static/
  echo " - copying VS variables file $VS_VS_LAST_ENV and $VS_VS_LAST_ENV$VS_LAST_ENV_QUOTED_SUFFIX and $VS_VS_LAST_ENV$VS_LAST_ENV_GROOVY_SUFFIX to $VS_TARGET"
  # to-do gp: set VS_TARGET in defaultSettings
  cp $VS_VS_LAST_ENV $VS_VS_LAST_ENV$VS_LAST_ENV_QUOTED_SUFFIX $VS_VS_LAST_ENV$VS_LAST_ENV_GROOVY_SUFFIX $VS_TARGET
}

createBuildReport() {
  if [ ! "$SAFE_TO_PROCEED" = "FALSE" ]; then
    EXIT_CODE=0
    echo ""
    echo "writing mail message to $VS_MAIL_NOTIFY_BUILD_MESSAGE"
    echo ""
    echo "" | tee $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "########################################################################" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "The site instance for branch $GIT_BRANCH should now be available in a few moments on $NODE_NAME - $VS_HOST_IP_ADDRESS" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "To configure your browser session for this branch please follow this link:"
    echo "  - $VS_PROXY_SERVER_SCHEME://$VS_PROXY_SERVER_FQDN/?vs_brxm_host=$VS_HOST_IP_ADDRESS&vs_brxm_port=$VS_CONTAINER_BASE_PORT&vs_brxm_http_host=$VS_BRXM_INSTANCE_HTTP_HOST&vs_ssr_http_port=$VS_CONTAINER_EXT_PORT_SSR&vs_tln_http_port=$VS_CONTAINER_EXT_PORT_TLN&vs_feature_branch=$BRANCH_NAME$VS_PROXY_QS_SSR" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "Thereafter, until you clear the settings, you will be able to access the environment on the following URLs" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo " - site:    $VS_PROXY_SERVER_SCHEME://$VS_PROXY_SERVER_FQDN/" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    #echo "The CMS for the instance should now be available at:" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo " - cms:     $VS_PROXY_SERVER_SCHEME://$VS_PROXY_SERVER_FQDN/cms/" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    #echo "and the Console at:" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo " - console: $VS_PROXY_SERVER_SCHEME://$VS_PROXY_SERVER_FQDN/cms/console/" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo " - logs:    $VS_PROXY_SERVER_SCHEME://$VS_PROXY_SERVER_FQDN/logs/" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    if [ ! -z "$VS_CONTAINER_EXT_PORT_TLN" ]; then echo " - tailon:  $VS_PROXY_SERVER_SCHEME://$VS_PROXY_SERVER_FQDN/tailon/" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE; fi
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "To clear the proxy server settings between sessions either close your browser or browse to:" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "  - $VS_PROXY_SERVER_SCHEME://$VS_PROXY_SERVER_FQDN/?vs_brxm_reset" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "Direct Tomcat access - available only on the Web Development LAN" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "  - http://$VS_HOST_IP_ADDRESS:$VS_CONTAINER_BASE_PORT/cms/" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "  - http://$VS_HOST_IP_ADDRESS:$VS_CONTAINER_BASE_PORT/site/" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "    -  both need a HOST header of \"localhost:8080\" to be passed with the request" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    if [ ! -z "$VS_CONTAINER_EXT_PORT_SSR" ]; then
      echo "Direct SSR access - available only on the Web Development LAN" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
      echo "  - http://$VS_HOST_IP_ADDRESS:$VS_CONTAINER_EXT_PORT_SSR/site/" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
      echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    fi
    if [ ! -z "$VS_CONTAINER_EXT_PORT_SSH" ]; then
      echo "SSH access (if enabled on the container) - available only on the Web Development LAN" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
      echo "  - ssh -p $VS_CONTAINER_EXT_PORT_SSH root@$VS_HOST_IP_ADDRESS ($VS_CONTAINER_SSH_PASS_ROOT)" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
      echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    fi
    if [ -e "$VS_MAIL_NOTIFY_BUILD_MESSAGE_EXTRA" ]; then
      cat $VS_MAIL_NOTIFY_BUILD_MESSAGE_EXTRA | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    fi
    echo "########################################################################" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" >> $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" >> $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "$VS_CONTAINER_BASE_PORT" > env_port.txt
    echo "$VS_HOST_IP_ADDRESS" > env_host.txt
  else
    EXIT_CODE=127
    echo "" | tee $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "########################################################################" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "JOB FAILED because $FAIL_REASON" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "########################################################################" | tee -a $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" >> $VS_MAIL_NOTIFY_BUILD_MESSAGE
    echo "" >> $VS_MAIL_NOTIFY_BUILD_MESSAGE
  fi
}

testSite() {
  false
}

sendBuildReport() {
  if [ -e "$VS_MAIL_NOTIFY_BUILD_MESSAGE" ] && [ "$VS_MAIL_NOTIFY_BUILD" == "TRUE" ]; then
    echo ""
    echo "sending environment build notification to $VS_MAIL_NOTIFY_BUILD_TO"
    mailx -s "$VS_MAIL_NOTIFY_BUILD_SUBJECT" -r "$VS_MAIL_NOTIFY_BUILD_SENDER" "$VS_MAIL_NOTIFY_BUILD_TO" < $VS_MAIL_NOTIFY_BUILD_MESSAGE
  fi
}

sendSiteReport() {
  if [ -e "$VS_MAIL_NOTIFY_SITE_MESSAGE" ] && [ "$VS_MAIL_NOTIFY_SITE" == "TRUE" ]; then
    echo ""
    echo "sending site check notification to $VS_MAIL_NOTIFY_SITE_TO"
    mailx -s "$VS_MAIL_NOTIFY_SITE_SUBJECT" -r "$VS_MAIL_NOTIFY_SITE_SENDER" "$VS_MAIL_NOTIFY_SITE_TO" < $VS_MAIL_NOTIFY_SITE_MESSAGE
  fi
}

# ====/FUNCTIONS ====

# ==== RUN ====
echo "called with $METHOD"
case $METHOD in
  other)
    false
  ;;
  default)
    false
  ;;
  setvars)
    checkVariables
    defaultSettings
    exportVSVariables
    copyVSVariables
  ;;
  *)
    echo "no function specified - running defaults"
    checkVariables
    defaultSettings
    reportSettings
    checkContainers
    manageContainers
    #stopContainers
    #startContainers
    #deleteContainers
    #deleteImages
    #getChildBranchesViaCurl
    #getBranchListViaCurl
    #getPullRequestListViaCurl
    getBranchListFromWorkspace
    getReservedPortList
    tidyContainers
    setPortRange
    findBasePort
    findDynamicPorts
    findHippoArtifact
    packageSSRArtifact
    if [ ! "$VS_CONTAINER_PRESERVE" == "TRUE" ]; then
      containerCreateAndStart
    elif [ "$VS_CONTAINER_PRESERVE" == "TRUE" ] && [ -z "$CONTAINER_ID" ]; then
      containerCreateAndStart
    else
      echo "re-using existing container $CONTAINER_ID"; echo "" 
    fi
    containerUpdates
    containerSSHStart
    containerCopyHippoArtifact
    containerCopySSRArtifact
    containerStartHippo
    exportVSVariables
    testSite
    createBuildReport
    sendBuildReport
  ;;
esac
# ====/RUN ====
exit $EXIT_CODE
