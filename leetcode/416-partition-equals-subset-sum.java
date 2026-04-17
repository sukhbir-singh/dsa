// In DP, always always try to create 2D DP array for memoization. Other map based dp is inefficient.
class Solution {
    // we can only divide array to two equal sum parts when total sum is even
    // and target will be sum/2
    // simple recursion solution is resulting in time limit exceeded because exponential time complexity and overlapping subproblems
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }

        if (sum%2 == 1) {
            return false;
        }

        Boolean[][] dp = new Boolean[nums.length+1][1+sum/2];
        return isTargetSum(nums, nums.length-1, sum/2, dp);
    }

    private boolean isTargetSum(int[] nums, int index, int target, Boolean[][] dp) {
        if (target < 0) {
            return false;
        }

        if (target == 0) {
            dp[index][target] = true;
            return true;
        }
        if (index == 0) {
            dp[index][target] = nums[index] == target;
            return nums[index] == target;
        }

        if (dp[index][target] != null) {
            return dp[index][target];
        }

        // pick
        boolean pick = isTargetSum(nums, index-1, target-nums[index], dp);
        // not pick
        boolean notPick = isTargetSum(nums, index-1, target, dp);

        boolean res = pick || notPick;
        dp[index][target] = res;
        return res;
    }
}

// Important learning => The logic is correct, but the HashMap with Pair record keys is too slow. Each recursive call creates a new Pair object, and HashMap lookups involve hashing, autoboxing, and GC pressure. With nums.length up to 200 and sum/2 up to 20,000, that's up to 4 million states — manageable with an array, but costly with a HashMap.
class TimeLimitExceedSolution {
    // we can only divide array to two equal sum parts when total sum is even
    // and target will be sum/2
    // simple recursion solution is resulting in time limit exceeded because exponential time complexity and overlapping subproblems
    private record Pair(int index, int target) {};

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }

        if (sum%2 == 1) {
            return false;
        }

        Map<Pair, Boolean> mp = new HashMap<>();
        return isTargetSum(nums, nums.length-1, sum/2, mp);
    }

    private boolean isTargetSum(int[] nums, int index, int target, Map<Pair, Boolean> mp) {
        Pair p = new Pair(index, target);
        if (mp.containsKey(p)) {
            // System.out.println("pair already present: " + p);
            return mp.get(p);
        }
        if (target == 0) {
            mp.put(p, true);
            return true;
        }
        if (index == 0) {
            mp.put(p, nums[index] == target);
            return nums[index] == target;
        }
        if (target < 0) {
            mp.put(p, false);
            return false;
        }

        // pick
        boolean pick = isTargetSum(nums, index-1, target-nums[index], mp);
        // not pick
        boolean notPick = isTargetSum(nums, index-1, target, mp);

        boolean res = pick || notPick;
        mp.put(p, res);
        return res;
    }
}