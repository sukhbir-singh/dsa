package algorithms.array;
import java.util.Arrays;

public class QuickSelect {
    public static int findKthSmallest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        if (k < 1 || k > nums.length) {
            throw new IllegalArgumentException("k must be between 1 and " + nums.length);
        }

        int targetIndex = k - 1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int pivotIndex = partition(nums, left, right);

            if (pivotIndex == targetIndex) {
                return nums[pivotIndex];
            }
            if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

        throw new IllegalStateException("Unable to find the requested element");
    }

    private static int partition(int[] nums, int left, int right) {
        // taking pivot element as right most element
        int pivot = nums[right];
        int smallerElementIndex = left; // this is where next smaller will be placed

        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, smallerElementIndex);
                smallerElementIndex++;
            }
        }

        swap(nums, smallerElementIndex, right);
        return smallerElementIndex;
    }

    private static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 1, 6, 8, 5, 3, 4};
        int k = 3;

        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println(k + "rd smallest element: " + findKthSmallest(nums, k));
    }
}
