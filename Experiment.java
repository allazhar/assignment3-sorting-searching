/**
 * Experiment class measures algorithm performance and runs all experiments.
 * Uses System.nanoTime() for high-resolution timing.
 */
public class Experiment {

    private final Sorter   sorter   = new Sorter();
    private final Searcher searcher = new Searcher();

    // ─────────────────────────────────────────────
    // TIMING METHODS
    // ─────────────────────────────────────────────

    /**
     * Measures how long a sort takes on a COPY of the given array.
     * The original array is not modified so it can be reused.
     *
     * @param arr  source array (will be copied)
     * @param type "basic" for Insertion Sort, "advanced" for Merge Sort
     * @return elapsed time in nanoseconds
     */
    public long measureSortTime(int[] arr, String type) {
        int[] copy = sorter.copyArray(arr);   // work on a copy

        long start = System.nanoTime();

        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(copy);
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(copy);
        } else {
            throw new IllegalArgumentException("type must be 'basic' or 'advanced'");
        }

        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Measures how long Binary Search takes on the given (sorted) array.
     *
     * @param arr    a sorted array
     * @param target value to search for
     * @return elapsed time in nanoseconds
     */
    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();
        searcher.search(arr, target);
        long end = System.nanoTime();
        return end - start;
    }

    // ─────────────────────────────────────────────
    // MAIN EXPERIMENT RUNNER
    // ─────────────────────────────────────────────

    /**
     * Runs all experiments:
     *   – Three array sizes   : small (10), medium (100), large (1000)
     *   – Two input types     : random, pre-sorted
     *   – Both sort algorithms: Insertion Sort, Merge Sort
     *   – Binary Search       : on the sorted large array
     */
    public void runAllExperiments() {

        int[] sizes = {10, 100, 1000};
        String[] labels = {"Small (10)", "Medium (100)", "Large (1000)"};

        System.out.println("=".repeat(65));
        System.out.println("          ALGORITHM PERFORMANCE EXPERIMENTS");
        System.out.println("=".repeat(65));

        // ── Sorting experiments ──────────────────────────────────────
        System.out.printf("%-16s %-12s %-22s %-22s%n",
                "Array Size", "Input Type",
                "Insertion Sort (ns)", "Merge Sort (ns)");
        System.out.println("-".repeat(72));

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];

            // --- RANDOM array ---
            int[] randomArr = sorter.generateRandomArray(size);

            long insertionRandom = measureSortTime(randomArr, "basic");
            long mergeRandom     = measureSortTime(randomArr, "advanced");

            System.out.printf("%-16s %-12s %-22d %-22d%n",
                    labels[i], "Random",
                    insertionRandom, mergeRandom);

            // --- SORTED array (sort randomArr once, reuse) ---
            int[] sortedArr = sorter.copyArray(randomArr);
            sorter.advancedSort(sortedArr);   // produce a clean sorted array

            long insertionSorted = measureSortTime(sortedArr, "basic");
            long mergeSorted     = measureSortTime(sortedArr, "advanced");

            System.out.printf("%-16s %-12s %-22d %-22d%n",
                    labels[i], "Sorted",
                    insertionSorted, mergeSorted);
        }

        // ── Searching experiment ─────────────────────────────────────
        System.out.println("\n" + "=".repeat(65));
        System.out.println("               BINARY SEARCH EXPERIMENT");
        System.out.println("=".repeat(65));
        System.out.printf("%-16s %-18s %-20s%n",
                "Array Size", "Target Status", "Binary Search (ns)");
        System.out.println("-".repeat(54));

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];

            // build a sorted array for binary search
            int[] sortedArr = sorter.generateRandomArray(size);
            sorter.advancedSort(sortedArr);

            // target that EXISTS (middle element)
            int existingTarget = sortedArr[size / 2];
            long timeFound = measureSearchTime(sortedArr, existingTarget);

            // target that does NOT exist (guaranteed negative)
            int missingTarget = -999;
            long timeNotFound = measureSearchTime(sortedArr, missingTarget);

            System.out.printf("%-16s %-18s %-20d%n",
                    labels[i], "Found",     timeFound);
            System.out.printf("%-16s %-18s %-20d%n",
                    labels[i], "Not Found", timeNotFound);
        }

        System.out.println("\n" + "=".repeat(65));
        System.out.println("Experiments complete.");
        System.out.println("=".repeat(65));
    }
}