#!/usr/bin/env bash
# crash fast on any error
# treat undefined vars as bugs
# propagate traps across functions
# don’t hide pipeline failures
set -Eeuo pipefail

# Inputs
BUILD_NUMBER_FILE="${1:-.build-number.txt}"
OUT_PROPS="${2:-ci/properties/release_env.properties}"

# validate env properties file presence
if [[ ! -f "$OUT_PROPS" ]]; then
  echo "ERROR: Output properties file not found: $OUT_PROPS" >&2
  echo "       Please make sure previous stages created it" >&2
  exit 1
fi

VS_SITE_WAR_BUILD_NUMBER=""

# Read build number from the canonical file if present
if [[ -f "$BUILD_NUMBER_FILE" ]]; then
  # remove every whitespace character from the input stream (which is the build-number.txt file)
  # redirect build-number.txt file into tr
  # capture the output of the command inside the parentheses and store it into the VS_SITE_WAR_BUILD_NUMBER variable
  VS_SITE_WAR_BUILD_NUMBER="$(tr -d '[:space:]' < "$BUILD_NUMBER_FILE")"
  if [[ -z "${VS_SITE_WAR_BUILD_NUMBER}" ]]; then
    echo "INFO: $BUILD_NUMBER_FILE is empty" >&2
  fi
else
  echo "INFO: Build number file not found: $BUILD_NUMBER_FILE" >&2
  # build-number.txt doesn't exist on its own, extract the build-number from within the release package files
  echo "INFO: Extracting Build number from the MANIFEST.MF file within $SITE_WAR instead"
  # Concept: should there be a site .war file with a manifest.mf in it, extract the build number from it
  # enter block if the war file exists
  if [[ -f "$SITE_WAR" ]]; then
    # Check if the MANIFEST.MF exists inside the WAR file
    if unzip -l "$SITE_WAR" | grep -q "$MANIFEST_PATH"; then
      # Check if the Build-Number entry exists within the MANIFEST.MF
      # Extract (stream) that file’s contents and read lines from it
      if unzip -p "$SITE_WAR" "$MANIFEST_PATH" | grep -qE '^Build-Number(:|\s)'; then
        # extract the actual build number
        # old version: unzip -p ${siteWarFilePath} ${siteWarManifestFile} | grep "Build-Number" | awk '{print \$2}'
        # new version's benefits:
        # ^Build-Number ensures we only match the actual manifest key, not incidental text
        # -F'[: ]+' treats colon or spaces as valid separators (Build-Number 123 or Build-Number: 123)
        # Uses only awk → simpler error handling and no pipe to grep
        # exit stops processing once it finds a match — faster and avoids duplicates
        # The "$VAR" quoting avoids word-splitting and globbing bugs
        VS_SITE_WAR_BUILD_NUMBER="$(unzip -p "$SITE_WAR" "$MANIFEST_PATH" | awk -F'[: ]+' '/^Build-Number(:| ){1}/{print $2; exit}')"
      else
        echo "INFO: Build-Number entry not found in ${MANIFEST_PATH}" >&2
      fi
    else
      echo "INFO: MANIFEST ${MANIFEST_PATH} not found in ${SITE_WAR}" >&2
    fi
  else
    echo "INFO: WAR not found: ${SITE_WAR}" >&2
  fi
fi

# Append to the existing file
echo "VS_SITE_WAR_BUILD_NUMBER=${VS_SITE_WAR_BUILD_NUMBER}" >> "$OUT_PROPS"
echo "Appended VS_SITE_WAR_BUILD_NUMBER='${VS_SITE_WAR_BUILD_NUMBER}' to ${OUT_PROPS}"