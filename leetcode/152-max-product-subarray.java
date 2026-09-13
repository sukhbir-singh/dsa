class Solution {
    public int maxProduct(int[] nums) {
        int pre = 1, suf = 1;
        int n = nums.length;
        int ans = nums[0];
        for (int i=0; i<n; i++) {
            pre = pre * nums[i];
            suf = suf * nums[n-1-i];
            ans = Math.max(ans, Math.max(pre, suf));
            if (pre == 0) pre = 1;
            if (suf == 0) suf = 1;
        }
        return ans;
    }
}