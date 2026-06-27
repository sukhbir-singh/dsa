class Solution {
    public int countSquares(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        int[][] dp = new int[r][c];

        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                    continue;
                }
                
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }

        int sum = 0;
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                sum += dp[i][j];
            }
        }

        return sum;
    }
}