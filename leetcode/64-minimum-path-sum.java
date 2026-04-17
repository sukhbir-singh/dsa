import java.util.*;
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i=0; i<m; i++) {
            Arrays.fill(dp[i], -1);
        }
        recursion(grid, m-1, n-1, dp);
        return dp[m-1][n-1];
    }

    private int recursion(int[][] grid, int r, int c, int[][] dp) {
        if (r < 0 || c < 0) {
            return Integer.MAX_VALUE; // taking very high value so that this path won't be considered
        }
        if (r == 0 && c == 0) {
            dp[r][c] = grid[r][c];
            return grid[r][c];
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        int left = recursion(grid, r, c-1, dp);
        int top = recursion(grid, r-1, c, dp);

        int ans = Math.min(left, top) + grid[r][c];;
        dp[r][c] = ans;
        return ans;
    }
}