import java.util.*;

class Solution {
    public int reversePairs(int[] nums) {
        int ans = mergeSort(nums, 0, nums.length-1);
        //System.out.println(Arrays.toString(nums));
        return ans;
    }

    private int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = (start + end)/2;
        int cnt = 0;
        // System.out.println("mid is " + mid);
        
        cnt += mergeSort(nums, start, mid);
        cnt += mergeSort(nums, mid + 1, end);

        cnt += countReverses(nums, start, mid, end);
        merge(nums, start, mid, end);

        return cnt;
    }

    private int countReverses(int[] nums, int start, int mid, int end) {
        int cnt = 0;
        int left = start, right = mid + 1;
        while (left <= mid) {
            while (right <= end && (long)nums[left] > (long)nums[right] * 2) {
                right++;
            }
            cnt += (right - (mid + 1));  // important line
            left++;
        }
        return cnt;
    }

    private void merge(int[] nums, int start, int mid, int end) {
        //System.out.println("start: " + start + " , mid: " + mid +" , end: "+ end);
        int[] res = new int[end-start+1];
        
        int left = start, right = mid+1, index = 0;
        while (left <= mid && right <= end) {
            if (nums[left] <= nums[right]) {
                res[index++] = nums[left++];
            } else {
                res[index++] = nums[right++];
            }
        }

        while (left <= mid) {
            res[index++] = nums[left++];
        }

        while (right <= end) {
            res[index++] = nums[right++];
        }

        for (int i=start; i<=end; i++) {
            nums[i] = res[i-start];
        }
    }
}