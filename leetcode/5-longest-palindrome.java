// current solution: expand from centres
// DP solution is also good
class Solution {
    public String longestPalindrome(String s) {
        int len = 1;
        int left = 0, right = 0;

        // check for odd palindromes
        for (int i=1; i<s.length()-1; i++) {
            if (s.charAt(i-1) == s.charAt(i+1)) {
                int tempLen = checkPal(s, i-1, i+1);

                if (tempLen > len) {
                    len = tempLen;
                    left = i - (tempLen-1)/2;
                    right = i + (tempLen-1)/2;
                }
            }
        }

        // check for even palindromes
        for (int i=0; i<s.length()-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                int tempLen = checkPal(s, i, i+1);

                if (tempLen > len) {
                    len = tempLen;
                    left = i - (tempLen)/2 + 1;
                    right = i + (tempLen)/2;
                }
            }
        }

        return s.substring(left, right+1);
    }

    private int checkPal(String s, int left, int right) {
        int len = 0;
        if (left + 1 != right) {
            len = 1;
        }

        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                len += 2;
                left--;
                right++;
            } else {
                break;
            }
        }
        return len;
    }
}

// DP solution
class Solution2 {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] ans = new int[] { 0, 0 };

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                ans[0] = i;
                ans[1] = i + 1;
            }
        }

        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }

        int i = ans[0];
        int j = ans[1];
        return s.substring(i, j + 1);
    }
}