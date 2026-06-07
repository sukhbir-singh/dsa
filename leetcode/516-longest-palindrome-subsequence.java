import java.util.*;

// Very important question: longest common subsequence implementation
class Solution {
    public int longestPalindromeSubseq(String s) {
        String t = new StringBuilder(s).reverse().toString();
        //System.out.println("s="+s);
        //System.out.println("t="+t);

        int[][] dp = new int[s.length()][t.length()];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }

        int len = findLongestSubsequence(s, t, s.length()-1, t.length()-1, dp);
        // String commonStr = findSubsequence(s, t, dp);
        // System.out.println(commonStr);

        return len;
    }

    private int findLongestSubsequence(String s, String t, int ind1, int ind2, int[][] dp) {
        if (ind1 < 0 || ind2 < 0) {
            return 0;
        }
        if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2];
        }

        char ch1 = s.charAt(ind1);
        char ch2 = t.charAt(ind2);

        int ans = 0;
        if (ch1 == ch2) {
            ans = 1 + findLongestSubsequence(s, t, ind1-1, ind2-1, dp);
        } else {
            ans = Math.max(findLongestSubsequence(s, t, ind1-1, ind2, dp), findLongestSubsequence(s, t, ind1, ind2-1, dp));
        }

        dp[ind1][ind2] = ans;
        return ans;
    }

    private String findSubsequence(String s, String t, int[][] dp) {
        int r = dp.length-1, c = dp[0].length-1;
        StringBuilder sb = new StringBuilder();

        // for (int i=0; i<=r; i++) {
        //     for (int j=0; j<=c; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        while (r>=0 && c>=0) {
            if (s.charAt(r) == t.charAt(c)) {
                sb.append(s.charAt(r));
                r--; c--;
            } else {
                // we need to add this condition because we are taking dp array sync with string indexes
                // in bottom up dp its better to take base indexes + 1 for dp array
                if (r==0 || c==0) {
                    if (r==0) {
                        c--;
                    } else {
                        r--;
                    }
                    continue;
                }

                if (dp[r-1][c] > dp[r][c-1]) {
                    r--;
                } else {
                    c--;
                }
            }
        }
        return sb.reverse().toString();
    }
}

// leetcode: another interesting way of solving the problem without reversing the string
class Solution2 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        return lps(s, 0, n - 1, memo);
    }

    private int lps(String s, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = lps(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(lps(s, i + 1, j, memo), lps(s, i, j - 1, memo));
        }
        return memo[i][j];
    }
}