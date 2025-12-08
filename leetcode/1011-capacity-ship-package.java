class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = max(weights), right = sum(weights);
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            boolean isPossible = validate(weights, mid, days);
            if (isPossible) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private int max(int[] arr) {
        int mn = 0;
        for (int a: arr) {
            mn = Math.max(a, mn);
        }
        return mn;
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int a: arr) {
            sum += a;
        }
        return sum;
    }

    private boolean validate(int[] weights, int capacity, int days) {
        int d = 0;
        int current = 0;
        for (int w: weights) {
            if (current + w > capacity) {
                d++;
                current = w;
            } else {
                current += w;
            }
        }
        d++;
        //System.out.println("c: " + capacity + ", d: " + d);
        return d <= days;
    }
}