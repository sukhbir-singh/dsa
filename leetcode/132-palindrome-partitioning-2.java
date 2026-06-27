// learnt two very important optimizations in this solution
// while making a cut, if left side of cut is palindrome then only make a cut.
// this will save recursion call on left side of string. saving lots of computations
class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n == 1) {
            return 0;
        }

        Integer[][] dp = new Integer[n+1][n+1];
        return calculateMinCut(s, 0, n-1, dp);
    }

    private int calculateMinCut(String s, int left, int right, Integer[][] dp) {
        if (left >= right) {
            return 0;
        }

        if (dp[left][right] != null) {
            // System.out.println("using cache");
            return dp[left][right];
        }

        // if palindrome return 0
        if (checkPalindrome(s, left, right)) {
            dp[left][right] = 0;
            return 0;
        }

        int mn = Integer.MAX_VALUE;

        // try out all possible cuts
        for (int i=left; i<right; i++) {
            // important optimization - only partition if left side is palindrom after partition
            if (!checkPalindrome(s, left, i)) {
                continue;
            }

            // Note: here two calls are not needed for two paritions because you already figured out that left side does not need any cut
            int totalCuts = 1 + calculateMinCut(s, i+1, right, dp);
            // System.out.println("cutting s="+s +" at "+i +" is giving totalCuts = "+totalCuts);
            mn = Math.min(mn, totalCuts);
        }

        dp[left][right] = mn;
        return mn;
    }

    private boolean checkPalindrome(String s, int left, int right) {
        boolean isPal = true;
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++; right--;
            } else {
                isPal = false;
                break;
            }
        }
        return isPal;
    }
}