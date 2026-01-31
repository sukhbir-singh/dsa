import java.util.*;
class Solution {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        // left to right iteration
        for (int i=1; i<ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        int sum = 0;
        
        // adding last position candy
        sum += candies[ratings.length-1];

        // right to left iteration
        int cur = 0, right = candies[ratings.length-1];
        for (int i=ratings.length-2; i>=0; i--) {
            if (ratings[i] > ratings[i+1]) {
                cur = right + 1;
            } else {
                cur = 1;
            }
            sum += Math.max(cur, candies[i]);
            right = cur;
        }

        return sum;
    }
}