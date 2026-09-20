class Solution {
    public String longestPrefix(String s) {
        int longest = 0;

        int base = 29;
        long mod = (long)1e9+7;
        long fh = 0, bh = 0, power = 1;

        for (int i=0; i<s.length()-1; i++) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(s.length()-1 - i);

            fh = (fh * base + (ch1 - 'a' + 1)) % mod;
            bh = (bh + power * (ch2 - 'a' + 1)) % mod;
            power = (power*base)%mod;

            if (fh == bh) {
                longest = i + 1;
            }
        }

        return s.substring(0, longest);
    }
}