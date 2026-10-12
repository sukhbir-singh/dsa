package algorithms.dp;

public class LongestBitonicSubsequence {
    public int longestBitonicSubsequence(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] lis = new int[n];
        int[] lds = new int[n];
        java.util.Arrays.fill(lis, 1);
        java.util.Arrays.fill(lds, 1);

        // 1. Compute LIS (Forward loop)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) lis[i] = Math.max(lis[i], lis[j] + 1);
            }
        }

        // 2. Compute LDS (Backward loop)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]) lds[i] = Math.max(lds[i], lds[j] + 1);
            }
        }

        // 3. Find the maximum bitonic sequence by combining them
        int maxLBS = 0;
        for (int i = 0; i < n; i++) {
            maxLBS = Math.max(maxLBS, lis[i] + lds[i] - 1);
        }

        return maxLBS;
    }
}
