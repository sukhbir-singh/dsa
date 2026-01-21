class Solution {
    // good question for referencing the standard pattern - counting subarrays using sliding window
    public int numberOfSubarrays(int[] nums, int k) {
        return countNiceUpto(nums, k) - countNiceUpto(nums, k-1);
    }

    private int countNiceUpto(int[] nums, int k) {
        if (k < 0) return 0;
        int l = 0, r = 0, odds = 0, sum = 0;
        while (r < nums.length) {
            if (nums[r] % 2 == 1) {
                odds++;
            }
            while (odds > k) {
                if (nums[l] % 2 == 1) {
                    odds--;
                }
                l++;
            }
            sum += r - l + 1;
            r++;
        }
        return sum;
    }
}