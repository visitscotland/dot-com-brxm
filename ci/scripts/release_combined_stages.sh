#!/usr/bin/env bash
# One-shot runner that replaces:
#   release_url_md5.sh + release_build_number.sh + release_compose_email.sh
# Outputs:
#   - <out_dir>/results.json  (single source of truth for pipeline)
#   - <out_dir>/email.html    (rendered email body)
#   - <props_path>            (legacy .properties for back-compat)
set -Eeuo pipefail
IFS=$'\n\t'

# ---- Inputs ----
POM="${1:-pom.xml}"
BUILD_NUMBER_FILE="${2:-.build-meta/build-number.txt}"
OUT_DIR="${3:-artifacts}"
LOG_FILE="${4:-${GIT_COMMIT:?GIT_COMMIT is required}.log}"
PROPS_PATH="${5:-ci/properties/release_env.properties}"

# Inputs
SITE_WAR="${6:-site/webapp/target/site.war}"
MANIFEST_PATH="${7:-META-INF/MANIFEST.MF}"

# ---- Preconditions / setup ----
: "${WORKSPACE:?WORKSPACE is required}"
mkdir -p "$OUT_DIR" "$(dirname "$PROPS_PATH")"
# Zero/init legacy props (same keys as before)
: > "$PROPS_PATH"

