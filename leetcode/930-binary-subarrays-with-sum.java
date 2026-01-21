class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return calcSubarraryHavingCountLessThanEqual(nums, goal) - calcSubarraryHavingCountLessThanEqual(nums, goal - 1);
    }

    private int calcSubarraryHavingCountLessThanEqual(int[] nums, int goal) {
        if (goal < 0) return 0;
        int l = 0, r = 0, cur = 0, ans = 0;

        while (r < nums.length) {
            cur += nums[r];

            while (cur > goal) {
                cur -= nums[l];
                l++;
            }

            ans += r-l+1;
            r++;
        }

        return ans;
    }
}