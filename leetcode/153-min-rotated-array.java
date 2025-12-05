// this is important question - how to find pivot in rotated array
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        int ans = nums[0];
        if (nums.length == 1) {
            return ans;
        }

        while (left <= right) {
            int mid = (left + right)/2;
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            if (nums[mid] >= nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}