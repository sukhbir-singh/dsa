import java.util.Arrays;

class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);

        int l=0, r=0;
        long total = 0;
        int maxLen = 0;

        // one iteration is for checking one target value. trying to make all elements equal to last element of window
        while (r < nums.length) {
            total += nums[r];

            // it is saying that while our window is invalid, we need to shrink it from the left
            // note: convert to long to avoid overflow - important!
            while ((long)nums[r] * (r-l+1) > total + k) {
                total -= nums[l];
                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }
        return maxLen;
    }
}