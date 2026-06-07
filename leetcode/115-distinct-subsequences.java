import java.util.*;

class Solution {
    public int numDistinct(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        int[][] dp = new int[n1][n2];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return findWays(s, t, n1-1, n2-1, dp);
    }

    private int findWays(String s, String t, int ind1, int ind2, int[][] dp) {
        if (ind2 == -1) {
            return 1;
        }
        if (ind1 < 0) {
            return 0;
        }
        if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2];
        }

        // recurrence relations
        int ways = 0;
        if (s.charAt(ind1) == t.charAt(ind2)) {
            // 2 options - take or not take
            ways += findWays(s, t, ind1-1, ind2-1, dp);
            ways += findWays(s, t, ind1-1, ind2, dp);
        } else {
            ways += findWays(s, t, ind1-1, ind2, dp);
        }

        dp[ind1][ind2] = ways;
        return ways;
    }
}