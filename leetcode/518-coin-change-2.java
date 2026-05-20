import java.util.*;
class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
        for (int[] row: dp) Arrays.fill(row, -1);
        return calculateWays(amount, coins, coins.length-1, dp);
    }

    private int calculateWays(int amount, int[] coins, int index, int[][] dp) {
        if (amount == 0) return 1;
        if (amount < 0) return 0;
        if (index == 0) return amount % coins[0] == 0 ? 1 : 0;

        if (dp[index][amount] != -1) return dp[index][amount];

        int take = calculateWays(amount - coins[index], coins, index, dp);
        int notTake = calculateWays(amount, coins, index-1, dp);
        dp[index][amount] = take + notTake;
        return take + notTake;
    }
}