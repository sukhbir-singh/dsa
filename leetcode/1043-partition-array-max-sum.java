import java.util.*;
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        // all these pre conditions are redundant
        if (k == 1) {
            int sm = 0;
            for (int num: arr) {
                sm += num;
            }
            return sm;
        }
        if (arr.length == 1) {
            return arr[0];
        } else if (arr.length == 0) {
            return 0;
        }

        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return SumAfterPartition(arr, 0, arr.length-1, k, dp);
    }

    private int SumAfterPartition(int[] arr, int left, int right, int k, int[] dp) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            return arr[left];
        }
        if (dp[left] != -1) {
            return dp[left];
        }

        int mx = Integer.MIN_VALUE;
        int largest = arr[left];
        for (int i=1; i<=k; i++) {
            int leftIndex = left+i-1;
            if (leftIndex > right) {
                break;
            }
            if (arr[leftIndex] > largest) {
                largest = arr[leftIndex];
            }

            int p1 = largest*i;
            int p2 = SumAfterPartition(arr, left+i, right, k, dp);
            int totalSum = p1 + p2;

            //System.out.println("partitioning at leftIndex=" + leftIndex + ", p1 = " + p1 + ", p2 = " + p2 + " , totalSum = " + totalSum + " provided left = " + left + ", right = " + right);
            mx = Math.max(mx, totalSum);
        }

        dp[left] = mx;
        return mx;
    }
}