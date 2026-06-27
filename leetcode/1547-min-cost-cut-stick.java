import java.util.*;
class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] points = new int[m+2];
        points[0] = 0;
        points[m+1] = n;
        for (int i=0; i<m; i++) {
            points[i+1] = cuts[i];
        }

        int[][] dp = new int[m+2][m+2];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }

        Arrays.sort(points);
        return calculateMinCost(points, 0, m+1, dp);
    }

    private int calculateMinCost(int[] points, int left, int right, int[][] dp) {
        if (right - left == 1) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int mn = Integer.MAX_VALUE;
        for (int m = left+1; m < right; m++) {
            int total = points[right] - points[left] + calculateMinCost(points, left, m, dp) + calculateMinCost(points, m, right, dp);
            mn = Math.min(mn, total);
        }

        if (mn == Integer.MAX_VALUE) {
            mn = 0;
        }

        dp[left][right] = mn;
        return mn;
    }
}