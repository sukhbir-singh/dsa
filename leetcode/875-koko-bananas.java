class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = findMax(piles);
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left)/2;
            long hrs = calcHours(piles, mid);
            if (hrs <= h) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private int findMax(int[] piles) {
        int max = piles[0];
        for (int n: piles) {
            max = Math.max(n , max);
        }
        return max;
    }

    private long calcHours(int[] piles, int speed) {
        long hrs = 0;
        for (int p : piles) {
            hrs += p/speed;
            if (p%speed > 0 ) {
                hrs++;
            }
        }
        //System.out.println(">> speed: " + speed +", hrs: "+ hrs);
        return hrs;
    }
}