# ---- Helpers ----
json_escape() {
  # minimal JSON string escaper
  local s=${1-}
  s=${s//\\/\\\\}
  s=${s//\"/\\\"}
  s=${s//$'\n'/\\n}
  s=${s//$'\r'/\\r}
  s=${s//$'\t'/\\t}
  s=${s//</\\u003C}
  s=${s//>/\\u003E}
  s=${s//&/\\u0026}
  printf '%s' "$s"
}

comma_split_to_json_array() {
  # turn "a, b, c" into ["a","b","c"] (empty -> [])
  local raw="${1:-}"
  if [[ -z "$raw" ]]; then
    printf '[]'
    return
  fi
  local IFS=,
  local out=()
  for part in $raw; do
    # trim surrounding spaces
    local t="${part#"${part%%[![:space:]]*}"}"; t="${t%"${t##*[![:space:]]}"}"
    out+=("\"$(json_escape "$t")\"")
  done
  printf '[%s]' "$(IFS=,; echo "${out[*]}")"
}

# ---- 1) Find distribution tarball (must be exactly one) + MD5 ----
shopt -s nullglob
mapfile -t files < <(printf '%s\n' "$WORKSPACE"/target/*distr*.tar.gz)
if (( ${#files[@]} == 0 )); then
  echo "ERROR: No distribution package found under $WORKSPACE/target/" >&2
  VS_PIPELINE_OUTCOME_EMAIL="ERROR"
  VS_ERROR_LINES_EMAIL="No distribution package found under $WORKSPACE/target/<br/>"
elif (( ${#files[@]} > 1 )); then
  echo "ERROR: Multiple distribution packages found:" >&2
  printf '  %s\n' "${files[@]}" >&2
  VS_PIPELINE_OUTCOME_EMAIL="ERROR"
  VS_ERROR_LINES_EMAIL="Multiple distribution packages found in $WORKSPACE/target/<br/>"
else
  DISTRO_FILE="${files[0]}"
fi

VS_RELEASE_PACKAGE_WORKSPACE_MD5=""
if [[ -n "${DISTRO_FILE:-}" && -f "$DISTRO_FILE" ]]; then
  if command -v md5sum >/dev/null 2>&1; then
    VS_RELEASE_PACKAGE_WORKSPACE_MD5="$(md5sum "$DISTRO_FILE" | awk '{print $1}')"
  elif command -v md5 >/dev/null 2>&1; then
    VS_RELEASE_PACKAGE_WORKSPACE_MD5="$(md5 -r "$DISTRO_FILE" | awk '{print $1}')"
  else
    echo "WARN: Neither md5sum nor md5 found; skipping MD5" >&2
  fi
fi

# ---- 2) Parse the build log for the Nexus URL + collect error lines ----
VS_PIPELINE_OUTCOME_EMAIL="${VS_PIPELINE_OUTCOME_EMAIL:-SUCCESS}"
VS_ERROR_LINES_EMAIL="${VS_ERROR_LINES_EMAIL:-}"
VS_RELEASE_PACKAGE_NEXUS_URL=""
VS_RELEASE_CANDIDATE_NEXUS_FILENAME=""

if [[ -f "$LOG_FILE" ]]; then
  MATCHING_LINE=""
  while IFS= read -r line; do
    if [[ "$line" =~ (\[ERROR\]|\[FAILURE\]|\[FAILED\]|\[EXCEPTION\]) ]]; then
      esc="${line//&/&amp;}"; esc="${esc//</&lt;}"; esc="${esc//>/&gt;}"
      VS_ERROR_LINES_EMAIL+="${esc}<br/>"
      [[ "$VS_PIPELINE_OUTCOME_EMAIL" == "SUCCESS" ]] && VS_PIPELINE_OUTCOME_EMAIL="ERROR"
    fi
    if [[ -z "$MATCHING_LINE" && "$line" =~ https://.*distr.*\.tar\.gz ]]; then
      MATCHING_LINE="$line"
    fi
  done < "$LOG_FILE"

  if [[ -n "$MATCHING_LINE" ]]; then
    url_part="$(printf '%s' "$MATCHING_LINE" | sed -E 's/.*(https:\/\/[^ ]+).*/\1/')"
    url_no443="${url_part//:443/}"
    VS_RELEASE_PACKAGE_NEXUS_URL="${url_no443//service\/local\/staging\/deployByRepositoryId/content\/repositories}"
    http_code="$(curl -o /dev/null --silent --head --write-out '%{http_code}' "$VS_RELEASE_PACKAGE_NEXUS_URL" || true)"
    if [[ "$http_code" == "200" ]]; then
      VS_RELEASE_CANDIDATE_NEXUS_FILENAME="${VS_RELEASE_PACKAGE_NEXUS_URL##*/}"
    else
      echo "WARN: Nexus URL invalid. HTTP ${http_code} -> ${VS_RELEASE_PACKAGE_NEXUS_URL}" >&2
    fi
  else
    echo "INFO: No distribution URL found in ${LOG_FILE}" >&2
  fi
else
  echo "INFO: Log file not found: ${LOG_FILE}" >&2
  VS_ERROR_LINES_EMAIL+="Log file not found: ${LOG_FILE}<br/>"
  VS_PIPELINE_OUTCOME_EMAIL="ERROR"
fi

# ---- 3) Build number (from file, or from MANIFEST.MF of the .war distro) ----
VS_SITE_WAR_BUILD_NUMBER=""
# Read build number from the canonical file if present
if [[ -f "$BUILD_NUMBER_FILE" ]]; then
  # remove every whitespace character from the input stream (which is the build-number.txt file)
  # redirect build-number.txt file into tr
  # capture the output of the command inside the parentheses and store it into the VS_SITE_WAR_BUILD_NUMBER variable
  VS_SITE_WAR_BUILD_NUMBER="$(tr -d '[:space:]' < "$BUILD_NUMBER_FILE")"
  if [[ -z "$VS_SITE_WAR_BUILD_NUMBER" ]]; then
      echo "INFO: $BUILD_NUMBER_FILE is empty, assigning it the value: 'UNKNOWN'" >&2
      VS_SITE_WAR_BUILD_NUMBER="UNKNOWN"
  fi
else
  echo "INFO: Build number file not found: $BUILD_NUMBER_FILE" >&2
  # build-number.txt doesn't exist on its own, extract the build-number from within the release package files
  echo "INFO: Extracting Build number from the MANIFEST.MF file within $SITE_WAR instead"
  # Concept: should there be a site .war file with a manifest.mf in it, extract the build number from it
  # enter block if the war file exists
  if [[ -f "$SITE_WAR" ]]; then
    # Check if the MANIFEST.MF exists inside the WAR file
    if unzip -l "$SITE_WAR" | grep -qF "$MANIFEST_PATH"; then
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

# ---- 4) Release version from pom (skip <parent>) ----
VS_RELEASE_VERSION_DETECTED_FOR_EMAIL="${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-}"
if [[ -z "$VS_RELEASE_VERSION_DETECTED_FOR_EMAIL" && -f "$POM" ]]; then
  VS_RELEASE_VERSION_DETECTED_FOR_EMAIL="$(
    awk '/<parent>/,/<\/parent>/ { next } /<version>/ { print; exit }' "$POM" \
      | sed -E 's/.*<version>(.*)<\/version>.*/\1/'
  )"
fi

# ---- 5) Repo name from GIT_URL ----
REPO_NAME="unknown-repo"
if [[ -n "${GIT_URL:-}" ]]; then
  REPO_NAME="${GIT_URL##*/}"
  REPO_NAME="${REPO_NAME%.git}"
fi

# ---- 6) Email subject + HTML ----
EMAIL_SUBJECT="[Release] ${REPO_NAME} v${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-?} - build #${BUILD_NUMBER:-?} - ${VS_PIPELINE_OUTCOME_EMAIL}"
EMAIL_HTML_FILE="$OUT_DIR/email.html"

{
  cat <<'HTML_HEAD'
<html>
<head>
  <meta charset="utf-8"/>
  <style>
    body {font-family: Arial, sans-serif; line-height: 1.6;}
    h1 {color: #333;}
    table {width: 100%; border-collapse: collapse;}
    th, td {border: 1px solid #ddd; padding: 8px; text-align: left;}
    th {background-color: #f2f2f2;}
    code {font-family: ui-monospace, SFMono-Regular, Menlo, Consolas, "Liberation Mono", monospace;}
  </style>
</head>
<body>
HTML_HEAD

  printf '<h1>%s: Package details for release v%s, <a href="%s">Jenkins Build %s</a> - %s</h1>\n' \
    "$(json_escape "$REPO_NAME")" \
    "$(json_escape "${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-?}")" \
    "$(json_escape "${BUILD_URL:-#}")" \
    "$(json_escape "${BUILD_NUMBER:-?}")" \
    "$(json_escape "$VS_PIPELINE_OUTCOME_EMAIL")"

  echo "<p>Here are the details for the artefacts (distribution/release and SSR packages) related to this build.</p>"
  echo "<h2>Release v$(json_escape "${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-?}") artefact</h2>"

  if [[ -n "${VS_ERROR_LINES_EMAIL:-}" ]]; then
    printf '%s\n' "${VS_ERROR_LINES_EMAIL}"
  else
    cat <<TABLE1
<table>
  <tr><th>Build-Number</th><td>$(json_escape "${VS_SITE_WAR_BUILD_NUMBER:-}")</td></tr>
  <tr><th>Nexus URL (hyperlink)</th><td><a href="$(json_escape "${VS_RELEASE_PACKAGE_NEXUS_URL:-}")">$(json_escape "${VS_RELEASE_CANDIDATE_NEXUS_FILENAME:-}")</a></td></tr>
  <tr><th>Nexus URL (clean link)</th><td><code>$(json_escape "${VS_RELEASE_PACKAGE_NEXUS_URL:-}")</code></td></tr>
  <tr><th>MD5 Checksum</th><td><code>$(json_escape "${VS_RELEASE_PACKAGE_WORKSPACE_MD5:-}")</code></td></tr>
</table>
TABLE1
  fi

  cat <<TABLE2
<h2>SSR Package (in Jenkins)</h2>
<table>
  <tr><th>URL (hyperlink)</th><td><a href="$(json_escape "${VS_SSR_ARCHIVED_PACKAGE_URL:-}")">$(json_escape "${VS_SSR_PACKAGE_NAME:-}")</a></td></tr>
  <tr><th>URL (clean link)</th><td><code>$(json_escape "${VS_SSR_ARCHIVED_PACKAGE_URL:-}")</code></td></tr>
  <tr><th>MD5 Checksum</th><td><code>$(json_escape "${VS_SSR_ARCHIVED_PACKAGE_MD5:-}")</code></td></tr>
</table>
<p>For more information, see the <a href="$(json_escape "${BUILD_URL:-#}")/consoleFull">Jenkins build log</a>.</p>
</body></html>
TABLE2
} > "$EMAIL_HTML_FILE"

# ---- 7) Recipients (from Jenkins env, like your old Groovy) ----
RECIPIENTS_ARRAY="$(comma_split_to_json_array "${MAIL_TO_NET:-}, ${VS_COMMIT_AUTHOR:-}, ${CC_DEV_LIST:-}")"

# ---- 8) Write legacy .properties (for any other consumers) ----
{
  echo "VS_RELEASE_PACKAGE_WORKSPACE_MD5=${VS_RELEASE_PACKAGE_WORKSPACE_MD5:-}"
  echo "VS_RELEASE_PACKAGE_NEXUS_URL=${VS_RELEASE_PACKAGE_NEXUS_URL:-}"
  echo "VS_RELEASE_CANDIDATE_NEXUS_FILENAME=${VS_RELEASE_CANDIDATE_NEXUS_FILENAME:-}"
  echo "VS_ERROR_LINES_EMAIL=${VS_ERROR_LINES_EMAIL:-}"
  echo "VS_PIPELINE_OUTCOME_EMAIL=${VS_PIPELINE_OUTCOME_EMAIL:-}"
  echo "VS_SITE_WAR_BUILD_NUMBER=${VS_SITE_WAR_BUILD_NUMBER:-}"
  echo "VS_RELEASE_VERSION_DETECTED_FOR_EMAIL=${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-}"
  echo "VS_EMAIL_SUBJECT=${EMAIL_SUBJECT}"
  echo "VS_EMAIL_HTML_FILE=${EMAIL_HTML_FILE}"
} >> "$PROPS_PATH"

# ---- 9) Write canonical JSON ----
RESULTS_JSON="$OUT_DIR/results.json"
cat > "$RESULTS_JSON" <<JSON
{
  "repo": "$(json_escape "$REPO_NAME")",
  "build": {
    "number": "$(json_escape "${BUILD_NUMBER:-}")",
    "url": "$(json_escape "${BUILD_URL:-}")",
    "logFile": "$(json_escape "$LOG_FILE")",
    "outcome": "$(json_escape "$VS_PIPELINE_OUTCOME_EMAIL")"
  },
  "release": {
    "version": "$(json_escape "${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-}")",
    "nexusUrl": "$(json_escape "${VS_RELEASE_PACKAGE_NEXUS_URL:-}")",
    "filename": "$(json_escape "${VS_RELEASE_CANDIDATE_NEXUS_FILENAME:-}")",
    "md5": "$(json_escape "${VS_RELEASE_PACKAGE_WORKSPACE_MD5:-}")",
    "buildNumber": "$(json_escape "${VS_SITE_WAR_BUILD_NUMBER:-}")"
  },
  "ssr": {
    "url": "$(json_escape "${VS_SSR_ARCHIVED_PACKAGE_URL:-}")",
    "md5": "$(json_escape "${VS_SSR_ARCHIVED_PACKAGE_MD5:-}")",
    "name": "$(json_escape "${VS_SSR_PACKAGE_NAME:-}")"
  },
  "email": {
    "subject": "$(json_escape "$EMAIL_SUBJECT")",
    "htmlFile": "$(json_escape "$EMAIL_HTML_FILE")",
    "recipients": ${RECIPIENTS_ARRAY}
  },
  "notes": {
    "errorsHtml": "$(json_escape "${VS_ERROR_LINES_EMAIL:-}")"
  }
}
JSON

echo "Wrote: $RESULTS_JSON"
echo "Wrote: $PROPS_PATH"
echo "Wrote: $EMAIL_HTML_FILE"
