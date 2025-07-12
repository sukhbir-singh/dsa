package algorithms.array;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10}; // odd
        // int[] arr = {8, 4, 11, 55, 5, 7, 15, 12}; // even
        System.out.println("Original array: ");
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array: ");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void swap(int[] arr, int p1, int p2) {
        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }

    private static int findPivot(int[] arr, int st, int end) {
        int pivot = arr[st];
        int i = st, j = end;

        // arranging smaller elements left side of pivot and larger elements right side of pivot
        while (i<=j) {
            while(i<=j && arr[i] <= pivot) {
                i++;
            }
            while(i<=j && arr[j] > pivot) {
                j--;
            }

            if (i<j) { // swap
                swap(arr, i, j);
            }
        }
        
        // swap pivot element
        swap(arr, st, j);
        return j;
    }

    private static void quickSort(int[] arr, int st, int end) {
        if (st >= end) {
            return;
        }

        int pivotIndex = findPivot(arr, st, end);
        quickSort(arr, st, pivotIndex-1);
        quickSort(arr, pivotIndex+1, end);
    }
}
