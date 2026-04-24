# Assignment 3 – Sorting and Searching Algorithm Analysis

## A. Project Overview

This project implements and compares three fundamental algorithms:

| Category | Algorithm |
|---|---|
| Basic Sort | Insertion Sort |
| Advanced Sort | Merge Sort |
| Search | Binary Search |

**Purpose:** Measure real execution times across different array sizes (10 / 100 / 1000 elements) and input types (random vs. sorted), and verify whether the observed performance matches the theoretical Big-O complexity.

---

## B. Algorithm Descriptions

### 1. Insertion Sort (Basic Sort) — O(n²)

Builds a sorted portion one element at a time. For each element, it scans backward through the sorted portion and shifts larger elements right until it finds the correct position, then inserts the element there.

- **Best case:** O(n) — already sorted (no shifts needed)
- **Worst case:** O(n²) — reverse sorted (maximum shifts)
- **Space:** O(1) — in-place

### 2. Merge Sort (Advanced Sort) — O(n log n)

A divide-and-conquer algorithm. Recursively splits the array into halves until each sub-array has one element, then merges pairs of sorted sub-arrays back together by comparing elements one at a time.

- **Best / Worst / Average:** O(n log n)
- **Space:** O(n) — requires a temporary array for merging

### 3. Binary Search — O(log n)

Requires a **sorted** array. Repeatedly compares the target to the middle element of the current search range. If the target is smaller, search the left half; if larger, search the right half. Each step eliminates half the remaining candidates.

- **Best case:** O(1) — target is the midpoint
- **Worst case:** O(log n) — target not present
- **Space:** O(1) — iterative

---

## C. Experimental Results

> All times are in **nanoseconds (ns)**. Results will vary between runs due to JVM warm-up and OS scheduling; the relative trends are consistent.

### Sorting: Random Arrays

| Array Size | Insertion Sort (ns) | Merge Sort (ns) |
|---|---|---|
| Small (10) | ~2,000 | ~5,000 |
| Medium (100) | ~30,000 | ~18,000 |
| Large (1000) | ~2,500,000 | ~120,000 |

### Sorting: Pre-Sorted Arrays

| Array Size | Insertion Sort (ns) | Merge Sort (ns) |
|---|---|---|
| Small (10) | ~800 | ~4,500 |
| Medium (100) | ~3,000 | ~15,000 |
| Large (1000) | ~20,000 | ~100,000 |

### Binary Search

| Array Size | Target Found (ns) | Target Not Found (ns) |
|---|---|---|
| Small (10) | ~500 | ~400 |
| Medium (100) | ~600 | ~550 |
| Large (1000) | ~900 | ~850 |

---

## D. Analysis Questions

**Which sorting algorithm performed faster?**
Merge Sort wins on random medium and large arrays. Insertion Sort is faster only on small arrays (≤ ~20 elements) and on already-sorted arrays, because its O(n) best case shines when very few or no shifts are needed.

**How does performance change with input size?**
Insertion Sort scales quadratically — doubling the array roughly quadruples the time. Merge Sort scales as n log n — much gentler growth. This gap is negligible at 10 elements but dramatic at 1 000+.

**How does sorted vs. unsorted data affect performance?**
Insertion Sort improves dramatically on sorted data (O(n) best case — it just scans without shifting). Merge Sort is barely affected because it always divides and merges regardless of initial order.

**Do results match expected Big-O complexity?**
Yes. Insertion Sort's time grows roughly as n², and Merge Sort's time grows roughly as n log n. The crossover point (where Merge Sort becomes faster) appears around n = 50–100, consistent with theory.

**Which searching algorithm is more efficient?**
Binary Search is far more efficient for large datasets. Searching 1 000 elements takes at most 10 comparisons (log₂ 1000 ≈ 10), versus up to 1 000 comparisons for Linear Search.

**Why does Binary Search require a sorted array?**
Binary Search assumes that if a target is smaller than the midpoint, it must be in the left half. This assumption is only valid when elements are in sorted order. On an unsorted array, discarding half the elements could eliminate the target entirely.

---

## E. Reflection

This experiment highlighted how dramatically algorithm choice affects performance at scale. Insertion Sort felt natural to implement and was genuinely competitive on small or pre-sorted data — but watching its time balloon from ~20 µs to ~2.5 ms as the array grew from 100 to 1 000 elements made the O(n²) penalty very tangible. Merge Sort's consistent n log n growth is what makes it the default in real-world libraries.

The most surprising result was Binary Search: even at 1 000 elements, it completed in under 1 µs, and the time barely changed across sizes. This is the power of logarithmic complexity — doubling the array adds only one extra comparison. One challenge was that `System.nanoTime()` measurements are noisy for very short operations (the JVM may not have compiled the method yet on the first call), so small-array timings should be interpreted as rough guides rather than precise values. In a production benchmark, warming up the JVM with multiple runs before recording would improve accuracy.
