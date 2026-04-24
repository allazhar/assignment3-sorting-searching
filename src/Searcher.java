/**
 * Searcher class handles all searching operations.
 * Implements Binary Search – O(log n).
 */
public class Searcher {

    // ─────────────────────────────────────────────
    // SEARCH: Binary Search  –  O(log n)
    // ─────────────────────────────────────────────

    /**
     * Binary Search: works ONLY on a SORTED array.
     * Repeatedly halves the search space:
     *   - Compare target with the middle element.
     *   - If equal   → found, return index.
     *   - If smaller → search left half.
     *   - If larger  → search right half.
     *
     * Best case:  O(1)      – target is the middle element
     * Worst case: O(log n)  – target not present
     * Space:      O(1)      – iterative, no extra memory
     *
     * @param arr    a SORTED integer array
     * @param target the value to find
     * @return index of target if found, -1 otherwise
     */
    public int search(int[] arr, int target) {
        int low  = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;   // safe midpoint (no overflow)

            if (arr[mid] == target) {
                return mid;                      // target found
            } else if (arr[mid] < target) {
                low = mid + 1;                   // target is in right half
            } else {
                high = mid - 1;                  // target is in left half
            }
        }

        return -1;   // target not found
    }
}