// main condition that needs to be handled is this: when arr[left] = arr[right] = arr[mid]
// in this case you won't be able to determine in which sorted part you are currently present.
class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums[left] == target || nums[right] == target) {
            return true;
        }

        while (left >= 0 && left < nums.length && right >= 0 && right < nums.length && nums[left] == nums[right]) {
            if (nums[left] == target) {
                return true;
            }

            left++;
            right--;
        }

        while (left <= right) {
            int mid = (left + right)/2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}