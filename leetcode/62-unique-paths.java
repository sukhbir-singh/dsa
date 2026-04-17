import java.util.*;
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i=0; i<m; i++) {
            Arrays.fill(dp[i], -1);
            // System.out.println(Arrays.toString(dp[i]));
        }
        
        recursion(0, 0, m, n, dp);

        // for (int i=0; i<m; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }

        return dp[0][0];
    }

    private int recursion(int r, int c, int m, int n, int[][] dp) {
        if (r == m-1 && c == n-1) {
            dp[r][c] = 1;
            return 1;
        }
        if (r >= m || c >= n) {
            return 0;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        int right = recursion(r, c+1, m, n, dp);
        int bottom = recursion(r+1, c, m, n, dp);

        dp[r][c] = right+bottom;
        return right+bottom;
    }
}
// bottom up dp - easier and cleaner
class Solution2 {
    public int uniquePaths(int m, int n) {
        int[][] d = new int[m][n];

        for (int[] arr : d) {
            Arrays.fill(arr, 1);
        }
        for (int col = 1; col < m; ++col) {
            for (int row = 1; row < n; ++row) {
                d[col][row] = d[col - 1][row] + d[col][row - 1];
            }
        }
        return d[m - 1][n - 1];
    }
}