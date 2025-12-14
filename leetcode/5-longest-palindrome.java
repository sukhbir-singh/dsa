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