class Solution {
    public boolean isPalindrome(String s) {
        return isPalindrome(s, 0, s.length()-1);
    }

    public boolean isPalindrome(String s, int i, int j) {
        if (i>=j) return true;

        if (!isAlphaNumeric(s.charAt(i))) {
            return isPalindrome(s, i+1, j);
        } else if (!isAlphaNumeric(s.charAt(j))) {
            return isPalindrome(s, i, j-1);
        }

        boolean isSame = Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j));
        if (!isSame) return false;
        return isPalindrome(s, i+1, j-1);
    }

    private boolean isAlphaNumeric(char c) {
        if ((c >= 'a' && c <='z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
            return true;
        else return false;
    }
}