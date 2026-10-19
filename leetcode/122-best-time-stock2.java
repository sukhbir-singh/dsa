import java.util.*;
// I solved this problem using DP
// this can also be solved using peak-valley greedy approach in O(n)

// Important: The key point is we need to consider every peak immediately following a valley to maximize the profit. In case we skip one of the 
// peaks (trying to obtain more profit), we will end up losing the profit over one of the transactions leading to an overall lesser profit.

// Conclusion: peak valley approach is better for this solution
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

// very good approach, can be used in different questions
class Solution2 {
    public int maxProfit(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }
}

// single pass
class Solution3 {
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}