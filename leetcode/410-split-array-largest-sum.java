import java.util.*;
class Solution {
    public int splitArray(int[] nums, int k) {
        // remember: this is maximum because each number will be allocated to an subarray. so that minimum allocated will be the maximum of this array elements.
        int left = Arrays.stream(nums).max().getAsInt();
        int right = Arrays.stream(nums).sum();

        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            boolean isPossible = splitArray(nums, k, mid);
            if (isPossible) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean splitArray(int[] nums, int k, int sumT) {
        int cnt = 1, sum = 0;
        for (int n: nums) {
            if (n + sum <= sumT) {
                sum += n;
            } else {
                cnt++;
                sum = n;
            }
        }
        return cnt <= k;
    }
}