import java.util.*;
// used bottom up dp
class Solution {
    public int climbStairs(int n) {
        int[] arr = new int[n+1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i=2; i<=n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }
}
// top down dp
class Solution2 {
    public int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }

    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
}