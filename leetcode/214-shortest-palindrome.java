// hash based algorithm is more efficient: Rabin–Karp rolling hash algorithm
class Solution {
    public String shortestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        // finding longest prefix palindrome
        int maxLen = 1;
        long hashBase = 29, forwardHash = 0, reverseHash = 0, powerValue = 1;
        long modValue = (long)1e9+7;
        
        // quite useful algorithm for pattern matching in string
        // quite efficient as well
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);

            forwardHash = (forwardHash * hashBase + (ch - 'a' + 1)) % modValue;
            reverseHash = (reverseHash + (ch - 'a' + 1) * powerValue) % modValue;
            powerValue = (powerValue * hashBase) % modValue;

            if (forwardHash == reverseHash) {
                maxLen = i+1;
            }
        }

        String suffix = s.substring(maxLen);
        String reversed = new StringBuilder(suffix).reverse().toString();
        return reversed + s;
    }
}

// slow giving TLE
class Solution2 {
    public String shortestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        // we have to find longest prefix palindrome
        int maxLen = 1;
        for (int i=0; i<s.length(); i++) {
            if (isPalindrome(s, 0, i)) {
                maxLen = i+1;
            }
        }

        String suffix = s.substring(maxLen);
        String reversed = new StringBuilder(suffix).reverse().toString();
        return reversed + s;
    }

    // slow time limit exceeded
    private boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
