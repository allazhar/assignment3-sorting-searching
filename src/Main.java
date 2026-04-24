public class Main {

    public static void main(String[] args) {

        Sorter     sorter     = new Sorter();
        Searcher   searcher   = new Searcher();
        Experiment experiment = new Experiment();

        // ── 1. DEMO: Small array (10 elements) ──────────────────────
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  SORTING & SEARCHING ALGORITHM ANALYSIS  ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        System.out.println("── DEMO: Small Random Array (10 elements) ─────────────────");
        int[] small = sorter.generateRandomArray(10);

        System.out.print("Original : ");
        sorter.printArray(small);

        // Insertion Sort demo
        int[] smallCopy = sorter.copyArray(small);
        sorter.basicSort(smallCopy);
        System.out.print("After Insertion Sort : ");
        sorter.printArray(smallCopy);

        // Merge Sort demo
        int[] smallCopy2 = sorter.copyArray(small);
        sorter.advancedSort(smallCopy2);
        System.out.print("After Merge Sort     : ");
        sorter.printArray(smallCopy2);

        // ── 2. DEMO: Binary Search ───────────────────────────────────
        System.out.println("\n── DEMO: Binary Search on sorted array ────────────────────");
        int target   = smallCopy[smallCopy.length / 2];   // pick a value we know exists
        int index    = searcher.search(smallCopy, target);
        System.out.println("Sorted array  : " + java.util.Arrays.toString(smallCopy));
        System.out.println("Searching for : " + target);
        System.out.println("Found at index: " + index);

        int missingTarget = -1;
        int missingIndex  = searcher.search(smallCopy, missingTarget);
        System.out.println("Searching for : " + missingTarget);
        System.out.println("Result        : " + missingIndex + " (not found)");

        // ── 3. FULL PERFORMANCE EXPERIMENTS ─────────────────────────
        System.out.println();
        experiment.runAllExperiments();
    }
}