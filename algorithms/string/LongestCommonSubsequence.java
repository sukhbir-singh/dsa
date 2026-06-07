package algorithms.string;
import java.util.*;

// Very important question: longest common subsequence implementation
class LongestCommonSubsequence {
    public static void main(String args[]) {
        int len = new LongestCommonSubsequence().findLongestSubsequence("bbbab", "babbb");
        System.out.println("ans = " + len);

        len = new LongestCommonSubsequence().findLongestSubsequence("abdcfcmbyxa", "ayfcdb");
        System.out.println("ans = " + len);

        len = new LongestCommonSubsequence().findLongestSubsequence("leetcode", "edocteel");
        System.out.println("ans = " + len);
    }

    public int findLongestSubsequence(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }

        int len = findLongestSubsequenceImp(s, t, s.length()-1, t.length()-1, dp);
        
        String commonStr = findSubsequence(s, t, dp);
        System.out.println(commonStr);

        return len;
    }

    private int findLongestSubsequenceImp(String s, String t, int ind1, int ind2, int[][] dp) {
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
            ans = 1 + findLongestSubsequenceImp(s, t, ind1-1, ind2-1, dp);
        } else {
            ans = Math.max(findLongestSubsequenceImp(s, t, ind1-1, ind2, dp), findLongestSubsequenceImp(s, t, ind1, ind2-1, dp));
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