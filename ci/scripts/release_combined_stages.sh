#!/usr/bin/env bash
# Unified release script that replaces:
#   release_url_md5.sh + release_build_number.sh + release_compose_email.sh
# Outputs:
#   - <out_dir>/results.json  (single source of truth for pipeline)
#   - <out_dir>/email.html    (rendered email body)
set -Eeuo pipefail
IFS=$'\n\t'
# Trap failures and include the exact command and the *actual* failing line number
#trap 'rc=$?; echo "ERROR: ${BASH_SOURCE[1]:-${BASH_SOURCE[0]:-?}}: ${LINENO:-${BASH_LINENO[0]:-?}}: command failed: ${BASH_COMMAND:-?}" >&2; exit $rc' ERR
trap 'rc=$?; echo "ERROR: ${BASH_SOURCE[0]}:${BASH_LINENO[0]}: ${BASH_COMMAND}" >&2; exit $rc' ERR

# ---- Shared variables ----
DISTRO_FILE=""
VS_RELEASE_PACKAGE_WORKSPACE_MD5=""
VS_PIPELINE_OUTCOME_EMAIL=""
VS_ERROR_LINES_EMAIL=""
VS_SITE_WAR_BUILD_NUMBER=""
VS_RELEASE_PACKAGE_NEXUS_URL=""
VS_RELEASE_CANDIDATE_NEXUS_FILENAME=""
VS_RELEASE_VERSION_DETECTED_FOR_EMAIL=""
VS_SSR_PACKAGE_NAME=""
VS_SSR_ARCHIVED_PACKAGE_PATH=""
VS_SSR_ARCHIVED_PACKAGE_MD5=""
VS_SSR_ARCHIVED_PACKAGE_URL=""

# ---- Preconditions / Defaults / Setup ----

# Preconditions
# if any of the variables GIT_COMMIT, VS_COMMIT_AUTHOR, REPO_NAME isn't set,
# we need to derive their values via git context/commands
if git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  # check if GIT_COMMIT is set (should be the case for a declarative/multibranch Jenkins pipeline
  if [[ -z "${GIT_COMMIT:-}" ]]; then
    GIT_COMMIT="$(git rev-parse HEAD 2>/dev/null || true)"
    #    Issue: If GIT_COMMIT is empty, invalid, or not present locally (shallow checkout edge cases),
    #           git show returns non-zero (error code) and with set -e, the pipeline will exit
    # Solution: Enforce the presence of GIT_COMMIT
    : "${GIT_COMMIT:?ERROR: GIT_COMMIT is empty (cannot derive HEAD)}"
    # After this point, GIT_COMMIT is 100% set
    echo "GIT_COMMIT wasn't set. Derived from git: $GIT_COMMIT"
  fi
  # make sure that VS_COMMIT_AUTHOR is set, as it is tied to the infrastructure.sh execution
  if [[ -z "${VS_COMMIT_AUTHOR:-}" ]]; then
    VS_COMMIT_AUTHOR="$(git show -s --pretty='%ae' "$GIT_COMMIT")"
  fi

  # adjusting the REPO_NAME (will be used in the email's subject)
  origin_url="$(git config --get remote.origin.url || true)"
  if [[ -n "$origin_url" ]]; then
    REPO_NAME="${origin_url##*/}"
    REPO_NAME="${REPO_NAME%.git}"
  else
    REPO_NAME="$(basename "$(git rev-parse --show-toplevel)")"
  fi
else
  # fail the pipeline if we can't derive the repo name
  echo "ERROR: Not inside a git work tree; cannot determine REPO_NAME" >&2
  exit 1
fi

: "${REPO_NAME:?ERROR: REPO_NAME is empty}"

# Inputs
#POM="${1:-pom.xml}"
#BUILD_NUMBER_FILE="${2:-.build-meta/build-number.txt}"
#OUT_DIR="${3:-artifacts}"
#LOG_FILE="${4:-${GIT_COMMIT:?GIT_COMMIT is required}.log}"
#SITE_WAR="${5:-site/webapp/target/site.war}"        # kept for backwards-compatibility, not used directly anymore
#MANIFEST_PATH="${6:-META-INF/MANIFEST.MF}"          # kept for backwards-compatibility, not used directly anymore
#MODE="${7:-all}"  # can be: all, step1, step2, step3, etc.

fail

# Defaults
MODE="all"
OUT_DIR="artifacts"
POM="pom.xml"
BUILD_NUMBER_FILE="build-number.txt"
LOG_FILE=""            # empty means "auto-derive"

