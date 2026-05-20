// remember to create dp array of size 2*sum+1
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int x : nums) sum += x;
        if (Math.abs(target) > sum) return 0;

        Integer[][] dp = new Integer[nums.length][2*sum+1];
        return calculateWays(nums, nums.length-1, target, 0, sum, dp);
    }

    private int calculateWays(int[] nums, int index, int target, int cur, int sum, Integer[][] dp){
        if (index == -1) return cur == target ? 1 : 0;

        if (dp[index][sum+cur] != null) return dp[index][sum+cur];

        int w = 0;
        w += calculateWays(nums, index-1, target, cur + nums[index], sum, dp);
        w += calculateWays(nums, index-1, target, cur - nums[index], sum, dp);
        dp[index][cur+sum] = w;
        return w;
    }
}