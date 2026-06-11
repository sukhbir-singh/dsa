import java.util.*;
// I solved this problem using DP
// this can also be solved using peak-valley greedy approach in O(n)
class Solution {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return findMaxProfit(prices, 0, 1, dp);
    }

    private int findMaxProfit(int[] prices, int ind, int buy, int[][] dp) {
        if (ind == prices.length) {
            return 0;
        }
        if (dp[ind][buy] != -1) {
            return dp[ind][buy];
        }

        int profit = 0;
        if (buy == 1) {
            int yesBuy = -prices[ind] + findMaxProfit(prices, ind+1, 0, dp);
            int noBuy = findMaxProfit(prices, ind+1, 1, dp);
            profit = Math.max(yesBuy, noBuy);

        } else {
            int yesSell = prices[ind] + findMaxProfit(prices, ind+1, 1, dp);
            int noSell = findMaxProfit(prices, ind+1, 0, dp);
            profit = Math.max(yesSell, noSell);
        }

        dp[ind][buy] = profit;
        return profit;
    }
}