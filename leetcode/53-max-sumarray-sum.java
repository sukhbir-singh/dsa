// kadane's algorithm: positive + positive = bigger positive value
// if you found current subarray sum to be negative, throw it away. because adding to next positive will decrease its value.
// negative sum should not be carried forward, otherwise sum will just reduce
// other solution (slower) could be divide and concure - bestCombinedSum = numsArray[mid] + bestLeftSum + bestRightSum;
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