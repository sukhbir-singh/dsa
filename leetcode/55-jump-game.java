// this was greedy approach
// DP can also be applied in this question
class Solution {
    public boolean canJump(int[] nums) {
        int[] reached = new int[nums.length];
        reached[0] = 1;

        for (int i=0; i<nums.length-1; i++) {
            // 0 case was interesting to handle
            if (reached[i] == 0) continue; 

            for (int j=1; j<=nums[i] && i+j <= nums.length-1; j++) {
                reached[i+j] = 1;
            }
        }

        return reached[nums.length - 1] == 1;
    }
}

// Better and simpler solution - greedy
class Solution2 {
    public boolean canJump(int[] nums) {
        int maxIndexReach = 0;
        // loop till last second element
        for (int i=0; i<nums.length-1; i++) {
            if (maxIndexReach < i) return false;
            maxIndexReach = Math.max(maxIndexReach, i + nums[i]);
        }
        return maxIndexReach >= nums.length-1;
    }
}