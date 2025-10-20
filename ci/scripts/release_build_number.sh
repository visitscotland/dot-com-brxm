#!/usr/bin/env bash
# crash fast on any error
# treat undefined vars as bugs
# propagate traps across functions
# don’t hide pipeline failures
set -Eeuo pipefail

# Inputs
BUILD_NUMBER_FILE="${1:-.build-meta/build-number.txt}"
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
fi

# Append to the existing file
echo "VS_SITE_WAR_BUILD_NUMBER=${VS_SITE_WAR_BUILD_NUMBER}" >> "$OUT_PROPS"
echo "Appended VS_SITE_WAR_BUILD_NUMBER='${VS_SITE_WAR_BUILD_NUMBER}' to ${OUT_PROPS}"