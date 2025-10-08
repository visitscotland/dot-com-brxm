#!/usr/bin/env bash
# crash fast on any error
# treat undefined vars as bugs
# propagate traps across functions
# don’t hide pipeline failures
set -Eeuo pipefail

# Inputs (from Jenkins env or defaults)
: "${GIT_COMMIT:?GIT_COMMIT is required}"
LOG_FILE="${1:-${GIT_COMMIT}.log}"
OUT_PROPS="${2:-ci/properties/release_env.properties}"

# Zero/initialize outputs
VS_RELEASE_PACKAGE_WORKSPACE_MD5=""
VS_RELEASE_PACKAGE_NEXUS_URL=""
VS_RELEASE_CANDIDATE_NEXUS_FILENAME=""
VS_ERROR_LINES_EMAIL=""
VS_PIPELINE_OUTCOME_EMAIL="SUCCESS"

# md5 of the distribution package
# Supports both GNU (md5sum) and macOS/BSD (md5 -r)
if compgen -G "target/*distr*.tar.gz" > /dev/null; then
  if command -v md5sum >/dev/null 2>&1; then
    VS_RELEASE_PACKAGE_WORKSPACE_MD5="$(md5sum target/*distr*.tar.gz | head -n1 | cut -d ' ' -f1 || true)"
  elif command -v md5 >/dev/null 2>&1; then
    VS_RELEASE_PACKAGE_WORKSPACE_MD5="$(md5 -r target/*distr*.tar.gz | head -n1 | awk '{print $1}' || true)"
  else
    echo "WARN: Neither md5sum nor md5 found; skipping MD5" >&2
  fi
else
  echo "WARN: No target/*distr*.tar.gz files found; skipping MD5" >&2
fi

if [[ -f "${LOG_FILE}" ]]; then
  MATCHING_LINE=""
  while IFS= read -r line; do
    # Collect error lines for email (HTML-escaped <br/>)
    if [[ "${line}" =~ (\[ERROR\]|\[FAILURE\]|\[FAILED\]|\[EXCEPTION\]) ]]; then
      # Minimal HTML escaping for &, <, >
      esc="${line//&/&amp;}"
      esc="${esc//</&lt;}"
      esc="${esc//>/&gt;}"
      VS_ERROR_LINES_EMAIL+="${esc}<br/>"
      if [[ "${VS_PIPELINE_OUTCOME_EMAIL}" == "SUCCESS" ]]; then
        VS_PIPELINE_OUTCOME_EMAIL="ERROR"
      fi
    fi

    # First URL match only, like Groovy version
    if [[ -z "${MATCHING_LINE}" && "${line}" =~ https://.*distr.*\.tar\.gz ]]; then
      MATCHING_LINE="${line}"
    fi
  done < "${LOG_FILE}"

  if [[ -n "${MATCHING_LINE}" ]]; then
    # Extract first https://… token until first space (or EOL)
    url_part="$(printf '%s' "${MATCHING_LINE}" | sed -E 's/.*(https:\/\/[^ ]+).*/\1/')"

    # Transform to public content URL
    url_no443="${url_part//:443/}"
    VS_RELEASE_PACKAGE_NEXUS_URL="${url_no443//service\/local\/staging\/deployByRepositoryId/content\/repositories}"

    # Validate existence
    http_code="$(curl -o /dev/null --silent --head --write-out '%{http_code}' "${VS_RELEASE_PACKAGE_NEXUS_URL}" || true)"
    if [[ "${http_code}" == "200" ]]; then
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

# Emit properties file for Jenkins to read back
{
  echo "VS_RELEASE_PACKAGE_WORKSPACE_MD5=${VS_RELEASE_PACKAGE_WORKSPACE_MD5}"
  echo "VS_RELEASE_PACKAGE_NEXUS_URL=${VS_RELEASE_PACKAGE_NEXUS_URL}"
  echo "VS_RELEASE_CANDIDATE_NEXUS_FILENAME=${VS_RELEASE_CANDIDATE_NEXUS_FILENAME}"
  echo "VS_ERROR_LINES_EMAIL=${VS_ERROR_LINES_EMAIL}"
  echo "VS_PIPELINE_OUTCOME_EMAIL=${VS_PIPELINE_OUTCOME_EMAIL}"
} > "${OUT_PROPS}"

echo "Wrote ${OUT_PROPS}"
