import java.util.Arrays;

public class HeapSort {

    // recursion based approach is better
    private static void heapify(int[] values, int heapSize, int rootIndex) {
        while (true) {
            int largestIndex = rootIndex;
            int leftChildIndex = 2 * rootIndex + 1;
            int rightChildIndex = 2 * rootIndex + 2;

            if (leftChildIndex < heapSize
                    && values[leftChildIndex] > values[largestIndex]) {
                largestIndex = leftChildIndex;
            }

            if (rightChildIndex < heapSize
                    && values[rightChildIndex] > values[largestIndex]) {
                largestIndex = rightChildIndex;
            }

            if (largestIndex == rootIndex) {
                return;
            }

            swap(values, rootIndex, largestIndex);
            rootIndex = largestIndex;
        }
    }

    static void heapSort(int[] values) {
        int length = values.length;

        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(values, length, i);
        }

        for (int endIndex = length - 1; endIndex > 0; endIndex--) {
            swap(values, 0, endIndex);
            heapify(values, endIndex, 0);
        }
    }

    private static void swap(int[] values, int firstIndex, int secondIndex) {
        int temporaryValue = values[firstIndex];
        values[firstIndex] = values[secondIndex];
        values[secondIndex] = temporaryValue;
    }

    public static void main(String[] args) {
        int[] values = {9, 4, 3, 8, 10, 2, 5};

        heapSort(values);

        System.out.println(Arrays.toString(values));
    }
}