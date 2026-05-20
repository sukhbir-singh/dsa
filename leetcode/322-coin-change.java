import java.util.*;
// Very important: In DP question - take variables in parameter and result should be returned by the recursive method. dont start passing result in parameter, it will start giving cached wrong result.
// trying all possibilities
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int[] row : dp) Arrays.fill(row, -2); // -2 = unvisited
        return changeCoin(coins, amount, coins.length - 1, dp);
    }

    private int changeCoin(int[] coins, int amt, int index, int[][] dp) {
        if (amt == 0) return 0;
        if (amt < 0 || index < 0) return -1;
        if (dp[index][amt] != -2) return dp[index][amt];

        int take = changeCoin(coins, amt - coins[index], index, dp);
        int notTake = changeCoin(coins, amt, index - 1, dp);

        int ans;
        if (take == -1 && notTake == -1) ans = -1;
        else if (take == -1) ans = notTake;
        else if (notTake == -1) ans = take + 1;
        else ans = Math.min(take + 1, notTake);

        dp[index][amt] = ans;
        return ans;
    }
}

// Bottom-up tabulation (unbounded knapsack pattern)
// arr[j] = minimum number of coins required to make amount j
class Solution2 {
    public int coinChange(int[] coins, int amount) {
        int cl = coins.length;

        // dp table of size (amount + 1) so indices 0..amount are valid
        int arr[] = new int[amount+1];

        // Base case: 0 coins are needed to make amount 0
        arr[0]=0;

        // Initialize all other amounts as "unreachable".
        // Use MAX_VALUE - 1 (not MAX_VALUE) so that "1 + arr[...]" never overflows.
        for(int i=1;i<amount+1;++i){
            arr[i] = Integer.MAX_VALUE-1;
        }

        // Outer loop: consider one coin at a time
        for(int i=0;i<cl;i++){
            // Inner loop: try to improve arr[j] for every amount j from 1..amount
            // Left-to-right traversal allows the same coin to be reused (unbounded supply),
            // because arr[j - coins[i]] may have already been updated in THIS same i-iteration.
            for(int j=1;j<amount+1;j++){
                // Can only use this coin if it fits into amount j
                if(j>=coins[i]){
                    // Two choices:
                    //   - keep arr[j] as is (don't use coins[i] right now)
                    //   - use one coin of coins[i] on top of the best way to make (j - coins[i])
                    arr[j] = Math.min(arr[j], 1+ arr[j-coins[i]]);     
                }
            }
        }
        
        // If arr[amount] was never updated, the amount is unreachable with given coins
        if(arr[amount] >= Integer.MAX_VALUE-1){
            return -1;
        }
        return arr[amount];
    }
}