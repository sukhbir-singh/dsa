class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = findMax(nums);
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            boolean isValid = validation(nums, mid, threshold);
            if (isValid) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private int findMax(int[] nums) {
        int max = nums[0];
        for (int n: nums) {
            max = Math.max(n, max);
        }
        return max;
    }

    private boolean validation(int[] nums, int d, int t) {
        int sum = 0;
        for (int n: nums) {
            sum += Math.ceil(1.0*n/d);
        }
        System.out.println(d +" " + t +" " + sum);
        return sum <= t;
    }
}