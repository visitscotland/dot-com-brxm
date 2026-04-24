# Unit Testing Standards

## Purpose of Unit Testing

Unit tests exist to **protect the behaviour and intent of the system**. They are not written to satisfy tooling, 
metrics, or frameworks, but to ensure that code continues to meet its **functional requirements** as it evolves.

### What We Expect from Unit Tests in This Project

- Behaviour‑driven, requirement‑focused tests
- Clear intent through naming and display descriptions
- Explicit coverage of edge cases and error handling
- Meaningful assertions over raw coverage numbers
- Failures that are investigated, not ignored

---

## Core Principles

### Unit tests validate requirements, not implementation

Unit tests should describe **what the code is supposed to do**, not *how it does it*.

- Tests must be written against public behaviour.
- Implementation details should be free to change without breaking tests.
- Tests that break after a behaviour‑preserving refactor indicate over‑coupling to implementation.

### Unit tests must cover edge cases and failure modes

In addition to the happy path, unit tests must:
- Assert behaviour when content or input is missing or invalid
- Cover conditional branches and edge cases
- Validate how the system communicates problems (exceptions, error messages, logging)

Logging is an intentional communication mechanism and part of the system’s contract. Units are expected to validate
**Content Logger messages** and **CMS Preview** messages as part of their behaviour.

### A failing unit test is a signal, not a nuisance

When a unit test fails, do not immediately change the test to make it pass.First investigate **why** the failure 
occurred. A failing test may indicate: 
- A regression
- An unintended side effect
- A broken cross‑module assumption

Silencing failing tests reduces their value and allows defects to propagate unnoticed.

### Code coverage is a metric, not a goal

Code coverage measures **where tests execute**, not how effective they are.

- High coverage does not guarantee meaningful testing.
- 100% coverage can still hide missing assertions, brittle tests, or untested behaviour.
- Coverage should be used to identify *risk areas*, not to enforce arbitrary targets.

As Martin Fowler notes, coverage tools indicate where tests run, but not whether they assert correct behaviour.  
Google’s testing guidance similarly emphasises that coverage is informative, not definitive.

Coverage should guide discussion — not replace judgment.

---

## Code Quality Standards

Well‑written unit tests are small and focus on verifying a single behaviour at a time. This makes them easy to read and 
understand without needing to mentally untangle multiple concerns. Avoiding unnecessary stubbing or excess configuration
helps keep the test clear, intention‑revealing, and easier to maintain over time.

If a test is hard to understand, it is harder to trust — especially when it fails.

The following standards describe how unit tests should be written to remain readable, reliable, and maintainable over time.

### Test Setup and Framework Usage

- Use `@ExtendWith(MockitoExtension.class)` to enable clean and reliable integration between JUnit 5 and Mockito.
- Use `@InjectMocks` for the class under test to execute real behaviour while injecting mocked collaborators.
- Avoid manual mock initialization or custom test runners when the Mockito extension is sufficient.

### Stubbing, Scope, and Assertions

- Stub only what each test needs to exercise the behaviour under test.
- Avoid global, shared, or excessive stubbing that can make tests brittle or unclear.
- Use small helper methods if stubbing HippoBean Documents to:
  - Create valid baseline objects
  - Reduce duplication across tests
  - Improve readability
- Assert outcomes and observable effects, not internal state changes or implementation details.
- Use real value objects when possible.

### Tests must be named by behaviour and intent

Each unit test should make its purpose clear **without reading the test body**.

- Test names should describe *behaviour*, not method calls.
- Use `@DisplayName` to reinforce context and intent.



---

## References

- Martin Fowler — *Test Coverage*  
  https://martinfowler.com/bliki/TestCoverage.html

- Google Testing Blog — *Code Coverage Best Practices*  
  https://testing.googleblog.com/2020/08/code-coverage-best-practices.html

- IEEE Software — *Why Code Coverage Is Not Enough*  
  https://ieeexplore.ieee.org/document/6062087
 