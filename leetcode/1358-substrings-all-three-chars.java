class Solution {
    public int numberOfSubstrings(String s) {
        int lastA = -1, lastB = -1, lastC = -1;
        int ct = 0;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                lastA = i;
            } else if (ch == 'b') {
                lastB = i;
            } else {
                lastC = i;
            }

            if (lastA != -1 && lastB != -1 && lastC != -1) {
                int mn = Math.min(lastA, lastB);
                mn = Math.min(mn, lastC);
                ct += mn + 1;
            }
        }
        return ct;
    }
}