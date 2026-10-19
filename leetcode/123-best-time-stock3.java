import java.util.*;

// In this problem, there are three changing states and so three dimensional dp array will be used - Nx2x3
// Another approach is to use 2D DP using - Nx4 size array (by considering transaction numbers instead)
class Solution {
    public int maxProfit(int[] prices) {
        int[][][] dp = new int[prices.length][2][3];
        for (int[][] arr: dp) {
            for (int[] arr1: arr)
                Arrays.fill(arr1, -1);
        }
        return findMaxProfit(prices, 0, 1, 2, dp);
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

// difficult to explain and understand this algo
// Track the best first- and second-transaction buy/profit states in one pass, carrying the first profit into the second buy cost.
class Solution2 {
    public int maxProfit(int[] prices) {
        int t1Cost = Integer.MAX_VALUE, t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0, t2Profit = 0;

        for (int price : prices) {
            // the maximum profit if only one transaction is allowed
            t1Cost = Math.min(t1Cost, price);
            t1Profit = Math.max(t1Profit, price - t1Cost);

            // reinvest the gained profit in the second transaction
            t2Cost = Math.min(t2Cost, price - t1Profit);
            t2Profit = Math.max(t2Profit, price - t2Cost);
        }

        // note: t2 is final profit because first transaction profit is accounted in t2Cost
        return t2Profit;
    }
}