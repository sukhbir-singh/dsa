import java.util.*;

// idea - for finding minimum insertions. lets try to keep maximum palindrome subsequence intact
// answer will be total length - maximum palindrome subsequence
class Solution {
    public int minInsertions(String s) {
        String t = new StringBuilder(s).reverse().toString();
        int n = s.length();

        int[][] dp = new int[n][n];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }

        int lcsLen = findLcsLength(s, t, n-1, n-1, dp);
        return n-lcsLen;
    }

    private int findLcsLength(String s, String t, int ind1, int ind2, int[][] dp) {
        if (ind1<0 || ind2<0) {
            return 0;
        }
        if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2];
        }

        char c1 = s.charAt(ind1);
        char c2 = t.charAt(ind2);

        int ans = 0;
        if (c1 == c2) {
            ans = 1 + findLcsLength(s, t, ind1-1, ind2-1, dp);
        } else {
            ans = Math.max(findLcsLength(s, t, ind1-1, ind2, dp), findLcsLength(s, t, ind1, ind2-1, dp));
        }

        dp[ind1][ind2] = ans;
        return ans;
    }
}