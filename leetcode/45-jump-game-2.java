import java.util.*;
class Solution {
    public int jump(int[] nums) {
        int len = nums.length;
        int[] jumps = new int[len];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;

        for (int i=0; i<len; i++) {
            for (int j=1; j<=nums[i] && i+j<len; j++) {
                jumps[i+j] = Math.min(jumps[i+j], jumps[i]+1);
            }
        }

        return jumps[len-1];
    }
}