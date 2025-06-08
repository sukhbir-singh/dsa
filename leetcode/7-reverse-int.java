class Solution {
    public int reverse(int x) {
        int ori = x;
        if (x < 0) x *= -1;
        
        long rev = 0;
        while (x>0) {
            rev = rev*10 + (x%10);
            x/=10;
        }

        if (rev < 0 || rev > Integer.MAX_VALUE) return 0;
        if (ori < 0) return -1*(int)rev;
        return (int)rev;
    }
}