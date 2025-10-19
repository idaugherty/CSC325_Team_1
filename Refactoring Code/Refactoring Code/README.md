# Refactor Summary

This repository contains a targeted refactor of the Assignment 8 implementation. The goal was to improve readability, reduce mutable state, and make the code easier to test and maintain without changing its external behavior.

## What changed

- Split large methods into smaller, single-purpose functions.
- Introduced clearer method and variable names to express intent.
- Reduced mutable shared state and confined side effects to well-documented places.
- Applied Java functional constructs (lambdas, method references, Streams) where they simplified logic.
- Removed dead/unused variables and redundant code paths.

## Before / After performance

This refactor focused on structure and maintainability, not algorithmic changes, so asymptotic performance is unchanged. No automated benchmarks were run in this session.

If you'd like to measure performance locally, here are two lightweight approaches you can run on Windows PowerShell.

1) Measure-Command (coarse-grained)

```powershell
# Compile (if needed)
javac -d .\bin .\src\Assignment8Refactored.java

# Run and measure wall-clock time
Measure-Command { java -cp .\bin Assignment8Refactored }
```

Run the original code the same way and record the `TotalMilliseconds` value to compare.

2) Simple timing harness inside Java (finer control)

Add a small timing wrapper in `main` around the work you want to measure:

```java
long start = System.nanoTime();
// call the main processing method
process();
long elapsedMs = (System.nanoTime() - start) / 1_000_000;
System.out.println("Elapsed ms: " + elapsedMs);
```

Run the program several times for warm-up and take the median to reduce noise.

If you provide an input dataset and runtime numbers from the pre-refactor commit, I can compute and add a before/after comparison here.

## How the changes improve maintainability and scalability

This refactor was intentionally small and low-risk, focused on improving long-term maintainability and making future scaling easier. Key practical benefits:

- Smaller, single-purpose functions
	- Easier to read and reason about; each method does one thing and has a clear contract.
	- Simpler to unit test: write focused tests for behavior rather than large integration tests for monoliths.

- Clear, intention-revealing names
	- Reduces cognitive load during reviews and onboarding; future contributors understand behavior without deep debugging.

- Reduced and localized mutable state
	- Lowers chance of subtle bugs from shared state.
	- Makes it safe to refactor parts of the code to run in parallel or to extract services.

- Functional-style transformations (Streams, lambdas, method references)
	- Improves expressiveness for data pipelines and makes transformations composable.
	- Enables easy experimentation with parallelism via `parallelStream()` for CPU-bound workloads (after profiling).

- Safer null handling and clearer error paths
	- Prefer `Optional` or explicit checks at boundaries to avoid NPEs and to document expectations.

Practical effects on scaling and extension:

- Concurrency: Localized state and pure transformation functions make it straightforward to run independent tasks concurrently or migrate to asynchronous processing (e.g., `CompletableFuture`).
- Large inputs: Clear separation of parsing, transformation, and output makes it easier to stream inputs (processing line-by-line) to reduce memory footprint for big datasets.
- Modularity: Smaller helpers are natural extraction points when splitting features into modules or microservices.

Recommended follow-ups to solidify these gains:

- Add unit tests for each helper (happy path + edge cases) and a small integration test for the end-to-end flow.
- Add basic static analysis tools (SpotBugs, Checkstyle) and a CI job to enforce them.
- Add a simple benchmark harness (JMH for microbenchmarks or the supplied timing wrapper) and profile hotspots before making parallel changes.
- Document public method contracts with JavaDoc and add examples for common usage patterns.

Together these changes reduce maintenance cost, speed up reviews, and make measured scaling straightforward and low-risk.

## Simulated peer-review (GitHub-style comment)

> Summary: Good refactor—large responsibilities are split, names are improved, and mutable state is minimized.
>
> Strengths:
> - Pure/data-transforming logic is separated from I/O and side effects.
> - Method and variable names map well to intent; this helps new contributors.
>
> Suggestions:
> - Add unit tests for the new helper methods (happy path + a couple edge cases).
> - Document public method contracts with JavaDoc, including null-handling expectations.
> - If runtime performance matters, add micro-benchmarks (JMH or repeated Measure-Command) and profile hotspots before parallelizing.
>

> Rationale: Small, well-named functions reduce the blast radius of bugs and are easier to test. Localizing state makes it safer to introduce concurrency or streaming data sources later.

### Paste-ready short review

Nice refactor — the code is much easier to follow now. I like the smaller, single-purpose methods and clearer names; they make behavior easier to reason about and test. A few quick suggestions: add unit tests for the new helpers (including edge cases), document public method contracts with JavaDoc, and run a simple benchmark (Measure-Command or a small timing wrapper) on any hot paths before attempting parallelization. Overall this decreases maintenance risk and prepares the code for safe incremental performance work.

## Next steps

- Add unit tests for critical helpers.
- Add a simple benchmark harness and capture baseline numbers for comparison.
- Consider incremental performance-focused refactors only where profiling shows real bottlenecks.

---

If you want, I can run the timing steps (Measure-Command) here if you allow execution or provide representative input and confirm compilation/run permissions.
# Refactor Summary

This repository contains a targeted refactor of the Assignment 8 implementation. The goal was to improve readability, reduce mutable state, and make the code easier to test and maintain without changing its external behavior.

## What changed

- Split large methods into smaller, single-purpose functions.
- Introduced clearer method and variable names to express intent.
- Reduced mutable shared state and confined side effects to well-documented places.
- Applied Java functional constructs (lambdas, method references, Streams) where they simplified logic.
- Removed dead/unused variables and redundant code paths.

## Before / After performance

This refactor focused on structure and maintainability, not algorithmic changes, so asymptotic performance is unchanged. No automated benchmarks were run in this session. To measure locally, run the program and use PowerShell's `Measure-Command` or add a small timing harness.

Example (PowerShell):

```powershell
Measure-Command { java -cp .\bin Assignment8Refactored }
```

If you supply runtime numbers from the original version, I can add a measured before/after comparison.

## How the changes improve maintainability and scalability

- Smaller functions: easier to read, reason about, and unit test.
- Clearer names: reduce cognitive load during reviews and debugging.
- Confined side effects: safer refactoring, easier reasoning about state, and a smaller surface for concurrency bugs.
- Functional constructs: concise, composable transformations that reduce boilerplate and common errors.

Together these changes lower the cost of future enhancements, make code reviews faster, and simplify adding tests or parallelism.

## Simulated peer-review (GitHub-style comment)

> Summary: Good refactor—large responsibilities are split, names are improved, and mutable state is minimized.
>
> Strengths:
> - Pure/data-transforming logic is separated from I/O and side effects.
> - Method and variable names map well to intent; this helps new contributors.
>
> Suggestions:
> - Add unit tests for the new helper methods (happy path + a couple edge cases).
> - Document public method contracts with JavaDoc, including null-handling expectations.
> - If runtime performance matters, add micro-benchmarks (JMH or repeated Measure-Command) and profile hotspots before parallelizing.
>
> Rationale: Small, well-named functions reduce the blast radius of bugs and are easier to test. Localizing state makes it safer to introduce concurrency or streaming data sources later.

## Next steps

- Add unit tests for critical helpers.
- Add a simple benchmark harness and capture baseline numbers for comparison.
- Consider incremental performance-focused refactors only where profiling shows real bottlenecks.

---

If you'd like, I can also open a branch with these documentation changes and include a PR description with this simulated review comment.
## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
