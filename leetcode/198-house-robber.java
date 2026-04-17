import java.util.*;
// standard problem: non-adjacent subsequence sum problem
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        recursion(nums, nums.length-1, dp);
        return dp[nums.length-1];
    }

    private int recursion(int[] nums, int n, int[] dp) {
        if (n == 0) {
            return nums[0];
        } else if (n < 0) {
            return 0;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        // if current is picked, next we can pick is n-2 (non-adjacent)
        int pick = nums[n] + recursion(nums, n-2, dp);
        // if current is not picked, next we can pick is n-1
        int notPick = recursion(nums, n-1, dp);

        int ans = Math.max(pick, notPick);
        dp[n] = ans;

        return ans;
    }
}