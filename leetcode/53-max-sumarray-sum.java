// kadane's algorithm: positive + positive = bigger positive value
// if you found current subarray sum to be negative, throw it away. because adding to next positive will decrease its value.
// negative sum should not be carried forward, otherwise sum will just reduce

// Other solution (slower) could be divide and concuer, bestCombinedSum = numsArray[mid] + bestLeftSum + bestRightSum;
// logic behind divide and concuer, if mid element is part of answer then bestCombinedSum we already calculated, and if not then we are checking in left partition and right partition, answer would be either of these three values. left and right partition will be calculated using recursion.
class Solution {
    public int maxSubArray(int[] nums) {
        int maxNum = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE, curSum = 0;

        for (int i=0; i<nums.length; i++) {
            maxNum = Math.max(maxNum, nums[i]);

            if (curSum + nums[i] > 0) {
                curSum += nums[i];
            } else {
                curSum = 0;
            }

            maxSum = Math.max(maxSum, curSum);
        }

        return maxNum <= 0 ? maxNum : maxSum;
    }
}
