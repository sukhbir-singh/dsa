import java.util.*;
// O(n^2) solution
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

// O(1) - greedy solution
// Solution2 treats reachable indexes like layers: all positions you can reach with 1 jump 
// are one layer, positions reachable with 2 jumps are the next, and so on.
class Solution2 {
    public int jump(int[] nums) {
        // The starting range of the first jump is [0, 0]
        int answer = 0, n = nums.length;
        int curEnd = 0, curFar = 0;

        for (int i = 0; i < n - 1; ++i) {
            // Update the farthest reachable index of this jump.
            curFar = Math.max(curFar, i + nums[i]);

            // If we finish the starting range of this jump,
            // Move on to the starting range of the next jump.
            if (i == curEnd) {
                answer++;
                curEnd = curFar;
            }
        }

        return answer;
    }
}