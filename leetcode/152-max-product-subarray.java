// Scan prefix and suffix products to handle negative numbers and reset at zeros.
/*
In this algorithm, pre checks products of prefixes (subarrays that start at the array’s first element), and suf checks products of suffixes (subarrays that end at the last element). For nonzero integer values, scanning both ends is enough:

If the whole array has an even number of negatives, its product is positive. Keeping all its factors gives a product at least as large as one made by removing factors, since every nonzero integer has absolute value at least 1.
If it has an odd number, the whole product is negative. The best positive product can be found by removing from one end: either through the first negative or starting at the last negative. The remaining subarray is a suffix or a prefix, which the scans check.
For example, [-2, 3, -4, 5, -6] has three negatives. Dropping the prefix through the first negative gives [3, -4, 5, -6], with product 360; the suffix scan considers it. The method doesn’t check every internal subarray directly—it relies on this property.
*/
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

// Track the maximum and minimum products ending at each index, since negatives can swap them.
class Solution2 {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int result = max_so_far;

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int temp_max = Math.max(
                curr,
                Math.max(max_so_far * curr, min_so_far * curr)
            );
            min_so_far = Math.min(
                curr,
                Math.min(max_so_far * curr, min_so_far * curr)
            );

            // Update max_so_far after min_so_far to avoid overwriting it
            max_so_far = temp_max;
            // Update the result with the maximum product found so far
            result = Math.max(max_so_far, result);
        }

        return result;
    }
}