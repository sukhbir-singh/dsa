import java.util.*;
// Note: In this dp question, there were 2 variables - index and boolean
// With little more thought process, you can find one more simpler solution - just create 2 new arrays: one without first element, other without last element and find max of both array answers.
class Solution {
    private record Pair(int index, boolean lastPicked){}

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Map<Pair, Integer> dp = new HashMap<>();
        recursion(nums, nums.length-1, dp, false);
        return Math.max(dp.getOrDefault(new Pair(nums.length-1, false), 0), dp.getOrDefault(new Pair(nums.length-1, true), 0));
    }

    private int recursion(int[] nums, int n, Map<Pair, Integer> dp, boolean lastPicked) {
        if (n == 0) {
            return lastPicked ? 0 : nums[0];
        } else if (n < 0) {
            return 0;
        }

        Pair p = new Pair(n, lastPicked);
        if (dp.containsKey(p)) {
            return dp.get(p);
        }

        boolean UpdatedlastPicked = n == nums.length-1 ? true : lastPicked;

        // if current is picked, next we can pick is n-2 (non-adjacent)
        int pick = nums[n] + recursion(nums, n-2, dp, UpdatedlastPicked);
        // if current is not picked, next we can pick is n-1
        int notPick = recursion(nums, n-1, dp, lastPicked);

        int ans = Math.max(pick, notPick);
        dp.put(p, ans);

        return ans;
    }
}