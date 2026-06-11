import java.util.*;
// Another approach is to use 2D DP using - Nx(2xk) size array (by considering transaction numbers instead)
class Solution {
    public int maxProfit(int k, int[] prices) {
        int[][][] dp = new int[prices.length][2][k+1];
        for (int[][] arr: dp) {
            for (int[] arr1: arr)
                Arrays.fill(arr1, -1);
        }
        return findMaxProfit(prices, 0, 1, k, dp);
    }

    private int findMaxProfit(int[] prices, int ind, int buy, int transactions, int[][][] dp) {
        if (transactions == 0) {
            return 0;
        }
        if (ind == prices.length) {
            return 0;
        }
        if (dp[ind][buy][transactions] != -1) {
            return dp[ind][buy][transactions];
        }

        int profit = 0;
        if (buy == 1) {
            int yesBuy = -prices[ind] + findMaxProfit(prices, ind+1, 0, transactions, dp);
            int noBuy = findMaxProfit(prices, ind+1, 1, transactions, dp);
            profit = Math.max(yesBuy, noBuy);

        } else {
            int yesSell = prices[ind] + findMaxProfit(prices, ind+1, 1, transactions-1, dp);
            int noSell = findMaxProfit(prices, ind+1, 0, transactions, dp);
            profit = Math.max(yesSell, noSell);
        }

        dp[ind][buy][transactions] = profit;
        return profit;
    }
}