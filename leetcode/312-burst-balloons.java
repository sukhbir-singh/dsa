import java.util.*;

// better to use array instead of list in this question
// Below Memoized interval DP is O(n^3): O(n^2) intervals, each trying O(n) choices for the last balloon.
// Subset-mask DP has O(2^n) states (typically O(n * 2^n) time); this recursion without memoization is O(3^n).
// Each of the n balloons is either burst or not burst, giving 2 × 2 × … × 2 = 2^n possible subsets
class Solution {
    public int maxCoins(int[] nums) {
        int m = nums.length;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int num: nums) {
            list.add(num);
        }
        list.add(1);

        int[][] dp = new int[m+2][m+2];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }

        return recursion(list, 1, m, dp);
    }

    private int recursion(List<Integer> list, int left, int right, int[][] dp) {
        if (left > right) return 0;

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int mx = Integer.MIN_VALUE;
        for (int i=left; i<=right; i++) {
            // selecting last balloon to burst
            int coins = list.get(left-1)*list.get(i)*list.get(right+1);
            // this breaks down into two independent subproblems
            int additional = recursion(list, left, i-1, dp) + recursion(list, i+1, right, dp);
            mx = Math.max(mx, coins + additional);
        }

        dp[left][right] = mx;
        return mx;
    }
}