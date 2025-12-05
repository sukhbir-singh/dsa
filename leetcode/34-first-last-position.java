
// solution1: good thing is this below implementation uses standard binary search to find the answer. (better solution)
// solution2: otherwise, lower bound and uppper bound implementatation can also be used with some handling of edge cases.
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int lb = findFirst(nums, target);
        int ub = findLast(nums, target);
        return new int[]{lb, ub};
    }

    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right-left) / 2;
            if (nums[mid] == target) {
                ans = mid;
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right-left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                ans = mid;
                left = mid + 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}