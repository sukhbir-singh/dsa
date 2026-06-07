import java.util.*;
class Solution {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        
        int[][] dp = new int[n1][n2];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }

        int cls = findLcsLength(word1, word2, n1-1, n2-1, dp);
        return n1 + n2 - 2*cls;
    }

    private int findLcsLength(String w1, String w2, int ind1, int ind2, int[][] dp) {
        if (ind1 < 0 || ind2 < 0) {
            return 0;
        }
        if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2];
        }

        int ans = 0;
        if (w1.charAt(ind1) == w2.charAt(ind2)) {
            ans = 1 + findLcsLength(w1, w2, ind1-1, ind2-1, dp);
        } else {
            ans = Math.max(findLcsLength(w1, w2, ind1-1, ind2, dp), findLcsLength(w1, w2, ind1, ind2-1, dp));
        }

        dp[ind1][ind2] = ans;
        return ans;
    }
}

// we can also apply direct DP without LCS to solve this problem
class Solution2 {
    public int minDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = i + j;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
