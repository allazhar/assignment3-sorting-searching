public class Sorter {
    public void basicSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];       // element to be placed
            int j = i - 1;

            // shift elements that are greater than key one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;       // insert key in correct position
        }
    }
    public void advancedSort(int[] arr) {
        if (arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    /** Recursively divides the array into halves */
    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;    // avoids integer overflow

            mergeSort(arr, left, mid);               // sort left half
            mergeSort(arr, mid + 1, right);          // sort right half
            merge(arr, left, mid, right);            // merge sorted halves
        }
    }

    /** Merges two sorted sub-arrays: arr[left..mid] and arr[mid+1..right] */
    private void merge(int[] arr, int left, int mid, int right) {
        // sizes of the two sub-arrays
        int leftSize  = mid - left + 1;
        int rightSize = right - mid;

        // temporary arrays
        int[] leftArr  = new int[leftSize];
        int[] rightArr = new int[rightSize];

        // copy data into temp arrays
        for (int i = 0; i < leftSize;  i++) leftArr[i]  = arr[left + i];
        for (int j = 0; j < rightSize; j++) rightArr[j] = arr[mid + 1 + j];

        // merge the two temp arrays back into arr[left..right]
        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // copy remaining elements
        while (i < leftSize)  arr[k++] = leftArr[i++];
        while (j < rightSize) arr[k++] = rightArr[j++];
    }

    // ─────────────────────────────────────────────
    // UTILITY METHODS
    // ─────────────────────────────────────────────

    /** Prints all elements of an array on one line */
    public void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    /** Generates an array of the given size filled with random integers (0–9999) */
    public int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }

    /** Returns a copy of the given array (preserves the original for multiple tests) */
    public int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }
}
