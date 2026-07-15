---
on:
  issue_comment:
    types: [created]
permissions:
  contents: read
  pull-requests: read
tools:
  github:
    toolsets: [repos, pull_requests, code_security]
safe-outputs:
  create-pull-request-review-comment:
    max: 10
  add-comment:
    max: 1
---

# Security review on demand

When someone comments "/security-review" on a pull request:

1) Load the PR diff and identify security risks.
2) Prioritize high-impact findings:
   - injection risks (SQL/command/template)
   - auth and session mistakes
   - secrets being logged or committed
   - unsafe deserialization
   - SSRF/file path traversal
   - supply chain / dependency red flags
3) For each finding: add a line-level PR review comment with:
   - the risk
   - why it matters
   - a safer alternative (code example if possible)
4) Add one summary comment with a short risk rating:
   - No obvious security issues
   - Needs attention (list top 3)
   - High risk (block merge until fixed)

Constraints:
- Base your review only on repository context and the 
  PR diff.
- Do not recommend disabling security controls.
- If you are uncertain, say so and ask for clarification.
