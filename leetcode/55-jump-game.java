class Solution {
    // 0 case was very interesting to handle
    public boolean canJump(int[] nums) {
        int[] reached = new int[nums.length];
        for (int i=0; i<nums.length-1; i++) {
            for (int j=1; j<=nums[i] && i+j <= nums.length-1; j++) {
                reached[i+j] = 1;
            }
        }

        for (int i=1; i<reached.length; i++) {
            if (reached[i] == 0) return false;
        }
        return true;
    }
}

// Better and simpler solution
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