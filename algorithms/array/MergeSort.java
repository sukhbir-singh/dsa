package algorithms.array;

public class MergeSort {
    public static void main(String[] args) {
        // int[] arr = {38, 27, 43, 3, 9, 82, 10}; // odd
        int[] arr = {8, 4, 11, 55, 5, 7, 15, 12}; // even
        System.out.println("Original array: ");
        printArray(arr);

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array: ");
        printArray(arr);
    }

    public static void printArray(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void mergeSort(int[] arr, int st, int end) {
        if (st >= end) {
            return;
        }

        int mid = st + (end-st)/2;
        mergeSort(arr, st, mid); // sort first half
        mergeSort(arr, mid+1, end); // sort second half
        
        merge(arr, st, mid, end); // merge sorted array
    }

    public static void merge(int[] arr, int st, int mid, int end) {
        int res[] = new int[end-st+1];
        int p = 0, p1 = st, p2 = mid + 1;
        while (p1 <= mid && p2 <= end) {
            if (arr[p1] <= arr[p2]) {
                res[p] = arr[p1];
                p1++;
            } else {
                res[p] = arr[p2];
                p2++;
            }
            p++;
        }

        while (p1 <= mid) {
            res[p++] = arr[p1++];
        }

        while (p2 <= end) {
            res[p++] = arr[p2++];
        }

        for (int i=0;i<res.length;i++) {
            arr[st+i] = res[i];
        }
    }
}
