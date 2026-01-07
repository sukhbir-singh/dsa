class Solution {
    public int minBitFlips(int start, int goal) {
        int ncb = start ^ goal; // get all non-common bits
        int cnt = 0;
        while (ncb > 0) {
            ncb = ncb & (ncb - 1);
            cnt++;
        }
        return cnt;
    }
}