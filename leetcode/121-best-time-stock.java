class Solution {
    public int maxProfit(int[] prices) {
        int lowest = prices[0];
        int mx = 0;
        for (int p: prices) {
            if (p>=lowest) {
                mx = Math.max(mx, p-lowest);
            } else {
                lowest = p;
            }
        }
        return mx;
    }
}