import java.util.*;
class Solution {
    // [1, 2, 3, 4,  5,  6,  7]
    // [1, 3, 6, 10, 15, 21, 28]
    public int subarraySum(int[] nums, int k) {
        int ans = 0;

        // cummulative sum array
        int[] cs = new int[nums.length];
        cs[0] = nums[0];
        for (int i=1; i<nums.length; i++) {
            cs[i] = cs[i-1] + nums[i];
        }

        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1); // important to cover edge case
        for (int s: cs) {
            if (mp.containsKey(s - k)) {
                ans += mp.get(s-k);
            }
            mp.put(s, 1+mp.getOrDefault(s, 0));
        }

        return ans;
    }
}
