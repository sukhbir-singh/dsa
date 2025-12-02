import java.util.*;

class Solution {
    public void nextPermutation(int[] nums) {
        int index = -1;
        for (int i=nums.length-2; i>=0; i--) {
            if (nums[i] < nums[i+1]) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            Arrays.sort(nums);
            return;
        }

        for (int i=nums.length-1; i>=index+1; i--) {
            if (nums[i] > nums[index]) {
                swap(nums, i, index);
                break; // remember this: just need to pick one larger element
            }
        }

        // reverse remaining elements
        int totalRem = nums.length-1 - index;
        // remember that while reversing you have to iterate to only half
        for (int i=0; i<totalRem/2; i++) {
            swap(nums, index+1+i, nums.length-1-i);
        }
    }

    public void swap(int[] arr, int p1, int p2) {
        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }
}