# Loop over every argument passed to the script
for arg in "$@"; do
  case "$arg" in
    --mode=*) MODE="${arg#*=}" ;;
    --out-dir=*) OUT_DIR="${arg#*=}" ;;
    --pom=*) POM="${arg#*=}" ;;
    --build-number-file=*) BUILD_NUMBER_FILE="${arg#*=}" ;;
    --log-file=*) LOG_FILE="${arg#*=}" ;;
    *)
      echo "ERROR: Unknown argument: $arg" >&2
      exit 2
      ;;
  esac
done

# Setup
WORKSPACE="${WORKSPACE:-$(pwd)}"

# Derive log file if not provided (and normalise it to WORKSPACE path)
if [[ -z "${LOG_FILE:-}" ]]; then
  if [[ -n "${GIT_COMMIT:-}" ]]; then
    LOG_FILE="${WORKSPACE}/${GIT_COMMIT}.log"
  else
    # should never reach here (but if it does, fail the pipeline)
    echo "ERROR: no $GIT_COMMIT detected, exiting..." >&2
    exit 2
    # fallback: pick a single *.log if present
    #LOG_FILE="$(ls -1t "${WORKSPACE}"/*.log 2>/dev/null | head -n1 || true)"
  fi
fi

# If BUILD_NUMBER_FILE path is relative, normalise it to WORKSPACE path
if [[ "${BUILD_NUMBER_FILE:-}" != /* ]]; then
  BUILD_NUMBER_FILE="$WORKSPACE/$BUILD_NUMBER_FILE"
fi

# If POM path is relative, normalise it to WORKSPACE path
if [[ "$POM" != /* ]]; then
  POM="$WORKSPACE/$POM"
fi

# If OUT_DIR path is relative, normalise it to WORKSPACE path
if [[ "$OUT_DIR" != /* ]]; then
  OUT_DIR="$WORKSPACE/$OUT_DIR"
fi
mkdir -p "$OUT_DIR"

# ---- Helpers ----
md5_for_file() {
  # MD5 helper: prints empty string if the tool doesn't exists or file passed as an argument is missing
  local f="$1" md5=""
  [[ -f "$f" ]] || { printf '%s' ""; return; }

  if command -v md5sum >/dev/null 2>&1; then
    md5="$(md5sum "$f" | awk '{print $1}')"
  fi
  printf '%s' "$md5"
}

# helper function for .tar.gz projects (like dot-com, dot-org)
get_manifest_from_tar() {
  local tar_file="$1"

  # Find site.war inside tarball
  local war_rel
  war_rel="$(tar -tzf "$tar_file" | grep -m1 'webapps/site\.war$' || true)"
  [[ -z "$war_rel" ]] && return

  # Make a temp dir
  local tmpdir
  tmpdir="$(mktemp -d "${TMPDIR:-/tmp}/distwar.XXXXXXXX")" || return

  tar -xzf "$tar_file" -C "$tmpdir" "$war_rel" 2>/dev/null || {
    rm -rf "$tmpdir"
    return
  }

  local war_path="$tmpdir/$war_rel"
  local manifest
  manifest="$(get_manifest_from_war "$war_path")"

  rm -rf "$tmpdir"

  printf '%s' "$manifest"
}

# helper function for .war projects (like vs-dms-products)
get_manifest_from_war() {
  local war_file="$1"

  # Attempt to read the manifest
  unzip -p "$war_file" META-INF/MANIFEST.MF 2>/dev/null || true
}

# =====================================================================
# Step 1 - Find distribution tarball + MD5
# =====================================================================
step_1_find_distro() {
  echo "==> Step 1: Finding distribution artifact (tar.gz or war, and SSR if needed)..."

  local files=()
  VS_SSR_PACKAGE_NAME=""
  VS_SSR_ARCHIVED_PACKAGE_PATH=""
  VS_SSR_ARCHIVED_PACKAGE_MD5=""

  # Iterate over everything under target/
  for f in "$WORKSPACE"/target/*; do
    [[ -f "$f" ]] || continue   # skip directories etc.

    local filename="${f##*/}"
    local lower="${filename,,}"   # lowercase for SSR check

    # 1) Detect SSR package: keep it, but do NOT treat it as main distro
    if [[ $lower == *ssr* && $filename == *.tar.gz ]]; then
      VS_SSR_PACKAGE_NAME="$filename"
      VS_SSR_ARCHIVED_PACKAGE_PATH="$f"

      # Compute MD5
      VS_SSR_ARCHIVED_PACKAGE_MD5="$(md5_for_file "$f")"

      # Construct the Jenkins URL to the SSR archived artifact
      VS_SSR_ARCHIVED_PACKAGE_URL="${BUILD_URL%/}/artifact/target/${filename}"

      # continue → do NOT add SSR to main distro list
      continue
    fi

    # 2) Main distribution candidates: .tar.gz OR .war (non-SSR only)
    if [[ $filename == *.tar.gz || $filename == *.war ]]; then
      files+=("$f")
    fi
  done

  # 3) Select main distro from non-SSR candidates
  if (( ${#files[@]} == 0 )); then
    echo "            ERROR: No suitable distribution (.tar.gz or .war, excluding SSR) found under $WORKSPACE/target/" >&2
    VS_PIPELINE_OUTCOME_EMAIL="ERROR"
    VS_ERROR_LINES_EMAIL="No suitable distribution found in target/<br/>"
    return 1
  fi

  if (( ${#files[@]} > 1 )); then
    echo "            WARN: Multiple matching artifacts found — using the first." >&2
    printf '        %s\n' "${files[@]}" >&2
  fi

  # Pick the "newest" file from the list, based on modification time
  DISTRO_FILE="$(ls -1t "${files[@]}" 2>/dev/null | head -n1)"

  echo "            INFO: Selected distribution artifact: $DISTRO_FILE"

  # Compute MD5 for main distro
    VS_RELEASE_PACKAGE_WORKSPACE_MD5="$(md5_for_file "$DISTRO_FILE")"
    if [[ -z "$VS_RELEASE_PACKAGE_WORKSPACE_MD5" ]]; then
      echo "            WARN: MD5 checksum could not be computed for $DISTRO_FILE (no md5/md5sum?)" >&2
    fi
}

# =====================================================================
# Step 2 - Parse build log for Nexus URL and collect error lines
# =====================================================================
step_2_parse_log() {
  echo "==> Step 2: Parsing build log..."
  VS_PIPELINE_OUTCOME_EMAIL="${VS_PIPELINE_OUTCOME_EMAIL:-SUCCESS}"
  VS_ERROR_LINES_EMAIL="${VS_ERROR_LINES_EMAIL:-}"
  VS_RELEASE_PACKAGE_NEXUS_URL=""
  VS_RELEASE_CANDIDATE_NEXUS_FILENAME=""

  if [[ ! -f "$LOG_FILE" ]]; then
    echo "            INFO: Log file not found: ${LOG_FILE}" >&2
    VS_ERROR_LINES_EMAIL+="Log file not found: ${LOG_FILE}<br/>"
    VS_PIPELINE_OUTCOME_EMAIL="ERROR"
    return
  fi

  local MATCHING_LINE=""
  while IFS= read -r line; do
    # Capture any ERROR-/FAILURE-like lines for email body
    if [[ "$line" =~ (\[ERROR\]|\[FAILURE\]|\[FAILED\]|\[EXCEPTION\]) ]]; then
      local esc="${line//&/&amp;}"
      esc="${esc//</&lt;}"
      esc="${esc//>/&gt;}"
      VS_ERROR_LINES_EMAIL+="${esc}<br/>"
      [[ "$VS_PIPELINE_OUTCOME_EMAIL" == "SUCCESS" ]] && VS_PIPELINE_OUTCOME_EMAIL="ERROR"
    fi

    # detect any maven artifact that has been deployed to Nexus (tar.gz or war)
    if [[ -z "$MATCHING_LINE" && "$line" =~ https://.*\.(tar\.gz|war) ]]; then
      MATCHING_LINE="$line"
    fi
  done < "$LOG_FILE"

  if [[ -z "$MATCHING_LINE" ]]; then
    echo "            INFO: No distribution URL found in ${LOG_FILE}" >&2
    return
  fi

  # Extract the URL from the matched line
  local url_part
  url_part="$(printf '%s' "$MATCHING_LINE" | sed -E 's/.*(https:\/\/[^ ]+).*/\1/')"
  local url_no443="${url_part//:443/}"
  VS_RELEASE_PACKAGE_NEXUS_URL="${url_no443//service\/local\/staging\/deployByRepositoryId/content\/repositories}"

  # Probe the URL; mark filename only if it exists
  local http_code
  http_code="$(curl -o /dev/null --silent --head --write-out '%{http_code}' "$VS_RELEASE_PACKAGE_NEXUS_URL" || true)"
  if [[ "$http_code" == "200" ]]; then
    VS_RELEASE_CANDIDATE_NEXUS_FILENAME="${VS_RELEASE_PACKAGE_NEXUS_URL##*/}"
  else
    echo "            WARN: Nexus URL invalid. HTTP ${http_code} -> ${VS_RELEASE_PACKAGE_NEXUS_URL}" >&2
  fi
}

# =====================================================================
# Step 3 - Build number (from file, or from MANIFEST.MF of the .war distro)
# =====================================================================
step_3_extract_build_number() {
  echo "==> Step 3: Extracting build number..."
  VS_SITE_WAR_BUILD_NUMBER=""

  # --------------------------------------------------------------------
  # 1) Preferred: Use build-number.txt (canonical source)
  # --------------------------------------------------------------------
  if [[ -s "$BUILD_NUMBER_FILE" ]]; then
    # remove every whitespace character from the input stream (which is the build-number.txt file)
    # redirect build-number.txt file into tr
    # capture the output of the command inside the parentheses and store it into the VS_SITE_WAR_BUILD_NUMBER variable
    VS_SITE_WAR_BUILD_NUMBER="$(tr -d '[:space:]' < "$BUILD_NUMBER_FILE")"
    if [[ -n "$VS_SITE_WAR_BUILD_NUMBER" ]]; then
      echo "            INFO: Build number extracted from $BUILD_NUMBER_FILE: $VS_SITE_WAR_BUILD_NUMBER"
      return
    fi
    # File existed and was non-empty but produced empty result after trimming → treat as UNKNOWN
    echo "            INFO: $BUILD_NUMBER_FILE content is whitespace-only — assigning UNKNOWN" >&2
    VS_SITE_WAR_BUILD_NUMBER="UNKNOWN"
    return
  elif [[ -f "$BUILD_NUMBER_FILE" ]]; then
    # File exists but is empty (size zero)
    echo "            INFO: $BUILD_NUMBER_FILE is empty — assigning UNKNOWN" >&2
    VS_SITE_WAR_BUILD_NUMBER="UNKNOWN"
    return
  fi

  # --------------------------------------------------------------------
  # 2) Fallback: MANIFEST.MF inside distribution artifact
  # --------------------------------------------------------------------
  echo "            INFO: Build number file not found in: $BUILD_NUMBER_FILE, widening the search..." >&2
  echo "            INFO: Falling back to MANIFEST.MF inside distribution artifact"

  if [[ -z "${DISTRO_FILE:-}" || ! -f "$DISTRO_FILE" ]]; then
    echo "            ERROR: DISTRO_FILE missing— Step 1 must run before Step 3" >&2
    exit 1
  fi

  echo "            INFO: Using distribution artifact $DISTRO_FILE"

  local manifest_contents=""

  # Case A — distribution is TAR.GZ with embedded WAR (dot-com, dot-org)
  if [[ "$DISTRO_FILE" == *.tar.gz ]]; then
    echo "            INFO: Distribution is TAR.GZ; searching for embedded site.war"
    manifest_contents="$(get_manifest_from_tar "$DISTRO_FILE")"

  # Case B — distribution is WAR (vs-dms-products)
  elif [[ "$DISTRO_FILE" == *.war ]]; then
    echo "            INFO: Distribution is WAR; reading MANIFEST directly"
    manifest_contents="$(get_manifest_from_war "$DISTRO_FILE")"
  fi

  if [[ -z "$manifest_contents" ]]; then
    echo "            WARN: No MANIFEST.MF could be extracted from $DISTRO_FILE" >&2
    return
  fi

  # --------------------------------------------------------------------
  # 3) Common MANIFEST parse logic (same for WAR or TAR)
  # --------------------------------------------------------------------
  VS_SITE_WAR_BUILD_NUMBER="$(
    printf '%s\n' "$manifest_contents" \
      | awk -F'[: ]+' '/^Build-Number/{print $2; exit}' \
      | tr -d '\r[:space:]'
  )"

  if [[ -n "$VS_SITE_WAR_BUILD_NUMBER" ]]; then
    echo "            INFO: Build number extracted from MANIFEST.MF: $VS_SITE_WAR_BUILD_NUMBER"
  else
    echo "            WARN: No Build-Number entry found in MANIFEST.MF" >&2
  fi
}

# =====================================================================
# Step 4 - Derive release version from pom.xml (skip <parent>)
# =====================================================================
step_4_parse_pom() {
  echo "==> Step 4: Deriving release version..."
  if [[ -n "$VS_RELEASE_VERSION_DETECTED_FOR_EMAIL" ]]; then
    return
  fi

  if [[ -f "$POM" ]]; then
    VS_RELEASE_VERSION_DETECTED_FOR_EMAIL="$(
      awk '/<parent>/,/<\/parent>/ { next } /<version>/ { print; exit }' "$POM" \
        | sed -E 's/.*<version>(.*)<\/version>.*/\1/'
    )"
  fi
}

# =====================================================================
# Step 5 - Compose email HTML (json-oriented approach)
# =====================================================================
step_5_compose_email() {
  echo "==> Step 5: Composing email..."
  echo "            INFO: REPO_NAME=$REPO_NAME"

  EMAIL_SUBJECT="[Release] ${REPO_NAME:-unknown-repo} v${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-?} - build #${BUILD_NUMBER:-?} - ${VS_PIPELINE_OUTCOME_EMAIL}"
  EMAIL_SUBJECT_FILE="$OUT_DIR/email.subject.txt"

  # Write subject as a single line, to a file
  printf '%s' "$EMAIL_SUBJECT" > "$EMAIL_SUBJECT_FILE"
  echo "            INFO: Wrote subject file: $EMAIL_SUBJECT_FILE"
  echo "            INFO: EMAIL_SUBJECT: $EMAIL_SUBJECT"

  # Write email recipients as a single line, to a file
  EMAIL_RECIPIENTS="${MAIL_TO_NET:-}, ${VS_COMMIT_AUTHOR:-}, ${CC_DEV_LIST:-}"
  EMAIL_RECIPIENTS_FILE="$OUT_DIR/email.recipients.txt"
  printf '%s' "$EMAIL_RECIPIENTS" > "$EMAIL_RECIPIENTS_FILE"
  echo "            INFO: Wrote recipients file: $EMAIL_RECIPIENTS_FILE"
  echo "            INFO: EMAIL_RECIPIENTS: $EMAIL_RECIPIENTS"

  # Constructing the email body in html, and writing it to a file
  EMAIL_HTML_FILE="$OUT_DIR/email.body.txt"

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
    code {font-family: ui-monospace, monospace;}
  </style>
</head>
<body>
HTML_HEAD

    printf '<h1>%s: Package details for release v%s, <a href="%s">Jenkins Build %s</a> - %s</h1>\n' \
      "$REPO_NAME" \
      "${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-?}" \
      "${BUILD_URL:-#}" \
      "${BUILD_NUMBER:-?}" \
      "$VS_PIPELINE_OUTCOME_EMAIL"

    echo "<p>Here are the details for the artefacts (distribution/release and SSR packages) related to this build.</p>"
    printf '<h2>Release v%s artefact</h2>\n' "${VS_RELEASE_VERSION_DETECTED_FOR_EMAIL:-?}"

    if [[ -n "${VS_ERROR_LINES_EMAIL:-}" ]]; then
      printf '%s\n' "${VS_ERROR_LINES_EMAIL}"
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

    if [[ -n "${VS_SSR_ARCHIVED_PACKAGE_URL:-}" ]]; then
      cat <<TABLE2
<h2>SSR Package (in Jenkins)</h2>
<table>
  <tr><th>URL (hyperlink)</th><td><a href="${VS_SSR_ARCHIVED_PACKAGE_URL}">${VS_SSR_PACKAGE_NAME:-}</a></td></tr>
  <tr><th>URL (clean link)</th><td>${VS_SSR_ARCHIVED_PACKAGE_URL}</td></tr>
  <tr><th>MD5 Checksum</th><td>${VS_SSR_ARCHIVED_PACKAGE_MD5:-}</td></tr>
</table>
TABLE2
    fi
    cat <<TABLE3
<p>For more information, here's the <a href="${BUILD_URL%/}/consoleFull">Jenkins build log</a>.</p>
</body></html>
TABLE3
  } > "$EMAIL_HTML_FILE"
  echo "            (debugging) Wrote: $EMAIL_HTML_FILE"
}

# =====================================================================
# Main dispatcher
# =====================================================================
usage() {
  cat >&2 <<'USAGE'
Usage:
  release_combined_stages.sh [--mode=all|step1|step2|step3|step4|step5]
                             [--out-dir=artifacts]
                             [--pom=pom.xml]
                             [--build-number-file=PATH]
                             [--log-file=PATH]

Notes:
  - If --build-number-file is not provided, the script will prioritise $WORKSPACE/$BUILD_NUMBER_FILE, and subsequently, auto-discover common locations
  - If --log-file is not provided, it defaults to $WORKSPACE/$GIT_COMMIT.log when possible
USAGE
}

main() {
  case "$MODE" in
    all)   step_1_find_distro; step_2_parse_log; step_3_extract_build_number; step_4_parse_pom; step_5_compose_email ;;
    step1) step_1_find_distro ;;
    step2) step_2_parse_log ;;
    step3) step_3_extract_build_number ;;
    step4) step_4_parse_pom ;;
    step5) step_5_compose_email ;;
    *) usage; exit 1 ;;
  esac
}

main "$@"
