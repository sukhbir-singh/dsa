import java.util.*;
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // important - remember these corner cases
        if (obstacleGrid[m-1][n-1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];
        for (int i=0; i<m; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        recursion(0, 0, m, n, dp, obstacleGrid);
        return dp[0][0];
    }

    private int recursion(int r, int c, int m, int n, int[][] dp, int[][] obstacleGrid) {
        if (r == m-1 && c == n-1) {
            dp[r][c] = 1;
            return 1;
        }
        if (r >= m || c >= n || obstacleGrid[r][c] == 1) {
            return 0;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        int right = recursion(r, c+1, m, n, dp, obstacleGrid);
        int bottom = recursion(r+1, c, m, n, dp, obstacleGrid);

        dp[r][c] = right+bottom;
        return right+bottom;
    }
}