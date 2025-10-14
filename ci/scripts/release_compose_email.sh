#!/usr/bin/env bash
set -Eeuo pipefail

POM="${1:-pom.xml}"
OUT_PROPS="${2:-ci/properties/release_env.properties}"

# 1) Require existing props file
if [[ ! -f "$OUT_PROPS" ]]; then
  echo "ERROR: Properties file not found: $OUT_PROPS" >&2
  exit 1
fi

# 2) Load all current key=value pairs into the environment
# shellcheck disable=SC1090
source "$OUT_PROPS"

# Now all variables like $VS_SITE_WAR_BUILD_NUMBER, $VS_RELEASE_PACKAGE_NEXUS_URL, etc. are available directly

# 3) Ensure release version is present (append if missing)
if [[ -z "${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-}" ]]; then
  if [[ -f "$POM" ]]; then
    VS_RELEASE_VERSION_DETECTED_FOR_EMAIL="$(
      awk '/<parent>/,/<\/parent>/ { next } /<version>/ { print; exit }' "$POM" \
        | sed -E 's/.*<version>(.*)<\/version>.*/\1/'
    )"
    echo "VS_RELEASE_VERSION_DETECTED_FOR_EMAIL=${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL}" >> "$OUT_PROPS"
  fi
fi

# 4) Derive repo name from GIT_URL
REPO_NAME="unknown-repo"
if [[ -n "${GIT_URL:-}" ]]; then
  # Strip everything up to last /
  REPO_NAME="${GIT_URL##*/}"
  # Strip trailing .git
  REPO_NAME="${REPO_NAME%.git}"
fi

# 5) Build subject + HTML
EMAIL_SUBJECT="[Release] ${REPO_NAME} v${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL} - build #${BUILD_NUMBER:-?} - ${VS_PIPELINE_OUTCOME_EMAIL}"
EMAIL_HTML_FILE="$(dirname "$OUT_PROPS")/email.html"

{
  cat <<'HTML_HEAD'
<html>
<head>
  <style>
    body {font-family: Arial, sans-serif; line-height: 1.6;}
    h1 {color: #333;}
    table {width: 100%; border-collapse: collapse;}
    th, td {border: 1px solid #ddd; padding: 8px; text-align: left;}
    th {background-color: #f2f2f2;}
  </style>
</head>
<body>
HTML_HEAD

  printf '<h1>%s: Package details for release v%s, <a href="%s">Jenkins Build %s</a> - %s</h1>\n' \
    "$REPO_NAME" "$VS_RELEASE_VERSION_DETECTED_FOR_EMAIL" "${BUILD_URL:-#}" "${BUILD_NUMBER:-?}" "${VS_PIPELINE_OUTCOME_EMAIL}"

  echo "<p>Here are the details for the artefacts (distribution/release and SSR packages) related to this build.</p>"
  echo "<h2>Release v${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL} artefact</h2>"

  if [[ -n "${VS_ERROR_LINES_EMAIL:-}" ]]; then
    echo "${VS_ERROR_LINES_EMAIL}"
  else
    cat <<TABLE1
<table>
  <tr><th>Build-Number</th><td>${VS_SITE_WAR_BUILD_NUMBER:-}</td></tr>
  <tr><th>Nexus URL (hyperlink)</th><td><a href="${VS_RELEASE_PACKAGE_NEXUS_URL:-}">${VS_RELEASE_CANDIDATE_NEXUS_FILENAME:-}</a></td></tr>
  <tr><th>Nexus URL (clean link)</th><td>${VS_RELEASE_PACKAGE_NEXUS_URL:-}</td></tr>
  <tr><th>MD5 Checksum</th><td>${VS_RELEASE_PACKAGE_WORKSPACE_MD5:-}</td></tr>
</table>
TABLE1
  fi

  cat <<TABLE2
<h2>SSR Package (in Jenkins)</h2>
<table>
  <tr><th>URL (hyperlink)</th><td><a href="${VS_SSR_ARCHIVED_PACKAGE_URL:-}">${VS_SSR_PACKAGE_NAME:-}</a></td></tr>
  <tr><th>URL (clean link)</th><td>${VS_SSR_ARCHIVED_PACKAGE_URL:-}</td></tr>
  <tr><th>MD5 Checksum</th><td>${VS_SSR_ARCHIVED_PACKAGE_MD5:-}</td></tr>
</table>
<p>For more information, see the <a href="${BUILD_URL:-#}/consoleFull">Jenkins build log</a>.</p>
</body></html>
TABLE2
} > "$EMAIL_HTML_FILE"

# 6) Append metadata for Jenkins
echo "VS_EMAIL_SUBJECT=${EMAIL_SUBJECT}"     >> "$OUT_PROPS"
echo "VS_EMAIL_HTML_FILE=${EMAIL_HTML_FILE}" >> "$OUT_PROPS"

echo "Composed email data and appended VS_EMAIL_SUBJECT / VS_EMAIL_HTML_FILE to $OUT_PROPS"
