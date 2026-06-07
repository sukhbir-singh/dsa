import java.util.*;

class Solution {
    public int minDistance(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();

        int[][] dp = new int[l1][l2];
        for (int arr[]: dp) {
            Arrays.fill(arr, -1);
        }

        return calculateMinDist(word1, word2, l1-1, l2-1, dp);
    }

    private int calculateMinDist(String w1, String w2, int ind1, int ind2, int[][] dp) {
        if (ind1 == -1 && ind2 == -1) {
            return 0;
        } else if (ind1 == -1) {
            return ind2+1; // need to add 1 because it is index and for coverting index to length of string
        } else if (ind2 == -1) {
            return ind1+1;
        }

        if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2];
        }

        int mn = 0;
        if (w1.charAt(ind1) == w2.charAt(ind2)) {
            // just move indexes
            mn = calculateMinDist(w1, w2, ind1-1, ind2-1, dp);
        } else {
            // delete or insert
            int d1 = calculateMinDist(w1, w2, ind1-1, ind2, dp);
            int d2 = calculateMinDist(w1, w2, ind1, ind2-1, dp);
            mn = Math.min(d1, d2);

            // replace
            int r = calculateMinDist(w1, w2, ind1-1, ind2-1, dp);
            mn = Math.min(mn, r);

            mn++; // one operation will be added in all three cases: delete, insert and replace
        }

        dp[ind1][ind2] = mn;
        return mn;
    }
}