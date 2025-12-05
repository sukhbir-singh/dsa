// three solutions i can think of:
// 1. Find pivot by linear search - O(n), then do binary search with updated pointers
// 2. In first step, find pivot using binary search and then do binary search to find element. O(log n)
// 3. Directly find element using binary search -> best solution. I have implemented this.
// 4. Striver's algo - good and simple algo. based on idea that we can make condition only on the sorted portion.

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        int ans = -1;
        int tp = target >= nums[0] ? 1:2;
        while (left <= right) {
            int mid = (left + right)/2;

            if (nums[mid] == target) {
                return mid;
            }

            // if current part is same as target part - normal binary search
            // else move toward target part

            if ((tp == 1 && nums[mid] >= nums[0]) || (tp == 2 && nums[mid] < nums[0])) {
                // same part
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // different part
                if (tp == 1) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return ans;
    }
}
