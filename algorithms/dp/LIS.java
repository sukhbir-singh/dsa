package algorithms.dp;

// bottom up DP based LIS calculations
class LISSuffix {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n]; // 1D array is sufficient
        int maxLIS = 1;
        
        // Base case: Any single element is an increasing subsequence of length 1
        java.util.Arrays.fill(dp, 1);
        
        // Loop backward from n-1 to 0
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }
        
        return maxLIS;
    }
}

class LISPrefix {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];  // 1D array is sufficient
        int maxLIS = 1;
        
        // Base case: Any single element is an increasing subsequence of length 1
        java.util.Arrays.fill(dp, 1);
        
        // Loop forward from 0 to n-1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }
        
        return maxLIS;
    }
}

