import java.util.*;
class Solution {
    public boolean isMatch(String s, String p) {
        int l1 = s.length(), l2 = p.length();

        int[][] dp = new int[l1][l2]; // 0 - false, 1 - true
        for (int arr[]: dp) {
            Arrays.fill(arr, -1); // -1 - not set
        }

        return isMatchImp(s, p, l1-1, l2-1, dp);
    }

    private boolean isMatchImp(String s, String p, int ind1, int ind2, int[][] dp) {
        if (ind1 == -1 && ind2 == -1) {
            return true;
        } else if (ind1 == -1) {
            if (p.charAt(ind2) == '*') {
                return isMatchImp(s, p, ind1, ind2-1, dp);
            } else {
                return false;
            }
        } else if (ind2 == -1) {
            if (s.charAt(ind1) == '*') {
                return isMatchImp(s, p, ind1-1, ind2, dp);
            } else {
                return false;
            }
        }

        if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2] == 1;
        }

        char c1 = s.charAt(ind1);
        char c2 = p.charAt(ind2);

        boolean res = false;
        if (c1 == '*' || c2 == '*') {
            // this is very nice deduction in rough copy
            // '*' either consumes one string character or matches an empty sequence.
            // suppose star is present in first string
            boolean case1 = isMatchImp(s, p, ind1-1, ind2, dp); // means does not match any char
            boolean case2 = isMatchImp(s, p, ind1, ind2-1, dp); // means one char is matched
            res = case1 || case2;

        } else if (c1 == '?' || c2 == '?') {
            res = isMatchImp(s, p, ind1-1, ind2-1, dp);
        } else if (c1 == c2) {
            res = isMatchImp(s, p, ind1-1, ind2-1, dp);
        } else {
            res = false; // chars dont match
        }

        dp[ind1][ind2] = res ? 1 : 0;
        return res;
    }
}